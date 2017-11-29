package object;

import java.util.ArrayList;


public class TestCase {

	private String startTime;
	private String status;
	private String testCaseId; 
	private String testCaseName;
	private String timeTaken;
	private ArrayList<Result> testStepResults;
	
	public TestCase() {
		this("","","","","");
		this.testStepResults = new ArrayList<Result>();
	}
	
	public TestCase( String startTime , String status , String testCaseId , String testCaseName , String timeTaken ) {
		this.startTime = startTime;
		this.status = status;
		this.testCaseId = testCaseId; 
		this.testCaseName = testCaseName;
		this.timeTaken = timeTaken;
		this.testStepResults = new ArrayList<Result>();
	}
	
	public void setStartTime( String newStartTime ){
		this.startTime = newStartTime;
	}
	
	public void setStatus( String newStatus ){
		this.status = newStatus;
	}
	
	public void setTestCaseId( String newTestCaseId ){
		this.testCaseId = newTestCaseId;
	}
	
	public void setTestCaseName( String newTestCaseName ){
		this.testCaseName = newTestCaseName;
	}
	
	public void setTimeTaken( String newTimeTaken ){
		this.timeTaken = newTimeTaken;
	}
	
	public void setTestStepResults( ArrayList<Result> newTestStepResults ){
		this.testStepResults = newTestStepResults;
	}
	
	public String getStartTime( ){
		return this.startTime;
	}
	
	public String getStatus( ){
		return this.status;
	}
	
	public String getTestCaseId( ){
		return this.testCaseId;
	}
	
	public String getTestCaseName( ){
		return this.testCaseName;
	}
	
	public String getTimeTaken( ){
		return this.timeTaken;
	}
	
	public ArrayList<Result> getTestStepResults( ){
		return this.testStepResults;
	}
	
}
