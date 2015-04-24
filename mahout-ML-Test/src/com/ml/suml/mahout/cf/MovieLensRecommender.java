package com.ml.suml.mahout.cf;

import java.util.Collection;
import java.util.List;

import org.apache.mahout.cf.taste.common.Refreshable;
import org.apache.mahout.cf.taste.common.TasteException;
import org.apache.mahout.cf.taste.impl.recommender.GenericItemBasedRecommender;
import org.apache.mahout.cf.taste.impl.similarity.PearsonCorrelationSimilarity;
import org.apache.mahout.cf.taste.model.DataModel;
import org.apache.mahout.cf.taste.recommender.IDRescorer;
import org.apache.mahout.cf.taste.recommender.RecommendedItem;
import org.apache.mahout.cf.taste.recommender.Recommender;
import org.apache.mahout.cf.taste.similarity.ItemSimilarity;

public class MovieLensRecommender implements Recommender{

	private final ItemSimilarity similarity ;
	private final Recommender delegate ;

	  public MovieLensRecommender(DataModel model) throws TasteException {
	    similarity = new PearsonCorrelationSimilarity(model);	    
	    delegate = new GenericItemBasedRecommender(model, similarity);
	  }
	

	  @Override
	  public List<RecommendedItem> recommend(long userID, int howMany) throws TasteException {
	    return delegate.recommend(userID, howMany);
	  }

	  @Override
	  public List<RecommendedItem> recommend(long userID, int howMany, IDRescorer rescorer) throws TasteException {
	    return delegate.recommend(userID, howMany, rescorer);
	  }

	  @Override
	  public float estimatePreference(long userID, long itemID) throws TasteException {
	    return delegate.estimatePreference(userID, itemID);
	  }

	  @Override
	  public void setPreference(long userID, long itemID, float value) throws TasteException {
		  delegate.setPreference(userID, itemID, value);
	  }

	  @Override
	  public void removePreference(long userID, long itemID) throws TasteException {
		  delegate.removePreference(userID, itemID);
	  }

	  @Override
	  public DataModel getDataModel() {
	    return delegate.getDataModel();
	  }

	  @Override
	  public void refresh(Collection<Refreshable> alreadyRefreshed) {
		  delegate.refresh(alreadyRefreshed);
	  }

	  @Override
	  public String toString() {
	    return "MovieLensRecommender[recommender:" + delegate + ']';
	  }

}
