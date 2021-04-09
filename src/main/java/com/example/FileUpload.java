package com.example;

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
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            ServletFileUpload sf = new ServletFileUpload(new DiskFileItemFactory());
            List<FileItem> multifiles = sf.parseRequest(request);

            for (FileItem item : multifiles) {
                item.write(new File("/home/rahul/Documents/AI-on-the-edge" + item.getName()));
            }
        }
        catch (Exception e){
            System.out.println(e);
        }
        System.out.println("file uploaded");

    }
}
