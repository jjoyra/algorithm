package algorithm.softeer;

import java.util.*;
import java.io.*;

public class Main_로봇이_지나간_경로 {
    static int H, W, N, R, C, D;
    static boolean[][] visited;
    static int[] dr = {-1, 0, 1, 0};
    static int[] dc = {0, 1, 0, -1};
    static String dAnswer = "^>v<";
    static Deque<Character> answer, list;

    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        H = Integer.parseInt(st.nextToken());
        W = Integer.parseInt(st.nextToken());

        boolean[][] map = new boolean[H][W];
        visited = new boolean[H][W];

        for (int i = 0; i < H; i++) {
            String str = br.readLine();
            for (int j = 0; j < W; j++) {
                if (str.charAt(j) == '.') map[i][j] = true;
            }
        }

        N = Integer.MAX_VALUE;
        answer = new ArrayDeque();

        for (int i = 0; i < H; i++) {
            for (int j = 0; j < W; j++) {
                if (map[i][j]) continue;
                for (int k = 0; k < 4; k++) {
                    list = new ArrayDeque<>();
                    copy(map, visited);
                    visited[i][j] = true;
                    if (dfs(i, j, k)) {

                        if (answer.size() == N) {
                            R = i + 1;
                            C = j + 1;
                            D = k;
                        }
                    }
                }
            }
        }

        System.out.println(R + " " + C);
        System.out.println(dAnswer.charAt(D));
        for (int i = 0; i < N; i++) {
            System.out.print(answer.pollFirst());
        }
    }

    static boolean dfs(int r, int c, int d) {
        boolean flag = false;
        if (isAllVisited(visited)) {
            if (list.size() < N) {
                N = list.size();
                answer = new ArrayDeque<>();
                for (int i = 0; i < N; i++) {
                    answer.addLast(list.peek());
                    list.addLast(list.poll());
                }
                return true;
            }
            return false;
        }

        for (int i = d; i < d + 4; i++) {
            int nr = r;
            int nc = c;
            int nd = i % 4;

            nr = r + dr[nd] * 2;
            nc = c + dc[nd] * 2;

            if (nr < 0 || nr >= H || nc < 0 || nc >= W || visited[nr][nc]) continue;

            nr = r + dr[nd];
            nc = c + dc[nd];

            if (nr < 0 || nr >= H || nc < 0 || nc >= W || visited[nr][nc]) continue;


            if (i == d + 1 || i == d + 2) {
                list.addLast('R');
            } else if (i == d + 3) {
                list.addLast('L');
            }
            list.addLast('A');

            visited[nr + dr[nd]][nc + dc[nd]] = true;
            visited[nr][nc] = true;
            flag = dfs(nr + dr[nd], nc + dc[nd], nd);
            visited[nr][nc] = false;
            visited[nr + dr[nd]][nc + dc[nd]] = false;

            list.pollLast();
            if (i != d) list.pollLast();

        }

        return flag;
    }

    static boolean isAllVisited(boolean[][] visited) {
        for (int i = 0; i < H; i++) {
            for (int j = 0; j < W; j++) {
                if (!visited[i][j]) return false;
            }
        }

        return true;
    }

    static void copy(boolean[][] arr, boolean[][] copyArr) {
        for (int i = 0; i < H; i++) {
            for (int j = 0; j < W; j++) {
                copyArr[i][j] = arr[i][j];
            }
        }
    }


}