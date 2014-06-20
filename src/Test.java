import Conversionpdftext.ConvertirDossierPDFtext;
import Entites.EvaluationDossier;



public class Test {

	public static void main(String[] args){
		try {
		ConvertirDossierPDFtext cDPDFT= new ConvertirDossierPDFtext();
		cDPDFT.convertirdossier("data/AN_@en");
		cDPDFT.convertirdossier("data/FR_@fr");
		EvaluationDossier eD=new EvaluationDossier("data");
		
			eD.evaluerDossierecriture();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}

