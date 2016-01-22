package derrick.io.file;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class TextReader {

	public static List<String> read(String filePath){
		return read(filePath,"UTF-8");
	}
	
	public static List<String> read(String filePath,String encoding){
		try {
			File file = new File(filePath);
			if (file.isFile() && file.exists()) { // 判断文件是否存在
				List<String> rawData=new ArrayList<>();
				InputStreamReader read = new InputStreamReader(new FileInputStream(file), encoding);// 考虑到编码格式
				BufferedReader bufferedReader = new BufferedReader(read);
				String lineTxt = null;
				while ((lineTxt = bufferedReader.readLine()) != null) {
					rawData.add(lineTxt);
				}
				read.close();
				return rawData;
			} else {
				System.err.println("can not find this file:"+file.getAbsolutePath());
			}
		} catch (Exception e) {
			System.err.println("read error");
			e.printStackTrace();
		}
		return null;
	}
	
}
