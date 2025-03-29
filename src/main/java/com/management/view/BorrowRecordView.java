package com.management.view;

import com.management.controller.BorrowRecordController;
import com.management.dao.BorrowRecordDAO;
import com.management.model.BorrowRecord;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

public class BorrowRecordView extends JPanel {

    private BorrowRecordController borrowRecordController;
    private JTable AdminTable;
    private DefaultTableModel tableModel;

    public BorrowRecordView() {
        setLayout(new BorderLayout());

        // 标题
        JLabel titleLabel = new JLabel("借阅记录", SwingConstants.CENTER);
        titleLabel.setFont(new Font("微软雅黑", Font.BOLD, 24));
        add(titleLabel, BorderLayout.NORTH);

        // 表格模型
        tableModel = new DefaultTableModel(new Object[]{"ID", "ISBN", "书名", "借阅人", "借阅时间", "归还时间"}, 0);
        AdminTable = new JTable(tableModel);
        JScrollPane BookPane = new JScrollPane(AdminTable);
        add(BookPane, BorderLayout.CENTER);

        borrowRecordController = new BorrowRecordController();
        loadBorrowRecords();
    }
    private void loadBorrowRecords() {
        tableModel.setRowCount(0); // 清空表格
        List<BorrowRecord> borrowRecords = borrowRecordController.getAllBorrowRecords();
        if (borrowRecords != null) {
            for (BorrowRecord record : borrowRecords) {
                tableModel.addRow(new Object[]{
                        record.getId(),
                        record.getISBN(),
                        record.getBookName(),
                        record.getBorrower(),
                        record.getBorrowingTime(),
                        record.getReturnTime()
                });
            }
        }
    }
}