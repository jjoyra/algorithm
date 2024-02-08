package algorithm.BOJ.Java;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_16933_벽_부수고_이동하기_3 {
    static class Box {
        int r, c, k;

        Box(int r, int c, int k) {
            this.r = r;
            this.c = c;
            this.k = k;
        }
    }
    static int N, M, K, answer;
    static int[][] map, visited;
    static int[] dr = {-1, 0, 1, 0};
    static int[] dc = {0, 1, 0, -1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        visited = new int[N][M];

        for(int i = 0; i < N; i++) {
            String string = br.readLine();
            for(int j = 0; j < M; j++) {
                map[i][j] = string.charAt(j) - '0';
            }
            Arrays.fill(visited[i], K + 1);
        }

        answer = -1;

        bfs();

        System.out.println(answer);

    }

    static void bfs() {
        Queue<Box> queue = new ArrayDeque<>();
        queue.offer(new Box(0, 0, 0));
        visited[0][0] = 0;
        int level = 1;

        while(!queue.isEmpty()) {
            int size = queue.size();

            for(int j = 0; j < size; j++) {
                Box box = queue.poll();

                if(box.r == N - 1 && box.c == M - 1) {
                    answer = level;
                    return;
                }

                for(int i = 0; i < 4; i++) {
                    int nr = box.r + dr[i];
                    int nc = box.c + dc[i];

                    if(nr < 0 || nc < 0 || nr >= N || nc >= M || visited[nr][nc] <= box.k) continue;
                    else if(map[nr][nc] == 1 && box.k < K) {
                        if(level % 2 == 1) {
                            visited[nr][nc] = box.k + 1;
                            queue.offer(new Box(nr, nc, box.k + 1));
                        } else {
                            queue.offer(box);
                        }
                    } else if (map[nr][nc] == 0) {
                        visited[nr][nc] = box.k;
                        queue.offer(new Box(nr, nc, box.k));
                    }

                }
            }

            level++;
        }
    }
}
