package tree;

import java.io.File;
import java.util.LinkedList;

/**
 * 实现Windows命令行tree命令的部分功能
 * 删去了对硬盘信息的输出
 * 增加了对当前目录的输出（即使没有子文件夹）
 * @author cz
 * @version 0.9
 */
public class Tree {
	/**
	 * tree外部接口的内部实现，递归输出当前文件夹（如果是）中所有子文件
	 * @param filename 结点文件夹路径
	 * @param formats 制表符格式栈
	 */
	private static void tree(String filename, LinkedList<String> formats) {
		File[] subFiles = new File(filename).listFiles();
		for (int i = 0; i < subFiles.length; ++i) {
			//print format strings
			for (String format : formats) {
				System.out.print(format);
			}
			//add box drawings
			if (i == subFiles.length - 1) {
				System.out.print("└─");
			}
			else {
				System.out.print("├─");
			}
			//give filename
			System.out.print(subFiles[i].getName());
			
			if (subFiles[i].isDirectory()) {
				System.out.println("/");

				if (i == subFiles.length - 1) {
					formats.add("   ");
				}
				else {
					formats.add("│  ");
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
	 * 外部调用的入口，以文件树形式输出路径filename下自身及所有子文件
	 * @param filename 文件树根路径
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
