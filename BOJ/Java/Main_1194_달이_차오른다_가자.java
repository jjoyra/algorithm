package algorithm.BOJ.Java;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_1194_달이_차오른다_가자 {
    static class Square {
        int r, c, key;

        Square(int r, int c, int key) {
            this.r = r;
            this.c = c;
            this.key = key;
        }
    }

    static int[] dr = {-1, 0, 1, 0};
    static int[] dc = {0, 1, 0, -1};
    static int N, M, map[][], visited[][], answer;
    static int[] start;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        visited = new int[N][M];

        for (int i = 0; i < N; i++) {
            Arrays.fill(visited[i], -1); // 방문체크 배열의 초기 상태 : -1
        }

        start = new int[2];
        answer = -1;

        for (int i = 0; i < N; i++) {
            String str = br.readLine();
            for (int j = 0; j < M; j++) {
                map[i][j] = str.charAt(j);

                if (map[i][j] == '0') {
                    start[0] = i;
                    start[1] = j;
                }
            }
        }

        bfs();

        System.out.println(answer);

    }

    static void bfs() {
        Queue<Square> queue = new ArrayDeque<>();
        int time = 0;

        queue.offer(new Square(start[0], start[1], 0));
        visited[start[0]][start[1]] = 0;

        while (!queue.isEmpty()) {
            int size = queue.size();

            while (size-- > 0) {
                Square square = queue.poll();

                if (map[square.r][square.c] == '1') { // 목적지에 도착했을 경우 bfs 종료
                    answer = time;
                    return;
                }

                for (int i = 0; i < 4; i++) {
                    int nr = square.r + dr[i];
                    int nc = square.c + dc[i];

                    if (nr < 0 || nr >= N || nc < 0 || nc >= M || map[nr][nc] == '#') continue; // 범위를 벗어나거나 벽일 경우 탐색 X
                    if (visited[nr][nc] >= 0 && (visited[nr][nc] == (visited[nr][nc] | square.key))) continue;
                    // 현재 열쇠 상태로 방문한 적이 있으면 탐색 X -> 현재 열쇠 상태와 방문 체크 배열의 열쇠를 비교했을 때 현재 열쇠 상태가 새로운 열쇠를 갖고 있지 않는 경우
                    if (map[nr][nc] - 'a' >= 0 && map[nr][nc] - 'a' < 6) { // 이동한 칸이 열쇠일 경우
                        int tmp = map[nr][nc] - 'a';
                        queue.offer(new Square(nr, nc, square.key | 1 << tmp));
                        visited[nr][nc] = square.key | 1 << tmp;
                    } else if (map[nr][nc] - 'A' >= 0 && map[nr][nc] - 'A' < 6) { // 이동한 칸이 문일 경우
                        int tmp = map[nr][nc] - 'A';
                        if ((square.key & 1 << tmp) != 0) { // 해당 문에 맞는 열쇠를 갖고 있을 경우
                            queue.offer(new Square(nr, nc, square.key));
                            visited[nr][nc] = square.key;
                        }
                    } else { // 방문할 수 있는 칸일 경우
                        queue.offer(new Square(nr, nc, square.key));
                        visited[nr][nc] = square.key;
                    }
                }
            }

            time++;
        }
    }

}
