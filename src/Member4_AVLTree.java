// components : AVL Tree, Insertion Sort.

public class Member4_AVLTree {

    private static class AVLNode {
        Member data;
        AVLNode left, right;
        int height = 1;
        AVLNode(Member data) { this.data = data; }
    }
    private AVLNode root;

    private int height(AVLNode n) { return n == null ? 0 : n.height; }
    private int balanceFactor(AVLNode n) { return n == null ? 0 : height(n.left) - height(n.right); }

    private AVLNode rotateRight(AVLNode y) {
        AVLNode x = y.left;
        AVLNode t2 = x.right;
        x.right = y;
        y.left = t2;
        y.height = Math.max(height(y.left), height(y.right)) + 1;
        x.height = Math.max(height(x.left), height(x.right)) + 1;
        return x;
    }

    private AVLNode rotateLeft(AVLNode x) {
        AVLNode y = x.right;
        AVLNode t2 = y.left;
        y.left = x;
        x.right = t2;
        x.height = Math.max(height(x.left), height(x.right)) + 1;
        y.height = Math.max(height(y.left), height(y.right)) + 1;
        return y;
    }

    public void insert(Member member) {
        root = insertRec(root, member);
    }

    private AVLNode insertRec(AVLNode node, Member member) {
        if (node == null) return new AVLNode(member);
        if (member.getMemberID() < node.data.getMemberID())
            node.left = insertRec(node.left, member);
        else if (member.getMemberID() > node.data.getMemberID())
            node.right = insertRec(node.right, member);
        else
            return node;

        node.height = 1 + Math.max(height(node.left), height(node.right));
        int balance = balanceFactor(node);

        if (balance > 1 && member.getMemberID() < node.left.data.getMemberID())
            return rotateRight(node);
        if (balance < -1 && member.getMemberID() > node.right.data.getMemberID())
            return rotateLeft(node);
        if (balance > 1 && member.getMemberID() > node.left.data.getMemberID()) {
            node.left = rotateLeft(node.left);
            return rotateRight(node);
        }
        if (balance < -1 && member.getMemberID() < node.right.data.getMemberID()) {
            node.right = rotateRight(node.right);
            return rotateLeft(node);
        }
        return node;
    }

    public Member search(int memberID) {
        AVLNode node = root;
        while (node != null) {
            if (memberID == node.data.getMemberID()) return node.data;
            node = memberID < node.data.getMemberID() ? node.left : node.right;
        }
        return null;
    }

    public void delete(int memberID) {
        root = deleteRec(root, memberID);
    }

    private AVLNode deleteRec(AVLNode node, int memberID) {
        if (node == null) return null;
        if (memberID < node.data.getMemberID()) {
            node.left = deleteRec(node.left, memberID);
        } else if (memberID > node.data.getMemberID()) {
            node.right = deleteRec(node.right, memberID);
        } else {
            if (node.left == null) return node.right;
            if (node.right == null) return node.left;
            AVLNode successor = node.right;
            while (successor.left != null) successor = successor.left;
            node.data = successor.data;
            node.right = deleteRec(node.right, successor.data.getMemberID());
        }

        node.height = 1 + Math.max(height(node.left), height(node.right));
        int balance = balanceFactor(node);

        if (balance > 1 && balanceFactor(node.left) >= 0) return rotateRight(node);
        if (balance > 1 && balanceFactor(node.left) < 0) {
            node.left = rotateLeft(node.left);
            return rotateRight(node);
        }
        if (balance < -1 && balanceFactor(node.right) <= 0) return rotateLeft(node);
        if (balance < -1 && balanceFactor(node.right) > 0) {
            node.right = rotateRight(node.right);
            return rotateLeft(node);
        }
        return node;
    }

    public void inorder() { inorderRec(root); System.out.println(); }
    private void inorderRec(AVLNode n) {
        if (n == null) return;
        inorderRec(n.left);
        System.out.print("Member ID: "+ n.data.getMemberID() + " | ");
        inorderRec(n.right);
    }

    // Insertion Sort
    public void insertionSort(Member[] arr) {
        int n = arr.length;
        for (int i = 1; i < n; i++) {
            Member key = arr[i];
            int j = i - 1;
            while (j >= 0 && arr[j].getMemberID() > key.getMemberID()) {
                arr[j + 1] = arr[j];
                j--;
            }
            arr[j + 1] = key;
        }
    }
}
