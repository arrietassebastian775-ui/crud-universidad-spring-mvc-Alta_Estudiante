package com.example.Services;

import java.util.List;

import org.jspecify.annotations.Nullable;

import com.example.entities.Facultad;

public interface FacultadService {
    void saveFacultad(Facultad facultad);
    List<Facultad> getAllFacultades();
    @Nullable
    Object getAll();
}
