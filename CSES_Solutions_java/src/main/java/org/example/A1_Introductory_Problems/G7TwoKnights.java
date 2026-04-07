//package org.example.A1_Introductory_Problems;

import java.io.*;
import java.util.StringTokenizer;

public class G7TwoKnights {
    public static void main(String[] args) {
        MyScanner sc = new MyScanner();
        out = new PrintWriter(new BufferedOutputStream(System.out));
        int n = sc.nextInt();

        for(int t=1;t<=n;t++){
            long sqare = t * t;
            long squareSum = (1l * sqare * (sqare-1))/2;
            long minus = 0;
            if(t>=3) minus = 8 * (1l * (t-2)*(t-1))/2;
            out.println(squareSum - minus);
        }
        out.close();
    }

    //-----------PrintWriter for faster output---------------------------------
    public static PrintWriter out;

    //-----------MyScanner class for faster input----------
    public static class MyScanner {
        BufferedReader br;
        StringTokenizer st;

        public MyScanner() {
            br = new BufferedReader(new InputStreamReader(System.in));
        }

        String next() {
            while (st == null || !st.hasMoreElements()) {
                try {
                    st = new StringTokenizer(br.readLine());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return st.nextToken();
        }

        int nextInt() {
            return Integer.parseInt(next());
        }

        long nextLong() {
            return Long.parseLong(next());
        }

        double nextDouble() {
            return Double.parseDouble(next());
        }

        String nextLine() {
            String str = "";
            try {
                str = br.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return str;
        }

    }
}




/*
 * 1 - > 0
 * 2 -> 6(4 * 3)(6 - 0)(3 + 2 + 1 - 0)
 * 3 -> 28(9 * 8)( 36 - 8 (8 * 1))(6 + 5 + 4 + 4 + 4 + 2 + 2 + 1 )
 * 4 -> 96(16 * 15)(120 - 24(8 * 3))(13 + 1
 * 5 -> 252(25 * 24)(300 - 48(8 * 6))
 * 6 -> 550(36 * 35)(630 - 80(8 * 10))
 * 7 ->    (49 * 48)(1176 - 120 (8 * 15))
 * */