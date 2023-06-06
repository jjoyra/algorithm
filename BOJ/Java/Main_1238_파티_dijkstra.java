package algorithm.BOJ.Java;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main_1238_파티_dijkstra {
    static class Node {
        int no, w;

        public Node(int no, int w) {
            this.no = no;
            this.w = w;
        }
    }
    static int N, M, X, answer;
    static int INF = 100001;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        X = Integer.parseInt(st.nextToken()) - 1;

        // 인접 리스트
        List<Node>[] adjList = new List[N]; // 정방향
        List<Node>[] reAdjList = new List[N]; // 역방향

        for(int i = 0; i < N; i++) {
            adjList[i] = new ArrayList<>();
            reAdjList[i] = new ArrayList<>();
        }

        for(int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());

            int start = Integer.parseInt(st.nextToken()) - 1;
            int end = Integer.parseInt(st.nextToken()) - 1;
            int weight = Integer.parseInt(st.nextToken());

            adjList[start].add(new Node(end, weight));
            reAdjList[end].add(new Node(start,weight));

        }

        int[] distanceCome = dijkstra(reAdjList); // X까지 가는 최단거리
        int[] distanceBack = dijkstra(adjList); // X에서 돌아오는 최단거리

        for(int i = 0; i < N; i++) {
            answer = Math.max(answer, distanceCome[i] + distanceBack[i]);
        }

        System.out.println(answer);

    }

    static int[] dijkstra(List<Node>[] list) {
        int[] dis = new int[N];
        Arrays.fill(dis, INF);
        PriorityQueue<Node> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a.w));
        dis[X] = 0;
        pq.offer(new Node(X, 0));

        while(!pq.isEmpty()) {
            // 방문하지 않은 정점 중에 최단거리인 정점 선택
            Node node = pq.poll();
            if(node.w > dis[node.no]) continue;

            // 선택 정점의 인접 정점 갱신 & 갱신된 정점 큐에 삽입
            for(int i = 0; i < list[node.no].size(); i++) {
                Node tmp = list[node.no].get(i);
                if(dis[tmp.no] > dis[node.no] + tmp.w) {
                    dis[tmp.no] = dis[node.no] + tmp.w;
                    pq.offer(new Node(tmp.no, dis[tmp.no]));
                }
            }
        }
        return dis;
    }
}
