package algorithm.BOJ.Java;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_1600_말이_되고픈_원숭이 {
    static int[][] map;
    static boolean[][][] visited;
    static int K, W, H, answer;
    static int[] dr = {-1, 0, 1, 0};
    static int[] dc = {0, 1, 0, -1};
    static int[] hr = {-2, -1, 1, 2, 2, 1, -1, -2};
    static int[] hc = {1, 2, 2, 1, -1, -2, -2, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        K = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());
        W = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());
        map = new int[H][W];
        visited = new boolean[H][W][K + 1];
        answer = -1;

        for(int i = 0; i < H; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < W; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        bfs();

        System.out.println(answer);
    }

    static void bfs() {
        Queue<int[]> queue = new ArrayDeque<>();
        queue.offer(new int[] {0, 0, 0});
        visited[0][0][0] = true;
        int level = 0;

        while(!queue.isEmpty()) {

            int size = queue.size();

            while(size-- > 0) {
                int[] tmp = queue.poll();

                if(tmp[0] == H - 1 && tmp[1] == W - 1) {
                    queue.clear();
                    answer = level;
                    break;
                }

                if(tmp[2] < K) {
                    for(int i = 0; i < 8; i++) {
                        int nr = tmp[0] + hr[i];
                        int nc = tmp[1] + hc[i];

                        if(nr < 0 || nc < 0 || nr >= H || nc >= W
                                || map[nr][nc] == 1 || visited[nr][nc][tmp[2] + 1]) continue;

                        queue.offer(new int[] {nr, nc, tmp[2] + 1});
                        visited[nr][nc][tmp[2] + 1] = true;
                    }
                }

                for(int i = 0; i < 4; i++) {
                    int nr = tmp[0] + dr[i];
                    int nc = tmp[1] + dc[i];

                    if(nr < 0 || nc < 0 || nr >= H || nc >= W
                            || map[nr][nc] == 1 || visited[nr][nc][tmp[2]]) continue;

                    queue.offer(new int[] {nr, nc, tmp[2]});
                    visited[nr][nc][tmp[2]] = true;
                }

            }

            level++;

        }
    }
}
