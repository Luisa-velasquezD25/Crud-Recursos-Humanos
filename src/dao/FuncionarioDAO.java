package dao;

import conexion.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import modelo.Funcionario;

public class FuncionarioDAO {

//metodo insertar

public int insertarFuncionario(Funcionario funcionario) {
    Connection conn = null;
    PreparedStatement stmt = null;
    ResultSet generatedKeys = null;
    int idGenerado = -1;

    try {
        conn = Conexion.obtenerConexion();
        String sql = "INSERT INTO funcionario (tipo_identificacion, numero_identificacion, nombres, apellidos, estado_civil, sexo, direccion, telefono, fecha_nacimiento) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        stmt = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);

        stmt.setString(1, funcionario.getTipoIdentificacion());
        stmt.setString(2, funcionario.getNumeroIdentificacion());
        stmt.setString(3, funcionario.getNombres());
        stmt.setString(4, funcionario.getApellidos());
        stmt.setString(5, funcionario.getEstadoCivil());
        stmt.setString(6, funcionario.getSexo());
        stmt.setString(7, funcionario.getDireccion());
        stmt.setString(8, funcionario.getTelefono());
        stmt.setString(9, funcionario.getFechaNacimiento());

        int filas = stmt.executeUpdate();
        if (filas > 0) {
            generatedKeys = stmt.getGeneratedKeys();
            if (generatedKeys.next()) {
                idGenerado = generatedKeys.getInt(1);
                System.out.println("Funcionario insertado con ID: " + idGenerado);
            }
        }

    } catch (SQLException e) {
        System.out.println("Error al insertar funcionario: " + e.getMessage());
    } finally {
        try {
            if (generatedKeys != null) generatedKeys.close();
            if (stmt != null) stmt.close();
            if (conn != null) conn.close();
        } catch (SQLException ignored) {}
    }

    return idGenerado;
}

    //metodo listar

    public List<Funcionario> listarFuncionarios() {
        List<Funcionario> lista = new ArrayList<>();
        String sql = "SELECT * FROM funcionario";

        try (Connection conn = Conexion.obtenerConexion();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Funcionario f = new Funcionario();
                f.setIdFuncionario(rs.getInt("id_funcionario"));
                f.setTipoIdentificacion(rs.getString("tipo_identificacion"));
                f.setNumeroIdentificacion(rs.getString("numero_identificacion"));
                f.setNombres(rs.getString("nombres"));
                f.setApellidos(rs.getString("apellidos"));
                f.setEstadoCivil(rs.getString("estado_civil"));
                f.setSexo(rs.getString("sexo"));
                f.setDireccion(rs.getString("direccion"));
                f.setTelefono(rs.getString("telefono"));
                f.setFechaNacimiento(rs.getString("fecha_nacimiento"));    
                lista.add(f);
            }

        } catch (SQLException e) {
            System.out.println("Error al listar funcionarios: " + e.getMessage());
        }

        return lista;
    }

    //metodo actualizar

    public boolean actualizarFuncionario(Funcionario funcionario) {
    boolean actualizado = false;
    String sql = "UPDATE funcionario SET tipo_identificacion = ?, numero_identificacion = ?, nombres = ?, apellidos = ?, estado_civil = ?, sexo = ?, direccion = ?, telefono = ?, fecha_nacimiento = ? WHERE id_funcionario = ?";

        try (Connection conn = Conexion.obtenerConexion();
         PreparedStatement stmt = conn.prepareStatement(sql)) {

        stmt.setString(1, funcionario.getTipoIdentificacion());
        stmt.setString(2, funcionario.getNumeroIdentificacion());
        stmt.setString(3, funcionario.getNombres());
        stmt.setString(4, funcionario.getApellidos());
        stmt.setString(5, funcionario.getEstadoCivil());
        stmt.setString(6, funcionario.getSexo());
        stmt.setString(7, funcionario.getDireccion());
        stmt.setString(8, funcionario.getTelefono());
        stmt.setString(9, funcionario.getFechaNacimiento());
        stmt.setInt(10, funcionario.getIdFuncionario());

        int filas = stmt.executeUpdate();
        actualizado = filas > 0;

        if (actualizado) {
            System.out.println("Funcionario actualizado correctamente.");
        } else {
            System.out.println("No se encontró el funcionario para actualizar.");
        }

    } catch (SQLException e) {
        System.out.println("Error al actualizar funcionario: " + e.getMessage());
    }

    return actualizado;
    }

    //metodo eliminar

    public boolean eliminarFuncionario(int idFuncionario) {
    boolean eliminado = false;
    String sql = "DELETE FROM funcionario WHERE id_funcionario = ?";

    try (Connection conn = Conexion.obtenerConexion();
         PreparedStatement stmt = conn.prepareStatement(sql)) {

        stmt.setInt(1, idFuncionario);
        int filas = stmt.executeUpdate();
        eliminado = filas > 0;

        if (eliminado) {
            System.out.println("Funcionario eliminado correctamente.");
        } else {
            System.out.println("No se encontró el funcionario para eliminar.");
        }

    } catch (SQLException e) {
        System.out.println("Error al eliminar funcionario: " + e.getMessage());
    }

    return eliminado;
}

}