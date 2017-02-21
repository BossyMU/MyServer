package io.muic.ooc.webapp.service;

import java.sql.*;

/**
 * Created by bossy on 16/2/2560.
 */
public class MySQLService {

    enum TestTableColumns {
        username,password;
    }
    private final String jdbcDriverStr = "com.mysql.jdbc.Driver";
    private final String jdbcURL = "jdbc:mysql://localhost/nanidb?"
            + "user=root&password=TorpongJuntree";

    private Connection connection;
    private Statement statement;
    private ResultSet resultSet;
    private PreparedStatement preparedStatement;

    public void readData() throws Exception {
        try {
            Class.forName(jdbcDriverStr);
            connection = DriverManager.getConnection(jdbcURL);
            statement = connection.createStatement();
            resultSet = statement.executeQuery("select * from userLogIn;");
            System.out.println("text: " + resultSet);
//            getResultSet(resultSet);
//            preparedStatement = connection.prepareStatement("insert into userLogIn values (default,?)");
//            preparedStatement.setString(1, "insert test from java");
//            preparedStatement.executeUpdate();
        } finally {
            close();
        }
    }

    public boolean checkMatch(String username, String password) throws Exception{
        try {
            Class.forName(jdbcDriverStr);
            connection = DriverManager.getConnection(jdbcURL);
            statement = connection.createStatement();
//            resultSet = statement.executeQuery("select password from userLogIn where username="+"'"+username+"'"+";");
            resultSet = statement.executeQuery("select password from userLogIn where username='"+username+"'");
//            System.out.println(resultSet);
//            System.out.println(resultSet);
            return getResultSet(resultSet,password);

        }finally {
            close();
        }
    }

    public void addDataBase(String username, String password) throws Exception{
        try {
            Class.forName(jdbcDriverStr);
            connection = DriverManager.getConnection(jdbcURL);
            preparedStatement = connection.prepareStatement("insert into userLogIn (username,password) values (?,?)");
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);
            preparedStatement.executeUpdate();
        }finally {
            close();
        }
    }

    public void editDataBase(String username, String password) throws Exception{
        try {
            Class.forName(jdbcDriverStr);
            connection = DriverManager.getConnection(jdbcURL);
            preparedStatement = connection.prepareStatement("update userLogIn set password=? where username=?");
            System.out.println("in database "+ username + password);
            preparedStatement.setString(1, password);
            preparedStatement.setString(2, username);

            preparedStatement.executeUpdate();
        }finally {
            close();
        }
    }

    public void deleteDataBase(String username) throws Exception{
        try {
            Class.forName(jdbcDriverStr);
            connection = DriverManager.getConnection(jdbcURL);
            preparedStatement = connection.prepareStatement("DELETE from userLogIn where username = ?");
            preparedStatement.setString(1, username);
            preparedStatement.executeUpdate();
        }finally {
            close();
        }
    }

    public boolean findOne(String username) throws Exception{
        try {
            Class.forName(jdbcDriverStr);
            connection = DriverManager.getConnection(jdbcURL);
            statement = connection.createStatement();
//            resultSet = statement.executeQuery("select password from userLogIn where username="+"'"+username+"'"+";");
            resultSet = statement.executeQuery("select username from userLogIn where username='"+username+"'");
//            System.out.println(resultSet);
//            System.out.println(resultSet);
            String getusername = "";
            while (resultSet.next()) {
//            String username = resultSet.getString(TestTableColumns.username.toString());
                getusername = resultSet.getString(TestTableColumns.username.toString());
                System.out.println(getusername);
            }
            return username.equals(getusername);

        }finally {
            close();
        }
    }

    private boolean getResultSet(ResultSet resultSet, String password) throws Exception {
        String passwords = "";
        while (resultSet.next()) {
//            String username = resultSet.getString(TestTableColumns.username.toString());
              passwords = resultSet.getString(TestTableColumns.password.toString());
            System.out.println(passwords);
        }
        System.out.println("password: " + passwords);
        return password.equals(passwords);
    }

    private void close() {
        try {
            if (resultSet != null) {
                resultSet.close();
            }
            if (statement != null) {
                statement.close();
            }
            if (connection != null) {
                connection.close();
            }
        } catch (Exception e) {
        }
    }
}
