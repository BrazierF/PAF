package Johan;

import java.io.File;

public class Main {
	
	public static void main(String[] args) throws Exception {
		
		// Construction du corpus de textes
		
		Corpus corpus = new Corpus();
		File repertoire = new File("C:\\Users\\Arturo\\Desktop\\PDF PAF");
		String s = "C:/Users/Arturo/Desktop/PDF PAF/" ;
		String[] listedocs = Corpus.listerRepertoire(repertoire);
		for (int i = 0; i < listedocs.length; i++){
			corpus.addText(s + listedocs[i]);
			System.out.println(listedocs[i]);
		}
		
		// Construction du corpus de vid�os
		
		/*Corpus corpus2 = new Corpus();
		String v = "C:/Users/Joh/Documents/PAF/VIDEOS/";
		corpus2.addText( v + "video3_HelenLegras.txt");
		corpus2.addText( v + "video7_BigData.txt");
		
		// Calculs de tf idf pour chaque mot
		
		TfIdf.tfidfcorpus(corpus2);*/
		
		// Traitement du corpus de textes
		
		TfIdf.tfidfcorpus(corpus);
		
	} 

}
