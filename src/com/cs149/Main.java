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


    public static DirectoryOrFile<String> current = new DirectoryOrFile<String>("");

    public static DirectoryOrFile<String> root = new DirectoryOrFile<String>("root");
	
    public static void main(String[] args) {

        current=root;
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

			// 1
			if (typing.contains("currentd")) {
				currentd();
			}

			// 2
			if (typing.contains("chdir")) {

				int indexOfEmptyString = typing.indexOf(" ");
				String command = typing.substring(indexOfEmptyString);
				chdir(command);
			}

			// 3
			if (typing.contains("maked")) {
				int indexOfEmptyString = typing.indexOf(" ");
				String command = typing.substring(indexOfEmptyString);
				maked(command);
			}

			// 4
			if (typing.contains("createf")) {
				int indexOfEmptyString = typing.indexOf(" ");
				String command = typing.substring(indexOfEmptyString);
				createf(command);
			}

			// 5
			if (typing.contains("extendf")) {
				int indexOfFirstEmptyString = typing.indexOf(" ");
				String firstCommandLine = typing.substring(indexOfFirstEmptyString);
				int indexOfSecondEmptyString = firstCommandLine.indexOf(" ");
				String firstCommand = typing.substring(indexOfFirstEmptyString, indexOfSecondEmptyString);
				String secondCommandStr = typing.substring(indexOfSecondEmptyString);
				int secondCommand = Integer.parseInt(secondCommandStr);

				extendf(firstCommand,secondCommand);
			}

			// 6
			if (typing.contains("truncf")) {
				int indexOfFirstEmptyString = typing.indexOf(" ");
				String firstCommandLine = typing.substring(indexOfFirstEmptyString);
				int indexOfSecondEmptyString = firstCommandLine.indexOf(" ");
				String firstCommand = typing.substring(indexOfFirstEmptyString, indexOfSecondEmptyString);
				String secondCommandStr = typing.substring(indexOfSecondEmptyString);
				int secondCommand = Integer.parseInt(secondCommandStr);

				truncf(firstCommand,secondCommand);
			}

			// 7
			if (typing.contains("deletefd")) {
				int indexOfEmptyString = typing.indexOf(" ");
				String command = typing.substring(indexOfEmptyString);
				deletefd(command);
			}

			// 8
			if (typing.contains("listd")) {
				int indexOfEmptyString = typing.indexOf(" ");
				String command = typing.substring(indexOfEmptyString);
				listd(command);
			}

			// 9
			if (typing.contains("listf")) {
				int indexOfEmptyString = typing.indexOf(" ");
				String command = typing.substring(indexOfEmptyString);
				listf(command);
			}

			// 10
			if (typing.contains("sizef")) {
				int indexOfEmptyString = typing.indexOf(" ");
				String command = typing.substring(indexOfEmptyString);
				sizef(command);
			}

			// 11
			if (typing.contains("movf")) {
				int indexOfFirstEmptyString = typing.indexOf(" ");
				String firstCommandLine = typing.substring(indexOfFirstEmptyString);
				int indexOfSecondEmptyString = firstCommandLine.indexOf(" ");
				String firstCommand = typing.substring(indexOfFirstEmptyString, indexOfSecondEmptyString);
				String secondCommand = typing.substring(indexOfSecondEmptyString);

				movf(firstCommand,secondCommand);
			}

			// 12
			if (typing.contains("listfb")) {
				listfb();
			}

			// 13
			if (typing.contains("dumpfs")) {
				dumpfs();
			}

			// 14
			if (typing.contains("formatd")) {
				formatd();
			}

			// Quit
			if (typing.equals("quit")) {

				on = false;
				return;
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
//		ArrayList<Object> contents = current.getContents();
//
//        for(Object x: contents)
//        {
//            if (x.getClass().getTypeName()=="DirOrFile")
//            {
//
//            }
//        }
	 }

    
    /*
     * 3. maked dname - creates a directory called "name" 
     * Implemented by Benjamin Liu
     * */
    
    public static void maked(String dname){
//    	DirOrFile dir = new DirOrFile(dname);
//    	current.addToDirectory(dir);
    }

	 /*
     * 4. createf file - creates a file called "file" (allocates space, name, permissions etc)
     * Implemented by Shahbaz Khan
     * */

	public static void createf(String file){
//		File f = new File(file);
//		current.addToDirectory(f);
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
     * 6. truncf file size - make "file" shortened by "size" bytes
     * Implemented by Shahbaz Khan
     * */

	public static void truncf(String file, int size){

	}

    
    /*
     * 7. deletefd name - delete file or directory with the given name
     * Implemented by Benjamin Liu
     * */
    public static void deletefd (String name){
//    	//Remove file from directory
//    	current.deleteFromDirectory(name);
//
//    	IndexBlock iblock = map.get(name);
//    	//Add iblock to list of free iblocks
//    	unusedIndexBlocks.add(iblock);
//
//    	ArrayList<DataBlock> datablocks = iblock.getDataBlocks();
//
//    	for(int i = 0; i < datablocks.size(); i++){
//    		//Add datablocks to list of free datablocks
//    		unusedDataBlocks.add(datablocks.get(i));
//    	}
//
//    	map.remove(name);
    }

	/*
    * 8. listd directory - list directories and files starting at "directory"
    * and if "directory" not stated, list the current directory
    * Implemented by Shahbaz Khan
    */
	public static void listd(String directory){

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
     *  10. sizef file - print out the size of "file" both in number of blocks and in number of bytes
     *  Implemented by Shahbaz Khan
     * */

	public static void sizef(String file){

	}
    
    /*
     * 11. movf file directory - move "file" from current directory to "directory"
     *  Implemented by Benjamin Liu
     * */

    public static void movf(String file, String dir){
//    	ArrayList<Object> contents = current.getContents();
//
//    	if(contents.contains(file)){
//	    	for(int i = 0; i < contents.size(); i++){
//	    		if(contents.get(i).equals(dir)){
//	    			contents.remove(i);
//	    			chdir(dir);
//	    			current.addToDirectory(file);
//	    			return;
//	    		}
//	    	}
//    	}else{System.out.println("File not found");}
    }

	/*
     * 12. listfb - list addresses (array index)  if free blocks
     *  Implemented by Shahbaz Khan
     * */

	public static void listfb()
	{

	}


    
    /*
     * 13. dumpfs - print out contents of proxy disk
     *  Implemented by Benjamin Liu
     * */
    public static void dumpfs(){
    	for(int i = 0; i < proxyDisk.size(); i++){
    		System.out.println(proxyDisk.get(i).getData() + " ");
    	}
    }


	/*
     * 14. formatd - erase proxy disk and create a "root" directory
     *  Implemented by Shahbaz Khan
     * */
	public static void formatd(){
//     proxyDisk.clear();
//		DirOrFile root = new DirOrFile("root");
//        current.addToDirectory(root);

	}
    
}