package Entites;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
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

	 public void setTypes(){
		 try {
			 URL oracle = new URL(uri);
			 URLConnection yc = oracle.openConnection();
			 BufferedReader in = new BufferedReader(new InputStreamReader(
					 yc.getInputStream()));
			 String inputLine;
			 String[] cequejeveux;
			 while ((inputLine = in.readLine()) != null) 
			 {if(inputLine.contains("\"rdf:type\"")){//peut-etre rajouter dbpedia-owl pour etre plus precis
				 cequejeveux = inputLine.split("</small>:");
				 cequejeveux = cequejeveux[1].split("</a>");
				 if(type==null) {type = cequejeveux[0];System.out.print("lu");}
				 else type=type.concat(cequejeveux[0]+" ");
			 }	
			 }
			 in.close();
			 System.out.println(type);
		 } catch (IOException e) {
			 // TODO Auto-generated catch block
			 e.printStackTrace();
		 }
	 }

}
