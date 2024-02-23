package algorithm.programmers.Java;

import java.util.*;

class Solution_광물_캐기 {
    public int solution(int[] picks, String[] minerals) {
        int answer = 0;

        int length = minerals.length % 5 != 0 ? minerals.length + 1 : minerals.length;
        length = Math.min(length, picks[0] + picks[1] + picks[2]);

        int[][] map = new int[length][3];

        for(int i = 0; i < length; i++) {
            for(int j = 0; j < 5; j++) {
                if(i * 5 + j >= minerals.length) continue;
                String mineral = minerals[i * 5 + j];
                if(mineral.equals("diamond")) map[i][0]++;
                else if(mineral.equals("iron")) map[i][1]++;
                else map[i][2]++;
            }
        }

        Arrays.sort(map, (a, b)
                -> {
            if(a[0] != b[0]) {
                return b[0] - a[0];
            } else if(a[1] != b[1]) {
                return b[1] - a[1];
            } else {
                return b[2] - a[2];
            }
        });

        for(int[] mineral : map) {
            if(picks[0] != 0) {
                picks[0]--;
                answer += mineral[0] + mineral[1] + mineral[2];
            } else if(picks[1] != 0) {
                picks[1]--;
                answer += mineral[0] * 5 + mineral[1] + mineral[2];
            } else if(picks[2] != 0) {
                picks[2]--;
                answer += mineral[0] * 25 + mineral[1] * 5 + mineral[2];
            }
        }
        return answer;
    }
}