package day12;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class Solution {
  int area = 0;

  void print(List<char[]> grid) {
    for (char[] ch : grid) {
      for (char c : ch) {
        System.out.print(c);
      }
      System.out.println();
    }
    System.out.println();
  }

  List<char[]> copy(List<char[]> grid) {
    List<char[]> copy = new ArrayList<>();
    for (char[] ch : grid) {
      copy.add(ch.clone());
    }
    return copy;
  }

  int dfs(List<char[]> grid, int i, int j, char letter, Set<String> set) {
    if (i < 0 || j < 0 || i >= grid.size() || j >= grid.get(i).length) {
      return 1;
    }

    if (grid.get(i)[j] == '.') {
      return 0;
    }

    if (grid.get(i)[j] != letter) {
      return 1;
    }

    grid.get(i)[j] = '.';
    set.add(i + "," + j);

    area++;
    int cnt = dfs(grid, i, j + 1, letter, set);
    cnt += dfs(grid, i + 1, j, letter, set);
    cnt += dfs(grid, i, j - 1, letter, set);
    cnt += dfs(grid, i - 1, j, letter, set);

    return cnt;
  }

  int start() {
    String dir = System.getProperty("user.dir") + "/src";
    File file = new File(dir + "/day12/input.txt");

    int ans = 0;
    try {
      Scanner sc = new Scanner(file);
      List<char[]> grid = new ArrayList<>();
      Set<String> set = new HashSet<>();

      while (sc.hasNextLine()) {
        String line = sc.nextLine();
        grid.add(line.toCharArray());
      }

      print(grid);

      for (int i = 0; i < grid.size(); i++) {
        for (int j = 0; j < grid.get(i).length; j++) {
          if (!set.contains(i + "," + j)) {
            area = 0;
            char letter = grid.get(i)[j];
            set.add(i + "," + j);
            int perimeter = dfs(copy(grid), i, j, letter, set);
            System.out.println("letter: " + letter + " area: " + area + " perimeter: " + perimeter);
            ans += (area * perimeter);
//            print(grid);
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