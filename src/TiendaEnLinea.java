import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TiendaEnLinea {

    static class Producto {
        String nombre;
        double precio;

        Producto(String nombre, double precio) {
            this.nombre = nombre;
            this.precio = precio;
        }

        public String toString() {
            return nombre + " - $" + precio;
        }
    }

    static List<Producto> productosDisponibles = new ArrayList<>();
    static List<Producto> carrito = new ArrayList<>();
    static double saldo = 500.00;

    public static void main(String[] args) {
        productosDisponibles.add(new Producto("Pastel de Fresas", 180.00));
        productosDisponibles.add(new Producto("Galletas Decoradas", 50.00));
        productosDisponibles.add(new Producto("Cupcakes", 75.00));

        Scanner scanner = new Scanner(System.in);
        boolean seguir = true;

        while (seguir) {
            System.out.println("\n--- Bienvenido a la Tienda de Pastelería Dulce Tentacion---");
            System.out.println("1. Ver productos disponibles");
            System.out.println("2. Agregar producto al carrito");
            System.out.println("3. Realizar compra");
            System.out.println("4. Consultar saldo");
            System.out.println("5. Recargar saldo");
            System.out.println("6. Salir");
            System.out.print("Selecciona una opción: ");
            int opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1:
                    System.out.println("\n--- Productos disponibles ---");
                    for (int i = 0; i < productosDisponibles.size(); i++) {
                        System.out.println((i + 1) + ". " + productosDisponibles.get(i));
                    }
                    break;

                case 2:
                    System.out.print("Ingresa el número del producto que deseas agregar al carrito: ");
                    int productoSeleccionado = scanner.nextInt();
                    if (productoSeleccionado > 0 && productoSeleccionado <= productosDisponibles.size()) {
                        Producto producto = productosDisponibles.get(productoSeleccionado - 1);
                        carrito.add(producto);
                        System.out.println("Producto agregado al carrito: " + producto);
                    } else {
                        System.out.println("Opción no válida.");
                    }
                    break;

                case 3:
                    double totalCompra = 0;
                    for (Producto p : carrito) {
                        totalCompra += p.precio;
                    }
                    if (totalCompra > 0) {
                        if (totalCompra <= saldo) {
                            saldo -= totalCompra;
                            carrito.clear();
                            System.out.println("Compra realizada con éxito. Total: $" + totalCompra);
                            System.out.println("Saldo disponible actual: $" + saldo);
                        } else {
                            System.out.println("No tienes suficiente saldo para realizar la compra.");
                        }
                    } else {
                        System.out.println("El carrito está vacío.");
                    }
                    break;

                case 4:
                    System.out.println("Saldo disponible: $" + saldo);
                    break;

                case 5:
                    System.out.print("¿Cuánto deseas recargar? $");
                    double cantidadRecarga = scanner.nextDouble();
                    if (cantidadRecarga > 0) {
                        saldo += cantidadRecarga;
                        System.out.println("¡Recarga exitosa! Nuevo saldo: $" + saldo);
                    } else {
                        System.out.println("Cantidad no válida.");
                    }
                    break;

                case 6:
                    System.out.println("¡Gracias por usar el sistema! ¡Hasta luego!");
                    seguir = false;
                    break;

                default:
                    System.out.println("Opción no válida, por favor elige de nuevo.");
                    break;
            }
        }

        scanner.close();
    }
}