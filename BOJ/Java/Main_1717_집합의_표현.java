package algorithm.BOJ.Java;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_1717_집합의_표현 {
    static int N, M, parent[];
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        N = Integer.parseInt(st.nextToken()) + 1;
        M = Integer.parseInt(st.nextToken());

        set();

        for(int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());

            int type = Integer.parseInt(st.nextToken());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            if(type == 0) {
                union(a, b);
            } else {
                if(isDisjoint(a, b)) sb.append("NO\n");
                else sb.append("YES\n");
            }
        }

        System.out.println(sb);
    }

    static void set() {
        parent = new int[N];

        for(int i = 0; i < N; i++) {
            parent[i] = i;
        }
    }

    static int find(int n) {
        if(parent[n] == n) return n;
        return parent[n] = find(parent[n]);
    }

    static void union(int a, int b) {
        if(isDisjoint(a, b)) {
            int aParent = find(a);
            int bParent = find(b);
            parent[aParent] = bParent;
        }
    }

    static boolean isDisjoint(int a, int b) {
        int aParent = find(a);
        int bParent = find(b);

        if(aParent == bParent) return false;
        else return true;
    }
}