import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class estudiantes {
    private JButton QUERY;
    private JPanel datitos;
    private JTextField id;
    private JTextField name;
    private JTextField edad;
    private JTextField ciudad;
    private JTextField cedula;
    private JTextField correo;
    private JButton eliminar;
    private JButton insertar;
    private JTextField idE;
    private JButton guardar;


    public estudiantes() {
    QUERY.addActionListener(new ActionListener() {
        static final String DB_URL = "jdbc:mysql://localhost/POO1";
        //cadena de conexion
        static final String user = "root";
        //usuario
        static final String pass= "root_bas3";
        //paswword
        static final String query = "SELECT * FROM ESTUDIANTES ";
        @Override
        public void actionPerformed(ActionEvent e) {
            String Id = id.getText();
            String Nombre = name.getText();
            String Edad = edad.getText();
            String Ciudad = ciudad.getText();
            String Cedula = cedula.getText();
            String Correo = correo.getText();
                try(Connection conn = DriverManager.getConnection(DB_URL,user, pass)) {
                    Statement stmt = conn.createStatement();
                    ResultSet rs = stmt.executeQuery(query);
                    while (rs.next()) {
                        System.out.println("id:" + rs.getInt("id"));
                        id.setText(rs.getString("id"));
                        System.out.println("Nombre:" + rs.getString("Nombre"));
                        name.setText(rs.getString("Nombre"));
                        System.out.println("Edad:" + rs.getInt("Edad"));
                        edad.setText(rs.getString("Edad"));
                        System.out.println("Ciudad:" + rs.getString("Ciudad"));
                        ciudad.setText(rs.getString("Ciudad"));
                        System.out.println("Cedula:" + rs.getInt("Cedula"));
                        cedula.setText(rs.getString("Cedula"));
                        System.out.println("Correo:" + rs.getString("Correo"));
                        correo.setText(rs.getString("Correo"));
                        System.out.println("-------------------");
                        new firstselect.GuardarDatosEnArchivo();
                    }
                }
                catch (SQLException e1){
                    throw new RuntimeException(e1);

                };

        }
    });
        insertar.addActionListener(new ActionListener() {
            static final String DB_URL = "jdbc:mysql://localhost/POO1";
            //cadena de conexion
            static final String user = "root";
            //usuario
            static final String pass= "root_bas3";
            //paswword
            static final String query = "SELECT * FROM ESTUDIANTES ";

            @Override
            public void actionPerformed(ActionEvent e) {
                String Id = id.getText();
                String Nombre = name.getText();
                String Edad = edad.getText();
                String Ciudad = ciudad.getText();
                String Cedula = cedula.getText();
                String Correo = correo.getText();
                try(Connection conn = DriverManager.getConnection(DB_URL,user, pass)){
                    String sql = "INSERT INTO ESTUDIANTES (ID, NOMBRE, EDAD, CIUDAD, CEDULA, CORREO) VALUES (?, ?, ?, ?, ?, ?)";
                    PreparedStatement pstmt = conn.prepareStatement(sql);
                    pstmt.setString(1, Id); // Obtener valor desde JTextField
                    pstmt.setString(2, Nombre); // Obtener valor desde JTextField
                    pstmt.setString(3, Edad); // Obtener valor desde JTextField
                    pstmt.setString(4, Ciudad); // Obtener valor desde JTextField
                    pstmt.setString(5, Cedula); // Obtener valor desde JTextField
                    pstmt.setString(6, Correo); // Obtener valor desde JTextField

                    int filasAfectadas = pstmt.executeUpdate();
                    System.out.println("Se han insertado " + filasAfectadas + " filas.");
                    pstmt.close();


                     } catch (SQLException e1){
                    e1.printStackTrace();

                };
            }
        });
        eliminar.addActionListener(new ActionListener() {
            static final String DB_URL = "jdbc:mysql://localhost/POO1";
            //cadena de conexion
            static final String user = "root";
            //usuario
            static final String pass= "root_bas3";
            //paswword
            static final String query = "SELECT * FROM ESTUDIANTES ";

            @Override
            public void actionPerformed(ActionEvent e) {
                String Id = idE.getText();
                String Nombre = name.getText();
                String Edad = edad.getText();
                String Ciudad = ciudad.getText();
                String Cedula = cedula.getText();
                String Correo = correo.getText();
                try(Connection conn = DriverManager.getConnection(DB_URL,user, pass)) {

                    String sql = "DELETE FROM ESTUDIANTES WHERE ID = " + Id;
                    PreparedStatement pstmt = conn.prepareStatement(sql);
                    String ID = idE.getText();

                    int filasAfectadas = pstmt.executeUpdate();
                    System.out.println("Se ha ELIMINADO " + filasAfectadas + " filas.");
                    pstmt.close();

                }catch (SQLException e1){
                    e1.printStackTrace();
                };
        }
        });
        guardar.addActionListener(new ActionListener() {
            static final String DB_URL = "jdbc:mysql://localhost/POO1";
            //cadena de conexion
            static final String user = "root";
            //usuario
            static final String pass= "root_bas3";
            //paswword
            @Override
            public void actionPerformed(ActionEvent e) {
                String IdN = idE.getText();
                String NombreN = name.getText();
                String EdadN = edad.getText();
                String CiudadN = ciudad.getText();
                String CedulaN = cedula.getText();
                String CorreoN = correo.getText();
                try(Connection conn = DriverManager.getConnection(DB_URL,user, pass)) {
                    String sql = "UPDATE ESTUDIANTES  SET NOMBRE = ?, EDAD = ?, CIUDAD = ?, CEDULA = ?, CORREO = ? WHERE  ID = ?";
                    PreparedStatement pstmt = conn.prepareStatement(sql);
                    pstmt.setString(1, NombreN); // Obtener valor desde JTextField
                    pstmt.setString(2, EdadN); // Obtener valor desde JTextField
                    pstmt.setString(3, CiudadN); // Obtener valor desde JTextField
                    pstmt.setString(4, CedulaN ); // Obtener valor desde JTextField
                    pstmt.setString(5, CorreoN); // Obtener valor desde JTextField
                    pstmt.setString(6, IdN); // Obtener valor desde JTextField

                    int filasAfectadas = pstmt.executeUpdate();
                    System.out.println("Se ha GUARDADO" + filasAfectadas + " filas.");
                    pstmt.close();

                }catch (SQLException e1){
                    e1.printStackTrace();
                };
            }
        });
    }
    public static void main(String[] args){
        JFrame frame = new JFrame("Datos");
        frame.setContentPane(new estudiantes().datitos);
        frame.setBounds(750,300,1000,1050);
        //   frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }


}
