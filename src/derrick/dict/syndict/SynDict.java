package derrick.dict.syndict;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import derrick.io.file.TextReader;
import derrick.io.file.TextWriter;

public class SynDict {
	// invert index:word_name,vectorId
	public static Map<String, List<Integer>> str2vec = new HashMap<>();
	public static List<SynItem> synDict = new ArrayList<>();
	public static boolean ifInited = false;
	public static String synDictPath = "syndict/syndict.txt";
	public static String encoding = "GBK";
	public static int wordNum = 0;

	public static void writeSyn(String filePath) {
		List<String> outputdata = new ArrayList<>();
		for (SynItem si : SynDict.synDict) {
			StringBuilder sb = new StringBuilder();
			for (String word : si.getWordList()) {
				sb.append(word + " ");
			}
			outputdata.add(sb.toString().trim());
		}

		TextWriter.write(outputdata, filePath, encoding);
	}

	public static void init() {
		List<String> data = TextReader.read(synDictPath, encoding);
		for (String line : data) {
			List<String> wordList = Arrays.asList(line.trim().split(" "));
			SynItem si = new SynItem(wordList);
			SynDict.synDict.add(si);
			for (String word : wordList) {
				if (str2vec.containsKey(word)) {
					str2vec.get(word).add(synDict.size() - 1);
				} else {
					List<Integer> vecIds = new ArrayList<>();
					vecIds.add(synDict.size() - 1);
					str2vec.put(word, vecIds);
				}
			}
		}
		ifInited = true;
	}
}
