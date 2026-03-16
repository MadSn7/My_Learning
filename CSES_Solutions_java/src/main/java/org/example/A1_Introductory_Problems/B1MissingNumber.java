package org.example.A1_Introductory_Problems;

import java.util.Scanner;

public class B1MissingNumber {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        long ans = 0;
        for(int i=1;i<n;i++){
            int curr = sc.nextInt();
            ans ^= curr;
            ans ^= i;
        }
        ans = ans ^ n;
        System.out.println(ans);
    }
}
// can also do by total sum - all number we get sum