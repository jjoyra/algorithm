package algorithm.BOJ.Java;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main_10775_공항 {
    static int G, P;
    static int[] visited;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        G = Integer.parseInt(br.readLine());
        P = Integer.parseInt(br.readLine());
        visited = new int[G + 1];

        for(int i = 0; i < G + 1; i++) {
            visited[i] = i;
        }

        for(int i = 0; i < P; i++) {
            int tmp = Integer.parseInt(br.readLine());
            if(search(tmp) < 0) {
                System.out.println(i);
                return;
            }
        }

        System.out.println(P);
    }

    static int search(int tmp) {
        if(visited[tmp] <= 0) {
            return -1;
        } else if(visited[tmp] == tmp) {
            visited[tmp] = tmp - 1;
        } else {
            visited[tmp] = search(visited[tmp]);
        }
        return visited[tmp];
    }
}




