package com.example.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.entities.Estudiante;

public interface Estudiantedao extends JpaRepository<Estudiante, Integer> {


}
