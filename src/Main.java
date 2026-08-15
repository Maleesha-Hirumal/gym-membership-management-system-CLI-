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
        System.out.println("██╗    ██╗███████╗██╗      ██████╗ ██████╗ ███╗   ███╗███████╗      ██╗");
        System.out.println("██║    ██║██╔════╝██║     ██╔════╝██╔═══██╗████╗ ████║██╔════╝      ██║");
        System.out.println("██║ █╗ ██║█████╗  ██║     ██║     ██║   ██║██╔████╔██║█████╗        ██║");
        System.out.println("██║███╗██║██╔══╝  ██║     ██║     ██║   ██║██║╚██╔╝██║██╔══╝        ╚═╝");
        System.out.println("╚███╔███╔╝███████╗███████╗╚██████╗╚██████╔╝██║ ╚═╝ ██║███████╗██╗██╗██╗");
        System.out.println(" ╚══╝╚══╝ ╚══════╝╚══════╝ ╚═════╝ ╚═════╝ ╚═╝     ╚═╝╚══════╝╚═╝╚═╝╚═╝");
     
        System.out.println("\n╔════════════════════════════════════════════════╗");
        System.out.println("║        Gym Membership Management System        ║");
        System.out.println("╚════════════════════════════════════════════════╝\n");


     // Login part
        System.out.println("--------------- LOGIN REQUIRED ---------------");
        System.out.println("For demo Log in Purpose  ( User Name : root  ,  Password : root)...!");
        System.out.println();


    System.out.print("Enter Username: ");
    String username = sc.nextLine();

    System.out.print("Enter Password: ");
    String password = sc.nextLine();

    if (!username.equals("root") || !password.equals("root")) {
        System.out.println("\n-------- Invalid username or password. --------");
        sc.close();
        return;
    }

        System.out.println("\n------------- LOGIN SUCCESSFULLY -------------");
    System.out.println();

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
                default: System.out.println("✘ Invalid choice.");
            }
        } while (choice != 0);

        DatabaseConnection.close();
        sc.close();
    }

    // Main Function Menu
    private static void printMenu() {

        System.out.println("\n┌───────────────────────────────────────────────────────────────┐");
        System.out.println("│             GYM MEMBERSHIP MANAGEMENT SYSTEM                  │");
        System.out.println("├───────────────────────────────────────────────────────────────┤");
        System.out.println("│  1. Register New Member                                       │");
        System.out.println("│  2. View All Members                                          │");
        System.out.println("│  3. Find a Member                                             │");
        System.out.println("│  4. Remove a Member                                           │");
        System.out.println("│  5. Undo Last Action                                          │");
        System.out.println("│  6. Trainer Waiting Queue                                     │");
        System.out.println("│  7. Sort All Members                                          │");
        System.out.println("│  8. View Sorted Member Records                                │");
        System.out.println("│  9. Referral Program                                          │");
        System.out.println("│ 10. Save All Data to Database                                 │");
        System.out.println("│  0. Exit                                                      │");
        System.out.println("└───────────────────────────────────────────────────────────────┘");
        System.out.print("Enter your choice: ");
    }

     private static void queueMenu() {

         System.out.println("\n┌───────────────────────────────────────────────┐");
         System.out.println("│          TRAINER WAITING QUEUE                │");
         System.out.println("├───────────────────────────────────────────────┤");
         System.out.println("│ 1. Add Member to Queue                        │");
         System.out.println("│ 2. Serve Next Member                          │");
         System.out.println("│ 0. Back to Main Menu                          │");
         System.out.println("└───────────────────────────────────────────────┘");
         System.out.print("Enter your choice: ");
     }
     private static void referralMenu() {

         System.out.println("\n┌───────────────────────────────────────────────┐");
         System.out.println("│             REFERRAL PROGRAM                  │");
         System.out.println("├───────────────────────────────────────────────┤");
         System.out.println("│ 1. Add Referral                               │");
         System.out.println("│ 2. Breadth First Search (BFS)                 │");
         System.out.println("│ 3. Depth First Search (DFS)                   │");
         System.out.println("│ 4. Print Referral Graph                       │");
         System.out.println("│ 0. Back to Main Menu                          │");
         System.out.println("└───────────────────────────────────────────────┘");
         System.out.print("Enter your choice: ");
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
    private static void registerMember(){
        System.out.println("\n┌────────────────────────────────────────────────────┐");
        System.out.println("│              REGISTER NEW MEMBER                   │");
        System.out.println("└────────────────────────────────────────────────────┘");

        System.out.print("Member ID : ");
        int id = readInt();

        System.out.print("Name      : ");
        String name = sc.nextLine();

        System.out.print("Email     : ");
        String email = sc.nextLine();

        System.out.print("Phone No. : ");
        String phone = sc.nextLine();

        MembershipPlan[] plans = member1.getMembershipPlans();

        System.out.println("\n┌───────────────────────────────────────────┐");
        System.out.println("│            AVAILABLE MEMBERSHIP PLANS     │");
        System.out.println("├────┬────────────┬──────────┬──────────────┤");
        System.out.printf("│ %-2s │ %-10s │ %-8s │ %-12s │%n",
                "No", "Plan", "Duration", "Fee");
        System.out.println("├────┼────────────┼──────────┼──────────────┤");

        for (int i = 0; i < plans.length; i++) {
            MembershipPlan p = plans[i];

            String duration = p.getDurationMonths() + " M";

            System.out.printf("│ %-2d │ %-10s │ %-8s │ Rs. %-7.2f  │%n",
                    (i + 1),
                    p.getPlanName(),
                    duration,
                    p.getFee());
        }

        System.out.println("└────┴────────────┴──────────┴──────────────┘");

        System.out.print("Choose a plan number: ");
        int planChoice = readInt();
        MembershipPlan chosenPlan = plans[Math.max(0, Math.min(planChoice - 1, plans.length - 1))];

        // Check email is already in the data base or not for prevent email duplication
        if (!member6.addEmail(email)) {
            System.out.println("✘ Registration failed: email already registered (duplicate prevented).");
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
        System.out.println("\n┌───────────────────────────────────────────┐");
        System.out.println("│    All members (linked list traversal)    │");
        System.out.println("└───────────────────────────────────────────┘");
        member1.traverse();
    }

    // 3.Search a Member
    private static void searchMember() {
        System.out.println("\n┌───────────────────────────────────────────┐");
        System.out.println("│              SEARCH MEMBER                │");
        System.out.println("└───────────────────────────────────────────┘");

        System.out.print("Enter Member ID : ");
        int id = readInt();

        Member viaHash = member6.get(id);

        if (viaHash != null) {
            System.out.println("\n┌───────────────────────────────────────────┐");
            System.out.println("│            MEMBER FOUND                   │");
            System.out.println("└───────────────────────────────────────────┘");
            System.out.println(" " + viaHash);
        } else {
            System.out.println("\n┌───────────────────────────────────────────┐");
            System.out.println("│         MEMBER NOT FOUND                  │");
            System.out.println("└───────────────────────────────────────────┘");
        }
    }

    // 4. Delete a Member
    private static void deleteMember() {
        System.out.println("\n┌───────────────────────────────────────────┐");
        System.out.println("│             DELETE MEMBER                 │");
        System.out.println("└───────────────────────────────────────────┘");

        System.out.print("Enter Member ID : ");
        int id = readInt();
        boolean removed = member1.delete(id);
        member3.delete(id);
        member4.delete(id);

        if (removed) {
            member2.pushAction("Deleted member " + id);
            System.out.println("\n┌───────────────────────────────────────────┐");
            System.out.println("│      MEMBER DELETED SUCCESSFULLY          │");
            System.out.println("└───────────────────────────────────────────┘");
            System.out.println("(Use menu option 10 to make this permanent in the database.)");
        } else {
            System.out.println("\n┌───────────────────────────────────────────┐");
            System.out.println("│         MEMBER NOT FOUND                  │");
            System.out.println("└───────────────────────────────────────────┘");
        }
    }

    // 5. Undo Last action
    private static void undoLastAction() {
        String action = member2.popAction();
        if (action != null) {
            System.out.println("\n┌───────────────────────────────────────────┐");
            System.out.println("│               UNDO ACTION                 │");
            System.out.println("└───────────────────────────────────────────┘");
            System.out.println(" " + action);
        }
    }

    // 6. Waiting list
    private static void frontDeskQueue() {
        queueMenu();
        int c = readInt();
        if (c == 1) {
            System.out.print("Member ID to enqueue: ");
            int id = readInt();
            Member m = member1.linearSearch(id);
            if (m != null) member2.enqueue(m); else System.out.println("✘ Member not found.");
        } else {
            Member served = member2.dequeue();
            if (served != null) System.out.println("Now serving: " + served);
        }
        member2.showQueue();
    }

    // 7. Sorting
    private static void sortingDemo() {
        Member[] base = member1.toArray();
        if (base.length == 0) { System.out.println("✘ No members to sort."); return; }

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

        System.out.println("\n┌────────────────────────────────────────────────────┐");
        System.out.println("│            SORTING PERFORMANCE                     │");
        System.out.println("├──────────────────────┬─────────────────────────────┤");
        System.out.printf("│ Bubble Sort          │ %-25d │%n", bubbleTime);
        System.out.printf("│ Selection Sort       │ %-25d │%n", selectionTime);
        System.out.printf("│ Insertion Sort       │ %-25d │%n", insertionTime);
        System.out.printf("│ Merge Sort           │ %-25d │%n", mergeTime);
        System.out.printf("│ Quick Sort           │ %-25d │%n", quickTime);
        System.out.println("└──────────────────────┴─────────────────────────────┘");
        System.out.println("(Times fluctuate on small datasets - add more members for a fairer comparison.)");
    }

    // 8. BST / AVL traversals
    private static void treeTraversals() {
        System.out.println("\n┌───────────────────────────────────────────┐");
        System.out.println("│            TREE TRAVERSALS                │");
        System.out.println("└───────────────────────────────────────────┘");
        System.out.print("Inorder:   ");   member3.inorder();
        System.out.print("Preorder:  ");  member3.preorder();
        System.out.print("Postorder: "); member3.postorder();

        System.out.println("\n┌───────────────────────────────────────────┐");
        System.out.println("│             AVL Tree inorder              │");
        System.out.println("└───────────────────────────────────────────┘");
        member4.inorder();
    }

    // load data from database
    private static void loadDataFromDatabase() {
        List<Member> members = DatabaseConnection.loadAllMembers();

        if (members == null || members.isEmpty()) {
            System.out.println("✘ No members found in database.");
            return;
        }

        for (Member m : members) {
            member1.insert(m);
            member6.put(m);
            member6.addEmail(m.getEmail());
            member3.insert(m);
            member4.insert(m);
            member5.addMemberNode(m.getMemberID());
        }

        System.out.println("✓ " + members.size() +
                " member(s) loaded successfully.");
    }

    // 10. Send data to Data Base
    private static void saveToDatabase() {
        Member[] all = member1.toArray();
        System.out.println("\n┌───────────────────────────────────────────┐");
        System.out.println("│        SAVING TO DATABASE...              │");
        System.out.println("└───────────────────────────────────────────┘");

        DatabaseConnection.saveAllMembers(all);

        if (dbAvailable) {
            System.out.println("\n┌───────────────────────────────────────────┐");
            System.out.println("│       DATA SAVED SUCCESSFULLY             │");
            System.out.println("└───────────────────────────────────────────┘");
        }
    }

    // 9.Referral Program
    private static void referralGraphDemo() {
        referralMenu();
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
