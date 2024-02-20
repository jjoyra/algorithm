package algorithm.BOJ.Java;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main_1486_등산 {
    static class Box implements Comparable<Box> {
        int r, c, weight;

        Box(int r, int c, int weight) {
            this.r = r;
            this.c = c;
            this.weight = weight;
        }

        @Override
        public int compareTo(Box box) {
            return this.weight - box.weight;
        }
    }
    static int N, M, T, D;
    static int[][] map, go, back;
    static int[] dr = {-1, 0, 1, 0};
    static int[] dc = {0, 1, 0, -1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        T = Integer.parseInt(st.nextToken());
        D = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        go = new int[N][M];
        back = new int[N][M];

        for(int i = 0; i < N; i++) {
            Arrays.fill(go[i], D);
            Arrays.fill(back[i], D);
        }

        for(int i = 0; i < N; i++) {
            String string = br.readLine();
            for(int j = 0; j < M; j++) {
                int tmp = string.charAt(j);
                if(tmp < 'a') {
                    map[i][j] = tmp - 'A';
                } else {
                    map[i][j] = tmp - 'a' + 26;
                }
            }
        }

        dijkstra(go, true);
        dijkstra(back, false);

        int answer = 0;

        for(int i = 0; i < N; i++) {
            for(int j = 0; j < M; j++) {
                if(go[i][j] + back[i][j] <= D) {
                    answer = Math.max(answer, map[i][j]);
                }
            }
        }

        System.out.println(answer);
    }

    static void dijkstra(int[][] dp, boolean isGoUp) {
        PriorityQueue<Box> pq = new PriorityQueue<>();
        pq.offer(new Box(0, 0, 0));
        dp[0][0] = 0;

        while(!pq.isEmpty()) {
            Box box = pq.poll();
            if(box.weight > dp[box.r][box.c]) continue;

            for(int i = 0; i < 4; i++) {
                int nr = box.r + dr[i];
                int nc = box.c + dc[i];

                if(nr < 0 || nc < 0 || nr >= N || nc >= M) continue;
                int height = Math.abs(map[box.r][box.c] - map[nr][nc]);

                if(height > T) continue;
                else if(isNextBoxLow(isGoUp, map[box.r][box.c], map[nr][nc])) {
                    if(dp[nr][nc] > box.weight + 1) {
                        dp[nr][nc] = box.weight + 1;
                        pq.offer(new Box(nr, nc, dp[nr][nc]));
                    }
                } else if(dp[nr][nc] > box.weight + height * height) {
                    dp[nr][nc] = box.weight + height * height;
                    pq.offer(new Box(nr, nc, dp[nr][nc]));
                }
            }
        }
    }

    static boolean isNextBoxLow(boolean isGoUp, int cur, int next) {
        if(isGoUp && cur >= next) {
            return true;
        } else if(!isGoUp && cur <= next) {
            return true;
        }
        return false;
    }
}
