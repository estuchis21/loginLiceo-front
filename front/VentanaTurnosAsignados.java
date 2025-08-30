package gestiontusanatorio.loginLiceo.front;

import gestiontusanatorio.back.Models.TurnosAsignados;
import gestiontusanatorio.loginLiceo.front.Controllers.AsignarTurnosController;
import gestiontusanatorio.loginLiceo.front.Controllers.AsignarTurnosController;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class VentanaTurnosAsignados extends JFrame {

    private JTable tablaTurnos;
    private JButton btnVolver;
    private AsignarTurnosController controller;
    private int idPaciente;

    public VentanaTurnosAsignados(int idPaciente) {
        this.idPaciente = idPaciente;
        controller = new AsignarTurnosController();

        setTitle("Turnos Asignados");
        setSize(700, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        initComponents();
        cargarTurnos();
    }

    private void initComponents() {
        String[] columnas = {"ID Turno", "ID Paciente", "ID Obra Social", "Fecha y Hora Asignación"};

        tablaTurnos = new JTable();
        JScrollPane scrollPane = new JScrollPane(tablaTurnos);

        btnVolver = new JButton("Volver");
        btnVolver.addActionListener(e -> {
            // Abrir ventana para crear turno u otra acción, por ejemplo
            VentanaCrearTurno crearTurno = new VentanaCrearTurno(idPaciente, 0); // 0 o la obra social que corresponda
            crearTurno.setVisible(true);
            this.dispose();
        });

        JPanel panelBotones = new JPanel();
        panelBotones.add(btnVolver);

        setLayout(new BorderLayout());
        add(scrollPane, BorderLayout.CENTER);
        add(panelBotones, BorderLayout.SOUTH);
    }

    private void cargarTurnos() {
        List<TurnosAsignados> turnos = controller.getTurnosPorPaciente(idPaciente);

        String[] columnas = {"ID Turno", "ID Paciente", "ID Obra Social", "Fecha y Hora Asignación"};
        Object[][] data = new Object[turnos.size()][4];

        for (int i = 0; i < turnos.size(); i++) {
            TurnosAsignados t = turnos.get(i);
            data[i][0] = t.getIdTurno();
            data[i][1] = t.getIdPaciente();
            data[i][2] = t.getIdObraSocial();
            data[i][3] = t.getFechaAsignacion();
        }

        tablaTurnos.setModel(new javax.swing.table.DefaultTableModel(data, columnas));
    }
}
