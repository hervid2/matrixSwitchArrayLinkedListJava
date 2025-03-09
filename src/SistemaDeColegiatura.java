

import java.awt.EventQueue;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

// Clase Estudiante
class Estudiante {
    private String id;
    private String nombre;
    private int edad;
    
    // Constructor que crea el objeto Estudiante e inicializa sus atributos
    public Estudiante(String id, String nombre, int edad) {
        this.id = id;
        this.nombre = nombre;
        this.edad = edad;
    }

    public String getId() { //métodos getter y setter para retornar los valores
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public int getEdad() {
        return edad;
    }

    // clase que retorna una única cadena "string" para asociar los atributos del estudiante
    public String toString() {
        return "ID: " + id + ", Nombre: " + nombre + ", Edad: " + edad;
    }
}


// Clase Curso
class Curso {
    private String id;
    private String nombre;
    private Profesor profesor;
    private List<Estudiante> estudiantes;
    private List<Calificacion> calificaciones;
    
    // Costructor que crea el objeto Curso e inicializa sus atributos
    public Curso(String id, String nombre) {
        this.id = id;
        this.nombre = nombre;
        this.estudiantes = new ArrayList<>();
        this.calificaciones = new ArrayList<>();
    }

    public String getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public Profesor getProfesor() {
        return profesor;
    }

    public void setProfesor(Profesor profesor) {
        this.profesor = profesor;
    }

    public List<Estudiante> getEstudiantes() {
        return estudiantes;
    }

    public List<Calificacion> getCalificaciones() {
        return calificaciones;
    }

    public void agregarEstudiante(Estudiante estudiante) {
        estudiantes.add(estudiante);
    }

    public void agregarCalificacion(Calificacion calificacion) {
        calificaciones.add(calificacion);
    }

    
    public String toString() {
        return "ID: " + id + ", Nombre: " + nombre + ", Profesor: " + (profesor != null ? profesor.getNombre() : "No asignado");
    }
}

// Clase Profesor
class Profesor {
    private String id;
    private String nombre;
    
    // Clase constructora que crea el objeto Profesor e inicializa sus atributos
    public Profesor(String id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    public String getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    
    public String toString() {
        return "ID: " + id + ", Nombre: " + nombre;
    }
}

// Clase Calificacion
class Calificacion {
    private Estudiante estudiante;
    private double nota;
    
    // Clase constructora que crea el objeto Calificación e inicializa sus atributos
    public Calificacion(Estudiante estudiante, double nota) {
        this.estudiante = estudiante;
        this.nota = nota;
    }

    public Estudiante getEstudiante() {
        return estudiante;
    }

    public double getNota() {
        return nota;
    }

    @Override
    public String toString() {
        return "Estudiante: " + estudiante.getNombre() + ", Nota: " + nota;
    }
}

// Clase principal del sistema
public class SistemaDeColegiatura {
    private JFrame frame;
    private List<Estudiante> estudiantes;
    private List<Curso> cursos;
    private List<Profesor> profesores;

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                SistemaDeColegiatura window = new SistemaDeColegiatura();
                window.frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
    
    // Clase constructora de la clase principal que inicializa los arrayList<>() para poder ser usados mas abajo
    public SistemaDeColegiatura() {
        estudiantes = new ArrayList<>();
        cursos = new ArrayList<>();
        profesores = new ArrayList<>();
        initialize();
    }

    private void initialize() {
        frame = new JFrame();
        frame.setBounds(100, 100, 723, 701);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);

        // Panel superior con título
        JPanel panel_1 = new JPanel();
        panel_1.setBackground(new Color(192, 192, 192));
        panel_1.setBounds(0, 0, 784, 156);
        frame.getContentPane().add(panel_1);
        panel_1.setLayout(null);

        JLabel lblNewLabel = new JLabel("Sistema informático Colegio");
        lblNewLabel.setForeground(new Color(255, 255, 255));
        lblNewLabel.setFont(new Font("Yu Gothic Medium", Font.BOLD, 30));
        lblNewLabel.setBounds(230, 11, 637, 49);
        panel_1.add(lblNewLabel);
        
        //Logo
        JLabel lblNewLabel_1 = new JLabel("");
        lblNewLabel_1.setIcon(new ImageIcon("C:\\Users\\Propietario\\eclipse-workspace\\ClaseGraphics\\src\\img\\conocimiento.png"));
        lblNewLabel_1.setBounds(10, 11, 158, 134);
        panel_1.add(lblNewLabel_1);

        // Panel de pestañas
        JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
        tabbedPane.setBackground(new Color(238, 232, 170));
        tabbedPane.setFont(new Font("Yu Gothic Medium", Font.BOLD, 16));
        tabbedPane.setBounds(0, 154, 733, 576);
        frame.getContentPane().add(tabbedPane);

        // Pestaña para gestionar estudiantes
        JPanel panelEstudiantes = new JPanel();
        panelEstudiantes.setLayout(null);
        tabbedPane.addTab("Estudiantes", null, panelEstudiantes, null);

        // Componentes para la pestaña de estudiantes
        JLabel lblIdEstudiante = new JLabel("ID Estudiante:");
        lblIdEstudiante.setBounds(20, 20, 100, 20);
        panelEstudiantes.add(lblIdEstudiante);

        JTextField txtIdEstudiante = new JTextField();
        txtIdEstudiante.setBounds(130, 20, 150, 20);
        panelEstudiantes.add(txtIdEstudiante);

        JLabel lblNombreEstudiante = new JLabel("Nombre:");
        lblNombreEstudiante.setBounds(20, 50, 100, 20);
        panelEstudiantes.add(lblNombreEstudiante);

        JTextField txtNombreEstudiante = new JTextField();
        txtNombreEstudiante.setBounds(130, 50, 150, 20);
        panelEstudiantes.add(txtNombreEstudiante);

        JLabel lblEdadEstudiante = new JLabel("Edad:");
        lblEdadEstudiante.setBounds(20, 80, 100, 20);
        panelEstudiantes.add(lblEdadEstudiante);

        JTextField txtEdadEstudiante = new JTextField();
        txtEdadEstudiante.setBounds(130, 80, 150, 20);
        panelEstudiantes.add(txtEdadEstudiante);

        JButton btnRegistrarEstudiante = new JButton("Registrar Estudiante");
        btnRegistrarEstudiante.setBounds(20, 120, 150, 30);
        panelEstudiantes.add(btnRegistrarEstudiante);

        JTextArea txtAreaEstudiantes = new JTextArea();
        JScrollPane scrollPaneEstudiantes = new JScrollPane(txtAreaEstudiantes);
        scrollPaneEstudiantes.setBounds(300, 20, 400, 200);
        panelEstudiantes.add(scrollPaneEstudiantes);

        // Lógica para registrar estudiantes
        btnRegistrarEstudiante.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String id = txtIdEstudiante.getText();
                String nombre = txtNombreEstudiante.getText();
                int edad = Integer.parseInt(txtEdadEstudiante.getText());
                Estudiante estudiante = new Estudiante(id, nombre, edad);
                estudiantes.add(estudiante);
                txtAreaEstudiantes.append(estudiante.toString() + "\n");
                JOptionPane.showMessageDialog(frame, "Estudiante registrado con éxito!");
            }
        });

        // Pestaña para gestionar cursos
        JPanel panelCursos = new JPanel();
        panelCursos.setLayout(null);
        tabbedPane.addTab("Cursos", null, panelCursos, null);

        // Componentes para la pestaña de cursos
        JLabel lblIdCurso = new JLabel("ID Curso:");
        lblIdCurso.setBounds(20, 20, 100, 20);
        panelCursos.add(lblIdCurso);

        JTextField txtIdCurso = new JTextField();
        txtIdCurso.setBounds(130, 20, 150, 20);
        panelCursos.add(txtIdCurso);

        JLabel lblNombreCurso = new JLabel("Nombre:");
        lblNombreCurso.setBounds(20, 50, 100, 20);
        panelCursos.add(lblNombreCurso);

        JTextField txtNombreCurso = new JTextField();
        txtNombreCurso.setBounds(130, 50, 150, 20);
        panelCursos.add(txtNombreCurso);

        JButton btnRegistrarCurso = new JButton("Registrar Curso");
        btnRegistrarCurso.setBounds(20, 80, 150, 30);
        panelCursos.add(btnRegistrarCurso);

        JTextArea txtAreaCursos = new JTextArea();
        JScrollPane scrollPaneCursos = new JScrollPane(txtAreaCursos);
        scrollPaneCursos.setBounds(300, 20, 400, 200);
        panelCursos.add(scrollPaneCursos);

        // Lógica para registrar cursos
        btnRegistrarCurso.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String id = txtIdCurso.getText();
                String nombre = txtNombreCurso.getText();
                Curso curso = new Curso(id, nombre);
                cursos.add(curso);
                txtAreaCursos.append(curso.toString() + "\n");
                JOptionPane.showMessageDialog(frame, "Curso registrado con éxito!");
            }
        });

        // Pestaña para gestionar profesores
        JPanel panelProfesores = new JPanel();
        panelProfesores.setLayout(null);
        tabbedPane.addTab("Profesores", null, panelProfesores, null);

        // Componentes para la pestaña de profesores
        JLabel lblIdProfesor = new JLabel("ID Profesor:");
        lblIdProfesor.setBounds(20, 20, 100, 20);
        panelProfesores.add(lblIdProfesor);

        JTextField txtIdProfesor = new JTextField();
        txtIdProfesor.setBounds(130, 20, 150, 20);
        panelProfesores.add(txtIdProfesor);

        JLabel lblNombreProfesor = new JLabel("Nombre:");
        lblNombreProfesor.setBounds(20, 50, 100, 20);
        panelProfesores.add(lblNombreProfesor);

        JTextField txtNombreProfesor = new JTextField();
        txtNombreProfesor.setBounds(130, 50, 150, 20);
        panelProfesores.add(txtNombreProfesor);

        JButton btnRegistrarProfesor = new JButton("Registrar Profesor");
        btnRegistrarProfesor.setBounds(20, 80, 150, 30);
        panelProfesores.add(btnRegistrarProfesor);

        JTextArea txtAreaProfesores = new JTextArea();
        JScrollPane scrollPaneProfesores = new JScrollPane(txtAreaProfesores);
        scrollPaneProfesores.setBounds(300, 20, 400, 200);
        panelProfesores.add(scrollPaneProfesores);

        // Lógica para registrar profesores
        btnRegistrarProfesor.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String id = txtIdProfesor.getText();
                String nombre = txtNombreProfesor.getText();
                Profesor profesor = new Profesor(id, nombre);
                profesores.add(profesor);
                txtAreaProfesores.append(profesor.toString() + "\n");
                JOptionPane.showMessageDialog(frame, "Profesor registrado con éxito!");
            }
        });

        // Pestaña para registrar calificaciones
        JPanel panelCalificaciones = new JPanel();
        panelCalificaciones.setLayout(null);
        tabbedPane.addTab("Calificaciones", null, panelCalificaciones, null);

        // Componentes para la pestaña de calificaciones
        JLabel lblIdEstudianteCalificacion = new JLabel("ID Estudiante:");
        lblIdEstudianteCalificacion.setBounds(20, 20, 100, 20);
        panelCalificaciones.add(lblIdEstudianteCalificacion);

        JTextField txtIdEstudianteCalificacion = new JTextField();
        txtIdEstudianteCalificacion.setBounds(130, 20, 150, 20);
        panelCalificaciones.add(txtIdEstudianteCalificacion);

        JLabel lblNota = new JLabel("Nota:");
        lblNota.setBounds(20, 50, 100, 20);
        panelCalificaciones.add(lblNota);

        JTextField txtNota = new JTextField();
        txtNota.setBounds(130, 50, 150, 20);
        panelCalificaciones.add(txtNota);

        JLabel lblIdCursoCalificacion = new JLabel("ID Curso:");
        lblIdCursoCalificacion.setBounds(20, 80, 100, 20);
        panelCalificaciones.add(lblIdCursoCalificacion);

        JTextField txtIdCursoCalificacion = new JTextField();
        txtIdCursoCalificacion.setBounds(130, 80, 150, 20);
        panelCalificaciones.add(txtIdCursoCalificacion);

        JButton btnRegistrarCalificacion = new JButton("Registrar Calificación");
        btnRegistrarCalificacion.setBounds(20, 120, 150, 30);
        panelCalificaciones.add(btnRegistrarCalificacion);

        JTextArea txtAreaCalificaciones = new JTextArea();
        JScrollPane scrollPaneCalificaciones = new JScrollPane(txtAreaCalificaciones);
        scrollPaneCalificaciones.setBounds(300, 20, 400, 200);
        panelCalificaciones.add(scrollPaneCalificaciones);

        // Lógica para registrar calificaciones
        btnRegistrarCalificacion.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String idEstudiante = txtIdEstudianteCalificacion.getText();
                String idCurso = txtIdCursoCalificacion.getText();
                double nota = Double.parseDouble(txtNota.getText());

                Estudiante estudiante = estudiantes.stream()
                        .filter(est -> est.getId().equals(idEstudiante)) //Es una expresión lambda que define la condición de filtrado.
                        .findFirst()
                        .orElse(null);

                Curso curso = cursos.stream()
                        .filter(cur -> cur.getId().equals(idCurso))
                        .findFirst()
                        .orElse(null);

                if (estudiante != null && curso != null) {
                    Calificacion calificacion = new Calificacion(estudiante, nota);
                    curso.agregarCalificacion(calificacion);
                    txtAreaCalificaciones.append("Curso: " + curso.getNombre() + ", " + calificacion.toString() + "\n");
                    JOptionPane.showMessageDialog(frame, "Calificación registrada con éxito!");
                } else {
                    JOptionPane.showMessageDialog(frame, "Estudiante o curso no encontrado.");
                }
            }
        });

        // Pestaña para vincular estudiantes con cursos y profesores
        JPanel panelVinculacion = new JPanel();
        panelVinculacion.setLayout(null);
        tabbedPane.addTab("Vincular", null, panelVinculacion, null);

        // Componentes para la pestaña de vinculación
        JLabel lblIdEstudianteVinculacion = new JLabel("ID Estudiante:");
        lblIdEstudianteVinculacion.setBounds(20, 20, 100, 20);
        panelVinculacion.add(lblIdEstudianteVinculacion);

        JTextField txtIdEstudianteVinculacion = new JTextField();
        txtIdEstudianteVinculacion.setBounds(130, 20, 150, 20);
        panelVinculacion.add(txtIdEstudianteVinculacion);

        JLabel lblIdCursoVinculacion = new JLabel("ID Curso:");
        lblIdCursoVinculacion.setBounds(20, 50, 100, 20);
        panelVinculacion.add(lblIdCursoVinculacion);

        JTextField txtIdCursoVinculacion = new JTextField();
        txtIdCursoVinculacion.setBounds(130, 50, 150, 20);
        panelVinculacion.add(txtIdCursoVinculacion);

        JLabel lblIdProfesorVinculacion = new JLabel("ID Profesor:");
        lblIdProfesorVinculacion.setBounds(20, 80, 100, 20);
        panelVinculacion.add(lblIdProfesorVinculacion);

        JTextField txtIdProfesorVinculacion = new JTextField();
        txtIdProfesorVinculacion.setBounds(130, 80, 150, 20);
        panelVinculacion.add(txtIdProfesorVinculacion);

        JButton btnVincular = new JButton("Vincular");
        btnVincular.setBounds(20, 120, 150, 30);
        panelVinculacion.add(btnVincular);

        JTextArea txtAreaVinculacion = new JTextArea();
        JScrollPane scrollPaneVinculacion = new JScrollPane(txtAreaVinculacion);
        scrollPaneVinculacion.setBounds(300, 20, 400, 200);
        panelVinculacion.add(scrollPaneVinculacion);

        // Lógica para vincular estudiantes con cursos y profesores
        btnVincular.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String idEstudiante = txtIdEstudianteVinculacion.getText();
                String idCurso = txtIdCursoVinculacion.getText();
                String idProfesor = txtIdProfesorVinculacion.getText();

                Estudiante estudiante = estudiantes.stream()
                        .filter(est -> est.getId().equals(idEstudiante))
                        .findFirst()
                        .orElse(null);

                Curso curso = cursos.stream()
                        .filter(cur -> cur.getId().equals(idCurso))
                        .findFirst()
                        .orElse(null);

                Profesor profesor = profesores.stream()
                        .filter(prof -> prof.getId().equals(idProfesor))
                        .findFirst()
                        .orElse(null);

                if (estudiante != null && curso != null && profesor != null) {
                    curso.agregarEstudiante(estudiante);
                    curso.setProfesor(profesor);
                    txtAreaVinculacion.append("Estudiante " + estudiante.getNombre() + " vinculado al curso " + curso.getNombre() + " con el profesor " + profesor.getNombre() + "\n");
                    JOptionPane.showMessageDialog(frame, "Vinculación realizada con éxito!");
                } else {
                    JOptionPane.showMessageDialog(frame, "Estudiante, curso o profesor no encontrado.");
                }
            }
        });

        // Pestaña para generar reportes
        JPanel panelReportes = new JPanel();
        panelReportes.setLayout(null);
        tabbedPane.addTab("Reportes", null, panelReportes, null);

        // Componentes para la pestaña de reportes
        JTextArea txtAreaReportes = new JTextArea();
        JScrollPane scrollPaneReportes = new JScrollPane(txtAreaReportes);
        scrollPaneReportes.setBounds(20, 20, 680, 400);
        panelReportes.add(scrollPaneReportes);

        JButton btnGenerarReporte = new JButton("Generar Reporte");
        btnGenerarReporte.setBounds(20, 440, 150, 30);
        panelReportes.add(btnGenerarReporte);

        // Lógica para generar reportes
        btnGenerarReporte.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                txtAreaReportes.setText(""); // Limpiar el área de texto
                txtAreaReportes.append("=== Reporte de Estudiantes ===\n");
                for (Estudiante estudiante : estudiantes) {
                    txtAreaReportes.append(estudiante.toString() + "\n");
                    for (Curso curso : cursos) {
                        if (curso.getEstudiantes().contains(estudiante)) {
                            txtAreaReportes.append("  - Curso: " + curso.getNombre() + ", Profesor: " + (curso.getProfesor() != null ? curso.getProfesor().getNombre() : "No asignado") + "\n");
                            for (Calificacion calificacion : curso.getCalificaciones()) {
                                if (calificacion.getEstudiante().equals(estudiante)) {
                                    txtAreaReportes.append("    - Calificación: " + calificacion.getNota() + "\n");
                                }
                            }
                        }
                    }
                }
                txtAreaReportes.append("\n=== Reporte de Cursos ===\n");
                for (Curso curso : cursos) {
                    txtAreaReportes.append(curso.toString() + "\n");
                    txtAreaReportes.append("  - Profesor: " + (curso.getProfesor() != null ? curso.getProfesor().getNombre() : "No asignado") + "\n");
                    txtAreaReportes.append("  - Estudiantes:\n");
                    for (Estudiante estudiante : curso.getEstudiantes()) {
                        txtAreaReportes.append("    - " + estudiante.getNombre() + "\n");
                    }
                }
                txtAreaReportes.append("\n=== Reporte de Profesores ===\n");
                for (Profesor profesor : profesores) {
                    txtAreaReportes.append(profesor.toString() + "\n");
                    for (Curso curso : cursos) {
                        if (curso.getProfesor() != null && curso.getProfesor().equals(profesor)) {
                            txtAreaReportes.append("  - Curso: " + curso.getNombre() + "\n");
                        }
                    }
                }
                JOptionPane.showMessageDialog(frame, "Reporte generado con éxito!");
            }
        });
    }
}