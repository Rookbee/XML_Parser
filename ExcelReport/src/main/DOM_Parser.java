package main;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import object.FileRepository;
import object.Result;
import object.TestCase;
import object.TestSuite;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;


public class DOM_Parser {

	private File inputFile;
	private DocumentBuilderFactory dbFactory;
	private DocumentBuilder dBuilder;
	private Document doc;
	private ArrayList<TestSuite> result;
	
	public DOM_Parser( FileRepository inputRepository ) throws ParserConfigurationException, SAXException, IOException {
		this.inputFile = new File(inputRepository.getInputFullFilePath());
		this.dbFactory = DocumentBuilderFactory.newInstance();
		this.dBuilder = dbFactory.newDocumentBuilder();
		this.doc = dBuilder.parse(inputFile);
		this.doc.getDocumentElement().normalize();
		this.result = new ArrayList<TestSuite>();
	}
	
	public ArrayList<Node> makeNodeArraylist(NodeList nList){
		ArrayList<Node> tempResult = new ArrayList<Node>();
		
		for (int i = 0 ; i < nList.getLength() ; i ++ ){
	      		Node nNode = nList.item(i);
	      		if (nNode.getNodeType() == Node.ELEMENT_NODE) {
	      			tempResult.add(nNode);      		
	      	}
	    }
		return tempResult;
	}
	

	public ArrayList<TestSuite> getTestSuiteList(){
		
		return this.result;
	}
	
	
	public int getIndexFromNodeName( NodeList nList , String nodeName ){
		
		int result = 0;
		boolean stop = false;
		
		for (int i = 0 ; i < nList.getLength() ; i ++){

			if(stop == true)
				break;
			
			System.out.println(  "LVL1 - " + nList.item(i).getNodeName() + " V.S " + nodeName );
			
			if( (nList.item(i).getNodeName()).equalsIgnoreCase(nodeName) ){
				
				System.out.println("LVL1 - " + " == | Equals | == ");
				result = i;
				stop = true;
				break;
				
			} else {
				
				System.out.println("LVL1 - " + " == | Not equal | == ");
				
				for(int j = 0 ; j < nList.item(i).getChildNodes().getLength() ; j ++ ) {
					
					System.out.println( "Current J - " + j );
					System.out.println( "J Max - " + nList.item(i).getChildNodes().getLength() );
					
					System.out.println( "LVL2 - " + nList.item(i).getChildNodes().item(j).getNodeName() + " V.S " + nodeName );
					
						if( (nList.item(i).getChildNodes().item(j).getNodeName()).equalsIgnoreCase(nodeName) ) {
							
							System.out.println(" == | Equals | == ");
							result = j-1;
							stop = true;
							break;
							
						} 
						
					}
					
				}
			

		}
		
		System.out.println("< FOUND EQUAL INDEX > : " + result);
		return result;
		
	}
	
	public void createTestSuite( ){
	
		ArrayList<Node> allTestSuite;
		NodeList nListTestSuite;
		int testSuiteIndexNode;
		TestSuite newSuite;
		
		nListTestSuite = this.doc.getElementsByTagName("testSuiteResults");
		testSuiteIndexNode = getIndexFromNodeName( nListTestSuite , "testSuite" );
		allTestSuite = makeNodeArraylist(nListTestSuite.item(testSuiteIndexNode).getChildNodes());
		
		//Retrieving information on each node
		for (Node nNode : allTestSuite){
			
			Element eElement = (Element) nNode;	
			String docStartTime = eElement.getElementsByTagName("startTime").item(0).getTextContent();
			String docStatus = eElement.getElementsByTagName("status").item(0).getTextContent();
			String docTestSuiteName = eElement.getElementsByTagName("testSuiteName").item(0).getTextContent();
			String docTimetaken = eElement.getElementsByTagName("timeTaken").item(0).getTextContent();
			
			System.out.println("----------------");			
			System.out.println("Xml - Start time : " + docStartTime );
			System.out.println("Xml - Status : " + docStatus );
			System.out.println("Xml - Test Suite Name : " + docTestSuiteName );
			System.out.println("Xml - Time Taken : " + docTimetaken );
			System.out.println("----------------");
			
			newSuite = new TestSuite();
			newSuite.setStartTime(docStartTime);
			newSuite.setStatus(docStatus);
			newSuite.setTestSuiteName(docTestSuiteName);
			newSuite.setTimetaken(docTimetaken);
			
			this.result.add(newSuite);
			
		}
		
	}
	
	
	public void createTestCases( ){
		
		ArrayList<Node> allTestSuite;
		ArrayList<Node> allTestCase;
		NodeList nListTestSuite;
		NodeList nListTestCase;
		int testSuiteIndexNode;
		int testCaseIndexNode;
		ArrayList<TestCase> resultTemp;
		TestCase aNewTestCase;
		
		nListTestSuite = this.doc.getElementsByTagName("testSuiteResults");
		testSuiteIndexNode = getIndexFromNodeName( nListTestSuite , "testSuite" );
		allTestSuite = makeNodeArraylist(nListTestSuite.item(testSuiteIndexNode).getChildNodes());
		
		for (int i = 0 ; i < allTestSuite.size() ; i ++){

			resultTemp = new ArrayList<TestCase>();		
			nListTestCase = allTestSuite.get(i).getChildNodes();
			testCaseIndexNode = getIndexFromNodeName( nListTestCase , "testRunnerResults" );
			allTestCase = makeNodeArraylist(nListTestCase.item(testCaseIndexNode).getChildNodes());
			
			for ( int j = 0 ; j < allTestCase.size() ; j ++ ){
				
				Element eElement = (Element) allTestCase.get(j);
				String docStartTime = eElement.getElementsByTagName("startTime").item(0).getTextContent();
				String docStatus = eElement.getElementsByTagName("status").item(0).getTextContent();
				String docTestCaseId = eElement.getElementsByTagName("testCaseId").item(0).getTextContent();
				String docTestCaseName = eElement.getElementsByTagName("testCaseName").item(0).getTextContent();
				String docTimeTaken = eElement.getElementsByTagName("timeTaken").item(0).getTextContent();
				
				System.out.println("----------------");			
				System.out.println("Xml - Start time : " + docStartTime );
				System.out.println("Xml - Status : " + docStatus );
				System.out.println("Xml - Test Case Id : " + docTestCaseId );
				System.out.println("Xml - Test Case Name : " + docTestCaseName );
				System.out.println("Xml - Time Taken : " + docTimeTaken );
				System.out.println("----------------");
				
				aNewTestCase = new TestCase();
				aNewTestCase.setStartTime(docStartTime);
				aNewTestCase.setStatus(docStatus);
				aNewTestCase.setTestCaseId(docTestCaseId);
				aNewTestCase.setTestCaseName(docTestCaseName);
				aNewTestCase.setTimeTaken(docTimeTaken);
				
				resultTemp.add(aNewTestCase);
						
			}
			
			this.result.get(i).setTestRunnerResults(resultTemp);
		}

	}
		
	public void createTestStepResults( ){
		
		ArrayList<Node> allTestSuite;
		ArrayList<Node> allTestCase;
		ArrayList<Node> allTestResults;
		ArrayList<Result> resultTemp;
		NodeList nListTestSuite;
		NodeList nListTestCase;
		int testSuiteIndexNode;
		int testCaseIndexNode;
		int testResultIndexNode;

		Result aNewStepResult;
		
		nListTestSuite = this.doc.getElementsByTagName("testSuiteResults");
		testSuiteIndexNode = getIndexFromNodeName( nListTestSuite , "testSuite" );
		testCaseIndexNode = getIndexFromNodeName( nListTestSuite , "testSuite" );	
		allTestSuite = makeNodeArraylist(nListTestSuite.item(testSuiteIndexNode).getChildNodes());
		
		for (int i = 0 ; i < allTestSuite.size() ; i ++){
			nListTestCase = allTestSuite.get(i).getChildNodes();
			testCaseIndexNode = getIndexFromNodeName( nListTestCase , "testRunnerResults" );
			System.out.println("Test case index Node : " + testCaseIndexNode );
			allTestCase = makeNodeArraylist(allTestSuite.get(i).getChildNodes().item(testCaseIndexNode).getChildNodes());
			System.out.println("Detected test Case length : " + allTestCase.size());
			
			for ( int j = 0 ; j < allTestCase.size() ; j ++ ){
				System.out.println("Current test case " + j);
				resultTemp = new ArrayList<Result>();	
				testResultIndexNode = getIndexFromNodeName( nListTestCase.item(testCaseIndexNode).getChildNodes(), "testStepResults" ) + 1; // +1 to compensate the empty tag for the testcase tag
				System.out.println("ASDASDASdTest result index node : " + testResultIndexNode);
				allTestResults = makeNodeArraylist(allTestCase.get(j).getChildNodes().item(testResultIndexNode).getChildNodes());
				
				for (int k = 0 ; k < allTestResults.size() ; k++ ){
					
					Element eElement = (Element) allTestResults.get(k);
					String docMessage = eElement.getElementsByTagName("message").item(0).getTextContent();
					String docName = eElement.getElementsByTagName("name").item(0).getTextContent();
					String docOrder = eElement.getElementsByTagName("order").item(0).getTextContent();
					String docStarted = eElement.getElementsByTagName("started").item(0).getTextContent();
					String docStatus = eElement.getElementsByTagName("status").item(0).getTextContent();
					String docTimeTaken = eElement.getElementsByTagName("timeTaken").item(0).getTextContent();
					
					System.out.println("----------------");			
					System.out.println("Xml - Message : " + docMessage );
					System.out.println("Xml - Name : " + docName );
					System.out.println("Xml - Order : " + docOrder );
					System.out.println("Xml - Started : " + docStarted );
					System.out.println("Xml - Status : " + docStatus );
					System.out.println("Xml - Time Taken : " + docTimeTaken );
					System.out.println("----------------");
					
					aNewStepResult = new Result();
					aNewStepResult.setMessage(docMessage);
					aNewStepResult.setName(docName);
					aNewStepResult.setOrder(docOrder);
					aNewStepResult.setStarted(docStarted);
					aNewStepResult.setStatus(docStatus);
					aNewStepResult.setTimetaken(docTimeTaken);
					
					resultTemp.add(aNewStepResult);
					
				}
					
				this.result.get(i).getTestRunnerResults().get(j).setTestStepResults(resultTemp);
				
			}

		}

	}
	
	
	public ArrayList<String> getTestSuiteValidationStatus() {
		
		ArrayList<String> testCaseValidStatus = new ArrayList<String>();
		
		return testCaseValidStatus;
	}

}
	
	

