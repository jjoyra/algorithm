package algorithm.BOJ.Java;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_7579_앱 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[] memory = new int[N];
        int[] cost = new int[N];

        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++) {
            memory[i] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());
        int costSum = 0;
        for(int i = 0; i < N; i++) {
            cost[i] = Integer.parseInt(st.nextToken());
            costSum += cost[i];
        }

        int[][] dp = new int[N + 1][costSum + 1];
        for(int i = 1; i < N + 1; i++) {
            for(int j = 0; j < costSum + 1; j++) {
                if(j < cost[i - 1]) {
                    dp[i][j] = dp[i - 1][j];
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j -cost[i - 1]] + memory[i - 1]);
                }
            }
        }

        for(int i = 0; i < costSum + 1; i++) {
            if(dp[N][i] >= M) {
                System.out.println(i);
                break;
            }
        }

    }
}
