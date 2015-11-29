package com.cs149;

public class DataBlock {
	   private int data;
	   public DataBlock next;
	   private int number;
	   public static int size = 100;
	   
	   public DataBlock(int number)
	   {
	      this.number =number;
	   }
	   
	   public int getNumber(){
		   return number;
	   }
	   
	   public int getData(){
		   return data;
	   }
	   
	   public void setData(int data){
		   this.data = data;
	   }
}
