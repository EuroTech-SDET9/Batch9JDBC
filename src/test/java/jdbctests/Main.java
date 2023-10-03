package jdbctests;

import java.sql.*;

public class Main {

    public static void main(String[] args) throws SQLException {

        String dbUrl = "jdbc:mysql://127.0.0.1/SampleDb";
//        String dbUrl = "jdbc:mysql://localhost/SampleDb";
        String dbUsername = "root";
        String dbPassword = "123456";

        System.out.println("--- Creating the connection ---");
        // create connection
        Connection connection = DriverManager.getConnection(dbUrl, dbUsername, dbPassword);

        // create a statement object
        Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

        // run query and get result in ResultSet object
        ResultSet resultSet = statement.executeQuery("SELECT * FROM customers;");

        resultSet.next();

        // getting info with the column name and index of column
        System.out.println("customerNumber: " + resultSet.getInt("customerNumber") + ", customerName: " + resultSet.getString(2));

        resultSet.next();
        System.out.println("contactFirstName: " + resultSet.getString("contactFirstName") + ", contactLastName: " + resultSet.getString("contactLastName"));

        System.out.println("--- Closing the connection ---");
        resultSet.close();
        statement.close();
        connection.close();

        // following will throw exception because we have already closed th connection
//        resultSet.next();
//        System.out.println("contactFirstName: " + resultSet.getString("contactFirstName") + ", contactLastName: " + resultSet.getString("contactLastName"));
    }

}
