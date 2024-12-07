package day03;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Solution {
  long start() {
    String dir = System.getProperty("user.dir") + "/src";
    File file = new File(dir + "/day03/input.txt");

    long ans = 0L;
    try {
      Scanner sc = new Scanner(file);

      boolean shouldCompute = true;
      while (sc.hasNextLine()) {
        String line = sc.nextLine();
//        String regex = "mul\\((\\d{1,3}),(\\d{1,3})\\)"; // Part 1
        String regex = "mul\\((\\d{1,3}),(\\d{1,3})\\)|do\\(\\)|don't\\(\\)"; // Part 2

        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(line);

        List<String> matches = new ArrayList<>();
        while (matcher.find()) {
          matches.add(matcher.group());
        }

        for (String match : matches) {
          if (match.equals("do()")) {
            shouldCompute = true;
            continue;
          } else if (match.equals("don't()")) {
            shouldCompute = false;
            continue;
          }

          if (!shouldCompute) continue;

          String str = match.substring(4, match.length() - 1);
          String[] vals = str.split(",");

          int num1 = Integer.parseInt(vals[0]);
          int num2 = Integer.parseInt(vals[1]);

          ans += (long) num1 * num2;
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