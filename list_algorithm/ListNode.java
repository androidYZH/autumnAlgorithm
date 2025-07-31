package list_algorithm;

public class ListNode {
    ListNode next;
    int val;
    public ListNode(){}
    public ListNode(int data){
        this.val = data;
    }

    public ListNode getNext() {
        return next;
    }

    public void setNext(ListNode next) {
        this.next = next;
    }

    public int getData() {
        return val;
    }

    public void setData(int data) {
        this.val = data;
    }
}
