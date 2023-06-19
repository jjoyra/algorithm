package algorithm.softeer;

import java.util.*;
import java.io.*;


public class Main_바이러스
{
    static long K, P;
    static int N;
    static int div = 1000000007;

    public static void main(String args[]) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        K = Long.parseLong(st.nextToken()) % div;
        P = Long.parseLong(st.nextToken()) % div;
        N = Integer.parseInt(st.nextToken());

        System.out.println( K * divide(P, N) % div);

    }

    static long divide(long a, long b) {
        if(b == 1) {
            return a % div;
        }

        long c = divide(a, b / 2) % div;
        return b % 2 == 0 ? c * c % div : c * c % div * a % div;
    }
}

