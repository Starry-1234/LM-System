package com.management.model;

public class Admin {
    private int adminid;
    private String adminname;
    private String password;

    // 构造函数
    public Admin( String adminname, String password) {
        this.adminname = adminname;
        this.password = password;
    }

    // getter and setter
    public String getAdminname() {
        return adminname;
    }

    public void setAdminname(String adminname) {
        this.adminname = adminname;
    }

    public int getAdminid() {
        return adminid;
    }

    public void setAdminid(int adminid) {
        this.adminid = adminid;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }



}
