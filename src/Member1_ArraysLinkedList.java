// components : Arrays, Linked List, Linear Search.
// Array - fixed set of membership plans (Basic/Premium/VIP)
// Linked List - dynamic, growable storage of all registered members
// Linear Search - search by scanning the linked list one node at a time

public class Member1_ArraysLinkedList {

    // Array
    private MembershipPlan[] membershipPlans = {
            new MembershipPlan(1, "Basic", 1, 3000),
            new MembershipPlan(2, "Premium", 3, 8000),
            new MembershipPlan(3, "VIP", 12, 25000)
    };

    public MembershipPlan[] getMembershipPlans() {
        return membershipPlans;
    }

    // Singly Linked List
    private static class Node {
        Member data;
        Node next;
        Node(Member data) { this.data = data; }
    }

    private Node head;
    private int size = 0;

    // add member
    public void insert(Member member) {
        Node newNode = new Node(member);
        if (head == null) {
            head = newNode;
        } else {
            Node temp = head;
            while (temp.next != null) temp = temp.next;
            temp.next = newNode;
        }
        size++;
    }

    // delete member
    public boolean delete(int memberID) {
        if (head == null) return false;

        if (head.data.getMemberID() == memberID) {
            head = head.next;
            size--;
            return true;
        }

        Node prev = head, curr = head.next;
        while (curr != null) {
            if (curr.data.getMemberID() == memberID) {
                prev.next = curr.next;
                size--;
                return true;
            }
            prev = curr;
            curr = curr.next;
        }
        return false;
    }

    public void traverse() {
        if (head == null) {
            System.out.println("No members yet.");
            return;
        }
        Node temp = head;
        while (temp != null) {
            System.out.println(temp.data);
            temp = temp.next;
        }
    }

    // Converts the linked list in to an array, used on 7. Sort all Members
    public Member[] toArray() {
        Member[] arr = new Member[size];
        Node temp = head;
        int i = 0;
        while (temp != null) {
            arr[i++] = temp.data;
            temp = temp.next;
        }
        return arr;
    }

    public int getSize() { return size; }

    // Linear Search
    public Member linearSearch(int memberID) {
        Node temp = head;
        while (temp != null) {
            if (temp.data.getMemberID() == memberID) return temp.data;
            temp = temp.next;
        }
        return null;
    }
}
