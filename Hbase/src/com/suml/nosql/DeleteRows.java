package com.suml.nosql;

import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.client.Delete;
import org.apache.hadoop.hbase.client.HTable;

public class DeleteRows {
	public static void main(String[] args) throws IOException {

		Configuration conf=HBaseConfiguration.create();
		HTable table=new HTable(conf, "UsingJavaApi");
		Delete delete=new Delete("row2".getBytes());
		table.delete(delete);
		System.out.println("Deleted successfully");
		
	}

}
