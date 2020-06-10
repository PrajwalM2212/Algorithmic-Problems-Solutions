package java_prob.LinkedList;

public class Intersection {

    class Result {
        int len;
        LinkedList.Node tail;

        Result(int len, LinkedList.Node tail) {
            this.len = len;
            this.tail = tail;
        }
    }

    private LinkedList.Node intersect(LinkedList.Node n1, LinkedList.Node n2) {

        if (n1 == null || n2 == null) {
            return null;
        }


        Result r1 = find_len_tail(n1);
        Result r2 = find_len_tail(n2);

        if (r1.tail != r2.tail) {
            return null;
        }

        int dif = Math.abs(r1.len - r2.len);

        if (r1.len > r2.len) {
            n1 = advance(n1, dif);
        } else {
            n2 = advance(n2, dif);
        }

        while (n1 != n2) {
            n1 = n1.next;
            n2 = n2.next;
        }


        return n1;

    }

    private LinkedList.Node advance(LinkedList.Node n1, int dif) {
        LinkedList.Node cur = n1;
        for (int i = 0; i < dif; i++) {
            cur = cur.next;
        }
        return n1;
    }

    private Result find_len_tail(LinkedList.Node node) {
        int len = 0;
        LinkedList.Node cur = node;
        LinkedList.Node prev = null;
        while (cur != null) {
            len++;
            prev = cur;
            cur = cur.next;
        }

        return new Result(len, prev);
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

        LinkedList.Node cur = ll.head;
        while (cur != null) {
            System.out.println(cur.data);
            cur = cur.next;
        }

    }


}
