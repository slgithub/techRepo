/**
 * 
 */
package com.datagrid.hadoop.eg.wc;

import java.io.IOException;
import java.util.Iterator;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

/**
 * @author su828791
 *
 */
public class WordCountReducer extends Reducer<Text, IntWritable, Text, IntWritable>{

	@Override
	protected void reduce(Text key, Iterable<IntWritable> values,
			Context context)
			throws IOException, InterruptedException {
	
		int sum = 0;
		Iterator<IntWritable> valuesIt = values.iterator();
		while(valuesIt.hasNext()){
			sum = sum + valuesIt.next().get();
		}
		context.write(key, new IntWritable(sum));
	}	
}