package algorithm.BOJ.Java;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main_15961_회전_초밥 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int d = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());

        int[] sushi = new int[N];
        int[] type = new int[d + 1];

        for(int i = 0; i < N; i++) {
            sushi[i] = Integer.parseInt(br.readLine());
        }

        int cnt = 1;
        type[c]++;

        for(int i = 0; i < k; i++) {
            if(type[sushi[i]] == 0) cnt++;

            type[sushi[i]]++;
        }

        int answer = cnt;

        for(int i = 0; i < N; i++) {
            if(type[sushi[i]] == 1) cnt--;

            type[sushi[i]]--;

            if(type[sushi[(i + k) % N]] == 0) cnt++;

            type[sushi[(i + k) % N]]++;

            answer = Math.max(answer, cnt);
        }

        System.out.println(answer);
    }
}
