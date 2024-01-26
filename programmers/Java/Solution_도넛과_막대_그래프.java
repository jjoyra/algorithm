package algorithm.programmers.Java;

import java.util.*;

class Solution_도넛과_막대_그래프 {
    static List<Integer>[] list;
    static int[] inEdge, outEdge;
    public int[] solution(int[][] edges) {
        int[] answer = new int[4];
        Arrays.sort(edges, (a, b) -> Math.max(b[0], b[1]) - Math.max(a[0], a[1]));
        list = new List[Math.max(edges[0][0], edges[0][1]) + 1];
        inEdge = new int[list.length];
        outEdge = new int[list.length];

        for(int i = 1; i < list.length; i++) {
            list[i] = new ArrayList<>();
        }

        for(int[] edge : edges) {
            list[edge[0]].add(edge[1]);
            inEdge[edge[1]]++;
            outEdge[edge[0]]++;
        }

        for(int i = 1; i < inEdge.length; i++) {
            if(inEdge[i] == 0 && outEdge[i] > 1) {
                answer[0] = i;
                inEdge[i]--;
                for(int node : list[answer[0]]) {
                    inEdge[node]--;
                }
                break;
            }
        }

        for(int i = 1; i < inEdge.length; i++) {
            if(inEdge[i] == 0) {
                answer[2]++;
            } else if(inEdge[i] == 2) {
                answer[3]++;
            }
        }

        answer[1] = outEdge[answer[0]] - (answer[2] + answer[3]);
        return answer;
    }
}