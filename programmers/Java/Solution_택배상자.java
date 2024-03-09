package algorithm.programmers.Java;

import java.util.*;

class Solution_택배상자 {
    public int solution(int[] order) {
        int answer = 0;
        Stack<Integer> stack = new Stack<>();

        for(int i = 1; i <= order.length; i++) {
            stack.add(i);
            while(!stack.isEmpty()) {
                if(stack.peek() == order[answer]) {
                    answer++;
                    stack.pop();
                } else {
                    break;
                }
            }
        }

        return answer;
    }
}

//class Solution {
//    public int solution(int[] order) {
//        int answer = 0;
//        int i = 1;
//        Stack<Integer> stack = new Stack<>();
//
//        while(!stack.isEmpty() || i <= order.length) {
//            if(order[answer] == i) {
//                answer++;
//                i++;
//            } else if(!stack.isEmpty() && order[answer] == stack.peek()) {
//                answer++;
//                stack.pop();
//            } else if(order[answer] < i) {
//                break;
//            } else {
//                stack.add(i);
//                i++;
//            }
//        }
//
//        return answer;
//    }
//}

