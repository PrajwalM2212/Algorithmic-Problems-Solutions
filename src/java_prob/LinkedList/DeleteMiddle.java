package java_prob.LinkedList;

public class DeleteMiddle {

    private boolean delete_middle(LinkedList.Node middle){
        if(middle == null || middle.next == null){
            return false;
        }

        LinkedList.Node next = middle.next;
        middle.data = next.data;
        middle.next = next.next;
        return true;
    }


    public static void main(String[] args) {
        LinkedList ll = new LinkedList();
        ll.head = new LinkedList.Node(4);
        LinkedList.Node n1 = new LinkedList.Node(3);
        ll.head.next = n1;
        LinkedList.Node n2 = new LinkedList.Node(3);
        n1.next = n2;
        LinkedList.Node n3 = new LinkedList.Node(4);
        n2.next = n3;
        LinkedList.Node n4 = new LinkedList.Node(3);
        n3.next = n4;
        DeleteMiddle deleteMiddle = new DeleteMiddle();
        deleteMiddle.delete_middle(n2);

        LinkedList.Node cur = ll.head;
        while (cur != null) {
            System.out.println(cur.data);
            cur = cur.next;
        }

    }

}
