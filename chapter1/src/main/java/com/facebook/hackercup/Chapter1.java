package com.facebook.hackercup;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

// Instantiate and call the compute() method to use this class
public class Chapter1 {
  Set<Character> vowels = new HashSet<>();

  private void fill() {
    char[] vow  = new char[]{'A','O','U','E','I'};
    for(char ch : vow) vowels.add(ch);
  }

  public void parseFirstLine(BufferedReader br) throws IOException {
    fill();

    File file = new File("output.txt");
    if(file.exists()) file.delete();
    file.createNewFile();

    int birthdays = Integer.parseInt(br.readLine());
    for(int i = 1; i <= birthdays; i++) {
      BufferedWriter writer = new BufferedWriter(new FileWriter(file, true));
      boolean[] mem = new boolean[26];
      int result = calculateString(br.readLine(), mem);
      writer.append("Case #" + i + ": " + result+ "\n");
      writer.close();
    }
  }

  private int calculateString(String s, boolean[] mem) {
    Set<Character> duplicates = new HashSet<>();
    int count = Integer.MAX_VALUE, v = 0, c = 0;
    for(int i = 0; i < s.length(); i++) {
      if(vowels.contains(s.charAt(i))) v +=1;
      else c += 1;
      duplicates.add(s.charAt(i));
      count = Math.min(count, counter(s, i, mem, count, vowels.contains(s.charAt(i))));
      mem[s.charAt(i) - 'A'] = true;
    }
    if(duplicates.size() == 1) return 0;
    else if(v == s.length() || c == s.length()) return s.length();
    else return count;
  }

  private int counter(String s, int ind, boolean[] mem, int count, boolean isVowel) {
    int localCount = 0;
    for(int i = 0; i < s.length(); i++) {
      if(localCount >= count) break;
      if(mem[s.charAt(ind) - 'A'] == true) {
        return Integer.MAX_VALUE;
      }
      if(i == ind) continue;
      boolean same = vowels.contains(s.charAt(i)) == isVowel;
      if(same && s.charAt(i) != s.charAt(ind)) {
        localCount += 2;
      } else if(!same) {
        localCount += 1;
      }
    }
    return localCount;
  }
  
  public void compute() throws FileNotFoundException
  {
    // 
      File file = new File("");
      FileReader f = new FileReader(file);

      BufferedReader br = new BufferedReader(f);
      try {
        parseFirstLine(br);
      } catch (Exception e) {
        System.out.println(e.getMessage());
      }
  }
}
