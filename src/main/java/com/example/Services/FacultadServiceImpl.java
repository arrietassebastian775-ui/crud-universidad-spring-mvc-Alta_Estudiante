package com.example.Services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.dao.Facultaddao;
import com.example.entities.Facultad;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class FacultadServiceImpl implements FacultadService {

    private final Facultaddao facultadDao;

    @Override
    public void saveFacultad(Facultad facultad) {
        facultadDao.save(facultad);
    }

    @Override
    public List<Facultad> getAllFacultades() {
        return facultadDao.findAll();
    }

    @Override
    public List<Facultad> getAll() {
        return facultadDao.findAll();
    }
}
