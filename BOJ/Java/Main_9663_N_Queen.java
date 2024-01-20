package algorithm.BOJ.Java;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main_9663_N_Queen {
    static int N, answer;
    static int[] arr;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        arr = new int[N + 1];

        recursion(0);

        System.out.println(answer);
    }

    static void recursion(int num) {
        for(int j = 1; j < num; j++) { // 현재 줄에 놓인 말이 이전에 놓인 말에 의해 공격당할 수 있으면
            if(arr[j] == arr[num]      // 종료
                    || Math.abs(j - num) == Math.abs(arr[j] - arr[num])) return;
        }

        if(num == N) { // 현재 줄이 마지막 줄이면 cnt 체크 후 종료
            answer++;
            return;
        }

        for(int i = 1; i <= N; i++) { // 다음 줄의 N개의 칸에 한 번씩 말을 놓고 재귀 함수 호출
            arr[num + 1] = i;
            recursion(num + 1);
        }
    }
}
