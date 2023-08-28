package algorithm.softeer;

import java.util.*;
import java.io.*;

public class Main_자동차_테스트 {

    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int q = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());

        int[] arr = new int[n];

        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(arr);

        for (int i = 0; i < q; i++) {
            int tmp = Integer.parseInt(br.readLine());

            int search = Arrays.binarySearch(arr, tmp);

            if (search <= 0 || search == n - 1) {
                System.out.println(0);
            } else {
                System.out.println((n - search - 1) * search);
            }

        }

    }
}
