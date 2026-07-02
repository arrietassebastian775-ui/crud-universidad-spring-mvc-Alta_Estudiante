package com.example;

import java.time.LocalDate;
import java.util.Set;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.Services.EstudianteService;
import com.example.Services.FacultadService;
import com.example.entities.Correo;
import com.example.entities.Estudiante;
import com.example.entities.Facultad;
import com.example.entities.Telefono;
import com.example.model.Genero;

import lombok.RequiredArgsConstructor;

@SpringBootApplication
@RequiredArgsConstructor
public class CrudEstudiantesSpringMvcApplication implements CommandLineRunner {

	private final EstudianteService estudianteService;
	private final FacultadService facultadService;

	public static void main(String[] args) {
		SpringApplication.run(CrudEstudiantesSpringMvcApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		Facultad facultad1 = Facultad.builder()
			.nombre("MATEMATICAS")
			.build();
		
		Facultad facultad2 = Facultad.builder()
			.nombre("CIENCIAS")
			.build();
		
		Facultad facultad3 = Facultad.builder()
			.nombre("LETRAS")
			.build();

		Facultad facultad4 = Facultad.builder()
			.nombre("GEOLOGIA")
			.build();

			Facultad facultad5 = Facultad.builder()
			.nombre("INGENIERIA")
			.build();

		facultadService.saveFacultad(facultad1);
		facultadService.saveFacultad(facultad2);
		facultadService.saveFacultad(facultad3);
		facultadService.saveFacultad(facultad4);
		facultadService.saveFacultad(facultad5);

		Estudiante estudiante1 = Estudiante.builder()
			.nombre("Javier")
			.primerApellido("Gonzalez")
			.segundoApellido("Sanch")
			.genero(Genero.HOMBRE)
			.FechadeMatriculacionFacultad(LocalDate.of(2026, 01, 01))
			.facultad(facultad5)
			.correos(Set.of(
				Correo.builder()
					.direccion("teg@out.com")
					.build(),	
				Correo.builder()
					.direccion("teg2@out.com")
					.build()
				))
			.telefonos(Set.of(
				Telefono.builder()
					.numero("123456789")
					.build(),
				Telefono.builder()
					.numero("987654321")
					.build()
			))
			.build();

			estudiante1.getCorreos().forEach(correo -> correo.setEstudiante(estudiante1));
			estudiante1.getTelefonos().forEach(telefono -> telefono.setEstudiante(estudiante1));

			Estudiante estudiante2 = Estudiante.builder()
			.nombre("Alberto")
			.primerApellido("Gonzalez")
			.segundoApellido("Sanchez")
			.genero(Genero.MUJER)
		.FechadeMatriculacionFacultad(LocalDate.of(2026, 01, 01))
			.facultad(facultad4)
			.correos(Set.of(
				Correo.builder()
					.direccion("hg@out.com")
					.build(),	
				Correo.builder()
					.direccion("hg@out.com")
					.build()
				))
			.telefonos(Set.of(
				Telefono.builder()
					.numero("111111111")
					.build(),
				Telefono.builder()
					.numero("222222222")
					.build()
			))
			.build();

			estudiante2.getCorreos().forEach(correo -> correo.setEstudiante(estudiante2));
			estudiante2.getTelefonos().forEach(telefono -> telefono.setEstudiante(estudiante2));

			estudianteService.saveEstudiante(estudiante1);
			estudianteService.saveEstudiante(estudiante2);
	}

}