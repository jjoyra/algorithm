package algorithm.BOJ.Java;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_9465_스티커 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        for(int t = 0; t < T; t++) {
            int N =Integer.parseInt(br.readLine());
            int[][] map = new int[2][N];
            for(int i = 0; i < 2; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                for(int j = 0; j < N; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            int[][] dp = new int[2][N];
            dp[0][0] = map[0][0];
            dp[1][0] = map[1][0];
            if(N > 1) {
                dp[0][1] = map[1][0] + map[0][1];
                dp[1][1] = map[0][0] + map[1][1];
            }

            for(int i = 2; i < N; i++) {
                int pre = Math.max(dp[0][i - 2], dp[1][i - 2]);
                dp[0][i] = Math.max(pre, dp[1][i - 1]) + map[0][i];
                dp[1][i] = Math.max(pre, dp[0][i - 1]) + map[1][i];
            }

            System.out.println(Math.max(dp[0][N - 1], dp[1][N - 1]));
        }
    }
}
