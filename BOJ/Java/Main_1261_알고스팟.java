package algorithm.BOJ.Java;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main_1261_알고스팟 {
    static int N, M, map[][], dis[][];
    static int[] dr = {-1, 0, 1, 0};
    static int[] dc = {0, 1, 0, -1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        dis = new int[N][M];

        for(int i = 0; i < N; i++) {
            Arrays.fill(dis[i], Integer.MAX_VALUE);
        }

        for(int i = 0; i < N; i++) {
            String str = br.readLine();
            for(int j = 0; j < M; j++) {
                map[i][j] = str.charAt(j) - '0';
            }
        }

        dijkstra();

        System.out.println(dis[N - 1][M - 1]);
    }

    static void dijkstra() {
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> {return a[2] -b[2];});
        dis[0][0] = 0;
        pq.offer(new int[] {0, 0, 0});

        while(!pq.isEmpty()) {
            int[] tmp = pq.poll();

            if(tmp[2] > dis[tmp[0]][tmp[1]]) continue;

            for(int i = 0; i < 4; i++) {
                int nr = tmp[0] + dr[i];
                int nc = tmp[1] + dc[i];

                if(nr < 0 || nr >= N || nc < 0 || nc >= M) continue;
                if(map[nr][nc] == 0 && dis[nr][nc] > tmp[2]) {
                    dis[nr][nc] = tmp[2];
                    pq.offer(new int[] {nr, nc, tmp[2]});
                } else if(dis[nr][nc] > tmp[2] + 1) {
                    dis[nr][nc] = tmp[2] + 1;
                    pq.offer(new int[] {nr, nc, dis[nr][nc]});
                }
            }
        }
    }
}
