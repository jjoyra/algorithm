package algorithm.BOJ.Java;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_1106_호텔 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int C = Integer.parseInt(st.nextToken());
        int N = Integer.parseInt(st.nextToken());

        int[] cost = new int[N];
        int[] customer = new int[N];

        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            cost[i] = Integer.parseInt(st.nextToken());
            customer[i] = Integer.parseInt(st.nextToken());
        }
        int[] dp = new int[C + 1];
        Arrays.fill(dp, 100001);
        dp[0] = 0;

        for(int i = 0; i < N; i++) {
            for(int j = 0; j < C + 1; j++) {
                if (j < customer[i]) {
                    dp[j] = Math.min(dp[j], cost[i]);
                } else {
                    dp[j] = Math.min(dp[j], dp[j - customer[i]] + cost[i]);
                }
            }
        }

        System.out.println(dp[C]);
    }
}
