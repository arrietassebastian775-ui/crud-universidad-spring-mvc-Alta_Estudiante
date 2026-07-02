package com.example.Services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.dao.Correodao;
import com.example.entities.Correo;
import com.example.entities.Estudiante;
import com.example.entities.Profesor;

import lombok.RequiredArgsConstructor;
@RequiredArgsConstructor
@Service
public class CorreoServiceImpl implements CorreoService {

    private final Correodao correodao;

    @Override
    public Correo savecorreo(Correo correo) {
        return correodao.save(correo);
    }

    @Override
    public List<Correo> getAllCorreos() {
        return correodao.findAll();
    }

    @Override
    public void deleteCorreoById(Integer id) {
        correodao.deleteById(id);
    }

    @Override
    public boolean existsByEstudiante(Estudiante estudiante) {
        return correodao.existsByEstudiante(estudiante);
    }

    @Override
    public List<Correo> findByEstudiante(Estudiante estudiante) {
        return correodao.findByEstudiante(estudiante);
    }

    @Override
    public void deleteByEstudiante(Estudiante estudiante) {
        correodao.deleteByEstudiante(estudiante);
    }

    @Override
    public boolean existsByProfesor(Profesor profesor) {
        return correodao.existsByProfesor(profesor);
    }

    @Override
    public List<Correo> findByProfesor(Profesor profesor) {
        return correodao.findByProfesor(profesor);
    }

    @Override
    public void deleteByProfesor(Profesor profesor) {
        correodao.deleteByProfesor(profesor);
    }

}