package algorithm.programmers.Java;

import java.util.*;

class Solution_달리기_경주 {
    public String[] solution(String[] players, String[] callings) {

        Map<String, Integer> player = new HashMap<>();
        Map<Integer, String> score = new HashMap<>();

        for(int i = 0; i < players.length; i++) {
            player.put(players[i], i);
            score.put(i, players[i]);
        }

        for(String calling : callings) {
            int num = player.get(calling);
            String pre = score.get(num - 1);

            player.put(calling, num - 1);
            player.put(pre, num);
            score.put(num - 1, calling);
            score.put(num, pre);
        }

        String[] answer = new String[players.length];

        for(int i = 0; i < players.length; i++) {
            answer[i] = score.get(i);
        }

        return answer;
    }
}