/*
Ibrahim Ansari
Period 7
03/6/2015

EncryptXOR

Time Spent: 25 minutes

Reflection:
This was fairly easy lab. It was straight-forward and explained well. The only problem for me was choosing a Class for
the IO operations. I choose the BufferedInputStream and the FileOutputStream because I have some previous experience
with them. I used them for a Android project that had Text file IO, so I was comfortable using them. Overall a easy lab.
 */

import java.io.*;
import java.util.Scanner;

public class EncryptXORIAnsariPeriod7 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String in, out, cipher;
        System.out.print("Enter a file to encrypt> ");
        in = sc.next();
        System.out.print("Enter a file to output> ");
        out = sc.next();
        System.out.print("Enter a private key> ");
        cipher = sc.next();
        encrypt(in, out, cipher);
    }

    public static void encrypt(String inFile, String outFile, String cipher) {
        try {
            char[] key = cipher.toCharArray();
            BufferedInputStream in = new BufferedInputStream(new FileInputStream(inFile), 2048);
            FileOutputStream out = new FileOutputStream(outFile);
            int read;
            int totalRead = 0;
            do {
                read = in.read();
                out.write(read ^ key[totalRead % (key.length - 1)]);
                totalRead++;
            } while (read != -1);
            in.close();
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
