package algorithm.BOJ.Java;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main_2234_성곽 {
    static int N, M, cnt, maxArea, maxSum;
    static int[][] map, label;
    static Map<Integer, Integer> labelSize;
    static int[] dr = {0, -1, 0, 1};
    static int[] dc = {-1, 0, 1, 0};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[M][N];
        label = new int[M][N];

        labelSize = new HashMap<>();
        cnt = 1;

        for(int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for(int i = 0; i < M; i++) {
            for(int j = 0; j < N; j++) {
                if(label[i][j] != 0) continue;
                bfs(i, j);
            }
        }

        System.out.println(cnt - 1);
        System.out.println(maxArea);
        System.out.println(maxSum);
    }

    static void bfs(int r, int c) {
        int size = 0;
        int nearArea = 0;
        Queue<int[]> queue = new ArrayDeque<>();
        queue.offer(new int[] {r, c});
        label[r][c] = cnt;
        while (!queue.isEmpty()) {
            int[] tmp = queue.poll();
            size++;

            for(int i = 0; i < 4; i++) {
                int nr = tmp[0] + dr[i];
                int nc = tmp[1] + dc[i];
                if(nr < 0 || nc < 0 || nr >= M || nc >= N || label[nr][nc] == cnt) continue;
                else if((map[nr][nc] & 1 << (i + 2) % 4) != 0) {
                    if(label[nr][nc] != 0) {
                        nearArea = Math.max(nearArea, labelSize.get(label[nr][nc]));
                    }
                    continue;
                }

                label[nr][nc] = cnt;
                queue.offer(new int[] {nr, nc});
            }
        }

        labelSize.put(cnt++, size);
        maxArea = Math.max(maxArea, size);
        maxSum = Math.max(maxSum, nearArea + size);
    }
}
