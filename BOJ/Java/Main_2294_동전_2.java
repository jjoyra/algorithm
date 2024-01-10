package algorithm.BOJ.Java;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_2294_동전_2 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        int[] dp = new int[K + 1];

        Arrays.fill(dp, 10001);
        dp[0] = 0;

        for(int i = 0; i < N; i++) {
            int coin = Integer.parseInt(br.readLine());

            for(int j = coin; j < K + 1; j++) {
                dp[j] = Math.min(dp[j - coin] + 1, dp[j]);
            }
        }

        System.out.println(dp[K] != 10001 ? dp[K] : -1);
    }
}
