package cn.plusman.springtransaction;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.sql.*;

/**
 * 原始 JDBC 执行逻辑
 * @author plusman
 * @since 2021/4/9 10:43 PM
 */
@Slf4j
public class PlainOldJDBCSample {
    public static void main(String[] args) throws ClassNotFoundException, SQLException, IOException {
        Class.forName("org.h2.Driver");
        
        Connection connection = DriverManager.getConnection("jdbc:h2:file:/tmp/plain_old_jdbc_sample", "sa", null); // (1)
        
        try {
            // 设置隔离级别
            connection.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
            
            connection.setAutoCommit(false); // (2)
            // execute some SQL statements...
            // insertTest(connection);
            savepointTest(connection);
            
            connection.commit(); // (3)
        } catch (SQLException e) {
            e.printStackTrace();
            connection.rollback(); // (4)
        } finally {
            connection.close();
        }
    }
    
    /**
     * 注意此处方法一定要将异常往外抛出，不然无法触发错误回滚
     * @param connection
     */
    public static void insertTest(Connection connection) throws SQLException {
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate("drop table BOOKINGS if exists;\n" +
                "create table BOOKINGS(ID serial, FIRST_NAME varchar(5) NOT NULL);");
            statement.executeUpdate("insert into bookings (FIRST_NAME) values('Gan');");
            statement.executeUpdate("insert into bookings (FIRST_NAME) values('Liu');");
        
            try (ResultSet resultSet = statement.executeQuery("SELECT * FROM BOOKINGS")) {
                printResult(resultSet);
            }
        }
    }
    
    /**
     * 保存点测试
     * @param connection
     */
    public static void savepointTest(Connection connection) throws SQLException {
        Savepoint savepoint = null;
        
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate("drop table BOOKINGS if exists;\n" +
                "create table BOOKINGS(ID serial, FIRST_NAME varchar(5) NOT NULL);");
            statement.executeUpdate("insert into bookings (FIRST_NAME) values('Gan');");
            // 创建 savePoint
            savepoint = connection.setSavepoint();
            
            statement.executeUpdate("insert into bookings (FIRST_NAME) values('Liu');");
            statement.executeUpdate("insert into bookings (FIRST_NAME) values('LiuHanSan');");
         
            
            try (ResultSet resultSet = statement.executeQuery("SELECT * FROM BOOKINGS")) {
                printResult(resultSet);
            }
        } catch (SQLException throwables) {
            log.error("savepointTest 发生异常错误：", throwables);
            
            log.info("打印结果包含 Gan、Liu");
            try (Statement statement = connection.createStatement()) {
                try (ResultSet resultSet = statement.executeQuery("SELECT * FROM BOOKINGS")) {
                    printResult(resultSet);
                }
            }
    
            log.info("即将回滚到保存点");
            connection.rollback(savepoint);
    
            log.info("打印结果只包含 Gan");
            try (Statement statement = connection.createStatement()) {
                try (ResultSet resultSet = statement.executeQuery("SELECT * FROM BOOKINGS")) {
                    printResult(resultSet);
                }
            }
        }
    }
    
    /**
     * 打印
     * @param rs
     * @throws SQLException
     */
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
