package Johan;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


import java.util.ArrayList;

import org.annolab.tt4j.TreeTaggerException;


//<editor-fold defaultstate="collapsed" desc="TFIDF calculator">
/**
 * Class to calculate TfIdf of term.
 * @author Mubin Shrestha
 */
public class TfIdf {
    
    //<editor-fold defaultstate="collapsed" desc="TF Calculator">
    /**
     * Calculated the tf of term termToCheck
     * @param totalterms : Array of all the words under processing document
     * @param termToCheck : term of which tf is to be calculated.
     * @return tf(term frequency) of term termToCheck
     */
    public static double tfCalculator(String[] totalterms, String termToCheck) {
        double count = 0;  //to count the overall occurrence of the term termToCheck
        for (String s : totalterms) {
            if (s.equalsIgnoreCase(termToCheck)) {
                count++;
            }
        }
        return count / totalterms.length;
    }
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Idf Calculator">
    /**
     * Calculated idf of term termToCheck
     * @param allTerms : all the terms of all the documents
     * @param termToCheck
     * @return idf(inverse document frequency) score
     */
    public static double idfCalculator(ArrayList<String[]> allTerms, String termToCheck) {
        double count = 0;
        for (String[] ss : allTerms) {
            for (String s : ss) {
                if (s.equalsIgnoreCase(termToCheck)) {
                    count++;
                    break;
                }
            }
        }
        return Math.log(allTerms.size() / count);
    }
    
public static Quartet[] tfidfcorpus(Corpus corpus) throws TreeTaggerException, Exception{
		
		ArrayList<String> filenames = corpus.getCorpus();
		ArrayList<String[]> motstexte = new ArrayList<String[]>();
		for (int j = 0; j < filenames.size(); j++ ){
			ArrayList<String> jcorpus = corpus.traitement(j);
			motstexte.add(TabArrayOperator.arrayToTab(jcorpus)) ;
			Lemmatisation lemm = new Lemmatisation(jcorpus);
			ArrayList<TripletTagg> trip = lemm.lemmatisation();
			System.out.println(j);
			motstexte.set(j, TabArrayOperator.arrayToTab(TripletTagg.getResultatArray(trip))); 
		}
		
		int nbmots = 0;
		for (int k = 0; k < motstexte.size(); k++){
			nbmots = nbmots + motstexte.get(k).length;
		}
		int compteur = 0;
		double tf;
		double idf;
		Quartet[] resultat = new Quartet[nbmots];
		for (int l = 0 ; l < motstexte.size() ; l++){
                    String[] l_ieme_motstexte = motstexte.get(l);
			for (int m = 0; m < l_ieme_motstexte.length; m++){
				tf = TfIdf.tfCalculator(l_ieme_motstexte, l_ieme_motstexte[m]);
				idf = TfIdf.idfCalculator(motstexte, l_ieme_motstexte[m]);
				resultat[compteur] = new Quartet(l_ieme_motstexte[m], tf, idf, tf*idf, corpus.getTitle(l));
				System.out.println( "lemme : " + l_ieme_motstexte[m] + ", " + "tf : " + tf + ", " + "idf : " + idf + ", " + "tfidf : " + tf*idf + ", " + "titre : " + corpus.getTitle(l) + ", ");
				compteur++;
			}
			
		}
		return resultat ;
	}
//</editor-fold>
}
//</editor-fold>
