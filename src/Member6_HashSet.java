// components : Hash Table, Set ADT, Quick Sort.

import java.util.HashSet;
import java.util.LinkedList;

public class Member6_HashSet {

    // Hash Table
    private static final int TABLE_SIZE = 20;
    private LinkedList<Member>[] table;

    @SuppressWarnings("unchecked")
    public Member6_HashSet() {
        table = new LinkedList[TABLE_SIZE];
        for (int i = 0; i < TABLE_SIZE; i++) table[i] = new LinkedList<>();
    }

    private int hash(int memberID) {
        return memberID % TABLE_SIZE;
    }

    public void put(Member member) {
        int index = hash(member.getMemberID());
        table[index].add(member);
    }

    public Member get(int memberID) {
        int index = hash(memberID);
        for (Member m : table[index]) {
            if (m.getMemberID() == memberID) return m;
        }
        return null;
    }

    public void printTable() {
        boolean hasRecord = false
        for (int i = 0; i < TABLE_SIZE; i++) {
            if (!table[i].isEmpty()) {
                hasRecords = true;
                System.out.println("Bucket " + i + ": " + table[i]);
        }
    }
        if (!hasRecord){
            System.out.println("✘ No memberr records are currently stored.");
        }
    }

    // Set ADT (duplicate email prevention)
    private HashSet<String> emailSet = new HashSet<>();

    /** Time: O(1) average. Returns false if the email is already registered. */
    public boolean addEmail(String email) {
        return emailSet.add(email);
    }

    public boolean containsEmail(String email) {
        return emailSet.contains(email);
    }

    // Quick Sort
    public void quickSort(Member[] arr, int low, int high) {
        if (low < high) {
            int pi = partition(arr, low, high);
            quickSort(arr, low, pi - 1);
            quickSort(arr, pi + 1, high);
        }
    }

    private int partition(Member[] arr, int low, int high) {
        int pivot = arr[high].getMemberID();
        int i = low - 1;
        for (int j = low; j < high; j++) {
            if (arr[j].getMemberID() < pivot) {
                i++;
                Member temp = arr[i]; arr[i] = arr[j]; arr[j] = temp;
            }
        }
        Member temp = arr[i + 1]; arr[i + 1] = arr[high]; arr[high] = temp;
        return i + 1;
    }
}
