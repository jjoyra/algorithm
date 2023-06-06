package algorithm.BOJ.Java;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_9205_맥주_마시면서_걸어가기 {
    static int N;
    static int[][] map;
    static StringBuilder sb;
    static boolean flag;
    static boolean[] visited;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());

        for(int tc = 1; tc <= T; tc++) {
            N = Integer.parseInt(br.readLine());
            flag = false;
            map = new int[2 + N][2];
            visited = new boolean[N];

            for(int i = 0; i < 2 + N; i++) {
                st = new StringTokenizer(br.readLine());
                map[i][0] = Integer.parseInt(st.nextToken());
                map[i][1] = Integer.parseInt(st.nextToken());
            }

            dfs(0, 0);

            if(flag) sb.append("happy\n");
            else sb.append("sad\n");
        }

        System.out.println(sb);
    }

    static void dfs(int cnt, int idx) { // 현재 위치
        int dis = Math.abs(map[idx][0] - map[N + 1][0]) + Math.abs(map[idx][1] - map[N + 1][1]); // 현재 위치에서 페스티벌까지의 맨헤튼 거리
        if(dis <= 1000) flag = true; // 맨헤튼 거리가 1000(맨헤튼 거리 / 50 <= 20) 이하일 경우 flag true

        if(cnt == N) return; // 모든 편의점을 다 고려했으면 return
        if(flag) return; // 이미 flag가 ture면 return

        for(int i = 0; i < N; i++) { // 다음 편의점 선택
            if(visited[i]) continue; // 이미 방문한 편의점이면 continue
            dis = Math.abs(map[idx][0] - map[i + 1][0]) + Math.abs(map[idx][1] - map[i + 1][1]); // 현재 위치에서 다음 편의점까지의 맨헤튼 거리
            if(dis > 1000) continue; // 맨헤튼 거리가 1000을 초과할 경우 continue

            visited[i] = true; // 방문 체크
            dfs(cnt + 1, i + 1); // 해당 편의점을 현재 위치로 설정하여 재귀 함수 호출
        }

    }
}
