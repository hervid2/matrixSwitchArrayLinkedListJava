import java.awt.EventQueue;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

// Clase Producto, atributos y métodos de Producto
class Producto {
    private int id; // ID del producto
    private String nombre; // Nombre del producto
    private double precio; // Precio del producto
    private int cantidad; // Cantidad disponible en stock
    private int cantidadVendida; // Cantidad vendida de stock

    //Clase constructora de Producto que incluye como parámetro los atributos de clase y crea un objeto 
    public Producto(int id, String nombre, double precio, int cantidad) {
        this.id = id; // inicialización de los parámetros
        this.nombre = nombre;
        this.precio = precio;
        this.cantidad = cantidad;
        this.cantidadVendida = 0; // Inicialmente no se ha vendido nada
    }

    // Getters y setters
    public int getId() { //Métodos getters y setters para acceder y modificar los atributos de la clase.
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public double getPrecio() {
        return precio;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public int getCantidadVendida() {
        return cantidadVendida;
    }

    public void setCantidadVendida(int cantidadVendida) {
        this.cantidadVendida = cantidadVendida;
    }

    
    //Método para mostrar la descripción de inventario
    public String toString() { 
        return "ID: " + id + ", Nombre: " + nombre + ", Precio: $" + precio +
               ", Stock: " + cantidad + ", Vendidos: " + cantidadVendida +
               ", Total Vendido: $" + (precio * cantidadVendida);
    }
}


// Clase GestionInventario, manejo de interfaz gráfica , gestión inventario
public class GestionInventario {

    private JFrame frame;
    private List<Producto> inventario; //Lista que almacena productos que se mostrarán en la seccion Inventario
    
    // Clase main que es punto de entrada y ejecución
    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                GestionInventario window = new GestionInventario();
                window.frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
    
    // Constructor de la clase que inicializa la lista "inventario" y llama al método inicialize() para configurar la interfaz gráfica
    public GestionInventario() {
        inventario = new ArrayList<>();
        initialize();
    }

    private void initialize() {
        frame = new JFrame();
        frame.setBounds(100, 100, 809, 669);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);

        JPanel panel = new JPanel();
        panel.setBounds(0, 0, 799, 113);
        panel.setBackground(new Color(60, 179, 113));
        frame.getContentPane().add(panel);
        panel.setLayout(null);

        JLabel lblNewLabel = new JLabel("Inventario Tienda En Linea");
        lblNewLabel.setForeground(new Color(255, 255, 255));
        lblNewLabel.setBounds(244, 33, 396, 49);
        panel.add(lblNewLabel);
        lblNewLabel.setFont(new Font("Yu Gothic Medium", Font.BOLD, 30));

        JLabel lblNewLabel_1 = new JLabel("");
        lblNewLabel_1.setIcon(new ImageIcon("C:\\Users\\Propietario\\eclipse-workspace\\ClaseGraphics\\src\\img\\icons8-online-shop-100.png"));
        lblNewLabel_1.setBounds(85, 11, 100, 91);
        panel.add(lblNewLabel_1);
         
        // Creacion de la interfaz de las pestañas
        JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.LEFT); //Left, ubica a la izquierda las pestañas en la GUI
        tabbedPane.setFont(new Font("Yu Gothic Medium", Font.BOLD, 20));
        tabbedPane.setBackground(new Color(255, 204, 102));
        tabbedPane.setBounds(0, 124, 771, 438);
        frame.getContentPane().add(tabbedPane);

        // Panel para agregar producto
        JPanel agregarProductoPanel = new JPanel();
        agregarProductoPanel.setLayout(null);
        tabbedPane.addTab("Agregar Producto", new ImageIcon("C:\\Users\\Propietario\\eclipse-workspace\\ClaseGraphics\\src\\img\\agregar-producto.png"), agregarProductoPanel, "Agrega nuevos productos aquí"); //se agrega el panel agregarProductoPanel como pestaña al tabbedPane

        JLabel lblId = new JLabel("ID:");
        lblId.setBounds(50, 50, 100, 30);
        agregarProductoPanel.add(lblId);

        JTextField idField = new JTextField();
        idField.setBounds(150, 50, 200, 30);
        agregarProductoPanel.add(idField);

        JLabel lblNombre = new JLabel("Nombre:");
        lblNombre.setBounds(50, 100, 100, 30);
        agregarProductoPanel.add(lblNombre);

        JTextField nombreField = new JTextField();
        nombreField.setBounds(150, 100, 200, 30);
        agregarProductoPanel.add(nombreField);

        JLabel lblPrecio = new JLabel("Precio:");
        lblPrecio.setBounds(50, 150, 100, 30);
        agregarProductoPanel.add(lblPrecio);

        JTextField precioField = new JTextField();
        precioField.setBounds(150, 150, 200, 30);
        agregarProductoPanel.add(precioField);

        JLabel lblStock = new JLabel("Stock:");
        lblStock.setBounds(50, 200, 100, 30);
        agregarProductoPanel.add(lblStock);

        JTextField stockField = new JTextField();
        stockField.setBounds(150, 200, 200, 30);
        agregarProductoPanel.add(stockField);

        JButton agregarButton = new JButton("Agregar Producto"); //Botón agregar producto
        agregarButton.setBounds(150, 250, 200, 30);
        agregarButton.addActionListener(e -> {
            int id = Integer.parseInt(idField.getText());
            String nombre = nombreField.getText();
            double precio = Double.parseDouble(precioField.getText());
            int stock = Integer.parseInt(stockField.getText());
            inventario.add(new Producto(id, nombre, precio, stock));
            JOptionPane.showMessageDialog(frame, "Producto agregado con éxito!");
        });
        agregarProductoPanel.add(agregarButton);

        // Botón para limpiar campos en agregar producto
        JButton limpiarAgregarButton = new JButton("Limpiar Campos");
        limpiarAgregarButton.setBounds(374, 250, 150, 30);
        limpiarAgregarButton.addActionListener(e -> {
            idField.setText("");
            nombreField.setText("");
            precioField.setText("");
            stockField.setText("");
        });
        agregarProductoPanel.add(limpiarAgregarButton);
        

        // Panel para ver inventario 
        JPanel verInventarioPanel = new JPanel();
        verInventarioPanel.setLayout(new BorderLayout());
        tabbedPane.addTab("Ver Inventario", new ImageIcon("C:\\Users\\Propietario\\eclipse-workspace\\ClaseGraphics\\src\\img\\inventario.png"), verInventarioPanel, "Actualiza, revisa inventario y ventas");

        JTextArea inventarioArea = new JTextArea();
        inventarioArea.setEditable(false); // Hace que el TextArea no pueda ser editado por el usuario
        JScrollPane scrollPane = new JScrollPane(inventarioArea);
        verInventarioPanel.add(scrollPane, BorderLayout.CENTER); //Agrega un scrollpane al JTextArea

        JButton actualizarInventarioButton = new JButton("Actualizar Inventario");
        actualizarInventarioButton.addActionListener(e -> { // e es una expresion Lambda, que representa el evento de clic
            inventarioArea.setText(""); // Inicializa el campo de texto vacío para luego poder agregar la información que el usuario ingresa
            for (Producto producto : inventario) { // ForEach para recorrer la lista "inventario" y agregar atributos(en este caso actualizar stock) de Producto con producto que ya está alojado en inventario
                inventarioArea.append(producto.toString() + "\n"); //añade la siguiente cadena (.toString une todo lo que se agrega en la variable temporal "producto" como un solo string) de texto al final del contenido actual del JTextArea
            }
        });
        verInventarioPanel.add(actualizarInventarioButton, BorderLayout.SOUTH);

        // Panel para registrar venta
        JPanel registrarVentaPanel = new JPanel();
        registrarVentaPanel.setLayout(null);
        tabbedPane.addTab("Registrar Venta", new ImageIcon("C:\\Users\\Propietario\\eclipse-workspace\\ClaseGraphics\\src\\img\\cajero.png"), registrarVentaPanel, "Lleva registro de las ventas hechas");

        JLabel lblIdVenta = new JLabel("ID Producto:");
        lblIdVenta.setBounds(50, 50, 100, 30);
        registrarVentaPanel.add(lblIdVenta);

        JTextField idVentaField = new JTextField();
        idVentaField.setBounds(150, 50, 200, 30);
        registrarVentaPanel.add(idVentaField);

        JLabel lblCantidadVenta = new JLabel("Cantidad:");
        lblCantidadVenta.setBounds(50, 100, 100, 30);
        registrarVentaPanel.add(lblCantidadVenta);

        JTextField cantidadVentaField = new JTextField();
        cantidadVentaField.setBounds(150, 100, 200, 30);
        registrarVentaPanel.add(cantidadVentaField);

        JButton registrarVentaButton = new JButton("Registrar Venta");
        registrarVentaButton.setBounds(150, 150, 200, 30);
        registrarVentaButton.addActionListener(e -> {
            int idProducto = Integer.parseInt(idVentaField.getText());
            int cantidadVenta = Integer.parseInt(cantidadVentaField.getText());
            boolean productoEncontrado = false;

            for (Producto producto : inventario) {
                if (producto.getId() == idProducto) {
                    if (producto.getCantidad() >= cantidadVenta) {
                        producto.setCantidad(producto.getCantidad() - cantidadVenta);
                        producto.setCantidadVendida(producto.getCantidadVendida() + cantidadVenta);
                        JOptionPane.showMessageDialog(frame, "Venta registrada con éxito!");
                        productoEncontrado = true;
                    } else {
                        JOptionPane.showMessageDialog(frame, "No hay suficiente stock para este producto.");
                    }
                    break;
                }
            }

            if (!productoEncontrado) {
                JOptionPane.showMessageDialog(frame, "Producto no encontrado.");
            }
        });
        registrarVentaPanel.add(registrarVentaButton);

        // Botón para limpiar campos en registrar venta
        JButton limpiarVentaButton = new JButton("Limpiar Campos");
        limpiarVentaButton.setBounds(360, 150, 150, 30);
        limpiarVentaButton.addActionListener(e -> {
            idVentaField.setText("");
            cantidadVentaField.setText("");
        });
        registrarVentaPanel.add(limpiarVentaButton);

        
        // Panel para actualizar stock
        JPanel actualizarStockPanel = new JPanel();
        tabbedPane.addTab("Actualizar Stock", new ImageIcon("C:\\Users\\Propietario\\eclipse-workspace\\ClaseGraphics\\src\\img\\actualizar-flecha.png"), actualizarStockPanel, "Agrega nuevo stock a un producto existente");
        actualizarStockPanel.setLayout(null);

        JLabel lblIdStock = new JLabel("ID Producto:");
        lblIdStock.setBounds(50, 50, 100, 30);
        actualizarStockPanel.add(lblIdStock);

        JTextField idStockField = new JTextField();
        idStockField.setBounds(150, 50, 200, 30);
        actualizarStockPanel.add(idStockField);

        JLabel lblNuevoStock = new JLabel("Nuevo Stock:");
        lblNuevoStock.setBounds(50, 100, 100, 30);
        actualizarStockPanel.add(lblNuevoStock);

        JTextField nuevoStockField = new JTextField();
        nuevoStockField.setBounds(150, 100, 200, 30);
        actualizarStockPanel.add(nuevoStockField);

        JButton actualizarStockButton = new JButton("Actualizar Stock");
        actualizarStockButton.setBounds(150, 150, 200, 30);
        actualizarStockButton.addActionListener(e -> {
            int idProducto = Integer.parseInt(idStockField.getText());
            int nuevoStock = Integer.parseInt(nuevoStockField.getText());
            boolean productoEncontrado = false;

            for (Producto producto : inventario) {
                if (producto.getId() == idProducto) {
                    // Sumar el nuevo stock al stock existente
                    producto.setCantidad(producto.getCantidad() + nuevoStock);
                    JOptionPane.showMessageDialog(frame, "Stock actualizado con éxito!");
                    productoEncontrado = true;
                    break;
                }
            }

            if (!productoEncontrado) {
                JOptionPane.showMessageDialog(frame, "Producto no encontrado.");
            }
        });
        actualizarStockPanel.add(actualizarStockButton);

        // Botón para limpiar campos en actualizar stock
        JButton limpiarStockButton = new JButton("Limpiar Campos");
        limpiarStockButton.setBounds(360, 150, 150, 30);
        limpiarStockButton.addActionListener(e -> {
            idStockField.setText("");
            nuevoStockField.setText("");
        });
        actualizarStockPanel.add(limpiarStockButton);
    }
}