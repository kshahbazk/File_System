package com.cs149;

import java.util.ArrayList;

public class IndexBlock {
	private int data;
	private IndexBlock next;
	private int number;
	private ArrayList<DataBlock> ptrDataBlocks = new ArrayList<>();

	public IndexBlock(int number)
		   {
		      this.data = data;
		      this.next = next;
		      this.number = number;
		      this.ptrDataBlocks = ptrDataBlocks;
		   }
	
	public ArrayList<DataBlock> getDataBlocks(){
		return ptrDataBlocks;
	}
	
	public void setDataBlocks(ArrayList<DataBlock> dbs){
		ptrDataBlocks = dbs;
	}
}
