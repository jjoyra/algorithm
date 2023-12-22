package algorithm.BOJ.Java;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_2636_치즈 {
    static int[] dr = {-1, 0, 1, 0};
    static int[] dc = {0, 1, 0, -1};
    static int r, c;
    static Queue<int[]> queue, queue2;
    static boolean[][] visited;
    static int[][] map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());

        map = new int[r][c];

        for(int i = 0; i < r; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < c; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        queue = new ArrayDeque<>();
        queue2 = new ArrayDeque<>();
        visited = new boolean[r][c];
        int time = 0;
        int cnt = 0;

        queue.offer(new int[]{0, 0});
        visited[0][0] = true;

        while(true) {
            while(!queue.isEmpty()) {
                int[] tmp = queue.poll();
                bfs(tmp[0], tmp[1]);
            }

            if(queue2.isEmpty()) break;

            cnt = queue2.size();

            for(int i = 0; i < cnt; i++) {
                int[] tmp = queue2.poll();
                bfs(tmp[0], tmp[1]);
            }

            time++;
        }

        System.out.println(time);
        System.out.println(cnt);

    }

    static void bfs(int cr, int cc) {
        for(int i = 0; i < 4; i++) {
            int nr = cr + dr[i];
            int nc = cc + dc[i];

            if(nr < 0 || nc < 0 || nr >= r || nc >= c || visited[nr][nc])
                continue;

            if(map[nr][nc] == 0) queue.offer(new int[]{nr, nc});
            else queue2.offer(new int[]{nr, nc});
            visited[nr][nc] = true;
        }
    }

}
