package com.suml.ml.clustering.kmean.dataprep;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import jdamasceno.stemmer.Stemmer;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.util.ToolRunner;
import org.apache.mahout.common.AbstractJob;
import org.apache.mahout.utils.io.ChunkedWriter;

import ptstemmer.exceptions.PTStemmerException;
import ptstemmer.support.PTStemmerUtilities;


public class SequenceFilesFromTwitterPigData extends AbstractJob{

	@Override
	public int run(String[] args) throws Exception {
		// TODO Auto-generated method stub
		
		int chunkSize = 64;
		
		Configuration conf = new Configuration();
		FileSystem fs = FileSystem.get(conf);

		List<String> tweets = new ArrayList<String>();

		//DataInputStream d = new DataInputStream(fs.open(new Path("/user/hadoop/socialmediadata/borderIssue/stage1/stg_1/part-m-00000")));

		DataInputStream d = new DataInputStream(fs.open(new Path(args[0])));
		
		BufferedReader reader = new BufferedReader(new InputStreamReader(d));
		try {
			String line;
			
			while ((line = reader.readLine()) != null) {
				//System.out.println(line);
				tweets.add(line);
				
			}
		} finally {
			// you should close out the BufferedReader
			reader.close();
		}

		// parameters
		//String dirUsuario = "/export/home/hadoop/socialmediadata/tweeter-analytics/sequence-files-pig-output";
		
		String dirUsuario = args[1];
		
		ChunkedWriter writer = new ChunkedWriter(getConf(), chunkSize,new Path(dirUsuario));
		
		//Stemmer stemmer = createStemmer();
		
		Integer i=1;
		
		if (!tweets.isEmpty()) {

			for (String tweet : tweets) {
				
				//String stemmedTweet = stem(tweet, stemmer);

				//writer.write( i.toString(), stemmedTweet);
				writer.write( i.toString(), tweet);
				i++;
			}

			
		}

		writer.close();
		System.out.println("SequenceFile generated ");
		return 0;
	}
	
	
	private Stemmer createStemmer() throws PTStemmerException {
		Stemmer stemmer = new Stemmer();
		stemmer.enableCaching(100000);
		stemmer.setStopWords(PTStemmerUtilities.fileToSet("stemmer/stopwords.txt"));
		stemmer.ignore(PTStemmerUtilities.fileToSet("stemmer/namedEntities.txt"));
		return stemmer;
	}

	private String stem(String tweet, Stemmer stemmer) throws PTStemmerException {
		
		String[] stems = stemmer.getPhraseStems(tweet);
		StringBuilder sb = new StringBuilder();
		
		for (String stem : stems) {
			String wordStemmed = PTStemmerUtilities.removeDiacritics(stem);
			sb.append(wordStemmed).append(" ");
		}
		
		System.out.println(sb.toString());
		
		return sb.toString();
	}
	
	
	public static void main(String[] args) throws Exception {
		if (args.length<2)
		{
			System.out.println("\n! Improper Usage. Correct usage is as : java SequenceFilesFromTwitterPigData Input-filepath output-filepath\n");
			return;
		}
		ToolRunner.run(new SequenceFilesFromTwitterPigData(), args);
	}

}
