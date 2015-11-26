package com.cs149;

import java.util.ArrayList;

public class IndexBlock {
	private int data;
	private IndexBlock next;
	private int number;
	private ArrayList<DataBlock> ptrDataBlocks = new ArrayList<>();

	public IndexBlock(int data, IndexBlock next, int number, ArrayList<DataBlock> ptrDataBlocks)
		   {
		      this.data = data;
		      this.next = next;
		      this.number = number;
		      this.ptrDataBlocks = ptrDataBlocks;
		   }
}
