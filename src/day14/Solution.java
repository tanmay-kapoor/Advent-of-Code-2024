package day14;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Solution {
  int width = 101, height = 103;

  static class Point {
    int x, y, vx, vy;

    public Point(int x, int y, int vx, int vy) {
      this.x = x;
      this.y = y;
      this.vx = vx;
      this.vy = vy;
    }

    public String toString() {
      return "(" + x + ", " + y + ")";
    }
  }

  void print(List<String> grid) {
    for (String s : grid) {
      System.out.println(s);
    }
    System.out.println();
  }

  List<String> getGrid(List<Point> list) {
    List<String> grid = new ArrayList<>();
    for (int i = 0; i < height; i++) {
      grid.add(".".repeat(Math.max(0, width)));
    }

    for (Point p : list) {
      StringBuilder sb = new StringBuilder(grid.get(p.y));
      sb.setCharAt(p.x, '#');
      grid.set(p.y, sb.toString());
    }

    return grid;
  }

  boolean tenInLine(List<String> grid) {
    for (int i = 0; i < height; i++) {
      if (grid.get(i).contains("##########")) {
        return true;
      }
    }
    return false;
  }

  int start() {
    String dir = System.getProperty("user.dir") + "/src";
    File file = new File(dir + "/day14/input.txt");

    int ans = 0, currSec = 1;
    try {
      Scanner sc = new Scanner(file);
      List<Point> list = new ArrayList<>();

      while (sc.hasNextLine()) {
        String line = sc.nextLine();
        String[] parts = line.split(" ");

        String[] vals1 = parts[0].split(",");
        int x = Integer.parseInt(vals1[0].substring(2));
        int y = Integer.parseInt(vals1[1]);

        String[] vals2 = parts[1].split(",");
        int vx = Integer.parseInt(vals2[0].substring(2));
        int vy = Integer.parseInt(vals2[1]);

        list.add(new Point(x, y, vx, vy));
      }

      while (true) {
        for (Point p : list) {
          p.x += p.vx;
          if (p.x < 0 && p.vx < 0) {
            p.x += width;
          } else if (p.x > width - 1 && p.vx >= 0) {
            p.x -= width;
          }

          p.y += p.vy;
          if (p.y < 0 && p.vy < 0) {
            p.y += height;
          } else if (p.y > height - 1 && p.vy >= 0) {
            p.y -= height;
          }
        }

        if (tenInLine(getGrid(list))) {
          print(getGrid(list));
          return currSec;
        }
        currSec++;
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