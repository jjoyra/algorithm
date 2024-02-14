package algorithm.programmers.Java;

import java.util.*;

class Solution_미로_탈출 {
    static class Box {
        int r, c, lever;

        Box(int r, int c, int lever) {
            this.r = r;
            this.c = c;
            this.lever = lever;
        }
    }
    static int N, M, answer;
    static int[] start, lever, end;
    static int[][] map, visited;
    static int[] dr = {-1, 0, 1, 0};
    static int[] dc = {0, 1, 0, -1};
    public int solution(String[] maps) {

        N = maps.length;
        M = maps[0].length();
        visited = new int[N][M];
        map = new int[N][M];

        for(int i = 0; i < N; i++) {
            Arrays.fill(visited[i], - 1);
        }

        for(int i = 0; i < N; i++) {
            for(int j = 0; j < M; j++) {
                map[i][j] = maps[i].charAt(j);

                if(map[i][j] == 'S') {
                    start = new int[] {i, j};
                } else if(map[i][j] == 'E') {
                    end = new int[] {i, j};
                } else if(map[i][j] == 'L') {
                    lever = new int[] {i, j};
                }
            }
        }

        bfs();

        return answer;
    }

    static void bfs() {
        Queue<Box> queue = new ArrayDeque<>();
        queue.offer(new Box(start[0], start[1], 0));
        visited[start[0]][start[1]] = 0;
        int level = 1;

        while(!queue.isEmpty()) {
            int size = queue.size();

            while(size-- > 0) {
                Box box = queue.poll();

                for(int i = 0; i < 4; i++) {
                    int nr = box.r + dr[i];
                    int nc = box.c + dc[i];

                    if(nr < 0 || nc < 0 || nr >= N || nc >= M || map[nr][nc] == 'X'
                            || visited[nr][nc] >= box.lever) continue;

                    if(nr == lever[0] && nc == lever[1]) {
                        visited[nr][nc] = 1;
                        queue.offer(new Box(nr, nc, 1));
                    } else if(box.lever == 1 && nr == end[0] && nc == end[1]) {
                        answer = level;
                        return;
                    } else {
                        visited[nr][nc] = box.lever;
                        queue.offer(new Box(nr, nc, box.lever));
                    }
                }

            }

            level++;
        }

        answer = -1;
    }
}
