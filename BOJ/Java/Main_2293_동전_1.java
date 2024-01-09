package algorithm.BOJ.Java;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_2293_동전_1 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        int[] coin = new int[N];
//        int[][] dp = new int[K + 1][N];
        int[] dp = new int[K + 1];

        dp[0] = 1;

        for(int i = 0; i < N; i++) {
            coin[i] = Integer.parseInt(br.readLine());

            for(int j = coin[i]; j < K + 1; j++) {
                dp[j] += dp[j - coin[i]];
            }
        }

        System.out.println(dp[K]);

//        Arrays.sort(coin);
//
//        for(int i = coin[0]; i < K + 1; i++) {
//            for(int j = 0; j < N; j++) {
//                if(coin[j] > i) {
//                    break;
//                }
//                else if(coin[j] == i) {
//                    dp[i][j] += 1;
//                }
//
//                for(int k = j; k < N; k++)
//                dp[i][j] += dp[i - coin[j]][k];
//            }
//        }
//
//        int answer = 0;
//
//        for(int i = 0; i < N; i++) {
//            answer += dp[K][i];
//        }
//
//        System.out.println(answer);
    }
}
