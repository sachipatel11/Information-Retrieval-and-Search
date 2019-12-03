/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package index;
import java.util.TreeSet;

/**
 *
 * @author patel381
 */
public class SortedDocScore extends DocScore implements Comparable {
    
    

    public SortedDocScore(double score, int doc_id, String content) {
        super(score, doc_id, content);
    }
    
    public SortedDocScore (DocScore ds){
        super (ds);
    }

    @Override
    public int compareTo(Object o) {
        if (!(o instanceof DocScore)) //if o is not an object of DocScore return -1 to indicate false
        return 1;
        
        DocScore ds = (DocScore)o; //cast the object to be DocScore
        if ((this._score) > (ds._score)) //if score is more than object score i.e. not equal
            return -1;
        if ((this._score) < (ds._score)) //if score is less than object score i.e. not equal
            return 1;
        if(!this._content.equals(ds._content)){
            return this._content.compareTo(ds._content);
        }
        return 0; //since we have eliminated all possibilites of inequality, this indicates that the objects are equal and has passed all other tests
        
    }
    
    public static void main (String [] args){
        SortedDocScore a = new SortedDocScore (5, 46, "what");
        SortedDocScore b = new SortedDocScore (7, 49, "whaaaat");
        SortedDocScore c = new SortedDocScore (5, 46, "what");
        TreeSet <DocScore>test = new TreeSet<>();
        test.add(a);
        test.add(b);
        test.add(c);
        System.out.println(test);
        
    }
}
