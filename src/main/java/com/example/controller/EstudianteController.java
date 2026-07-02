package com.example.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.logging.Logger;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.example.Services.EstudianteService;
import com.example.Services.FacultadService;
import com.example.entities.Correo;
import com.example.entities.Estudiante;
import com.example.entities.Telefono;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/estudiantes")
@RequiredArgsConstructor
public class EstudianteController {

    private static final Logger LOG = Logger.getLogger("EstudianteController");

    private final FacultadService facultadService;
    private final EstudianteService estudianteService;

    @GetMapping("/listar")
    public String listarEstudiantes(Model model) {
        model.addAttribute("estudiantes", estudianteService.getAllEstudiantes());
        return "listadoEstudiantes";
    }

    @GetMapping("/Alta")
    public String mostrarFormulario(Model model) {
        Estudiante estudiante = new Estudiante();
        estudiante.setTelefonos(new HashSet<>());
        estudiante.setCorreos(new HashSet<>());

        model.addAttribute("estudiante", estudiante);
        model.addAttribute("facultades", facultadService.getAllFacultades());
        return "altaEstudiante";
    }

    @PostMapping("/persistir")
    public String procesarFormularioAltaModificacion(@ModelAttribute Estudiante estudiante,
            @RequestParam(name = "numerostlf", required = false) String numtlf,
            @RequestParam(name = "mail", required = false) String emails,
            @RequestParam(name = "file", required = false) MultipartFile file) throws IOException {

        LOG.info("Estudiante encontrado :");
        LOG.info(estudiante.toString());

        if (numtlf != null && !numtlf.isBlank()) {
            Set<Telefono> numerostlf = new HashSet<>();
            List<String> listadoNumeros = Arrays.asList(numtlf.split(";"));
            listadoNumeros.forEach(numero -> {
                numerostlf.add(Telefono.builder().numero(numero.trim()).estudiante(estudiante).build());
            });
            estudiante.setTelefonos(numerostlf);
        }

        if (emails != null && !emails.isBlank()) {
            Set<Correo> dirCorreos = new HashSet<>();
            List<String> listadoCorreos = Arrays.asList(emails.split(";"));
            listadoCorreos.forEach(correo -> {
                dirCorreos.add(Correo.builder().direccion(correo.trim()).estudiante(estudiante).build());
            });
            estudiante.setCorreos(dirCorreos);
        }

        if (file != null && !file.isEmpty()) {
            String nombreArchivo = UUID.randomUUID() + "_" + file.getOriginalFilename();
            Path destino = Paths.get("uploads", nombreArchivo);
            Files.createDirectories(destino.getParent());
            file.transferTo(destino);
            estudiante.setFoto(nombreArchivo);
        }

        estudianteService.saveEstudiante(estudiante);

        return "redirect:/estudiantes/listar";
    }

    @GetMapping("/details/{id}")
    public String detalles(@PathVariable int id, Model model) {
        model.addAttribute("estudiante", estudianteService.getAllEstudiantes().stream()
                .filter(e -> e.getId() == id)
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Estudiante no encontrado con id: " + id)));
        return "detailsEstudiante";
    }
}