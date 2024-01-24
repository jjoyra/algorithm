package algorithm.BOJ.Java;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main_9328_열쇠 {
    static int h, w, answer, key;
    static int[][] map;
    static int[][] visited;
    static int[] dr = {-1, 0, 1, 0};
    static int[] dc = {0, 1, 0, -1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();

        for(int t = 0; t < T; t++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            h = Integer.parseInt(st.nextToken());
            w = Integer.parseInt(st.nextToken());
            List<int[]> list = new ArrayList<>();
            key = 0;
            answer = 0;

            map = new int[h][w];
            visited = new int[h][w];
            for(int i = 0; i < h; i++) {
                Arrays.fill(visited[i], -1);
            }
            for(int i = 0; i < h; i++) {
                String string = br.readLine();
                for(int j = 0; j < w; j++) {
                    map[i][j] = string.charAt(j);
                    if((i == 0 || j == 0 || i == h - 1 || j == w - 1) && map[i][j] != '*') { // 테두리에 있는 벽이 아닌 좌표를 리스트에 저장
                        list.add(new int[]{i, j});
                        visited[i][j] = key;
                    }
                }
            }

            String string = br.readLine();
            if(string.charAt(0) != '0') {
                for(int i = 0; i < string.length(); i++) {
                    key |= (1 << string.charAt(i) - 'a');
                }
            }

            while(true) { // 건물 밖으로 이동해서 다시 들어올 수 있기 때문에 더 방문할 수 있는 곳이 있는지 확인해봐야 함
                int preKey = key;
                bfs(list);
                if(preKey == key) break;
            }

            sb.append(t == T - 1 ? answer : answer + "\n");
        }
        System.out.println(sb);
    }

    static void bfs(List<int[]> list) {
        Queue<int[]> queue = new ArrayDeque<>();
        for(int[] rc: list) {
            queue.offer(rc);
        }
        while(!queue.isEmpty()) {
            int[] tmp = queue.poll();

            if(map[tmp[0]][tmp[1]] >= 'A' && map[tmp[0]][tmp[1]] <= 'Z') { // 테두리에서(건물 안에서) 시작하기 때문에 시작점이 문, 문서, 열쇠인지 확인 필요
                if((key & (1 << map[tmp[0]][tmp[1]] - 'A')) == 0) {
                    continue;
                }
            }else if(map[tmp[0]][tmp[1]] == '$') {
                answer++;
                map[tmp[0]][tmp[1]] = '.';
            } else if(map[tmp[0]][tmp[1]] >= 'a' && map[tmp[0]][tmp[1]] <= 'z') {
                key |= (1 << map[tmp[0]][tmp[1]] - 'a');
            }

            for(int i = 0; i < 4; i++) {
                int nr = tmp[0] + dr[i];
                int nc = tmp[1] + dc[i];
                if(nr < 0 || nc < 0 || nr >= h || nc >= w
                        || map[nr][nc] == '*' || visited[nr][nc] >= key) continue;

                visited[nr][nc] = key;
                queue.offer(new int[] {nr, nc});
            }
        }
    }
}

//public class Main_9328_열쇠 {
//    static int h, w, answer, key;
//    static int[][] map;
//    static int[][] visited;
//    static int[] dr = {-1, 0, 1, 0};
//    static int[] dc = {0, 1, 0, -1};
//    public static void main(String[] args) throws IOException {
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        int T = Integer.parseInt(br.readLine());
//        StringBuilder sb = new StringBuilder();
//
//        for(int t = 0; t < T; t++) {
//            StringTokenizer st = new StringTokenizer(br.readLine());
//            h = Integer.parseInt(st.nextToken());
//            w = Integer.parseInt(st.nextToken());
//            key = 0;
//            answer = 0;
//             // 건물 밖으로 상하좌우 1칸씩 넓힘
//             // while문으로 반복해서 bfs() 호출할 필요없음
//            map = new int[h + 2][w + 2];
//            visited = new int[h + 2][w + 2];
//            for(int i = 0; i < h + 2; i++) {
//                Arrays.fill(visited[i], -1);
//            }
//            for(int i = 0; i <= h + 1; i++) {
//                map[i][0] = '.';
//                map[i][w + 1] = '.';
//
//            }
//
//            for(int i = 1; i <= w + 1; i++) {
//                map[0][i] = '.';
//                map[h + 1][i] = '.';
//            }
//
//            for(int i = 1; i <= h; i++) {
//                String string = br.readLine();
//                for(int j = 1; j <= w; j++) {
//                    map[i][j] = string.charAt(j - 1);
//                }
//            }
//
//            String string = br.readLine();
//            if(string.charAt(0) != '0') {
//                for(int i = 0; i < string.length(); i++) {
//                    key |= (1 << string.charAt(i) - 'a');
//                }
//            }
//
//            bfs();
//
//            sb.append(t == T - 1 ? answer : answer + "\n");
//        }
//        System.out.println(sb);
//    }
//
//    static void bfs() {
//        Queue<int[]> queue = new ArrayDeque<>();
//        queue.offer(new int[]{0, 0});
//        visited[0][0] = key;
//
//        while(!queue.isEmpty()) {
//            int[] tmp = queue.poll();
//
//            for(int i = 0; i < 4; i++) {
//                int nr = tmp[0] + dr[i];
//                int nc = tmp[1] + dc[i];
//                if(nr < 0 || nc < 0 || nr >= h + 2 || nc >= w + 2
//                        || map[nr][nc] == '*' || visited[nr][nc] >= key) continue;
//                if(map[nr][nc] >= 'A' && map[nr][nc] <= 'Z') {
//                    if((key & (1 << map[nr][nc] - 'A')) == 0) {
//                        visited[nr][nc] = key;
//                        continue;
//                    }
//                } else if(map[nr][nc] == '$') {
//                    answer++;
//                    map[nr][nc] = '.';
//                } else if(map[nr][nc] >= 'a' && map[nr][nc] <= 'z') {
//                    key |= (1 << map[nr][nc] - 'a');
//                }
//                visited[nr][nc] = key;
//                queue.offer(new int[] {nr, nc});
//            }
//        }
//    }
//}
