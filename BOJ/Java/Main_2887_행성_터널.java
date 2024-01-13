package algorithm.BOJ.Java;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main_2887_행성_터널 {
    static class Edge {
        int a, b, weight;

        Edge(int a, int b, int weight) {
            this.a = a;
            this.b = b;
            this.weight = weight;
        }
    }
    static int N;
    static List<int[]> list;
    static int[] parent;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        list = new ArrayList<>();
        parent = new int[N];

        for(int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            list.add(new int[] {a, b, c, i});
            parent[i] = i;
        }

        PriorityQueue<Edge> queue = new PriorityQueue<>(Comparator.comparingInt(e -> e.weight));

        for(int i = 0; i < 3; i++) {
            int finalI = i;
            Collections.sort(list, Comparator.comparingInt(l -> l[finalI]));

            for(int j = 1; j < N; j++) {
                int a = list.get(j - 1)[3];
                int b = list.get(j)[3];
                int sum = Math.abs(list.get(j - 1)[i] - list.get(j)[i]);
                queue.add(new Edge(a, b, sum));
            }
        }

        int cnt = 0;
        int sum = 0;

        while(cnt < N - 1) {
            Edge edge = queue.poll();

            if(!union(edge.a, edge.b)) continue;
            sum += edge.weight;
            cnt++;
        }

        System.out.println(sum);

    }

    static int find(int node) {
        if(parent[node] == node) return node;
        return parent[node] = find(parent[node]);
    }

    static boolean union(int a, int b) {
        int parentA = find(a);
        int parentB = find(b);

        if(parentA == parentB) return false;

        parent[parentA] = parentB;
        return true;
    }
}
