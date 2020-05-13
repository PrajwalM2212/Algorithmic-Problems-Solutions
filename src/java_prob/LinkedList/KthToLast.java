package java_prob.LinkedList;

public class KthToLast {

    // iterative
    private LinkedList.Node kth_iterative(LinkedList.Node head, int k){
        LinkedList.Node p1 = head;
        LinkedList.Node p2 = head;

        for(int i=0; i<k; i++){
            if(p1 == null){
                return null;
            }
            p1 = p1.next;
        }

        while(p1 != null){
            p1 = p1.next;
            p2 = p2.next;
        }

        return p2;
    }

    public static void main(String[] args){
        LinkedList ll = new LinkedList();
        ll.head = new LinkedList.Node(4);
        LinkedList.Node n1 = new LinkedList.Node(3);
        ll.head.next = n1;
        LinkedList.Node n2 = new LinkedList.Node(5);
        n1.next = n2;
        LinkedList.Node n3 = new LinkedList.Node(2);
        n2.next = n3;
        LinkedList.Node n4 = new LinkedList.Node(1);
        n3.next = n4;

        KthToLast kthToLast = new KthToLast();
        System.out.println(kthToLast.kth_iterative(ll.head, 3).data);
    }
}
