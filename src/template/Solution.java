package template;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Solution {
  int dayNum;
  String aocDir, fileName, inputFilePath;


  long start() throws FileNotFoundException {
    Scanner sc = new Scanner(new File(inputFilePath));
    long ans = 0L;

    while (sc.hasNextLine()) {
      String line = sc.nextLine();
      System.out.println(line);
    }

    return ans;
  }

  public Solution() {
    this.aocDir = System.getProperty("user.dir");
    this.dayNum = 0;
    this.fileName = "sample.txt";
    this.inputFilePath = String.format("%s/src/day%02d/%s", aocDir, dayNum, fileName);
  }

  public static void main(String[] args) {
    Solution ob = new Solution();
    try {
      System.out.println(ob.start());
    } catch (FileNotFoundException e) {
      System.out.println("File not found");
    }
  }
}