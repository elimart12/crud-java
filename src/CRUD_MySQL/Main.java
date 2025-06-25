package CRUD_MySQL;

import java.sql.SQLException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int opcion;

        do {
            mostrarMenu();
            System.out.print("Seleccione una opción: ");
            opcion = scanner.nextInt();
            scanner.nextLine(); // Limpiar buffer
            try {
                procesarOpcion(opcion); // <- esto debe estar fuera del main
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }

        } while (opcion != 5);
    }

    private static void mostrarMenu() {
        System.out.println("\n=== Menú ===");
        System.out.println("1. Insertar persona");
        System.out.println("2. Listar personas");
        System.out.println("3. Actualizar persona");
        System.out.println("4. Eliminar persona");
        System.out.println("5. Salir");
    }

    // ⛔ Estaba mal ubicada antes (dentro de main), ya está corregida
    private static void procesarOpcion(int opcion) throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        Scanner scanner = new Scanner(System.in);
        PersonaDAO dao = new PersonaDAO();

        switch (opcion) {
            case 1:
                System.out.print("Nombre: ");
                String nombre = scanner.nextLine();
                System.out.print("Edad: ");
                int edad = scanner.nextInt();
                scanner.nextLine(); // limpiar buffer
                dao.insertar(new Persona(nombre, edad));
                break;
            case 2:
                dao.listar();
                break;
            case 3:
                System.out.print("ID de la persona a actualizar: ");
                int idActualizar = scanner.nextInt();
                scanner.nextLine();
                System.out.print("Nuevo nombre: ");
                String nuevoNombre = scanner.nextLine();
                System.out.print("Nueva edad: ");
                int nuevaEdad = scanner.nextInt();
                dao.actualizar(new Persona(idActualizar, nuevoNombre, nuevaEdad));
                break;
            case 4:
                System.out.print("ID de la persona a eliminar: ");
                int idEliminar = scanner.nextInt();
                dao.eliminar(idEliminar);
                break;
            case 5:
                System.out.println("Saliendo...");
                break;
            default:
                System.out.println("Opción no válida.");
        }
    }
}

