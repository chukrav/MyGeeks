package LinkedListsCC;

public class LinkedListCC {

    private LinkedListNode head;

    public LinkedListCC(){
        head = null;
    }

    public LinkedListNode addToList(LinkedListNode node){
        if (head == null) {
            head = node;
        } else {
            LinkedListNode tmp = head;
            while(tmp.next != null){
                tmp = tmp.next;
            }
            tmp.next = node;
        }
        return  head;
    }

    public LinkedListNode getHead() {
        return head;
    }

    public void printList(LinkedListNode node){
        while (node != null){
            System.out.println("  "+node.data);
            node = node.next;
        }
    }

    public void printList(){
        LinkedListNode node = head;
        while (node != null){
            System.out.println("  "+node.data);
            node = node.next;
        }
    }

}
