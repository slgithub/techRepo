package com.suml.data.prep.classification;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.InputStreamReader;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileStatus;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.SequenceFile;
import org.apache.hadoop.io.SequenceFile.Writer;
import org.apache.hadoop.io.Text;

public class TweetCSVToSeq {

	public static void main(String args[]) throws Exception {
		if (args.length != 2) {
			System.err
					.println("Arguments: [input csv file] [output sequence file]");
			return;
		}
		String inputdirName = args[0];
		String outputDirName = args[1];

		Configuration configuration = new Configuration();
		FileSystem fs = FileSystem.get(configuration);

		Writer writer = new SequenceFile.Writer(fs, configuration, new Path(
				outputDirName + "/chunk-0"), Text.class, Text.class);

		int count = 1;
		Path path = new Path(inputdirName);
		FileStatus fstatus[] = fs.listStatus(path);
		for (FileStatus f : fstatus) {
			String inputFileName=f.getPath().toUri().getPath();
			DataInputStream d = new DataInputStream(fs.open(new Path(inputFileName)));

			BufferedReader reader = new BufferedReader(new InputStreamReader(d));
			Text key = new Text();
			Text value = new Text();

			while (true) {
				String line = reader.readLine();
				if (line == null) {
					break;
				}
				String[] tokens = line.split("\\,", 2);
				if (tokens.length != 2) {
					System.out.println("Skip line: " + line);
					continue;
				}
				String message = tokens[0];
				String category = tokens[1];
				// String id = tokens[1];
				//message=message.replaceAll("\\s+","");
				message=message.replaceAll("(?:http?|https?|ftps?)://[\\w/%.-]+", "");
				message=message.replaceAll("\\p{Punct}|\\d", "");
				
				if(category.contains(",")){
				int i= category.lastIndexOf(",");
				category=category.substring(i+1, category.length());
				}
				if(category!=null&& category.trim().length()<=0){
					System.out.println("Skip line: " + line);
					continue;
				}
				key.set("/" + category + "/" + count);
				value.set(message);
				writer.append(key, value);
				count++;
			}
		}
		writer.close();
		System.out.println("Wrote " + count + " entries.");
	}
}
