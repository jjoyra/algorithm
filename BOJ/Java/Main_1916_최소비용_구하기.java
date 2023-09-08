package algorithm.BOJ.Java;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main_1916_최소비용_구하기 {
    static class Edge {
        int no, weight;

        public Edge(int no, int weight) {
            this.no = no;
            this.weight = weight;
        }
    }
    static int N, M, visited[];
    static List<Edge>[] list;

    public static void main(String[] args) throws IOException {
        BufferedReader br =new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());

        list = new List[N + 1];
        visited = new int[N + 1];

        // 버스 비용이 최대 100000이고, 도시의 개수가 최대 1000이므로
        // INF는 100000 * 1000 이상이여야 한다.
        Arrays.fill(visited, Integer.MAX_VALUE);

        for(int i = 1; i <= N; i++) {
            list[i] = new ArrayList<>();
        }

        for(int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());

            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());

            list[a].add(new Edge(b, w));
        }

        st = new StringTokenizer(br.readLine());
        int start = Integer.parseInt(st.nextToken());
        int end = Integer.parseInt(st.nextToken());

        dijkstra(start);

        System.out.println(visited[end]);

    }

    static void dijkstra(int start) {
        PriorityQueue<Edge> pq = new PriorityQueue<>(Comparator.comparing(a -> a.weight));
        pq.offer(new Edge(start, 0));
        visited[start] = 0;

        while(!pq.isEmpty()) {
            Edge tmp = pq.poll();

            if(tmp.weight > visited[tmp.no]) continue;

            for(Edge edge : list[tmp.no]) {
                if(visited[edge.no] > visited[tmp.no] + edge.weight) {
                    visited[edge.no] = visited[tmp.no] + edge.weight;
                    pq.offer(new Edge(edge.no, visited[edge.no]));
                }
            }
        }

    }
}
