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
  int corners = 0;

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

    char left = grid.get(i)[j - 1];
    char right = grid.get(i)[j + 1];
    char up = grid.get(i - 1)[j];
    char down = grid.get(i + 1)[j];
    char leftDiagUp = grid.get(i - 1)[j - 1];
    char leftDiagDown = grid.get(i + 1)[j - 1];
    char rightDiagUp = grid.get(i - 1)[j + 1];
    char rightDiagDown = grid.get(i + 1)[j + 1];

    boolean l = left != letter && left != '.';
    boolean r = right != letter && right != '.';
    boolean u = up != letter && up != '.';
    boolean d = down != letter && down != '.';
    boolean lu = leftDiagUp != letter && leftDiagUp != '.';
    boolean ld = leftDiagDown != letter && leftDiagDown != '.';
    boolean ru = rightDiagUp != letter && rightDiagUp != '.';
    boolean rd = rightDiagDown != letter && rightDiagDown != '.';

    // top left outward corner
    if (l && u) {
      corners++;
    }

    // top left inward corner
    if (!l && !u && lu) {
      corners++;
    }

    // top right outward corner
    if (r && u) {
      corners++;
    }

    // top right inward corner
    if (!r && !u && ru) {
      corners++;
    }

    // bottom left outward corner
    if (l && d) {
      corners++;
    }

    // bottom left inward corner
    if (!l && !d && ld) {
      corners++;
    }

    // bottom right outward corner
    if (r && d) {
      corners++;
    }

    // bottom right inward corner
    if (!r && !d && rd) {
      corners++;
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
        String line = "1" + sc.nextLine() + "1";
        grid.add(line.toCharArray());
      }
      grid.addFirst("1".repeat(grid.getFirst().length).toCharArray());
      grid.add("1".repeat(grid.getFirst().length).toCharArray());

      print(grid);

      for (int i = 1; i < grid.size() - 1; i++) {
        for (int j = 1; j < grid.get(i).length - 1; j++) {
          if (!set.contains(i + "," + j)) {
            area = 0;
            corners = 0;
            char letter = grid.get(i)[j];
            set.add(i + "," + j);
            int perimeter = dfs(copy(grid), i, j, letter, set);
            System.out.println("letter: " + letter + " area: " + area + " sides: " + corners);
            ans += (area * corners);
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