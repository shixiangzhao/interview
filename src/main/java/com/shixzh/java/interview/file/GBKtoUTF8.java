package com.shixzh.java.interview.file;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.nio.charset.Charset;

/**
 * 将一个GBK编码的文本文件转存为一个UTF-8编码的文本文件。
 * @author Shixiang
 *
 */
public class GBKtoUTF8 {
	public static void fileList(File file) {
		File rootFile = file;
		File[] files = rootFile.listFiles();
		if(files != null) {
			for( File f: files) {
				if ( !f.isDirectory()){
					codeConvert(f);
				}
				System.out.println(f.getPath());
				fileList(f);
			}
		}
	}
	
	public static void fileList1(File file, int node) {
		node++;
		File rootFile = file;
		File[] files = rootFile.listFiles();
		if(files != null) {
			for( File f: files) {
				for(int i=0; i< node;i++) {
					 if(i ==node-1) {
						 System.out.print("|-");
					 } else {
						 System.out.print(" ");
					 }
					System.out.println(f.getName());
					fileList1(f, node);
				}
			}
		}
	}
	
	public static void codeConvert(File file) {
		try {
			BufferedReader br = new BufferedReader(
					new InputStreamReader(
							new FileInputStream(file), Charset.forName("GBK")));
			StringBuilder sb = new StringBuilder();
			String str;
			while((str = br.readLine())!= null) {
				sb.append(str);
				sb.append("\n");
			}
			BufferedWriter bw = new BufferedWriter(
					new OutputStreamWriter(
							new FileOutputStream(file), Charset.forName("UTF-8")));
			bw.write(sb.toString());
			bw.flush();
			bw.close();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		File file = new File("");
		GBKtoUTF8.fileList(file);
	}
}