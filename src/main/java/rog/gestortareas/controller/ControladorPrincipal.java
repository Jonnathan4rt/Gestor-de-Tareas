package rog.gestortareas.controller;

import jakarta.annotation.PostConstruct;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import lombok.Data;
import org.primefaces.PrimeFaces;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import rog.gestortareas.model.Tarea;
import rog.gestortareas.services.ITareaService;

import java.util.List;
@Component
@Data
@ViewScoped
public class ControladorPrincipal {

    @Autowired
    private ITareaService tareaService;

    private List<Tarea> tareas;
    private Tarea tareaSeleccionada;

    private static final Logger logger = LoggerFactory.getLogger(ControladorPrincipal.class);

    @PostConstruct
    public void init() {
        cargarDatos();
    }

    public void cargarDatos() {
        this.tareas = tareaService.listarTareas();
        tareas.forEach((tarea) -> logger.info(tarea.toString()));
    }

    public void agregarTarea() {
        logger.info("Se ha creado un objeto Tarea seleccionada para el caso de agregar");
        this.tareaSeleccionada = new Tarea();
    }

    // Agregar Tarea
    public void guardarTarea() {
        logger.info("Tarea a guardar: " + this.tareaSeleccionada);
        if (this.tareaSeleccionada.getIdTarea() == null) {
            this.tareaService.guardarTarea(this.tareaSeleccionada);
            this.tareas.add(this.tareaSeleccionada);
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage("Tarea Agregada"));
        } else { // Modificar Tarea (Actualizar)
            this.tareaService.guardarTarea(this.tareaSeleccionada);
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage("Tarea Actualizada"));
        }
        // Se oculta la ventana modal
        PrimeFaces.current().executeScript("PF('windowModalTarea').hide()");
        // Se actualiza la tabla
        PrimeFaces.current().ajax().update("shape-tareas:messages",
                "shape-tareas:tarea-table");
        // Se reinicia
        this.tareaSeleccionada = null;
    }

    public void eliminarTarea() {
        logger.info("Tarea a eliminar: " + this.tareaSeleccionada);
        this.tareaService.eliminarTarea(this.tareaSeleccionada);
        // Se elimina el registro de la lista de tareas
        this.tareas.remove(this.tareaSeleccionada);
        // Se reinicia el objeto seleccionado de la tabla
        this.tareaSeleccionada = null;
        FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage("Tarea Eliminada"));
        PrimeFaces.current().ajax().update("shape-tareas:messages",
                "shape-tareas:tarea-table");
    }
}
