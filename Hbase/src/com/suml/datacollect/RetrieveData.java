package com.suml.datacollect;

import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.client.HTable;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.client.ResultScanner;
import org.apache.hadoop.hbase.client.Scan;
import org.apache.hadoop.hbase.filter.FirstKeyOnlyFilter;

public class RetrieveData {
	public static void main(String[] args) throws IOException {

		Configuration conf=HBaseConfiguration.create();
		conf.set("hbase.zookeeper.quorum", "54.235.68.112");
		conf.set("hbase.zookeeper.property.clientPort","2181");
		//	conf.set("hbase.master", "ec2-54-235-68-112.compute-1.amazonaws.com:60000");
		HTable table1=new HTable(conf, "ADDRESS");
		HTable table2=new HTable(conf, "CITIZEN");
		HTable table3=new HTable(conf, "MOTOR_VEHICLE");
		HTable table4=new HTable(conf, "TELEPHONE");
		HTable table11=new HTable(conf, "CITIZEN_ADDRESS");
		HTable table5=new HTable(conf, "CALL_DETAILS");
		HTable table6=new HTable(conf, "CONNECTION_GRAPH");
		HTable table7=new HTable(conf, "AUDIOWORD");
		HTable table8=new HTable(conf, "GEO_COORDINATES");
		HTable table9=new HTable(conf, "SUSPECT_DETAILS");
		HTable table10=new HTable(conf, "CELLID_GEO_DETAILS");
		
		HTable table12=new HTable(conf, "ADDRESS");
		
		ResultScanner scanner = table1.getScanner(new Scan().setFilter(new FirstKeyOnlyFilter()));
		
		for (Result result : scanner) {
			System.out.println("ADDRESS: "+new String(result.getValue("ADDRESS".getBytes(), "ADDRESS".getBytes())));
			System.out.println("ADDRESS: "+new String(result.getValue("ADDRESS".getBytes(), "CITY".getBytes())));
			System.out.println("ADDRESS: "+new String(result.getValue("ADDRESS".getBytes(), "STATE".getBytes())));
			System.out.println("ADDRESS: "+new String(result.getValue("ADDRESS".getBytes(), "PIN_CODE".getBytes())));
			System.out.println("ADDRESS: "+new String(result.getValue("ADDRESS".getBytes(), "STREET".getBytes())));
			System.out.println("ADDRESS: "+new String(result.getValue("ADDRESS".getBytes(), "COUNTRY".getBytes())));
			System.out.println("ADDRESS: "+new String(result.getValue("ADDRESS".getBytes(), "ADDRESS_ID".getBytes())));
			System.out.println("ADDRESS: "+new String(result.getValue("ADDRESS".getBytes(), "DATE_OF_RECORDING".getBytes())));
		//	System.out.println("ADDRESS: "+new String(result.getValue("ColumnFamily".getBytes(), "Column".getBytes())));
		}
		


		}
	}