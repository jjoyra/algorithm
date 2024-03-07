package algorithm.BOJ.Java;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_4781_사탕_가게 {
    /*
        모든 수가 소수점 둘째짜리까지 -> * 100으로 정수로 만들어서 dp 배열을 만든다.
        단, double형 -> int형으로 형변환할 때 보정을 위해 + 0.5를 해야 한다.
     */
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        while (true) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int M = (int) (Double.parseDouble(st.nextToken()) * 100 + 0.5);

            if(N == 0 && M == 0) {
                break;
            }

            int[] dp = new int[M + 1];

            for(int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                int kcal = Integer.parseInt(st.nextToken());
                int pay = (int) (Double.parseDouble(st.nextToken()) * 100 + 0.5);

                for (int j = pay; j < M + 1; j++) {
                    if (dp[j] < dp[j - pay] + kcal) {
                        dp[j] = dp[j - pay] + kcal;
                    }
                }
            }

            sb.append(dp[M]).append("\n");
        }
        System.out.println(sb);
    }
}
