package main;

import object.FileRepository;


public class mainProgram {

	public static void main(String[] args) {
		
		FileRepository currentRepository;
		ExcelFileWriter outputRepository;
		
		DOM_Parser bidon;
		
		try {
			
			currentRepository = new FileRepository("C:\\Data\\SOAP_Results\\","SOAP_UI_Results_Example_2.xml");
					
			bidon = new DOM_Parser(currentRepository);
			bidon.createTestSuite();
			bidon.createTestCases();
			bidon.createTestStepResults();
			
//			outputRepository = new ExcelFileWriter(bidon,"C:\\Data\\SOAP_Results\\","caca1.xlsx");
//			outputRepository.WriteFile();
			
		} catch (Exception e) {
			
			e.printStackTrace();
			
		}

	}

}
