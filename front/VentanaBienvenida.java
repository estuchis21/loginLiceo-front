package gestiontusanatorio.loginLiceo.front;

import javax.swing.*;
import java.awt.*;

public class VentanaBienvenida extends JFrame {
    private int idUsuario;
    private String nombres;
    private int idObraSocial;
    private int idRol;

    public VentanaBienvenida(int idUsuario, String nombres, int idObraSocial, int idRol) {
        this.idUsuario = idUsuario;
        this.nombres = nombres;
        this.idObraSocial = idObraSocial;
        this.idRol = idRol;

        setTitle("Bienvenido");
        setSize(400, 200);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        initComponents();
        setVisible(true);
    }

    private void initComponents() {
        String rolTexto = (idRol == 1) ? "Paciente" : (idRol == 2) ? "Médico" : "Desconocido";
        JLabel lblBienvenida = new JLabel("¡Bienvenido/a, " + nombres + " (" + rolTexto + ")!");
        lblBienvenida.setHorizontalAlignment(SwingConstants.CENTER);

        JButton btnVerTurnos = new JButton("Ver Turnos Asignados");
        btnVerTurnos.addActionListener(e -> abrirVentanaTurnos());

        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        panel.add(lblBienvenida, BorderLayout.CENTER);
        panel.add(btnVerTurnos, BorderLayout.SOUTH);

        add(panel);
    }

    private void abrirVentanaTurnos() {
        VentanaTurnosAsignados ventanaTurnos = new VentanaTurnosAsignados(idUsuario);
        ventanaTurnos.setVisible(true);
        dispose();
    }
}
