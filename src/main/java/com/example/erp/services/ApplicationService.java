package com.example.erp.services;

import com.example.erp.bean.Application;
import com.example.erp.bean.Login;
import com.example.erp.dao.impl.ApplicationDaoImpl;
import com.example.erp.dao.impl.LoginDaoImpl;

public class ApplicationService {
    public int register_app(Application appobj)
    {
        ApplicationDaoImpl obj=new ApplicationDaoImpl();
        int ret= obj.registerapp(appobj);
        return ret;
    }
}
