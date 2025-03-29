package com.management.dao;

import com.management.model.BorrowRecord;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class BorrowRecordDAO {
    private Connection conn;
    private static final Logger logger = Logger.getLogger(BorrowRecordDAO.class.getName());

    public BorrowRecordDAO(Connection conn) {
        this.conn = conn;
    }

    // 获取所有借阅记录
    public List<BorrowRecord> getAllBorrowRecords() {
        List<BorrowRecord> borrowRecords = new ArrayList<>();
        String sql = "SELECT * FROM library.BorrowRecord";
        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                BorrowRecord record = new BorrowRecord(
                        rs.getInt("id"),
                        rs.getString("ISBN"),
                        rs.getString("book_name"),
                        rs.getString("Borrower"),
                        rs.getDate("Borrowing_time"),
                        rs.getDate("Return_time")
                );
                borrowRecords.add(record);
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "加载借阅记录数据失败：" + e.getMessage());
            e.printStackTrace();
        }
        return borrowRecords;
    }

    // 添加借阅记录
    public void addBorrowRecord(BorrowRecord record) {
        String sql = "INSERT INTO library.BorrowRecord (id, ISBN, book_name, Borrower, Borrowing_time, Return_time) VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, record.getId());
            stmt.setString(2, record.getISBN());
            stmt.setString(3, record.getBookName());
            stmt.setString(4, record.getBorrower());
            stmt.setDate(5, new java.sql.Date(record.getBorrowingTime().getTime()));
            stmt.setDate(6, new java.sql.Date(record.getReturnTime().getTime()));
            stmt.executeUpdate();
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "保存借阅记录数据失败：" + e.getMessage());
            e.printStackTrace();
        }
    }

    // 编辑借阅记录
    public void editBorrowRecord(BorrowRecord updatedRecord) {
        String sql = "UPDATE library.BorrowRecord SET ISBN = ?, book_name = ?, Borrower = ?, Borrowing_time = ?, Return_time = ? WHERE id = ?";
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
    }

    // 删除借阅记录
    public void deleteBorrowRecord(int id) {
        String sql = "DELETE FROM library.BorrowRecord WHERE id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "删除借阅记录数据失败：" + e.getMessage());
            e.printStackTrace();
        }
    }

    // 根据ID查找借阅记录
    public BorrowRecord getBorrowRecordById(int id) {
        String sql = "SELECT * FROM library.BorrowRecord WHERE id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                BorrowRecord record = new BorrowRecord(
                        rs.getInt("id"),
                        rs.getString("ISBN"),
                        rs.getString("book_name"),
                        rs.getString("Borrower"),
                        rs.getDate("Borrowing_time"),
                        rs.getDate("Return_time")
                );
                return record;
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "查找借阅记录数据失败：" + e.getMessage());
            e.printStackTrace();
        }
        return null;
    }
}

