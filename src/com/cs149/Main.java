package com.cs149;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;

public class Main {


	public static HashMap<String, IndexBlock> map = new HashMap<String, IndexBlock>();
	
	public static ArrayList<DataBlock> proxyDisk = new ArrayList<DataBlock>();
	
	public static LinkedList<DataBlock> unusedDataBlocks = new LinkedList<DataBlock>();
	
	public static LinkedList<IndexBlock> unusedIndexBlocks = new LinkedList<IndexBlock>();
	
	public Directory current = new Directory("root");
	
    public static void main(String[] args) {

    	//Set-up
    	for(int i = 0; i < 100; i++){
    		proxyDisk.add(new DataBlock(i));
    	}
    	
    }
    
    /*
     * 1. currentd - report name of current directory
     * Implemented by Benjamin Liu
     * */
    
    public void currentd(){
    	System.out.println(current.getName());
    }
    
    /*
     * 3. maked dname - creates a directory called "name" 
     * Implemented by Benjamin Liu
     * */
    
    public void maked(String dname){
    	Directory dir = new Directory(dname);
    	current.addToDirectory(dir);
    }
    
    /*
     * 5.extendf file size - make "file" extended by "size" bytes
     * Implemented by Benjamin Liu
     * */
    public void extendf(String file, int size){
    	
    	IndexBlock iblock = map.get(file);
    	ArrayList<DataBlock> datablocks = iblock.getDataBlocks();
    	int amountOfBlocks = size / DataBlock.size;
    	
    	for(int  i = 0; i < amountOfBlocks; i++){
    		
    		datablocks.add(unusedDataBlocks.get(i));
    		unusedDataBlocks.remove(i);
    		
    	}
    	
    	iblock.setDataBlocks(datablocks);
    	
    	map.put(file, iblock);
    }
    
    /*
     * 7. deletefd name - delete file or directory with the given name
     * Implemented by Benjamin Liu
     * */
    public void deletefd (String name){
    	//Remove file from directory
    	current.deleteFromDirectory(name);
    	
    	IndexBlock iblock = map.get(name);
    	//Add iblock to list of free iblocks
    	unusedIndexBlocks.add(iblock);
    	
    	ArrayList<DataBlock> datablocks = iblock.getDataBlocks();
    	
    	for(int i = 0; i < datablocks.size(); i++){
    		//Add datablocks to list of free datablocks
    		unusedDataBlocks.add(datablocks.get(i));
    	}
    	
    	map.remove(name);
    }
    
    /*
     * 9. listf file - list addresses of blocks allocated to file
     *  Implemented by Benjamin Liu
     * */
    
    public void listf(String file){
    	IndexBlock iblock = map.get(file);
    	ArrayList<DataBlock> dataBlocks = iblock.getDataBlocks();
    	
    	for(int i = 0; i < dataBlocks.size(); i++){
    		System.out.print(dataBlocks.get(i).getNumber() + " ");
    	}
    }
    
    /*
     * 11. movf file directory - move "file" from current directory to "directory"
     *  Implemented by Benjamin Liu
     * */
    public void movf(String file, Directory dir){
    	
    }
    
    /*
     * dumpfs - print out contents of proxy disk
     *  Implemented by Benjamin Liu
     * */
    public void dumpfs(){
    	
    }
    
}
