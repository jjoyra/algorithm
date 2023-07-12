package algorithm.BOJ.Java;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_14938_서강그라운드_floyd {
    static int N, M, R, tem[], distance[][];
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());

        tem = new int[N];
        distance = new int[N][N];

        for(int i = 0; i < N; i++) {
            Arrays.fill(distance[i], 15 * N);
            distance[i][i] = 0;
        }

        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++) {
            tem[i] = Integer.parseInt(st.nextToken());
        }

        for(int i = 0; i < R; i++) {
            st = new StringTokenizer(br.readLine());

            int a = Integer.parseInt(st.nextToken()) - 1;
            int b = Integer.parseInt(st.nextToken()) - 1;
            int w = Integer.parseInt(st.nextToken());

            distance[a][b] = w;
            distance[b][a] = w;
        }

        floyd();

        int answer = 0;

        for(int i = 0; i < N; i++) {
            int sum = 0;
            for(int j = 0; j < N; j++) {
                if(distance[i][j] <= M) sum += tem[j];
            }

            answer = Math.max(answer, sum);
        }

        System.out.println(answer);
    }

    static void floyd() {
        for(int k = 0; k < N; k++) {
            for(int i = 0; i < N; i++) {
                for(int j = 0; j < N; j++) {
                    if(distance[i][j] > distance[i][k] + distance[k][j]) {
                        distance[i][j] = distance[i][k] + distance[k][j];
                    }
                }
            }
        }
    }


}
