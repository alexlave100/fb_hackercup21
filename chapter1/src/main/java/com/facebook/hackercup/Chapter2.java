package com.facebook.hackercup;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Chapter2 {
  public void parseFirstLine(BufferedReader br) throws IOException {
    File file = new File("output.txt");
    if(file.exists()) file.delete();
    file.createNewFile();

    int birthdays = Integer.parseInt(br.readLine());
    for(int i = 1; i <= birthdays; i++) {
      BufferedWriter writer = new BufferedWriter(new FileWriter(file, true));
      Set<Character> searchSet = new HashSet<>();
      Map<Character, List<Character>> map = new HashMap<>();
      String s = br.readLine();
      int length = Integer.parseInt(br.readLine());
      for(int j = 0; j < length; j++) {
        String s1 = br.readLine();
        searchSet.add(s1.charAt(1));
        map.putIfAbsent(s1.charAt(0), new ArrayList<Character>());
        map.get(s1.charAt(0)).add(s1.charAt(1));
      }

      //int result = calculateString(br.readLine(), mem);
      //writer.append("Case #" + i + ": " + result+ "\n");
      //System.out.println("Case #"+ i + ": " + br.readLine());
      writer.close();
    }
  }
  public void compute() throws FileNotFoundException {
    // /Users/alexanderlavesson/Documents/Programming/Facebook/HackerCup_21/chapter1/consistency_chapter_2_sample_input.txt
    File file = new File("../../../../../../consistency_chapter_2_sample_input.txt");
    String filePath = file.getAbsolutePath();

    FileReader f = new FileReader(filePath);
    BufferedReader br = new BufferedReader(f);
    try {
      parseFirstLine(br);
    } catch (Exception e) {
      System.out.println(e.getMessage());
    }
  }
}
