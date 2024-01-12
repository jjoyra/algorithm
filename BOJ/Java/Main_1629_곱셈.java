package algorithm.BOJ.Java;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_1629_곱셈 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int A = Integer.parseInt(st.nextToken());
        int B = Integer.parseInt(st.nextToken());
        int C = Integer.parseInt(st.nextToken());

        // 분할 정복과 DP의 차이점은 부분 문제가 중복되는지 여부

        System.out.println(recursion(A, B, C));
    }

    static long recursion(int A, int B, int C) {
        if(B == 1) return A % C;
        else {
            long result = recursion(A, B /2, C);
            return B % 2 == 0 ? (result * result) % C : ((result * result) % C) * (A % C) % C;
        }
    }
}
