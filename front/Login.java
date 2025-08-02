package gestiontusanatorio.loginLiceo.front;

import gestiontusanatorio.loginLiceo.front.Controllers.LoginController;

import javax.swing.*;
import java.awt.*;

public class Login extends JFrame {
    private JTextField txtUsername;
    private JPasswordField txtContrasena;
    private JButton btnIngresar, btnRegistrarse;

    private LoginController controller;

    public Login() {
        setTitle("Login");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(350, 200);
        setLocationRelativeTo(null);
        setLayout(new GridBagLayout());

        controller = new LoginController(this); // <-- Constructor recibe this

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);

        JLabel lblUsuario = new JLabel("Username:");
        txtUsername = new JTextField(15);

        JLabel lblContrasena = new JLabel("Contraseña:");
        txtContrasena = new JPasswordField(15);

        btnIngresar = new JButton("Ingresar");
        btnRegistrarse = new JButton("Registrarse");

        gbc.anchor = GridBagConstraints.WEST;

        gbc.gridx = 0;
        gbc.gridy = 0;
        add(lblUsuario, gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        add(txtUsername, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.fill = GridBagConstraints.NONE;
        add(lblContrasena, gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        add(txtContrasena, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.CENTER;
        add(btnIngresar, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        add(btnRegistrarse, gbc);

        btnIngresar.addActionListener(e -> {
            String username = txtUsername.getText();
            String password = new String(txtContrasena.getPassword());
            controller.login(username, password);
        });

        btnRegistrarse.addActionListener(e -> {
            // Aquí abrirías la ventana de registro
            Registro registro = new Registro();
            registro.setVisible(true);
            dispose();
        });

        setVisible(true);
    }

    public void mostrarMensaje(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje);
    }

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception ignored) {}

        SwingUtilities.invokeLater(Login::new);
    }
}

