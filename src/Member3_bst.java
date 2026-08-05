// components : Binary Search Tree (BST), Selection Sort.

public class Member3_BST {

    private static class TreeNode {
        Member data;
        TreeNode left, right;
        TreeNode(Member data) { this.data = data; }
    }
    private TreeNode root;

    // insert
    public void insert(Member member) {
        root = insertRec(root, member);
    }

    private TreeNode insertRec(TreeNode node, Member member) {
        if (node == null) return new TreeNode(member);
        if (member.getMemberID() < node.data.getMemberID())
            node.left = insertRec(node.left, member);
        else if (member.getMemberID() > node.data.getMemberID())
            node.right = insertRec(node.right, member);
        return node;
    }

    // search
    public Member search(int memberID) {
        TreeNode node = root;
        while (node != null) {
            if (memberID == node.data.getMemberID()) return node.data;
            node = memberID < node.data.getMemberID() ? node.left : node.right;
        }
        return null;
    }

    //delete
    public void delete(int memberID) {
        root = deleteRec(root, memberID);
    }

    private TreeNode deleteRec(TreeNode node, int memberID) {
        if (node == null) return null;
        if (memberID < node.data.getMemberID()) {
            node.left = deleteRec(node.left, memberID);
        } else if (memberID > node.data.getMemberID()) {
            node.right = deleteRec(node.right, memberID);
        } else {
            if (node.left == null) return node.right;
            if (node.right == null) return node.left;
            TreeNode successor = node.right;
            while (successor.left != null) successor = successor.left;
            node.data = successor.data;
            node.right = deleteRec(node.right, successor.data.getMemberID());
        }
        return node;
    }

    public void inorder() { inorderRec(root); System.out.println(); }
    private void inorderRec(TreeNode n) {
        if (n == null) return;
        inorderRec(n.left);
        System.out.print(n.data.getMemberID() + " ");
        inorderRec(n.right);
    }

    public void preorder() { preorderRec(root); System.out.println(); }
    private void preorderRec(TreeNode n) {
        if (n == null) return;
        System.out.print(n.data.getMemberID() + " ");
        preorderRec(n.left);
        preorderRec(n.right);
    }

    public void postorder() { postorderRec(root); System.out.println(); }
    private void postorderRec(TreeNode n) {
        if (n == null) return;
        postorderRec(n.left);
        postorderRec(n.right);
        System.out.print(n.data.getMemberID() + " ");
    }

    // Selection Sort
    public void selectionSort(Member[] arr) {
        int n = arr.length;
        for (int i = 0; i < n - 1; i++) {
            int minIdx = i;
            for (int j = i + 1; j < n; j++) {
                if (arr[j].getMemberID() < arr[minIdx].getMemberID()) minIdx = j;
            }
            Member temp = arr[minIdx];
            arr[minIdx] = arr[i];
            arr[i] = temp;
        }
    }
}
