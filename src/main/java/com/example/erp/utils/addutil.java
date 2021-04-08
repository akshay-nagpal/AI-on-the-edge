package com.example.erp.utils;

import com.example.erp.bean.Login;
import com.example.erp.bean.Resource;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;

public class addutil {
    public static void main(String[] args)
    {
        Session session = SessionUtil.getSession();
        Transaction transaction = session.beginTransaction();
        Login log=new Login();
        Resource res=new Resource();
//        Students s=new Students();
//                List<Courses> query=session.createQuery("From Courses ").getResultList();
//                for (int i = 0; i < query.size(); i++) {
//                        System.out.println(query.get(i).getName());
//                }


        res.setIP("192.168.43.49");
        res.setSudo_password("rahul166");
        res.setUsername("rahul");
        session.save(res);
        log.setEmail("rahul@gmail.com");
        log.setPassword("pass");
        log.setUsertype(2);
        session.save(log);
        log.setResource(res);
        session.save(log);

//                        session.save(student);
        transaction.commit();
        session.close();
    }
}
