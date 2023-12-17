package algorithm.BOJ.Java;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Main_1389_케빈_베이컨의_6단계_법칙 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken()) + 1;
        int M = Integer.parseInt(st.nextToken());

        int[][] distance = new int[N][N];

        for(int i = 0; i < N; i++) {
            Arrays.fill(distance[i], 101);
        }

        for(int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            distance[a][b] = 1;
            distance[b][a] = 1;
        }

        for(int i = 0; i < N; i++) {
            for(int j = 0; j < N; j++) {
                for(int k = 0; k < N; k++) {
                    distance[j][k] = Math.min(distance[j][k],
                            distance[j][i] + distance[i][k]);
                }
            }
        }

        int sum = Integer.MAX_VALUE;
        int answer = -1;

        for(int i = 1; i < N; i++) {
            int tmp = 0;

            for(int j = 1; j < N; j++) {
                if(i == j) continue;
                tmp += distance[i][j];
            }

            if(sum > tmp) {
                sum = tmp;
                answer = i;
            }
        }

        System.out.println(answer);
    }
}
