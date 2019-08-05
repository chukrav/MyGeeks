package LinkedListsCC;

public class testMakeList {


    public static void main(String[] args) {
        ListNode L1 = new ListNode();
        L1.add(1);
        L1.add(2);
        L1.add(3);
        L1.add(4);
//        L1.printList();

        Node n1 = new Node(5);
        Node n2 = new Node(6);
        Node n3 = new Node(7);
        L1.add(n1);
        L1.add(n2);
        L1.add(n3);
        L1.printList();
        System.out.println("========================");
        ListNode L2 = new ListNode();
        L2.add(10);
        L2.add(20);
        L2.add(30);
        L2.add(40);
        L2.add(n1);
        L2.add(n2);
        L2.add(n3);
        L2.printList();
        System.out.println("========================");
        System.out.println("L1h == L2.h: " +(L1.head == L2.head));


    }

    static class Node{
        public  int data;
        public Node next;
        public Node(int n){
            data = n;
            next = null;
        }
    }

    static class ListNode{
        Node head;
        public ListNode(){
            head = null;
        }
        public void add(Node node){
            node.next = head;
            head = node;
        }

        public void add(int n){
            Node node = new Node(n);
            add(node);
        }

        public void printList(){
            Node run = head;
            while(run != null ){
                System.out.println(""+run.data);
                run = run.next;
            }
        }
    }

}
