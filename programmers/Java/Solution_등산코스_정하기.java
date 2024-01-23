package algorithm.programmers.Java;

import java.util.*;

class Solution_등산코스_정하기 {
    static class Edge implements Comparable<Edge> {
        int node, weight;

        Edge(int node, int weight) {
            this.node = node;
            this.weight = weight;
        }

        @Override
        public int compareTo(Edge o) {
            return this.weight - o.weight;
        }
    }
    static List<Edge>[] list;
    static int[] intensity;
    static Set<Integer> set;
    public int[] solution(int n, int[][] paths, int[] gates, int[] summits) {
        int[] answer = new int[2];

        list = new List[n + 1];
        intensity = new int[n + 1];
        Arrays.fill(intensity, 10000001);

        for(int i = 1; i < n + 1; i++) {
            list[i] = new ArrayList<>();
        }

        for(int[] path : paths) {
            list[path[0]].add(new Edge(path[1], path[2]));
            list[path[1]].add(new Edge(path[0], path[2]));
        }

        set = new HashSet<>();

        for(int summit : summits) {
            set.add(summit);
        }

        dijkstra(gates, summits);

        answer[1] = 10000001;

        Arrays.sort(summits);

        for(int summit : summits) {
            if(answer[1] > intensity[summit]) {
                answer[1] = intensity[summit];
                answer[0] = summit;
            }
        }

        return answer;
    }

    static void dijkstra(int[] gates, int[] summits) {
        PriorityQueue<Edge> pq = new PriorityQueue<>();

        for(int gate : gates) {
            pq.offer(new Edge(gate, 0));
            intensity[gate] = 0;
        }

        while(!pq.isEmpty()) {
            Edge edge = pq.poll();
            if(intensity[edge.node] < edge.weight || set.contains(edge.node)) continue;

            for(Edge next : list[edge.node]) {
                int tmp = Math.max(next.weight, intensity[edge.node]);
                if(intensity[next.node] > tmp) {
                    intensity[next.node] = tmp;
                    pq.offer(new Edge(next.node, tmp));
                }
            }
        }
    }
}