package org.example;

import java.sql.*;

public class Main {
    public static void main(String[] args) throws Exception{

        Class.forName("com.mysql.cj.jdbc.Driver");

        Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/GOT", "root", "#VasquezM19");

        //PreparedStatemet
        crearRegistros(conexion);

        //CallableStatement
        eliminarRegistros(conexion, 7);

        //Statement
        listarRegistros(conexion);
    }

    public static void listarRegistros(Connection connection) throws Exception{

        Statement st = connection.createStatement();
        ResultSet rs = st.executeQuery("SELECT * FROM Characters");

        while(rs.next()) {
            System.out.println(rs.getString("id")+ " " +
                    rs.getString("name")+ " " +
                    rs.getString("lastname")+ " " +
                    rs.getString("age")+ " " +
                    rs.getString("house")+ " " +
                    rs.getString("gender"));
        }
    }

    public static void crearRegistros(Connection connection) throws Exception{

        PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO Characters VALUES(?,?,?,?,?,?)");
        preparedStatement.setInt(1, 7);
        preparedStatement.setString(2, "Tywin");
        preparedStatement.setString(3, "Lannister");
        preparedStatement.setString(4, "67");
        preparedStatement.setString(5, "House Lannister");
        preparedStatement.setString(6, "M");

        int filasAfectadas = preparedStatement.executeUpdate();
        System.out.println("Filas afectadas: " + filasAfectadas);
    }

    public static void eliminarRegistros(Connection connection,Integer id) throws Exception{

        CallableStatement cs = connection.prepareCall("{call deleteCharactersById(?)}");
        cs.setInt(1, id); //id
        cs.executeQuery();
        System.out.println("Datos eliminados" );
    }
}