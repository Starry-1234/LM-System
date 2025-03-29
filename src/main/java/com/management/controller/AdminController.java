package com.management.controller;

import com.management.dao.AdminDAO;
import com.management.model.Admin;
import org.apache.commons.dbcp2.BasicDataSource;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Properties;

public class AdminController {

    private AdminDAO adminDAO;

    public AdminController() {

        Properties properties = new Properties();
        try {
            // 使用类加载器读取 application.properties 文件
            properties.load(getClass().getClassLoader().getResourceAsStream("db.properties"));
            BasicDataSource dataSource = new BasicDataSource();
            dataSource.setUrl(properties.getProperty("db.url"));
            dataSource.setUsername(properties.getProperty("db.username"));
            dataSource.setPassword(properties.getProperty("db.password"));
            dataSource.setDriverClassName(properties.getProperty("db.driver"));

            Connection conn = dataSource.getConnection();
            adminDAO = new AdminDAO(conn);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 获取所有管理员
    public List<Admin> getAllAdmins() {
        return adminDAO.getAllAdmins()  ;
    }

    //添加管理员
    public void addAdmin(Admin admin) {
        adminDAO.addAdmin(admin);
    }

    //删除管理员
    public void deleteAdmin(int id) {
        adminDAO.deleteAdmin(id);
    }
}
