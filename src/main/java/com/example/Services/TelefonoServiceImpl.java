package com.example.Services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.dao.Telefonodao;
import com.example.entities.Estudiante;
import com.example.entities.Profesor;
import com.example.entities.Telefono;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TelefonoServiceImpl implements TelefonoService {

    private final Telefonodao telefonodao;

    @Override
    public Telefono saveTelefono(Telefono telefono) {
        return telefonodao.save(telefono);
    }

    @Override
    public List<Telefono> getAllTelefonos() {
        return telefonodao.findAll();
    }

    @Override
    public void deleteTelefonoById(Integer id) {
        telefonodao.deleteById(id);
    }

    @Override
    public boolean existsByEstudiante(Estudiante estudiante) {
        return telefonodao.existsByEstudiante(estudiante);
    }

    @Override
    public List<Telefono> findByEstudiante(Estudiante estudiante) {
        return telefonodao.findByEstudiante(estudiante);
    }

    @Override
    public void deleteByEstudiante(Estudiante estudiante) {
        telefonodao.deleteByEstudiante(estudiante);
    }

    @Override
    public boolean existsByProfesor(Profesor profesor) {
        return telefonodao.existsByProfesor(profesor);
    }

    @Override
    public List<Telefono> findByProfesor(Profesor profesor) {
        return telefonodao.findByProfesor(profesor);
    }

    @Override
    public void deleteByProfesor(Profesor profesor) {
        telefonodao.deleteByProfesor(profesor);
    }

    
}
