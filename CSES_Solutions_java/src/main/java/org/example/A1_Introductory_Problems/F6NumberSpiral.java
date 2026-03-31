package org.example.A1_Introductory_Problems;

import java.util.Scanner;

public class F6NumberSpiral {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
        while (t-- > 0) {
            long x  = sc.nextInt();
            long y = sc.nextInt();

            long max = Math.max(x, y);
            long min = Math.min(x, y);

            if(max == y){
                if(max % 2 == 0){
                    System.out.println((max -1)*(max -1) + min);
                }else{
                    System.out.println(max*max - min -1);
                }
            }else{
                if(max % 2 == 0){
                    System.out.println(max*max - min -1);
                }else{
                    System.out.println((max -1)*(max -1) + min);
                }
            }
        }
    }
}
