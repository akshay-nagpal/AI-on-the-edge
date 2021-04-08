package com.example.erp.dao.impl;

import com.example.erp.bean.Login;
import com.example.erp.dao.LoginDao;
import com.example.erp.utils.SessionUtil;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;

public class LoginDaoImpl implements LoginDao {
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
}
