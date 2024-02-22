package algorithm.programmers.Java;

import java.util.*;

class Solution_순위 {
    public int solution(int n, int[][] results) {
        int answer = 0;

        int[][] distance = new int[n + 1][n + 1];

        for(int i = 0; i < n + 1; i++) {
            Arrays.fill(distance[i], n + 1);
        }

        for(int[] result : results) {
            distance[result[0]][result[1]] = 1;
        }

        for(int k = 1; k < n + 1; k++) {
            for(int i = 1; i < n + 1; i++) {
                for(int j = 1; j < n + 1; j++) {
                    distance[i][j] = Math.min(distance[i][j], distance[i][k] + distance[k][j]);
                }
            }
        }

        int[] front = new int[n + 1];
        int[] back = new int[n + 1];

        for(int i = 1; i < n + 1; i++) {
            for(int j = 1; j < n + 1; j++) {
                if(distance[i][j] != n + 1) {
                    front[j]++;
                    back[i]++;
                }
            }
        }

        for(int i = 1; i < n + 1; i++) {
            if(front[i] + back[i] == n - 1) {
                answer++;
            }
        }

        return answer;
    }
}