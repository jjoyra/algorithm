package algorithm.BOJ.Java;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_1459_걷기 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int X = Integer.parseInt(st.nextToken());
        int Y = Integer.parseInt(st.nextToken());
        long W = Integer.parseInt(st.nextToken());
        long S = Integer.parseInt(st.nextToken());

        long answer = 0;

        int num = Math.min(X, Y);
        if(W * 2 > S) {
            answer += S * num;
        } else {
            answer += W * 2 * num;
        }
        X -= num;
        Y -= num;

        num = X + Y;

        if(W > S) {
            answer += num / 2 * 2 * S;
            answer += num % 2 * W;
        } else {
            answer += num * W;
        }

        System.out.println(answer);
    }
}
