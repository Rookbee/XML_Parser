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
	private XSSFWorkbook currWorkbook;
	private XSSFSheet currSheet;
	
	public ExcelFileWriter( DOM_Parser outputTestSuite , String outputFilePath , String outputFileName ) {
		this.outputFilePath = outputFilePath;
		this.outputFileName = outputFileName;
		this.outputFileFullPath = this.outputFilePath + this.outputFileName;
		this.currTestSuite = outputTestSuite.getTestSuiteList();
		this.currWorkbook = new XSSFWorkbook();
	}
	
	public void WriteFile(  ) {
		
		createSummarySheet();
//		createDetailedSheet();
//		createFailedSheet();
		/*
		 Object[][] datatypes = {
	                {"Datatype", "Type", "Size(in bytes)"},
	                {"int", "Primitive", 2},
	                {"float", "Primitive", 4},
	                {"double", "Primitive", 8},
	                {"char", "Primitive", 1},
	                {"String", "Non-Primitive", "No fixed size"}
	        };

	        int rowNum = 0;
	        System.out.println("Creating excel");

	        for (Object[] datatype : datatypes) {
	            Row row = currSheet.createRow(rowNum++);
	            int colNum = 0;
	            for (Object field : datatype) {
	                Cell cell = row.createCell(colNum++);
	                if (field instanceof String) {
	                    cell.setCellValue((String) field);
	                } else if (field instanceof Integer) {
	                    cell.setCellValue((Integer) field);
	                }
	            }
	        }
*/
		
//			Row row = currSheet.createRow(0);
//			Cell cell = row.createCell(0);
			
//			cell.setCellValue("Peanuts");
			
		
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
	
	public void createSummarySheet( ) {
		
		this.currSheet = currWorkbook.createSheet("Summary");
		int startRowNum = 1;
		final String [] headerList = {"TEST SUITE","TEST CASE" , "STATUS"};
		
		Row row = currSheet.createRow(startRowNum);
		
		Row rowData = currSheet.createRow(startRowNum + 1);
		
		System.out.println("Length of DOM SUITE : " + currTestSuite.size());
		
		for (int i = 0 ; i < headerList.length ; i ++){
			
			//Creating header for excel
			Cell cell = row.createCell(i);
			cell.setCellValue(headerList[i]);
			
			//Creating data for excel
			Cell testSuiteCell = rowData.createCell(0);
			testSuiteCell.setCellValue(currTestSuite.get(0).getTestSuiteName());

			Cell testCaseCell = rowData.createCell(1);
			testCaseCell.setCellValue(currTestSuite.get(0).getTestRunnerResults().get(0).getTestCaseName());
		
		}

	 }
	
	public void createDetailedSheet( ) {
		
		this.currSheet = currWorkbook.createSheet("Details");
		final String [] headerList = {"TEST SUITE"," TEST CASE" , "TEST STEP" };
		
		Row row = currSheet.createRow(0);
		Cell cell = row.createCell(0);
		cell.setCellValue("Snoopy");
		
	 }
	
	public void createFailedSheet( ) {
		
		this.currSheet = currWorkbook.createSheet("Fails");
		final String [] headerList = {"TEST SUITE"," TEST CASE" , "TEST STEP"};
		
		Row row = currSheet.createRow(0);
		Cell cell = row.createCell(0);
		cell.setCellValue("Catomatic");

	 }
	
	
}
