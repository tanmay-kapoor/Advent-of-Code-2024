package day10;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

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
            ans += dfs(grid, i, j, 0);
          }
        }
      }

      return ans;
    } catch (FileNotFoundException e) {
      System.out.println("File not found");
    }
    return ans;
  }

  int dfs(int[][] grid, int i, int j, int reqd) {
    if (i < 0 || i >= grid.length || j < 0 || j >= grid[i].length || grid[i][j] == -1 || grid[i][j] != reqd) {
      return 0;
    }

    if (grid[i][j] == 9) return 1;
    grid[i][j] = -1;

    int cnt = 0;
    cnt += dfs(grid, i, j + 1, reqd + 1);
    cnt += dfs(grid, i + 1, j, reqd + 1);
    cnt += dfs(grid, i, j - 1, reqd + 1);
    cnt += dfs(grid, i - 1, j, reqd + 1);

    grid[i][j] = reqd;

    return cnt;
  }

  public static void main(String[] args) {
    Solution ob = new Solution();
    System.out.println(ob.start());
  }
}