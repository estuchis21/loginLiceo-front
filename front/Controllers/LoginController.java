package gestiontusanatorio.loginLiceo.front.Controllers;

import gestiontusanatorio.back.Models.Usuarios;
import gestiontusanatorio.back.Services.UsuarioService;
import gestiontusanatorio.loginLiceo.front.Login;

public class LoginController {
    private UsuarioService userService = new UsuarioService();
    private Login login;

    // Constructor recibe la ventana Login
    public LoginController(Login login) {
        this.login = login;
    }

    /**
     * Retorna el objeto Usuarios si login es correcto, sino null.
     */
    public Usuarios login(String username, String password) {
        Usuarios usuario = userService.login(username, password);

        if (usuario != null) {
            login.mostrarMensaje("¡Bienvenido, " + usuario.getNombres() + "!");
            return usuario;
        } else {
            login.mostrarMensaje("Usuario o contraseña incorrectos.");
            return null;
        }
    }
    
    public int obtenerIdObraSocialPaciente(int idPaciente) {
        return userService.obtenerIdObraSocialPaciente(idPaciente);
    }
}
