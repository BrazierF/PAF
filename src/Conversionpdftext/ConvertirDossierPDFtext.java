package Conversionpdftext;
import java.io.File;
import java.io.FileFilter;


public class ConvertirDossierPDFtext {
private static File file ; 


	public static void convertirdossier(){
		class MyFilter implements FileFilter {
		    @Override
		    public boolean accept(File file) {
		      return !file.isHidden() && file.getName().endsWith(".pdf");
		    }
		  }
		file = new File("data");
		MyFilter filter = new MyFilter();
		String filename;
		PDFTextParser textparser = new PDFTextParser();
		
		for (File x: file.listFiles(filter)){
			filename = x.getPath();
			textparser.convertpdftotxt(filename,filename.substring(0,filename.length()-4)+".txt");
		}
	}

	public static void main (String[] args){
		convertirdossier();

	}


}