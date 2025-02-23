import java.awt.EventQueue;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;
import java.util.ArrayList;
import java.util.List;

public class cineSenema extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private Map<String, boolean[][]> salas; // Mapa para almacenar el estado de los puestos por película
    private JFrame framePuestos; // Ventana de puestos
    private String peliculaSeleccionada; // Película seleccionada
    private List<String[]> reservas; // Lista para almacenar las reservas (nombre, película, fila, columna)
    private String[] peliculas; // Nombres de las películas
    private JTextArea listadoReservas; // Referencia directa al JTextArea para el listado de reservas

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                	cineSenema frame = new cineSenema();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public cineSenema() {
        // Inicializar la lista de reservas
        reservas = new ArrayList<>();

        // Permitir al administrador editar los nombres de las películas
        peliculas = new String[5];
        for (int i = 0; i < peliculas.length; i++) {
            peliculas[i] = JOptionPane.showInputDialog("Ingrese el nombre de la película " + (i + 1) + ":");
            if (peliculas[i] == null || peliculas[i].trim().isEmpty()) {
                peliculas[i] = "Película " + (i + 1); // Nombre por defecto
            }
        }

        // Inicializar el mapa de salas
        salas = new HashMap<>();
        for (String pelicula : peliculas) {
            salas.put(pelicula, new boolean[5][5]); // 5 filas, 5 columnas
        }

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 672, 500);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblTitulo = new JLabel("Bienvenidos a su cine Senama!!!");
        lblTitulo.setFont(new Font("Segoe UI Emoji", Font.BOLD, 18));
        lblTitulo.setBounds(185, 23, 313, 40);
        contentPane.add(lblTitulo);

        JLabel lblInstruccion = new JLabel("Seleccione por favor la cinta de su preferencia:");
        lblInstruccion.setFont(new Font("Segoe UI Historic", Font.BOLD, 13));
        lblInstruccion.setBounds(174, 106, 313, 40);
        contentPane.add(lblInstruccion);

        JPanel panelPeliculas = new JPanel();
        panelPeliculas.setBounds(161, 156, 326, 250);
        contentPane.add(panelPeliculas);
        panelPeliculas.setLayout(null);

        // Crear botones de radio para las películas
        ButtonGroup grupoPeliculas = new ButtonGroup();
        int y = 18;
        for (String pelicula : peliculas) {
            JRadioButton radioButton = new JRadioButton(pelicula);
            radioButton.setBounds(91, y, 200, 23);
            radioButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    peliculaSeleccionada = pelicula; // Guardar la película seleccionada
                }
            });
            panelPeliculas.add(radioButton);
            grupoPeliculas.add(radioButton);
            y += 42;
        }

        // Botón para mostrar los puestos
        JButton btnMostrarPuestos = new JButton("Mostrar Puestos");
        btnMostrarPuestos.setBounds(250, 420, 150, 30);
        btnMostrarPuestos.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (peliculaSeleccionada != null) {
                    mostrarPuestos(peliculaSeleccionada);
                } else {
                    JOptionPane.showMessageDialog(contentPane, "Por favor, seleccione una película.");
                }
            }
        });
        contentPane.add(btnMostrarPuestos);
    }

    // Método para mostrar los puestos de una película
    private void mostrarPuestos(String pelicula) {
        framePuestos = new JFrame("Puestos disponibles: " + pelicula);
        framePuestos.setSize(800, 700);
        framePuestos.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        framePuestos.setLocationRelativeTo(null);

        JPanel panelPrincipal = new JPanel(new BorderLayout());

        // Panel para la pantalla del cine
        JPanel panelPantalla = new JPanel();
        panelPantalla.setBackground(Color.BLACK);
        JLabel lblPantalla = new JLabel("Pantalla", SwingConstants.CENTER);
        lblPantalla.setFont(new Font("Arial", Font.BOLD, 24));
        lblPantalla.setForeground(Color.WHITE);
        panelPantalla.add(lblPantalla);
        panelPrincipal.add(panelPantalla, BorderLayout.NORTH);

        // Panel para los puestos
        JPanel panelPuestos = new JPanel();
        panelPuestos.setLayout(new GridLayout(salas.get(pelicula).length, salas.get(pelicula)[0].length, 5, 5));

        boolean[][] puestos = salas.get(pelicula);

        for (int i = 0; i < puestos.length; i++) {
            for (int j = 0; j < puestos[i].length; j++) {
                JButton botonPuesto = new JButton("F" + (i + 1) + "C" + (j + 1));
                botonPuesto.setEnabled(!puestos[i][j]); // Deshabilitar si está ocupado
                if (puestos[i][j]) {
                    botonPuesto.setText("Ocupado");
                    botonPuesto.setBackground(Color.RED);
                } else {
                    botonPuesto.setBackground(Color.GREEN);
                }
                int finalI = i;
                int finalJ = j;
                botonPuesto.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        String nombre = JOptionPane.showInputDialog(framePuestos, "Ingrese su nombre:");
                        if (nombre != null && !nombre.trim().isEmpty()) {
                            puestos[finalI][finalJ] = true; // Marcar como ocupado
                            botonPuesto.setText("Ocupado");
                            botonPuesto.setBackground(Color.RED);
                            botonPuesto.setEnabled(false);
                            reservas.add(new String[]{nombre, pelicula, "F" + (finalI + 1), "C" + (finalJ + 1)});
                            actualizarListadoReservas();
                            JOptionPane.showMessageDialog(framePuestos, "Asiento F" + (finalI + 1) + "C" + (finalJ + 1) + " reservado por " + nombre + ".");
                        }
                    }
                });
                panelPuestos.add(botonPuesto);
            }
        }

        // Panel para el listado de reservas
        listadoReservas = new JTextArea();
        listadoReservas.setEditable(false);
        JScrollPane scrollReservas = new JScrollPane(listadoReservas);

        // Panel para contadores
        JPanel panelContadores = new JPanel();
        JLabel lblDisponibles = new JLabel("Puestos disponibles: " + contarPuestosDisponibles(puestos));
        JLabel lblOcupados = new JLabel("Puestos ocupados: " + contarPuestosOcupados(puestos));
        panelContadores.add(lblDisponibles);
        panelContadores.add(lblOcupados);

        // Botón para reiniciar selección
        JButton btnReiniciar = new JButton("Reiniciar Selección");
        btnReiniciar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                reiniciarPuestos(pelicula);
                framePuestos.dispose();
                mostrarPuestos(pelicula);
            }
        });

        // Agregar componentes al panel principal
        panelPrincipal.add(panelContadores, BorderLayout.CENTER);
        panelPrincipal.add(panelPuestos, BorderLayout.SOUTH);
        panelPrincipal.add(scrollReservas, BorderLayout.EAST);
        panelPrincipal.add(btnReiniciar, BorderLayout.WEST);

        framePuestos.add(panelPrincipal);
        framePuestos.setVisible(true);
    }

    // Método para contar puestos disponibles
    private int contarPuestosDisponibles(boolean[][] puestos) {
        int disponibles = 0;
        for (boolean[] fila : puestos) {
            for (boolean puesto : fila) {
                if (!puesto) disponibles++;
            }
        }
        return disponibles;
    }

    // Método para contar puestos ocupados
    private int contarPuestosOcupados(boolean[][] puestos) {
        int ocupados = 0;
        for (boolean[] fila : puestos) {
            for (boolean puesto : fila) {
                if (puesto) ocupados++;
            }
        }
        return ocupados;
    }

    // Método para reiniciar los puestos
    private void reiniciarPuestos(String pelicula) {
        boolean[][] puestos = salas.get(pelicula);
        for (int i = 0; i < puestos.length; i++) {
            for (int j = 0; j < puestos[i].length; j++) {
                puestos[i][j] = false;
            }
        }
        reservas.clear(); // Limpiar la lista de reservas
        actualizarListadoReservas(); // Actualizar el listado
    }

    // Método para actualizar el listado de reservas
    private void actualizarListadoReservas() {
        listadoReservas.setText("Reservas:\n");
        for (String[] reserva : reservas) {
            listadoReservas.append(reserva[0] + " - " + reserva[1] + " - Fila " + reserva[2] + ", Columna " + reserva[3] + "\n");
        }
    }
}