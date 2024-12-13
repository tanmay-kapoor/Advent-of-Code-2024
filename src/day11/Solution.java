package day11;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Solution {
  int start() {
    String dir = System.getProperty("user.dir") + "/src";
    File file = new File(dir + "/day11/input.txt");

    int ans = 0;
    try {
      Scanner sc = new Scanner(file);
      String input = sc.nextLine();
      StringBuilder str = new StringBuilder(input);

      for (int i = 0; i < 25; i++) {
//        System.out.println(i);
        String[] arr = str.toString().split(" ");
        str = new StringBuilder();
        for (String val : arr) {
          if (val.equals("0")) {
            str.append("1");
          } else if (val.length() % 2 == 1) {
            str.append(Long.parseLong(val) * 2024);
          } else {
            String p1 = val.substring(0, val.length() / 2);
            str.append(p1).append(" ");

            String p2 = val.substring(val.length() / 2);
            str.append(Long.parseLong(p2));
          }

          str.append(" ");
        }
      }

      return str.toString().trim().split(" ").length;
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