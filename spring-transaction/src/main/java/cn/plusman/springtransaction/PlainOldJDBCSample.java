package cn.plusman.springtransaction;

import java.io.IOException;
import java.sql.*;

/**
 * 原始 JDBC 执行逻辑
 * @author plusman
 * @since 2021/4/9 10:43 PM
 */
public class PlainOldJDBCSample {
    public static void main(String[] args) throws ClassNotFoundException, SQLException, IOException {
        Class.forName("org.h2.Driver");
        
        Connection connection = DriverManager.getConnection("jdbc:h2:file:/tmp/plain_old_jdbc_sample", "sa", null); // (1)
        
        try {
            connection.setAutoCommit(false); // (2)
            // execute some SQL statements...
            try (Statement statement = connection.createStatement()) {
                statement.executeUpdate("drop table BOOKINGS if exists;\n" +
                    "create table BOOKINGS(ID serial, FIRST_NAME varchar(5) NOT NULL);");
                statement.executeUpdate("insert into bookings (FIRST_NAME) values('Gan');");
                statement.executeUpdate("insert into bookings (FIRST_NAME) values('Liu');");
                
                try (ResultSet resultSet = statement.executeQuery("SELECT * FROM BOOKINGS")) {
                    printResult(resultSet);
                }
            }
            
            connection.commit(); // (3)
        } catch (SQLException e) {
            e.printStackTrace();
            connection.rollback(); // (4)
        } finally {
            connection.close();
        }
    }
    
    public static void printResult(ResultSet rs) throws SQLException {
        while(rs.next()){
            //Retrieve by column name
            Integer id = rs.getInt("id");
            String content = rs.getString("first_name");
            
            //Display values
            System.out.print("ID: " + id);
            System.out.println(" | Content: " + content);
        }
    }
}
