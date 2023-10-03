package jdbctests;

import org.testng.annotations.Test;

import java.sql.*;

public class jdbc_example {

    String dbUrl = "jdbc:mysql://127.0.0.1/SampleDb";
    String dbUsername = "root";
    String dbPassword = "123456";

    @Test
    public void test1() throws SQLException {
        System.out.println("--- Creating the connection ---");
        Connection connection = DriverManager.getConnection(dbUrl, dbUsername, dbPassword);
        Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        ResultSet resultSet = statement.executeQuery("SELECT * FROM customers;");

        resultSet.last();

       int rowCount = resultSet.getRow();

        System.out.println("Row count: " + rowCount);

        resultSet.first();


        System.out.println("****************");
        do {
            System.out.println(resultSet.getString("customerName"));
        } while(resultSet.next());
        System.out.println("****************");

        System.out.println("--- Closing the connection ---");
        resultSet.close();
        statement.close();
        connection.close();

    }

    @Test
    public void MetaDataExample() throws SQLException {
        System.out.println("--- Creating the connection ---");
        Connection connection = DriverManager.getConnection(dbUrl, dbUsername, dbPassword);
        Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        ResultSet resultSet = statement.executeQuery("SELECT * FROM customers;");

        // get the database related data inside the DatabaseMetaData object
        DatabaseMetaData dbMetaData = connection.getMetaData();

        System.out.println("User: " + dbMetaData.getUserName());
        System.out.println("Database URL: " + dbMetaData.getURL());
        System.out.println("Database Product Name: " + dbMetaData.getDatabaseProductName());
        System.out.println("Database Product Version: " + dbMetaData.getDatabaseProductVersion());
        System.out.println("Database Driver Name: " + dbMetaData.getDriverName());
        System.out.println("Database Driver Version: " + dbMetaData.getDriverVersion());

        // get the resultset metadata
        ResultSetMetaData resultSetMetaData = resultSet.getMetaData();

        // how many columns we have ?
        int colCount = resultSetMetaData.getColumnCount();
        System.out.println("Column count: " + colCount);

        // column names
        System.out.println(resultSetMetaData.getColumnName(1));
        System.out.println(resultSetMetaData.getColumnName(2));

        System.out.println("*******************");
        for(int i = 1; i <= colCount; i++){
            System.out.println("Column " + i + ": " + resultSetMetaData.getColumnName(i));
        }

        System.out.println("--- Closing the connection ---");
        resultSet.close();
        statement.close();
        connection.close();
    }

    // create a connection
    // get the number of records from customers table using COUNT keyword
    // print out
    // close the connection
    // try-catch-finally block
    @Test
    public void task() {
    }
}
