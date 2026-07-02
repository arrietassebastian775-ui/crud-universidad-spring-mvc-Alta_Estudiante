package com.example.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.example.Services.FacultadService;
import com.example.Services.ProfesorService;
import com.example.entities.Correo;
import com.example.entities.Profesor;
import com.example.entities.Telefono;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/profesores")
@RequiredArgsConstructor
public class ProfesorController {

    private final ProfesorService profesorService;
    private final FacultadService facultadService;

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("profesores", profesorService.getAllProfesores());
        return "profesores/listadoProfesores";
    }

    @GetMapping("/nuevo")
    public String nuevo(Model model) {
        model.addAttribute("profesor", new Profesor());
        model.addAttribute("facultades", facultadService.getAllFacultades());
        return "profesores/altaModificacionProfesores";
    }

    @GetMapping("/details/{id}")
    public String detalles(@PathVariable int id, Model model) {
        model.addAttribute("profesor", profesorService.getProfesorById(id));
        return "profesores/details";
    }

    @PostMapping("/guardar")
    public String guardar(@Valid Profesor profesor, BindingResult result, Model model,
            @RequestParam(name = "numerosTelefono", required = false) String numerosTelefono,
            @RequestParam(name = "direccionesCorreo", required = false) String direccionesCorreo,
            @RequestParam(name = "file", required = false) MultipartFile file) throws IOException {

        if (result.hasErrors()) {
            model.addAttribute("facultades", facultadService.getAllFacultades());
            return "profesores/altaModificacionProfesores";
        }

        if (numerosTelefono != null && !numerosTelefono.isBlank()) {
            Set<Telefono> telefonos = new HashSet<>();
            for (String numero : numerosTelefono.split(";")) {
                telefonos.add(Telefono.builder().numero(numero.trim()).profesor(profesor).build());
            }
            profesor.setTelefonos(telefonos);
        }

        if (direccionesCorreo != null && !direccionesCorreo.isBlank()) {
            Set<Correo> correos = new HashSet<>();
            for (String direccion : direccionesCorreo.split(";")) {
                correos.add(Correo.builder().direccion(direccion.trim()).profesor(profesor).build());
            }
            profesor.setEmails(correos);
        }

        if (file != null && !file.isEmpty()) {
            String nombreArchivo = UUID.randomUUID() + "_" + file.getOriginalFilename();
            Path destino = Paths.get("uploads", nombreArchivo);
            Files.createDirectories(destino.getParent());
            file.transferTo(destino);
            profesor.setFoto(nombreArchivo);
        }

        profesorService.saveProfesor(profesor);
        return "redirect:/profesores";
    }

    @GetMapping("/editar/{id}")
    public String editar(@PathVariable int id, Model model) {
        model.addAttribute("profesor", profesorService.getProfesorById(id));
        model.addAttribute("facultades", facultadService.getAllFacultades());
        return "profesores/altaModificacionProfesores";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable int id) {
        profesorService.deleteProfesor(id);
        return "redirect:/profesores";
    }
}


