package com.example.Services;

import java.util.List;

import com.example.entities.Correo;
import com.example.entities.Estudiante;
import com.example.entities.Profesor;

public interface CorreoService {

    Correo savecorreo(Correo correo);

    List<Correo> getAllCorreos();

    void deleteCorreoById(Integer id);

    boolean existsByEstudiante(Estudiante estudiante);
    List<Correo> findByEstudiante(Estudiante estudiante);
    void deleteByEstudiante(Estudiante estudiante);

    boolean existsByProfesor(Profesor profesor);
    List<Correo> findByProfesor(Profesor profesor);
    void deleteByProfesor(Profesor profesor);

    
   
}
