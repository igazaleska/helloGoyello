package com.izaleska.counter;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.SortedSet;
import java.util.TreeSet;

public class WordCounter {

	private Map<String, Integer> wordMap = new HashMap<>();

	public void countWordsFromFile(String filePath) {
		try (InputStream is = new FileInputStream(new File(filePath))) {
			BufferedReader br = new BufferedReader(new InputStreamReader(is, "UTF-8"));

			String line;
			while ((line = br.readLine()) != null) {
				processLine(line);
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
		sortMapByWordCount();
	}

	private void processLine(String line) {
		String[] words = line.split(" ");

		for (String word : words) {
			Integer wordCount = wordMap.get(word);
			if (wordCount != null) {
				wordCount++;
				wordMap.put(word, wordCount);
			} else {
				wordMap.put(word, 1);
			}

		}
	}

	private void sortMapByWordCount() {
		SortedSet<Map.Entry<String, Integer>> sortedset = new TreeSet<Map.Entry<String, Integer>>(
				new Comparator<Map.Entry<String, Integer>>() {
					@Override
					public int compare(Map.Entry<String, Integer> e1, Map.Entry<String, Integer> e2) {
						if (e1.getValue() == (e2.getValue())) {
							return 1;
						}
						return e1.getValue().compareTo(e2.getValue());
					}
				});
		System.out.println("wordMap: " + wordMap);
		System.out.println("entrySet: " + wordMap.entrySet());
		sortedset.addAll(wordMap.entrySet());
		System.out.println("Sorted values: " + sortedset);

	}

	public static void main(String[] args) {
		WordCounter wc = new WordCounter();
		wc.countWordsFromFile("bin/com/izaleska/counter/nirvana.txt");
	}

}
