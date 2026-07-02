package com.example.Services;

import java.util.List;

import com.example.entities.Estudiante;
import com.example.entities.Profesor;
import com.example.entities.Telefono;

public interface TelefonoService {

    Telefono saveTelefono(Telefono telefono);
    List<Telefono> getAllTelefonos();
    void deleteTelefonoById(Integer id);

    boolean existsByEstudiante(Estudiante estudiante);
    List<Telefono> findByEstudiante(Estudiante estudiante);
    void deleteByEstudiante(Estudiante estudiante);

    boolean existsByProfesor(Profesor profesor);
    List<Telefono> findByProfesor(Profesor profesor);
    void deleteByProfesor(Profesor profesor);

}
