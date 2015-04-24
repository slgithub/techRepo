package com.suml.nosql.all;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.HColumnDescriptor;
import org.apache.hadoop.hbase.HTableDescriptor;
import org.apache.hadoop.hbase.client.Delete;
import org.apache.hadoop.hbase.client.Get;
import org.apache.hadoop.hbase.client.HBaseAdmin;
import org.apache.hadoop.hbase.client.HTable;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.client.Result;

public class AllHbase {
	
	public static void main(String[] args) throws Exception {
		Configuration configuration=HBaseConfiguration.create();
		configuration.set("hbase.zookeeper.quorum", "192.168.0.10");
		CRUD crud=new CRUD();
		crud.create(configuration);
		//crud.insert(configuration);
		//crud.delete(configuration);
		//crud.retrieve(configuration);
		//crud.drop(configuration);
	}

}
class CRUD{
	HBaseAdmin admin;
	void create(Configuration conf) throws Exception{
		HBaseAdmin admin = new HBaseAdmin(conf);
		HTableDescriptor tableDescriptor=new HTableDescriptor("new");
		tableDescriptor.addFamily(new HColumnDescriptor("cf1"));
		tableDescriptor.addFamily(new HColumnDescriptor("cf2"));
		tableDescriptor.addFamily(new HColumnDescriptor("cf3"));
		admin.createTable(tableDescriptor);

	}
	void insert(Configuration conf) throws Exception{
		HTable hTable=new HTable(conf, "new");
		Put put=new Put("row1".getBytes());
		put.add("cf1".getBytes(), "name".getBytes(), "abhishek".getBytes());
		hTable.put(put);
		hTable.close();

	}
	void delete(Configuration conf) throws Exception{
		HTable hTable=new HTable(conf, "new");
		hTable.delete(new Delete("row1".getBytes()));
		hTable.close();

	}
	void retrieve(Configuration conf) throws Exception{
		HTable hTable=new HTable(conf, "new");
		Get get=new Get("row1".getBytes());
		Result result=hTable.get(get);
		System.out.println(new String(result.getValue("cf1".getBytes(),"name".getBytes())).toString());
		

	}
	void drop(Configuration conf) throws Exception{
		HBaseAdmin hBaseAdmin=new HBaseAdmin(conf);
		hBaseAdmin.disableTable("abhi".getBytes());
		hBaseAdmin.deleteTable("abhi".getBytes());
	}
}