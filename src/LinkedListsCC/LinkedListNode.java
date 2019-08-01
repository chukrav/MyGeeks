package LinkedListsCC;

public class LinkedListNode {
    public int data;
    public LinkedListNode next;
    public LinkedListNode(int num){
        data = num;
        next = null;
    }
    public LinkedListNode(){
        data = 0;
        next = null;
    }

    public void setNext(LinkedListNode more) {
        next = more;
    }
}
