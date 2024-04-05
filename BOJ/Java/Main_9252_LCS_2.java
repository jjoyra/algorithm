package algorithm.BOJ.Java;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main_9252_LCS_2 {
    static int[][] dp;
    static int length;
    static char[] answer;
    static String a, b;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        a = br.readLine();
        b = br.readLine();

        dp = new int[a.length() + 1][b.length() + 1];

        for(int i = 0; i < a.length(); i++) {
            for(int j = 0; j < b.length(); j++) {
                if(a.charAt(i) == b.charAt(j)) {
                    dp[i + 1][j + 1] = dp[i][j] + 1;
                } else {
                    dp[i + 1][j + 1] = Math.max(dp[i][j + 1], dp[i + 1][j]);
                }
            }
        }

        length = dp[a.length()][b.length()];
        answer = new char[length];

        lcs(a.length(), b.length());

        System.out.println(length);

        if(length > 0) {
            System.out.println(answer);
        }
    }

    static void lcs(int aIdx, int bIdx) {
        if(dp[aIdx][bIdx] == 0) return;
        else if(dp[aIdx][bIdx - 1] == dp[aIdx][bIdx]) {
            lcs(aIdx, bIdx - 1);
        } else if(dp[aIdx - 1][bIdx] == dp[aIdx][bIdx]) {
            lcs(aIdx - 1, bIdx);
        } else if(dp[aIdx - 1][bIdx - 1] == dp[aIdx][bIdx] - 1) {
            answer[dp[aIdx][bIdx] - 1] = a.charAt(aIdx - 1);
            lcs(aIdx - 1, bIdx - 1);
        }
    }
}
