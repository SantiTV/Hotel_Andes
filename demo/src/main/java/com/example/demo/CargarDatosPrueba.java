import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CargarDatosPrueba {

    public static void main(String[] args) {
        String url = "jdbc:oracle:thin:@localhost:1521:nombre_de_servicio"; 
        String usuario = "usuario";
        String contraseña = "contraseña";

        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            // Establecer la conexión a la base de datos
            connection = DriverManager.getConnection(url, usuario, contraseña);

            // Realizar inserciones de datos de prueba en la tabla clientes
            String insertQuery = "INSERT INTO clientes (id_clientes, nombre, reservas, consumos, tipodeplan) VALUES (?, ?, ?, ?, ?)";
            preparedStatement = connection.prepareStatement(insertQuery);

            // Generar datos de prueba y realizar inserciones
            for (int i = 1; i <= 10; i++) {
                preparedStatement.setInt(1, i);
                preparedStatement.setString(2, "Cliente " + i);
                preparedStatement.setInt(3, i * 2); // Número de reservas ficticio
                preparedStatement.setDouble(4, i * 100.0); // Consumos ficticios
                preparedStatement.setString(5, "Plan " + i);

                preparedStatement.executeUpdate();
            }

            System.out.println("Datos de prueba insertados en la tabla clientes.");

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Cerrar recursos
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}