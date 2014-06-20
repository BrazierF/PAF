package Johan;

import java.util.ArrayList;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

 /**
 * TripletTagg - Un nouveau type de variable pour
 * stocker le résultat de la lemmatisation
 * 
 * @version1.0
 *
 * @see ...
 * @author gueffaz
 * @copyright (C) gueffaz 2013
 * @date 28/10/2013
 * @notes notes particulières sur la classe
 *
 * @revision référence
 *          date 28/10/2013
 *          author Gueffaz M.
 *          raison  description
 *          description supplémentaire
 */
 
public class TripletTagg {
    
    public String tag;
    public String type;
    public String resultat;

    /*
     * Constructeur
     */
    public TripletTagg(String a, String b, String c) {
        
        this.tag=a;
        this.type=b;
        this.resultat=c;
    
    }
    
    public TripletTagg() {
        
        this.tag=null;
        this.type=null;
        this.resultat=null;
    
    }
    
    /*
     * Accesseurs
     */
    
    public String getTag(){
        return this.tag;
    }
    
    public String getType(){
        return this.type;
    }
    
    public String getResultat(){
        return this.resultat;
    }
    
    /*
     * Mutateur
     */
    
    public void setTag(String a){
        this.tag=a;
    }
    
    public void setType(String a){
        this.type=a;
    }
    
    public void setResultat(String a){
        this.resultat=a;
    }
    
    public String ToString2(){
        return this.getTag()+" "+this.getType()+" "+this.getResultat();
    }
    
    public static ArrayList<String> getResultatArray(ArrayList<TripletTagg> triplist){
    	
    	ArrayList<String> arraymots = new ArrayList<String>();
    	for (int i = 0; i < triplist.size(); i++){
    		arraymots.add(triplist.get(i).getResultat());
    	}
    	return arraymots ;
    }
    
}
