package java_prob.LinkedList;

public class SumLists {
    // backward sum
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


    // forward sum
    class PartialSum{
        LinkedList.Node sum = null;
        int carry = 0;
    }

    private LinkedList.Node forward_sum(LinkedList.Node n1, LinkedList.Node n2){

        int l1 = get_len(n1);
        int l2 = get_len(n2);

        if(l1 > l2){
            n2 = add_padding(n2, l1-l2);
        }else{
            n1 = add_padding(n1, l2-l1);
        }

        PartialSum sum = add_lists(n1, n2);

        if(sum.carry != 0){
            return insertBefore(sum.sum, sum.carry);
        }else{
            return sum.sum;
        }

    }

    private PartialSum add_lists(LinkedList.Node n1, LinkedList.Node n2) {

        if(n1 == null && n2 == null){
            return new PartialSum();
        }

        PartialSum sum = add_lists(n1.next, n2.next);

        int val = n1.data + n2.data + sum.carry;

        sum.sum = insertBefore(sum.sum, val % 10);
        sum.carry = val / 10;

        return sum;
    }


    private LinkedList.Node add_padding(LinkedList.Node node, int len) {
        for(int i=0; i<len; i++){
            node = insertBefore(node, 0);
        }
        return node;
    }

    private LinkedList.Node insertBefore(LinkedList.Node node, int data) {

        if(node == null){
            return new LinkedList.Node(data);
        }

        LinkedList.Node head = new LinkedList.Node(data);
        head.next = node;

        return head;
    }

    private int get_len(LinkedList.Node n1) {
        int i = 0;
        while(n1 != null){
            i++;
            n1 = n1.next;
        }
        return i;
    }

    public static void main(String args[]) {
        LinkedList ll = new LinkedList();
        ll.head = new LinkedList.Node(4);
        ll.head.next = new LinkedList.Node(3);

        LinkedList ll1 = new LinkedList();
        ll1.head = new LinkedList.Node(3);
        ll1.head.next = new LinkedList.Node(8);

        SumLists sumLists = new SumLists();
        LinkedList.Node result = sumLists.forward_sum(ll.head, ll1.head);

        while (result != null) {
            System.out.println(result.data);
            result = result.next;
        }

    }
}
