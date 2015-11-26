package com.cs149;

/**
 * Created by shahbazkhan on 11/25/15.
 */
public class File {
    private String Name;
    private String Type;
    private String Owner;
    private int Size;
    private String permissions;

    public File () {
    }

    public int getSize() {
        return Size;
    }

    public String getName() {
        return Name;
    }

    public String getOwner() {
        return Owner;
    }

    public String getPermissions() {
        return permissions;
    }

    public String getType() {
        return Type;
    }

    public void setName(String name) {
        Name = name;
    }

    public void setOwner(String owner) {
        Owner = owner;
    }

    public void setPermissions(String permissions) {
        this.permissions = permissions;
    }

    public void setSize(int size) {
        Size = size;
    }

    public void setType(String type) {
        Type = type;
    }
}
