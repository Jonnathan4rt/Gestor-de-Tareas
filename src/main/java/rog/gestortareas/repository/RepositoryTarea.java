package rog.gestortareas.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import rog.gestortareas.model.Tarea;

public interface RepositoryTarea extends JpaRepository<Tarea, Integer> {
}
