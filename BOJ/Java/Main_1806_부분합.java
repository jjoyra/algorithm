package algorithm.BOJ.Java;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_1806_부분합 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int S = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        int[] arr = new int[N + 1];
        for(int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int start = 0;
        int end = 0;
        int answer = N + 1;
        int sum = arr[0];

        while(end < N) {
            if(sum >= S) {
                answer = Math.min(answer, end - start + 1);
                if(start == end) {
                    answer = 1;
                    break;
                }
                sum -= arr[start];
                start++;
            } else {
                end++;
                sum += arr[end];
            }
        }

        System.out.println(answer == N + 1 ? 0 : answer);
    }
}
