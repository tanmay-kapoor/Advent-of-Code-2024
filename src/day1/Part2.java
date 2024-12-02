package day1;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Part2 {
  int start() {
    String dir = System.getProperty("user.dir") + "/src";
    File file = new File(dir + "/day1/sample.txt");

    int ans = 0;
    try {
      Scanner sc = new Scanner(file);

      List<Integer> nums1 = new ArrayList<>();

      Map<Integer, Integer> map = new HashMap<>();

      while (sc.hasNextLine()) {
        String line = sc.nextLine();
        String[] vals = line.split("\\s+");
        int num1 = Integer.parseInt(vals[0]);
        int num2 = Integer.parseInt(vals[1]);
        nums1.add(num1);

        map.put(num2, map.getOrDefault(num2, 0) + 1);
      }

      for (int i = 0; i < nums1.size(); i++) {
        int num = nums1.get(i);
        ans = ans + (num * map.getOrDefault(num, 0));
      }

      return ans;
    } catch (FileNotFoundException e) {
      System.out.println("File not found");
    }
    return ans;
  }

  public static void main(String[] args) {
    Part2 ob = new Part2();
    System.out.println(ob.start());
  }
}