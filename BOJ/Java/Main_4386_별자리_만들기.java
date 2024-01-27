package algorithm.BOJ.Java;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main_4386_별자리_만들기 {
    static class Node implements Comparable<Node> {
        int no;
        double weight;
        Node(int no, double weight) {
            this.no = no;
            this.weight = weight;
        }

        @Override
        public int compareTo(Node o) {
            if(this.weight > o.weight) {
                return 1;
            } else if(this.weight < o.weight) {
                return -1;
            } else {
                return 0;
            }
        }
    }
    static double[][] stars;
    static double[] distance;
    static int N;
    static double MAX_VALUE = 1001.0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        stars = new double[N][2];
        distance = new double[N];
        Arrays.fill(distance, MAX_VALUE);
        for(int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            stars[i][0] = Double.parseDouble(st.nextToken());
            stars[i][1] = Double.parseDouble(st.nextToken());
        }

        prim();

        double answer = 0;

        for(double num : distance) {
            answer += num;
        }

        System.out.println(answer);

    }

    static void prim() {
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.offer(new Node(0, 0));

        while(!pq.isEmpty()) {
            Node node = pq.poll();
            if(distance[node.no] != MAX_VALUE) continue;
            distance[node.no] = node.weight;

            for(int i = 1; i < N; i++) {
                if(node.no == i || distance[i] != MAX_VALUE) continue;

                double tmp = Math.sqrt(Math.pow(stars[node.no][0] - stars[i][0], 2)
                        + Math.pow(stars[node.no][1] - stars[i][1], 2));

                pq.offer(new Node(i, tmp));

            }
        }
    }
}
