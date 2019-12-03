/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tokenizer;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author patel381
 */
public class IndexingTokenizer implements Tokenizer {
    
    @Override
    public ArrayList<String> tokenize(String s) {
        ArrayList<String> ret = new ArrayList<String>();
		Pattern p = Pattern.compile("(\\w[\\w-]*)");
		Matcher m = p.matcher(s);
                
                //Ask TA about the condition below 
		while (m.find()) {
			ret.add(m.group().toLowerCase());
			// Equivalently we could also say: ret.add(s.substring(m.start(), m.end()));
                        
               }
                return ret ;
    }
}
