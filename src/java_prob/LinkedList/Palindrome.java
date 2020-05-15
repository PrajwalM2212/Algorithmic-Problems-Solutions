package java_prob.LinkedList;

import java.util.Stack;

public class Palindrome {

    // reverse and compare
    private boolean reverse_and_check(LinkedList.Node node) {

        LinkedList.Node reverse = clone_reverse(node);

        while (node != null && reverse != null) {
            if (node.data != reverse.data) {
                return false;
            }
            node = node.next;
            reverse = reverse.next;
        }

        return node == null && reverse == null;

    }

    private LinkedList.Node clone_reverse(LinkedList.Node node) {

        LinkedList.Node head = null;

        while (node != null) {
            LinkedList.Node clone = new LinkedList.Node(node.data);
            clone.next = head;
            head = clone;
            node = node.next;
        }

        return head;
    }


    // store first half in stack, find middle and compare the remaining half with the stack contents
    private boolean compare(LinkedList.Node node) {
        LinkedList.Node fast = node;
        LinkedList.Node slow = node;
        Stack<Integer> stack = new Stack<>();

        while (fast != null && fast.next != null) {
            stack.add(slow.data);
            slow = slow.next;
            fast = fast.next.next;
        }


        if(fast != null){
            slow = slow.next;
        }


        while (slow != null){
            int top = stack.pop();
            if(top != slow.data){
                return false;
            }
            slow = slow.next;
        }

        return true;
    }


    public static void main(String[] args) {
        LinkedList ll = new LinkedList();
        ll.head = new LinkedList.Node(4);
        LinkedList.Node n1 = new LinkedList.Node(3);
        ll.head.next = n1;
        LinkedList.Node n2 = new LinkedList.Node(3);
        n1.next = n2;
        LinkedList.Node n3 = new LinkedList.Node(3);
        n2.next = n3;
        LinkedList.Node n4 = new LinkedList.Node(4);
        n3.next = n4;
        Palindrome palindrome = new Palindrome();
        System.out.println(palindrome.compare(ll.head));
    }

}
