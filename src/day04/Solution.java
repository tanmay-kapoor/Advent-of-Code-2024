package day04;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;

public class Solution {

  int start() {
    String dir = System.getProperty("user.dir") + "/src";
    File file = new File(dir + "/day04/input.txt");

    int ans = 0;
    List<char[]> grid = new ArrayList<>();

    try {
      Scanner sc = new Scanner(file);
      Queue<int[]> q = new LinkedList<>();

      int row = 0;
      while (sc.hasNextLine()) {
        String line = sc.nextLine();
        char[] letters = line.toCharArray();

        grid.add(letters);
        row++;
      }

      for (int i = 1; i < grid.size() - 1; i++) {
        for (int j = 1; j < grid.getFirst().length - 1; j++) {
          if (grid.get(i)[j] == 'A') {
            q.add(new int[]{i, j});
          }
        }
      }

      while (!q.isEmpty()) {
        int[] point = q.poll();
        int i = point[0], j = point[1];

        char topLeft = grid.get(i - 1)[j - 1];
        char topRight = grid.get(i - 1)[j + 1];
        char bottomLeft = grid.get(i + 1)[j - 1];
        char bottomRight = grid.get(i + 1)[j + 1];

        if (topLeft == 'S') {
          if (bottomRight == 'M') {
            if ((topRight == 'S' && bottomLeft == 'M') || (topRight == 'M' && bottomLeft == 'S')) {
              ans++;
            }
          }
        } else if (topLeft == 'M') {
          if (bottomRight == 'S') {
            if ((topRight == 'S' && bottomLeft == 'M') || (topRight == 'M' && bottomLeft == 'S')) {
              ans++;
            }
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