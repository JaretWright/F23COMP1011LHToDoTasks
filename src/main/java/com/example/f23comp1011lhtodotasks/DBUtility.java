package com.example.f23comp1011lhtodotasks;

import java.sql.*;
import java.util.ArrayList;
import java.util.TreeSet;

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

    /**
     * This method will return an ArrayList of all valid users in the database
     * @return
     */
    public static ArrayList<Person> getUsers()
    {
        ArrayList<Person> users = new ArrayList<>();

        String sql = "SELECT * FROM users";
        try(
                Connection conn = DriverManager.getConnection(connectUrl, user,password);
                Statement statement = conn.createStatement();
                ResultSet resultSet = statement.executeQuery(sql);
                )
        {
            //loop over all the users in the result set and add Person objects to the ArrayList
            while (resultSet.next())
            {
                String fullName = resultSet.getString("fullName");
                String email = resultSet.getString("email");
                try{
                    Person person = new Person(fullName, email);
                    users.add(person);
                }catch (IllegalArgumentException e)
                {
                    System.out.printf("email: '%s' fullName: '%s' not valid ",email, fullName);
                }
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return users;
    }

    /**
     * This method returns all the valid categories from the Database
     * @return
     */
    public static ArrayList<String> getCategories()
    {
        ArrayList<String> categories = new ArrayList<>();

        String sql = "SELECT * FROM categories ORDER BY category";
        try(
                Connection conn = DriverManager.getConnection(connectUrl, user,password);
                Statement statement = conn.createStatement();
                ResultSet resultSet = statement.executeQuery(sql);
        )
        {
            //loop over all the users in the result set and add Person objects to the ArrayList
            while (resultSet.next())
            {
                categories.add(resultSet.getString("category"));
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return categories;
    }

    /**
     * This method will save a Task object to the tasks table
     */
    public static int saveTaskToDB(Task task) throws SQLException {
        int taskID = -1;

        ResultSet resultSet = null;

        String sql = "INSERT INTO tasks (title, description, category, creationDate, dueDate, priority, status, email)" +
                "VALUES (?,?,?,?,?,?,?,?)";

        //use the try...with resources block to catch exceptions and auto-close items inside of ()
        try (
                Connection conn = DriverManager.getConnection(connectUrl, user, password);
                PreparedStatement ps = conn.prepareStatement(sql, new String[]{"taskID"})
        ) {
            ps.setString(1, task.getTitle());
            ps.setString(2,task.getDescription());
            ps.setString(3,task.getCategory());
            ps.setDate(4,Date.valueOf(task.getCreationDate()));
            ps.setDate(5,Date.valueOf(task.getDueDate()));
            ps.setInt(6,task.getPriority());
            ps.setString(7, task.getStatus().toString());
            ps.setString(8,task.getAssignedTo().getEmail());

            ps.executeUpdate();

            //loop over the resultSet to get the TaskID
            resultSet = ps.getGeneratedKeys();

            while (resultSet.next())
                taskID = resultSet.getInt(1);
        } catch (Exception e)
        {
            e.printStackTrace();
        }
        finally {
            if (resultSet != null)
                resultSet.close();
        }

        return taskID;
    }


}
