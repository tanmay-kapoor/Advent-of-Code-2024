package day7;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Solution {
  boolean isPossible(String exp, long[] nums, long target, long curr, int i) {
    if (curr > target) {
      return false;
    }

    if (curr == target) {
      if (i >= nums.length) {
        System.out.println(exp + " = " + curr);
        return true;
      }
    }

    if (i >= nums.length) {
      return false;
    }

    long newVal = Long.parseLong(Long.toString(curr).concat(Long.toString(nums[i])));

    return isPossible(exp + " + " + nums[i], nums, target, curr + nums[i], i + 1) ||
            isPossible(exp + " * " + nums[i], nums, target, curr * nums[i], i + 1) ||
            isPossible(exp + " || " + nums[i], nums, target, newVal, i + 1);
  }

  long start() {
    String dir = System.getProperty("user.dir") + "/src";
    File file = new File(dir + "/day7/input.txt");

    long ans = 0L;
    try {
      Scanner sc = new Scanner(file);

      while (sc.hasNextLine()) {
        String line = sc.nextLine();

        String[] vals = line.split(": ");
        long target = Long.parseLong(vals[0]);
        String[] n = vals[1].split(" ");
        long[] nums = new long[n.length];

        for (int i = 0; i < n.length; i++) {
          nums[i] = Long.parseLong(n[i]);
        }

        if (isPossible("", nums, target, 0, 0)) {
          ans += target;
        }
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