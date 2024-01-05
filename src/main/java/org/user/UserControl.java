package org.user;

import org.common.Status;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserControl {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/electrical_function";
    private static final String DB_USERNAME = "root";
    private static final String DB_PASSWORD = "gqq";

    public List<User> getAllPersons() {
        List<User> users = new ArrayList<>();
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;

        try {
            conn = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
            stmt = conn.createStatement();
            String sql = "SELECT * FROM users";
            rs = stmt.executeQuery(sql);

            while (rs.next()) {
                String account = rs.getString("account");
                String name = rs.getString("name");
                int identity = rs.getInt("identity");
                String password = rs.getString("password");

                User user = new User(account, name, identity, password);
                users.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println(e.getErrorCode());
        } finally {
            closeResources(conn, stmt, rs);
        }

        return users;
    }

    public User getPersonByAccount(String account) {
        User user = null;
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            conn = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
            String sql = "SELECT * FROM users WHERE account = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, account);
            rs = stmt.executeQuery();

            if (rs.next()) {
                String name = rs.getString("name");
                int identity = rs.getInt("identity");
                String password = rs.getString("password");

                user = new User(account, name, identity, password);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeResources(conn, stmt, rs);
        }

        return user;
    }

    public Status addPerson(User user) {
        Connection conn = null;
        PreparedStatement stmt = null;
        Status status = Status.ok(user);
        try {
            conn = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
            String sql = "INSERT INTO users (account, name, identity, password) VALUES (?, ?, ?, ?)";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, user.getAccount());
            stmt.setString(2, user.getName());
            stmt.setString(3, Integer.toString(user.getIdentity()));
            stmt.setString(4, user.getPassword());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            status = Status.error(e.toString());
        } finally {
            closeResources(conn, stmt, null);
        }
        return status;
    }

    public Status updatePerson(String account, User updatedUser) {
        Connection conn = null;
        PreparedStatement stmt = null;
        Status status = Status.ok(updatedUser);
        try {
            conn = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
            String sql = "UPDATE users SET name = ?, identity = ?, password = ? WHERE account = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, updatedUser.getName());
            stmt.setString(2, Integer.toString(updatedUser.getIdentity()));
            stmt.setString(3, updatedUser.getPassword());
            stmt.setString(4, account);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            status = Status.error(e.toString());
        } finally {
            closeResources(conn, stmt, null);
        }
        return status;
    }

    public Status deletePerson(String account) {
        Connection conn = null;
        PreparedStatement stmt = null;
        Status status = new Status();
        try {
            conn = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
            String sql = "DELETE FROM users WHERE account = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, account);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            status = Status.error(e.toString());
        } finally {
            closeResources(conn, stmt, null);
        }
        return status;
    }

    private void closeResources(Connection conn, Statement stmt, ResultSet rs) {
        try {
            if (rs != null) {
                rs.close();
            }
            if (stmt != null) {
                stmt.close();
            }
            if (conn != null) {
                conn.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}