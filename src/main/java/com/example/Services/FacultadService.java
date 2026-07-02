package com.example.Services;

import java.util.List;

import com.example.entities.Facultad;

public interface FacultadService {
    void saveFacultad(Facultad facultad);
    List<Facultad> getAllFacultades();
    List<Facultad> getAll();
}
