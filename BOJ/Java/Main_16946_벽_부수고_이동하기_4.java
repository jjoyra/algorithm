package algorithm.BOJ.Java;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main_16946_벽_부수고_이동하기_4 {
    static int N, M, num;
    static int[][] map;
    static Map<Integer, Integer> cnt;
    static int[] dr = {-1, 0, 1, 0};
    static int[] dc = {0, 1, 0, -1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        cnt = new HashMap<>();
        num = 2;

        for(int i = 0; i < N; i++) {
           String string = br.readLine();
            for(int j = 0; j < M; j++) {
                if(string.charAt(j) == '1') {
                    map[i][j] = 1;
                }
            }
        }

        for(int i = 0; i < N; i++) {
            for(int j = 0; j < M; j++) {
                if(map[i][j] != 0) continue;
                bfs(i, j);
            }
        }

        StringBuilder sb = new StringBuilder();

        for(int i = 0; i < N; i++) {
            for(int j = 0; j < M; j++) {
                if(map[i][j] == 1) {
                    int sum = 1;
                    Set<Integer> set = new HashSet<>();
                    for(int k = 0; k < 4; k++) {
                        int nr = i + dr[k];
                        int nc = j + dc[k];
                        if(nr < 0 || nc < 0 || nr >= N || nc >= M || map[nr][nc] == 1) continue;
                        set.add(map[nr][nc]);
                    }

                    for(int idx : set) {
                        sum += cnt.get(idx);
                    }

                    sb.append(sum % 10);
                } else {
                    sb.append(0);
                }
            }
            sb.append("\n");
        }

        System.out.println(sb);
    }

    static void bfs(int r, int c) {
        Queue<int[]> queue = new ArrayDeque<>();
        queue.offer(new int[] {r, c});
        map[r][c] = num;

        int sum = 0;

        while(!queue.isEmpty()) {
            int[] tmp = queue.poll();
            sum++;

            for(int i = 0; i < 4; i++) {
                int nr = tmp[0] + dr[i];
                int nc = tmp[1] + dc[i];

                if(nr < 0 || nc < 0 || nr >= N || nc >= M || map[nr][nc] != 0) continue;
                map[nr][nc] = num;
                queue.offer(new int[] {nr, nc});
            }
        }

        cnt.put(num, sum);

        num++;
    }
}
