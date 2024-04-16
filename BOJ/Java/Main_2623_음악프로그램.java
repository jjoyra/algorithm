package algorithm.BOJ.Java;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main_2623_음악프로그램 {
    static int N, M;
    static List<Integer>[] list;
    static int[] inputEdge, answer;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        list = new List[N + 1];
        inputEdge = new int[N + 1];
        answer = new int[N];

        for(int i = 0; i < N + 1; i++) {
            list[i] = new ArrayList<>();
        }

        for(int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int num = Integer.parseInt(st.nextToken());
            int front = Integer.parseInt(st.nextToken());
            for(int j = 0; j < num - 1; j++) {
                int tmp = Integer.parseInt(st.nextToken());
                list[front].add(tmp);
                inputEdge[tmp]++;
                front = tmp;
            }
        }

        Queue<Integer> queue = new ArrayDeque<>();
        int idx = 0;
        for(int i = 1; i < N + 1; i++) {
            if(inputEdge[i] == 0) {
                queue.offer(i);
                inputEdge[i]--;
            }
        }

        while(!queue.isEmpty()) {
            int tmp = queue.poll();
            answer[idx++] = tmp;

            for(int num : list[tmp]) {
                if(--inputEdge[num] == 0) {
                    queue.offer(num);
                    inputEdge[num]--;
                }
            }
        }

        if(answer[N - 1] == 0) {
            System.out.println(0);
        } else {
            for(int i : answer) {
                System.out.println(i);
            }
        }
    }
}
