package com.facebook.hackercup;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class Chapter2 {
  int globalCount = Integer.MAX_VALUE;
  public void parseFirstLine(BufferedReader br) throws IOException {
    File file = new File("output.txt");
    if (file.exists()) file.delete();
    file.createNewFile();

    int birthdays = Integer.parseInt(br.readLine());
    for (int i = 1; i <= birthdays; i++) {
      BufferedWriter writer = new BufferedWriter(new FileWriter(file, true));
      Set<Character> searchSet = new HashSet<>();
      List<String> replacements = new ArrayList<>();
      String word = br.readLine();

      int length = Integer.parseInt(br.readLine());
      if(length == 0) {
        System.out.println("Case #"+ i + ": " + 0);
        continue;
      }
      for (int j = 0; j < length; j++) {
        String s1 = br.readLine();
        replacements.add(s1);
        searchSet.add(s1.charAt(1));
      }
      PriorityQueue<Character> pq = new PriorityQueue<>(searchSet);
      Map<Character, List<Character>> map = new HashMap<>();
      for(char ch : word.toCharArray()) {
        build(map, replacements, ch);
      }
      int finalRes = -1;
      while(!pq.isEmpty()) {
        char pqChar = pq.poll();
        int locCount = 0;
        for(char ch : word.toCharArray()) {
          int look = runIt(pqChar, ch, map);
          if (look == -1) {
            locCount = -1;
            break;
          } else locCount += look;
        }

        if(locCount != -1 && finalRes == -1) finalRes = Math.max(locCount, finalRes);
        else if(finalRes != -1) finalRes = Math.min(locCount, finalRes);
      }

      System.out.println("Case #"+ i + ": " + finalRes);
      writer.close();
    }
  }

  private Integer runIt(Character pq, Character curr, Map<Character, List<Character>> map) {
    if(curr == pq) return 0;
    boolean[] visited = new boolean[26];
    Queue<Character> queue = new ArrayDeque<>();
    queue.add(curr);
    Integer i = 0;
    while(!queue.isEmpty()) {
      char chr = queue.poll();
      if(map.get(chr) != null && !visited[chr - 'A']) {
        i++;
        visited[chr - 'A'] = true;
        for(Character c : map.get(chr)) {
          if(c == pq) { return i; }
          queue.add(c);
        }
      } else {
        continue;
      }
    }

    return -1;
  }

  private void build(Map<Character, List<Character>> map, List<String> replacements, char ch) {
    Set<Character> set = new HashSet<>();
    for(String s : replacements) {
        map.putIfAbsent(s.charAt(0), new ArrayList<>(s.charAt(1)));
        map.get(s.charAt(0)).add(s.charAt(1));

      if(ch == s.charAt(0)) {
        set.add(s.charAt(1));
      }
    }

    map.putIfAbsent(ch, new ArrayList<>(set));
    map.get(ch).addAll(set);

  }


  public void compute() throws FileNotFoundException {

    File file = new File("/home/alexander/Documents/fb/fb_hackercup21/chapter1/consistency_chapter_2_sample_input.txt");
    String filePath = file.getAbsolutePath();

    FileReader f = new FileReader(filePath);
    BufferedReader br = new BufferedReader(f);
    try {
      parseFirstLine(br);
    } catch (Exception e) {
      System.out.println("???: " + e.getMessage());
    }
  }
}
