package Entites;

public class Test {

	public static void main(String[] args) {
		EvaluationDossier eD=new EvaluationDossier("data");
		try {
			eD.evaluerDossierecriture();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
