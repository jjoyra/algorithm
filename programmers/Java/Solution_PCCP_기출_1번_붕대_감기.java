package algorithm.programmers.Java;

public class Solution_PCCP_기출_1번_붕대_감기 {
    public int solution(int[] bandage, int health, int[][] attacks) {
        int answer = health - attacks[0][1];

        System.out.println(answer);

        for (int i = 1; i < attacks.length; i++) {
            int time = attacks[i][0] - attacks[i - 1][0] - 1;
            answer += bandage[1] * time + time / bandage[0] * bandage[2];
            answer = Math.min(answer, health);
            answer -= attacks[i][1];

            if (answer <= 0) return -1;
        }

        return answer;
    }
}
