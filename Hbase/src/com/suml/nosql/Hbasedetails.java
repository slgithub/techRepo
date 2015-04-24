package com.suml.nosql;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.ClusterStatus;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.client.HBaseAdmin;
import org.apache.hadoop.hbase.client.HConnection;

public class Hbasedetails {
	
	public static void main(String[] args) throws IOException {
		
	Configuration conf=HBaseConfiguration.create();
	HBaseAdmin admin=new HBaseAdmin(conf);
	HConnection connection=admin.getConnection();
//	Configuration configuration=connection.getConfiguration();
//	OutputStream out=System.out;
//	configuration.writeXml(out);
	ClusterStatus status=admin.getClusterStatus();
	System.out.println("configuration: "+admin.getConfiguration());
	System.out.println("Connection configuration(this): "+connection.getConfiguration());
	System.out.println("Master: "+admin.getMaster());
	System.out.println("Cluster Status average load: "+status.getAverageLoad());
	System.out.println("Cluster Status Dead servers: "+status.getDeadServers());
	System.out.println("Cluster Status Hbase version: "+status.getHBaseVersion());
	System.out.println("Cluster Status Region Count: "+status.getRegionsCount());
	System.out.println("Cluster Status Requests: "+status.getRequestsCount());
	System.out.println("Cluster Status servers: "+status.getServers());
	
	}

}
