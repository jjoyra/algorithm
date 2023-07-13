package algorithm.BOJ.Java;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_1697_숨바꼭질 {
    static int N, K, answer;
    static boolean[] visited;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        visited = new boolean[100001];

        bfs();

        System.out.println(answer);

    }

    static void bfs() {
        Queue<Integer> queue = new ArrayDeque<>();
        queue.offer(N);
        visited[N] = true;

        int time = 0;

        while(!queue.isEmpty()) {
            int size = queue.size();
            while(size-- > 0) {
                int tmp = queue.poll();

                if(tmp == K) {
                    answer = time;
                    return;
                }

                if(tmp - 1 >= 0 && tmp - 1 <= 100000 && !visited[tmp - 1]) {
                    queue.offer(tmp - 1);
                    visited[tmp - 1] = true;
                }

                if(tmp + 1 >= 0 && tmp + 1 <= 100000 && !visited[tmp + 1]) {
                    queue.offer(tmp + 1);
                    visited[tmp + 1] = true;
                }

                if(tmp * 2 >= 0 && tmp * 2 <= 100000 && !visited[tmp * 2]) {
                    queue.offer(tmp * 2);
                    visited[tmp * 2] = true;
                }

            }
                time++;
        }
    }
}
