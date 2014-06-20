package Johan;

import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;


public class Corpus {

	public ArrayList<String> corpus;         // contient les noms des fichiers txt ou pdf du corpus
	
	public Corpus() {
		
		corpus = new ArrayList<String>() ;
		
	}
	
	public ArrayList<String> getCorpus(){ // getter de l'arraylist du corpus
		
		return corpus ;
		
	}
	
public String getTitle(int n){ // getter du titre du doc num�ro n
		
		return corpus.get(n) ;
		
	}
	
	public void addText(String name){     // ajouter un texte au corpus en entrant son nom
		
		corpus.add(name);
		
	}
	
	public static String[] listerRepertoire(File repertoire){
		
		String[] listefichiers;
		listefichiers = repertoire.list();
		/*for (int i=0; i < listefichiers.length ; i++){ 
				System.out.println(listefichiers[i]);
		} */
		return listefichiers ;	
	}
	
	public ArrayList<String> lecture(int n){   // on convertit le nom du fichier en ArrayList contenant tous ses mots
		try {
			
			String s = corpus.get(n);
			FileReader fr = new FileReader(s);
			Scanner sc = new Scanner(fr);
			ArrayList<String> lexique = new ArrayList<String>();

			while (sc.hasNext()){
				lexique.add(sc.next());
				// System.out.println(lexique.get(i));
			}
			sc.close();
			return lexique ;
		}
		catch (IndexOutOfBoundsException e){
			System.out.println( "Taille du corpus d�pass�e" );
			return null;
		}
		catch (Exception e){
			System.out.println( "Le fichier demand� n'est pas dans le corpus" );
			return null;
		}
	}
	
	public ArrayList<String> correction(ArrayList<String> array){   // On supprime ce qui g�ne pour la lemmatisation, les caract�res sp�ciaux donc
		
		String element;
		String[] decoupage;
		ArrayList<String> transport = new ArrayList<String>();
		for (int i = 0; i < array.size(); i++){
			
			element = array.get(i);
			if (element.contains("'")){
				decoupage = element.split("'");
				for (int j = 0; j < decoupage.length; j++){
					transport.add(decoupage[j]);
				}
				
			}
			else if (element.contains(".")){
				decoupage = element.split(".");
				for (int j = 0; j < decoupage.length; j++){
					transport.add(decoupage[j]);
				}
				
			}
			else if (element.contains("...)")){              // � revoir
				decoupage = element.split("...)");
				for (int j = 0; j < decoupage.length; j++){
					transport.add(decoupage[j]);
				}
				
			}
			else if (element.contains("�")){
				decoupage = element.split("�");
				for (int j = 0; j < decoupage.length; j++){
					transport.add(decoupage[j]);
				}
				
			}
			else if (element.contains(",")){
				decoupage = element.split(",");
				for (int j = 0; j < decoupage.length; j++){
					transport.add(decoupage[j]);
				}
				
			}
			else if (element.contains("(")){
				decoupage = element.split("\\(");
				for (int j = 0; j < decoupage.length; j++){
					transport.add(decoupage[j]);
				}
				
			}
			else if (element.contains(")")){
				decoupage = element.split("\\)");
				for (int j = 0; j < decoupage.length; j++){
					transport.add(decoupage[j]);
				}
				
			}
			else transport.add(array.get(i));
		}
		return transport ;
	}
	
	public  ArrayList<String> traitement(int n){
		
		ArrayList<String> array = lecture(n);
		array = correction(array);
		return array;
	}
	
}

