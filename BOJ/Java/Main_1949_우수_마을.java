package algorithm.BOJ.Java;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Main_1949_우수_마을 {
    static int N;
    static int[] people;
    static boolean[] visited;
    static int[][] dp;
    static List<Integer>[] adjList;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        people = new int[N];
        dp = new int[N][2];
        adjList = new List[N];
        visited = new boolean[N];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++) {
            people[i] = Integer.parseInt(st.nextToken());
            adjList[i] = new ArrayList<>();
        }

        for(int i = 0; i < N - 1; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken()) - 1;
            int b = Integer.parseInt(st.nextToken()) - 1;

            adjList[a].add(b);
            adjList[b].add(a);
        }

        dfs(0);

        System.out.println(Math.max(dp[0][0], dp[0][1]));
    }

    static void dfs(int num) {
        dp[num][0] += people[num];

        for(int next : adjList[num]) {
            if(dp[next][0] != 0) continue;
            dfs(next);
            dp[num][0] += dp[next][1];
            dp[num][1] += Math.max(dp[next][0], dp[next][1]);
        }
    }
}
