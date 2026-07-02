package com.example.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.entities.Estudiante;
import com.example.entities.Telefono;

public interface Telefonodao extends JpaRepository<Estudiante, Integer> {

    boolean existByestudiante(Estudiante estudiante);
    void deleteByestudiante(Estudiante estudiante);
    List<Telefono> findByEstudiante(Estudiante estudiante);

}
