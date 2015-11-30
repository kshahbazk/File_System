package com.cs149;

import java.util.*;

public class Main {

	private static String owner = "admin";

	public static FileSystem<String> fileSystem = new FileSystem<String>();

	public static FileSystemNode<String> root = new FileSystemNode<>("root");

	public static FileSystemNode<String> current = new FileSystemNode<>("");

	public static HashMap<String, IndexBlock> map = new HashMap<String, IndexBlock>();

	public static ArrayList<DataBlock> proxyDisk = new ArrayList<DataBlock>();

	public static LinkedList<DataBlock> unusedDataBlocks = new LinkedList<DataBlock>();

	public static LinkedList<IndexBlock> unusedIndexBlocks = new LinkedList<IndexBlock>();

	public static void main(String[] args) {

		fileSystem.setRoot(root);
		current = root;
		// Initialize our Proxy Disk
		for (int i = 0; i < 100; i++) {
			proxyDisk.add(new DataBlock(i));
			unusedIndexBlocks.add(new IndexBlock(i));
			unusedDataBlocks.add(proxyDisk.get(i));
		}
		prompt();
	}

	/*
	 * 1. currentd - report name of current directory Implemented by Benjamin
	 * Liu
	 */

	public static void currentd() {
		System.out.println(current.getData());
	}

	/*
	 * 2. chdir directory - change current directory to "directory" Implemented
	 * by Shahbaz Khan
	 */

	public static void chdir(String directory) {

		if (fileSystem.exists(new FileSystemNode<>(directory))) {

			current = fileSystem.find(new FileSystemNode(directory));

		}

	}

	/*
	 * 3. maked dname - creates a directory called "name" Implemented by Shahbaz
	 * Khan
	 */

	public static void maked(String dname) {

        FileSystemNode<String> toAdd = new FileSystemNode<>(dname);

		if (current.getData().equals("root")) {
            toAdd.setOwner(owner);
            toAdd.setType("Directory");
			root.addChild(toAdd);
		}
        else
        {
            toAdd.setType("Directory");
            toAdd.setOwner(owner);
            current.addChild(toAdd);

            if(!fileSystem.exists(current)){
                root.addChild(current);
            }
        }

        IndexBlock iblock = unusedIndexBlocks.get(0);
        ArrayList<DataBlock> datablocks = new ArrayList<>();

        int numBlocks = ( 300 / DataBlock.size) + 1;

        for(int i = 0; i < numBlocks; i++){
            datablocks.add(unusedDataBlocks.get(i));
            //Write to datablock
            datablocks.get(i).setData(12345);
            unusedDataBlocks.remove(i);
        }
        iblock.setDataBlocks(datablocks);
        updateProxyDisk(datablocks);
        map.put(dname, iblock);

        unusedDataBlocks.remove(0);

	}

	/*
	 * 4. createf file - creates a file called "file" (allocates space, name,
	 * permissions etc) Implemented by Benjamin Liu
	 */

	public static void createf(String file) {
		
		//TODO  add file to directory
		
        FileSystemNode<String> toAdd = new FileSystemNode<>(file);

		if (current.getData().equals("root")) {
            toAdd.setOwner(owner);
            toAdd.setType("File");
            toAdd.setName(file);
			root.addChild(toAdd);
		}
        else
        {
            toAdd.setType("File");
            toAdd.setOwner(owner);
            toAdd.setName(file);
            current.addChild(toAdd);

            if(!fileSystem.exists(current)){
                root.addChild(current);
            }
        }

		
		
		IndexBlock iblock = unusedIndexBlocks.get(0);
		ArrayList<DataBlock> datablocks = new ArrayList<>();
		
		int numBlocks = ( toAdd.getSize() / DataBlock.size);
		
		for(int i = 0; i < numBlocks; i++){
			datablocks.add(unusedDataBlocks.get(i));
			//Write to datablock
			datablocks.get(i).setData(i);
			unusedDataBlocks.remove(i);
		}
		iblock.setDataBlocks(datablocks);
		updateProxyDisk(datablocks);
		map.put(file, iblock);
		
		unusedIndexBlocks.remove(0);
	}

	/*
	 * 5.extendf file size - make "file" extended by "size" bytes Implemented by
	 * Benjamin Liu
	 */
	public static void extendf(String file, int size) {
		if(map.containsKey(file)){
		IndexBlock iblock = map.get(file);
		ArrayList<DataBlock> datablocks = iblock.getDataBlocks();
		
		int amountOfBlocks = size / DataBlock.size;

		for (int i = 0; i < amountOfBlocks; i++) {

			datablocks.add(unusedDataBlocks.get(i));
			//Write to datablock
			datablocks.get(i).setData(i);
			unusedDataBlocks.remove(i);

		}

		iblock.setDataBlocks(datablocks);
		updateProxyDisk(datablocks);
		map.put(file, iblock);
		
		}else{
			System.out.println("File not found");
		}
	}

	/*
	 * 6. truncf file size - make "file" shortened by "size" bytes Implemented
	 * by Shahbaz Khan
	 */

	public static void truncf(String file, int size) {

	}

	/*
	 * 7. deletefd name - delete file or directory with the given name
	 * Implemented by Benjamin Liu
	 */
	public static void deletefd(String name) {
		// //Remove file from directory
		
		if(map.containsKey(name)){
		
		//FileSystemNode<String> toDel = new FileSystemNode<>(name);
		List<FileSystemNode<String>> ls = current.getChildren();
		for(int i = 0; i < ls.size(); i++){
			if(ls.get(i).getName().equals(name)){
				current.removeChildAt(i);
			}
		}
			
		 IndexBlock iblock = map.get(name);
		 //Add iblock to list of free iblocks
		 unusedIndexBlocks.add(iblock);
		
		 ArrayList<DataBlock> datablocks = iblock.getDataBlocks();
		
		for(int i = 0; i < datablocks.size(); i++){
		//Add datablocks to list of free datablocks
		datablocks.get(i).setData(0);
		unusedDataBlocks.add(datablocks.get(i));
		}
		updateProxyDisk(datablocks);
	    map.remove(name);
		}else{
			System.out.println("File not found");
		}
	}

	/*
	 * 8. listd directory - list directories and files starting at "directory"
	 * and if "directory" not stated, list the current directory Implemented by
	 * Shahbaz Khan
	 */
	public static void listd(String directory) {

        if(fileSystem.exists(new FileSystemNode(directory))){
            FileSystemNode<String> toList = fileSystem.find(new FileSystemNode(directory));
            List<FileSystemNode<String>> children = toList.getChildren();
            for(FileSystemNode<String> child : children){
                System.out.println(child.getData());
            }
        }
        else {
            List<FileSystemNode<String>> children = current.getChildren();
            for(FileSystemNode<String> child : children){
                System.out.println(child.getData());
            }
        }


	}

	/*
	 * 9. listf file - list addresses of blocks allocated to file Implemented by
	 * Benjamin Liu
	 */

	public static void listf(String file) {
		IndexBlock iblock = map.get(file);
		ArrayList<DataBlock> dataBlocks = iblock.getDataBlocks();

		for (int i = 0; i < dataBlocks.size(); i++) {
			System.out.print(dataBlocks.get(i).getNumber() + " ");
		}
		System.out.println("");
	}

	/*
	 * 10. sizef file - print out the size of "file" both in number of blocks
	 * and in number of bytes Implemented by Shahbaz Khan
	 */

	public static void sizef(String file) {

	}

	/*
	 * 11. movf file directory - move "file" from current directory to
	 * "directory" Implemented by Benjamin Liu
	 */

	public static void movf(String file, String dir) {
		
		deletefd(file);
		chdir(dir);
		createf(file);
		
	}

	/*
	 * 12. listfb - list addresses (array index) if free blocks Implemented by
	 * Benjamin Liu
	 */

	public static void listfb() {
		for (int i = 0; i < unusedDataBlocks.size(); i++) {
			System.out.println(unusedDataBlocks.get(i).getNumber());
		}
	}

	/*
	 * 13. dumpfs - print out contents of proxy disk Implemented by Benjamin Liu
	 */
	public static void dumpfs() {
		//System.out.println(proxyDisk.get(1).getData());
		for (int i = 0; i < proxyDisk.size(); i++) {
			System.out.println(proxyDisk.get(i).getData() + " ");
		}
	}

	/*
	 * 14. formatd - erase proxy disk and create a "root" directory Implemented
	 * by Shahbaz Khan
	 */
	public static void formatd() {
        FileSystem<String> newfileSystem = new FileSystem<String>();
        FileSystemNode<String> newroot = new FileSystemNode<>("root");
        newfileSystem.setRoot(newroot);
        current=newroot;
        fileSystem = newfileSystem;

	}
	
	public static void updateProxyDisk(ArrayList<DataBlock> datablocks){
		
		for(int i = 0; i < datablocks.size();i++){
			proxyDisk.set(datablocks.get(i).getNumber(), datablocks.get(i));
		}
		
	}

	public static void prompt() {

		String typing = "";
		System.out.println("Enter your command or enter quit to exit");
		Scanner sc = new Scanner(System.in);
		Boolean on = true;
		while (sc.hasNextLine() && on) {

			typing = sc.nextLine();
			String[] input = typing.split("\\s+");
			// 1
			if (typing.contains("currentd")) {
				currentd();
			}

			// 2
			else if (typing.contains("chdir")) {

				chdir(input[1]);
			}

			// 3
			else if (typing.contains("maked")) {

				maked(input[1]);
			}

			// 4
			else if (typing.contains("createf")) {

				createf(input[1]);
			}

			// 5
			else if (typing.contains("extendf")) {

				int j = Integer.parseInt(input[2]);
				extendf(input[1], j);
			}

			// 6
			else if (typing.contains("truncf")) {

				int j = Integer.parseInt(input[2]);

				truncf(input[1], j);
			}

			// 7
			else if (typing.contains("deletefd")) {

				deletefd(input[1]);
			}
			
			// 12
			else if (typing.equals("listfb")) {
				listfb();
			}

			// 8
			else if (typing.contains("listd")) {
	
				listd(input[1]);
			}

			// 9
			else if (typing.contains("listf")) {

				listf(input[1]);
			}

			// 10
			else if (typing.contains("sizef")) {

				sizef(input[1]);
			}

			// 11
			else if (typing.contains("movf")) {
				
				movf(input[1], input[2]);
			}

			// 13
			else if (typing.contains("dumpfs")) {
				dumpfs();
			}

			// 14
			else if (typing.contains("formatd")) {
				formatd();
			}

			// Quit
			else if (typing.equals("quit")) {

				on = false;
				return;
			}else{
				
				System.out.println("Command not found");
				prompt();
				
			}
		}
		System.out.println("System is off");
	}

}