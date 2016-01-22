package derrick.dict.syndict.integration;

import java.util.ArrayList;
import java.util.List;

import derrick.dict.syndict.SynDict;
import derrick.dict.syndict.SynItem;
import derrick.io.file.TextReader;

public abstract class DictReader {

	public abstract SynItem handleRawLine(String line);

	public void read(String filePath) {
		String encoding = "UTF-8";
		read(filePath, encoding);
	}

	public void read(String filePath, String encoding) {
		List<String> rawData = TextReader.read(filePath, encoding);
		for (String line : rawData) {
			SynItem item = handleRawLine(line);
			if (item != null)
				add2SynDict(item);
		}
	}

	/**
	 * add item to dict
	 * 
	 * @param si
	 */
	public void add2SynDict(SynItem si) {
		SynDict.synDict.add(si);
		int vecId = SynDict.synDict.size() - 1;
		for (String word : si.getWordList()) {
			if (SynDict.str2vec.containsKey(word)) {
				SynDict.str2vec.get(word).add(vecId);

			} else {
				List<Integer> vecIds = new ArrayList<>();
				vecIds.add(vecId);
				SynDict.str2vec.put(word, vecIds);
			}
		}

		// // check if syndict has this word
		// boolean ifExisted = false;// flag
		// int vecId = -1;// item index
		// for (String word : si.getWordList()) {
		// if (SynDict.str2vec.containsKey(word)) {
		// ifExisted = true;
		// vecId = SynDict.str2vec.get(word);
		// break;
		// }
		// }
		//
		// if (ifExisted) {
		// List<String> wordList = SynDict.synDict.get(vecId).getWordList();
		// for (String word : si.getWordList()) {
		// if (!SynDict.str2vec.containsKey(word)) {
		// SynDict.str2vec.put(word, vecId);
		// wordList.add(word);
		// }
		// }
		// SynDict.synDict.get(vecId).setWordList(wordList);
		// } else {
		// // new an item
		// SynDict.synDict.add(si);
		// for (String word : si.getWordList()) {
		// SynDict.str2vec.put(word, SynDict.synDict.size() - 1);
		// }
		// }

	}

}
