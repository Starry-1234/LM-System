package com.management.dao;

import com.management.model.User;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UserDAO {
    private Connection conn;
    private static final Logger logger = Logger.getLogger(UserDAO.class.getName());

    public UserDAO(Connection conn) {
        this.conn = conn;
    }

    // 获取所有用户
    public List<User> getAllUsers() {
        List<User> userList = new ArrayList<>();
        String sql = "SELECT * FROM user";
        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                User user = new User(
                        rs.getString("username"),
                        rs.getString("gender"),
                        rs.getString("password"),
                        rs.getString("role"),
                        rs.getString("email")
                );
                user.setId(rs.getString("id"));
                userList.add(user);
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "加载用户数据失败：" + e.getMessage());
            e.printStackTrace();
        }
        return userList;
    }

    // 添加用户
    public void addUser(User user) {
        String sql = "INSERT INTO user (id, username, gender, password, role, email) VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, user.getId());
            stmt.setString(2, user.getUsername());
            stmt.setString(3, user.getGender());
            stmt.setString(4, user.getPassword());
            stmt.setString(5, user.getRole());
            stmt.setString(6, user.getEmail());
            stmt.executeUpdate();
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "保存用户数据失败：" + e.getMessage());
            e.printStackTrace();
        }
    }

    // 编辑用户
    public void editUser(User updatedUser) {
        String sql = "UPDATE user SET username = ?, gender = ?, password = ?, role = ?, email = ? WHERE id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, updatedUser.getUsername());
            stmt.setString(2, updatedUser.getGender());
            stmt.setString(3, updatedUser.getPassword());
            stmt.setString(4, updatedUser.getRole());
            stmt.setString(5, updatedUser.getEmail());
            stmt.setString(6, updatedUser.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "更新用户数据失败：" + e.getMessage());
            e.printStackTrace();
        }
    }

    // 删除用户
    public void deleteUser(String id) {
        String sql = "DELETE FROM user WHERE id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "删除用户数据失败：" + e.getMessage());
            e.printStackTrace();
        }
    }

    // 根据ID查找用户
    public User getUserById(String id) {
        String sql = "SELECT * FROM user WHERE id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                User user = new User(
                        rs.getString("username"),
                        rs.getString("gender"),
                        rs.getString("password"),
                        rs.getString("role"),
                        rs.getString("email")
                );
                user.setId(rs.getString("id"));
                return user;
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "查找用户数据失败：" + e.getMessage());
            e.printStackTrace();
        }
        return null;
    }
}
