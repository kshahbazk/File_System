package com.cs149;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by shahbazkhan on 11/29/15.
 *
 *  Using from Example from
 *  http://stackoverflow.com/questions/19330731/tree-implementation-in-java-root-parents-and-children
 */


public class DirectoryOrFile<T> {
    private List<DirectoryOrFile<T>> children = new ArrayList<DirectoryOrFile<T>>();
    private DirectoryOrFile<T> parent = null;
    private T data = null;

    private String name;
    private String Type;
    private String Owner;
    private int Size;
    private String permissions;


    public DirectoryOrFile(T data) {
        this.data = data;
    }

    public DirectoryOrFile(T data, DirectoryOrFile<T> parent) {
        this.data = data;
        this.parent = parent;
    }

    public List<DirectoryOrFile<T>> getChildren() {
        return children;
    }

    public void setParent(DirectoryOrFile<T> parent) {
        parent.addChild(this);
        this.parent = parent;
    }

    public void addChild(T data) {
        DirectoryOrFile<T> child = new DirectoryOrFile<T>(data);
        child.setParent(this);
        this.children.add(child);
    }

    public void addChild(DirectoryOrFile<T> child) {
        child.setParent(this);
        this.children.add(child);
    }

    public T getData() {
        return this.data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public boolean isRoot() {
        return (this.parent == null);
    }

    public boolean isLeaf() {
        if(this.children.size() == 0)
            return true;
        else
            return false;
    }

    public void removeParent() {
        this.parent = null;
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