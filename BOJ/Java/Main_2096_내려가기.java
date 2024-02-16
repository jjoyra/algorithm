package algorithm.BOJ.Java;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_2096_내려가기 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[][] map = new int[N][3];
        int[][] minDp = new int[N][3];
        int[][] maxDp = new int[N][3];

        for(int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j = 0; j < 3; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for(int i = 0; i < 3; i++) {
            minDp[0][i] = map[0][i];
            maxDp[0][i] = map[0][i];
        }

        for(int i = 1; i < N; i++) {
            int minFirst = Math.min(minDp[i - 1][0], minDp[i - 1][1]);
            minDp[i][0] = minFirst + map[i][0];
            minDp[i][1] = Math.min(minFirst, minDp[i - 1][2]) + map[i][1];
            minDp[i][2] = Math.min(minDp[i - 1][1], minDp[i - 1][2]) + map[i][2];

            int maxFirst = Math.max(maxDp[i - 1][0], maxDp[i - 1][1]);
            maxDp[i][0] = maxFirst + map[i][0];
            maxDp[i][1] = Math.max(maxFirst, maxDp[i - 1][2]) + map[i][1];
            maxDp[i][2] = Math.max(maxDp[i - 1][1], maxDp[i - 1][2]) + map[i][2];
        }

        int max = Math.max(maxDp[N - 1][0], maxDp[N - 1][1]);
        max = Math.max(max, maxDp[N - 1][2]);

        int min = Math.min(minDp[N - 1][0], minDp[N - 1][1]);
        min = Math.min(min, minDp[N - 1][2]);

        System.out.println(max + " " + min);
    }
}
