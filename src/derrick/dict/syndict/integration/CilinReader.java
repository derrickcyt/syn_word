package derrick.dict.syndict.integration;

import java.util.ArrayList;
import java.util.List;

import derrick.dict.syndict.SynDict;
import derrick.dict.syndict.SynItem;

public class CilinReader extends DictReader {

	@Override
	public SynItem handleRawLine(String line) {
		String[] item = line.split("=");
		if (item.length >= 2) {
			String[] arr = item[1].trim().split(" ");
			List<String> wordList = new ArrayList<>();
			for (String word : arr) {
				wordList.add(word);
			}
			return new SynItem(wordList);
		}
		return null;
	}

	public static void main(String[] args) {
		new CilinReader().read("rawdict/哈工大信息检索研究中心同义词词林扩展版.txt", "GBK");
		System.out.println(SynDict.str2vec.size());
		System.out.println(SynDict.synDict.size());
	}

}
