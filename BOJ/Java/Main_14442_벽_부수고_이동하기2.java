package algorithm.BOJ.Java;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_14442_벽_부수고_이동하기2 {
    static class Square {
        int r, c, cnt;

        public Square(int r, int c, int cnt) {
            this.r = r;
            this.c = c;
            this.cnt = cnt;
        }
    }

    static int N, M, K, map[][], answer;
    static int visited[][];
    static int[] dr = {-1, 0, 1, 0};
    static int[] dc = {0, 1, 0, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        answer = -1;

        map = new int[N][M];
        visited = new int[N][M];

        for (int i = 0; i < N; i++) {
            String str = br.readLine();
            for (int j = 0; j < M; j++) {
                map[i][j] = str.charAt(j) - '0';
            }
        }

        for(int i = 0; i < N; i++) {
            Arrays.fill(visited[i], 11); // 11은 아직 방문하지 않은 칸
        }

        bfs();

        System.out.println(answer);

    }

    static void bfs() {
        Queue<Square> queue = new ArrayDeque<>();
        int count = 1;
        queue.offer(new Square(0, 0, 0));
        visited[0][0] = 0; // 벽을 뚫지 않고 방문했을 때는 0

        while (!queue.isEmpty()) {
            int size = queue.size();
            while (size-- > 0) {
                Square square = queue.poll();

                if (square.r == N - 1 && square.c == M - 1) {
                    answer = count;
                    return;
                }

                for (int i = 0; i < 4; i++) {
                    int nr = square.r + dr[i];
                    int nc = square.c + dc[i];

                    if (nr < 0 || nr >= N || nc < 0 || nc >= M) continue;

                    // 방문체크 배열에서 다음 위치 칸에 저장된 횟수가 현재 위치에서 벽을 부순 횟수(+1)보다 작거나 같으면
                    // 벽을 적거나 같게 부수면서 이미 해당 위치를 방문했다는 뜻이므로 방문할 필요가 없음
                    // -> 3차원 배열을 사용하는 것보다 시간/공간 절약

                    if (map[nr][nc] == 1) { // 다음 위치가 벽일 경우
                        if (square.cnt < K) { // 벽을 부순 횟수가 K보다 작을 경우
                             if(visited[nr][nc] <= square.cnt + 1) continue;
                            visited[nr][nc] = square.cnt + 1; // 방문 체크 배열에 현재 벽 부순 횟수 + 1
                            queue.offer(new Square(nr, nc, square.cnt + 1)); // 벽 부순 횟수 증가
                        }
                    } else { // 벽이 아닐 경우
                        if(visited[nr][nc] <= square.cnt) continue;
                        visited[nr][nc] = square.cnt; // 방문 체크 배열에 현재 벽 부순 횟수
                        queue.offer(new Square(nr, nc, square.cnt));
                    }
                }
            }

            count++;
        }
    }
}
