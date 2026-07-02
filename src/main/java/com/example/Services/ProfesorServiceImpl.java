package com.example.Services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.dao.ProfesorDao;
import com.example.entities.Profesor;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProfesorServiceImpl implements ProfesorService {


    private final ProfesorDao profesorDao;



    @Override
    public List<Profesor> getAllProfesores() {
        // Implementation here
        return profesorDao.findAll();
    }

    @Override
    public Profesor getProfesorById(int id) {
        // Implementation here
        return profesorDao.findById(id).orElseThrow(() -> 
            new RuntimeException("Profesor no encontrado con id: " + id));
    }

    @Override
    public Profesor saveProfesor(Profesor profesor) {
        // Implementation here
        return profesorDao.save(profesor);
    }

    @Override
    public void deleteProfesor(int id) {
        // Implementation here
        profesorDao.deleteById(id);
    }

    @Override
    public void deleteProfesor(Profesor profesor) {
        // Implementation here
        profesorDao.delete(profesor);
    }

    @Override
    public Profesor updateProfesor(Profesor profesor) {
        // Implementation here
        return profesorDao.save(profesor);
    }

}
