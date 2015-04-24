package com.suml.nosql;

import java.io.IOException;
import java.util.Scanner;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.client.Get;
import org.apache.hadoop.hbase.client.HTable;
import org.apache.hadoop.hbase.client.Result;

public class RetrieveData {
	public static void main(String[] args) throws IOException {

		Configuration conf=HBaseConfiguration.create();
		conf.set("hbase.zookeeper.quorum", "192.168.0.11");
		HTable table=new HTable(conf, "UsingJavaApi");
		System.out.print("Enter the Row ID to Fetch: ");
		Get get=new Get(new Scanner(System.in).next().getBytes());
		Result result=new Result();
		result=table.get(get);
		System.out.println("Name: "+new String(result.getValue("demographic".getBytes(), "Name".getBytes())));
		System.out.println("Gender: "+new String(result.getValue("demographic".getBytes(), "Gender".getBytes())));
		System.out.println("Age: "+new String(result.getValue("demographic".getBytes(), "age".getBytes())));
		System.out.println("Data populated successfully");
	}
}