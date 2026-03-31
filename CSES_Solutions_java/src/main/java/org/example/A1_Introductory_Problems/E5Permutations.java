package org.example.A1_Introductory_Problems;

import java.util.Scanner;

public class E5Permutations {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();

        if(n<=3){
            if( n== 1) System.out.println("1");
            else
            System.out.println("NO SOLUTION");
        }else{
            if(n == 4) {
                System.out.println("2 4 1 3 ");
            }else{
                StringBuilder sb = new StringBuilder();
                for(int i=1;i<=n;i+=2){
                    sb.append(i+" ");
                }
                for(int i=2;i<=n;i+=2){
                    sb.append(i+" ");
                }
                System.out.println(sb);
            }
        }
    }
}
