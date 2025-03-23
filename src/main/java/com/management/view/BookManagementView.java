package com.management.view;

import com.management.controller.BookController;
import com.management.model.Book;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class BookManagementView extends JPanel {
    private BookController bookController;
    private JTextField titleField;
    private JTextField authorField;
    private JTextField isbnField;
    private JTextField publisherField;
    private JTextField publicationDateField;
    private JTextField stockQuantityField;
    private JTextField categoryField;
    private JTextField priceField;
    private JTable bookTable;
    private DefaultTableModel tableModel;

    public BookManagementView() {
        setLayout(new BorderLayout());
        JLabel titleLabel = new JLabel("书籍管理", SwingConstants.CENTER);
        titleLabel.setFont(new Font("微软雅黑", Font.BOLD, 24));
        add(titleLabel, BorderLayout.NORTH);

        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5);

        gbc.gridx = 0;
        gbc.gridy = 0;
        contentPanel.add(new JLabel("书名:"), gbc);

        gbc.gridx = 1;
        titleField = new JTextField();
        contentPanel.add(titleField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        contentPanel.add(new JLabel("作者:"), gbc);

        gbc.gridx = 1;
        authorField = new JTextField();
        contentPanel.add(authorField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        contentPanel.add(new JLabel("ISBN:"), gbc);

        gbc.gridx = 1;
        isbnField = new JTextField();
        contentPanel.add(isbnField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        contentPanel.add(new JLabel("出版社:"), gbc);

        gbc.gridx = 1;
        publisherField = new JTextField();
        contentPanel.add(publisherField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        contentPanel.add(new JLabel("出版日期 (yyyy-MM-dd):"), gbc);

        gbc.gridx = 1;
        publicationDateField = new JTextField();
        contentPanel.add(publicationDateField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 5;
        contentPanel.add(new JLabel("库存数量:"), gbc);

        gbc.gridx = 1;
        stockQuantityField = new JTextField();
        contentPanel.add(stockQuantityField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 6;
        contentPanel.add(new JLabel("分类:"), gbc);

        gbc.gridx = 1;
        categoryField = new JTextField();
        contentPanel.add(categoryField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 7;
        contentPanel.add(new JLabel("价格:"), gbc);

        gbc.gridx = 1;
        priceField = new JTextField();
        contentPanel.add(priceField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 8;
        JButton addButton = new JButton("添加书籍");
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addBook();
            }
        });
        contentPanel.add(addButton, gbc);

        gbc.gridx = 1;
        JButton updateButton = new JButton("更新书籍");
        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateBook();
            }
        });
        contentPanel.add(updateButton, gbc);

        gbc.gridx = 0;
        gbc.gridy = 9;
        JButton deleteButton = new JButton("删除书籍");
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deleteBook();
            }
        });
        contentPanel.add(deleteButton, gbc);

        add(contentPanel, BorderLayout.CENTER);

        // Table to display books
        tableModel = new DefaultTableModel(new Object[]{"ID", "书名", "作者", "ISBN", "出版社", "出版日期", "库存数量", "分类", "价格"}, 0);
        bookTable = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(bookTable);
        add(scrollPane, BorderLayout.SOUTH);

        bookController = new BookController();
        loadBooks();
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

        Book book = new Book(0, title, author, isbn, publisher, publicationDate, stock_Quantity, category, price);
        bookController.addBook(book);
        JOptionPane.showMessageDialog(this, "书籍已添加");
        loadBooks();
        clearFields();
    }

    private void updateBook() {
        int selectedRow = bookTable.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "请选择要更新的书籍");
            return;
        }

        int id = (int) tableModel.getValueAt(selectedRow, 0);
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

        Book book = new Book(id, title, author, isbn, publisher, publicationDate, stock_Quantity, category, price);
        bookController.updateBook(book);
        JOptionPane.showMessageDialog(this, "书籍已更新");
        loadBooks();
        clearFields();
    }

    private void deleteBook() {
        int selectedRow = bookTable.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "请选择要删除的书籍");
            return;
        }

        int id = (int) tableModel.getValueAt(selectedRow, 0);
        bookController.deleteBook(id);
        JOptionPane.showMessageDialog(this, "书籍已删除");
        loadBooks();
        clearFields();
    }

    private void loadBooks() {
        tableModel.setRowCount(0);
        List<Book> books = bookController.getAllBooks();
        if (books != null) {
            for (Book book : books) {
                tableModel.addRow(new Object[]{
                        book.getId(),
                        book.getTitle(),
                        book.getAuthor(),
                        book.getIsbn(),
                        book.getPublisher(),
                        new SimpleDateFormat("yyyy-MM-dd").format(book.getPublicationDate()),
                        book.getStock_Quantity(),
                        book.getCategory(),
                        book.getPrice()
                });
            }
        }
    }

    private void clearFields() {
        titleField.setText("");
        authorField.setText("");
        isbnField.setText("");
        publisherField.setText("");
        publicationDateField.setText("");
        stockQuantityField.setText("");
        categoryField.setText("");
        priceField.setText("");
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("书籍管理系统");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(new BookManagementView());
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}