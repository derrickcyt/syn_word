package derrick.dict.syndict.integration;

import java.util.ArrayList;
import java.util.List;

import derrick.dict.syndict.SynDict;
import derrick.dict.syndict.SynItem;
import derrick.io.file.TextWriter;

public class CommonReader extends DictReader {

	@Override
	public SynItem handleRawLine(String line) {
		String[] arr = line.trim().split("→");
		if (arr.length < 2)
			return null;
		List<String> wordList = new ArrayList<>();
		wordList.add(arr[0]);
		wordList.add(arr[1]);
		return new SynItem(wordList);
	}

	public static void main(String[] args) {
		new CilinReader().read("rawdict/哈工大信息检索研究中心同义词词林扩展版.txt", "GBK");
		new CommonReader().read("rawdict/2万同义词库.txt", "GBK");
		System.out.println(SynDict.str2vec.size());
		System.out.println(SynDict.synDict.size());

		SynDict.writeSyn(SynDict.synDictPath);
	}

}
