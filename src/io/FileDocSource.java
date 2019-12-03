/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io;

import java.io.File;
import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.FileReader; 

/**
 *
 * @author patel381
 */
public class FileDocSource extends DocSource {
    
    protected ArrayList<File> _files= new ArrayList<File>();
            
    public FileDocSource (String src){///src is the directory 
        for (File f: FileFinder.GetAllFiles(src)){
            _files.add(f); 
        } 
        
    }
    
    @Override
    public int getNumDocs() {
            return _files.size();
    }

    @Override
    public String getDoc(int id) { //gets doc name and info
            BufferedReader cin = null;
            StringBuilder sb = new StringBuilder();
            
        try {
        String line  ; 
        cin = new BufferedReader (new FileReader (_files.get(id)));
        //passing id to get file at that index 
        //passing ID you are passing the file and now you will be reading into it
        //cehck the statment below 
         while ((line = cin.readLine()) != null ) {
             sb.append(line + " "); 
           // line = cin.readLine();
         }   
         cin.close();    
        }   
        catch (IOException e) {
                System.out.println("Exception!");
               
        }
        
         return sb.toString() ;    
         //use default string method 
    }
    

    
}
