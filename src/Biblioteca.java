import java.awt.EventQueue;
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

    public Libro(String titulo, String autor) {
        this.titulo = titulo;
        this.autor = autor;
        this.prestado = false;
    }

    public String getTitulo() { //método para devolver el titulo 
        return titulo;
    }

    public String getAutor() {//metodo para 
        return autor;
    }

    public boolean isPrestado() {
        return prestado;
    }

    public void prestar() {
        if (!prestado) {
            prestado = true;
        } else {
            System.out.println("El libro ya está prestado.");
        }
    }

    public void devolver() {
        if (prestado) {
            prestado = false;
        } else {
            System.out.println("El libro no estaba prestado.");
        }
    }

    
    public String toString() {
        return titulo + " - " + autor + " (" + (prestado ? "Prestado" : "Disponible") + ")";
    }
}

// Clase gestionBiblioteca
class gestionBiblioteca {
    private List<Libro> libros;

    public gestionBiblioteca() {
        libros = new ArrayList<>();
    }

    public void agregarLibro(Libro libro) {
        libros.add(libro);
    }

    public void prestarLibro(String titulo) {
        for (Libro libro : libros) {
            if (libro.getTitulo().equals(titulo)) {
                libro.prestar();
                return;
            }
        }
        System.out.println("Libro no encontrado.");
    }

    public void devolverLibro(String titulo) {
        for (Libro libro : libros) {
            if (libro.getTitulo().equals(titulo)) {
                libro.devolver();
                return;
            }
        }
        System.out.println("Libro no encontrado.");
    }

    public List<Libro> getLibrosDisponibles() {
        List<Libro> disponibles = new ArrayList<>();
        for (Libro libro : libros) {
            if (!libro.isPrestado()) {
                disponibles.add(libro);
            }
        }
        return disponibles;
    }

    public List<Libro> getLibrosPrestados() {
        List<Libro> prestados = new ArrayList<>();
        for (Libro libro : libros) {
            if (libro.isPrestado()) {
                prestados.add(libro);
            }
        }
        return prestados;
    }

    public List<Libro> getTodosLosLibros() {
        return libros;
    }
}

// Clase Biblioteca (Interfaz gráfica)
public class Biblioteca extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private gestionBiblioteca biblioteca;
    private JList<String> listDisponibles;
    private JList<String> listPrestados;
    private DefaultListModel<String> modelDisponibles;
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
        biblioteca = new gestionBiblioteca();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 791, 553);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JPanel panel = new JPanel();
        panel.setBackground(new Color(0, 128, 255));
        panel.setBounds(10, 11, 765, 535);
        contentPane.add(panel);
        panel.setLayout(null);

        JLabel lblNewLabel = new JLabel("Biblioteca");
        lblNewLabel.setBounds(145, 31, 147, 49);
        lblNewLabel.setFont(new Font("Yu Gothic", Font.BOLD, 30));
        panel.add(lblNewLabel);

        // Lista de libros disponibles con JScrollPane
        modelDisponibles = new DefaultListModel<>();
        JScrollPane scrollPaneDisponibles = new JScrollPane();
        scrollPaneDisponibles.setBounds(39, 170, 310, 238);
        panel.add(scrollPaneDisponibles);

        // Lista de libros prestados con JScrollPane
        modelPrestados = new DefaultListModel<>();
        JScrollPane scrollPanePrestados = new JScrollPane();
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
            @Override
            public void actionPerformed(ActionEvent e) {
                agregarLibro();
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
        panel.add(btnNewButton_2);
        listPrestados = new JList<>(modelPrestados);
        listPrestados.setBounds(387, 171, 328, 236);
        panel.add(listPrestados);
        listDisponibles = new JList<>(modelDisponibles);
        listDisponibles.setBounds(39, 170, 310, 236);
        panel.add(listDisponibles);
    }

    // Método para agregar un libro
    private void agregarLibro() {
        String titulo = JOptionPane.showInputDialog(this, "Ingrese el título del libro:");
        String autor = JOptionPane.showInputDialog(this, "Ingrese el autor del libro:");
        if (titulo != null && autor != null) {
            biblioteca.agregarLibro(new Libro(titulo, autor));
            actualizarListas();
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
        modelDisponibles.clear();
        modelPrestados.clear();

        for (Libro libro : biblioteca.getLibrosDisponibles()) {
            modelDisponibles.addElement(libro.toString());
        }

        for (Libro libro : biblioteca.getLibrosPrestados()) {
            modelPrestados.addElement(libro.toString());
        }
    }
}
