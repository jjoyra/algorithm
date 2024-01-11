package algorithm.BOJ.Java;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main_1005_ACM_Craft {
    static int N, K, W;
    static int[] time, inEdge, dp;
    static List<Integer>[] list;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        for(int t = 0; t < T; t++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken()) + 1;
            K = Integer.parseInt(st.nextToken());
            time = new int[N];
            inEdge = new int[N];
            dp = new int[N];
            list = new List[N];

            st = new StringTokenizer(br.readLine());

            for(int i = 1; i < N ; i++) {
                time[i] = Integer.parseInt(st.nextToken());
                list[i] = new ArrayList<>();
            }

            for(int i = 0; i < K; i++) {
                st = new StringTokenizer(br.readLine());
                int X = Integer.parseInt(st.nextToken());
                int Y = Integer.parseInt(st.nextToken());

                list[X].add(Y);
                inEdge[Y]++;
            }

            W = Integer.parseInt(br.readLine());

            Queue<Integer> queue = new ArrayDeque<>();
            boolean flag = false;

            while(!flag) {
                for(int i = 1; i < N; i++){
                    if(inEdge[i] == 0) {
                        queue.offer(i);
                        inEdge[i]--;
                    }
                }

                int size = queue.size();

                for(int i = 0; i < size; i++) {
                    int node = queue.poll();
                    dp[node] += time[node];

                    if(node == W) {
                        flag = true;
                        break;
                    }

                    for(int j = 0; j < list[node].size(); j++) {
                        inEdge[list[node].get(j)]--;
                        dp[list[node].get(j)] = Math.max(dp[list[node].get(j)], dp[node]);
                    }

                }
            }

            System.out.println(dp[W]);
        }
    }
}
