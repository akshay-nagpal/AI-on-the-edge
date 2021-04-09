package com.example.erp.controller;

import com.example.erp.bean.Login;
import com.example.erp.bean.Resource;
import com.example.erp.services.LoginService;
import com.example.erp.utils.dtutil;

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
    public Response login(Login loginobj) throws URISyntaxException {
//        System.out.println("Usertpe:"+loginobj.getUsertype());
//        System.out.println(loginobj.getEmail());
        LoginService ls_obj = new LoginService();

        int ret = ls_obj.login(loginobj);
        if (ret == 0) {
            return Response.status(406).build();
        }
        return Response.ok().status(200).build();
    }
    @POST
    @Path("/registeruser")
    @Produces(MediaType.TEXT_PLAIN)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response registeruser(Login loginobj) throws URISyntaxException {
        LoginService ls_obj = new LoginService();

        int ret = ls_obj.registeruser(loginobj);
        if (ret == 0) {
            return Response.status(406).build();
        }
        return Response.ok().status(200).build();
    }
    @POST
    @Path("/server")
    @Produces(MediaType.TEXT_PLAIN)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response register_server(dtutil obj) throws URISyntaxException {
        LoginService ls_obj = new LoginService();
        int ret = ls_obj.registerserver(obj);
        System.out.println(ret);
        if (ret == 0) {
            return Response.status(406).build();
        }
        return Response.ok().status(200).build();
    }
//    @POST
//    @Path("/register_server_resource")
//    @Produces(MediaType.TEXT_PLAIN)
//    @Consumes(MediaType.APPLICATION_JSON)
//    public Response registerserver_resource(Resource obj) throws URISyntaxException {
//        LoginService ls_obj = new LoginService();
//        int ret = ls_obj.registerserver_server(obj);
//        System.out.println(ret);
//        if (ret == 0) {
//            return Response.status(406).build();
//        }
//        return Response.ok().status(200).build();
//    }
}
