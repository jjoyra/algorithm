package algorithm.BOJ.Java;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collection;
import java.util.Collections;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main_14719_빗물 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int H = Integer.parseInt(st.nextToken());
        int W = Integer.parseInt(st.nextToken());

        int[] arr = new int[W];
        st = new StringTokenizer(br.readLine());

        int answer = 0;

        Stack<Integer> stack = new Stack<>();

        for(int i = 0; i < W; i++) {
            arr[i] = Integer.parseInt(st.nextToken());

            if(stack.isEmpty() && arr[i] != 0) {
                stack.add(i);
            }
        }

        int tmp = stack.peek();

        while(tmp < W) {
            int max = tmp + 1;
            for(int i = tmp + 1; i < W; i++) {
                if(arr[tmp] <= arr[i]) {
                    stack.push(i);
                    break;
                } else if(arr[max] < arr[i]) {
                    max = i;
                }
            }

            if(tmp == stack.peek()) {
                stack.push(max);
            }
            tmp = stack.peek();
        }

        while(stack.size() > 1) {
            tmp = stack.pop();
            for(int i = tmp - 1; i > stack.peek(); i--) {
                answer += Math.min(arr[tmp], arr[stack.peek()]) - arr[i];
            }
        }

        System.out.println(answer);

    }
}
