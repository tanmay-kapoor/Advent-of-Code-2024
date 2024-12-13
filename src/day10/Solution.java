package day10;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class Solution {
  int start() {
    String dir = System.getProperty("user.dir") + "/src";
    File file = new File(dir + "/day10/input.txt");

    int ans = 0;
    try {
      Scanner sc = new Scanner(file);
      List<String> list = new ArrayList<>();

      while (sc.hasNextLine()) {
        String line = sc.nextLine();
        list.add(line);
      }

      int[][] grid = new int[list.size()][list.getFirst().length()];

      for (int i = 0; i < list.size(); i++) {
        for (int j = 0; j < list.get(i).length(); j++) {
          grid[i][j] = list.get(i).charAt(j) - '0';
        }
      }

      for (int i = 0; i < grid.length; i++) {
        for (int j = 0; j < grid[i].length; j++) {
          if (grid[i][j] == 0) {
            Set<String> set = new HashSet<>();
            dfs(grid, i, j, 0, set);
            ans += set.size();
          }
        }
      }

      return ans;
    } catch (FileNotFoundException e) {
      System.out.println("File not found");
    }
    return ans;
  }

  void dfs(int[][] grid, int i, int j, int reqd, Set<String> set) {
    if (i < 0 || i >= grid.length || j < 0 || j >= grid[i].length || grid[i][j] != reqd) {
      return;
    }

    if (grid[i][j] == 9) {
      set.add(i + "," + j);
      return;
    }

    dfs(grid, i, j + 1, reqd + 1, set);
    dfs(grid, i + 1, j, reqd + 1, set);
    dfs(grid, i, j - 1, reqd + 1, set);
    dfs(grid, i - 1, j, reqd + 1, set);
  }

  public static void main(String[] args) {
    Solution ob = new Solution();
    System.out.println(ob.start());
  }
}