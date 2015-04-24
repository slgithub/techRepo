package com.suml.ml.clustering.kmean.dataprep;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;

import com.google.common.base.Charsets;
import com.google.common.io.Closeables;

public class FilesFromTwitterTweet {

	public static void main(String[] arg) throws Exception {

		Configuration conf = new Configuration();
		FileSystem fs = FileSystem.get(conf);

		List<String> DATA1 = new ArrayList<String>();

		DataInputStream d = new DataInputStream(fs.open(new Path("/user/hadoop/socialmediadata/borderIssue/stage1/stg_1/part-m-00000")));

		BufferedReader reader = new BufferedReader(new InputStreamReader(d));
		try {
			String line;
			
			while ((line = reader.readLine()) != null) {
				System.out.println(line);
				DATA1.add(line);
				
			}
		} finally {
			// you should close out the BufferedReader
			reader.close();
		}

		// parameters

		// create
		Path tmpDir = new Path("/user/hadoop/socialmediadata/");
		Path inputDir = new Path(tmpDir, "inputMahoutTweetDir");
		fs.mkdirs(inputDir);

		// prepare input files
		createFilesFromArrays(conf, inputDir, DATA1);

	}

	private static void createFilesFromArrays(Configuration conf,
			Path inputDir, List<String> data) throws IOException {
		FileSystem fs = FileSystem.get(conf);
		int i = 0;
		for (String aData : data) {
			OutputStreamWriter writer = new OutputStreamWriter(
					fs.create(new Path(inputDir, "tweet" + i)), Charsets.UTF_8);
			try {
				writer.write(aData);
				i++;
			} finally {
				/*Closeables.closeQuietly(writer);*/
				Closeables.close(writer, true);
			}
		}
	}
}
