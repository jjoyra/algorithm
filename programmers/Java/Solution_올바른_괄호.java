package algorithm.programmers.Java;

import java.util.*;
public class Solution_올바른_괄호 {
    boolean solution(String s) {
        Stack<Character> stack = new Stack();

        if(s.charAt(0) == '(') stack.push('(');
        else {
            return false;
        }

        for(int i = 1; i < s.length(); i++) {
            char tmp = s.charAt(i);
            if(tmp == ')' && !stack.isEmpty() && stack.peek() == '(') stack.pop();
            else if (tmp == '(') stack.push('(');
            else return false;
        }

        if(!stack.isEmpty()) return false;
        return true;
    }
}