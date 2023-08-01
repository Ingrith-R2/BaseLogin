import java.sql.*;
import java.io.FileWriter;
import java.io.IOException;

public class firstselect {
    static final String DB_URL = "jdbc:mysql://localhost/POO1";
    //cadena de conexion
    static final String user = "root";
    //usuario
    static final String pass= "root_bas3";
    //paswword
    static final String query = "SELECT * FROM ESTUDIANTES WHERE ID=2020201340";


    public static void main(String[] args){
        try(Connection conn = DriverManager.getConnection(DB_URL,user,pass);
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query))
        {
            while (rs.next()){
                System.out.println("id:"+rs.getInt("id"));
                System.out.println("Nombre:"+rs.getString("Nombre"));
                System.out.println("Edad:"+rs.getInt("Edad"));
                System.out.println("Ciudad:"+rs.getString("Ciudad"));
                System.out.println("Cedula:"+rs.getInt("Cedula") );
                System.out.println("Correo:"+rs.getString("Correo"));
                System.out.println("-------------------");
                new GuardarDatosEnArchivo();
            }
        }catch (SQLException e){
            throw new RuntimeException(e);
        };
    }


    public static class GuardarDatosEnArchivo {

        public static void main(String[] args) {
            String contenido = "Estos son los datos que quiero guardar en el archivo.";

            // Ruta del archivo donde se guardarán los datos
            String rutaArchivo = "datos.txt";

            try {
                // Crear un objeto FileWriter con la ruta del archivo
                FileWriter escritor = new FileWriter(rutaArchivo);

                // Escribir el contenido en el archivo
                escritor.write(contenido);

                // Cerrar el FileWriter
                escritor.close();

                System.out.println("Datos guardados correctamente en el archivo.");
            } catch (IOException e) {
                System.out.println("Error al guardar los datos: " + e.getMessage());
            }
        }
    }
}
