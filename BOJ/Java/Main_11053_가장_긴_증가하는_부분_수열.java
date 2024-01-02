package algorithm.BOJ.Java;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_11053_가장_긴_증가하는_부분_수열 {
    static int[] arr, visited;
    static int N;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());
        arr = new int[N];
        visited = new int[N];

        for(int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.fill(visited, 1);

        int answer = 1;

        for(int i = 0; i < N; i++) {
            for(int j = i - 1; j >= 0; j--) {
                if(arr[i] > arr[j]) {
                    visited[i] = Math.max(visited[i], visited[j] + 1);
                }
            }
            answer = Math.max(answer, visited[i]);
        }
        System.out.println(answer);
    }
}
