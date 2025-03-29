package com.management.view;

import com.management.controller.BookController;
import com.management.model.Book;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class AddBookDialog extends JDialog {
    private JTextField titleField, authorField, isbnField, publisherField, publicationDateField, stockQuantityField, categoryField, priceField;
    private JButton confirmButton, cancelButton;
    private BookController bookController;


    public AddBookDialog(JFrame parent, BookController bookController) {
        super(parent, "添加书籍", true);
        this.bookController = bookController;
        setSize(400, 300);
        setLocationRelativeTo(parent);
        setLayout(new BorderLayout());

        // 创建面板
        JPanel panel = new JPanel(new GridLayout(8, 2, 10, 10));
        panel.add(new JLabel("书名:"));
        titleField = new JTextField();
        panel.add(titleField);

        panel.add(new JLabel("作者:"));
        authorField = new JTextField();
        panel.add(authorField);

        panel.add(new JLabel("ISBN:"));
        isbnField = new JTextField();
        panel.add(isbnField);

        panel.add(new JLabel("出版社:"));
        publisherField = new JTextField();
        panel.add(publisherField);

        panel.add(new JLabel("出版日期:"));
        publicationDateField = new JTextField();
        panel.add(publicationDateField);

        panel.add(new JLabel("库存:"));
        stockQuantityField = new JTextField();
        panel.add(stockQuantityField);

        panel.add(new JLabel("分类:"));
        categoryField = new JTextField();
        panel.add(categoryField);

        panel.add(new JLabel("价格:"));
        priceField = new JTextField();
        panel.add(priceField);

        add(panel, BorderLayout.CENTER);

        // 创建按钮面板
        JPanel buttonPanel = new JPanel();
        confirmButton = new JButton("确认");
        cancelButton = new JButton("取消");

        confirmButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addBook();
            }
        });

        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        buttonPanel.add(confirmButton);
        buttonPanel.add(cancelButton);
        add(buttonPanel, BorderLayout.SOUTH);
    }

    private void addBook() {
        String title = titleField.getText();
        String author = authorField.getText();
        String isbn = isbnField.getText();
        String publisher = publisherField.getText();
        String publicationDateString = publicationDateField.getText();
        int stock_Quantity = Integer.parseInt(stockQuantityField.getText());
        String category = categoryField.getText();
        double price = Double.parseDouble(priceField.getText());

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date publicationDate = null;
        try {
            publicationDate = dateFormat.parse(publicationDateString);
        } catch (ParseException ex) {
            JOptionPane.showMessageDialog(this, "日期格式错误，请使用 yyyy-MM-dd 格式");
            return;
        }

        // 检查所有字段是否为空
        if (title.isEmpty() || author.isEmpty() || isbn.isEmpty() || publisher.isEmpty() || price == 0
                || publicationDate == null || category.isEmpty() || stock_Quantity == 0) {
            JOptionPane.showMessageDialog(this, "所有字段不能为空！");
            return;
        }

        // 添加书籍
        Book book = new Book(0, title, author, isbn, publisher, publicationDate, stock_Quantity, category, price);
        bookController.addBook(book);

        // 刷新表格
        ((AdminManagementView) getParent()).loadBooks();
        dispose();
    }

}
