// components : Stack, Queue, Bubble Sort.
// Stack - undo recent activity from the log.
// Queue - Trainer Waiting Queue
// Bubble Sort

public class Member2_StackQueue {

    // Stack
    private String[] undoStack = new String[100];
    private int top = -1;

    public void pushAction(String action) {
        if (top == undoStack.length - 1) {
            System.out.println("Undo stack is full.");
            return;
        }
        undoStack[++top] = action;
    }

    public String popAction() {
        if (top == -1) {
            System.out.println("No actions to undo.");
            return null;
        }
        return undoStack[top--];
    }

    public boolean isUndoStackEmpty() { return top == -1; }

    // Queue
    private Member[] queue = new Member[100];
    private int front = 0, rear = -1, count = 0;

    public void enqueue(Member member) {
        if (count == queue.length) {
            System.out.println("Trainer queue is full.");
            return;
        }
        rear = (rear + 1) % queue.length;
        queue[rear] = member;
        count++;
    }

    public Member dequeue() {
        if (count == 0) {
            System.out.println("No members waiting for a trainer.");
            return null;
        }
        Member m = queue[front];
        front = (front + 1) % queue.length;
        count--;
        return m;
    }

    public void showQueue() {
        if (count == 0) {
            System.out.println("No one waiting For a trainer.");
            return;
        }
        int idx = front;
        for (int i = 0; i < count; i++) {
            System.out.println((i + 1) + ". " + queue[idx]);
            idx = (idx + 1) % queue.length;
        }
    }

    // Bubble Sort
    public void bubbleSort(Member[] arr) {
        int n = arr.length;
        for (int i = 0; i < n - 1; i++) {
            boolean swapped = false;
            for (int j = 0; j < n - i - 1; j++) {
                if (arr[j].getMemberID() > arr[j + 1].getMemberID()) {
                    Member temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                    swapped = true;
                }
            }
            if (!swapped) break;
        }
    }
}
