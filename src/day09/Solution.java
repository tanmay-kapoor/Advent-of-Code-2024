package day09;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Solution {
  class Node {
    int val;
    int freq;

    public Node(int val, int freq) {
      this.val = val;
      this.freq = freq;
    }
  }

//  void print(List<Node> list) {
//    for (int i = 0; i < list.size(); i++) {
//      if (list.get(i).val == -1) {
//        System.out.print(".".repeat(list.get(i).freq));
//      } else {
//        System.out.print(Integer.toString(list.get(i).val).repeat(list.get(i).freq));
//      }
//    }
//    System.out.println();
//  }

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
          list.add(new Node(id++, num));
        } else {
          list.add(new Node(-1, num));
        }
      }

//      print(list);
      for (int j = list.size() - 1; j >= 0; j--) {
        Node right = list.get(j);
        if (right.val == -1) continue;

        for (int i = 0; i < j; ) {
          Node left = list.get(i);

          if (left.val != -1) {
            i++;
          } else if (left.freq >= right.freq) {
            int diff = left.freq - right.freq;

            left.val = right.val;
            left.freq = right.freq;

            right.val = -1;

            if (diff > 0) {
              Node node = new Node(-1, diff);
              list.add(i + 1, node);
              j++;
            }
            break;
          } else {
            i++;
          }
        }
//        print(list);
      }

//      print(list);

      int idx = 0;
      for (int i = 0; i < list.size(); i++) {
        if (list.get(i).val != -1) {
          int f = list.get(i).freq;
          int x = 0;
          while (x++ < f) {
            ans += ((long) idx * list.get(i).val);
            idx++;
          }
        } else {
          idx+= list.get(i).freq;
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