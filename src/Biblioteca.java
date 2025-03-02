import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

// Clase Libro
class Libro {
    private String titulo;
    private String autor;
    private boolean prestado;

    public Libro(String titulo, String autor) { //metodo constructor que recibe titulo, autor y estado como un objeto
        this.titulo = titulo;
        this.autor = autor;
        this.prestado = false; 
    }

    public String getTitulo() { //método para almarcenar y devolver el titulo 
        return titulo;
    }

    public String getAutor() {//metodo para almacenar y devolver el autor
        return autor;
    }

    public boolean isPrestado() { //método con boolean para determinar si el libro está prestado o no. 
        return prestado;
    }

    public void prestar() {
        if (!prestado) {//método con condicional para mostrar si el libro ya está prestado 
            prestado = true;
        } else {
            System.out.println("El libro ya está prestado.");
        }
    }

    public void devolver() { // método con condicional para mostrar si el libro no está prestado
        if (prestado) {
            prestado = false;
        } else {
            System.out.println("El libro no estaba prestado.");
        }
    }

    
    public String toString() {//método que retorna la descripcioón del título, autor y estado del préstamo para mostrar
        return titulo + " - " + autor + " (" + (prestado ? "Prestado" : "Disponible") + ")"; //Operador terniario que determina el estado del libro
    }
}


class gestionBiblioteca { //clase que maneja la lógica de los procesos en la biblioteca de almacenar y gestionar libros
    private List<Libro> libros; //lista dinámica para almacenar TODOS los libros, incluyendo sub arrayList "prestados" y "disponibles"
    public gestionBiblioteca() {
        libros = new ArrayList<>(); //Constructor que inicializa libros como un ArrayList.
    }

    public void agregarLibro(Libro libro) { //método que agrega un objeto del tipo "Libro" A la lista de libros
        libros.add(libro); //método del arrayList que agrega un libro nuevo a "libros"
    }

    public void prestarLibro(String titulo) {//Método que busca un libro en la lista de libros y si lo encuentra lo marca como prestado
        for (Libro libro : libros) {//For each que  que usa el objeto Libro creado en la clase Libro instanciado como "libro" y alojado el arrayList "libros"
            if (libro.getTitulo().equals(titulo)) {  //si el nombre del libro que se introduce el la consulta por el usuario es igual al almacenado en la lista, pasa a ser evaluado  por prestar()
                libro.prestar(); //entonces libro, es evaluado por el método de la clase libro prestar() y determina si se puede prestar o no
                return;  //finaliza el for apenas encuentra el libro y lo presta
            }
        }
        System.out.println("Libro no encontrado.");
    }

    public void devolverLibro(String titulo) { //Método que busca un libro por título y lo devuelve.
        for (Libro libro : libros) {
            if (libro.getTitulo().equals(titulo)) {
                libro.devolver();
                return;
            }
        }
        System.out.println("Libro no encontrado.");
    }

    public List<Libro> getLibrosDisponibles() { //Método que retorna una lista de libros que no están prestados.
        List<Libro> disponibles = new ArrayList<>(); //Se inicializa un nuevo arrayList para almacenar libros disponibles
        for (Libro libro : libros) {
            if (!libro.isPrestado()) {
                disponibles.add(libro);//evalua si el libro no está prestado y lo mantiene o añade en "disponibles"
            }
        }
        return disponibles; //retorna "disponibles" que contiene solo los libros disponibles
    }

    public List<Libro> getLibrosPrestados() { //Método que retorna una lista de libros prestados
        List<Libro> prestados = new ArrayList<>();
        for (Libro libro : libros) {
            if (libro.isPrestado()) {
                prestados.add(libro);
            }
        }
        return prestados;  //retorna "prestados" que contiene solo los libros prestados
    }

}

// Clase Biblioteca (Interfaz gráfica)
public class Biblioteca extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private gestionBiblioteca biblioteca; //nueva instancia de la clase "gestionBiblioteca"
    private JList<String> listDisponibles;  //espacio en la GUI para visualizar los libros disponibles
    private JList<String> listPrestados;  //espacion el la GUI para visualizar los libros prestados
    private DefaultListModel<String> modelDisponibles; //DefaultListModel<String>, que es una estructura de datos dinámica para almacenar y gestionar elementos de una lista en Swing.
    private DefaultListModel<String> modelPrestados;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Biblioteca frame = new Biblioteca();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public Biblioteca() { 
        biblioteca = new gestionBiblioteca(); //Inicializa la ventana y la instancia de gestionBiblioteca.
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 791, 553);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JPanel panel = new JPanel(); //Crea y configura el panel de contenido.
        panel.setBackground(new Color(0, 128, 255));
        panel.setBounds(10, 11, 765, 535);
        contentPane.add(panel);
        panel.setLayout(null);

        JLabel lblNewLabel = new JLabel("Biblioteca"); //Crea un JLabel con el título "Biblioteca".
        lblNewLabel.setBounds(145, 31, 147, 49);
        lblNewLabel.setFont(new Font("Yu Gothic", Font.BOLD, 30));
        panel.add(lblNewLabel);

        
        modelDisponibles = new DefaultListModel<>();  //DefaultListModel<> permite agregar, eliminar y modificar elementos fácilmente sin necesidad de actualizar manualmente la interfaz gráfica.
        JScrollPane scrollPaneDisponibles = new JScrollPane();// Lista de libros disponibles con JScrollPane
        scrollPaneDisponibles.setBounds(39, 170, 310, 238);
        panel.add(scrollPaneDisponibles);

       
        modelPrestados = new DefaultListModel<>(); 
        JScrollPane scrollPanePrestados = new JScrollPane();  // Lista de libros prestados con JScrollPane que es un contenedor con barras de desplazamiento
        scrollPanePrestados.setBounds(387, 170, 328, 238);
        panel.add(scrollPanePrestados);

        JLabel lblNewLabel_1 = new JLabel("Libros disponibles");
        lblNewLabel_1.setFont(new Font("Yu Gothic", Font.BOLD, 20));
        lblNewLabel_1.setBounds(109, 137, 179, 49);
        panel.add(lblNewLabel_1);

        JLabel lblNewLabel_2 = new JLabel("Libros prestados");
        lblNewLabel_2.setFont(new Font("Yu Gothic", Font.BOLD, 20));
        lblNewLabel_2.setBounds(470, 147, 171, 29);
        panel.add(lblNewLabel_2);

        JLabel lblNewLabel_3 = new JLabel("New label");
        lblNewLabel_3.setIcon(new ImageIcon("C:\\Users\\Propietario\\Downloads\\biblioteca (1) (1).png"));
        lblNewLabel_3.setBounds(39, 11, 91, 87);
        panel.add(lblNewLabel_3);

        // Botón para agregar libro
        JButton btnNewButton = new JButton("Agregar libro");
        btnNewButton.setFont(new Font("Yu Gothic", Font.BOLD, 11));
        btnNewButton.setBounds(68, 419, 107, 49);
        btnNewButton.addActionListener(new ActionListener() {
           
            public void actionPerformed(ActionEvent e) {
                agregarLibro(); //ejecuta el método de la clase "gestionBiblioteca" al presionar el botón
            }
        });
        panel.add(btnNewButton);

        // Botón para prestar libro
        JButton btnNewButton_1 = new JButton("Prestar libro");
        btnNewButton_1.setFont(new Font("Yu Gothic", Font.BOLD, 11));
        btnNewButton_1.setBounds(220, 419, 107, 49);
        btnNewButton_1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                prestarLibro();
            }
        });
        panel.add(btnNewButton_1);

        // Botón para devolver libro
        JButton btnNewButton_2 = new JButton("Regresar libro");
        btnNewButton_2.setFont(new Font("Yu Gothic", Font.BOLD, 11));
        btnNewButton_2.setBounds(491, 419, 107, 49);
        btnNewButton_2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                devolverLibro();
            }
        });
        panel.add(btnNewButton_2);  //agregar boton a la GUI
        listPrestados = new JList<>(modelPrestados); //Mostras las listas de libros en la GUI valiendose del objeto modelPrestados
        listPrestados.setBounds(387, 171, 328, 236);
        panel.add(listPrestados);
        listDisponibles = new JList<>(modelDisponibles); //se agrega el JList al panel del GUI con el modelo DefaultListModel, que convierte la lista en algo dinámico
        listDisponibles.setBounds(39, 170, 310, 236);
        panel.add(listDisponibles);
    }

    // Método para agregar un libro
    private void agregarLibro() {
        String titulo = JOptionPane.showInputDialog(this, "Ingrese el título del libro:"); //Muestra un cuadro de diálogo emergente donde el usuario debe escribir el título del libro.
        String autor = JOptionPane.showInputDialog(this, "Ingrese el autor del libro:"); //this, se refiere a la instancia de Biblioteca, que en sí es el frame de la GUI, lo cual asocia el cuadro de diálogo a la vantana principal
        if (titulo != null && autor != null) {
            biblioteca.agregarLibro(new Libro(titulo, autor)); //se llama al método agregarLibro(Libro, libro) de la clase gestionBiblioteca instanciada dentro de esa clase como "biblioteca"
            actualizarListas(); //se llama al método actualizarListas() creado mas abajo
        }
    }

    // Método para prestar un libro
    private void prestarLibro() {
        String titulo = JOptionPane.showInputDialog(this, "Ingrese el título del libro a prestar:");
        if (titulo != null) {
            biblioteca.prestarLibro(titulo);
            actualizarListas();
        }
    }

    // Método para devolver un libro
    private void devolverLibro() {
        String titulo = JOptionPane.showInputDialog(this, "Ingrese el título del libro a devolver:");
        if (titulo != null) {
            biblioteca.devolverLibro(titulo);
            actualizarListas();
        }
    }

    // Método para actualizar las listas de libros disponibles y prestados
    private void actualizarListas() {
        modelDisponibles.clear(); //Si no se vacía, al agregar nuevos elementos se duplicarían los libros en la interfaz.
        modelPrestados.clear(); //Borra la lista gráfica de libros disponibles.

        for (Libro libro : biblioteca.getLibrosDisponibles()) { //Obtiene los libros disponibles de gestionBiblioteca.
            modelDisponibles.addElement(libro.toString()); //Convierte el objeto Libro en un String usando su método toString().
        }// Lo agrega a modelDisponibles, el cual está vinculado a JList<String> listDisponibles.

        for (Libro libro : biblioteca.getLibrosPrestados()) {
            modelPrestados.addElement(libro.toString());
        }
    }
}
