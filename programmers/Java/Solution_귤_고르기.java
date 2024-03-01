package algorithm.programmers.Java;

import java.util.*;

class Solution_귤_고르기 {
    public int solution(int k, int[] tangerine) {
        int answer = 0;

        Map<Integer, Integer> map = new HashMap<>();

        for(int num : tangerine) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }

        Integer[] cnts = map.values().toArray(new Integer[0]);
        Arrays.sort(cnts, Collections.reverseOrder());

        for(int cnt : cnts) {
            answer++;
            k -= cnt;

            if(k <= 0) {
                break;
            }
        }

        return answer;
    }
}