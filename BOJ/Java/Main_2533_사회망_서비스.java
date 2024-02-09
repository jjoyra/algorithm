package algorithm.BOJ.Java;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main_2533_사회망_서비스 {
    static List<Integer>[] adj;
    static int[][] dp;
    static boolean[] visited;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        adj = new List[N + 1];
        visited = new boolean[N + 1];
        dp = new int[N + 1][2];

        for(int i = 1; i < N + 1; i++) {
            adj[i] = new ArrayList<>();
        }

        for(int i = 0; i < N - 1; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            adj[a].add(b);
            adj[b].add(a);
        }

        dfs(1);

        System.out.println(Math.min(dp[1][0], dp[1][1]));

    }

    static void dfs(int node) {
        visited[node] = true;
        int isNotEarly = 0;
        int isEarly = 1;
        for(int num : adj[node]) {
            if(visited[num]) continue;
            dfs(num);

            isNotEarly += dp[num][1];
            isEarly += Math.min(dp[num][0], dp[num][1]);
        }

        dp[node][0] = isNotEarly;
        dp[node][1] = isEarly;
    }
}
