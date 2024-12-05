package day5;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Solution {
  int start() {
    String dir = System.getProperty("user.dir") + "/src";
    File file = new File(dir + "/day5/input.txt");

    int ans = 0;
    try {
      Scanner sc = new Scanner(file);
      Set<String> set = new HashSet<>();

      boolean isFirstHalf = true;
      while (sc.hasNextLine()) {
        String line = sc.nextLine();
        if (line.equals("")) {
          isFirstHalf = false;
          continue;
        }

        if (isFirstHalf) {
          set.add(line);
        } else {
          String[] nums = line.split(",");
          boolean isValid = true;
          for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
              String str = nums[j] + "|" + nums[i];
              if (set.contains(str)) {
                isValid = false;
                break;
              }
            }
            if (!isValid) {
              break;
            }
          }

//          // Part 1
//          if (isValid) {
//            int val = Integer.parseInt(nums[nums.length / 2]);
//            ans += val;
//            System.out.println("valid " + val);
//          } else {
//            System.out.println("invalid");
//          }

          // Part 2
          if (!isValid) {
            Arrays.sort(nums, (a, b) -> {
              String str = a + "|" + b;
              if (set.contains(str)) {
                return -1;
              }
              return 0;
            });

            int val = Integer.parseInt(nums[nums.length / 2]);
            ans += val;
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