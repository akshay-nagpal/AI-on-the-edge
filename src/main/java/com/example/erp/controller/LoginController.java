package com.example.erp.controller;

import com.example.erp.bean.Login;
import com.example.erp.services.LoginService;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.StreamingOutput;
import java.net.URISyntaxException;

@Path("login")
public class LoginController {
    @POST
    @Path("/login")
    @Produces(MediaType.TEXT_PLAIN)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response registerStudent(Login loginobj) throws URISyntaxException {
//        System.out.println("ma chudao");
//        System.out.println("Usertpe:"+loginobj.getUsertype());
//        System.out.println(loginobj.getEmail());
        LoginService ls_obj = new LoginService();

        int ret = ls_obj.login(loginobj);
        if (ret == 0) {
            return Response.status(406).build();
        }
        return Response.ok().status(200).build();
    }
}
