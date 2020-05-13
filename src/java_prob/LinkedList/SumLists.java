package java_prob.LinkedList;

public class SumLists {

    // iterative
    private LinkedList.Node sum_iterative(LinkedList.Node n1, LinkedList.Node n2) {

        LinkedList.Node result = null;
        LinkedList.Node head = null;
        int carry = 0;

        while (n1 != null || n2 != null || carry != 0) {

            int sum = carry;

            if (n1 != null) {
                sum += n1.data;
                n1 = n1.next;
            }

            if (n2 != null) {
                sum += n2.data;
                n2 = n2.next;
            }


            if (result == null) {
                result = new LinkedList.Node(sum % 10);
                head = result;
            } else {
                result.next = new LinkedList.Node(sum % 10);
                result = result.next;
            }
            carry = sum > 10 ? 1 : 0;

        }

        return head;
    }

    // recursive
    private LinkedList.Node sum_recursive(LinkedList.Node n1, LinkedList.Node n2, int carry) {

        if(n1 == null && n2 == null && carry == 0){
            return null;
        }

        int sum = carry;

        if (n1 != null) {
            sum += n1.data;
        }

        if (n2 != null) {
            sum += n2.data;
        }

        LinkedList.Node result = new LinkedList.Node(sum % 10);

        result.next = sum_recursive(n1 == null ? null : n1.next,
                n2 == null ? null : n2.next,
                sum > 10 ? 1 : 0
        );
        return result;

    }

    public static void main(String args[]) {
        LinkedList ll = new LinkedList();
        ll.head = new LinkedList.Node(4);
        ll.head.next = new LinkedList.Node(3);

        LinkedList ll1 = new LinkedList();
        ll1.head = new LinkedList.Node(3);
        ll1.head.next = new LinkedList.Node(8);

        SumLists sumLists = new SumLists();
        LinkedList.Node result = sumLists.sum_recursive(ll.head, ll1.head, 0);

        while (result != null) {
            System.out.println(result.data);
            result = result.next;
        }

    }
}
