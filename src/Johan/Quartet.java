package Johan;


public class Quartet {
	
	public String lemme ;
	public double tf ;
	public double idf ;
	public double tfidf ;
	public String titre ;
	
	public Quartet(String a, double b, double c, double d, String e){
		
		lemme = a;
		tf = b;
		idf = c;
		tfidf = d;
		titre = e;
	}
	
	public String getLemme(){
		
		return lemme;
		
	}
	
	public double getTf(){
		
		return tf;
		
	}

	public double getIdf(){
	
		return idf;
	
	}
	
	public double getTfidf(){
		
		return tfidf;
		
	}
	
public String getTitre(){
		
		return titre;
		
	}
	
	public void setLemme(String s){
		
		lemme = s;
		
	}
	
	public void setTf(double a){
		
		tf = a;
		
	}

	public void setIdf(double b){
	
		idf = b;
	
	}
	
	public void setTfidf(double c){
		
		tfidf = c;
		
	}
	
public void setTitre(String s){
		
		titre = s;
		
	}
	
}
