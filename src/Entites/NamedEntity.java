package Entites;
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 *
 * @author gueffaz
 */
public class NamedEntity {
    
    private String name;    
    private String uri;    
    private String type;
    
    public NamedEntity(){
        this.name=null;
        this.uri=null;
        this.type=null;
    }
    
    public NamedEntity(String a, String b, String c){
        this.name=a;
        this.uri=b;
        this.type=c;
    }
    
    /*
     * Accesseurs
     */
    public String getName(){
        return this.name;
    }
    
    public String getURI(){
        return this.uri;
    }
    
    public String getType(){
        return this.type;
    }
    /*
     * Mutateurs
     */
    public void setName(String a){
        this.name=a;
    }
    
    public void setURI(String a){
        this.uri=a;
    }
    
    public void setType(String a){
        this.type=a;
    }
}
