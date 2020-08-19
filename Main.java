import java.util.*;

public class Main {

    static ArrayList<Integer>[] graph; // 인접리스트 방식으로 그래프 저장
    static boolean[] check; // 정점 방문 여부를 확인하는 배열
    // 리스트(그래프)의 원소(정점) 접근은 for each문을 사용하여 처리
    // (for문과 get()메소드로 인덱스를 사용해 접근하는 것보다 빠르기 때문)

    static void dfs(int x) {
        if (check[x]) return; // 이미 방문한 적이 있는 정점이라면 해당 정점 탐색하지 않음
        check[x]= true; // 방문한 정점 체크
        System.out.print(x + " "); // 정점 방문하면 출력
        for (int y : graph[x]) { // 각 정점에 연결되어 있는 정점들 모두에 대해 확인 (리스트에도 for each문 적용 가능)
            if (!check[y]) { // 방문한 적이 없는 정점이라면
                dfs(y); // 해당 정점을 방문하고 그 정점에서 dfs 수행
            }
        }
    }

    static void bfs(int start) {
        Queue<Integer> queue = new LinkedList<Integer>();
        queue.add(start); // 처음으로 bfs 탐색을 시작할 정점을 큐에 추가
        check[start] = true; // 정점 방문 체크
        while (!queue.isEmpty()) { // 큐가 비면 모든 탐색을 완료한 것임
            int x = queue.remove(); // bfs 탐색을 수행할 현재 정점
            System.out.print(x + " "); // 정점 방문하면 출력
            for (int y : graph[x]) { // bfs를 수행하는 정점에 연결된 모든 정점들 확인
                if (!check[y]) { // 정점을 방문하지 않았다면
                    queue.add(y); // 정점 방문
                    check[y] = true; // 정점 방문 체크
                }
            }
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int v = sc.nextInt();
        int e = sc.nextInt();
        int start = sc.nextInt();
        graph = (ArrayList<Integer>[]) new ArrayList[v+1];
        for (int i=1;i<=v;i++) { // 각 배열의 원소(정점)에 대해 리스트 생성
            graph[i] = new ArrayList<Integer>();
        }
        // 각 배열의 원소는 각 정점에 해당한 정점들을 저장하는 리스트로 구성된다.
        for (int i=0;i<e;i++) {
            int temp1 = sc.nextInt();
            int temp2 = sc.nextInt();
            // 양 정점 모두에 add 해주어야 함 (undirected edge이기 때문)
            graph[temp1].add(temp2);
            graph[temp2].add(temp1);
        }
        // 방문할 수 있는 정점이 여러 개인 경우에는 정점 번호가 작은 것을 먼저 방문하는 조건을 충족하기 위한 정렬
        for (int i=1;i<=v;i++) Collections.sort(graph[i]);

        check = new boolean[v+1]; // dfs 수행 전에 정점 방문 여부 배열 초기화
        dfs(start);
        System.out.println();
        check = new boolean[v+1]; // bfs 수행 전에 정점 방문 여부 배열 초기화
        bfs(start);

    }
}
