package day1;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

public class Part1 {
  int start() {
    String dir = System.getProperty("user.dir") + "/src";
    File file = new File(dir + "/day1/sample.txt");

    int ans = 0;
    try {
      Scanner sc = new Scanner(file);
      
      List<Integer> nums1 = new ArrayList<>();
      List<Integer> nums2 = new ArrayList<>();

      while (sc.hasNextLine()) {
        String line = sc.nextLine();
        String[] vals = line.split("\\s+");
        nums1.add(Integer.parseInt(vals[0]));
        nums2.add(Integer.parseInt(vals[1]));
      }

      Collections.sort(nums1);
      Collections.sort(nums2);

      for(int i = 0; i < nums1.size(); i++) {
        int num1 = nums1.get(i);
        int num2 = nums2.get(i);
        int dist = Math.abs(num1 - num2);
        ans += dist;
      }

      return ans;
    } catch (FileNotFoundException e) {
      System.out.println("File not found");
    }
    return ans;
  }

  public static void main(String[] args) {
    Part1 ob = new Part1();
    System.out.println(ob.start());
  }
}
