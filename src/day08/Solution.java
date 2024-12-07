package day08;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Solution {
  int start() {
    String dir = System.getProperty("user.dir") + "/src";
    File file = new File(dir + "/day08/sample.txt");

    int ans = 0;
    try {
      Scanner sc = new Scanner(file);

      while (sc.hasNextLine()) {
        String line = sc.nextLine();

      }

      return ans;
    } catch (FileNotFoundException e) {
      System.out.println("File not found");
    }
    return ans;
  }

  public static void main(String[] args) {
    Solution ob = new Solution();
    System.out.println(ob.start());
  }
}