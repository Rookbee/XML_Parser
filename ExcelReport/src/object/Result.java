package object;

public class Result {
	
	private String message;
	private String name;
	private String order; 
	private String started;
	private String status;
	private String timeTaken;
	
	public Result( ) {
		this("","","","","","");
	}

	public Result(String message , String name , String order , String started , String status , String timeTaken ) {
		this.message = message;
		this.name = name;
		this.order = order;
		this.started = started;
		this.status = status;
		this.timeTaken = timeTaken;
	}
	
	public void setMessage( String newMessage ) {
		this.message = newMessage;
	}
	
	public void setName( String newName ) {
		this.name = newName;
	}
	
	public void setOrder( String newOrder ) {
		this.order = newOrder;
	}
	
	public void setStarted( String newStarted ) {
		this.started = newStarted;
	}
	
	public void setStatus( String newStatus ) {
		this.status = newStatus;
	}
	
	public void setTimetaken( String newTimetaken ) {
		this.timeTaken = newTimetaken;
	}
	
	public String getMessage( ) {
		return this.message;
	}
	
	public String getName( ) {
		return this.name;
	}
	
	public String getOrder( ) {
		return this.order;
	}
	
	public String getStarted( ) {
		return this.started;
	}
	
	public String getStatus( ) {
		return this.status;
	}
	
	public String getTimetaken( ) {
		return this.timeTaken;
	}
	
}
