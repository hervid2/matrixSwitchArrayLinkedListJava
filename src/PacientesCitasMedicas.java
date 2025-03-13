import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.ImageIcon;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;

public class PacientesCitasMedicas {

    private JFrame frame;
    private final JPanel panel = new JPanel();
    private List<Paciente> pacientes = new ArrayList<>();
    private List<Medico> medicos = new ArrayList<>();
    private List<Cita> citas = new ArrayList<>();

   
    
    
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    PacientesCitasMedicas window = new PacientesCitasMedicas();
                    window.frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

   // Método Constructor
    public PacientesCitasMedicas() {
        initialize();
    }

    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {
        frame = new JFrame();
        frame.setBounds(100, 100, 550, 544);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);

        JPanel panel_1 = new JPanel();
        panel_1.setBounds(0, 0, 750, 127);
        panel_1.setBackground(Color.RED);
        frame.getContentPane().add(panel_1);
        panel_1.setLayout(null);

        JLabel lblNewLabel = new JLabel("Software Citas Médicas Pacientes");
        lblNewLabel.setForeground(Color.WHITE);
        lblNewLabel.setFont(new Font("Yu Gothic Medium", Font.BOLD, 20));
        lblNewLabel.setBounds(141, 34, 387, 55);
        panel_1.add(lblNewLabel);

        JLabel lblNewLabel_1 = new JLabel("");
        lblNewLabel_1.setIcon(new ImageIcon("C:\\Users\\Propietario\\eclipse-workspace\\ClaseGraphics\\src\\img\\cruz-roja.png"));
        lblNewLabel_1.setBounds(40, 23, 91, 66);
        panel_1.add(lblNewLabel_1);

        JPanel panel_2 = new JPanel();
        panel_2.setBackground(Color.WHITE);
        panel_2.setBounds(0, 126, 534, 357);
        frame.getContentPane().add(panel_2);

        // Crear la barra de menú principal
        JMenuBar menuBar = new JMenuBar();
        menuBar.setFont(new Font("Yu Gothic Medium", Font.BOLD, 14));
        frame.setJMenuBar(menuBar);

        // Menú de Pacientes
        JMenu mnPacientes = new JMenu("Pacientes");
        menuBar.add(mnPacientes);

        JMenuItem mntmRegistrarPaciente = new JMenuItem("Registrar Paciente");
        mntmRegistrarPaciente.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String nombre = JOptionPane.showInputDialog(frame, "Ingrese el nombre del paciente:");
                String telefono = JOptionPane.showInputDialog(frame, "Ingrese el teléfono del paciente:");
                pacientes.add(new Paciente(nombre, telefono));
                JOptionPane.showMessageDialog(frame, "Paciente registrado con éxito.");
            }
        });
        mnPacientes.add(mntmRegistrarPaciente);

        JMenuItem mntmEditarPaciente = new JMenuItem("Editar Paciente");
        mntmEditarPaciente.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String nombre = JOptionPane.showInputDialog(frame, "Ingrese el nombre del paciente a editar:");
                for (Paciente paciente : pacientes) {
                    if (paciente.getNombre().equals(nombre)) {
                        String nuevoNombre = JOptionPane.showInputDialog(frame, "Ingrese el nuevo nombre:");
                        String nuevoTelefono = JOptionPane.showInputDialog(frame, "Ingrese el nuevo teléfono:");
                        paciente.setNombre(nuevoNombre);
                        paciente.setTelefono(nuevoTelefono);
                        JOptionPane.showMessageDialog(frame, "Paciente editado con éxito.");
                        return;
                    }
                }
                JOptionPane.showMessageDialog(frame, "Paciente no encontrado.");
            }
        });
        mnPacientes.add(mntmEditarPaciente);

        JMenuItem mntmEliminarPaciente = new JMenuItem("Eliminar Paciente");
        mntmEliminarPaciente.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String nombre = JOptionPane.showInputDialog(frame, "Ingrese el nombre del paciente a eliminar:");
                pacientes.removeIf(paciente -> paciente.getNombre().equals(nombre)); // .removeIf se usa para eliminar elementos de una colección basándose en una condición
                JOptionPane.showMessageDialog(frame, "Paciente eliminado con éxito.");
            }
        });
        mnPacientes.add(mntmEliminarPaciente);
        
        // Construye una cadena de texto (String) con la información de todos los pacientes.
        JMenuItem mntmConsultarPacientes = new JMenuItem("Consultar Pacientes");
        mntmConsultarPacientes.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                StringBuilder sb = new StringBuilder(); // StringBuilder Es una clase en Java que permite construir cadenas de texto
                for (Paciente paciente : pacientes) {
                    sb.append(paciente).append("\n");
                }
                JOptionPane.showMessageDialog(frame, sb.toString());
            }
        });
        mnPacientes.add(mntmConsultarPacientes);
        

        // Menú de Médicos
        JMenu mnMedicos = new JMenu("Médicos");
        menuBar.add(mnMedicos);

        JMenuItem mntmRegistrarMedico = new JMenuItem("Registrar Médico");
        mntmRegistrarMedico.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String nombre = JOptionPane.showInputDialog(frame, "Ingrese el nombre del médico:");
                String especialidad = JOptionPane.showInputDialog(frame, "Ingrese la especialidad del médico:");
                medicos.add(new Medico(nombre, especialidad));
                JOptionPane.showMessageDialog(frame, "Médico registrado con éxito.");
            }
        });
        mnMedicos.add(mntmRegistrarMedico);

        JMenuItem mntmEditarMedico = new JMenuItem("Editar Médico");
        mntmEditarMedico.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String nombre = JOptionPane.showInputDialog(frame, "Ingrese el nombre del médico a editar:");
                for (Medico medico : medicos) {
                    if (medico.getNombre().equals(nombre)) {
                        String nuevoNombre = JOptionPane.showInputDialog(frame, "Ingrese el nuevo nombre:");
                        String nuevaEspecialidad = JOptionPane.showInputDialog(frame, "Ingrese la nueva especialidad:");
                        medico.setNombre(nuevoNombre);
                        medico.setEspecialidad(nuevaEspecialidad);
                        JOptionPane.showMessageDialog(frame, "Médico editado con éxito.");
                        return;
                    }
                }
                JOptionPane.showMessageDialog(frame, "Médico no encontrado.");
            }
        });
        mnMedicos.add(mntmEditarMedico);

        JMenuItem mntmEliminarMedico = new JMenuItem("Eliminar Médico");
        mntmEliminarMedico.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String nombre = JOptionPane.showInputDialog(frame, "Ingrese el nombre del médico a eliminar:");
                medicos.removeIf(medico -> medico.getNombre().equals(nombre));
                JOptionPane.showMessageDialog(frame, "Médico eliminado con éxito.");
            }
        });
        mnMedicos.add(mntmEliminarMedico);

        JMenuItem mntmConsultarMedicos = new JMenuItem("Consultar Médicos");
        mntmConsultarMedicos.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                StringBuilder sb = new StringBuilder();
                for (Medico medico : medicos) {
                    sb.append(medico).append("\n");
                }
                JOptionPane.showMessageDialog(frame, sb.toString());
            }
        });
        mnMedicos.add(mntmConsultarMedicos);
        

        // Menú de Citas
        JMenu mnCitas = new JMenu("Citas");
        menuBar.add(mnCitas);

        JMenuItem mntmAgendarCita = new JMenuItem("Agendar Cita");
        mntmAgendarCita.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String nombrePaciente = JOptionPane.showInputDialog(frame, "Ingrese el nombre del paciente:");
                String nombreMedico = JOptionPane.showInputDialog(frame, "Ingrese el nombre del médico:");
                String fecha = JOptionPane.showInputDialog(frame, "Ingrese la fecha de la cita (dd/MM/yyyy):");
                Paciente paciente = pacientes.stream().filter(p -> p.getNombre().equals(nombrePaciente)).findFirst().orElse(null); // stream() es un método mas conciso para recorrer un arreglo
                Medico medico = medicos.stream().filter(m -> m.getNombre().equals(nombreMedico)).findFirst().orElse(null); // orElse(null) Si no se encuentra ningún paciente, devuelve null.
                if (paciente != null && medico != null) {
                    citas.add(new Cita(paciente, medico, fecha));
                    JOptionPane.showMessageDialog(frame, "Cita agendada con éxito.");
                } else {
                    JOptionPane.showMessageDialog(frame, "Paciente o médico no encontrado.");
                }
            }
        });
        mnCitas.add(mntmAgendarCita);

        JMenuItem mntmConsultarCitas = new JMenuItem("Consultar Citas");
        mntmConsultarCitas.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                StringBuilder sb = new StringBuilder();
                for (Cita cita : citas) {
                    sb.append(cita).append("\n");
                }
                JOptionPane.showMessageDialog(frame, sb.toString());
            }
        });
        mnCitas.add(mntmConsultarCitas);

        JMenuItem mntmCancelarCita = new JMenuItem("Cancelar Cita");
        mntmCancelarCita.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String nombrePaciente = JOptionPane.showInputDialog(frame, "Ingrese el nombre del paciente:");
                String fecha = JOptionPane.showInputDialog(frame, "Ingrese la fecha de la cita a cancelar (dd/MM/yyyy):");
                citas.removeIf(cita -> cita.getPaciente().getNombre().equals(nombrePaciente) && cita.getFecha().equals(fecha));
                JOptionPane.showMessageDialog(frame, "Cita cancelada con éxito.");
            }
        });
        mnCitas.add(mntmCancelarCita);
    }

    
    // Clases internas para Paciente, Médico y Cita
    // Paciente
    class Paciente {
        private String nombre;
        private String telefono;

        public Paciente(String nombre, String telefono) {
            this.nombre = nombre;
            this.telefono = telefono;
        }

        public String getNombre() {
            return nombre;
        }

        public void setNombre(String nombre) {
            this.nombre = nombre;
        }

        public String getTelefono() {
            return telefono;
        }

        public void setTelefono(String telefono) {
            this.telefono = telefono;
        }

        
        public String toString() {
            return "Paciente [nombre=" + nombre + ", telefono=" + telefono + "]";
        }
    }
    
    // Médico
    class Medico {
        private String nombre;
        private String especialidad;

        public Medico(String nombre, String especialidad) {
            this.nombre = nombre;
            this.especialidad = especialidad;
        }

        public String getNombre() {
            return nombre;
        }

        public void setNombre(String nombre) {
            this.nombre = nombre;
        }

        public String getEspecialidad() {
            return especialidad;
        }

        public void setEspecialidad(String especialidad) {
            this.especialidad = especialidad;
        }

        
        public String toString() {
            return "Médico [nombre=" + nombre + ", especialidad=" + especialidad + "]";
        }
    }
    
    // Cita 
    class Cita {
        private Paciente paciente;
        private Medico medico;
        private String fecha;

        public Cita(Paciente paciente, Medico medico, String fecha) {
            this.paciente = paciente;
            this.medico = medico;
            this.fecha = fecha;
        }

        public Paciente getPaciente() {
            return paciente;
        }

        public Medico getMedico() {
            return medico;
        }

        public String getFecha() {
            return fecha;
        }

       
        public String toString() {
            return "Cita [paciente=" + paciente.getNombre() + ", médico=" + medico.getNombre() + ", fecha=" + fecha + "]";
        }
    }
}
