package gestiontusanatorio.loginLiceo.front.Controllers;

import gestiontusanatorio.back.Models.Obras_sociales;
import gestiontusanatorio.back.Models.TurnosAsignados;
import gestiontusanatorio.back.Models.TurnosDisponibles;
import gestiontusanatorio.back.Services.TurnosService;

import java.util.List;

public class AsignarTurnosController {

    private TurnosService turnosService = new TurnosService();

    public List<TurnosDisponibles> getTodosLosTurnosDisponibles() {
        return turnosService.obtenerTurnosDisponiblesNoAsignados();
    }

    public List<Obras_sociales> getObrasSociales() {
        return turnosService.getObrasSociales();
    }
    
    public boolean asignarTurno(int idTurnoDisponible, int idPaciente, int idObraSocial) {
        return turnosService.asignarTurno(idTurnoDisponible, idPaciente, idObraSocial);
    }

    public List<TurnosAsignados> getTurnosPorPaciente(int idPaciente) {
        return turnosService.getTurnosPorPaciente(idPaciente);
    }
    // Si necesitás otros métodos del repo también podés exponerlos acá
}
