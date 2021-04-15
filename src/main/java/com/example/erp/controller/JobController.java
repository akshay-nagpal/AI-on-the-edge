package com.example.erp.controller;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.*;
import java.net.URISyntaxException;

import com.example.erp.utils.Jobutil;
import com.example.erp.utils.ScriptPython;
@Path("/job")
public class JobController {
    @POST
    @Path(("/schedule"))
    @Produces(MediaType.TEXT_PLAIN)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response schedule_job(Jobutil data) throws URISyntaxException{
        System.out.println(data.getEmail());
        System.out.println(data.getAppname());
        String email=data.getEmail();
        String appname=data.getAppname();
        ScriptPython sp=new ScriptPython();
        sp.runPython(email,appname);
        return Response.ok().status(200).build();
    }
    @GET
    @Path(("/status"))
    @Produces(MediaType.TEXT_PLAIN)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response get_status(Jobutil data) throws URISyntaxException, IOException, InterruptedException {
//        System.out.println(json);
        RandomAccessFile in = new RandomAccessFile("/mnt/nfs_share"+data.getEmail()+"/completed.txt", "r");
        String line;
        while(true) {
            if((line = in.readLine()) != null) {
                return Response.status(200).build();
            } else {
                Thread.sleep(2000); // poll the file every 2 seconds
            }
        }


    }
}



