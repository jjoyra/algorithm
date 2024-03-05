package algorithm.BOJ.Java;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_2240_수_고르기 {
    static int N, M;
    static int[] arr;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        arr = new int[N];

        for(int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }

        Arrays.sort(arr);

        int answer = Integer.MAX_VALUE;
        int start = 0;
        int end = 0;

        while(start < N && end < N) {
            int tmp = Math.abs(arr[start] - arr[end]);

            if(tmp < M || start == end){
                end++;
            } else {
                answer = Math.min(answer, tmp);
                start++;
            }
        }

        System.out.println(answer);
    }
}
