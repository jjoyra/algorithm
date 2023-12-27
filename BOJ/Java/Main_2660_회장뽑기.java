package algorithm.BOJ.Java;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Main_2660_회장뽑기 {
    static int N;
    static int[][] map;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        map = new int[N + 1][N + 1];

        for(int i = 0; i < N + 1; i++) {
            Arrays.fill(map[i], 51);
            map[i][i] = 0;
        }

        while(true) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            if(a == -1) break;

            map[a][b] = 1;
            map[b][a] = 1;
        }

        for(int k = 1; k < N + 1; k++) {
            for(int i = 1; i < N + 1; i++) {
                for(int j = 1; j < N + 1; j++) {
                    if(map[i][j] > map[i][k] + map[k][j]) {
                        map[i][j] = map[i][k] + map[k][j];
                    }
                }
            }
        }

        int[] score = new int[N + 1];

        int minScore = 51;
        int cnt = 0;
        StringBuilder sb = new StringBuilder();

        for(int i = 1; i < N + 1; i++) {
            for(int j = 1; j < N + 1; j++) {
                score[i] = Math.max(score[i], map[i][j]);
            }

            if(minScore > score[i]) {
                minScore = score[i];
                cnt = 1;
                sb = new StringBuilder();
                sb.append(i + " ");
            } else if(minScore == score[i]) {
                sb.append(i + " ");
                cnt++;
            }
        }

        System.out.println(minScore + " " + cnt);
        System.out.println(sb);

    }
}
