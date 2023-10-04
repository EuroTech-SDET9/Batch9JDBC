package jdbctests;

import org.testng.annotations.Test;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class listOfMap_example {

    String dbUrl = "jdbc:mysql://127.0.0.1:3306/SampleDb";
    String dbUsername = "root";
    String dbPassword = "123456";

    @Test
    public void ListOfMapExample(){

        //list of maps for keeping all rows
        List<Map<String, Object>> queryData = new ArrayList<>();

        Map<String, Object> row1 = new HashMap<>();
        row1.put("year", 2022);
        row1.put("country", "Central");
        row1.put("name", "Kivell");
        row1.put("product", "Pen Set");
        row1.put("units", 80);
        row1.put("unitcost", 25.21);
        row1.put("profit", 2016.80);

        System.out.println(row1.toString());

        Map<String, Object> row2 = new HashMap<>();
        row2.put("year", 2022);
        row2.put("country", "East");
        row2.put("name", "Jane");
        row2.put("product", "NoteBook");
        row2.put("units", 100);
        row2.put("unitcost", 3);
        row2.put("profit", 300);

        System.out.println(row2.toString());

        System.out.println(row2.get("product") + " " + row2.get("profit"));

        queryData.add(row1);
        queryData.add(row2);

        //get the kivell name directly from the list
        System.out.println(queryData.get(0).get("name"));
    }


    @Test
    public void MetadataExample() throws SQLException {
        Connection connection = DriverManager.getConnection(dbUrl,dbUsername,dbPassword);
        Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
        ResultSet resultSet = statement.executeQuery("SELECT * FROM sales");

        ResultSetMetaData rsMetadata = resultSet.getMetaData();

        //list of maps for keeping all rows
        List<Map<String, Object>> queryData = new ArrayList<>();

        //move to first row
        resultSet.next();
        Map<String, Object> row1 = new HashMap<>();
        row1.put(rsMetadata.getColumnLabel(1), resultSet.getString(1));
        row1.put(rsMetadata.getColumnLabel(2), resultSet.getString(2));
        row1.put(rsMetadata.getColumnLabel(3), resultSet.getString(3));
        row1.put(rsMetadata.getColumnLabel(4), resultSet.getString(4));
        row1.put(rsMetadata.getColumnLabel(5), resultSet.getString(5));
        row1.put(rsMetadata.getColumnLabel(6), resultSet.getString(6));
        row1.put(rsMetadata.getColumnLabel(7), resultSet.getString(7));

        System.out.println(row1.toString());

        //move to second row
        resultSet.next();
        Map<String, Object> row2 = new HashMap<>();
        row2.put(rsMetadata.getColumnLabel(1), resultSet.getString(1));
        row2.put(rsMetadata.getColumnLabel(2), resultSet.getString(2));
        row2.put(rsMetadata.getColumnLabel(3), resultSet.getString(3));
        row2.put(rsMetadata.getColumnLabel(4), resultSet.getString(4));
        row2.put(rsMetadata.getColumnLabel(5), resultSet.getString(5));
        row2.put(rsMetadata.getColumnLabel(6), resultSet.getString(6));
        row2.put(rsMetadata.getColumnLabel(7), resultSet.getString(7));

        System.out.println(row2.toString());

        System.out.println("Product on row 2: " + row2.get(rsMetadata.getColumnLabel(2)) + "\nProfit on row 2: " + row2.get(resultSet.getString(7)));

        queryData.add(row1);
        queryData.add(row2);

        //get the kivell name directly from the list
        System.out.println(queryData.get(1).get(rsMetadata.getColumnLabel(3)));

        //close all connections
        resultSet.close();
        statement.close();
        connection.close();
    }

}
