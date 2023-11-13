package com.example.demo;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class CargarDatosPrueba {

    public static void main(String[] args) {
        String url = "jdbc:oracle:thin:@fn4.oracle.virtual.uniandes.edu.co:1521/PROD"; // Replace with your own details
        String usuario = "";
        String contraseña = "";

        try (Connection connection = DriverManager.getConnection(url, usuario, contraseña)) {
            connection.setAutoCommit(false);

            // Specify the path to your SQL file
            String filePath = "src\\main\\resources\\Data\\Clientes.sql";

            // Execute SQL statements from the file
            ejecutarSQLDesdeArchivo(connection, filePath);

            connection.commit();
        } catch (SQLException | IOException e) {
            e.printStackTrace();
            System.out.println("Rolling back the transaction.");
        }
    }

    // Method to execute SQL statements from a file
    private static void ejecutarSQLDesdeArchivo(Connection connection, String filePath) throws SQLException, IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath));
             Statement statement = connection.createStatement()) {

            StringBuilder sqlStatement = new StringBuilder();
            String line;

            while ((line = reader.readLine()) != null) {
                // Skip comments and empty lines
                if (!line.trim().startsWith("--") && !line.trim().isEmpty()) {
                    sqlStatement.append(line.trim());

                    // If the line ends with a semicolon, it's the end of the SQL statement
                    if (line.trim().endsWith(";")) {
                        String sql = sqlStatement.toString();
                        try {
                            // Execute the SQL statement
                            statement.executeUpdate(sql);
                            System.out.println("Executed SQL statement: " + sql);
                        } catch (SQLException ex) {
                            System.err.println("Error executing SQL statement: " + sql);
                            ex.printStackTrace();
                        }

                        // Clear the StringBuilder for the next statement
                        sqlStatement.setLength(0);
                    }
                }
            }
        }
    }
}
