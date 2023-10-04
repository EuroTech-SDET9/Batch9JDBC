package jdbctests;

import org.testng.Assert;
import org.testng.annotations.Test;

import java.sql.*;

public class CRUD_example {

    String dbUrl = "jdbc:mysql://127.0.0.1/SampleDb";
    String dbUsername = "root";
    String dbPassword = "123456";

    @Test
    public void createRecord() throws SQLException {
        System.out.println("--- Creating the connection ---");
        Connection connection = DriverManager.getConnection(dbUrl, dbUsername, dbPassword);
        Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);

        String queryOne = "INSERT INTO customers(customerNumber,customerName,contactLastName,contactFirstName,phone,addressLine1,addressLine2,city_id,town_id,postalCode,country_id,salesRepEmployeeNumber,creditLimit)"
                + "Values(555,'Eurotech','Tech','Euro ','40.32.2555','54, London','United Kingdom',1,1,44000,7,1370,21000.00);";

        int rowsAffected = statement.executeUpdate(queryOne);
        System.out.println("Number of rows affected: " + rowsAffected);

        System.out.println("--- Closing the connection ---");
        statement.close();
        connection.close();
    }

    @Test
    public void readRecord() throws SQLException {
        System.out.println("--- Creating the connection ---");
        Connection connection = DriverManager.getConnection(dbUrl, dbUsername, dbPassword);
        Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

        String queryOne = "select * from customers where customerNumber=555;";

        ResultSet resultSet = statement.executeQuery(queryOne);

        resultSet.next();
        System.out.println("CustomerNumber: " + resultSet.getString("customerNumber"));
        System.out.println("CustomerName: " + resultSet.getString("customerName"));

        System.out.println("--- Closing the connection ---");
        resultSet.close();
        statement.close();
        connection.close();
    }

    @Test
    public void updateRecord() throws SQLException {
        System.out.println("--- Creating the connection ---");
        Connection connection = DriverManager.getConnection(dbUrl, dbUsername, dbPassword);
        Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);

        String expectedCustomerName = "Eurotech";
        String queryOne = "UPDATE customers SET customerName='" + expectedCustomerName + "' WHERE customerNumber=555;";

        int rowsAffected = statement.executeUpdate(queryOne);
        System.out.println("Number of rows affected: " + rowsAffected);

        String queryTwo = "select * from customers where customerNumber=555;";
        ResultSet resultSet = statement.executeQuery(queryTwo);
        resultSet.next();
        System.out.println("Updated value of customerName column is: " + resultSet.getString("customerName"));
        Assert.assertEquals(resultSet.getString("customerName"), expectedCustomerName, "Failure in verifying updated customerName value");

        // it is possible to reassign the resultSet object by running other queries like below
//        in case you would like to keep all resultSets coming from multiple queries, then you my also create separate resultSet objects
//        String queryThree = "select * from customers;";
//        resultSet = statement.executeQuery(queryThree);
//        resultSet.next();
//        System.out.println("Value of customerName column on first row is: " + resultSet.getString("customerName"));

        System.out.println("--- Closing the connection ---");
        resultSet.close();
        statement.close();
        connection.close();
    }

    @Test
    public void deleteRecord() throws SQLException {
        System.out.println("--- Creating the connection ---");
        Connection connection = DriverManager.getConnection(dbUrl, dbUsername, dbPassword);
        Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);

        String queryOne = "DELETE FROM customers WHERE customerNumber=555;";

        int rowsAffected = statement.executeUpdate(queryOne);
        System.out.println("Number of rows affected: " + rowsAffected);

        System.out.println("--- Closing the connection ---");
        statement.close();
        connection.close();
    }


}
