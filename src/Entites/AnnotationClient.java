package Entites;
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.httpclient.DefaultHttpMethodRetryHandler;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.apache.log4j.Logger;
import org.dbpedia.spotlight.exceptions.AnnotationException;
import org.dbpedia.spotlight.model.DBpediaResource;
import org.dbpedia.spotlight.model.Text;
import org.json.JSONArray;

/**
 *
 * @author gueffaz
 */
public abstract class AnnotationClient {
    
    public Logger LOG = Logger.getLogger(this.getClass());
    private List<String> RES = new ArrayList<String>();
    private JSONArray  aCentities=null; 
    
    public JSONArray getEntities(){
        return aCentities;
    }

    // Create an instance of HttpClient.
    private static HttpClient client = new HttpClient();
    
    public List<String> getResu(){
        return RES;     
    }

    public String request(HttpMethod method) throws IOException {
        String response = null;
        // Provide custom retry handler is necessary
        method.getParams().setParameter(HttpMethodParams.RETRY_HANDLER,
                new DefaultHttpMethodRetryHandler(3, false));
        
            // Execute the method.
            int statusCode = client.executeMethod(method);
            if (statusCode != HttpStatus.SC_OK) {
                LOG.error("Method failed: " + method.getStatusLine());
            }

            // Read the response body.
            byte[] responseBody = method.getResponseBody(); //TODO Going to buffer response body of large or unknown size. Using getResponseBodyAsStream instead is recommended.

            // Deal with the response.
            // Use caution: ensure correct character encoding and is not binary data
            response = new String(responseBody);

        
        return response;

    }

    protected static String readFileAsString(String filePath) throws java.io.IOException{
        return readFileAsString(new File(filePath));
    }

    protected static String readFileAsString(File file) throws IOException {
        byte[] buffer = new byte[(int) file.length()];
        @SuppressWarnings("resource")
        BufferedInputStream f = new BufferedInputStream(new FileInputStream(file));
        f.read(buffer);
        return new String(buffer);
    }

    static abstract class LineParser {

        public abstract String parse(String s) throws ParseException;

        static class ManualDatasetLineParser extends LineParser {
            public String parse(String s) throws ParseException {
                return s.trim();
            }
        }

        static class OccTSVLineParser extends LineParser {
            public String parse(String s) throws ParseException {
                String result = s;
                try {
                    result = s.trim().split("\t")[3];
                } catch (ArrayIndexOutOfBoundsException e) {
                    throw new ParseException(e.getMessage(), 3);
                }
                return result; 
            }
        }
    }

    public void saveExtractedEntitiesSet(String Question, LineParser parser, int restartFrom, String Langue) throws Exception {
    	//aCentities = new JSONArray();
    	String text = Question;
        int i=0;
        //int correct =0 ; int error = 0;int sum = 0;
        int nbcaracteres=2000;
        List<String> aextraire = new ArrayList<String>();
		String bloc = " ";
        for (String snippet: text.split("\n")) {
        	bloc =bloc.concat(snippet);
        	if(bloc.length()>nbcaracteres){
				aextraire.add(bloc);
				bloc=" ";
        	}
        	}
        aextraire.add(bloc);
        
        for(String snippet : aextraire){
            String s = parser.parse(snippet);
            if (s!= null && !s.equals("")) {
                i++;

                if (i<restartFrom) continue;

                List<DBpediaResource> entities = new ArrayList<DBpediaResource>();

                 try {
                     
                    entities = extract(new Text(snippet.replaceAll("\\s+"," ")), Langue);
                   // for(int k=0; k<entities.size(); k++)
                    //System.out.println(i);
                  

                } catch (AnnotationException e) {
                   // error++;
                    LOG.error(e);
                    e.printStackTrace();
                }

                
                for (DBpediaResource e: entities) {
                    RES.add(e.uri());
                }
            }
        }
    }


    public abstract List<DBpediaResource> extract(Text text, String Langue) throws AnnotationException;

    public void evaluate(String Question, String Langue) throws Exception {
        evaluateManual(Question,0, Langue);
    }

    public void evaluateManual(String Question, int restartFrom, String Langue) throws Exception {
         saveExtractedEntitiesSet(Question,new LineParser.ManualDatasetLineParser(), restartFrom, Langue);
    }
}
