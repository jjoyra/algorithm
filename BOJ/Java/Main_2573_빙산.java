package algorithm.BOJ.Java;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_2573_빙산 {
    static int N, M;
    static int[][] map;
    static boolean[][] visited;
    static int[] dr = {-1, 0, 1, 0};
    static int[] dc = {0, 1, 0, -1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][M];

        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int answer = -1;

        while(true) {
            int cnt = 0;
            visited = new boolean[N][M];

            for(int i = 0; i < N; i++) {
                for(int j = 0; j < M; j++) {
                    if(map[i][j] != 0 && !visited[i][j]) {
                        bfs(i, j);
                        cnt++;
                    }
                }
            }
            answer++;

            if(cnt != 1) {
                System.out.println(cnt == 0 ? 0 : answer);
                break;
            }
        }
    }

    static void bfs(int r, int c) {
        Queue<int[]> queue = new ArrayDeque<>();
        queue.offer(new int[] {r, c});
        visited[r][c] = true;

        while(!queue.isEmpty()) {
            int[] tmp = queue.poll();
            int cnt = 0;

            for(int i = 0; i < 4; i++) {
                int nr = tmp[0] + dr[i];
                int nc = tmp[1] + dc[i];

                if(nr < 0 || nc < 0 || nr >= N || nc >= M || visited[nr][nc]) continue;
                if(map[nr][nc] == 0) {
                    cnt++;
                } else {
                    queue.offer(new int[] {nr, nc});
                    visited[nr][nc] = true;
                }
            }

            map[tmp[0]][tmp[1]] = map[tmp[0]][tmp[1]] >= cnt ? map[tmp[0]][tmp[1]] - cnt : 0;
        }
    }
}