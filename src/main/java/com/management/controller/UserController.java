package com.management.controller;

import com.management.model.User;
import java.util.ArrayList;
import java.util.List;

public class UserController {
    private List<User> userList;

    public UserController() {
        userList = new ArrayList<>();
        // 初始化一些测试数据
        userList.add(new User(1, "admin", "admin123", "ADMIN", "admin@example.com"));
        userList.add(new User(2, "user1", "user123", "USER", "user1@example.com"));
    }

    // 获取所有用户
    public List<User> getAllUsers() {
        return userList;
    }

    // 添加用户
    public void addUser(User user) {
        user.setId(userList.size() + 1); // 自动生成ID
        userList.add(user);
    }

    // 编辑用户
    public void editUser(User updatedUser) {
        for (User user : userList) {
            if (user.getId() == updatedUser.getId()) {
                user.setUsername(updatedUser.getUsername());
                user.setPassword(updatedUser.getPassword());
                user.setRole(updatedUser.getRole());
                user.setEmail(updatedUser.getEmail());
                break;
            }
        }
    }

    // 删除用户
    public void deleteUser(int id) {
        userList.removeIf(user -> user.getId() == id);
    }

    // 根据ID查找用户
    public User getUserById(int id) {
        for (User user : userList) {
            if (user.getId() == id) {
                return user;
            }
        }
        return null;
    }
}