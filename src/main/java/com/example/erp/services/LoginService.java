package com.example.erp.services;

import com.example.erp.bean.Login;
import com.example.erp.dao.impl.LoginDaoImpl;

public class LoginService {
    public int login(Login loginobj)
    {
        LoginDaoImpl obj=new LoginDaoImpl();
        int ret=obj.login(loginobj);
        return ret;
    }
}
