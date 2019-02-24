package tree;

import java.io.File;
import java.util.LinkedList;

public class Tree {
	public static void tree(String filename, LinkedList<String> formats) {
		File[] subFiles = new File(filename).listFiles();
		for (int i = 0; i < subFiles.length; ++i) {
			//print format strings
			for (String format : formats) {
				System.out.print(format);
			}
			//add box drawings
			if (i == subFiles.length - 1) {
				System.out.print("©¸©¤");
			}
			else {
				System.out.print("©À©¤");
			}
			//give filename
			System.out.print(subFiles[i].getName());
			
			if (subFiles[i].isDirectory()) {
				System.out.println("/");

				if (i == subFiles.length - 1) {
					formats.add("   ");
				}
				else {
					formats.add("©¦  ");
				}
				tree(subFiles[i].getAbsolutePath(), formats);
				formats.removeLast();
			}
			else {
				System.out.println();
			}
		}
	}
	
	/**
	 * @author cz
	 * @param filename The path to root of your file tree
	 * @return void
	 */
	public static void tree(String filename) {
		File f = new File(filename);
		
		//check existence
		if (!f.exists()) {
			System.out.println("File does not exist, please check abs path.");
			return;
		}
		
		String name = f.getName();
		if (f.isDirectory()) {
			name += "/";
		}
		System.out.println(name);
		
		LinkedList<String> formats = new LinkedList<String>();
		formats.add(" ");
		tree(filename, formats);
	}

	public static void main(String[] args) {
		if (args.length != 0) {
			tree(args[0]);
		}
		else {
			tree(".");
		}
	}
}
