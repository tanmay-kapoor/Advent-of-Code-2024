package day15;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Solution {
  Map<Character, int[]> map;

  public Solution() {
    map = new HashMap<>();
    map.put('>', new int[]{0, 1});
    map.put('v', new int[]{1, 0});
    map.put('<', new int[]{0, -1});
    map.put('^', new int[]{-1, 0});
  }

  void print(List<char[]> grid) {
    for (char[] row : grid) {
      for (char c : row) {
        System.out.print(c);
      }
      System.out.println();
    }
  }

  long calc(List<char[]> grid) {
    long ans = 0L;
    for (int i = 0; i < grid.size(); i++) {
      for (int j = 0; j < grid.get(i).length; j++) {
        if (grid.get(i)[j] == 'O') {
          long val = 100L * i + j;
          ans += val;
        }
      }
    }
    return ans;
  }

  static class Location {
    int row, col;

    public Location(int row, int col) {
      this.row = row;
      this.col = col;
    }

    public String toString() {
      return "(" + row + ", " + col + ")";
    }
  }

  Location move(List<char[]> grid, Location loc, char move) {
    int[] vals = map.get(move);

    boolean found = false;
    int boxes = 0;

    int i = loc.row + vals[0];
    int j = loc.col + vals[1];
    while (i > 0 && j > 0 && i < grid.size() - 1 && j < grid.get(i).length - 1) {
      if (grid.get(i)[j] == '.') {
        found = true;
        break;
      } else if (grid.get(i)[j] == '#') {
        break;
      } else {
        boxes++;
      }
      i += vals[0];
      j += vals[1];
    }

    if (!found) {
      return loc;
    }

    Location nextLoc = new Location(loc.row + vals[0], loc.col + vals[1]);
    grid.get(loc.row)[loc.col] = '.';
    grid.get(loc.row + vals[0])[loc.col + vals[1]] = '@';
    if (boxes > 0) {
      grid.get(loc.row + (boxes + 1) * vals[0])[loc.col + (boxes + 1) * vals[1]] = 'O';
    }
    return nextLoc;
  }

  long start() {
    String dir = System.getProperty("user.dir") + "/src";
    File file = new File(dir + "/day15/input.txt");

    long ans = 0L;
    try {
      Scanner sc = new Scanner(file);
      List<char[]> grid = new ArrayList<>();
      int currRow = -1, currCol = -1;

      while (sc.hasNextLine()) {
        String line = sc.nextLine();
        if (line.isEmpty()) {
          break;
        }
        if (line.contains("@")) {
          currRow = grid.size();
          currCol = line.indexOf("@");
        }
        grid.add(line.toCharArray());
      }

      Location loc = new Location(currRow, currCol);
      while (sc.hasNextLine()) {
        String moves = sc.nextLine();
        for (int i = 0; i < moves.length(); i++) {
          loc = move(grid, loc, moves.charAt(i));
        }
      }
      print(grid);
      System.out.println(loc);

      return calc(grid);
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