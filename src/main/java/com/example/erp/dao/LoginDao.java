package com.example.erp.dao;

import com.example.erp.bean.Login;
import com.example.erp.bean.Resource;
import com.example.erp.utils.dtutil;

import java.awt.image.RescaleOp;

public interface LoginDao {
    public abstract int login(Login loginobj);
    public abstract int registeruser(Login loginobj);
    public abstract int registerserver(dtutil obj);
//    public abstract int registerserver_server(Resource res);
}
