package object;

public class FileRepository {

	private String inputFilePath;
	private String inputFileName;
	private String inputFileFullpath;
	
	public FileRepository( ) {
		this("","");	
	}
	
	public FileRepository( String currentPath , String currentFileName ) {
		this.inputFilePath = currentPath;
		this.inputFileName = currentFileName;
		this.inputFileFullpath = this.inputFilePath + this.inputFileName;
	}

	public void setInputFilePath( String newFilePath ) {
		inputFilePath = newFilePath;
	   }

	public void setInputFileName( String newFileName ) {
		inputFileName = newFileName;
	   }
	
	public void setInputFullFilePath( String newFileName ) {
		inputFileFullpath = newFileName;
	   }
	
	public String getInputFilePath( ) {
		return inputFilePath;
	   }

	public String getInputFileName( ) {
		return inputFileName;
	   }
	
	public String getInputFullFilePath( ) {
		return inputFileFullpath;
	   }
	
}
