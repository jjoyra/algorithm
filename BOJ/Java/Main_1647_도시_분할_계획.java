package algorithm.BOJ.Java;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main_1647_도시_분할_계획 {
    static class Edge implements Comparable<Edge> {
        int a, b, weight;

        Edge(int a, int b, int weight) {
            this.a = a;
            this.b = b;
            this.weight = weight;
        }

        @Override
        public int compareTo(Edge edge) {
            return this.weight - edge.weight;
        }
    }
    static int N, M;
    static int[] parents;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        parents = new int[N + 1];

        for(int i = 0; i < N + 1; i++) {
            parents[i] = i;
        }

        PriorityQueue<Edge> pq = new PriorityQueue();

        for(int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            pq.offer(new Edge(a, b, c));
        }



        int cnt = 0;
        long answer = 0;
        while(!pq.isEmpty()) {
            if(cnt == N - 2) break;
            Edge edge = pq.poll();
            if(union(edge.a, edge.b)) {
                answer += edge.weight;
                cnt++;
            }
        }

        System.out.println(answer);
    }

    static int findParent(int node) {
        if(parents[node] == node) return node;
        return parents[node] = findParent(parents[node]);
    }

    static boolean union(int a, int b) {
        int aParent = findParent(a);
        int bParent = findParent(b);

        if(aParent == bParent) return false;
        else if(aParent < bParent) {
            parents[bParent] = aParent;
        } else {
            parents[aParent] = bParent;
        }
        return true;
    }
}
