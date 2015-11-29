package com.cs149;

/**
 * Created by shahbazkhan on 11/25/15.
 */
public class File {
    private String name;
    private String Type;
    private String Owner;
    private int Size;
    private String permissions;

    public File (String name) {
        this.name = name;
        // Initialize with Read/Write permissions
        permissions="Read/Write";

    }

    public int getSize() {
        return Size;
    }

    public String getName() {
        return this.name;
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
        this.name = name;
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
