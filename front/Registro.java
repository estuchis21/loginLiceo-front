package gestiontusanatorio.loginLiceo.front;

import javax.swing.*;
import java.awt.*;

public class Registro extends JFrame {
    private JTextField txtUsername, txtNombres, txtApellido, txtDNI, txtMail, txtTelefono, txtEspecialidad;
    private JPasswordField txtContrasena;
    private JComboBox<String> comboRol;
    private JButton btnRegistrar, btnVolverLogin;
    private JLabel lblEspecialidad;

    public Registro() {
        setTitle("Registro de Usuario");
        setSize(500, 550);
        setMinimumSize(new Dimension(400, 500));
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Panel de formulario
        JPanel formPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Campos y etiquetas
        JLabel lblUsername = new JLabel("Username:");
        txtUsername = new JTextField(15);

        JLabel lblMail = new JLabel("Email:");
        txtMail = new JTextField(15);

        JLabel lblContrasena = new JLabel("Contraseña:");
        txtContrasena = new JPasswordField(15);

        JLabel lblNombres = new JLabel("Nombres:");
        txtNombres = new JTextField(15);

        JLabel lblApellido = new JLabel("Apellido:");
        txtApellido = new JTextField(15);

        JLabel lblDNI = new JLabel("DNI:");
        txtDNI = new JTextField(15);

        JLabel lblTelefono = new JLabel("Teléfono:");
        txtTelefono = new JTextField(15);

        JLabel lblRol = new JLabel("Rol:");
        comboRol = new JComboBox<>(new String[]{"Paciente", "Médico"});

        // Campo de especialidad (inicialmente oculto)
        lblEspecialidad = new JLabel("Especialidad:");
        txtEspecialidad = new JTextField(15);
        lblEspecialidad.setVisible(false);
        txtEspecialidad.setVisible(false);

        // Botones
        btnRegistrar = new JButton("Registrar");
        btnVolverLogin = new JButton("Volver al Login");

        // Agregado de componentes al formulario
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

        // Especialidad (oculta por defecto)
        gbc.gridx = 0; gbc.gridy = y; formPanel.add(lblEspecialidad, gbc);
        gbc.gridx = 1; gbc.gridy = y++; formPanel.add(txtEspecialidad, gbc);

        // Botón registrar
        gbc.gridx = 0; gbc.gridy = y; gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        formPanel.add(btnRegistrar, gbc);

        // Botón volver al login
        gbc.gridy = ++y;
        formPanel.add(btnVolverLogin, gbc);

        // Panel centrado
        JPanel outerPanel = new JPanel(new GridBagLayout());
        outerPanel.add(formPanel);
        add(outerPanel, BorderLayout.CENTER);

        // Evento para mostrar campo "Especialidad" si se elige "Médico"
        comboRol.addActionListener(e -> {
            String rolSeleccionado = (String) comboRol.getSelectedItem();
            boolean esMedico = rolSeleccionado != null && rolSeleccionado.equalsIgnoreCase("Médico");

            lblEspecialidad.setVisible(esMedico);
            txtEspecialidad.setVisible(esMedico);
        });

        // Evento para volver al login
        btnVolverLogin.addActionListener(e -> {
            new Login();
            dispose();
        });

        setVisible(true);
        SwingUtilities.invokeLater(() -> txtUsername.requestFocusInWindow());
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(Registro::new);
    }
}
