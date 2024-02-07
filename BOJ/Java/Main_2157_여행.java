package algorithm.BOJ.Java;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main_2157_여행 {
    static class Node {
        int no, weight;

        Node(int no, int weight) {
            this.no = no;
            this.weight = weight;
        }
    }
    static int N, M, K, answer;
    static int[][] dp;
    static List<Node>[] list;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        list = new List[N + 1];

        for(int i = 0; i < N + 1; i++) {
            list[i] = new ArrayList<>();
        }

        for(int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            if(a > b) continue;

            list[a].add(new Node(b, c));
        }

        dp = new int[M + 1][N + 1]; // 거쳐온 도시 개수 별로 해당 도시에서의 최댓값

        for(Node node : list[1]) { // 1번에서 갈 수 있는 초깃값 셋팅, 같은 도시와 연결된 여러 항로가 있을 수 있으므로 최댓값 검사 필요
            dp[2][node.no] = Math.max(dp[2][node.no], node.weight);
        }

        for(int i = 2; i < N + 1; i++) { // 2번부터 N번까지 순서대로 이동할 수 있는 도시 탐색
            for(Node node : list[i]) {
                for(int j = 3; j < M + 1; j++) { // 해당 도시에 도착할 때까지 들른 도시 개수 별로 dp 배열 업데이트
                    if(dp[j - 1][i] == 0) continue; // 값이 0일 경우 해당 개수의 도시를 들러서 해당 도시에 들를 수 없다는 뜻이므로 건너뜀
                    dp[j][node.no] = Math.max(dp[j][node.no], dp[j - 1][i] + node.weight);
                    // j번 걸려서 다음 도시로 도착했을 때 기내식의 점수 = j - 1번 걸려서 해당 도시까지 먹은 기내식 점수 + 해당 도시에서 다음 도시로 이동할 때 먹은 기내식 점수
                }
            }
        }

        for(int i = 1; i < M + 1; i++) {
            answer = Math.max(answer, dp[i][N]);
        }

        System.out.println(answer);
    }
}
