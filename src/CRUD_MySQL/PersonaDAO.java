package CRUD_MySQL;

import java.sql.*;

public class PersonaDAO {

    private Connection conexion;

    /**
     * Constructor: establece la conexión a la base de datos.
     */
    public PersonaDAO() throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        conectar();
    }

    /**
     * Método para conectar a la base de datos MySQL.
     * Cambia los valores según tu base local o remota (ej. db4free).
     */
    private void conectar() throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
        conexion = DriverManager.getConnection(
            "jdbc:mysql://localhost:3306/crud_java?useSSL=false&serverTimezone=UTC", 
            "root", // Usuario
            "clave123"      // Contraseña
        );
    }

    /**
     * Inserta una nueva persona en la tabla.
     */
    public void insertar(Persona persona) {
        String sql = "INSERT INTO personas (nombre, edad) VALUES (?, ?)";
        try (PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setString(1, persona.getNombre());
            ps.setInt(2, persona.getEdad());
            ps.executeUpdate();
            System.out.println("✅ Persona insertada exitosamente.");
        } catch (SQLException e) {
            System.err.println("❌ Error al insertar persona: " + e.getMessage());
        }
    }

    /**
     * Lista todas las personas registradas en la tabla.
     */
    public void listar() {
        String sql = "SELECT * FROM personas";
        try (Statement stmt = conexion.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            System.out.println("📋 Lista de personas:");
            while (rs.next()) {
                System.out.println("ID: " + rs.getInt("id") +
                                   ", Nombre: " + rs.getString("nombre") +
                                   ", Edad: " + rs.getInt("edad"));
            }
        } catch (SQLException e) {
            System.err.println("❌ Error al listar personas: " + e.getMessage());
        }
    }

    /**
     * Actualiza una persona por su ID.
     */
    public void actualizar(Persona persona) {
        String sql = "UPDATE personas SET nombre = ?, edad = ? WHERE id = ?";
        try (PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setString(1, persona.getNombre());
            ps.setInt(2, persona.getEdad());
            ps.setInt(3, persona.getId());
            int filas = ps.executeUpdate();
            if (filas > 0) {
                System.out.println("✅ Persona actualizada exitosamente.");
            } else {
                System.out.println("⚠️ No se encontró una persona con ese ID.");
            }
        } catch (SQLException e) {
            System.err.println("❌ Error al actualizar persona: " + e.getMessage());
        }
    }

    /**
     * Elimina una persona por su ID.
     */
    public void eliminar(int id) {
        String sql = "DELETE FROM personas WHERE id = ?";
        try (PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setInt(1, id);
            int filas = ps.executeUpdate();
            if (filas > 0) {
                System.out.println("✅ Persona eliminada exitosamente.");
            } else {
                System.out.println("⚠️ No se encontró una persona con ese ID.");
            }
        } catch (SQLException e) {
            System.err.println("❌ Error al eliminar persona: " + e.getMessage());
        }
    }
}
