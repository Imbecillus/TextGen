package inout;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import data.Node;
import data.Tree;

public class InOut {

	public final static String FILE_IN = "D:\\sample.txt";
	public final static String FILE_OUT = "D:\\output.txt";
	public final static int MAX_CHARS_PER_LINE = 70;
	
	public Tree readFile(String fileName) throws IOException {
		log("##################### R E A D I N G #####################");
		Path path = Paths.get(fileName);
		Tree tree = new Tree();
		String word0 = "";
		String word1 = "";
		int status = -1; //-1 = first entry / 0 = word0 goes next / 1 = word1 goes next
		try (BufferedReader reader = Files.newBufferedReader(path)) {
			String line = null;
			while ((line = reader.readLine()) != null) {
				String[] words = line.split(" ");
				for (String s:words) {
					if (status == 0) {
						word1 = s.replaceAll("[^A-Za-z0-9 üצה]", "").toLowerCase();
						tree.addLink(word0, word1);
						status = 1;
					} else if (status == 1) {
						word0 = s.replaceAll("[^A-Za-z0-9 üצה]", "").toLowerCase();
						tree.addLink(word1, word0);
						status = 0;
					} else {
						word0 = s.replaceAll("[^A-Za-z0-9 üצה]", "").toLowerCase();
						status = 0;
					}
				}
				log(line);
			}
		}
		return tree;
	}
	
	public void writeFile(String fileName, Tree tree, int lenght) throws IOException {
		log("##################### W R I T I N G #####################");
		Path path = Paths.get(fileName);
		lenght = Math.abs(lenght);
		if (tree == null) return;
		tree.calculatePercentage(tree.getEntry());
		Node word = tree.getRandomEntry();
		int word_count = 0;
		String line = "";
		try (BufferedWriter writer = Files.newBufferedWriter(path)) {
			while (word_count < lenght) {
				if (word.getWord().length()+line.length()+1 > MAX_CHARS_PER_LINE) {
					writer.write(line);
					log(line);
					writer.newLine();
					line = "";
				}
				word_count++;
				line = line + word.getWord() + " ";
				word = word.getRandomNext();
				if (word == null) word = tree.getRandomEntry();
			}
		}
	}
	
	private static void log(Object message) {
		System.out.println(String.valueOf(message));
	}
	
}
