package com.example.erp.controller;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.*;
import java.net.URISyntaxException;
import java.text.NumberFormat;
import java.util.List;

import com.example.erp.bean.Application;
import com.example.erp.services.ApplicationService;
import com.example.erp.utils.Jobutil;
import com.example.erp.utils.ScriptPython;


@Path("/job")
public class JobController {
    @POST
    @Path(("/schedule"))
    @Produces(MediaType.TEXT_PLAIN)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response schedule_job(Application data) throws URISyntaxException, IOException {
        System.out.println(data.getEmail());
        System.out.println(data.getAppname());
        String email=data.getEmail();
        String appname=data.getAppname();
        String path="/home/rahul/Documents/"+email+"/"+appname;
        File theDir = new File(path);
        if (!theDir.exists()){
            theDir.mkdirs();
        }

        File myObj = new File("/home/rahul/Documents/"+email+"/"+appname+"/"+"completed.txt");
        myObj.createNewFile();
        FileWriter myWriter = new FileWriter("/home/rahul/Documents/"+email+"/"+appname+"/"+"completed.txt");
        myWriter.write("0");
        myWriter.close();
        //        ApplicationService ap_st=new ApplicationService();
//        int ret=ap_st.register_app(data);
        ScriptPython sp=new ScriptPython();
        sp.runPython(email,appname);
//        if (ret == 0) {
//            return Response.status(406).build();
//        }
        return Response.ok().status(200).build();
    }
    @GET
    @Path(("/status"))
    @Produces(MediaType.TEXT_PLAIN)
    public Response get_status(@DefaultValue("All")
                                   @QueryParam(value = "item")
                                   final List<String> item) throws URISyntaxException, IOException, InterruptedException {
//        System.out.println(json);
//        RandomAccessFile in = new RandomAccessFile("/mnt/nfs_share"+data.getEmail()+"/completed.txt", "r");
//        MultiThreading th=new MultiThreading();
//        th.run(item.get(0),item.get(1));
        System.out.println("Inside status");
        System.out.println(item);
        String email= item.get(0);
        String appname=item.get(1);
//        System.out.println(appname);
        RandomAccessFile in = new RandomAccessFile("/home/rahul/Documents/"+email+"/"+appname+"/"+"completed.txt", "r");
        System.out.println("Inside status");
        String line;
        File f=new File("/home/rahul/Documents/"+email+"/"+appname+"/"+"completed.txt");
        while(true) {
            in.seek(0);
            line = in.readLine();
            System.out.println(line);
            if( line.equals("1")) {
                System.out.println(appname+"Done");
//                    f.delete();
//                    return Response.ok().entity(appname).build();
                break;
            } else {
                System.out.println(appname+"Not done");
                Thread.sleep(2000); // poll the file every 2 seconds
            }
        }
        return Response.ok().build();

    }
    @GET
    @Path(("/download"))
    @Produces(MediaType.APPLICATION_OCTET_STREAM)
    public Response downloadFile(@DefaultValue("All")
                                     @QueryParam(value = "item")
                                     final List<String> item) {
        String email= item.get(0);
        String appname=item.get(1);
        System.out.println("Download file "+appname);
        File fileDownload = new File("/home/rahul/Documents/"+email+"/"+appname+"/"+appname+".csv");
        Response.ResponseBuilder response = Response.ok((Object) fileDownload);
        response.header("Content-Disposition", "attachment;filename=" + appname+".csv");
        return response.build();
    }

}





