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
        setSize(600, 500);
        setLocationRelativeTo(null);

        // Panel contenedor general con fondo azul marino (navy)
        JPanel backgroundPanel = new JPanel();
        backgroundPanel.setBackground(new Color(0, 0, 128));  // Azul marino
        backgroundPanel.setLayout(new GridBagLayout());

        // Panel interno con fondo rojo sólido que contiene el formulario
        JPanel loginPanel = new JPanel(new GridBagLayout());
        loginPanel.setBackground(new Color(204, 0, 0)); // Rojo tipo Liceo

        controller = new LoginController(this);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.anchor = GridBagConstraints.WEST;

        JLabel lblUsuario = new JLabel("Username:");
        lblUsuario.setForeground(Color.WHITE);
        txtUsername = new JTextField(15);

        JLabel lblContrasena = new JLabel("Contraseña:");
        lblContrasena.setForeground(Color.WHITE);
        txtContrasena = new JPasswordField(15);

        btnIngresar = new JButton("Ingresar");
        btnRegistrarse = new JButton("Registrarse");

        gbc.gridx = 0;
        gbc.gridy = 0;
        loginPanel.add(lblUsuario, gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        loginPanel.add(txtUsername, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.fill = GridBagConstraints.NONE;
        loginPanel.add(lblContrasena, gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        loginPanel.add(txtContrasena, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.CENTER;
        loginPanel.add(btnIngresar, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        loginPanel.add(btnRegistrarse, gbc);

        btnIngresar.addActionListener(e -> {
            String username = txtUsername.getText();
            String password = new String(txtContrasena.getPassword());
            controller.login(username, password);
        });

        btnRegistrarse.addActionListener(e -> {
            Registro registro = new Registro();
            registro.setVisible(true);
            dispose();
        });

        // Agrego el panel rojo (loginPanel) dentro del panel azul (backgroundPanel)
        backgroundPanel.add(loginPanel);

        // Seteo el panel azul como contenido principal
        setContentPane(backgroundPanel);

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
