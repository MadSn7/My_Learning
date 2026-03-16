//package org.example.A1_Introductory_Problems;

import java.util.Scanner;

public class A1WeirdAlgorithm {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        long n = sc.nextLong();

        while(n>1){
            System.out.print(n+" ");
            if(n%2==0){
                n=n/2;
            }else {
                n=n*3 + 1;
            }
        }
        System.out.println("1");
    }
}
