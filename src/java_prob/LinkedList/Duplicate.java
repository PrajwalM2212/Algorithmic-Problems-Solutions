package java_prob.LinkedList;


import java.util.HashSet;

public class Duplicate {

    private void remove_duplicates(LinkedList.Node head) {

        LinkedList.Node current = head;
        while (current != null) {
            LinkedList.Node runner = current.next;
            LinkedList.Node previous = current;
            while (runner != null) {
                if (runner.data == current.data) {
                    previous.next = runner.next;
                }
                runner = runner.next;
            }
            current = current.next;
        }
    }

    private void remove_duplicates_buffer(LinkedList.Node head) {
        HashSet<Integer> set = new HashSet<>();
        LinkedList.Node current = head;
        LinkedList.Node previous = null;
        while (current != null) {
            if (set.contains(current.data)) {
                previous.next = current.next;
            } else {
                set.add(current.data);
                previous = current;
            }
            current = current.next;
        }
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
        Duplicate dup = new Duplicate();
        dup.remove_duplicates(ll.head);

        LinkedList.Node cur = ll.head;
        while (cur != null) {
            System.out.println(cur.data);
            cur = cur.next;
        }

    }


}
