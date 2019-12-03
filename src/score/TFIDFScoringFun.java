/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package score;

import index.Index;
import io.DocSource;
import java.lang.Math;

/**
 *
 * @author patel381
 */
public class TFIDFScoringFun implements TermScoringFun {
    Index _index;

    @Override
    public void init(Index s) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        _index=s;
    
    }
    

    @Override
    public double scoreToken(String term, int freq) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        double n = (_index.getDocSource().getNumDocs());
        double docfreq = (_index.getDocumentFreq(term));
        return ((1.0+(double) Math.log10((double)freq))* ((double) Math.log10 (n/docfreq)));
    
    
    
    
    }
    
}
