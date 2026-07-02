package com.example.Services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.dao.Estudiantedao;
import com.example.entities.Estudiante;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EstudianteServiceImpl implements EstudianteService {

    private final Estudiantedao estudianteDao;

    @Override
    public void saveEstudiante(Estudiante estudiante) {
        estudianteDao.save(estudiante);
    }

    @Override
    public List<Estudiante> getAllEstudiantes() {
        return estudianteDao.findAll();
    }

    @Override
    public Estudiante getEstudianteById(int id) {
        return estudianteDao.findById(id).orElseThrow(() ->
                new RuntimeException("Estudiante no encontrado con id: " + id));
    }

    @Override
    public void deleteEstudiante(int id) {
        estudianteDao.deleteById(id);
    }
}