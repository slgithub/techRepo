/**
 * 
 */
package com.sl.social.jason.perser;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

import com.google.gson.Gson;

/**
 * @author shruthi
 * 
 */
public class GsonFrmFile {

	public static final String patternStep1 = "\"data\"\\:\\{\"interaction\"\\:";

	// <a\\b[^>]*href=\"[^>]*>(.*?)</a>

	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		Gson gson = new Gson();
		try {
			// FileInputStream in = new
			// FileInputStream("D:\\eclipse\\workSPACE_Jan2013\\jsonParser\\resource\\datasift-8_cpy.txt");
			FileInputStream in = new FileInputStream(
					"D:\\socialMedia\\TwitterDumps\\twitter-stream.log.1");
			BufferedReader br = new BufferedReader(new InputStreamReader(in));
			String strLine;
			int i = 1;
			while ((strLine = br.readLine()) != null) {

				try {
					// System.out.println(strLine);
					String comboStr = null;
					System.out.println("-->" + i + ")--------------------------------");
					// matcher.reset( strLine ); //reset the input

					Tweets obj = gson.fromJson(strLine, Tweets.class);
					 if(obj.user.lang!=null && obj.user.lang.equalsIgnoreCase("en")){            
				            if(obj.created_at != null || obj.text != null && obj.id != 0 )
				     		comboStr=obj.created_at+"|"+obj.text+"|"+obj.id+"|"+obj.user.lang;
				            }
					System.out.println(comboStr);

					i++;
				} catch (Exception e) {
					System.err.println(e.getMessage());
				}
			}

		} catch (Exception e) {
			System.err.println(e.getMessage());
		}

	}

}
