package com.example.erp.services;

import com.example.erp.bean.Login;
import com.example.erp.bean.Resource;
import com.example.erp.dao.impl.LoginDaoImpl;
import com.example.erp.utils.DTutils;

public class LoginService {
    public int login(Login loginobj)
    {
        LoginDaoImpl obj=new LoginDaoImpl();
        int ret=obj.login(loginobj);
        return ret;
    }
    public int registeruser(Login loginobj)
    {
        LoginDaoImpl obj=new LoginDaoImpl();
        int ret=obj.registeruser(loginobj);
        return ret;
    }
    public int registerserver(DTutils dtobj)
    {
        LoginDaoImpl obj=new LoginDaoImpl();
        int ret=obj.registerserver(dtobj);
        return ret;
    }
//    public int registerserver_server(Resource res)
//    {
//        LoginDaoImpl obj=new LoginDaoImpl();
//        int ret=obj.registerserver_server(res);
//        return ret;
//    }
}
