package algorithm.BOJ.Java;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main_20055_컨베이어_벨트_위의_로봇 {
    static int N, K, cnt;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        Deque<int[]> front = new ArrayDeque<>();
        Deque<int[]> back = new ArrayDeque<>();

        st = new StringTokenizer(br.readLine());

        for(int i = 0; i < N; i++) {
           front.offerFirst(new int[] {Integer.parseInt(st.nextToken()), 0});
        }
        for(int i = 0; i < N; i++) {
            back.offerFirst(new int[] {Integer.parseInt(st.nextToken()), 0});
        }

        int score = 1;

        while(true) {
            back.offer(new int[] {front.poll()[0], 0});
            front.offer(back.poll());

            int[] tmp = front.poll();
            tmp[1] = 0;

            for(int i = 1; i < N; i++) {
                int[] cur = front.poll();

                if(cur[1] == 1 && tmp[1] == 0 && moveRobot(tmp)) {
                    cur[1] = 0;
                }
                front.offer(tmp);
                tmp = cur;
            }

            moveRobot(tmp);
            front.offer(tmp);

            if(cnt >= K) {
                break;
            }

            score++;
        }

        System.out.println(score);
    }

    static boolean moveRobot(int[] tmp) {
        if(tmp[0] > 0) {
            tmp[0]--;
            tmp[1] = 1;

            if(tmp[0] == 0) {
                cnt++;
                tmp[0]--;
            }
            return true;
        }
        return false;
    }
}
