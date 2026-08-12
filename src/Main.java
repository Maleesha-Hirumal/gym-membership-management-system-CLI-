import java.util.List;
import java.util.Scanner;

 public class Main {

    private static Member1_ArraysLinkedList member1 = new Member1_ArraysLinkedList();
    private static Member2_StackQueue member2 = new Member2_StackQueue();
    private static Member3_BST member3 = new Member3_BST();
    private static Member4_AVLTree member4 = new Member4_AVLTree();
    private static Member5_Graph member5 = new Member5_Graph();
    private static Member6_HashSet member6 = new Member6_HashSet();

    private static Scanner sc = new Scanner(System.in);
    private static boolean dbAvailable;


  
    public static void main(String[] args) {
     // Login part
     System.out.println("\n╔════════════════════════════════════════════════╗");
        System.out.println("║        GYM MEMBERSHIP MANAGEMENT SYSTEM        ║");
        System.out.println("║                LOGIN REQUIRED                  ║");
        System.out.println("╚════════════════════════════════════════════════╝\n");


    System.out.print("Username: ");
    String username = sc.nextLine();

    System.out.print("Password: ");
    String password = sc.nextLine();

    if (!username.equals("root") || !password.equals("root")) {
        System.out.println("Invalid username or password.");
        System.out.println("Access denied!");
        sc.close();
        return;
    }

    System.out.println("Login successful!");
    System.out.println();
     
        System.out.println("=== Gym Membership Management System ===");
        dbAvailable = DatabaseConnection.connect();
        if (dbAvailable) {
            loadDataFromDatabase();
        }

        int choice;
        do {
            printMenu();
            choice = readInt();
            switch (choice) {
                case 1: registerMember(); break;
                case 2: viewAllMembers(); break;
                case 3: searchMember(); break;
                case 4: deleteMember(); break;
                case 5: undoLastAction(); break;
                case 6: frontDeskQueue(); break;
                case 7: sortingDemo(); break;
                case 8: treeTraversals(); break;
                case 9: referralGraphDemo(); break;
                case 10: saveToDatabase(); break;
                case 0: System.out.println("Exiting..."); break;
                default: System.out.println("Invalid choice.");
            }
        } while (choice != 0);

        DatabaseConnection.close();
        sc.close();
    }

    // Main Function Menu
    private static void printMenu() {
        System.out.println("\n--- Main Menu ---");
        System.out.println("1. Register new member");
        System.out.println("2. View all members");
        System.out.println("3. Find a Member");
        System.out.println("4. Remove a Member");
        System.out.println("5. Undo last action");
        System.out.println("6. Trainer Waiting Queue");
        System.out.println("7. Sort all Members");
        System.out.println("8. View Sorted Member Records (Trees)");
        System.out.println("9. Referral Program");
        System.out.println("10. Save all data to database");
        System.out.println("0. Exit");
        System.out.print("Enter choice: ");
    }

    private static int readInt() {
        while (!sc.hasNextInt()) {
            sc.next();
            System.out.print("Enter a valid number: ");
        }
        int val = sc.nextInt();
        sc.nextLine();
        return val;
    }

    // 1. Register a new Member
    private static void registerMember() {
        System.out.print("Member ID: ");
        int id = readInt();
        System.out.print("Name: ");
        String name = sc.nextLine();
        System.out.print("Email: ");
        String email = sc.nextLine();
        System.out.print("Phone: ");
        String phone = sc.nextLine();

        MembershipPlan[] plans = member1.getMembershipPlans();
        System.out.println("\nAvailable membership plans:");
        for (int i = 0; i < plans.length; i++) {
            MembershipPlan p = plans[i];
            System.out.println((i + 1) + ". " + p.getPlanName()
                    + " - " + p.getDurationMonths() + " month(s)"
                    + " - Rs. " + p.getFee());
        }
        System.out.print("Choose a plan number: ");
        int planChoice = readInt();
        MembershipPlan chosenPlan = plans[Math.max(0, Math.min(planChoice - 1, plans.length - 1))];

        // Check email is already in the data base or not for prevent email duplication
        if (!member6.addEmail(email)) {
            System.out.println("Registration failed: email already registered (duplicate prevented).");
            return;
        }

        Member member = new Member(id, name, email, phone, chosenPlan.getPlanID(), chosenPlan.getPlanName());

        member1.insert(member);
        member6.put(member);
        member3.insert(member);
        member4.insert(member);
        member5.addMemberNode(id);
        member2.pushAction("Registered member " + id);

        System.out.println("\nMember registered successfully: " + member.getName()
                + " (" + chosenPlan.getPlanName() + " plan) - stored in memory.");
        System.out.println("(Use menu option 10 to save this to the database.)");
    }

    // 2. View All Registered Members
    private static void viewAllMembers() {
        System.out.println("\n--- All members (linked list traversal) ---");
        member1.traverse();
    }

    // 3.Search a Member
    private static void searchMember() {
        System.out.print("Enter member ID to search: ");
        int id = readInt();

        long start = System.nanoTime();
        Member viaLinear = member1.linearSearch(id);
        long linearTime = System.nanoTime() - start;

        start = System.nanoTime();
        Member viaHash = member6.get(id);
        long hashTime = System.nanoTime() - start;

        if (viaHash != null) {
            System.out.println("Found: " + viaHash);
            System.out.println("Linear search time: " + linearTime + " ns");
            System.out.println("Hash table lookup time: " + hashTime + " ns");
        } else {
            System.out.println("Member not found.");
        }
    }

    // 4. Delete a Member
    private static void deleteMember() {
        System.out.print("Enter member ID to delete: ");
        int id = readInt();
        boolean removed = member1.delete(id);
        member3.delete(id);
        member4.delete(id);

        if (removed) {
            member2.pushAction("Deleted member " + id);
            System.out.println("Member deleted from memory.");
            System.out.println("(Use menu option 10 to make this permanent in the database.)");
        } else {
            System.out.println("Member not found.");
        }
    }

    // 5. Undo Last action
    private static void undoLastAction() {
        String action = member2.popAction();
        if (action != null) {
            System.out.println("Undo: " + action);
        }
    }

    // 6. Waiting list
    private static void frontDeskQueue() {
        System.out.println("1. Add member to queue   2. Serve next member");
        int c = readInt();
        if (c == 1) {
            System.out.print("Member ID to enqueue: ");
            int id = readInt();
            Member m = member1.linearSearch(id);
            if (m != null) member2.enqueue(m); else System.out.println("Member not found.");
        } else {
            Member served = member2.dequeue();
            if (served != null) System.out.println("Now serving: " + served);
        }
        member2.showQueue();
    }

    // 7. Sorting
    private static void sortingDemo() {
        Member[] base = member1.toArray();
        if (base.length == 0) { System.out.println("No members to sort."); return; }

        Member[] bubble = base.clone();
        Member[] selection = base.clone();
        Member[] insertion = base.clone();
        Member[] merge = base.clone();
        Member[] quick = base.clone();

        long t1 = System.nanoTime(); member2.bubbleSort(bubble); long bubbleTime = System.nanoTime() - t1;
        long t2 = System.nanoTime(); member3.selectionSort(selection); long selectionTime = System.nanoTime() - t2;
        long t3 = System.nanoTime(); member4.insertionSort(insertion); long insertionTime = System.nanoTime() - t3;
        long t4 = System.nanoTime(); member5.mergeSort(merge, 0, merge.length - 1); long mergeTime = System.nanoTime() - t4;
        long t5 = System.nanoTime(); member6.quickSort(quick, 0, quick.length - 1); long quickTime = System.nanoTime() - t5;

        System.out.println("\n--- Sorting comparison (by member ID, in nanoseconds) ---");
        System.out.println("Bubble Sort:    " + bubbleTime);
        System.out.println("Selection Sort: " + selectionTime);
        System.out.println("Insertion Sort: " + insertionTime);
        System.out.println("Merge Sort:     " + mergeTime);
        System.out.println("Quick Sort:     " + quickTime);
        System.out.println("(Times fluctuate on small datasets - add more members for a fairer comparison.)");
    }

    // 8. BST / AVL traversals
    private static void treeTraversals() {
        System.out.println("\n--- BST traversals ---");
        System.out.print("Inorder:   ");   member3.inorder();
        System.out.print("Preorder:  ");  member3.preorder();
        System.out.print("Postorder: "); member3.postorder();

        System.out.println("\n--- AVL Tree inorder ---");
        member4.inorder();
    }

    // load data from database
    private static void loadDataFromDatabase() {
        List<Member> members = DatabaseConnection.loadAllMembers();
        for (Member m : members) {
            member1.insert(m);
            member6.put(m);
            member6.addEmail(m.getEmail());
            member3.insert(m);
            member4.insert(m);
            member5.addMemberNode(m.getMemberID());
        }
        if (!members.isEmpty()) {
            System.out.println("Loaded " + members.size() + " member(s) from the database.");
        }
    }

    // 10. Send data to Data Base
    private static void saveToDatabase() {
        Member[] all = member1.toArray();
        DatabaseConnection.saveAllMembers(all);
    }

    // 9.Referral Program
    private static void referralGraphDemo() {
        System.out.println("1. Add referral   2. BFS   3. DFS   4. Print graph");
        int c = readInt();
        if (c == 1) {
            System.out.print("Referrer member ID: ");
            int a = readInt();
            System.out.print("Referred member ID: ");
            int b = readInt();
            member5.addReferral(a, b);
            System.out.println("Referral added.");
        } else if (c == 2) {
            System.out.print("Start member ID: ");
            member5.bfs(readInt());
        } else if (c == 3) {
            System.out.print("Start member ID: ");
            member5.dfs(readInt());
        } else {
            member5.printGraph();
        }
    }
}
