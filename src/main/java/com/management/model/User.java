package com.management.model;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class User {
    private String id;
    private String username;
    private String gender;
    private String password;
    private String role; // USER or ADMIN
    private String email;

    // 构造方法
    public User(String username, String gender, String password, String role, String email) {
        this.id = generateUniqueId();
        this.username = username;
        this.gender = gender;
        this.password = encryptPassword(password);
        this.role = role;
        this.email = email;
    }

    // Getters and Setters
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getGender() { return gender; }
    public void setGender(String gender) { this.gender = gender; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = encryptPassword(password); }

    public String getRole() { return role; }
    public void setRole(String role) { this.role = role; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    // 生成唯一ID
    private String generateUniqueId() {
        return java.util.UUID.randomUUID().toString();
    }

    // 密码加密
    private String encryptPassword(String password) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hash = md.digest(password.getBytes());
            StringBuilder sb = new StringBuilder();
            for (byte b : hash) {
                sb.append(String.format("%02x", b));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Error encrypting password", e);
        }
    }
}
