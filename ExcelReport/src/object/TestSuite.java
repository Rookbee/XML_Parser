package object;
import java.util.ArrayList;

public class TestSuite {

	private String startTime;
	private String status;
	private String testSuiteName; 
	private String timeTaken;
	private ArrayList<TestCase> testRunnerResults;
	
	public TestSuite() {
		this("", "", "", "");
		this.testRunnerResults = new ArrayList<TestCase>();
	}
	
	public TestSuite( String startTime , String status , String testSuiteName , String timeTaken ) {
		this.startTime = startTime;
		this.status = status;
		this.testSuiteName = testSuiteName; 
		this.timeTaken = timeTaken;
		this.testRunnerResults = new ArrayList<TestCase>();
	}

	public void setStartTime( String newStartTime ){
		this.startTime = newStartTime;
	}
	
	public void setStatus( String newStatus ){
		this.status = newStatus;
	}
	
	public void setTestSuiteName( String newTestSuiteName ){
		this.testSuiteName = newTestSuiteName;
	}
	
	public void setTimetaken( String newTimeTaken ){
		this.timeTaken = newTimeTaken;
	}
	
	public void setTestRunnerResults( ArrayList<TestCase> newTestRunnerResults ){
		this.testRunnerResults = newTestRunnerResults;
	}

	public String getStartTime( ){
		return this.startTime;
	}
	
	public String getStatus( ){
		return this.status;
	}
	
	public String getTestSuiteName( ){
		return this.testSuiteName;
	}
	
	public String getTimetaken( ){
		return this.timeTaken;
	}
	
	public ArrayList<TestCase> getTestRunnerResults( ){
		return this.testRunnerResults;
	}
	
}
