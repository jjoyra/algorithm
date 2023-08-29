package algorithm.softeer;

import java.util.*;
import java.io.*;


public class Main_순서대로_방문하기 {
    static int n, m, map[][], arr[][], answer;
    static boolean[][] visited;
    static int[] dr = {-1, 0, 1, 0};
    static int[] dc = {0, 1, 0, -1};

    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        map = new int[n][n];
        arr = new int[m][2];
        visited = new boolean[n][n];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            arr[i][0] = Integer.parseInt(st.nextToken()) - 1;
            arr[i][1] = Integer.parseInt(st.nextToken()) - 1;

            map[arr[i][0]][arr[i][1]] = 2;

        }

        dfs(0, arr[0][0], arr[0][1]);
        System.out.println(answer);

    }

    static void dfs(int cnt, int r, int c) {
        if (arr[m - 1][0] == r && arr[m - 1][1] == c) { // 도착지에 방문하면 종료
            answer++;
            return;
        }

        for (int i = 0; i < 4; i++) {
            int nr = r + dr[i];
            int nc = c + dc[i];

            if (nr < 0 || nr >= n || nc < 0 || nc >= n
                    || visited[nr][nc] || map[nr][nc] == 1) continue;

            visited[r][c] = true;

            if (nr == arr[cnt + 1][0] && nc == arr[cnt + 1][1]) dfs(cnt + 1, nr, nc); // 다음에 방문해야할 지점에 방문하면 cnt 증가
            else if (map[nr][nc] != 2) dfs(cnt, nr, nc); // 순서대로 방문하기 위해 다음에 방문할 지점이 아니면 먼저 방문하면 안됨

            visited[nr][nc] = false;

        }

    }
}