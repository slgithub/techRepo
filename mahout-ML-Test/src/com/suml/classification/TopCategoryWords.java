package com.suml.classification;

import java.util.HashMap;
import java.util.Map;
import java.util.SortedSet;
import java.util.TreeSet;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.mahout.classifier.naivebayes.BayesUtils;
import org.apache.mahout.classifier.naivebayes.NaiveBayesModel;
import org.apache.mahout.classifier.naivebayes.StandardNaiveBayesClassifier;
import org.apache.mahout.common.Pair;
import org.apache.mahout.common.iterator.sequencefile.SequenceFileIterable;

public class TopCategoryWords {

	public static Map<Integer, String> readInverseDictionnary(Configuration conf, Path dictionnaryPath) {
		Map<Integer, String> inverseDictionnary = new HashMap<Integer, String>();
		for (Pair<Text, IntWritable> pair : new SequenceFileIterable<Text, IntWritable>(dictionnaryPath, true, conf)) {
			inverseDictionnary.put(pair.getSecond().get(), pair.getFirst().toString());
		}
		return inverseDictionnary;
	}

	public static Map<Integer, Long> readDocumentFrequency(Configuration conf,
			Path documentFrequencyPath) {
		Map<Integer, Long> documentFrequency = new HashMap<Integer, Long>();
		for (Pair<IntWritable, LongWritable> pair : new SequenceFileIterable<IntWritable, LongWritable>(documentFrequencyPath, true, conf)) {
			documentFrequency.put(pair.getFirst().get(), pair.getSecond().get());
		}
		return documentFrequency;
	}

	public static class WordWeight implements Comparable<WordWeight> {
		private int wordId;
		private double weight;

		public WordWeight(int wordId, double weight) {
			this.wordId = wordId;
			this.weight = weight;
		}

		public int getWordId() {
			return wordId;
		}

		public Double getWeight() {
			return weight;
		}

		@Override
		public int compareTo(WordWeight w) {
			return -getWeight().compareTo(w.getWeight());
		}
	}

	public static void main(String[] args) throws Exception {
		if (args.length < 4) {
			System.out
					.println("Arguments: [model] [label index] [dictionnary] [document frequency]");
			return;
		}
		String modelPath = args[0];
		String labelIndexPath = args[1];
		String dictionaryPath = args[2];
		String documentFrequencyPath = args[3];
		Configuration configuration = new Configuration(); // model is a matrix
															// (wordId, labelId)
															// => probability
															// score
		NaiveBayesModel model = NaiveBayesModel.materialize(new Path(modelPath), configuration);

		StandardNaiveBayesClassifier classifier = new StandardNaiveBayesClassifier(model);

		// labels is a map label => classId
		Map<Integer, String> labels = BayesUtils.readLabelIndex(configuration,new Path(labelIndexPath));
		Map<Integer, String> inverseDictionary = readInverseDictionnary(configuration, new Path(dictionaryPath));
		Map<Integer, Long> documentFrequency = readDocumentFrequency(configuration, new Path(documentFrequencyPath));

		int labelCount = labels.size();
		int documentCount = documentFrequency.get(-1).intValue();

		System.out.println("Number of labels: " + labelCount);
		System.out.println("Number of documents in training set: "+ documentCount);

		for (int labelId = 0; labelId < model.numLabels(); labelId++) {
			SortedSet<WordWeight> wordWeights = new TreeSet<WordWeight>();
			for (int wordId = 0; wordId < model.numFeatures(); wordId++) {
				WordWeight w = new WordWeight(wordId, model.weight(labelId,	wordId));
				wordWeights.add(w);
			}
			System.out.println("Top 10 words for label " + labels.get(labelId));
			int i = 0;
			for (WordWeight w : wordWeights) {
				System.out.println(" - " + inverseDictionary.get(w.getWordId())
						+ ": " + w.getWeight());
				i++;
				if (i >= 10) {
					break;
				}
			}
		}
	}
}
