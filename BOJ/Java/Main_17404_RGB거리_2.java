
package algorithm.BOJ.Java;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_17404_RGB거리_2 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        int[][] map = new int[N][3];
        for(int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j = 0; j < 3; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int[][][] dp = new int[3][N][3];

        for(int i = 0; i < 3; i++) {
            dp[i][1][i] = 1000001;
            dp[i][1][(i + 1) % 3] = map[0][i] + map[1][(i + 1) % 3];
            dp[i][1][(i + 2) % 3] = map[0][i] + map[1][(i + 2) % 3];

            for(int j = 2; j < N; j++) {
                dp[i][j][0] = Math.min(dp[i][j - 1][1], dp[i][j - 1][2]) + map[j][0];
                dp[i][j][1] = Math.min(dp[i][j - 1][0], dp[i][j - 1][2]) + map[j][1];
                dp[i][j][2] = Math.min(dp[i][j - 1][0], dp[i][j - 1][1]) + map[j][2];
            }
        }

        int answer = Integer.MAX_VALUE;

        for(int i = 0; i < 3; i++) {
            answer = Math.min(answer, dp[i][N - 1][(i + 1) % 3]);
            answer = Math.min(answer, dp[i][N - 1][(i + 2) % 3]);
        }

        System.out.println(answer);

    }
}
