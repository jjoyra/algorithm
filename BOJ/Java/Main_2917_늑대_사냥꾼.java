package algorithm.BOJ.Java;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main_2917_늑대_사냥꾼 {
    static class Box {
        int r, c, weight;

        Box(int r, int c, int weight) {
            this.r = r;
            this.c = c;
            this.weight = weight;
        }
    }
    static int N, M;
    static int[] start, end;
    static int[][] distance;
    static boolean[][] visited;
    static Queue<Box> queue;
    static int[] dr = {-1, 0, 1, 0};
    static int[] dc = {0, 1, 0, -1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        visited = new boolean[N][M];
        distance = new int[N][M];
        queue = new ArrayDeque<>();

        for(int i = 0; i < N; i++) {
            Arrays.fill(distance[i], 1000);
            String string = br.readLine();

            for(int j = 0; j < M; j++) {
                int tmp = string.charAt(j);

                if(tmp == '+') {
                    queue.offer(new Box(i, j, 0));
                    distance[i][j] = 0;
                } else if(tmp == 'V') {
                    start = new int[] {i, j};
                } else if(tmp == 'J') {
                    end = new int[] {i, j};
                }
            }

        }

        bfs();
        dijkstra();
    }

    // PQ보다 BFS가 더 빠르다
    static void bfs() {
        while(!queue.isEmpty()) {
            Box box = queue.poll();
            for(int i = 0; i < 4; i++) {
                int nr = box.r + dr[i];
                int nc = box.c + dc[i];

                if(nr < 0 || nc < 0 || nr >= N || nc >= M || distance[nr][nc] <= box.weight + 1)
                    continue;

                distance[nr][nc] = box.weight + 1;
                queue.offer(new Box(nr, nc, distance[nr][nc]));
            }
        }
    }

    static void dijkstra() {
        PriorityQueue<Box> pq = new PriorityQueue<>((a, b) -> b.weight - a.weight);
        pq.offer(new Box(start[0], start[1], distance[start[0]][start[1]]));

        while(!pq.isEmpty()) {
            Box box = pq.poll();
            if(visited[box.r][box.c]) continue;
            else if(box.r == end[0] && box.c == end[1]) {
                System.out.println(box.weight);
                return;
            }
            visited[box.r][box.c] = true;

            for(int i = 0; i < 4; i++) {
                int nr = box.r + dr[i];
                int nc = box.c + dc[i];

                if(nr < 0 || nc < 0 || nr >= N || nc >= M || visited[nr][nc]) continue;

                pq.offer(new Box(nr, nc, Math.min(distance[nr][nc], box.weight)));
            }
        }
    }
}