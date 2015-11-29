package com.cs149;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Scanner;

public class Main {

    private String User;


	public static HashMap<String, IndexBlock> map = new HashMap<String, IndexBlock>();
	
	public static ArrayList<DataBlock> proxyDisk = new ArrayList<DataBlock>();
	
	public static LinkedList<DataBlock> unusedDataBlocks = new LinkedList<DataBlock>();
	
	public static LinkedList<IndexBlock> unusedIndexBlocks = new LinkedList<IndexBlock>();
	
	public static Directory current = new Directory("root");
	
    public static void main(String[] args) {

    	//Initialize our Proxy Disk
    	for(int i = 0; i < 100; i++){
    		proxyDisk.add(new DataBlock(i));
    	}

		String typing= "";
		System.out.println("Enter your command or enter quit to exit");
		Scanner sc = new Scanner(System.in);
        Boolean on = true;
		while(sc.hasNextLine() && on){

			typing = sc.nextLine();

			if (typing.contains("currentd")) {
				currentd();
			}

			if (typing.contains("chdir")) {

				int indexOfEmptyString = typing.indexOf(" ");
				String command = typing.substring(indexOfEmptyString);
				chdir(command);
			}

			if (typing.contains("maked")) {
				int indexOfEmptyString = typing.indexOf(" ");
				String command = typing.substring(indexOfEmptyString);
				maked(command);
			}

			if (typing.contains("createf")) {
				int indexOfEmptyString = typing.indexOf(" ");
				String command = typing.substring(indexOfEmptyString);
				createf(command);
			}

			if (typing.contains("extendf")) {
				int indexOfFirstEmptyString = typing.indexOf(" ");
				String firstCommandLine = typing.substring(indexOfFirstEmptyString);
				int indexOfSecondEmptyString = firstCommandLine.indexOf(" ");
				String firstCommand = typing.substring(indexOfFirstEmptyString, indexOfSecondEmptyString);
				String secondCommandStr = typing.substring(indexOfSecondEmptyString);
				int secondCommand = Integer.parseInt(secondCommandStr);

				extendf(firstCommand,secondCommand);
			}

			if (typing.contains("deletefd")) {
				int indexOfEmptyString = typing.indexOf(" ");
				String command = typing.substring(indexOfEmptyString);
				deletefd(command);
			}

			if (typing.contains("listf")) {
				int indexOfEmptyString = typing.indexOf(" ");
				String command = typing.substring(indexOfEmptyString);
				listf(command);
			}

			if (typing.contains("movf")) {
				int indexOfFirstEmptyString = typing.indexOf(" ");
				String firstCommandLine = typing.substring(indexOfFirstEmptyString);
				int indexOfSecondEmptyString = firstCommandLine.indexOf(" ");
				String firstCommand = typing.substring(indexOfFirstEmptyString, indexOfSecondEmptyString);
				String secondCommand = typing.substring(indexOfSecondEmptyString);

				movf(firstCommand,secondCommand);
			}

			if (typing.contains("dumpfs")) {
				dumpfs();
			}

			if (typing.equals("quit")) {

				on = false;
			}

//			if (typing.contains("currentd")) {
//				currentd();
//			}
//
//			if (typing.contains("currentd")) {
//				currentd();
//			}
//
//			if (typing.contains("currentd")) {
//				currentd();
//			}
//
//			if (typing.contains("currentd")) {
//				currentd();
//			}

		}
		System.out.println("System is off");
    }




    
    /*
     * 1. currentd - report name of current directory
     * Implemented by Benjamin Liu
     * */
    
    public static void currentd(){
    	System.out.println(current.getName());
    }

	 /*
     * 2. chdir directory - change current directory to "directory"
     * Implemented by Shahbaz Khan
     * */

	public static void chdir(String directory){
		 current.setName(directory);
	 }

    
    /*
     * 3. maked dname - creates a directory called "name" 
     * Implemented by Benjamin Liu
     * */
    
    public static void maked(String dname){
    	Directory dir = new Directory(dname);
    	current.addToDirectory(dir);
    }

	 /*
     * createf file - creates a file called "file" (allocates space, name, permissions etc)
     * Implemented by Shahbaz Khan
     * */

	public static void createf(String file){
		File f = new File(file);
		current.addToDirectory(f);
	}
    
    /*
     * 5.extendf file size - make "file" extended by "size" bytes
     * Implemented by Benjamin Liu
     * */
    public static void extendf(String file, int size){
    	
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
    public static void deletefd (String name){
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
    
    public static void listf(String file){
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
    public static void movf(String file, String dir){
    	
    }
    
    /*
     * dumpfs - print out contents of proxy disk
     *  Implemented by Benjamin Liu
     * */
    public static void dumpfs(){
    	
    }
    
}