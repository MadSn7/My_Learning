package org.example.A1_Introductory_Problems;

import java.util.Scanner;

public class C3Repetitions {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String s = sc.nextLine();
        int ans = 1;
        int prev = 0;
        for(int i=1;i<s.length();i++){
            if(s.charAt(i)==s.charAt(prev)){
                ans = Math.max(ans,i-prev +1);
            }else{
                prev = i;
            }
        }
        System.out.println(ans);
    }
}
