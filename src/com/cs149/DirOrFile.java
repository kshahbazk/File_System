package com.cs149;

import java.util.ArrayList;
import java.util.Objects;

/**
 * Created by shahbazkhan on 11/25/15.
 *
 *  This is DataStructure which is mimicking a DirOrFile on a file System
 *
 */
public class DirOrFile {
    private String name;
    private String permissions;
    private ArrayList<Object> contents;
    private String type;

    public DirOrFile(String name) {

        contents = new ArrayList<>();
        // Initialize with Read/Write permissions
        permissions="Read/Write";
        this.name = name;
    }

    // The setters and getters for the DirOrFile
    public void setName(String name) {
        this.name = name;
    }

    public void setPermissions(String permissions) {
        this.permissions = permissions;
    }

    public String getPermissions() {
        return permissions;
    }

    public String getName() {
        return name;
    }

    public void addToDirectory(Object a) {
         contents.add(a);
     }

    public void deleteFromDirectory(Object a) {
        contents.remove(a);
    }
    
    public ArrayList<Object> getContents(){
    	return contents;
    }


}