package algorithm.programmers.Java;

import java.util.*;

class Solution_피로도 {
    static int cur, answer, N;
    static boolean[] visited;
    public int solution(int k, int[][] dungeons) {
        answer = 0;
        cur = k;
        N = dungeons.length;
        visited = new boolean[N];

        per(0, 0, cur, dungeons);

        return answer;
    }

    static void per(int cnt, int visit, int cur, int[][] dungeons) {
        if(N == cnt) {
            answer = Math.max(answer, visit);
        }

        for(int i = 0; i < N; i++) {
            if(visited[i]) continue;

            visited[i] = true;
            if (dungeons[i][0] > cur) {
                per(cnt + 1, visit, cur, dungeons);
            } else {
                per(cnt + 1, visit + 1, cur - dungeons[i][1], dungeons);
            }
            visited[i]= false;
        }
    }
}