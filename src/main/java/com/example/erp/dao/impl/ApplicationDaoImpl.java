package com.example.erp.dao.impl;

import com.example.erp.bean.Application;
import com.example.erp.dao.ApplicationDao;
import com.example.erp.utils.SessionUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class ApplicationDaoImpl implements ApplicationDao {
    @Override
    public int registerapp (Application obj){
        Session session = SessionUtil.getSession();
        Transaction transaction = session.beginTransaction();
        Application application=new Application();
        application.setAppname(obj.getAppname());
        application.setEmail(obj.getEmail());
        session.save(application);
        transaction.commit();
        session.close();
        return 1;
    }
}
