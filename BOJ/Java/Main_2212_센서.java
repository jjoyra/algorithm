package algorithm.BOJ.Java;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main_2212_센서 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        int N = Integer.parseInt(br.readLine());
        int K = Integer.parseInt(br.readLine());
        int[] arr = new int[N];

        st = new StringTokenizer(br.readLine());

        for(int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        // 좌표값 오름차순으로 정렬
        Arrays.sort(arr);

        // 인접한 좌표끼리의 거리 계산
        Integer[] arr2 = new Integer[N - 1];

        for(int i = 0; i < N - 1 ; i++) {
            arr2[i] = arr[i + 1] - arr[i];
        }

        // 좌표 간의 거리를 내림차순으로 정렬
        Arrays.sort(arr2, Collections.reverseOrder());

        // 좌표 거리가 가장 긴 K - 1개의 값을 제외한 나머지 값을 더해줌
        int answer = 0;
        for(int i = K - 1; i < N - 1; i++) {
            answer += arr2[i];
        }

        System.out.println(answer);
    }
}
