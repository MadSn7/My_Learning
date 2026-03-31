package org.example.A1_Introductory_Problems;

import java.util.Scanner;

public class D4IncreasingArray {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        long ans = 0;

//        int prev = sc.nextInt();
//        for(int i = 1; i < n; i++){
//            int curr = sc.nextInt();
//            ans += Math.max(0, prev - curr);
//            prev = curr;
//        }
//        System.out.println(prev);
        int prevMax = sc.nextInt();
        for (int i = 1; i < n; i++) {
            int curr =  sc.nextInt();
            if (curr >= prevMax) {
                prevMax = curr;
            }else{
                ans += prevMax - curr;
            }
//            ans += Math.max(0, prevMax - curr);
//            prevMax = Math.max(prevMax, curr);
        }
        System.out.println(ans);
        sc.close();
    }
}
