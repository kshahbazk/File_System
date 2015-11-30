package com.cs149;

/**
 * Created by shahbazkhan on 11/29/15.
 *
 * Borrowed Example from http://vivin.net/2010/01/30/generic-n-ary-tree-in-java/2/
 */
import java.util.*;

public class FileSystem<T> {

    private FileSystemNode<T> root;

    public FileSystem() {
        super();
    }

    public FileSystemNode<T> getRoot() {
        return this.root;
    }

    public void setRoot(FileSystemNode<T> root) {
        this.root = root;
    }

    public int getNumberOfNodes() {
        int numberOfNodes = 0;

        if(root != null) {
            numberOfNodes = auxiliaryGetNumberOfNodes(root) + 1; //1 for the root!
        }

        return numberOfNodes;
    }

    private int auxiliaryGetNumberOfNodes(FileSystemNode<T> node) {
        int numberOfNodes = node.getNumberOfChildren();

        for(FileSystemNode<T> child : node.getChildren()) {
            numberOfNodes += auxiliaryGetNumberOfNodes(child);
        }

        return numberOfNodes;
    }

    public boolean exists(FileSystemNode<T> nodeToFind) {
        return (find(nodeToFind) != null);
    }

    public FileSystemNode<T> find(FileSystemNode<T> nodeToFind) {
        FileSystemNode<T> returnNode = null;

        if(root != null) {
            returnNode = auxiliaryFind(root, nodeToFind);
        }

        return returnNode;
    }

    private FileSystemNode<T> auxiliaryFind(FileSystemNode<T> currentNode, FileSystemNode<T> nodeToFind) {
        FileSystemNode<T> returnNode = null;
        int i = 0;

        if (currentNode.equals(nodeToFind)) {
            returnNode = currentNode;
        }

        else if(currentNode.hasChildren()) {
            i = 0;
            while(returnNode == null && i < currentNode.getNumberOfChildren()) {
                returnNode = auxiliaryFind(currentNode.getChildAt(i), nodeToFind);
                i++;
            }
        }

        return returnNode;
    }

    public boolean isEmpty() {
        return (root == null);
    }

    public List<FileSystemNode<T>> build(FileSystemTraversal traversalOrder) {
        List<FileSystemNode<T>> returnList = null;

        if(root != null) {
            returnList = build(root, traversalOrder);
        }

        return returnList;
    }

    public List<FileSystemNode<T>> build(FileSystemNode<T> node, FileSystemTraversal traversalOrder) {
        List<FileSystemNode<T>> traversalResult = new ArrayList<FileSystemNode<T>>();

        if(traversalOrder == FileSystemTraversal.PRE_ORDER) {
            buildPreOrder(node, traversalResult);
        }

        else if(traversalOrder == FileSystemTraversal.POST_ORDER) {
            buildPostOrder(node, traversalResult);
        }

        return traversalResult;
    }

    private void buildPreOrder(FileSystemNode<T> node, List<FileSystemNode<T>> traversalResult) {
        traversalResult.add(node);

        for(FileSystemNode<T> child : node.getChildren()) {
            buildPreOrder(child, traversalResult);
        }
    }

    private void buildPostOrder(FileSystemNode<T> node, List<FileSystemNode<T>> traversalResult) {
        for(FileSystemNode<T> child : node.getChildren()) {
            buildPostOrder(child, traversalResult);
        }

        traversalResult.add(node);
    }

    public Map<FileSystemNode<T>, Integer> buildWithDepth(FileSystemTraversal traversalOrder) {
        Map<FileSystemNode<T>, Integer> returnMap = null;

        if(root != null) {
            returnMap = buildWithDepth(root, traversalOrder);
        }

        return returnMap;
    }

    public Map<FileSystemNode<T>, Integer> buildWithDepth(FileSystemNode<T> node, FileSystemTraversal traversalOrder) {
        Map<FileSystemNode<T>, Integer> traversalResult = new LinkedHashMap<FileSystemNode<T>, Integer>();

        if(traversalOrder == FileSystemTraversal.PRE_ORDER) {
            buildPreOrderWithDepth(node, traversalResult, 0);
        }

        else if(traversalOrder == FileSystemTraversal.POST_ORDER) {
            buildPostOrderWithDepth(node, traversalResult, 0);
        }

        return traversalResult;
    }

    private void buildPreOrderWithDepth(FileSystemNode<T> node, Map<FileSystemNode<T>, Integer> traversalResult, int depth) {
        traversalResult.put(node, depth);

        for(FileSystemNode<T> child : node.getChildren()) {
            buildPreOrderWithDepth(child, traversalResult, depth + 1);
        }
    }

    private void buildPostOrderWithDepth(FileSystemNode<T> node, Map<FileSystemNode<T>, Integer> traversalResult, int depth) {
        for(FileSystemNode<T> child : node.getChildren()) {
            buildPostOrderWithDepth(child, traversalResult, depth + 1);
        }

        traversalResult.put(node, depth);
    }

    public String toString() {
        /*
        We're going to assume a pre-order traversal by default
         */

        String stringRepresentation = "";

        if(root != null) {
            stringRepresentation = build(FileSystemTraversal.PRE_ORDER).toString();

        }

        return stringRepresentation;
    }

    public String toStringWithDepth() {
        /*
        We're going to assume a pre-order traversal by default
         */

        String stringRepresentation = "";

        if(root != null) {
            stringRepresentation = buildWithDepth(FileSystemTraversal.PRE_ORDER).toString();
        }

        return stringRepresentation;
    }
}