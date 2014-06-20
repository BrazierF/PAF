package Entites;
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.commons.httpclient.Header;
import org.apache.commons.httpclient.methods.GetMethod;
import org.dbpedia.spotlight.exceptions.AnnotationException;
import org.dbpedia.spotlight.model.DBpediaResource;
import org.dbpedia.spotlight.model.Text;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author gueffaz
 */
public class db extends AnnotationClient{

	private JSONArray  entities=null;
	private ArrayList<JSONArray>  totalentities= null;

	public JSONArray getEntities(){
		return entities;
	}

	public ArrayList<JSONArray> getTotalEntities(){
		return totalentities;
	}

	//private final static String API_URL = "http://jodaiber.dyndns.org:2222/";
	private static String API_URL="http://spotlight.sztaki.hu:2225/"; //the french web service of DBPedia Spotlight
	private static String API_URL_en="http://spotlight.sztaki.hu:2222/";//the english web service of DBPedia Spotlight

	//private static String  API_URL    = "http://spotlight.dbpedia.org:80/";
	private static  double  CONFIDENCE = 0.0;
	private static  int     SUPPORT    = 0;
	private static  String  powered_by ="non";
	private static  String  spotter ="CoOccurrenceBasedSelector";//"LingPipeSpotter"=Annotate all spots 
	//AtLeastOneNounSelector"=No verbs and adjs.    
	//"CoOccurrenceBasedSelector" =No 'common words'
	//"NESpotter"=Only Per.,Org.,Loc.
	private static String  disambiguator ="Default";//Default ;Occurrences=Occurrence-centric;Document=Document-centric
	private static String  showScores ="yes";

	@SuppressWarnings("static-access")
	public void configiration(double CONFIDENCE,int SUPPORT,
			String powered_by,String spotter,String disambiguator,String showScores){
		this.CONFIDENCE=CONFIDENCE;
		this.SUPPORT=SUPPORT;
		this.powered_by=powered_by;
		this.spotter=spotter;
		this.disambiguator=disambiguator;
		this.showScores=showScores;

	}
	@Override
	public List<DBpediaResource> extract(Text text, String Langue) throws AnnotationException {

		LOG.info("Querying API.");
		String spotlightResponse = "";

		try{
			/*String Query=API_URL + "rest/annotate/?" +
                    "confidence=" + CONFIDENCE
                  + "&support=" + SUPPORT
                  + "&spotter=" + spotter
                  + "&disambiguator=" + disambiguator
                  + "&showScores=" + showScores
                  + "&powered_by=" + powered_by
                  + "&text=" + URLEncoder.encode(text.text(), "utf-8");*/

			String Query="";
			//pour l'utilisation du web service
			switch (Langue) {
			case "@fr":
				Query=API_URL + "rest/annotate?" 
						+ "text=" + URLEncoder.encode(text.text(), "utf-8");
				break;
			case "@en":
				Query=API_URL_en + "rest/annotate?"
						+ "text=" + URLEncoder.encode(text.text(), "utf-8");
				break;
			}

			LOG.info(Query);

			GetMethod getMethod = new GetMethod(Query);
			getMethod.addRequestHeader(new Header("Accept", "application/json"));
			spotlightResponse = request(getMethod);

		}catch (UnsupportedEncodingException e) {
			throw new AnnotationException("Could not encode text.", e);
		} catch (IOException ex) {
			Logger.getLogger(db.class.getName()).log(Level.SEVERE, null, ex);
		}


		assert     spotlightResponse != null;
		JSONObject resultJSON         = null;
		// entities           = null;

		try{                       
			resultJSON = new JSONObject(spotlightResponse);
			entities = resultJSON.getJSONArray("Resources");

		} catch (JSONException e) {
			//throw new AnnotationException("Received invalid response from DBpedia Spotlight API.");
		}


		LinkedList<DBpediaResource> resources = new LinkedList<DBpediaResource>();
		if(entities!=null){
			if(totalentities==null){totalentities = new ArrayList<JSONArray>();}
			totalentities.add(entities);
			for(int i = 0; i < entities.length(); i++) {
				try {//System.out.println(i+"/"+entities.length());
					JSONObject entity = entities.getJSONObject(i);
					//affichage
					//System.out.println(entity);//afficher chaque entitée nommée avec + d'informations
					Iterator<?> keys = entity.keys();

					while(keys.hasNext() ){
						String key = (String)keys.next();
						//System.out.println(key+" "+entity.get(key).toString());

						if( entity.get(key) instanceof JSONObject ){
							//System.out.print("\t"+entity.get(key));
						}                
					}
					resources.add(
							new DBpediaResource(new String(entity.getString("@URI").getBytes("ISO-8859-1"), "UTF-8"),
									Integer.parseInt(entity.getString("@support"))));

				} catch (JSONException | NumberFormatException | UnsupportedEncodingException e) {
					LOG.error("JSON exception "+e);

				}
			}
		}//System.out.print("Salut");
		return resources;
	}

}