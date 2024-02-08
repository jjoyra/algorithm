package algorithm.BOJ.Java;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main_2133_타일_채우기 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[][] dp = new int[N + 2][3];

        dp[2][0] = 1;
        dp[2][1] = 1;
        dp[2][2] = 1;

        for(int i = 4; i < N + 1; i++) {
            if(i % 2 != 0) continue;
            int pre = dp[i - 2][0] + dp[i - 2][1] + dp[i - 2][2];
            dp[i][0] = pre + dp[i - 2][0];
            dp[i][1] = pre + dp[i - 2][1];
            dp[i][2] = pre;

        }

        System.out.println(dp[N][0] + dp[N][1] + dp[N][2]);
    }
}
