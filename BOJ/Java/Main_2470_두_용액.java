package algorithm.BOJ.Java;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_2470_두_용액 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());

        int[] arr = new int[N];

        for(int i = 0; i < arr.length; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(arr);

        int firstIdx = 0;
        int lastIdx = N - 1;
        int sum = 2000000000;
        int a = firstIdx;
        int b = lastIdx;

        while(firstIdx < lastIdx) {
            int cur = arr[firstIdx] + arr[lastIdx];

            if(sum > Math.abs(cur)) {
                a = firstIdx;
                b = lastIdx;
                sum = Math.abs(cur);
            }

            if(cur < 0) firstIdx++;
            else lastIdx--;
        }

        System.out.println(arr[a] + " " + arr[b]);
    }
}
