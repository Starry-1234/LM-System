package com.management.controller;

import com.management.dao.UserDAO;
import com.management.model.User;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UserController {
    private UserDAO userDAO;
    private List<User> userList;
    private static final Logger logger = Logger.getLogger(UserController.class.getName());

    public UserController(UserDAO userDAO) {
        this.userDAO = userDAO;
        userList = new ArrayList<>();
        loadUsersFromDatabase();
    }

    // 获取所有用户
    public List<User> getAllUsers() {
        return userList;
    }

    // 添加用户
    public void addUser(User user) {
        userList.add(user);
        userDAO.addUser(user);
    }

    // 编辑用户
    public void editUser(User updatedUser) {
        for (User user : userList) {
            if (user.getId().equals(updatedUser.getId())) {
                user.setUsername(updatedUser.getUsername());
                user.setPassword(updatedUser.getPassword());
                user.setRole(updatedUser.getRole());
                user.setEmail(updatedUser.getEmail());
                user.setGender(updatedUser.getGender()); // 更新性别属性
                userDAO.editUser(updatedUser);
                break;
            }
        }
    }

    // 删除用户
    public void deleteUser(String id) {
        userList.removeIf(user -> user.getId().equals(id));
        userDAO.deleteUser(id);
    }

    // 根据ID查找用户
    public User getUserById(String id) {
        for (User user : userList) {
            if (user.getId().equals(id)) {
                return user;
            }
        }
        return userDAO.getUserById(id);
    }

    // 从数据库加载用户数据
    private void loadUsersFromDatabase() {
        userList = userDAO.getAllUsers();
    }
}
