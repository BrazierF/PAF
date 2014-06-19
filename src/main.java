import java.io.IOException;

import org.dbpedia.spotlight.exceptions.AnnotationException;
import org.dbpedia.spotlight.model.Text;


public class main {

	public static void main(String[] args) throws Exception {
		db aC = new db();
		Text txtaext = new Text(AnnotationClient.readFileAsString("data/bim.txt"));//texte a extraire
		//System.out.print(aC.extract(txtaext, "@en").isEmpty());
		aC.evaluate(AnnotationClient.readFileAsString("data/bim.txt"),"@fr");
		System.out.print(aC.getResu());
	}

}
