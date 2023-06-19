package algorithm.BOJ.Java;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class Main_1931_회의실_배정 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        int N = Integer.parseInt(br.readLine());
        int answer = 0;
        int lastTime = -1;

        int[][] arr = new int[N][2];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            arr[i][0] = Integer.parseInt(st.nextToken());
            arr[i][1] = Integer.parseInt(st.nextToken());

        }

        // 끝나는 시간, 끝나는 시간이 같을 경우 시작 시간 기준으로 정렬
        Arrays.sort(arr, (a, b) -> {
            if(a[1] != b[1]) return a[1] - b[1];
            else return a[0] - b[0];
        });

        for(int i = 0; i < N; i++) {
            if(lastTime <= arr[i][0]) { // 한 회의가 끝남과 동시에 다음 회의가 시작할 수 있음
                lastTime = arr[i][1];
                answer++;
            }
        }

        System.out.println(answer);

    }
}
