// components : Graph, BFS, DFS, Merge Sort.
// Graph : 9. Referral Program.

import java.util.*;

public class Member5_Graph {
    private Map<Integer, List<Integer>> adjList = new HashMap<>();
    public void addMemberNode(int memberID) {
        adjList.putIfAbsent(memberID, new ArrayList<>());
    }

    public void addReferral(int referrerID, int referredID) {
        addMemberNode(referrerID);
        addMemberNode(referredID);
        adjList.get(referrerID).add(referredID);
    }

    // BFS
    public void bfs(int startID) {
        if (!adjList.containsKey(startID)) { System.out.println("✘ Member not found in graph."); return; }
        Set<Integer> visited = new HashSet<>();
        Queue<Integer> queue = new LinkedList<>();
        queue.add(startID);
        visited.add(startID);

        System.out.print("BFS referral chain from member " + startID + ": ");
        while (!queue.isEmpty()) {
            int curr = queue.poll();
            System.out.print(curr + " ");
            for (int neighbor : adjList.getOrDefault(curr, new ArrayList<>())) {
                if (!visited.contains(neighbor)) {
                    visited.add(neighbor);
                    queue.add(neighbor);
                }
            }
        }
        System.out.println();
    }

    // DFS
    public void dfs(int startID) {
        if (!adjList.containsKey(startID)) { System.out.println("✘ Member not found in graph."); return; }
        Set<Integer> visited = new HashSet<>();
        System.out.print("DFS referral chain from member " + startID + ": ");
        dfsHelper(startID, visited);
        System.out.println();
    }

    private void dfsHelper(int curr, Set<Integer> visited) {
        visited.add(curr);
        System.out.print(curr + " ");
        for (int neighbor : adjList.getOrDefault(curr, new ArrayList<>())) {
            if (!visited.contains(neighbor)) dfsHelper(neighbor, visited);
        }
    }

    public void printGraph() {
        if (adjList.isEmpty()) { System.out.println("⚠ Graph is empty."); return; }
        for (int node : adjList.keySet()) {
            System.out.println(node + " -> " + adjList.get(node));
        }
    }

    // Merge Sort
    public void mergeSort(Member[] arr, int left, int right) {
        if (left < right) {
            int mid = (left + right) / 2;
            mergeSort(arr, left, mid);
            mergeSort(arr, mid + 1, right);
            merge(arr, left, mid, right);
        }
    }

    private void merge(Member[] arr, int left, int mid, int right) {
        Member[] leftArr = Arrays.copyOfRange(arr, left, mid + 1);
        Member[] rightArr = Arrays.copyOfRange(arr, mid + 1, right + 1);
        int i = 0, j = 0, k = left;
        while (i < leftArr.length && j < rightArr.length) {
            arr[k++] = leftArr[i].getMemberID() <= rightArr[j].getMemberID() ? leftArr[i++] : rightArr[j++];
        }
        while (i < leftArr.length) arr[k++] = leftArr[i++];
        while (j < rightArr.length) arr[k++] = rightArr[j++];
    }
}
