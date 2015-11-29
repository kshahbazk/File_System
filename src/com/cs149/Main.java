package com.cs149;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;

public class Main {

	public HashMap<String, IndexBlock> map = new HashMap<String, IndexBlock>();
	public ArrayList<DataBlock> proxyDisk = new ArrayList<DataBlock>();
	public LinkedList<DataBlock> unusedDataBlocks = new LinkedList<DataBlock>();
	public LinkedList<IndexBlock> unusedIndexBlocks = new LinkedList<IndexBlock>();
	
	public Directory current = new Directory("root");
	
    public static void main(String[] args) {
    	
    	//Set-up
    	
    }
    
    /*
     * 1. currentd
     * Implemented by Benjamin Liu
     * */
    
    public void currentd(){
    	System.out.println(current.getName());
    }
}
