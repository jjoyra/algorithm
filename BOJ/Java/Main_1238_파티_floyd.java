package algorithm.BOJ.Java;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_1238_파티_floyd {
    static int N, M, X, answer;
    static int[][] distance;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        X = Integer.parseInt(st.nextToken());

        distance = new int[N + 1][N + 1];

        for(int i = 1; i <= N; i++) {
            Arrays.fill(distance[i], 100001);
            distance[i][i] = 0; // 자신의 정점과의 거리는 0
        }

        for(int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());

            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());

            distance[start][end] = w;
        }

        floyd();

        for(int i = 1; i <= N; i++) {
            int dis = distance[i][X] + distance[X][i];
            answer = Math.max(answer, dis);
        }

        System.out.println(answer);
    }

    static void floyd() {
        for(int i = 1; i <= N; i++) { // 경유지
            for(int j = 1; j <= N; j++) { // 출발지
                if(distance[j][i] == 100001) continue; // 출발지 - 경유지가 INF인 경우는 continue
                for(int k = 1; k <= N; k++) { // 도착지
                    if(distance[j][k] > distance[j][i] + distance[i][k])
                        distance[j][k] = distance[j][i] + distance[i][k];
                }
            }
        }
    }

}
