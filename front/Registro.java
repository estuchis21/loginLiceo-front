package gestiontusanatorio.loginLiceo.front;

import gestiontusanatorio.loginLiceo.front.Controllers.RegistroController;
import gestiontusanatorio.back.Models.Usuarios;

import javax.swing.*;
import java.awt.*;

public class Registro extends JFrame {
    private JTextField txtUsername, txtNombres, txtApellido, txtDNI, txtMail, txtTelefono, txtEspecialidad;
    private JPasswordField txtContrasena;
    private JComboBox<String> comboRol;
    private JButton btnRegistrar, btnVolverLogin;
    private JLabel lblEspecialidad;

    private RegistroController registroController;

    public Registro() {
        setTitle("Registro de Usuario");
        setSize(500, 550);
        setMinimumSize(new Dimension(400, 500));
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Panel fondo azul marino
        JPanel backgroundPanel = new JPanel(new GridBagLayout());
        backgroundPanel.setBackground(new Color(0, 0, 128)); // Azul marino

        // Panel formulario con fondo rojo
        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setBackground(new Color(204, 0, 0)); // Rojo Liceo

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel lblUsername = new JLabel("Username:");
        lblUsername.setForeground(Color.WHITE);
        txtUsername = new JTextField(15);

        JLabel lblMail = new JLabel("Email:");
        lblMail.setForeground(Color.WHITE);
        txtMail = new JTextField(15);

        JLabel lblContrasena = new JLabel("Contraseña:");
        lblContrasena.setForeground(Color.WHITE);
        txtContrasena = new JPasswordField(15);

        JLabel lblNombres = new JLabel("Nombres:");
        lblNombres.setForeground(Color.WHITE);
        txtNombres = new JTextField(15);

        JLabel lblApellido = new JLabel("Apellido:");
        lblApellido.setForeground(Color.WHITE);
        txtApellido = new JTextField(15);

        JLabel lblDNI = new JLabel("DNI:");
        lblDNI.setForeground(Color.WHITE);
        txtDNI = new JTextField(15);

        JLabel lblTelefono = new JLabel("Teléfono:");
        lblTelefono.setForeground(Color.WHITE);
        txtTelefono = new JTextField(15);

        JLabel lblRol = new JLabel("Rol:");
        lblRol.setForeground(Color.WHITE);
        comboRol = new JComboBox<>(new String[]{"Paciente", "Médico"});

        lblEspecialidad = new JLabel("Especialidad:");
        lblEspecialidad.setForeground(Color.WHITE);
        txtEspecialidad = new JTextField(15);
        lblEspecialidad.setVisible(false);
        txtEspecialidad.setVisible(false);

        btnRegistrar = new JButton("Registrar");
        btnVolverLogin = new JButton("Volver al Login");

        int y = 0;
        gbc.gridx = 0; gbc.gridy = y; formPanel.add(lblUsername, gbc);
        gbc.gridx = 1; gbc.gridy = y++; formPanel.add(txtUsername, gbc);

        gbc.gridx = 0; gbc.gridy = y; formPanel.add(lblMail, gbc);
        gbc.gridx = 1; gbc.gridy = y++; formPanel.add(txtMail, gbc);

        gbc.gridx = 0; gbc.gridy = y; formPanel.add(lblContrasena, gbc);
        gbc.gridx = 1; gbc.gridy = y++; formPanel.add(txtContrasena, gbc);

        gbc.gridx = 0; gbc.gridy = y; formPanel.add(lblNombres, gbc);
        gbc.gridx = 1; gbc.gridy = y++; formPanel.add(txtNombres, gbc);

        gbc.gridx = 0; gbc.gridy = y; formPanel.add(lblApellido, gbc);
        gbc.gridx = 1; gbc.gridy = y++; formPanel.add(txtApellido, gbc);

        gbc.gridx = 0; gbc.gridy = y; formPanel.add(lblDNI, gbc);
        gbc.gridx = 1; gbc.gridy = y++; formPanel.add(txtDNI, gbc);

        gbc.gridx = 0; gbc.gridy = y; formPanel.add(lblTelefono, gbc);
        gbc.gridx = 1; gbc.gridy = y++; formPanel.add(txtTelefono, gbc);

        gbc.gridx = 0; gbc.gridy = y; formPanel.add(lblRol, gbc);
        gbc.gridx = 1; gbc.gridy = y++; formPanel.add(comboRol, gbc);

        gbc.gridx = 0; gbc.gridy = y; formPanel.add(lblEspecialidad, gbc);
        gbc.gridx = 1; gbc.gridy = y++; formPanel.add(txtEspecialidad, gbc);

        gbc.gridx = 0; gbc.gridy = y; gbc.gridwidth = 2; gbc.anchor = GridBagConstraints.CENTER;
        formPanel.add(btnRegistrar, gbc);

        gbc.gridy = ++y;
        formPanel.add(btnVolverLogin, gbc);

        backgroundPanel.add(formPanel);

        setContentPane(backgroundPanel);

        registroController = new RegistroController(this);

        btnRegistrar.addActionListener(e -> {
            try {
                String username = txtUsername.getText().trim();
                String mail = txtMail.getText().trim();
                String contrasena = new String(txtContrasena.getPassword());
                String nombres = txtNombres.getText().trim();
                String apellido = txtApellido.getText().trim();
                int dni = Integer.parseInt(txtDNI.getText().trim());
                String telefono = txtTelefono.getText().trim();
                String rolSeleccionado = (String) comboRol.getSelectedItem();
                int idRol = rolSeleccionado.equalsIgnoreCase("Médico") ? 2 : 1;
                Integer idEspecialidad = null;
                if (idRol == 2) {
                    String esp = txtEspecialidad.getText().trim();
                    idEspecialidad = esp.isEmpty() ? null : Integer.parseInt(esp);
                }

                Usuarios usuario = new Usuarios();
                usuario.setUsername(username);
                usuario.setEmail(mail);
                usuario.setContrasena(contrasena);
                usuario.setNombres(nombres);
                usuario.setApellido(apellido);
                usuario.setDni(dni);
                usuario.setTelefono(telefono);
                usuario.setIdRol(idRol);
                usuario.setIdEspecialidad(idEspecialidad);

                registroController.registro(usuario);
            } catch (NumberFormatException ex) {
                mostrarMensaje("DNI y Especialidad (si es médico) deben ser números válidos.");
            }
        });

        comboRol.addActionListener(e -> {
            String rolSeleccionado = (String) comboRol.getSelectedItem();
            boolean esMedico = rolSeleccionado != null && rolSeleccionado.equalsIgnoreCase("Médico");
            lblEspecialidad.setVisible(esMedico);
            txtEspecialidad.setVisible(esMedico);
        });

        btnVolverLogin.addActionListener(e -> {
            new Login();
            dispose();
        });

        setVisible(true);
        SwingUtilities.invokeLater(() -> txtUsername.requestFocusInWindow());
    }

    public void mostrarMensaje(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(Registro::new);
    }
}
