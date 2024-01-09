package algorithm.BOJ.Java;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_20057_마법사_상어와_토네이도 {
    static int N, answer;
    static int[][] map;
    static int[] sr = {-1, -1, -2, -1, 0, 1, 1, 2, 1, 0};
    static int[] sc = {1, 0, 0, -1, -2, -1, 0, 0, 1, -1};
    static int[] percent = {1, 7, 2, 10, 5, 10, 7, 2, 1, 55};
    static int[] dr = {0, 1, 0, -1};
    static int[] dc = {-1, 0, 1, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        map = new int[N][N];

        for(int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            for(int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int r = N / 2;
        int c = N / 2;

        int num = 1;
        int cnt = 0;
        boolean flag = false;

        while(!flag) {
            int nr = r;
            int nc = c;
            for(int i = 0; i < num; i++) {
                nr += dr[cnt];
                nc += dc[cnt];

                wind(nr, nc, cnt);

                if(nr == 0 && nc == 0) {
                    flag = true;
                    break;
                }
            }

            if(cnt % 2 != 0) {
                num++;
            }

            cnt = (cnt + 1) % 4;

            r = nr;
            c = nc;
        }

        System.out.println(answer);

    }

    static void wind(int r, int c, int cnt) {
        int sum = 0;
        for(int i = 0; i < 10; i++) {
            int nr = r + sr[i];
            int nc = c + sc[i];

            if(cnt == 1) {
                nr = r - sc[i];
                nc = c + sr[i];
            } else if(cnt == 2) {
                nc = c - sc[i];
            } else if(cnt == 3) {
                nr = r + sc[i];
                nc = c + sr[i];
            }

            int sand = map[r][c] * percent[i] / 100;
            if(i == 9) {
                sand = map[r][c] - sum;
            } else {
                sum += sand;
            }

            if(nr < 0 || nc < 0 || nr >= N || nc >= N) {
                answer += sand;
            } else {
                map[nr][nc] += sand;
            }
        }

        map[r][c] = 0;
    }
}
