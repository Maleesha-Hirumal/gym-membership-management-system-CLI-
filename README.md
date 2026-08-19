# 🏋️ Gym Membership Management System
## Version 3.0

A console-based Gym Membership Management System built in Java, developed as a group project for **CCS2300 — Data Structures and Algorithms**. The system demonstrates real, working implementations of core data structures — Arrays, Linked Lists, Stacks, Queues, Binary Search Trees, AVL Trees, Hash Tables, Set ADTs, and Graphs — integrated into a single practical application with MySQL persistence.

---

## 📌 Overview

This system manages gym members, membership plans, trainer waiting lists, and member referral networks. It's built so that **every data structure does a real job** rather than existing as an isolated demo — for example, the Hash Table powers member lookup, and the Graph models an actual member referral network traced with BFS/DFS.

The system runs entirely in memory and only writes to the database when explicitly told to — this means it keeps working normally even if MySQL is unreachable.

---

## ✨ Features

- **Login Screen** — simple username/password gate before the system loads
- **Member Registration** — with duplicate email prevention via a Set ADT, and full plan pricing shown before choosing
- **Member Lookup** — via Hash Table
- **Undo Support** — Stack-based log of recent register/delete actions
- **Trainer Waiting Queue** — first-come, first-served queue for trainer assignment
- **Sorted Member Records** — Binary Search Tree and self-balancing AVL Tree, both with full traversals
- **Referral Network** — directed graph of member referrals, explorable via BFS and DFS
- **Sorting Comparison** — Bubble, Selection, Insertion, Merge, and Quick Sort, benchmarked live with timing, plus the actual sorted member order printed for verification
- **MySQL Persistence** — loads existing data on startup, and saves on demand (never automatically)
- **Graceful Offline Mode** — the entire system keeps working in-memory if the database is unreachable

---

## 🛠️ Tech Stack

| Component | Technology |
|---|---|
| Language | Java (JDK 17+ recommended) |
| Database | MySQL 8.0.46+ |
| DB Driver | MySQL Connector/J 26.7.0 |
| Interface | Command-Line Interface (CLI) |

---

## 📂 Project Structure

```
gym-membership-system-CLI/
├── database-script/
│   └── gym_db.sql                     # Run once in MySQL Workbench to set up the schema
├── docs/
│   ├── UML_Class_Diagram.png
│   ├── ER_Diagram.png
│   ├── Flowchart.png
│   └── Report.pdf
├── lib/
│   └── mysql-connector-j-26.7.0.jar
├── src/
│   ├── Main.java                      # Entry point, login screen, CLI menu
│   ├── DatabaseConnection.java        # All MySQL connect/load/save logic
│   ├── Member.java                    # Member data model
│   ├── MembershipPlan.java            # Plan data model
│   ├── Member1_ArraysLinkedList.java  # Array + Linked List + Linear Search
│   ├── Member2_StackQueue.java        # Stack (undo) + Queue (trainer queue) + Bubble Sort
│   ├── Member3_BST.java               # Binary Search Tree + Selection Sort
│   ├── Member4_AVLTree.java           # AVL Tree + Insertion Sort
│   ├── Member5_Graph.java             # Referral Graph (BFS/DFS) + Merge Sort
│   └── Member6_HashSet.java           # Hash Table + Set ADT + Quick Sort
└── README.md
```

---

## ⚙️ Setup Instructions

### 1. Set up the database

Open **MySQL Workbench** and run `database-script/gym_db.sql`. This creates the `gym_db` database, the `plans` and `members` tables (linked by a foreign key), and seeds the three fixed membership plans. Table creation happens entirely through this script — the Java code does not create tables itself.

### 2. Configure your credentials

Open `src/DatabaseConnection.java` and update these two lines to match your MySQL Workbench login:

```java
private static final String USER = "root";
private static final String PASSWORD = "your_password_here";
```

### 3. MySQL JDBC Driver

The driver jar (`mysql-connector-j-26.7.0.jar`) is already included in the `lib/` folder — no separate download needed.

> **Recommended: use IntelliJ IDEA.** Open the project folder in IntelliJ, then add the jar as a library: **File > Project Structure > Libraries > "+" > Java**, and select `lib/mysql-connector-j-26.7.0.jar`. Then just run `Main.java` directly from the editor.
>
> Command line steps are below as an alternative.

### 4. Compile (Command Line)

```bash
javac -d out src/*.java
```

This compiles everything into a separate `out/` folder instead of scattering `.class` files into `src/`.

### 5. Run (Command Line)

**Windows:**
```bash
java -cp out;lib/mysql-connector-j-26.7.0.jar Main
```

**Mac/Linux:**
```bash
java -cp out:lib/mysql-connector-j-26.7.0.jar Main
```

> If MySQL isn't running or the driver jar is missing, the program still works — it simply runs fully in-memory and shows `Cannot connect to the database.`

> If box-drawing characters (┌ ─ ┐) show as `?` marks in Command Prompt, run `chcp 65001` first, or use IntelliJ IDEA instead.

### 6. Login

The CLI opens with a login prompt. Default demo credentials:
```
Username: root
Password: root
```

---

## 📋 Menu Overview

```
1.  Register New Member         (Linked List, Hash Table, Set ADT, BST, AVL Tree, Graph)
2.  View All Members            (Linked List)
3.  Find a Member                (Hash Table)
4.  Remove a Member              (Linked List, BST, AVL Tree)
5.  Undo Last Action             (Stack)
6.  Trainer Waiting Queue        (Queue)
7.  Sort All Members             (Bubble, Selection, Insertion, Merge, Quick Sort)
8.  View Sorted Member Records   (BST, AVL Tree Traversal)
9.  Referral Program             (Graph - BFS/DFS)
10. Save All Data to Database    (MySQL)
0.  Exit
```

All registration, deletion, sorting, and searching happens **in memory only**. Nothing touches MySQL until option 10 is chosen — at that point, the entire `members` table is cleared and rewritten to exactly match what's currently held in memory.

---

## 🗄️ Database Schema

**`plans`**
| Column | Type |
|---|---|
| plan_id | INT (PK) |
| plan_name | VARCHAR(50) |
| duration_months | INT |
| fee | DOUBLE |

**`members`**
| Column | Type |
|---|---|
| member_id | INT (PK) |
| name | VARCHAR(100) |
| email | VARCHAR(100) UNIQUE |
| phone | VARCHAR(20) |
| plan_id | INT (FK → plans.plan_id) |

One plan can be linked to many members (one-to-many).

**Default plans:**
| Plan | Duration | Fee |
|---|---|---|
| Basic | 1 month | Rs. 1500 |
| Premium | 3 months | Rs. 4000 |
| VIP | 12 months | Rs. 9000 |

---

## 🧠 Data Structures & Algorithms Used

| Structure/Algorithm | Purpose | Average Time Complexity |
|---|---|---|
| Array | Fixed membership plans | O(1) access |
| Linked List | Dynamic member storage | O(n) insert/search/delete |
| Stack | Undo log | O(1) push/pop |
| Queue | Trainer waiting list | O(1) enqueue/dequeue |
| Binary Search Tree | Sorted member records | O(log n) average, O(n) worst |
| AVL Tree | Self-balancing sorted records | O(log n) guaranteed |
| Hash Table | Member lookup | O(1) average |
| Set ADT | Duplicate email prevention | O(1) average |
| Graph (Adjacency List) | Member referral network | O(V+E) traversal |
| Bubble / Selection / Insertion / Merge / Quick Sort | Sorting comparison | O(n²) to O(n log n) |

---

## 👥 Team & Contributions

| Task | Name | Student No | Component |
|---|---|---|---|
| Task 1 , 4 | H M Hirumal | CIT-25-01-0337 | Arrays, Linked List, Linear Search , Hash Table, Set ADT, Quick Sort |
| Task 2 | M D B Deneth | CIT-25-01-0574 | Stack, Queue, Bubble Sort |
| Task 3 | | - | Binary Search Tree, Selection Sort |
| Task 4 | M W R Rashmika | CIT-25-01-0356 | AVL Tree, Insertion Sort |
| Task 5 | P A A Vimod | CIT-25-01-0569 | Graph, BFS/DFS, Merge Sort |


---

## 📋 Module Information

- **Module:** CCS2300 — Data Structures and Algorithms
- **Assignment Type:** Group Project with Practical Demonstration and Viva Voce
- **Group Size:** 6 members

---

## 📄 License

This project was developed for academic purposes as part of a university coursework assignment.
