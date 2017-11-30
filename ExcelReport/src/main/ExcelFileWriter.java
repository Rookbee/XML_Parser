package main;

import java.util.ArrayList;
import object.TestSuite;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


public class ExcelFileWriter {

	private String outputFilePath;
	private String outputFileName;
	private String outputFileFullPath;
	private ArrayList<TestSuite> currTestSuite;
	private ArrayList<String> currTestSuiteStatus;
	private ArrayList<ArrayList<String>> currTestCaseStatus;
	private ArrayList<ArrayList<ArrayList<String>>> currTestCaseStepStatus;
	private XSSFWorkbook currWorkbook;
	private XSSFSheet currSheet;
	
	public ExcelFileWriter( DOM_Parser outputTestSuite , String outputFilePath , String outputFileName ) {
		this.outputFilePath = outputFilePath;
		this.outputFileName = outputFileName;
		this.outputFileFullPath = this.outputFilePath + this.outputFileName;
		this.currTestSuite = outputTestSuite.getTestSuiteList();
		this.currTestSuiteStatus = outputTestSuite.getTestSuiteStatus();
		this.currTestCaseStatus = outputTestSuite.getTestCaseStatus();
		this.currTestCaseStepStatus = outputTestSuite.getTestCaseStepStatus();
		this.currWorkbook = new XSSFWorkbook();
	}
	
	public void WriteFile(  ) {
		
		createTestSuiteSummarySheet();
		createTestSuiteDetailedSheet();
		createTestCaseDetailedSheet();
//		createFailedSheet();
		
	        try {
	            FileOutputStream outputStream = new FileOutputStream(outputFileFullPath);
	          
	            currWorkbook.write(outputStream);
	            currWorkbook.close();
	        } catch (FileNotFoundException e) {
	            e.printStackTrace();
	        } catch (IOException e) {
	            e.printStackTrace();
	        }

	        System.out.println("Done");
	        
	 }

	public void createTestSuiteSummarySheet( ) {
		
		Cell testSuiteCell;
		Cell testCaseStatus;
		final String [] headerList = {"TEST SUITE", "STATUS"};
		this.currSheet = currWorkbook.createSheet("Test Suite - Summary");
		
		int rowNumber = 2;
		Row rowData = currSheet.createRow(rowNumber);		
		
		//Create Excel Header
		createExcelHeader(this.currSheet,headerList,1);
		
		//Create Excel Data
		for (int tcSuiteIndex = 0 ; tcSuiteIndex < currTestSuite.size() ; tcSuiteIndex++){
			
				rowNumber ++;
				rowData = currSheet.createRow(rowNumber);
				testSuiteCell = rowData.createCell(0);
				testCaseStatus = rowData.createCell(1);
				
				testSuiteCell.setCellValue(currTestSuite.get(tcSuiteIndex).getTestSuiteName());
				testCaseStatus.setCellValue(currTestSuiteStatus.get(tcSuiteIndex));
									
		}
		
		resizeAllColumnFromSheet (currSheet,headerList.length);
		
	 }
	
	public void createTestSuiteDetailedSheet( ) {
		
		Cell testSuiteCell;
		Cell testCaseCell;
		Cell testCaseStatus;
		final String [] headerList = {"TEST SUITE","TEST CASE" , "STATUS"};
		this.currSheet = currWorkbook.createSheet("Test Suite - Details");
		
		int rowNumber = 2;
		Row rowData = currSheet.createRow(rowNumber);		
		
		//Create Excel Header
		createExcelHeader(this.currSheet,headerList,1);
		
		//Create Excel Data
		for (int tcSuiteIndex = 0 ; tcSuiteIndex < currTestSuite.size() ; tcSuiteIndex++){
			
			for (int tcCaseIndex = 0 ; tcCaseIndex < currTestSuite.get(tcSuiteIndex).getTestRunnerResults().size() ; tcCaseIndex ++) {
				
				rowNumber ++;
				rowData = currSheet.createRow(rowNumber);
				testSuiteCell = rowData.createCell(0);
				testCaseCell = rowData.createCell(1);
				testCaseStatus = rowData.createCell(2);
				
				testSuiteCell.setCellValue(currTestSuite.get(tcSuiteIndex).getTestSuiteName());
				testCaseCell.setCellValue(currTestSuite.get(tcSuiteIndex).getTestRunnerResults().get(tcCaseIndex).getTestCaseName());
				testCaseStatus.setCellValue(currTestCaseStatus.get(tcSuiteIndex).get(tcCaseIndex));
									
			}
			
			rowNumber ++; //Seperator

		}
		
		resizeAllColumnFromSheet (currSheet,headerList.length);

	 }
	
	public void createTestCaseDetailedSheet( ) {
		
		Cell testSuiteCell;
		Cell testCaseCell;
		Cell testStepCell;
		Cell testCaseStatus;
		
		this.currSheet = currWorkbook.createSheet("Test Case - Details");
		final String [] headerList = {"TEST SUITE"," TEST CASE" , "TEST STEP" , "STATUS" };
		
		int rowNumber = 2;
		Row rowData = currSheet.createRow(rowNumber);		
		
		//Create Excel Header
		createExcelHeader(this.currSheet,headerList,1);
		
		//Create Excel Data
		for (int tcSuiteIndex = 0 ; tcSuiteIndex < currTestSuite.size() ; tcSuiteIndex++){
					
			for (int tcCaseIndex = 0 ; tcCaseIndex < currTestSuite.get(tcSuiteIndex).getTestRunnerResults().size() ; tcCaseIndex ++) {

			System.out.println("detected size tests steps : " + currTestSuite.get(tcSuiteIndex).getTestRunnerResults().get(tcCaseIndex).getTestStepResults().size());
						
				for (int tcStepResult = 0 ; tcStepResult < currTestSuite.get(tcSuiteIndex).getTestRunnerResults().get(tcCaseIndex).getTestStepResults().size() ; tcStepResult++){
					
					rowNumber ++;
					rowData = currSheet.createRow(rowNumber);
					testSuiteCell = rowData.createCell(0);
					testCaseCell = rowData.createCell(1);
					testStepCell = rowData.createCell(2);
					testCaseStatus = rowData.createCell(3);
					
					testSuiteCell.setCellValue(currTestSuite.get(tcSuiteIndex).getTestSuiteName());
					testCaseCell.setCellValue(currTestSuite.get(tcSuiteIndex).getTestRunnerResults().get(tcCaseIndex).getTestCaseName());
					testStepCell.setCellValue(currTestSuite.get(tcSuiteIndex).getTestRunnerResults().get(tcCaseIndex).getTestStepResults().get(tcStepResult).getMessage());
					testCaseStatus.setCellValue(currTestCaseStepStatus.get(tcSuiteIndex).get(tcCaseIndex).get(tcStepResult));
				}
											
			 }
					
			 rowNumber ++; //Seperator

		}
		
		resizeAllColumnFromSheet (currSheet,headerList.length);
		
	 }
	
	public void createFailedSheet( ) {
		
		this.currSheet = currWorkbook.createSheet("Failures");
		final String [] headerList = {"TEST SUITE"," TEST CASE" , "TEST STEP"};
		
		Row row = currSheet.createRow(0);
		Cell cell = row.createCell(0);
		cell.setCellValue("Catomatic");

	 }
	
	public void resizeAllColumnFromSheet( XSSFSheet sheet , int numberOfColumns ){
		
		for (int i = 0 ; i < numberOfColumns ; i++ ){
			sheet.autoSizeColumn(i);
		}

	}
	
	public void createExcelHeader( XSSFSheet sheet , String [] headerList , int rowStartPosition){
		
		Row rowData = sheet.createRow(rowStartPosition);
		
		for (int headerIndex = 0 ; headerIndex < headerList.length ; headerIndex ++){
			Cell cell = rowData.createCell(headerIndex);
			cell.setCellValue(headerList[headerIndex]);
		}
	}
	
	
}
