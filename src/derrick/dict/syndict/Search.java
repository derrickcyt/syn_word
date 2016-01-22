package derrick.dict.syndict;

import java.util.ArrayList;
import java.util.List;

import derrick.dict.syndict.integration.CilinReader;

public class Search {

	public static List<SynItem> search(String word) {
		// check if inited
		// if(!SynDict.ifInited)
		if (SynDict.str2vec.containsKey(word)) {
			List<Integer> vecIds = SynDict.str2vec.get(word);
			List<SynItem> items = new ArrayList<>();
			for (int vecId : vecIds) {
				items.add(SynDict.synDict.get(vecId));
			}
			return items;
		}
		return null;
	}

	public static void main(String[] args) {
		new CilinReader().read("rawdict/哈工大信息检索研究中心同义词词林扩展版.txt", "GBK");
		List<SynItem> sis = search("屌丝");
		if (sis != null) {
			for (SynItem si : sis) {
				System.out.println("=======");
				for (String word : si.getWordList()) {
					System.out.println(word);
				}
				System.out.println("=======");
			}
		}else{
			System.out.println("没有这个词");
		}
		

	}
}
