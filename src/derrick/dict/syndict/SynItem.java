package derrick.dict.syndict;

import java.util.ArrayList;
import java.util.List;

public class SynItem {

	private List<String> wordList = new ArrayList<String>();

	public SynItem(List<String> wordList) {
		super();
		this.wordList = wordList;
	}

	public List<String> getWordList() {
		return wordList;
	}

	public void setWordList(List<String> wordList) {
		this.wordList = wordList;
	}

}
