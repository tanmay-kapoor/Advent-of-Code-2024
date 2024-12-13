package day11;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Solution {

  Map<Long, Long> dfs(Map<Long, Long> map, int depth) {
//    System.out.println(map);
    if (depth == 0) {
      return map;
    }

    Map<Long, Long> map2 = new HashMap<>();

    for (long num : map.keySet()) {
      long freq = map.get(num);

      if (num == 0L) {
        map2.put(1L, map2.getOrDefault(1L, 0L) + freq);
      } else {
        String str = Long.toString(num);

        if (str.length() % 2 == 1) {
          map2.put(num * 2024, map2.getOrDefault(num * 2024, 0L) + freq);
        } else {
          String p1 = str.substring(0, str.length() / 2);
          long num1 = Long.parseLong(p1);

          String p2 = str.substring(str.length() / 2);
          long num2 = Long.parseLong(p2);

          map2.put(num1, map2.getOrDefault(num1, 0L) + freq);
          map2.put(num2, map2.getOrDefault(num2, 0L) + freq);
        }
      }
    }

    return dfs(map2, depth - 1);
  }

  long start() {
    long ans = 0L;
    String dir = System.getProperty("user.dir") + "/src";
    File file = new File(dir + "/day11/input.txt");

    try {
      Scanner sc = new Scanner(file);
      String input = sc.nextLine();
      Map<Long, Long> map = new HashMap<>();

      String[] vals = input.split(" ");
      for (String val : vals) {
        long num = Long.parseLong(val);
        map.put(num, map.getOrDefault(num, 0L) + 1);
      }

      map = dfs(map, 75);
      for (Long num : map.keySet()) {
        ans += map.get(num);
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