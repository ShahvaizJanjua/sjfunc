package com.example.fn;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;
import java.io.BufferedReader;
import java.io.FileOutputStream;


public class HelloFunction {

    public String handleRequest(String input) {
        input = "XYZPolicies.pdf";
        System.out.println(input);
        //ObjectMapper objectMapper = new ObjectMapper();
        //Map data = objectMapper.convertValue(event.getData().get(), Map.class);
        //Map additionalDetails = objectMapper.convertValue(data.get("additionalDetails"), Map.class);
        System.out.println("Building ObjectURL, OBJ Name: "+input);
        String objectURL = "https://frofufwuvghu.objectstorage.uk-london-1.oci.customer-oci.com/p/w7dj07NLfA0cJcKaIGl7oJwLiZaZmjkOzjaFDLeDfQKzauu9muot5HqVj_NXLYd0/n/frofufwuvghu/b/SJBucket/o/"
        +input;

        /*try (BufferedInputStream in = new BufferedInputStream(new URL(objectURL).openStream());
            FileOutputStream fileOutputStream = new FileOutputStream(input)) {
                byte dataBuffer[] = new byte[1024];
                int bytesRead;
                while ((bytesRead = in.read(dataBuffer, 0, 1024)) != -1) {
                    fileOutputStream.write(dataBuffer, 0, bytesRead);
                }   
            } catch (IOException e) {
             // handle exception
        }*/
        System.out.println("Ready to D/L Object from OSS");
        /*
        InputStream in = new URL(objectURL).openStream();
        Files.copy(in, Paths.get(input), StandardCopyOption.REPLACE_EXISTING);
        */
        try (BufferedInputStream in = new BufferedInputStream(new URL(objectURL).openStream());
            FileOutputStream fileOutputStream = new FileOutputStream(input)) {
            byte dataBuffer[] = new byte[1024];
            int bytesRead;
            while ((bytesRead = in.read(dataBuffer, 0, 1024)) != -1) {
                fileOutputStream.write(dataBuffer, 0, bytesRead);
            }
        } catch (IOException e) {
            System.out.println("Up shits creek");
            //return e.println();
        }
        System.out.println("Obj downloaded");

        System.out.println("About to try and upload!");
        String charset = "UTF-8";
        File uploadFile1 = new File("input");
        String requestURL = "https://g4bd449b5f2d9a3-adbrag.adb.uk-london-1.oraclecloudapps.com/ords/admin/api/insertdoc?file_name=fnfile.pdf&file_size=100&file_type=PDF";
 
        try {
            System.out.println("We are in the try!");
            MultipartUtility multipart = new MultipartUtility(requestURL, charset);
             
            multipart.addHeaderField("User-Agent", "CodeJava");
            multipart.addHeaderField("Test-Header", "Header-Value");
             
            multipart.addFilePart("fileUpload", uploadFile1);
 
            List<String> response = multipart.finish();
             
            System.out.println("SERVER REPLIED:");
             
            for (String line : response) {
                System.out.println(line);
            }
        } catch (IOException ex) {
            System.err.println(ex);
        }
        System.out.println("FileUploaded!");

        return "RETURNED BRO";
    }

}