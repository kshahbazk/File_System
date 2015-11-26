package com.cs149;

import java.util.ArrayList;

/**
 * Created by shahbazkhan on 11/25/15.
 *
 *  This is DataStructure which is mimicing a Directory on a file System
 *
 */
public class Directory {
    private String name;
    private String permissions;
    private ArrayList<Object> contents;

    public Directory() {

    }

    // The setters and getters for the Directory
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

    public void deleteFromDirectoy(Object a) {
        contents.remove(a);
    }


}
