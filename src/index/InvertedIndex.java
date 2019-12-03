/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package index;

import io.DocSource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.TreeSet;
import score.TermScoringFun;
import tokenizer.Tokenizer;

/**
 *
 * @author patel381
 */
public class InvertedIndex extends Index {
    
    private HashMap<String,HashMap<Integer,Integer>> _index;
    private HashMap<String,Integer> _termDF;
    //protected TreeSet <SortedDocScore> sdc; 
    

    
        
        
    public InvertedIndex(DocSource doc_source, Tokenizer tokenizer, TermScoringFun scoring) {
                
        //use super to acess parent class index 
        super(doc_source,tokenizer, scoring);  
        _index  = new HashMap<String,HashMap<Integer,Integer>>();
        _termDF = new HashMap<String,Integer>();
        
    }

    @Override
    public void buildIndex() { 
        ArrayList<String> tr = new ArrayList<String>();
        
        //go through all documents
        for ( int i = 0 ; i < this._docSource.getNumDocs(); i ++ ) {
            //create an arraylist to hold term
            tr = this._tokenizer.tokenize(_docSource.getDoc(i)) ; 
//take this object to class Tokenizer using the _tokenize var, and use tokenize method
//need to add ret arraylist to new copy arraylist otherwise there's no way to access ret, its just lost
            //int count = 1;
            for (String term : tr){
                if (!_index.containsKey(term)){
                    _index.put(term, new HashMap<Integer,Integer>());
                    _index.get(term).put(i, 1);
                    _termDF.put(term,1);
                }
                else{
                    if (_index.containsKey(term) && _index.get(term).containsKey(i)){
                    
                    _index.get(term).replace(i,_index.get(term).get(i)+1);
                    
                }else {
                    _index.get(term).put(i, 1);
                    _termDF.put(term, _termDF.get(term)+1);
                }
                    } 
               
            }
            tr.clear();
        }
        
    }
    


    @Override
    public Integer getDocumentFreq(String term) {
        if(!_termDF.containsKey(term))
            return 1;
        return _termDF.get(term);

    //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<DocScore> getSortedSearchResults(String query) { //for given term find the score and update it 
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        
        ArrayList<String> ssr = new ArrayList<String>();
        HashMap <Integer,Double> score = new HashMap <Integer,Double> (); 
        TreeSet <SortedDocScore>_src = new TreeSet<SortedDocScore>();
        
        ssr= this._tokenizer.tokenize(query);
        double scores =0;
        
        
        //for (int i=0; i < this._docSource.getNumDocs(); i ++) {
            for (String trm : ssr){

                if (_index.containsKey(trm)){
                    for (int x : _index.get(trm).keySet())
                        if(!score.containsKey(x)) {
                            score.put(x,this._scoring.scoreToken(trm,_index.get(trm).get(x)));
                        }
                        else if (score.containsKey(x) ){
                            double newScore = score.get(x) + this._scoring.scoreToken(trm,_index.get(trm).get(x));
                            score.put(x, newScore);
                        }
                        
            }        
             
    //}
        }
          for (Integer i: score.keySet()) { // adding info to sorted set
              _src.add(new SortedDocScore(score.get(i), i, _docSource.getDoc(i)));
          }
          
          ArrayList<DocScore> ds = new ArrayList <DocScore>(_src);
          //ds.addAll(_src); //addAll add all content from treeset into arraylist 
          return ds;
    }
    
        
}
