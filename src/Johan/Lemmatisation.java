package Johan;



import java.util.ArrayList;

import org.annolab.tt4j.TokenHandler;
import org.annolab.tt4j.TreeTaggerException;
import org.annolab.tt4j.TreeTaggerWrapper;

public class Lemmatisation {
	
	public ArrayList<String> arraymots;
	public ArrayList<TripletTagg> triplist ;
	@SuppressWarnings("rawtypes")
	public TreeTaggerWrapper tt;
	
	@SuppressWarnings("rawtypes")
	public Lemmatisation(){
		arraymots = new ArrayList<String>();
		triplist = new ArrayList<TripletTagg>();
		tt = new TreeTaggerWrapper();
	}
	
	@SuppressWarnings("rawtypes")
	public Lemmatisation(ArrayList<String> arraymots){
		this.arraymots = arraymots;
		triplist = new ArrayList<TripletTagg>();
		tt = new TreeTaggerWrapper();
	}

	@SuppressWarnings("unchecked")
	public ArrayList<TripletTagg> lemmatisation() throws Exception, TreeTaggerException {
		
        // Point TT4J to the TreeTagger installation directory. The executable is expected
        // in the "bin" subdirectory - in this example at "/opt/treetagger/bin/tree-tagger"
		
        System.setProperty("treetagger.home", "C:/Users/Arturo/Desktop/TreeTagger/TreeTagger");
        
        try {
                tt.setModel("C:/Users/Arturo/Desktop/TreeTagger/TreeTagger/lib/french.par");
               
                tt.setHandler(new TokenHandler<String>() {
                        public void token(String token, String pos, String lemma) {
                                // System.out.println(token + "\t" + pos + "\t" + lemma);
                        	TripletTagg tg;
                        	tg = new TripletTagg(token, pos, lemma);
                            triplist.add(tg);
                        }
                });
                tt.process(arraymots);
               /* for (int j = 0 ; j < triplist.size(); j++ ){
                	System.out.println(triplist.get(j).getResultat());
                } */
                return triplist ;
        }
        finally {
                tt.destroy();
        }
        // return Corpus.arrayToTab(array);
        
}
	
}
