package Entites;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


public class EvaluationFichiertxt {

	private  ArrayList<NamedEntity> entites_nommées;
	private File file;
	private String pathname;
	private String langue;
	private db aC;

	public EvaluationFichiertxt(String chemin){
		pathname=chemin;
		if(pathname.contains("@en"))langue="@en";
		else if(pathname.contains("fr")) langue="@fr";
		else langue="@en";
		file = new File(pathname);
		entites_nommées = new ArrayList<NamedEntity>();
	}

	public void evaluerfichiertxt() throws Exception{
		aC = new db();
		aC.configiration(0.0, 0, "non", "CoOccurrenceBasedSelector", "Default", "yes");
		aC.evaluate(AnnotationClient.readFileAsString(pathname),langue);
		ArrayList<JSONArray> resultat= aC.getTotalEntities();
		NamedEntity a;
		//System.out.print(aC.getResu());				

		if(resultat!=null){
			//System.out.print(resultat.size());
			for(int i = 0; i < resultat.size(); i++) {
				for(int j=1;j<resultat.get(i).length();j++){
					//System.out.print(resultat.get(i).getJSONObject(j));
					JSONObject entity = resultat.get(i).getJSONObject(j);
					Iterator<?> keys = entity.keys();                    
					a = new NamedEntity();

					while(keys.hasNext() ){

						String key = (String)keys.next();
						//System.out.println(key+" "+entity.get(key).toString());

						if(key.contains("@surfaceForm")){
							a.setName(entity.get(key).toString());
							//System.out.println(key+" "+entity.get(key).toString());
						}

						else if(key.contains("@URI")){
							a.setURI(entity.get(key).toString());
						}

						else if(key.contains("@types")){
							a.setType(entity.get(key).toString());
						}


						if(entity.get(key) instanceof JSONObject ){
							//System.out.print("\t"+entity.get(key));
						}
						entites_nommées.add(a);
					}

				}
			}
		}
	}

	public void evaluerfichiertxtecriture() throws Exception{
		evaluerfichiertxt();
		PrintWriter ecrivain = null;
		try {
			ecrivain =  new PrintWriter(new BufferedWriter
					(new FileWriter(pathname.substring(0,pathname.length()-4)+".ENTITES.txt")));

			for(NamedEntity y : entites_nommées){
				ecrivain.println(y.getName());
				ecrivain.println(y.getType());
				ecrivain.println(y.getURI()+"\n");
			}
			ecrivain.close();

		}catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void ecritureEntitesNommees(){
		PrintWriter ecrivain = null;
		try {
			ecrivain =  new PrintWriter(new BufferedWriter
					(new FileWriter(pathname.substring(0,pathname.length()-4)+".ENTITES.txt")));

			for(NamedEntity y : entites_nommées){
				ecrivain.println(y.getName());
				ecrivain.println(y.getType());
				ecrivain.println(y.getURI()+"\n");
			}
			ecrivain.close();

		}catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
