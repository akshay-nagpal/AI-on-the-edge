package com.example.erp.controller;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.URISyntaxException;
import com.example.erp.utils.ScriptPython;
@Path("/job")
public class JobController {
    @POST
    @Path(("/schedule"))
    @Produces(MediaType.TEXT_PLAIN)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response schedule_job(String email) throws URISyntaxException{
        System.out.println(email);
        ScriptPython sp=new ScriptPython();
        sp.runPython(email);
        return Response.ok().status(200).build();
    }
    @GET
    @Path(("/status"))
    @Produces(MediaType.TEXT_PLAIN)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response get_status(String email) throws URISyntaxException{
        System.out.println(email);
        return Response.ok().status(200).build();
    }
}
