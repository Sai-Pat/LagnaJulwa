package com.dao;

import com.model.User;
import com.util.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDAOImpl implements UserDAO {

    @Override
    public boolean registerUser(User u) {
        String sql = "INSERT INTO users(name,age,gender,email,password,city,profession,looking_for) VALUES (?,?,?,?,?,?,?,?)";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, u.getName());
            ps.setInt(2, u.getAge());
            ps.setString(3, u.getGender());
            ps.setString(4, u.getEmail());
            ps.setString(5, u.getPassword());
            ps.setString(6, u.getCity());
            ps.setString(7, u.getProfession());
            ps.setString(8, u.getLookingFor());

            ps.executeUpdate();
            return true;

        } catch (SQLIntegrityConstraintViolationException e) {
            System.out.println("⚠️ Email already registered.");
        } catch (SQLException e) {
            System.out.println("❌ Database error occurred.");
        } catch (Exception e) {
            System.out.println("❌ Unexpected error.");
        }
        return false;
    }

    @Override
public User login(String email, String password) {
    String sql = "SELECT * FROM users WHERE email=? AND password=?";

    try (Connection con = DBConnection.getConnection();
         PreparedStatement ps = con.prepareStatement(sql)) {

        ps.setString(1, email);
        ps.setString(2, password);

        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            User u = new User();
            u.setName(rs.getString("name"));
            u.setCity(rs.getString("city"));
            u.setGender(rs.getString("gender"));        
            u.setLookingFor(rs.getString("looking_for"));
            return u;
        }

    } catch (SQLException e) {
        System.out.println("❌ Database error during login.");
    }
    return null;
}


    @Override
public List<User> findMatches(User user) {

    List<User> list = new ArrayList<>();

    // Show all opposite-gender profiles
    String sql = "SELECT name, profession FROM users WHERE gender=?";

    try (Connection con = DBConnection.getConnection();
         PreparedStatement ps = con.prepareStatement(sql)) {

        if (user.getGender().equalsIgnoreCase("female")) {
            ps.setString(1, "male");
        } else {
            ps.setString(1, "female");
        }

        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            User u = new User();
            u.setName(rs.getString("name"));
            u.setProfession(rs.getString("profession"));
            list.add(u);
        }

    } catch (SQLException e) {
        System.out.println("❌ Error while fetching matches.");
    }

    return list;
}

}
