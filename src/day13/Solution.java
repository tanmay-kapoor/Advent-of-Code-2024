package day13;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Solution {
  long calcTokens(long[] x, long[] y, long px, long py) {
    long c1 = x[0] * y[0];

    long c2 = x[1] * y[0];
    long v1 = y[0] * px;

    long c3 = x[0] * y[1];
    long v2 = x[0] * py;

    double val = (v1 - v2) / (c2 - c3 + 0.0);
    if (val != (long) val) {
      return 0;
    }

    long b = (long) val;

    val = (v1 - c2 * b) / (c1 + 0.0);
    if (val != (long) val) {
      return 0;
    }

    long a = (long) val;

    return a * 3 + b;
  }

  long start() {
    String dir = System.getProperty("user.dir") + "/src";
    File file = new File(dir + "/day13/input.txt");

    long ans = 0;
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
        long[] x = new long[2];
        long[] y = new long[2];
        long px = -1, py = -1;
        int i = 0;

        for (String line : lines) {
          Matcher matcher = pattern.matcher(line);
          if (matcher.find()) {
            // Extract components
            String xValue = matcher.group(2); // X coordinate
            String yValue = matcher.group(3); // Y coordinate

            x[i] = Long.parseLong(xValue);
            y[i++] = Long.parseLong(yValue);
          } else {
            px = Long.parseLong(vals[0]) + 10000000000000L;
            py = Long.parseLong(vals[1]) + 10000000000000L;
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