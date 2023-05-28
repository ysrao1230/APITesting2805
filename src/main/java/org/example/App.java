package org.example;

import java.util.HashMap;
import java.util.Map;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {

        String val = "aabbbcdddddd";
        System.out.println("Output is a2b3c1d6");
        HashMap<Character, Integer> map = new HashMap<Character, Integer>();
        char [] ch= val.toCharArray();
        for(char c:ch){
            if(map.containsKey(c)){
                map.put(c,map.get(c)+1);
            }else {
                map.put(c,1);
            }
        }
        for(Map.Entry<Character,Integer> chs:map.entrySet()){
            if (chs.getValue()>=1){
                System.out.print(chs.getKey()+String.valueOf(chs.getValue()));
            }
        }

    }
}