package com.suml.nosql;

import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.HColumnDescriptor;
import org.apache.hadoop.hbase.HTableDescriptor;
import org.apache.hadoop.hbase.client.HBaseAdmin;

public class CreateTable {
	public static void main(String[] args) throws IOException {
	Configuration conf=HBaseConfiguration.create();
	//conf.set("fs.default.name", "192.168.0.11:9000");
	//conf.set("hbase.rootdir", "192.168.0.11:9000/hbasedata");
	conf.set("hbase.zookeeper.quorum", "192.168.0.10");
	HBaseAdmin admin=new HBaseAdmin(conf);
	HTableDescriptor tableDescriptor=new HTableDescriptor();
	HColumnDescriptor columnDescriptor1=new HColumnDescriptor("demographic");
	HColumnDescriptor columnDescriptor2=new HColumnDescriptor("binary");

	tableDescriptor.setName("UsingJavaApi".getBytes());
	tableDescriptor.addFamily(columnDescriptor1);
	tableDescriptor.addFamily(columnDescriptor2);
	admin.createTable(tableDescriptor);
	admin.close();
	}
}
