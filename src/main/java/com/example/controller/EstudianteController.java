package com.example.controller;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Logger;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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

    @PostMapping("/Save")
    public String procesarFormularioAltaModificacion(Estudiante estudiante, Model model) {

        estudiante.getTelefonos().forEach(t -> t.setEstudiante(estudiante));
        estudiante.getCorreos().forEach(c -> c.setEstudiante(estudiante));

        estudianteService.saveEstudiante(estudiante);

        return "redirect:/estudiantes/listar";
    }

    @PostMapping("/persistir")
    public String procesarFormularioAltaModificacion(@ModelAttribute Estudiante estudiante,
            @RequestParam(name = "numerostlf") String numtlf,
            @RequestParam(name = "mail") String emails) {

        LOG.info("Estudiante encontrado :");
        LOG.info(estudiante.toString());
        LOG.info(numtlf);
        LOG.info(emails);

        Set<Telefono> numerostlf = new HashSet<>();
        Set<Correo> dirCorreos = new HashSet<>();

        if (!numtlf.isEmpty() && !numtlf.isBlank()) {

            String[] arraynumTlf = numtlf.split(";");
            List<String> listadoNumeros = Arrays.asList(arraynumTlf);
            listadoNumeros.forEach(numero -> {
                numerostlf.add(Telefono.builder().numero(numero).estudiante(estudiante).build());
            });
            estudiante.setTelefonos(numerostlf);

        }

        if (!emails.isEmpty() && !emails.isBlank()) {

            String[] email = emails.split(";");
            List<String> listadoCorreos = Arrays.asList(email);
            listadoCorreos.forEach(correo -> {
                dirCorreos.add(Correo.builder().direccion(correo).estudiante(estudiante).build());
            });
            estudiante.setCorreos(dirCorreos);
        }

        estudianteService.saveEstudiante(estudiante);

        return "redirect:/estudiantes/listar";
    }

}
