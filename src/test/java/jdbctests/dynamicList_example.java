package jdbctests;

import org.testng.annotations.Test;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class dynamicList_example {

    String dbUrl = "jdbc:mysql://127.0.0.1:3306/SampleDb";
    String dbUsername = "root";
    String dbPassword = "123456";

    @Test
    public void dynamicList() throws SQLException {
        Connection connection = DriverManager.getConnection(dbUrl,dbUsername,dbPassword);
        Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
        ResultSet resultSet = statement.executeQuery("SELECT * FROM sales");
//        ResultSet resultSet = statement.executeQuery("SELECT * FROM sales LIMIT 3");

        //get the resultset object metadata
        ResultSetMetaData rsMetaData = resultSet.getMetaData();

        //list for keeping all rows in a map
        List<Map<String, Object>> queryData = new ArrayList<>();

        //number of columns
        int colCount = rsMetaData.getColumnCount();

        //loop through each row
        while (resultSet.next()) {
            Map<String, Object> row = new HashMap<>();

            for (int i = 1; i <= colCount; i++) {
                //row.put(rsMetadata.getColumnLabel(1),resultset.getObject(1));
                row.put(rsMetaData.getColumnName(i), resultSet.getObject(i));
            }
            queryData.add(row);
        }

        //print the result
        for (Map<String, Object> row : queryData) {
            System.out.println(row.toString());
        }

        //close all connections
        resultSet.close();
        statement.close();
        connection.close();
    }

}
