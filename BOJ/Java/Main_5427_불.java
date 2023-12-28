package algorithm.BOJ.Java;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_5427_불 {
    static int w, h;
    static int[][] map;
    static boolean[][] visited;
    static Queue<int[]> fire, queue;
    static int[] dr = {-1, 0, 1, 0};
    static int[] dc = {0, 1, 0, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int tc = Integer.parseInt(br.readLine());

        for(int t = 0; t < tc; t++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            w = Integer.parseInt(st.nextToken());
            h = Integer.parseInt(st.nextToken());

            map = new int[h][w];
            visited = new boolean[h][w];
            queue = new ArrayDeque<>();
            fire = new ArrayDeque<>();

            for(int i = 0; i < h; i++) {
                String string = br.readLine();
                for(int j = 0; j < w; j++) {
                    map[i][j] = string.charAt(j);
                    if(map[i][j] == '@') {
                        queue.offer(new int[] {i, j});
                        visited[i][j] = true;
                        map[i][j] = '.';
                    } else if(map[i][j] == '*') {
                        fire.offer(new int[] {i, j});
                    }
                }
            }

            int level = 0;
            String answer = "IMPOSSIBLE";

            while(!queue.isEmpty() && answer.equals("IMPOSSIBLE")) {
                level++;

                int fireSize = fire.size();

                for (int i = 0; i < fireSize; i++) {
                    int[] tmp = fire.poll();
                    for(int j = 0; j < 4; j++) {
                        int nr = tmp[0] + dr[j];
                        int nc = tmp[1] + dc[j];

                        if(nr < 0 || nc < 0 || nr >= h || nc >= w) continue;
                        if(map[nr][nc] == '.') {
                            map[nr][nc] = '*';
                            fire.offer(new int[] {nr, nc});
                        }
                    }
                }

                int size = queue.size();

                for (int i = 0; i < size; i++) {
                    int[] tmp = queue.poll();
                    for(int j = 0; j < 4; j++) {
                        int nr = tmp[0] + dr[j];
                        int nc = tmp[1] + dc[j];

                        if(nr < 0 || nc < 0 || nr >= h || nc >= w) {
                            answer = level + "";
                            break;
                        }
                        if(!visited[nr][nc] && map[nr][nc] == '.') {
                            visited[nr][nc] = true;
                            queue.offer(new int[] {nr, nc});
                        }
                    }
                }
            }

            System.out.println(answer);
        }
    }
}
