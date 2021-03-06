package com.example.erp.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.example.erp.bean.Login;
import com.example.erp.bean.Resource;
import com.example.erp.services.LoginService;
import com.example.erp.utils.DTutils;
import com.example.erp.utils.FileUpload;

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
    private static final Logger logger = LogManager.getLogger(LoginController.class);
    @POST
    @Path("/login")
    @Produces(MediaType.TEXT_PLAIN)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response login(Login loginobj) throws URISyntaxException {
        System.out.println("Usertpe:"+loginobj.getUsertype());
        System.out.println(loginobj.getEmail());
        LoginService ls_obj = new LoginService();

        int ret = ls_obj.login(loginobj);
        System.out.println(ret);
        if (ret == 0) {
            logger.info("{} Login unsuccessful for ", loginobj.getEmail());

            return Response.status(406).build();
        }
        else {
            logger.info("{} Login successful for", loginobj.getEmail());

            return Response.ok().status(200).build();
        }
        }
    @POST
    @Path("/registeruser")
    @Produces(MediaType.TEXT_PLAIN)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response registeruser(Login loginobj) throws URISyntaxException {
        LoginService ls_obj = new LoginService();
        int ret = ls_obj.registeruser(loginobj);
        System.out.println(loginobj.getEmail());
        System.out.println(loginobj.getPassword());
        System.out.println(loginobj.getUsertype());
        if (ret == 0) {
            logger.info("{} User registration is unsuccessful ", loginobj.getEmail());

            return Response.status(406).build();
        }
        logger.info("{} User registration is successful ", loginobj.getEmail());

        return Response.ok().status(200).build();
    }
    @POST
    @Path("/server")
    @Produces(MediaType.TEXT_PLAIN)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response register_server(DTutils obj) throws URISyntaxException {
        LoginService ls_obj = new LoginService();
        int ret = ls_obj.registerserver(obj);
        System.out.println(ret);
        System.out.println(obj.getEmail());
        System.out.println(obj.getPassword());
        System.out.println(obj.getUsername());
        System.out.println(obj.getIP());
        System.out.println(obj.getSudo_password());
        System.out.println(obj.getUsertype());
        if (ret == 0) {
            logger.info("{} Server registration is unsuccessful ", obj.getEmail());
            return Response.status(406).build();
        }
        logger.info("{} server registration is successful ", obj.getEmail());

        return Response.ok().status(200).build();
    }
//    @POST
//    @Path("/register_server_resource")
//    @Produces(MediaType.TEXT_PLAIN)
//    @Consumes(MediaType.APPLICATION_JSON)
//    public Response registerserver_resource(Resource obj) throws URISyntaxException {
//        LoginService ls_obj = new LoginService();
//        int ret = ls_obj.registerserver(obj);
//        if (ret == 0) {
//            return Response.status(406).build();
//        }
//        return Response.ok().status(200).build();
//    }
}
