package algorithm.BOJ.Java;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_2118_공유기_설치 {
    static int N, C, answer;
    static int[] house;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        house = new int[N];

        for(int i = 0; i < N; i++) {
            house[i] = Integer.parseInt(br.readLine());
        }

        Arrays.sort(house);

        binarySearch(1, (house[N - 1] - house[0]) / (C - 1));

        System.out.println(answer);
    }

    static void binarySearch(int left, int right) {
        if(left > right) return;

        int mid = (left + right) / 2;
        int start = house[0];
        int cnt = 1;

        for(int i = 1; i < N; i++) {
            if(house[i] - start >= mid) {
                cnt++;
                start = house[i];
            }
        }

        if(cnt >= C) {
            answer = mid;
            binarySearch(mid + 1, right);
        } else {
            binarySearch(left, mid - 1);
        }
    }
}
