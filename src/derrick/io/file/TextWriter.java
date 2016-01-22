package derrick.io.file;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.List;

public class TextWriter {

	public static void write(List<String> data,String filePath,String encoding){
		
		File file = new File(filePath);  
		try {  
			OutputStreamWriter osw=new OutputStreamWriter(new FileOutputStream(file),encoding);
		    BufferedWriter writer  = new BufferedWriter(osw);
		    for(String line:data){
		    	writer.write(line+"\n");
		    }
		    writer.flush();  
		    writer.close();  
		} catch (FileNotFoundException e) {  
		    e.printStackTrace();  
		} catch (IOException e) {  
		    e.printStackTrace();  
		}  
		
	}
	
	
	
}
