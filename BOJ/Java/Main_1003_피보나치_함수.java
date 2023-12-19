package algorithm.BOJ.Java;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main_1003_피보나치_함수 {
    static int[][] fibonacci;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());
        fibonacci = new int[41][2];

        fibonacci[0][0] = 1;
        fibonacci[1][1] = 1;

        for(int j = 2; j < 41; j++) {
            fibonacci[j][0] = fibonacci[j - 1][0] + fibonacci[j - 2][0];
            fibonacci[j][1] = fibonacci[j - 1][1] + fibonacci[j - 2][1];
        }

        for(int i = 0; i < T; i++) {
            int N = Integer.parseInt(br.readLine());

            System.out.println(fibonacci[N][0] + " " + fibonacci[N][1]);
        }
    }
}
