package com.example.Services;

import java.util.List;

import com.example.entities.Profesor;

public interface ProfesorService {
    void saveProfesor(Profesor profesor);
    List<Profesor> getAllProfesores();

}
