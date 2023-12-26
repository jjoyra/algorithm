package algorithm.BOJ.Java;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main_11727_2xn_타일링_2 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[][] dp = new int[n + 2][2];

        dp[1][0] = 1;
        dp[2][0] = 1;
        dp[2][1] = 2;

        for(int i = 3; i < n + 1; i++) {
            dp[i][0] = (dp[i - 1][0] + dp[i - 1][1]) % 10007;
            dp[i][1] = (dp[i - 2][0] + dp[i - 2][1]) * 2 % 10007;
        }

        System.out.println((dp[n][0] + dp[n][1]) % 10007);
    }
}
