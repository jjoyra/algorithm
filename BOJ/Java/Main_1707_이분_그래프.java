package algorithm.BOJ.Java;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Main_1707_이분_그래프 {
    static List<Integer>[] list;
    static int[] colorArr;
    static boolean flag;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        int K = Integer.parseInt(br.readLine());

        for(int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            int V = Integer.parseInt(st.nextToken());
            int E = Integer.parseInt(st.nextToken());

            list = new List[V + 1];
            flag = false;
            colorArr = new int[V + 1]; // 1과 2로 색상 표시

            Arrays.fill(colorArr, -1);

            for(int j = 1; j <= V; j++) {
                list[j] = new ArrayList();
            }

            for(int j = 0; j < E; j++) {
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());

                list[a].add(b);
                list[b].add(a);
            }

            for(int j = 1; j <= V; j++) {
                if(colorArr[j] == -1 && !flag) {
                    dfs(0, j);
                }
            }

            if(flag) System.out.println("NO");
            else System.out.println("YES");
        }
    }

    // 이분 그래프는 현재의 정점과 인접한 정점들을 모두 반대 색으로 칠해가는 과정으로 판별할 수 있다.
    // 만약 인접 정점이 이미 현재 정점과 같은 색을 갖고 있다면 그 그래프는 이분 그래프가 될 수 없다.
    // 비연결 그래프인 경우가 있을 수 있으므로 모든 그래프에 대해 탐색이 필요하다.

    static void dfs(int color, int cur) {
        if(flag) return;
        colorArr[cur] = color; // 현재 정점 색칠

        for(int i = 0; i < list[cur].size(); i++) {
            int tmp = list[cur].get(i);
            if(colorArr[tmp] == -1) { // 인접 정점이 아직 방문하지 않은 정점인 경우
                dfs((color + 1) % 2, tmp);
            } else if(colorArr[tmp] == color) { // 인접 정점이 현재 정점과 같은 색으로 칠해진 경우
                flag = true;
                return;
            }
        }
    }
}
