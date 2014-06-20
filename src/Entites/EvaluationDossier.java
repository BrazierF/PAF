package Entites;
import java.io.File;
import java.io.FileFilter;


public class EvaluationDossier {
	private String pathname;
	private File file;

	public EvaluationDossier(String chemin){
		pathname=chemin;
		file = new File(pathname);
	}

	public void evaluerDossier() throws Exception{
		class MyFilter implements FileFilter {
			@Override
			public boolean accept(File file) {
				return !file.isHidden() && file.getName().endsWith(".txt")&& !file.getName().endsWith(".ENTITES.txt");
			}
		}
		MyFilter filter = new MyFilter();
		for(File x : file.listFiles()){//passer data An et data Fr
			for(File xx : x.listFiles(filter)){
				EvaluationFichiertxt eFtxt = new EvaluationFichiertxt(xx.getPath());
				eFtxt.evaluerfichiertxt();
			}
		}
	}
	
	public void evaluerDossierecriture() throws Exception{
		class MyFilter implements FileFilter {
			@Override
			public boolean accept(File file) {
				return !file.isHidden() && file.getName().endsWith(".txt")&& !file.getName().endsWith(".ENTITES.txt");
			}
		}
		MyFilter filter = new MyFilter();
		for(File x : file.listFiles()){//passer data An et data Fr
			for(File xx : x.listFiles(filter)){
				EvaluationFichiertxt eFtxt = new EvaluationFichiertxt(xx.getPath());
				eFtxt.evaluerfichiertxtecriture();
			}
		}
	}
}