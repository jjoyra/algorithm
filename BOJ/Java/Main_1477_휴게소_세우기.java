package algorithm.BOJ.Java;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_1477_휴게소_세우기 {
    static int N, M, L, minDistance;
    static int[] restArea;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());

        restArea = new int[N + 2];
        st = new StringTokenizer(br.readLine());

        int[] input = new int[N + 2];
        input[N] = 0;
        input[N + 1] = L;

        for(int i = 0; i < N; i++) {
            input[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(input);

        for(int i = 0; i < N + 1; i++) {
            restArea[i] = input[i + 1] - input[i];
        }

        Arrays.sort(restArea);

        minDistance = restArea[N + 1];
        binarySearch(1, restArea[N + 1]);
        System.out.println(minDistance);
    }

    static void binarySearch(int start, int end) {
        if(start > end) return;
        int mid = (start + end) / 2;
        int cnt = 0;
        boolean flag = true;

        for(int i = 0; i < N + 2; i++) {
            if(restArea[i] > mid) {
                cnt += restArea[i] % mid == 0 ? restArea[i] / mid - 1 : restArea[i] / mid;
                if(cnt > M) flag = false;
            }
        }

        if(flag) {
            minDistance = Math.min(minDistance, mid);
            binarySearch(start, mid - 1);
        } else {
            binarySearch(mid + 1, end);
        }
    }
}
