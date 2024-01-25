package algorithm.BOJ.Java;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main_10282_해킹 {
    static class Node implements Comparable<Node> {
        int no, weight;

        Node(int node, int weight) {
            this.no = node;
            this.weight = weight;
        }

        @Override
        public int compareTo(Node node) {
            return this.weight - node.weight;
        }
    }
    static int N;
    static int[] distance;
    static List<Node>[] list;
    static int MAX_NUM = 10000001;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();

        for(int t = 0; t < T; t++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken()) + 1;
            int D = Integer.parseInt(st.nextToken());
            int C = Integer.parseInt(st.nextToken());

            list = new List[N];
            for(int i = 0; i < N; i++) {
                list[i] = new ArrayList<>();
            }
            distance = new int[N];
            Arrays.fill(distance, MAX_NUM);

            for(int i = 0; i < D; i++) {
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                int s = Integer.parseInt(st.nextToken());

                list[b].add(new Node(a, s));
            }

            dijkstra(C);

            int cnt = 0;
            int time = 0;

            for(int i = 1; i < N; i++) {
                if(distance[i] != MAX_NUM) {
                    if(time < distance[i]) {
                        time = distance[i];
                    }
                    cnt++;
                }
            }

            sb.append(cnt);
            sb.append(" ");
            sb.append(time);
            sb.append("\n");
        }

        System.out.println(sb);
    }

    static void dijkstra(int num) {
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.offer(new Node(num, 0));
        distance[num] = 0;

        while(!pq.isEmpty()) {
            Node node = pq.poll();
            if(node.weight > distance[node.no]) continue;

            for(Node next : list[node.no]) {
                if(distance[next.no] > distance[node.no] + next.weight) {
                    distance[next.no] = distance[node.no] + next.weight;
                    pq.offer(new Node(next.no, distance[next.no]));
                }
            }
        }
    }
}
