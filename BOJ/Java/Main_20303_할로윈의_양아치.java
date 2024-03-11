package algorithm.BOJ.Java;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main_20303_할로윈의_양아치 {
    /*
     1차원 DP를 사용하려면 DP 배열을 뒤에서부터 계산해야 한다.
     i - 1번째의 값을 참조해야 하는데, 앞에서부터 계산하면 1차원 배열의 갱신이 일어나기 때문.
     */
    static int N, M, K;
    static int[] candy;
    static boolean[] visited;
    static List<Integer>[] friends;
    static List<int[]> group;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        candy = new int[N + 1];
        friends = new List[N + 1];
        st = new StringTokenizer(br.readLine());

        for(int i = 1; i < N + 1; i++) {
            candy[i] = Integer.parseInt(st.nextToken());
            friends[i] = new ArrayList<>();
        }

        for(int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            friends[a].add(b);
            friends[b].add(a);
        }

        group = new ArrayList<>();
        visited = new boolean[N + 1];

        for(int i = 1; i < N + 1; i++) {
            if(visited[i]) continue;
            bfs(i);
        }

        int[] dp = new int[K];

        for(int i = 1; i < group.size() + 1; i++) {
            int weight = group.get(i - 1)[0];
            int value = group.get(i - 1)[1];

            for(int j = K - 1; j >= weight; j--) {
                dp[j] = Math.max(dp[j], dp[j - weight] + value);
            }
        }

        System.out.println(dp[K - 1]);
    }

    static void bfs(int i) {
        int sum = candy[i];
        int cnt = 1;
        Queue<Integer> queue = new ArrayDeque<>();
        queue.offer(i);
        visited[i] = true;

        while(!queue.isEmpty()) {
            int tmp = queue.poll();

            for(int next : friends[tmp]) {
                if(visited[next]) continue;

                queue.offer(next);
                visited[next] = true;
                sum += candy[next];
                cnt++;
            }
        }

        group.add(new int[] {cnt, sum});
    }
}
