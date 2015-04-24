package com.suml.nosql;

import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.client.HTable;
import org.apache.hadoop.hbase.client.Put;

public class simpleTest {

	public static void main(String[] args) throws IOException {
		Configuration config=HBaseConfiguration.create();
		config.set("hbase.zookeeper.quorum", "192.168.0.11");
		HTable table=new HTable(config,"UsingJavaApi");
		table.setAutoFlush(false);
		table.setWriteBufferSize(1024*1024*12);
		for(int i=1;i<=10000;i++){
		Put put=new Put(new String("row"+i).getBytes());
		put.add("cf".getBytes(), "id".getBytes(), ("actual value"+i).getBytes());
		table.put(put);
		}
		System.out.println("Flush started");
		table.flushCommits();
		table.close();
		
		System.out.println("task completed");
	}
}
