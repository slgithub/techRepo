package com.sl.csv.udf;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.pig.EvalFunc;
import org.apache.pig.data.Tuple;

public class ReTweetExcluder extends EvalFunc<String> {

	public static final String patternNonAscii="[^\\x20-\\x7E]";
	public static final String patternStep1=",en,";
	public static final String patternRT="(?:RT|rt)";
	public static final String patternRTURL="(?:http?|https?|ftps?)://[\\w/%.-]+";	
	public static final String patternHashTag="(?:@)(\\w)*";
	

	@Override
	public String exec(Tuple input) throws IOException {
	
		
		if (input == null || input.size() == 0)
            return null;
        String text;
        String comboStr = null;
        try{
        	  		    
 		    Pattern regexpAscii = Pattern.compile(patternNonAscii);
 		    Matcher matcherAscii = regexpAscii.matcher("");
 		    
 		    Pattern regexpRT = Pattern.compile(patternRT);
 		    Matcher matcherRT = regexpRT.matcher("");
 	        text=input.get(0).toString(); 				  
 				  
 				   matcherAscii.reset(text);
 			       if ( !matcherAscii.find()) {
 				   
 				   
 				   matcherRT=matcherRT.reset(text.trim());
 				   
 				   if(!(matcherRT.find())){
 					   String orgStatus=text;
 					   String orgStatusWithoutURL=null;
 					   String orgStatusWithoutURLHashTag=null;
 					   //URL removal
 					   if(text!=null){
 						   orgStatusWithoutURL=text.replaceAll(patternRTURL, "");
 						   
 					   }
 					   
 					   //hash tag separation
 					   if(orgStatusWithoutURL!=null){
 						   orgStatusWithoutURLHashTag=orgStatusWithoutURL.replaceAll(patternHashTag, " ");
 						  comboStr=orgStatusWithoutURLHashTag.trim();
 					   }
 				    }
 			       }
            
           
        } catch (Exception e){        	 
            return null;
        }
        return comboStr;
	
 }
}
