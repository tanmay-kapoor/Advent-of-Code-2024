package day13;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Solution {
  int calcTokens(int[] x, int[] y, int px, int py) {
    int c1 = x[0] * y[0];

    int c2 = x[1] * y[0];
    int v1 = y[0] * px;

    int c3 = x[0] * y[1];
    int v2 = x[0] * py;

    double val = (v1 - v2) / (c2 - c3 + 0.0);
    if (val != (int) val) {
      return 0;
    }

    int b = (int) val;

    val = (v1 - c2 * b) / (c1 + 0.0);
    if (val != (int) val) {
      return 0;
    }

    int a = (int) val;

    return a * 3 + b;
  }

  int start() {
    String dir = System.getProperty("user.dir") + "/src";
    File file = new File(dir + "/day13/input.txt");

    int ans = 0;
    try {
      Scanner sc = new Scanner(file);

      while (sc.hasNextLine()) {
        String buttonA = sc.nextLine();
        String buttonB = sc.nextLine();
        String prize = sc.nextLine();
        String[] lines = {buttonA, buttonB, prize};
        sc.nextLine();

        String regex = "^(Button A:|Button B:|Prize:)\\s*X([-+]?\\d+),\\s*Y([-+]?\\d+)$";
        Pattern pattern = Pattern.compile(regex);

        String[] vals = prize.split(", ");
        vals[0] = vals[0].substring(vals[0].indexOf("=") + 1);
        vals[1] = vals[1].substring(2);
        int[] x = new int[2];
        int[] y = new int[2];
        int px = -1, py = -1, i = 0;

        for (String line : lines) {
          Matcher matcher = pattern.matcher(line);
          if (matcher.find()) {
            // Extract components
            String prefix = matcher.group(1); // Button A:, Button B:, Prize:
            String xValue = matcher.group(2); // X coordinate
            String yValue = matcher.group(3); // Y coordinate

            // Store into a string array
            String[] result = {prefix, xValue, yValue};
            x[i] = Integer.parseInt(xValue);
            y[i++] = Integer.parseInt(yValue);
          } else {
            px = Integer.parseInt(vals[0]);
            py = Integer.parseInt(vals[1]);
          }
        }

        ans += calcTokens(x, y, px, py);
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