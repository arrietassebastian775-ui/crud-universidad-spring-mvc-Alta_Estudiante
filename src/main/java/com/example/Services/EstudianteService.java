package com.example.Services;

import java.util.List;

import com.example.entities.Estudiante;

public interface EstudianteService {
    void saveEstudiante(Estudiante estudiante);
    List<Estudiante> getAllEstudiantes();
    Estudiante getEstudianteById(int id);
    void deleteEstudiante(int id);
}