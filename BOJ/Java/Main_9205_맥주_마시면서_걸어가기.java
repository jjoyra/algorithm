package BOJ.Java;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_9205_맥주_마시면서_걸어가기 {
    static int N;
    static int[][] map;
    static StringBuilder sb;
    static boolean flag;
    static boolean[] visited;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());

        for(int tc = 1; tc <= T; tc++) {
            N = Integer.parseInt(br.readLine());
            flag = false;
            map = new int[2 + N][2];
            visited = new boolean[N];

            for(int i = 0; i < 2 + N; i++) {
                st = new StringTokenizer(br.readLine());
                map[i][0] = Integer.parseInt(st.nextToken());
                map[i][1] = Integer.parseInt(st.nextToken());
            }

            dfs(0, 0);

            if(flag) sb.append("happy\n");
            else sb.append("sad\n");
        }

        System.out.println(sb);
    }

    static void dfs(int cnt, int idx) { // 이전 위치
        int dis = Math.abs(map[idx][0] - map[N + 1][0]) + Math.abs(map[idx][1] - map[N + 1][1]);
        if(dis <= 1000) flag = true;

        if(cnt == N) return;
        if(flag) return;

        for(int i = 0; i < N; i++) {
            if(visited[i]) continue;
            dis = Math.abs(map[idx][0] - map[i + 1][0]) + Math.abs(map[idx][1] - map[i + 1][1]);
            if(dis > 1000) continue;

            visited[i] = true;
            dfs(cnt + 1, i + 1);
        }

    }
}
