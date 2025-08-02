package gestiontusanatorio.loginLiceo.front.Controllers;

import gestiontusanatorio.back.Services.UsuarioService;
import gestiontusanatorio.back.Models.Usuarios;
import gestiontusanatorio.loginLiceo.front.Login;

public class LoginController {
    private UsuarioService userService = new UsuarioService();
    private Login login;

    public LoginController(Login login) {
        this.login = login;
    }

    public void login(String username, String password) {
        Usuarios usuario = userService.login(username, password);

        if (usuario != null) {
            login.mostrarMensaje("¡Bienvenido, " + usuario.getNombres() + "!");
            // Aquí podés abrir la siguiente ventana y cerrar el login si querés.
        } else {
            login.mostrarMensaje("Usuario o contraseña incorrectos.");
        }
    }
}

