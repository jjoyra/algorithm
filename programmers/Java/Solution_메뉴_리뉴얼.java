package algorithm.programmers.Java;

import java.util.*;

class Solution_메뉴_리뉴얼 {
    static char[] visited;
    static Map<String, Integer>[] mapList;
    static int[] maxCnt;
    public String[] solution(String[] orders, int[] course) {
        mapList = new Map[11];
        maxCnt = new int[11];

        Arrays.fill(maxCnt, 2);

        for(int i = 0; i < 11; i++) {
            mapList[i] = new HashMap<>();
        }

        for(String orderS : orders) {
            for(int num : course) {
                if(num > orderS.length()) continue;
                char[] order = orderS.toCharArray();
                Arrays.sort(order);
                visited = new char[num];
                conbination(order, num, 0, 0);
            }
        }

        List<String> courseList = new ArrayList<>();

        for(int num : course) {
            for(Map.Entry<String, Integer> entry : mapList[num].entrySet()) {
                if(entry.getValue() == maxCnt[num]) {
                    courseList.add(entry.getKey());
                }
            }
        }

        Collections.sort(courseList);
        return courseList.toArray(new String[0]);
    }

    static void conbination(char[] order, int num, int cnt, int idx) {
        if(num == cnt) {
            String string = String.valueOf(visited);
            mapList[num].put(string, mapList[num].getOrDefault(string, 0) + 1);
            maxCnt[num] = Math.max(maxCnt[num], mapList[num].get(string));
            return;
        }

        for(int i = idx; i < order.length; i++) {
            visited[cnt] = order[i];
            conbination(order, num, cnt + 1, i + 1);
        }
    }
}
