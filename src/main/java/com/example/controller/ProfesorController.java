package com.example.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.Services.FacultadService;
import com.example.Services.ProfesorService;
import com.example.entities.Profesor;

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
        model.addAttribute("facultades", facultadService.getAll());
        return "profesores/altaModificacionProfesores";
    }

    @PostMapping("/guardar")
    public String guardar(@Valid Profesor profesor, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("facultades", facultadService.getAll());
            return "profesores/altaModificacionProfesores";
        }
        profesorService.saveProfesor(profesor);
        return "redirect:/profesores";
    }

    @GetMapping("/editar/{id}")
    public String editar(@PathVariable int id, Model model) {
        model.addAttribute("profesor", profesorService.getProfesorById(id));
        model.addAttribute("facultades", facultadService.getAll());
        return "profesores/altaModificacionProfesores";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable int id) {
        profesorService.deleteProfesor(id);
        return "redirect:/profesores";
    }
}


