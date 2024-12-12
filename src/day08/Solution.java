package day08;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Solution {
  int start() {
    String dir = System.getProperty("user.dir") + "/src";
    File file = new File(dir + "/day08/input.txt");

    int ans = 0;
    try {
      Scanner sc = new Scanner(file);
      List<String> lines = new ArrayList<>();
      Map<Character, List<int[]>> map = new HashMap<>();

      while (sc.hasNextLine()) {
        String line = sc.nextLine();
        lines.add(line);
      }
      char[][] grid = new char[lines.size()][lines.getFirst().length()];
      for (int i = 0; i < lines.size(); i++) {
        String line = lines.get(i);
        for (int j = 0; j < line.length(); j++) {
          grid[i][j] = line.charAt(j);
          if (grid[i][j] != '.') {
            List<int[]> list = map.getOrDefault(grid[i][j], new ArrayList<>());
            list.add(new int[]{i, j});
            map.put(grid[i][j], list);
          }
        }
      }

      for (Character node : map.keySet()) {
        List<int[]> list = map.get(node);
        for (int i = 0; i < list.size(); i++) {
          for (int j = i + 1; j < list.size(); j++) {
            int[] a = list.get(i);
            int[] b = list.get(j);
            int x = Math.abs(a[0] - b[0]);
            int y = Math.abs(a[1] - b[1]);
            if (a[1] < b[1]) {
              int cr = a[0];
              int cc = a[1];

              while (true) {
                int nr = cr - x;
                int nc = cc - y;
                if (nr >= 0 && nc >= 0) {
                  if (grid[nr][nc] == '.') {
                    grid[nr][nc] = '#';
                  }
                } else {
                  break;
                }
                cr = nr;
                cc = nc;
              }

              cr = b[0];
              cc = b[1];

              while (true) {
                int nr = cr + x;
                int nc = cc + y;
                if (nr < grid.length && nc < grid[0].length) {
                  if (grid[nr][nc] == '.') {
                    grid[nr][nc] = '#';
                  }
                } else {
                  break;
                }
                cr = nr;
                cc = nc;
              }
            } else {
              int cr = a[0];
              int cc = a[1];

              while (true) {
                int nr = cr - x;
                int nc = cc + y;
                if (nr >= 0 && nc < grid[0].length) {
                  if (grid[nr][nc] == '.') {
                    grid[nr][nc] = '#';
                  }
                } else {
                  break;
                }
                cr = nr;
                cc = nc;
              }

              cr = b[0];
              cc = b[1];

              while (true) {
                int nr = cr + x;
                int nc = cc - y;
                if (nr < grid.length && nc >= 0) {
                  if (grid[nr][nc] == '.') {
                    grid[nr][nc] = '#';
                  }
                } else {
                  break;
                }
                cr = nr;
                cc = nc;
              }
            }
          }
        }
      }

      for (char[] chars : grid) {
        for (int j = 0; j < grid[0].length; j++) {
          System.out.print(chars[j]);
          if (chars[j] != '.') ans++;
        }
        System.out.println();
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