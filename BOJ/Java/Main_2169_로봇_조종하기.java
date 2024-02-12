package algorithm.BOJ.Java;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_2169_로봇_조종하기 {
    static int N, M;
    static int[][] map;
    static int[][][] dp;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        dp = new int[N][M][2];

        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        dp[0][0][0] = map[0][0];
        dp[0][0][1] = map[0][0];

        for(int i = 1; i < M; i++) {
            dp[0][i][0] = dp[0][i - 1][0] + map[0][i];
            dp[0][i][1] = dp[0][i][0];
        }

        for(int i = 1; i < N; i++) {
            for(int j = 0; j < M; j++) {
                int down = Math.max(dp[i - 1][j][0], dp[i - 1][j][1]) + map[i][j];
                dp[i][j][0] = down;
                dp[i][j][1] = down;
            }

            for(int j = 1; j < M; j++) {
                dp[i][j][0] = Math.max(dp[i][j][0], dp[i][j - 1][0] + map[i][j]);
            }

            for(int j = M - 2; j >= 0; j--) {
                dp[i][j][1] = Math.max(dp[i][j][1], dp[i][j + 1][1] + map[i][j]);
            }
        }

        System.out.println(dp[N - 1][M - 1][0]);


    }
}
