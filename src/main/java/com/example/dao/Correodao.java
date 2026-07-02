package com.example.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.entities.Correo;
import com.example.entities.Estudiante;
import com.example.entities.Profesor;

import java.util.List;


public interface Correodao extends JpaRepository<Correo, Integer> {

    boolean existsByEstudiante(Estudiante estudiante);
    void deleteByEstudiante(Estudiante estudiante);
    List<Correo> findByEstudiante(Estudiante estudiante);

    boolean existsByProfesor(Profesor profesor);
    void deleteByProfesor(Profesor profesor);
    List<Correo> findByProfesor(Profesor profesor);

}
