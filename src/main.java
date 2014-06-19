import java.io.BufferedWriter;
import java.io.File;
import java.io.FileFilter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import org.dbpedia.spotlight.exceptions.AnnotationException;
import org.dbpedia.spotlight.model.Text;


public class main {

	public static void evaluationtext(String path,String Langue) throws Exception {
		db aC = new db();
		aC.evaluate(AnnotationClient.readFileAsString(path),Langue);
		System.out.print(aC.getResu());
	}

	public static void evaluationdossier(String path) throws Exception {
		class MyFilter implements FileFilter {
			@Override
			public boolean accept(File file) {
				return !file.isHidden() && file.getName().endsWith(".txt");
			}
		}
		//db aC = new db();
		File file = new File(path);
		MyFilter filter = new MyFilter();
		for(File x : file.listFiles(filter)){
			db aC = new db();
			aC.evaluate(AnnotationClient.readFileAsString(x.getPath()),"@fr");
			System.out.print(aC.getResu());}
	}


	public static void evaluationdossierecriture(String path) throws Exception {
		class MyFilter implements FileFilter {
			@Override
			public boolean accept(File file) {
				return !file.isHidden() && file.getName().endsWith(".txt")&& !file.getName().endsWith(".ENTITES.txt");
			}
		}
		//db aC = new db();
		File file = new File(path);
		MyFilter filter = new MyFilter();
		for(File x : file.listFiles(filter)){
			String pathname = x.getPath();
			db aC = new db();
			System.out.print(x.getName());
			System.out.print(x.getPath());
			if(x.getPath().contains("@fr"))aC.evaluate(AnnotationClient.readFileAsString(pathname),"@fr");
			if(x.getName().contains("@en"))aC.evaluate(AnnotationClient.readFileAsString(pathname),"@en");		
			PrintWriter ecrivain;
			ecrivain =  new PrintWriter(new BufferedWriter
					(new FileWriter(pathname.substring(0,pathname.length()-4)+".ENTITES.txt")));
			List<String> results = aC.getResu();
			for(String y : results){
				ecrivain.println(y);
			}
			 ecrivain.close();
		}
	} 

	public static void main(String[] args) throws Exception {
		evaluationdossierecriture("data");
	}

}
