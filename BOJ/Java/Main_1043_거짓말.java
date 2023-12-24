package algorithm.BOJ.Java;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_1043_거짓말 {
    static int[] parent;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int answer = 0;

        parent = new int[N + 1];
        for(int i = 0; i < N + 1; i++) {
            parent[i] = i;
        }

        st = new StringTokenizer(br.readLine());
        int cnt = Integer.parseInt(st.nextToken());
        int a = 0;

        if(cnt > 0) {
            a = Integer.parseInt(st.nextToken());

            for(int i = 1; i < cnt; i++) {
                int b = Integer.parseInt(st.nextToken());
                union(a, b);
            }
        }

        int[][] arr = new int[M][];
        for(int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            cnt = Integer.parseInt(st.nextToken());
            arr[i] = new int[cnt];
            arr[i][0] = Integer.parseInt(st.nextToken());

            for(int j = 1; j < cnt; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
                union(arr[i][0], arr[i][j]);
            }
        }

        for(int i = 0; i < M; i++) {
            if(findSet(a) != findSet(arr[i][0])) answer++;
        }

        System.out.println(answer);
    }

    static int findSet(int v) {
        if(parent[v] == v) return v;
        return parent[v] = findSet(parent[v]);
    }

    static boolean union(int a, int b) {
        int parentA = findSet(a);
        int parentB = findSet(b);

        if(parentA == parentB) return false;

        parent[parentB] = parentA;
        return true;
    }
}
