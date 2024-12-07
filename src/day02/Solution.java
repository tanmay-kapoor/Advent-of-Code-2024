package day02;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Solution {

  boolean isIncreasing(int[] arr, int toSkip) {
    int prev = toSkip == 0 ? 1 : 0;
    int start = toSkip == 0 ? 2 : 1;

    for (int i = start; i < arr.length; i++) {
      if (i == toSkip) continue;
      if (arr[i] - arr[prev] < 1 || arr[i] - arr[prev] > 3) {
        return false;
      }
      prev = i;
    }
    return true;
  }

  boolean isDecreasing(int[] arr, int toSkip) {
    int prev = toSkip == 0 ? 1 : 0;
    int start = toSkip == 0 ? 2 : 1;

    for (int i = start; i < arr.length; i++) {
      if (i == toSkip) continue;
      if (arr[i] - arr[prev] > -1 || arr[i] - arr[prev] < -3) {
        return false;
      }
      prev = i;
    }
    return true;
  }

  int start() {
    String dir = System.getProperty("user.dir") + "/src";
    File file = new File(dir + "/day02/input.txt");

    int ans = 0;
    try {
      Scanner sc = new Scanner(file);

      while (sc.hasNextLine()) {
        String line = sc.nextLine();
        String[] levels = line.split("\\s+");
        int[] arr = new int[levels.length];
        for (int i = 0; i < levels.length; i++) {
          arr[i] = Integer.parseInt(levels[i]);
        }

//        // Part 1
//        if (isIncreasing(arr, -1) || isDecreasing(arr, -1)) {
//          ans++;
//        }

        // Part 2
        for (int i = 0; i < arr.length; i++) {
          if (isIncreasing(arr, i) || isDecreasing(arr, i)) {
            ans++;
            break;
          }
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