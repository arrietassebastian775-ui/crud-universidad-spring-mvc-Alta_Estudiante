package com.example.Services;

import java.util.List;

import com.example.entities.Estudiante;
import com.example.entities.Profesor;
import com.example.entities.Telefono;

public interface TelefonoService {

    List<Telefono> getAllTelefonos1();
	Telefono saveTelefono1(Telefono telefono);
	boolean existsByEstudiante(Estudiante estudiante);
	void deleteByEstudiante(Estudiante estudiante);
	List<Telefono> findByEstudiante(Estudiante estudiante);

    List<Telefono> getAllTelefonos2();
	Telefono saveTelefono(Telefono telefono);
	boolean existsByProfesor(Profesor profesor);
	void deleteByProfesor(Profesor profesor);
	List<Telefono> findByProfesor(Profesor profesor);

}
