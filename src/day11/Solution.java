package day11;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Solution {

  long dfs(String val, int depth) {
    if (depth == 25) {
      return 1;
    }

    long cnt = 0L;

    switch (val.length() % 2) {
      case 0:
        String p1 = val.substring(0, val.length() / 2);
        String p2 = val.substring(val.length() / 2).replaceAll("^0+(?!$)", "");
        cnt = dfs(p1, depth + 1) + dfs(p2, depth + 1);
        break;

      case 1:
        if (val.equals("0")) {
          cnt = dfs("1", depth + 1);
        } else {
          String nv = Long.toString(Long.parseLong(val) * 2024);
          cnt = dfs(nv, depth + 1);
        }
        break;
    }

    return cnt;
  }

  long start() {
    long ans = 0L;
    String dir = System.getProperty("user.dir") + "/src";
    File file = new File(dir + "/day11/input.txt");

    try {
      Scanner sc = new Scanner(file);
      String input = sc.nextLine();

      String[] vals = input.split(" ");

      for (int i = 0; i < vals.length; i++) {
        ans += dfs(vals[i], 0);
      }

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