package algorithm.BOJ.Java;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main_2447_별_찍기_10 {
    static char[][] map;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(br.readLine());
        map = new char[N][N];

        divide(0, 0, N, true);

        for(int i = 0; i < N; i++) {
            for(int j = 0; j < N; j++) {
                sb.append(map[i][j]);
            }
            sb.append("\n");
        }

        System.out.println(sb);
    }

    static void divide(int r, int c, int n, boolean flag) {
        if(!flag) {
            for(int i = r; i < r + n; i++) {
                for(int j = c; j < c + n; j++) {
                    map[i][j] = ' ';
                }
            }
            return;
        }

        if(n == 1) {
            map[r][c] = '*';
            return;
        }

        int gap = n / 3;
        for(int i = r; i < r + n; i += gap) {
            for(int j = c; j < c + n; j += gap) {
                if(i == r + gap && j == c + gap) {
                    divide(i, j, gap, false);
                } else divide(i, j, gap, true);
            }
        }
    }
}
