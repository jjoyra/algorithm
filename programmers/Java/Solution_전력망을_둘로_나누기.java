package algorithm.programmers.Java;

import java.util.*;

class Solution_전력망을_둘로_나누기 {
    static List<Integer>[] node;
    static int answer;
    public int solution(int n, int[][] wires) {
        node = new List[n + 1];

        for(int i = 1; i < n + 1; i++) {
            node[i] = new ArrayList<>();
        }

        for(int[] wire : wires) {
            node[wire[0]].add(wire[1]);
            node[wire[1]].add(wire[0]);
        }

        answer = n;

        for(int[] wire : wires) {
            sol(wire);
        }

        return answer;
    }

    static void sol(int[] wire) {
        int cnt = 1;
        boolean[] visited = new boolean[node.length];

        Queue<Integer> queue = new ArrayDeque<>();
        queue.offer(wire[0]);
        visited[wire[0]] = true;

        while(!queue.isEmpty()) {
            int tmp = queue.poll();

            for(int next : node[tmp]) {
                if(visited[next] || (tmp == wire[0] && next == wire[1])) continue;
                queue.offer(next);
                visited[next] = true;
                cnt++;
            }
        }
        answer = Math.min(answer, Math.abs(node.length - (2 * cnt + 1)));
    }
}
