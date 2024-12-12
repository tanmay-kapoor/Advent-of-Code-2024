package day09;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Solution {
  class Node {
    int val;

    public Node(int val) {
      this.val = val;
    }
  }

  long start() {
    String dir = System.getProperty("user.dir") + "/src";
    File file = new File(dir + "/day09/input.txt");

    long ans = 0L;
    try {
      Scanner sc = new Scanner(file);
      String input = sc.nextLine();

      List<Node> list = new ArrayList<>();
      int id = 0;

      for (int i = 0; i < input.length(); i++) {
        int num = input.charAt(i) - '0';

        if (i % 2 == 0) {
          while (num-- > 0) {
            list.add(new Node(id));
          }
          id++;
        } else {
          while (num-- > 0) {
            list.add(new Node(-1));
          }
        }
      }

      for (int i = 0, j = list.size() - 1; i <= j; ) {
        Node left = list.get(i);
        Node right = list.get(j);

        if (left.val != -1 && right.val != -1) {
          i++;
        } else if (left.val == -1 && right.val != -1) {
          left.val = right.val;
          right.val = -1;
          i++;
          j--;
        } else if (left.val == -1) {
          j--;
        } else {
          i++;
          j--;
        }
      }

      for (int i = 0; i < list.size(); i++) {
        if (list.get(i).val != -1) {
          ans += ((long) i * list.get(i).val);
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