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
        if (grid.get(i)[j] == '[') {
          ans += 100L * i + j;
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

    if (move == '<' || move == '>') {
      boolean found = false;
      int row = loc.row;
      int col = loc.col + vals[1];

      while (col > 1 && col < grid.getFirst().length - 2) {
        char ch = grid.get(row)[col];
        if (ch == '#') {
          break;
        }

        if (ch == '.') {
          found = true;
          break;
        }

        col += vals[1];
      }

      if (!found) {
        return loc;
      }

      int newCol = col;
      row = loc.row;
      col = loc.col;

      while (newCol != col) {
        grid.get(row)[newCol] = grid.get(row)[newCol - vals[1]];
        newCol -= vals[1];
      }

      grid.get(row)[col] = '.';
      return new Location(row, col + vals[1]);
    } else {
      int row = loc.row;
      int col = loc.col;

      boolean[][] visited = new boolean[grid.size()][grid.getFirst().length];
      boolean found = find(grid, row + vals[0], col, vals[0], visited);

      if (!found) {
        return loc;
      }

      visited = new boolean[grid.size()][grid.getFirst().length];
      changeGrid(grid, row + vals[0], col, vals[0], visited, grid.get(row)[col]);

      grid.get(row)[col] = '.';
      return new Location(row + vals[0], col);
    }
  }

  boolean find(List<char[]> grid, int row, int col, int val, boolean[][] visited) {
    if (row < 0 || row > grid.size() || col < 0 || col > grid.getFirst().length - 1 || grid.get(row)[col] == '#') {
      return false;
    }

    visited[row][col] = true;

    if (grid.get(row)[col] == '.') {
      return true;
    }

    boolean f = find(grid, row + val, col, val, visited);
    if (!f) return false;

    if (grid.get(row)[col] == '[' && !visited[row][col + 1]) {
      return find(grid, row, col + 1, val, visited);
    } else if (grid.get(row)[col] == ']' && !visited[row][col - 1]) {
      return find(grid, row, col - 1, val, visited);
    }

    return true;
  }

  void changeGrid(List<char[]> grid, int row, int col, int val, boolean[][] visited, char prev) {
    if (row < 0 || row > grid.size() || col < 0 || col > grid.getFirst().length - 1 || grid.get(row)[col] == '#') {
      return;
    }

    visited[row][col] = true;

    if (grid.get(row)[col] == '.') {
      grid.get(row)[col] = prev;
      return;
    }

    changeGrid(grid, row + val, col, val, visited, grid.get(row)[col]);

    if (grid.get(row)[col] == '[' && !visited[row][col + 1]) {
      char ch = '.';
      changeGrid(grid, row, col + 1, val, visited, ch);
    } else if (grid.get(row)[col] == ']' && !visited[row][col - 1]) {
      char ch = '.';
      changeGrid(grid, row, col - 1, val, visited, ch);
    }

    grid.get(row)[col] = prev;
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
        String l = sc.nextLine();
        if (l.isEmpty()) {
          break;
        }

        StringBuilder line = new StringBuilder();
        for (int i = 0; i < l.length(); i++) {
          char ch = l.charAt(i);
          switch (ch) {
            case '#':
              line.append("##");
              break;

            case 'O':
              line.append("[]");
              break;

            case '.':
              line.append("..");
              break;

            case '@':
              line.append("@.");
              break;

            default:
              line.append(ch);
              break;
          }
        }

        if (line.toString().contains("@")) {
          currRow = grid.size();
          currCol = line.indexOf("@");
        }
        grid.add(line.toString().toCharArray());
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