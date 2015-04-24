package com.suml.nosql;

import java.io.IOException;
import java.util.Scanner;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.client.HTable;
import org.apache.hadoop.hbase.client.Put;

public class InsertUpdate {
	public static void main(String[] args) throws IOException {
		Configuration conf=HBaseConfiguration.create();
		conf.set("hbase.zookeeper.quorum", "192.168.0.11");
		HTable table=new HTable(conf,"UsingJavaApi");
		String s="n";
		while(s.equalsIgnoreCase("n"))
		{
		System.out.println("Enter Row Identifier:");
		Put put=new Put(new Scanner(System.in).next().getBytes());
		System.out.println("Enter Name: ");
		put.add("demographic".getBytes(), "Name".getBytes(),new Scanner(System.in).next().getBytes());
		System.out.println("Enter Gender: ");
		put.add("demographic".getBytes(), "Gender".getBytes(), new Scanner(System.in).next().getBytes());
		System.out.println("Enter Age: ");
		put.add("demographic".getBytes(), "age".getBytes(), new Scanner(System.in).next().getBytes());
		table.put(put);
		table.flushCommits();
		
		System.out.println("Task completed. Do you want to Quit(Y / N):?");
		s=new Scanner(System.in).next();
		}
		table.close();
		
		System.out.println("Done!!");
	}
}
