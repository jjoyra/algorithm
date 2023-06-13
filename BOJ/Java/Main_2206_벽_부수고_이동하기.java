package algorithm.BOJ.Java;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_2206_벽_부수고_이동하기 {
    static class Square {
        int r, c;
        boolean wall;

        public Square(int r, int c, boolean wall) {
            this.r = r;
            this.c = c;
            this.wall = wall;
        }
    }

    static int N, M, answer;
    static int[][] map;
    static boolean[][][] visited;
    static int[] dr = {-1, 0, 1, 0};
    static int[] dc = {0, 1, 0, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        answer = -1;

        map = new int[N][M];
        visited = new boolean[N][M][2]; // 벽을 부신 이후와 부시기 전의 경우를 분리해서 방문 체크

        for (int i = 0; i < N; i++) {
            String str = br.readLine();
            for (int j = 0; j < M; j++) {
                map[i][j] = str.charAt(j) - '0';
            }
        }

        bfs();

        System.out.println(answer);

    }

    static void bfs() {
        Queue<Square> queue = new ArrayDeque<>();
        queue.offer(new Square(0, 0, false));
        int dis = 1;

        while (!queue.isEmpty()) {
            int size = queue.size();

            while (size-- > 0) {
                Square square = queue.poll();

                if(square.r == N - 1 && square.c == M - 1) {
                    answer = dis;
                    return;
                }

                for (int i = 0; i < 4; i++) {
                    int nr = square.r + dr[i];
                    int nc = square.c + dc[i];
                    int wall = square.wall ? 1 : 0;

                    if (nr < 0 || nr >= N || nc < 0 || nc >= M || visited[nr][nc][wall]) continue;

                    if(map[nr][nc] == 1) { // 해당 위치가 벽일 때
                        if(!square.wall) { // 벽을 부신 적이 없으면
                            queue.offer(new Square(nr, nc, true));
                        }
                    } else {
                        if(!square.wall) visited[nr][nc][0] = true; // 벽을 부신 적이 없으면 0번에 방문 체크
                        else visited[nr][nc][1] = true; // 있으면 1번에 방문 체크

                        queue.offer(new Square(nr, nc, square.wall));
                    }
                }
            }

            dis++;

        }

    }
}

