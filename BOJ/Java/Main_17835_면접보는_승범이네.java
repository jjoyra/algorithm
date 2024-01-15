package algorithm.BOJ.Java;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main_17835_면접보는_승범이네 {
    static class Edge{
        int node;
        long weight;

        Edge(int node, long weight) {
            this.node = node;
            this.weight = weight;
        }
    }

    static int N, M, K;
    static List<Edge>[] list;
    static long[] distance;
    static int[] interview;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken()) + 1;
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        list = new List[N];
        for(int i = 1; i < N; i++) {
            list[i] = new ArrayList<>();
        }

        distance = new long[N];
        Arrays.fill(distance, Long.MAX_VALUE);

        for(int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int U = Integer.parseInt(st.nextToken());
            int V = Integer.parseInt(st.nextToken());
            int C = Integer.parseInt(st.nextToken());

            list[V].add(new Edge(U, C));
        }

        st = new StringTokenizer(br.readLine());

        interview = new int[K];

        for(int i = 0; i < K; i++) {
            interview[i] = Integer.parseInt(st.nextToken());
        }

        dijkstra();

        int city = 0;
        long answer = -1;

        for(int i = 1; i < N; i++) {
            if(answer < distance[i]) {
                city = i;
                answer = distance[i];
            }
        }

        StringBuilder sb = new StringBuilder();
        sb.append(city);
        sb.append("\n");
        sb.append(answer);

        System.out.println(sb);
    }

    static void dijkstra() {
        PriorityQueue<Edge> pq = new PriorityQueue<>(Comparator.comparingLong(e -> e.weight));

        for(int no : interview) {
            distance[no] = 0;
            pq.offer(new Edge(no, 0));
        }

        while(!pq.isEmpty()) {
            Edge edge = pq.poll();
            if(edge.weight > distance[edge.node]) continue;
            for(Edge next : list[edge.node]) {
                if(distance[next.node] > distance[edge.node] + next.weight) {
                    distance[next.node] = distance[edge.node] + next.weight;
                    pq.offer(new Edge(next.node, distance[next.node]));
                }
            }
        }
    }
}
