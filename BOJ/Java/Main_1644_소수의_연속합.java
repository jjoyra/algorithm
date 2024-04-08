package algorithm.BOJ.Java;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main_1644_소수의_연속합 {
    static int N, answer;
    static boolean[] prime;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        prime = new boolean[N + 1];
        prime[0] = true;
        prime[1] = true;

        List<Integer> list = new ArrayList<>();

        for(int i = 2; i < N + 1; i++) {
            if(!prime[i]) {
                for(int j = i + i; j < N + 1; j += i) {
                    prime[j] = true;
                }
                list.add(i);
            }
        }

        int start = 0;
        int end = N == 1 ? -1 : 0;
        int sum = N == 1 ? 0 : list.get(0);

        while(start <= end) {
            if(sum == N) {
                answer++;
            }

            if(sum < N && end < list.size() - 1) {
                sum += list.get(++end);
            } else {
                sum -= list.get(start++);
            }
        }

        System.out.println(answer);
    }
}
