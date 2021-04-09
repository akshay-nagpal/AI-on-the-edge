package com.example.erp.dao.impl;

import com.example.erp.bean.Login;
import com.example.erp.bean.Resource;
import com.example.erp.dao.LoginDao;
import com.example.erp.utils.SessionUtil;
import com.example.erp.utils.DTutils;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class LoginDaoImpl implements LoginDao {
    public final Resource resglobal=new Resource();
    @Override
    public int login(Login loginobj){
        Session session = SessionUtil.getSession();
        session.beginTransaction();
        String hql="select email from Login  where email= :param and password= :param2 and usertype= :param3";
        Query query=session.createQuery(hql);
        query.setParameter("param",loginobj.getEmail());
        query.setParameter("param2",loginobj.getPassword());
        query.setParameter("param3",loginobj.getUsertype());
        System.out.println(loginobj.getUsertype());
        List results= query.list();
        for(int i=0;i<results.size();i++){
            System.out.println(results.get(i));
        }
        if(results.size()==0){
            return 0;
        }

        session.close();
        return 1;

    }

    @Override
    public int registeruser(Login loginobj) {
        Session session = SessionUtil.getSession();
        Transaction transaction = session.beginTransaction();
        Login log=new Login();
        log.setEmail(loginobj.getEmail());
        log.setPassword(loginobj.getPassword());
        log.setUsertype(loginobj.getUsertype());
        session.save(log);
        transaction.commit();
        session.close();
        return 1;
    }

    @Override
    public int registerserver(DTutils obj) {
        System.out.println("hithere");
        Session session = SessionUtil.getSession();
        Transaction transaction = session.beginTransaction();
        Login log=new Login();
        log.setEmail(obj.getEmail());
        log.setPassword(obj.getPassword());
        log.setUsertype(obj.getUsertype());
        session.save(log);
        Resource res=new Resource();
        res.setIP(obj.getIP());
        res.setSudo_password(obj.getSudo_password());
        res.setUsername(obj.getUsername());
        session.save(res);
        res.setLogin(log);
        session.save(res);
        transaction.commit();
        session.close();
        return 1;
    }

//    @Override
//    public int registerserver_server(Resource res) {
//        Session session = SessionUtil.getSession();
//        Transaction transaction = session.beginTransaction();
//        resglobal.setUsername(res.getUsername());
//        resglobal.setSudo_password(res.getSudo_password());
//        resglobal.setIP(res.getIP());
//        session.save(resglobal);
//        transaction.commit();
//        session.close();
//        return 1;
//    }
}
