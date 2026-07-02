package com.example.Services;

import java.util.List;

import com.example.entities.Correo;
import com.example.entities.Estudiante;

public interface CorreoService {

    Correo savecorreo(Correo correo);

    List<Correo> getAllCorreos();

    void deleteCorreoById(Integer id);

    boolean existsByEstudiante(Estudiante estudiante);

    List<Correo> findByEstudiante(Estudiante estudiante);

    void deleteByEstudiante(Estudiante estudiante);

    
   
}
