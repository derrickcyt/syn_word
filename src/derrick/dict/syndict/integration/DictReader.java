package derrick.dict.syndict.integration;

import java.util.ArrayList;
import java.util.List;

import derrick.dict.syndict.SynDict;
import derrick.dict.syndict.SynItem;
import derrick.io.file.TextReader;

public abstract class DictReader {

	/**
	 * handle one String line.
	 * 
	 * @param line
	 * @return SynItem or null
	 */
	public abstract SynItem handleRawLine(String line);

	public void read(String filePath) {
		String encoding = "UTF-8";
		read(filePath, encoding);
	}

	/**
	 * start reading dict
	 * 
	 * @param filePath
	 * @param encoding
	 */
	public void read(String filePath, String encoding) {
		List<String> rawData = TextReader.read(filePath, encoding);
		for (String line : rawData) {
			SynItem item = handleRawLine(line);
			if (item != null)
				add2SynDict(item);
		}
	}

	/**
	 * add item to dict. if item is pair-word, using addPair2SynDict method.
	 * 
	 * @param si
	 */
	public void add2SynDict(SynItem si) {
		if (si.getWordList().size() == 2) {
			addPair2SynDict(si);
		} else {
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
		}
	}

	/**
	 * add pair item to syndict. e.g. 古貌古心→古道热肠
	 * 
	 * @param si
	 */
	public void addPair2SynDict(SynItem si) {
		// check if syndict has this word
		boolean ifExisted = false;// flag
		int vecId = -1;// item index
		for (String word : si.getWordList()) {
			if (SynDict.str2vec.containsKey(word)) {
				ifExisted = true;
				List<Integer> vecIds = SynDict.str2vec.get(word);
				vecId = vecIds.get(0);
				break;
			}
		}

		if (ifExisted) {
			List<String> wordList = SynDict.synDict.get(vecId).getWordList();
			for (String word : si.getWordList()) {
				if (!SynDict.str2vec.containsKey(word)) {
					List<Integer> vecIds = new ArrayList<>();
					vecIds.add(vecId);
					SynDict.str2vec.put(word, vecIds);
					wordList.add(word);
				}
			}
			SynDict.synDict.get(vecId).setWordList(wordList);
		} else {
			// new an item
			SynDict.synDict.add(si);
			for (String word : si.getWordList()) {
				List<Integer> vecIds = new ArrayList<>();
				vecIds.add(SynDict.synDict.size() - 1);
				SynDict.str2vec.put(word, vecIds);
			}
		}
	}

}
