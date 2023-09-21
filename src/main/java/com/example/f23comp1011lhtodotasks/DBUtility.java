package com.example.f23comp1011lhtodotasks;

import java.sql.*;

public class DBUtility {

    /**
     * These should reflect whatever the user name / password is for
     * your instance of MySQL Server
     */
    private static String user = "student";
    private static String password = "student";

    /**
     * jdbc:mysql - this piece tells Java which driver to use
     * 127.0.0.1:3306 - this is the IP address and port #
     * F23COMP1011LH - this is your database name
     */
    private static String connectUrl = "jdbc:mysql://127.0.0.1:3306/F23COMP1011LH";

    /**
     * This method will save a Person into the users table
     */
    public static String saveUserToDB(Person person) throws SQLException {
        String responseMsg;

        String sql = "INSERT INTO users (email, fullname) VALUES (?,?)";

        //this is called a try...with resource block
        try (
                Connection conn = DriverManager.getConnection(connectUrl, user,password);
                PreparedStatement ps = conn.prepareStatement(sql);
                )
        {
            //configure the preparedStatement
            ps.setString(1, person.getEmail());
            ps.setString(2, person.getFullName());

            ps.executeUpdate();

            responseMsg = "User Added";
        }
        catch (SQLIntegrityConstraintViolationException e)
        {
            return "Account with email "+person.getEmail()+" already exists";
        }
        catch (Exception e)
        {
            e.printStackTrace();
            responseMsg = e.getMessage();
        }
        return responseMsg;
    }
}
