package rog.gestortareas.services;

import rog.gestortareas.model.Tarea;

import java.util.List;

public interface ITareaService {
    List<Tarea> listarTareas();

    Tarea encontrarTareaPorId(Integer idTarea);

    void guardarTarea(Tarea tarea);

    void eliminarTarea(Tarea tarea);
}
