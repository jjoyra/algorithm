package algorithm.programmers.Java;

import java.util.*;

class Solution_합승_택시_요금 {
    static class Node implements Comparable<Node> {
        int no, weight;

        Node(int no, int weight) {
            this.no = no;
            this.weight = weight;
        }

        @Override
        public int compareTo(Node node) {
            return this.weight - node.weight;
        }
    }
    static List<Node>[] adj;
    static int[] sDis, aDis, bDis;
    static int MAX_VALUE = 20000001;
    public int solution(int n, int s, int a, int b, int[][] fares) {
        int answer = MAX_VALUE;

        sDis = new int[n + 1];
        aDis = new int[n + 1];
        bDis = new int[n + 1];

        Arrays.fill(sDis, MAX_VALUE);
        Arrays.fill(aDis, MAX_VALUE);
        Arrays.fill(bDis, MAX_VALUE);

        adj = new List[n + 1];

        for(int i = 0; i < n + 1; i++) {
            adj[i] = new ArrayList<>();
        }

        for(int[] fare : fares) {
            adj[fare[0]].add(new Node(fare[1], fare[2]));
            adj[fare[1]].add(new Node(fare[0], fare[2]));
        }

        dijkstra(s, sDis);
        dijkstra(a, aDis);
        dijkstra(b, bDis);

        for(int i = 1; i < n + 1; i++) {
            answer = Math.min(answer, sDis[i] + aDis[i] + bDis[i]);
        }

        return answer;
    }

    static void dijkstra(int start, int[] distance) {
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.offer(new Node(start, 0));
        distance[start] = 0;

        while(!pq.isEmpty()) {
            Node node = pq.poll();
            if(node.weight > distance[node.no]) continue;

            for(Node next : adj[node.no]) {
                if(distance[next.no] > distance[node.no] + next.weight) {
                    distance[next.no] = distance[node.no] + next.weight;
                    pq.offer(new Node(next.no, distance[next.no]));
                }
            }
        }
    }
}