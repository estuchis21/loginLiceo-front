package gestiontusanatorio.loginLiceo.front.Controllers;

import gestiontusanatorio.back.Models.Usuarios;
import gestiontusanatorio.back.Services.UsuarioService;
import gestiontusanatorio.loginLiceo.front.Registro;

/**
 *
 * @author Esteban
 */
public class RegistroController {
    private UsuarioService userService = new UsuarioService();
    private Registro registro;
    
    // Constructor: corrige el parámetro y la asignación
    public RegistroController(Registro registro) {
        this.registro = registro;
    }
    
    public void registro(Usuarios users){     
        try {
            boolean exito = userService.registro(users);
            if (exito) {
                registro.mostrarMensaje("Registro exitoso.");
            } else {
                registro.mostrarMensaje("Error al registrar usuario.");
            }
        } catch (Exception e) {
            // El catch debe recibir Exception o Throwable, no 'e' solo
            registro.mostrarMensaje("Error: " + e.getMessage());
            e.printStackTrace();
        }
    }
}

