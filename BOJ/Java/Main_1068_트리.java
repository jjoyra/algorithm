package algorithm.BOJ.Java;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main_1068_트리 {
    static int N, root, remove, answer;
    static List<Integer>[] tree;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        tree = new List[N];

        for(int i = 0; i < N; i++) {
            tree[i] = new ArrayList<>();
        }

        StringTokenizer st = new StringTokenizer(br.readLine());

        for(int i = 0; i < N; i++) {
            int no = Integer.parseInt(st.nextToken());
            if(no == - 1) {
                root = i;
            } else {
                tree[no].add(i);
            }
        }

        remove = Integer.parseInt(br.readLine());

        bfs();

        System.out.println(answer);
    }

    static void bfs() {
        Queue<Integer> queue = new ArrayDeque<>();
        queue.offer(root);

        while(!queue.isEmpty()) {
            int node = queue.poll();

            int child = tree[node].size();
            if(node == remove) continue;
            else if(child == 0) {
                answer++;
            }

            for(int i = 0; i < child; i++) {
                int next = tree[node].get(i);
                queue.offer(next);

                if(child == 1 && next == remove) {
                    answer++;
                }
            }

        }
    }
}
