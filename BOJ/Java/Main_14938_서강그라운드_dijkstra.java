package algorithm.BOJ.Java;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main_14938_서강그라운드_dijkstra {
    static class Node {
        int no, weight;

        public Node(int no, int weight) {
            this.no = no;
            this.weight = weight;
        }
    }
    static int N, M, R, tem[], distance[], answer;
    static List<Node>[] list;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());

        list = new List[N];

        for(int i = 0; i < N; i++) {
            list[i] = new ArrayList<>();
        }

        tem = new int[N];

        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++) {
            tem[i] = Integer.parseInt(st.nextToken());
        }

        for(int i = 0; i < R; i++) {
            st = new StringTokenizer(br.readLine());

            int a = Integer.parseInt(st.nextToken()) - 1;
            int b = Integer.parseInt(st.nextToken()) - 1;
            int w = Integer.parseInt(st.nextToken());

            list[a].add(new Node(b, w));
            list[b].add(new Node(a, w));
        }

        for(int i = 0; i < N; i++) {
            dijkstra(i);
            int sum = 0;
            for(int j = 0; j < N; j++) {
                if(distance[j] <= M) sum+= tem[j];
            }
            answer = Math.max(answer, sum);
        }

        System.out.println(answer);

    }

    static void dijkstra(int num) {
        PriorityQueue<Node> pq = new PriorityQueue<>((a, b) -> a.weight - b.weight);
        distance = new int[N];
        Arrays.fill(distance, 15 * N);
        distance[num] = 0;
        pq.offer(new Node(num, 0));

        while(!pq.isEmpty()) {
            Node node = pq.poll();
            for(Node tmp : list[node.no]) {
                if(distance[tmp.no] > distance[node.no] + tmp.weight) {
                    distance[tmp.no] = distance[node.no] + tmp.weight;
                    pq.offer(tmp);
                }
            }
        }
    }
}
