package algorithm.programmers.Java;

import java.util.*;

class Solution_호텔_대실 {
    static class Time implements Comparable<Time> {
        int start, end;

        Time(int start, int end) {
            this.start = start;
            this.end = end;
        }

        @Override
        public int compareTo(Time time) {
            if(this.start != time.start) {
                return this.start - time.start;
            }

            return this.end - time.end;
        }
    }
    public int solution(String[][] book_time) {
        List<Time> list = new ArrayList<>();

        for(int i = 0; i < book_time.length; i++) {
            int start = Integer.parseInt(book_time[i][0].substring(0, 2)) * 60 + Integer.parseInt(book_time[i][0].substring(3, 5));
            int end = Integer.parseInt(book_time[i][1].substring(0, 2)) * 60 + Integer.parseInt(book_time[i][1].substring(3, 5));
            list.add(new Time(start, end));
        }

        Collections.sort(list);

        PriorityQueue<Integer> pq = new PriorityQueue<>();
        pq.offer(list.get(0).end + 10);

        for(int i = 1; i < list.size(); i++) {
            if(pq.peek() <= list.get(i).start) {
                pq.poll();
            }
            pq.offer(list.get(i).end + 10);
        }

        return pq.size();
    }
}