package com.suml.nosql.tablepool;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.client.HTableInterface;
import org.apache.hadoop.hbase.client.HTablePool;

public class TablePooling {
	public static void main(String[] args) {
		Configuration conf=HBaseConfiguration.create();
		HTablePool pool=new HTablePool(conf, Integer.MAX_VALUE);
		HTableInterface[] tableInterfaces=new HTableInterface[10];
		tableInterfaces[1]=pool.getTable("UsingJavaApi");
		
	}
}
