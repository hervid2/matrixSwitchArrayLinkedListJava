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
    private JPanel contentPane;// contenedor principal de la interfaz que aloja botones, radio button y demás
    private Map<String, boolean[][]> salas; // Mapa para almacenar el estado de los puestos por película
    private JFrame framePuestos; // Ventana de puestos
    private String peliculaSeleccionada; // Almacena el nombre de la película seleccionada por el usuario.
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
        // Inicializa la lista de reservas 
        reservas = new ArrayList<>();

        // Permitir al administrador editar los nombres de las películas
        peliculas = new String[5];
        for (int i = 0; i < peliculas.length; i++) {
            peliculas[i] = JOptionPane.showInputDialog("Ingrese el nombre de la película " + (i + 1) + ":");
            if (peliculas[i] == null || peliculas[i].trim().isEmpty()) {
                peliculas[i] = "Película " + (i + 1); // Nombre por defecto
            }
        }

        // Aquí se agregan las respectivas salas de cine según cada película.
        salas = new HashMap<>(); //inicializa el hashmap de almacenará las salas con sus estados disponible o reservado
        for (String pelicula : peliculas) { // clave : valor del hashmap<>() que representa nombre de pelicula : matriz de sala respectiva
            salas.put(pelicula, new boolean[5][5]); // se itera sobre cada elemento del array peliculas y ejecuta el bloque interno
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

        JPanel panelPeliculas = new JPanel(); // Se crea un panel para mostrar los botones de selección de películas
        panelPeliculas.setBounds(161, 156, 326, 250);
        contentPane.add(panelPeliculas);
        panelPeliculas.setLayout(null);

        
        ButtonGroup grupoPeliculas = new ButtonGroup(); // ButtonGroup: Clase Swing que se usa para agrupar botones de radio, y que solo uno de ellos puede ser seleccionado simultaneamente
        int y = 18; // y- coordinate, situa vertical los jradiobutton con posición de 18 píxeles
        for (String pelicula : peliculas) {//blucle for each
            JRadioButton radioButton = new JRadioButton(pelicula);
            radioButton.setBounds(91, y, 200, 23);
            radioButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    peliculaSeleccionada = pelicula; // Guarda la película seleccionada
                }
            });
            panelPeliculas.add(radioButton); //se agregan los radio button al panel películas
            grupoPeliculas.add(radioButton);// de agrega la buttonGroup al panel películas
            y += 42; //Aumenta la posición vertical en 42 píxeles para el siguiente botón de radio.
        }

        
        JButton btnMostrarPuestos = new JButton("Mostrar Puestos"); // Botón para mostrar los puestos
        btnMostrarPuestos.setBounds(250, 420, 150, 30);
        btnMostrarPuestos.addActionListener(new ActionListener() {
      
            public void actionPerformed(ActionEvent e) {
                if (peliculaSeleccionada != null) { //se mostraran los puestos de la pelicula seleccionada
                    mostrarPuestos(peliculaSeleccionada);//mostrarPuestos es el metodo que crea una nueva ventana para mostrar los asientos disponibles
                } else {
                    JOptionPane.showMessageDialog(contentPane, "Por favor, seleccione una película.");
                }
            }
        });
        contentPane.add(btnMostrarPuestos);//se añade el botón al jPanel principal
    }

    
    private void mostrarPuestos(String pelicula) { // Método para mostrar los puestos de una película
        framePuestos = new JFrame("Puestos disponibles: " + pelicula); //nueva ventana para mostrar los asientos de la película
        framePuestos.setSize(800, 700);
        framePuestos.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        framePuestos.setLocationRelativeTo(null);

        JPanel panelPrincipal = new JPanel(new BorderLayout());

        
        JPanel panelPantalla = new JPanel();// Panel para la pantalla del cine
        panelPantalla.setBackground(Color.BLACK);
        JLabel lblPantalla = new JLabel("Pantalla", SwingConstants.CENTER);//se agrega un label para representar en la interfaz la pocicion de la pantalla de cine el la sala
        lblPantalla.setFont(new Font("Arial", Font.BOLD, 24));
        lblPantalla.setForeground(Color.WHITE);
        panelPantalla.add(lblPantalla);
        panelPrincipal.add(panelPantalla, BorderLayout.NORTH);

        
        JPanel panelPuestos = new JPanel();// Panel para la matriz con los puestos
        panelPuestos.setLayout(new GridLayout(salas.get(pelicula).length, salas.get(pelicula)[0].length, 5, 5)); //el constructor GridLayout ORGANIZA los componentes del hashmap salas en filas y columnas

        boolean[][] puestos = salas.get(pelicula);//Obtención de la matriz de asientos

        for (int i = 0; i < puestos.length; i++) {
            for (int j = 0; j < puestos[i].length; j++) {
                JButton botonPuesto = new JButton("F" + (i + 1) + "C" + (j + 1)); //le asigna nombre en código a cada botón que representa un asiento
                botonPuesto.setEnabled(!puestos[i][j]); // habilita todos los puestos por con el boolean false por default  con setEnabled
                if (puestos[i][j]) { //condicional para darle color al puesto rojo o verde según aplique
                    botonPuesto.setText("Ocupado");
                    botonPuesto.setBackground(Color.RED);
                } else {
                    botonPuesto.setBackground(Color.GREEN);
                }
                int finalI = i;  //copia de i que puede ser usada dentro del ActionListener
                int finalJ = j;  //copia de j que puede ser usada dentro del ActionListener
                botonPuesto.addActionListener(new ActionListener() {
                    
                    public void actionPerformed(ActionEvent e) {
                        String nombre = JOptionPane.showInputDialog(framePuestos, "Ingrese su nombre:");
                        if (nombre != null && !nombre.trim().isEmpty()) {
                            puestos[finalI][finalJ] = true; // Marcar como ocupado
                            botonPuesto.setText("Ocupado");
                            botonPuesto.setBackground(Color.RED);
                            botonPuesto.setEnabled(false);
                            reservas.add(new String[]{nombre, pelicula, "F" + (finalI + 1), "C" + (finalJ + 1)}); //se va agregando cada reserva individual a un stacklist
                            actualizarListadoReservas(); //metodo que actualiza el area de texto que muestra las reservas
                            JOptionPane.showMessageDialog(framePuestos, "Asiento F" + (finalI + 1) + "C" + (finalJ + 1) + " reservado por " + nombre + ".");
                        }
                    }
                });
                panelPuestos.add(botonPuesto); //va agregando al panelPuestos un puesto nuevo con cada iteración
            }
        }

        // Panel para el listado de reservas
        listadoReservas = new JTextArea();
        listadoReservas.setEditable(false);
        JScrollPane scrollReservas = new JScrollPane(listadoReservas);

        // Panel para contadores
        JPanel panelContadores = new JPanel();
        JLabel lblDisponibles = new JLabel("Puestos disponibles: " + contarPuestosDisponibles(puestos)); //se usa el metodo para contar los puestos
        JLabel lblOcupados = new JLabel("Puestos ocupados: " + contarPuestosOcupados(puestos)); //se usa el metodo para contar los puestos
        panelContadores.add(lblDisponibles);
        panelContadores.add(lblOcupados);

        // Botón para reiniciar selección
        JButton btnReiniciar = new JButton("Reiniciar Selección");
        btnReiniciar.addActionListener(new ActionListener() {
            
            public void actionPerformed(ActionEvent e) {
                reiniciarPuestos(pelicula); //aplica el método reiniciarPuestos a la matriz tipo hashmap "película"
                framePuestos.dispose();//.dispose cierra la ventana framePuestos y libera todo la informacion almacenada en sus variables allí alojadas
                mostrarPuestos(pelicula); //el metodo deja todo otra vez como al principio y todas las sillas disponibles
            }
        });

        // Agrega componentes al panel principal 
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
    private void reiniciarPuestos(String pelicula) {//recorre cada puesto de la matriz pelicula y la setea nuevamente en "false"
        boolean[][] puestos = salas.get(pelicula);
        for (int i = 0; i < puestos.length; i++) {
            for (int j = 0; j < puestos[i].length; j++) {
                puestos[i][j] = false;
            }
        }
        reservas.clear(); // Limpiar la lista de reservas
        actualizarListadoReservas(); // Actualizar el listado con el metodo a continuacion
    }

    // Método para actualizar el listado de reservas
    private void actualizarListadoReservas() {
        listadoReservas.setText("Reservas:\n");
        for (String[] reserva : reservas) {
            listadoReservas.append(reserva[0] + " - " + reserva[1] + " - Fila " + reserva[2] + ", Columna " + reserva[3] + "\n");
        }
    }
}