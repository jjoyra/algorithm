package algorithm.BOJ.Java;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main_1197_최소_스패닝_트리 {
    static class Edge implements Comparable<Edge> {
        int from, to, weight;

        public Edge(int from, int to, int weight) {
            this.from = from;
            this.to = to;
            this.weight = weight;
        }

        @Override
        public int compareTo(Edge o) {
            return this.weight - o.weight;
        }
    }
    static int V, E, parent[];
    static Edge[] edgeList;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());

        edgeList = new Edge[E];
        parent = new int[V + 1];

        for(int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());

            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            edgeList[i] = new Edge(a, b, c);
        }

        Arrays.sort(edgeList);

        set();

        int answer = 0;
        int cnt = 0;

        for(int i = 0; i < E; i++) {
            Edge edge = edgeList[i];
            if(union(edge.from, edge.to)) {
                answer += edge.weight;
                cnt++;
            }

            if(cnt == V - 1) break;
        }

        System.out.println(answer);
    }

    static void set() {
        for(int i = 1; i < V + 1; i++) {
            parent[i] = i;
        }
    }

    static int findSet(int v) {
        if(parent[v] == v) return v;
        return parent[v] = findSet(parent[v]);
    }

    static boolean union(int a, int b) {
        int parentA = findSet(a);
        int parentB = findSet(b);

        if(parentA == parentB) return false;

        parent[parentB] = parentA;
        return true;
    }
}
