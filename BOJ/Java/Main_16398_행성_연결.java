package algorithm.BOJ.Java;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main_16398_행성_연결 {
    static class Vertex implements Comparable<Vertex> {
        int node;
        long weight;

        Vertex(int node, long weight) {
            this.node = node;
            this.weight = weight;
        }

        @Override
        public int compareTo(Vertex o) {
            return Long.compare(this.weight, o.weight);
        }
    }

    static int[][] map;
    static boolean[] visited;
    static long[] minEdge;
    static int N;
    static long answer;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        map = new int[N + 1][N + 1];
        visited = new boolean[N + 1];
        minEdge = new long[N + 1];

        Arrays.fill(minEdge, Integer.MAX_VALUE);

        for(int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j = 0; j < N; j++) {
                map[i + 1][j + 1] = Integer.parseInt(st.nextToken());
            }
        }

        prim();

        System.out.println(answer);
    }

    static void prim() {
        PriorityQueue<Vertex> pq = new PriorityQueue<>();

        pq.offer(new Vertex(1, 0));
        minEdge[1] = 0;

        while(!pq.isEmpty()) {
            Vertex v = pq.poll();

            if(visited[v.node]) continue;
            visited[v.node] = true;
            answer += v.weight;

            for(int i = 1; i < N + 1; i++) {
                if(!visited[i] && v.node != i && minEdge[i] > map[v.node][i]) {
                    minEdge[i] = map[v.node][i];
                    pq.offer(new Vertex(i, minEdge[i]));
                }
            }
        }
    }


}
