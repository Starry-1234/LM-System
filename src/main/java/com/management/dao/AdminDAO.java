package com.management.dao;

import com.management.model.Admin;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AdminDAO {
    private Connection conn;
    private static final Logger logger = Logger.getLogger(AdminDAO.class.getName());

    public AdminDAO(Connection conn) {
        this.conn = conn;
    }

    // 获取所有用户
    public List<Admin> getAllAdmins() {
        List<Admin> admins = new ArrayList<>();
        String sql = "SELECT * FROM library.admins        ";
        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Admin record = new Admin(
                        rs.getString("admin_name"),
                        rs.getString("admin_password")
                );
                admins.add(record);
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "加载借阅记录数据失败：" + e.getMessage());
            e.printStackTrace();
        }
        return admins;
    }

    // 添加管理员
    public void addAdmin(Admin record) {
        String sql = "INSERT INTO admins (admin_name, admin_password) VALUES ( ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, record.getAdminname());
            stmt.setString(2, record.getPassword());
            stmt.executeUpdate();
        } catch (SQLException e) {}
    }

    // 删除管理员
    public void deleteAdmin(int id) {
        String sql = "DELETE FROM admins WHERE admin_id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "删除借阅记录数据失败：" + e.getMessage());
            e.printStackTrace();
        }
    }

    // 编辑管理员
    public void updateAdmin(Admin updatedRecord) {
        String sql = "UPDATE library.Admin SET admin_name = ?, admin_password = ? WHERE adminid = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, updatedRecord.getAdminname());
            stmt.setString(2, updatedRecord.getPassword());
            stmt.setInt(3, updatedRecord.getAdminid());
            stmt.executeUpdate();
        } catch(SQLException e) {}
    }


    /*// 添加借阅记录
    public void addAdmin(Admin record) {
        String sql = "INSERT INTO library.Admin (id, ISBN, book_name, Borrower, Borrowing_time, Return_time) VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, record.getAdminid());
            stmt.setString(2, record.getAdminname());
            stmt.setString(3, record.getPassword());
            stmt.executeUpdate();
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "保存借阅记录数据失败：" + e.getMessage());
            e.printStackTrace();
        }
    }*/

    /*// 编辑借阅记录
    public void editAdmin(Admin updatedRecord) {
        String sql = "UPDATE library.Admin SET ISBN = ?, book_name = ?, Borrower = ?, Borrowing_time = ?, Return_time = ? WHERE id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, updatedRecord.getISBN());
            stmt.setString(2, updatedRecord.getBookName());
            stmt.setString(3, updatedRecord.getBorrower());
            stmt.setDate(4, new java.sql.Date(updatedRecord.getBorrowingTime().getTime()));
            stmt.setDate(5, new java.sql.Date(updatedRecord.getReturnTime().getTime()));
            stmt.setInt(6, updatedRecord.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "更新借阅记录数据失败：" + e.getMessage());
            e.printStackTrace();
        }
    }*/

    // 删除借阅记录
    /*public void deleteAdmin(int id) {
        String sql = "DELETE FROM library.Admin WHERE id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "删除借阅记录数据失败：" + e.getMessage());
            e.printStackTrace();
        }
    }*/

}

