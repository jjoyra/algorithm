package algorithm.BOJ.Java;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;

public class Main_1938_통나무_옮기기 {
    static int N, bDir, eDir, answer;
    static int[][] map;
    static int[] B, E;
    static int[] dr = {-1, -1, 0, 1, 1, 1, 0, -1};
    static int[] dc = {0, 1, 1, 1, 0, -1, -1, -1};
    static boolean[][][] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        map = new int[N][N];
        visited = new boolean[N][N][2];

        B = new int[2];
        E = new int[2];
        answer = Integer.MAX_VALUE;

        for (int i = 0; i < N; i++) {
            String str = br.readLine();
            for (int j = 0; j < N; j++) {
                map[i][j] = str.charAt(j);
                if (map[i][j] == 'B') {
                    B[0] = i;
                    B[1] = j;
                } else if (map[i][j] == 'E') {
                    E[0] = i;
                    E[1] = j;
                } else {
                    map[i][j] = map[i][j] - '0';
                }
            }
        }

        bDir = findDir(B[0], B[1], 'B');
        eDir = findDir(E[0], E[1], 'E');

        if (bDir == 0) B[1]--;
        else B[0]--;

        if (eDir == 0) E[1]--;
        else E[0]--;

        bfs();

        System.out.println(answer == Integer.MAX_VALUE ? 0 : answer);
    }

    static int findDir(int r, int c, int roc) { // 0 : 가로, 1 : 세로
        if (r == 0) return 0;
        else if (c == 0) return 1;
        else if (map[r][c - 1] == roc) {
            return 0;
        } else {
            return 1;
        }
    }

    private static void bfs() {
        Queue<int[]> queue = new ArrayDeque<>();
        queue.offer(new int[]{B[0], B[1], bDir});
        int cnt = 0;

        while (!queue.isEmpty()) {
            int size = queue.size();
            while (size-- > 0) {
                int[] tmp = queue.poll();
                int r = tmp[0];
                int c = tmp[1];
                int dir = tmp[2];

                if (r == E[0] && c == E[1] && dir == eDir) {
                    answer = Math.min(answer, cnt);
                    return;
                }

                if (visited[r][c][dir]) continue;
                visited[r][c][dir] = true;

                for (int i = 0; i < 4; i++) {
                    int nr = r + dr[i * 2];
                    int nc = c + dc[i * 2];
                    if (dir == 1) {
                        if (nc < 0 || nc >= N || nr - 1 < 0 || nr + 1 >= N) continue;
                        if (map[nr][nc] == 1 || map[nr - 1][nc] == 1 || map[nr + 1][nc] == 1) continue;
                    } else {
                        if (nr < 0 || nr >= N || nc - 1 < 0 || nc + 1 >= N) continue;
                        if (map[nr][nc] == 1 || map[nr][nc - 1] == 1 || map[nr][nc + 1] == 1) continue;
                    }
                    queue.offer(new int[]{nr, nc, dir});
                }
                if (canTurn(r, c)) queue.offer(new int[]{r, c, (dir + 1) % 2});
            }
            cnt++;
        }

    }

    static boolean canTurn(int r, int c) {
        for (int i = 0; i < 8; i++) {
            int nr = r + dr[i];
            int nc = c + dc[i];

            if (nr < 0 || nr >= N || nc < 0 || nc >= N || map[nr][nc] == 1)
                return false;
        }
        return true;
    }
}
