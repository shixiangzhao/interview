package com.shixzh.java.interview.file;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class BigFileSort {

    private static final String FILE_PATH_1 = "D:\\tmp\\files\\big1g1.txt";
    private static final String FILE_PATH_2 = "D:\\tmp\\files\\big1g2.txt";
    private static final String FILE_PATH_r = "D:\\tmp\\files\\result.txt";

    public static void main(String[] args) {
        File fa = new File(FILE_PATH_1);
        File fb = new File(FILE_PATH_2);
        File fr = new File(FILE_PATH_r);

        InputStreamReader ina = null;
        InputStreamReader inb = null;
        OutputStreamWriter outr = null;
        BufferedReader bra = null;
        BufferedReader brb = null;
        BufferedWriter bwr = null;
        try {

            bra = new BufferedReader(new FileReader(fa));
            brb = new BufferedReader(new FileReader(fb));
            bwr = new BufferedWriter(new FileWriter(fr));

            // FileInputStream 可以设置文件流的编码格式
            ina = new InputStreamReader(new FileInputStream(fa), "GBK");
            inb = new InputStreamReader(new FileInputStream(fb), "GBK");
            outr = new OutputStreamWriter(new FileOutputStream(fr), "GBK");
            // bra = new BufferedReader(ina);
            // brb = new BufferedReader(inb);
            // bwr = new BufferedWriter(outr);

            String tempa = bra.readLine();
            String tempb = brb.readLine();
            while (tempa != null || tempb != null) {
                int ida = 0, idb = 0;
                if (tempa != null) {
                    String[] arraya = tempa.split(" ");
                    ida = Integer.parseInt(arraya[0]);
                    System.out.println(ida);
                }
                if (tempb != null) {
                    String[] arrayb = tempb.split(" ");
                    idb = Integer.parseInt(arrayb[0]);
                    System.out.println(idb);
                }
                if (tempa != null && tempb != null && ida < idb) {
                    bwr.write(String.valueOf(ida));
                    bwr.newLine();
                    tempa = bra.readLine();
                } else if (tempa != null && tempb != null && ida >= idb) {
                    bwr.write(String.valueOf(idb));
                    bwr.newLine();
                    tempb = brb.readLine();
                } else if (tempa != null && tempb == null) {
                    bwr.write(String.valueOf(ida));
                    bwr.newLine();
                    tempa = bra.readLine();
                } else if (tempa == null && tempb != null) {
                    bwr.write(String.valueOf(idb));
                    bwr.newLine();
                    tempb = brb.readLine();
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                bwr.flush();
                bwr.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
