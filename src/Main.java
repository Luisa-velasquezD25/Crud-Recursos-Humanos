import dao.FuncionarioDAO;
import java.util.List;
import modelo.Funcionario;

public class Main {
    public static void main(String[] args) {
        Funcionario funcionario = new Funcionario(
            0, 
            "CC",
            "123456789",
            "Juan",
            "Pérez",
            "Soltero",
            "Masculino",
            "Calle 123",
            "3001234567",
            "03-02-1990"

        );

        FuncionarioDAO dao = new FuncionarioDAO();
        int idInsertado = dao.insertarFuncionario(funcionario);

        if (idInsertado > 0) {
            System.out.println("Funcionario insertado correctamente con ID: " + idInsertado);
        } else {
            System.out.println("No se pudo insertar el funcionario.");
        }

        System.out.println("\nListado de funcionarios:");
        List<Funcionario> funcionarios = dao.listarFuncionarios();
        for (Funcionario f : funcionarios) {
            System.out.println(f.getIdFuncionario() + " - " + f.getNombres() + " " + f.getApellidos());
        }

        Funcionario actualizado = new Funcionario(
        idInsertado, 
        "TI",
        "987654321",
        "Juan Carlos",
        "Pérez López",
        "Casado",
        "Masculino",
        "Avenida Siempre Viva",
        "3119876543",
        "02-03-1999"

        );

        boolean actualizadoOK = dao.actualizarFuncionario(actualizado);
        System.out.println("¿Se actualizó? " + actualizadoOK);

        System.out.println("\nEliminando funcionario con ID " + idInsertado + "...");
        boolean eliminado = dao.eliminarFuncionario(idInsertado);
        System.out.println("¿Se eliminó? " + eliminado);


        // Listar nuevamente
        System.out.println("\nListado tras eliminar:");
        for (Funcionario f : dao.listarFuncionarios()) {
            System.out.println(f.getIdFuncionario() + " - " + f.getNombres() + " " + f.getApellidos());
        }


    }
}

