package com.cs149;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by shahbazkhan on 11/29/15.
 *
 *  Using from Example from
 *  http://vivin.net/2010/01/30/generic-n-ary-tree-in-java/
 */


public class FileSystemNode<T> {
    public T data;
    public List<FileSystemNode<T>> children;
    private String Type;
    private String Owner;
    private int Size;
    private String permissions;

    public FileSystemNode() {
        super();
        children = new ArrayList<FileSystemNode<T>>();
        permissions="Read/Write";
    }

    public FileSystemNode(T data) {
        this();
        setData(data);
        permissions="Read/Write";
    }

    public List<FileSystemNode<T>> getChildren() {
        return this.children;
    }

    public int getNumberOfChildren() {
        return getChildren().size();
    }

    public boolean hasChildren() {
        return (getNumberOfChildren() > 0);
    }

    public void setChildren(List<FileSystemNode<T>> children) {
        this.children = children;
    }

    public void addChild(FileSystemNode<T> child) {
        children.add(child);
    }

    public void addChildAt(int index, FileSystemNode<T> child) throws IndexOutOfBoundsException {
        children.add(index, child);
    }

    public void removeChildren() {
        this.children = new ArrayList<FileSystemNode<T>>();
    }

    public void removeChildAt(int index) throws IndexOutOfBoundsException {
        children.remove(index);
    }

    public FileSystemNode<T> getChildAt(int index) throws IndexOutOfBoundsException {
        return children.get(index);
    }

    public T getData() {
        return this.data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String toString() {
        return getData().toString();
    }

    public boolean equals(FileSystemNode<T> node) {
        return node.getData().equals(getData());
    }

    public int hashCode() {
        return getData().hashCode();
    }

    public String toStringVerbose() {
        String stringRepresentation = getData().toString() + ":[";

        for (FileSystemNode<T> node : getChildren()) {
            stringRepresentation += node.getData().toString() + ", ";
        }

        //Pattern.DOTALL causes ^ and $ to match. Otherwise it won't. It's retarded.
        Pattern pattern = Pattern.compile(", $", Pattern.DOTALL);
        Matcher matcher = pattern.matcher(stringRepresentation);

        stringRepresentation = matcher.replaceFirst("");
        stringRepresentation += "]";

        return stringRepresentation;
    }

    public int getSize() {
        return Size;
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