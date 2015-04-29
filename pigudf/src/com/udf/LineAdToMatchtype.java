/**
 * 
 */
package com.udf;

import java.io.IOException;

import org.apache.pig.EvalFunc;
import org.apache.pig.data.DataBag;
import org.apache.pig.data.DefaultBagFactory;
import org.apache.pig.data.DefaultTupleFactory;
import org.apache.pig.data.Tuple;

/**
 * @author su828791
 *
 */
public class LineAdToMatchtype extends EvalFunc<DataBag> {

	private String lineAdSourceToMatchtype(String lineAdSource) {
		if (lineAdSource.equals("0")) {
			return "1";
		} else if (lineAdSource.equals("9")) {
			return "2";
		} else if (lineAdSource.equals("13")) {
			return "3";
		} else
			return "0";
	}

	public DataBag exec(Tuple input) throws IOException {
		if (input == null || input.size() == 0)
			return null;
		String lineAdSource;
		try {
			lineAdSource = (String) input.get(0);
		} catch (Exception e) {
			System.err
					.println("ExpectedClick.Evals.LineAdToMatchType: Can't convert field to a string; error = "
							+ e.getMessage());
			return null;
		}
		Tuple t = DefaultTupleFactory.getInstance().newTuple();
		try {
			t.set(0,lineAdSourceToMatchtype(lineAdSource));
		} catch (Exception e) {
		}
		DataBag output = DefaultBagFactory.getInstance().newDefaultBag();
		output.add(t);
		return output;
	}

}
