package com.example.erp.utils;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.File;
import java.io.IOException;
import java.util.List;

public class FileUpload extends HttpServlet {
    public static String email;
    public void setEmail(String mail){
        email=mail;
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            System.out.println("File upload"+email);
            ServletFileUpload sf = new ServletFileUpload(new DiskFileItemFactory());
            List<FileItem> multifiles = sf.parseRequest(request);
            File theDir = new File("/mnt/nfs_share/"+email);
            File CmpDir = new File("/mnt/nfs_share/"+email+"/"+"completed.txt");
            if (!theDir.exists()){
                theDir.mkdirs();
            }
            if (!CmpDir.exists()){
                CmpDir.mkdirs();
            }
//            String dir="/mnt/nfs_share/ %s";
//            String.format(dir, email);
            for (FileItem item : multifiles) {
                item.write(new File("/mnt/nfs_share/"+email+"/"+item.getName()));
            }
        }
        catch (Exception e){
            System.out.println(e);
        }
        System.out.println("file uploaded");

    }
}
