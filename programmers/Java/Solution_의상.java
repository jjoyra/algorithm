package algorithm.programmers.Java;


import java.util.*;

public class Solution_의상 {
    static int count, arr[];
    static Map<String, Integer> map;

    public int solution(String[][] clothes) {

        map = new HashMap<>();

        for(String[] arr : clothes) {
            if(map.containsKey(arr[1])) map.put(arr[1], map.get(arr[1]) + 1);
            else map.put(arr[1], 1);
        }

        arr = new int[map.size()];
        int idx = 0;

        for(String key : map.keySet()) {
            arr[idx++] = map.get(key);
        }

        subSet(0, 1);
        System.out.println(Arrays.toString(arr));
        return count - 1;

    }

    public void subSet(int cnt, int tmp) {
        if(cnt == map.size()) {
            count += tmp;
            return;
        }

        subSet(cnt + 1, tmp * arr[cnt]);
        subSet(cnt + 1, tmp);
    }

}