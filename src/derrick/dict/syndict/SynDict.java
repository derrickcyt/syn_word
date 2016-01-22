package derrick.dict.syndict;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SynDict {
	// invert index:word_name,vectorId
	public static Map<String, List<Integer>> str2vec = new HashMap<>();
	public static List<SynItem> synDict = new ArrayList<>();
	public static boolean ifInited=false;
	
	public static void writeSyn(String filePath){
		
	}
}
