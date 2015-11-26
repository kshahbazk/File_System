package com.cs149;

public class DataBlock {
	   private int data;
	   private DataBlock next;
	   private int number;

	   public DataBlock(int data, DataBlock next, int number)
	   {
	      this.data = data;
	      this.next = next;
	      this.number =number;
	   }
}
