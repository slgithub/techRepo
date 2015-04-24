package com.sl.social.jason.perser;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.pig.EvalFunc;
import org.apache.pig.data.Tuple;

import com.google.gson.Gson;

public class TwitterUdfJson extends EvalFunc<String> {

	public static final String patternNonAscii="[^\\x20-\\x7E]";
	public static final String patternStep1=",en,";
	public static final String patternRT="(?:RT|rt)";
	public static final String patternRTURL="(?:http?|https?|ftps?)://[\\w/%.-]+";	
	public static final String patternHashTag="(?:@)(\\w)*";

	@Override
	public String exec(Tuple input) throws IOException {
	
		
		if (input == null || input.size() == 0)
            return null;
        String d;
        String comboStr = null;
        String text;
        try{
        	Gson gson = new Gson();        			    
	       
        	if(input.get(0) == null || input.get(0).toString().length() == 0 )
        	return null;
            d = input.get(0).toString();
            Tweets obj = gson.fromJson(d, Tweets.class);
           
            if(obj.user.lang!=null && obj.user.lang.equalsIgnoreCase("en")){            
            if(obj.created_at != null || obj.text != null && obj.id != 0 ){
     		
            Pattern regexpAscii = Pattern.compile(patternNonAscii);
 		    Matcher matcherAscii = regexpAscii.matcher("");
 		    
 		    Pattern regexpRT = Pattern.compile(patternRT);
 		    Matcher matcherRT = regexpRT.matcher("");
 	        text=obj.text; 				  
 				  
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
            }
            }
           
        } catch (Exception e){        	 
            return null;
        }
        return comboStr;
	
 }
}
