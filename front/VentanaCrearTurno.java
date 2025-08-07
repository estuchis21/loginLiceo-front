package gestiontusanatorio.loginLiceo.front;

import gestiontusanatorio.back.Models.Obras_sociales;
import gestiontusanatorio.back.Models.TurnosDisponibles;
import gestiontusanatorio.back.Services.TurnosService;
import gestiontusanatorio.loginLiceo.front.Controllers.AsignarTurnosController;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class VentanaCrearTurno extends JFrame {

    private JComboBox<String> comboTurnos;
    private JComboBox<Obras_sociales> comboObrasSociales;
    private JButton btnAsignar;

    private AsignarTurnosController turnoController;
    private TurnosService turnosService;

    private int idPaciente;
    private int id_obra_social;

    // Constructor recibe idPaciente y id_obra_social (aunque este último no se usa para asignar, se puede mostrar o mantener)
    public VentanaCrearTurno(int idPaciente, int id_obra_social) {
        this.idPaciente = idPaciente;
        this.id_obra_social = id_obra_social;
        turnoController = new AsignarTurnosController();
        turnosService = new TurnosService();

        setTitle("Asignar Turno");
        setSize(600, 250);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        initComponents();
        cargarTodosLosTurnos();
        cargarObrasSociales();
    }

    private void initComponents() {
        JLabel lblTurnos = new JLabel("Turnos Disponibles:");
        comboTurnos = new JComboBox<>();

        JLabel lblObrasSociales = new JLabel("Obra Social:");
        comboObrasSociales = new JComboBox<>();

        btnAsignar = new JButton("Asignar Turno");
        btnAsignar.addActionListener(e -> asignarTurno());

        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        gbc.insets = new Insets(10, 10, 10, 10);

        // Label Turnos
        gbc.gridx = 0; gbc.gridy = 0; gbc.anchor = GridBagConstraints.WEST;
        panel.add(lblTurnos, gbc);

        // Combo Turnos
        gbc.gridx = 1; gbc.gridy = 0; gbc.fill = GridBagConstraints.HORIZONTAL; gbc.weightx = 1.0;
        panel.add(comboTurnos, gbc);

        // Label Obras Sociales
        gbc.gridx = 0; gbc.gridy = 1; gbc.fill = GridBagConstraints.NONE; gbc.weightx = 0;
        panel.add(lblObrasSociales, gbc);

        // Combo Obras Sociales
        gbc.gridx = 1; gbc.gridy = 1; gbc.fill = GridBagConstraints.HORIZONTAL; gbc.weightx = 1.0;
        panel.add(comboObrasSociales, gbc);

        // Botón Asignar
        gbc.gridx = 0; gbc.gridy = 2; gbc.gridwidth = 2; gbc.fill = GridBagConstraints.NONE; gbc.anchor = GridBagConstraints.CENTER;
        panel.add(btnAsignar, gbc);

        add(panel);
    }

    private void cargarTodosLosTurnos() {
        List<TurnosDisponibles> turnos = turnoController.getTodosLosTurnosDisponibles();
        comboTurnos.removeAllItems();

        if (turnos.isEmpty()) {
            comboTurnos.addItem("No hay turnos disponibles");
            comboTurnos.setEnabled(false);
            btnAsignar.setEnabled(false);
        } else {
            for (TurnosDisponibles turno : turnos) {
                String item = String.format("%d - Médico ID: %d - Fecha: %s",
                        turno.getIdTurno(),
                        turno.getIdMedico(),
                        turno.getFechaTurno().toString());
                comboTurnos.addItem(item);
            }
            comboTurnos.setEnabled(true);
            btnAsignar.setEnabled(true);
        }
    }

    private void cargarObrasSociales() {
        List<Obras_sociales> obrasSociales = turnosService.getObrasSociales();
        comboObrasSociales.removeAllItems();

        if (obrasSociales.isEmpty()) {
            comboObrasSociales.addItem(new Obras_sociales(0, "No hay obras sociales"));
            comboObrasSociales.setEnabled(false);
            btnAsignar.setEnabled(false);
        } else {
            for (Obras_sociales os : obrasSociales) {
                comboObrasSociales.addItem(os);
            }
            comboObrasSociales.setEnabled(true);
        }
    }

    private void asignarTurno() {
        if (comboTurnos.getSelectedIndex() == -1 || !comboTurnos.isEnabled()) {
            JOptionPane.showMessageDialog(this, "Debe seleccionar un turno.");
            return;
        }
        if (comboObrasSociales.getSelectedIndex() == -1 || !comboObrasSociales.isEnabled()) {
            JOptionPane.showMessageDialog(this, "Debe seleccionar una obra social.");
            return;
        }

        String seleccion = (String) comboTurnos.getSelectedItem();
        int idTurno = Integer.parseInt(seleccion.split(" - ")[0]);

        Obras_sociales obraSocialSeleccionada = (Obras_sociales) comboObrasSociales.getSelectedItem();
        int idObraSocialSeleccionada = obraSocialSeleccionada.getIdObraSocial();

        // Asigna el turno con idPaciente guardado y la obra social seleccionada en el combo
        boolean exito = turnoController.asignarTurno(idTurno, idPaciente, idObraSocialSeleccionada);

        if (exito) {
            JOptionPane.showMessageDialog(this, "Turno asignado correctamente.");
            dispose();
        } else {
            JOptionPane.showMessageDialog(this, "No se pudo asignar el turno.");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            // Para probar, pasar un paciente y obra social ejemplo
            VentanaCrearTurno ventana = new VentanaCrearTurno(123, 1);
            ventana.setVisible(true);
        });
    }
}
