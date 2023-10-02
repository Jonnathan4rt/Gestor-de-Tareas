package rog.gestortareas.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rog.gestortareas.model.Tarea;
import rog.gestortareas.repository.RepositoryTarea;


import java.util.List;

@Service
public class TareaService implements ITareaService {

    @Autowired
    private RepositoryTarea repositoryTarea;

    @Override
    public List<Tarea> listarTareas() {
        return repositoryTarea.findAll();
    }

    @Override
    public Tarea encontrarTareaPorId(Integer idTarea) {
        return repositoryTarea.findById(idTarea).orElse(null);
    }

    @Override
    public void guardarTarea(Tarea tarea) {
        repositoryTarea.save(tarea);
    }

    @Override
    public void eliminarTarea(Tarea tarea) {
        repositoryTarea.delete(tarea);
    }
}