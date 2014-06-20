package Johan;

import java.util.ArrayList;


public class TabArrayOperator {
	
	public TabArrayOperator(){
			
	}
	
	public static boolean appartientArray(String s, ArrayList<String> tab){ // retourne vrai si le mot s appartient à l'arraylist en entrée
		
		boolean appartient = false ;
		int i = 0;
		while ((appartient == false) && (i < tab.size())){
			if (s.equals(tab.get(i))){
				appartient = true ;
			}
			i++;
		}
		return appartient ;	
	}
	
public static boolean appartientTab(String s, String[] tab){ // retourne vrai si le mot s appartient au tableau en entrée
		
		boolean appartient = false ;
		int i = 0;
		while ((appartient == false) && (i < tab.length)){
			if (s.equals(tab[i])){
				appartient = true ;
			}
			i++;
		}
		return appartient ;	
	}
	
    public static ArrayList<String> tabmots(String[] text){ // construction de l'arraylist contenant chaque mot du texte (qui sera lemmatisé) une fois
		
		int size = text.length;
		ArrayList<String> tabmots = new ArrayList<String>() ;
		for (int i = 0 ; i < size ; i++){ 
			if (appartientArray(text[i], tabmots) == false){
				tabmots.add(text[i]);
			}
		}
		
		return tabmots ;
	}
    
    public static String[] arrayToTab(ArrayList<String> array){ // transforme une arraylist en sa copie en tableau
		
		int size = array.size();
		String[] tab = new String[size];
		for (int i = 0; i < array.size(); i++){
			tab[i] = array.get(i);
		}
		return tab ;
	}
    
}
