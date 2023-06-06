package algorithm.BOJ.Java;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main_4485_녹색_옷_입은_애가_젤다지 {
    static class Rupee {
        int r, c, dis;

        public Rupee(int r, int c, int dis) {
            this.r = r;
            this.c = c;
            this.dis = dis;
        }
    }
    static int N;
    static int[][] map, distance;
    static int[] dr = {-1, 0, 1, 0};
    static int[] dc = {0, 1, 0, -1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        StringBuilder sb = new StringBuilder();
        int tc = 1;

        N = Integer.parseInt(br.readLine());

        while(N != 0) {
            map = new int[N][N];
            distance = new int[N][N];

            for(int i = 0; i < N; i++) {
                Arrays.fill(distance[i], Integer.MAX_VALUE);
            }

            for(int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                for(int j = 0; j < N; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            dijkstra();

            sb.append("Problem " + tc + ": " + distance[N - 1][N - 1] + "\n");
            N = Integer.parseInt(br.readLine());
            tc++;
        }

        System.out.println(sb);

    }

    static void dijkstra() {
        PriorityQueue<Rupee> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a.dis));
        distance[0][0] = map[0][0];
        pq.offer(new Rupee(0, 0, distance[0][0]));

        while(!pq.isEmpty()) {
        // 1) 방문하지 않은 정점에서 가장 가까운 정점 선택
            Rupee rupee = pq.poll();
            if(rupee.dis > distance[rupee.r][rupee.c]) continue;
        // 2) 선택한 정점을 방문했을 때 최단 거리 갱신
            for(int i = 0; i < 4; i++) {
                int nr = rupee.r + dr[i];
                int nc = rupee.c + dc[i];

                if(nr < 0 || nr >= N || nc < 0 || nc >= N) continue;
                if(distance[rupee.r][rupee.c] + map[nr][nc] < distance[nr][nc]) {
                    distance[nr][nc] = distance[rupee.r][rupee.c] + map[nr][nc];
                    pq.offer(new Rupee(nr, nc, distance[nr][nc]));
                }
            }

        }
    }

}
