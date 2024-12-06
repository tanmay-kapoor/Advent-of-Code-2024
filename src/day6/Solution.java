package day6;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class Solution {

  int[] getNextValues(int row, int col, String dir) {
    int[] ans = new int[2];
    if (dir.equals("up")) {
      ans[0] = row - 1;
      ans[1] = col;
    } else if (dir.equals("down")) {
      ans[0] = row + 1;
      ans[1] = col;
    } else if (dir.equals("left")) {
      ans[0] = row;
      ans[1] = col - 1;
    } else {
      ans[0] = row;
      ans[1] = col + 1;
    }
    return ans;
  }

  String turn90Degree(String dir) {
    if (dir.equals("up")) {
      return "right";
    } else if (dir.equals("right")) {
      return "down";
    } else if (dir.equals("down")) {
      return "left";
    } else {
      return "up";
    }
  }

  int start() {
    String dire = System.getProperty("user.dir") + "/src";
    File file = new File(dire + "/day6/input.txt");

    try {
      Scanner sc = new Scanner(file);
      List<char[]> grid = new ArrayList<>();

      int i = 0, startRow = 0, startCol = 0;
      while (sc.hasNextLine()) {
        String line = sc.nextLine();
        int j = line.indexOf("^");
        if (j != -1) {
          startRow = i;
          startCol = j;
        }
        grid.add(line.toCharArray());
        i++;
      }

      Set<String> set = new HashSet<>();
      int currRow = startRow, currCol = startCol;
      String dir = "up";
      set.add("(" + currRow + "," + currCol + ")");

      while (true) {
        int[] newVals = getNextValues(currRow, currCol, dir);
        int newRow = newVals[0];
        int newCol = newVals[1];

        if (newRow < 0 || newRow >= grid.size() || newCol < 0 || newCol >= grid.getFirst().length) {
          break;
        }

        if (grid.get(newRow)[newCol] == '#') {
          dir = turn90Degree(dir);
        } else {
          currRow = newRow;
          currCol = newCol;
          set.add("(" + currRow + "," + currCol + ")");
        }
      }

      return set.size();
    } catch (FileNotFoundException e) {
      System.out.println("File not found");
    }
    return 0;
  }

  public static void main(String[] args) {
    Solution ob = new Solution();
    System.out.println(ob.start());
  }
}