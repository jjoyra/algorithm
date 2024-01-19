package algorithm.programmers.Java;

import java.util.*;
public class Solution_PCCP_기출_2번_석유_시추 {
    static int[][] landMap;
    static int[] dr = {-1, 0, 1, 0};
    static int[] dc = {0, 1, 0, -1};
    static int N, M, labal;
    static Map<Integer, Integer> map;

    public int solution(int[][] land) {
        int answer = 0;
        N = land.length;
        M = land[0].length;
        landMap = land;
        map = new HashMap<>();
        labal = 2;

        for(int i = 0; i < N; i++) {
            for(int j = 0; j < M; j++) {
                if(landMap[i][j] != 1) continue;
                bfs(i, j);
            }
        }

        for(int i = 0; i < M; i++) {
            Set<Integer> set = new HashSet<>();
            for(int j = 0; j < N; j++) {
                if(landMap[j][i] == 0) continue;
                set.add(landMap[j][i]);
            }

            int sum = 0;

            for(int labal : set) {
                sum += map.get(labal);
            }

            answer = Math.max(answer, sum);
        }

        return answer;
    }

    static void bfs(int r, int c) {
        int cnt = 0;
        Queue<int[]> queue = new ArrayDeque<>();
        queue.offer(new int[] {r, c});
        landMap[r][c] = labal;

        while(!queue.isEmpty()) {
            int[] tmp = queue.poll();
            cnt++;

            for(int i = 0; i < 4; i++) {
                int nr = tmp[0] + dr[i];
                int nc = tmp[1] + dc[i];

                if(nr < 0 || nc < 0 || nr >= N || nc >= M || landMap[nr][nc] != 1)
                    continue;

                queue.offer(new int[] {nr, nc});
                landMap[nr][nc] = labal;
            }
        }

        map.put(labal++, cnt);
    }

}
