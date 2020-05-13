package java_prob.LinkedList;

public class Partition {

    // stable partition
    private LinkedList.Node partition_stable(LinkedList.Node head, int x){

        LinkedList.Node current = head;
        LinkedList.Node afterStart = null;
        LinkedList.Node afterEnd = null;
        LinkedList.Node beforeStart = null;
        LinkedList.Node beforeEnd = null;

        while (current != null){
            LinkedList.Node next = current.next;
            current.next = null;

            if(current.data < x){
                if(beforeStart == null){
                    beforeStart = current;
                    beforeEnd = current;
                }else{
                    beforeEnd.next = current;
                    beforeEnd = current;
                }
            }else{
                if(afterStart == null){
                    afterStart = current;
                    afterEnd = current;
                }else{
                    afterEnd.next = current;
                    afterEnd = current;
                }
            }

            current = next;
        }

        if(beforeStart == null){
            return afterStart;
        }

        beforeEnd.next = afterStart;
        return beforeStart;
    }

    // unstable partition
    private LinkedList.Node partition_unstable(LinkedList.Node node, int x){

        LinkedList.Node head = node;
        LinkedList.Node tail = node;

        node = node.next;
        while(node != null){
            LinkedList.Node next = node.next;
            if(node.data < x){
                node.next = head;
                head = node;
            }else{
                tail.next = node;
                tail = node;
            }
            node = next;
        }

        return head;

    }


    public static void main(String[] args){

        LinkedList ll = new LinkedList();
        ll.head = new LinkedList.Node(4);
        LinkedList.Node n1 = new LinkedList.Node(5);
        ll.head.next = n1;
        LinkedList.Node n2 = new LinkedList.Node(2);
        n1.next = n2;
        LinkedList.Node n3 = new LinkedList.Node(1);
        n2.next = n3;
        LinkedList.Node n4 = new LinkedList.Node(6);
        n3.next = n4;
        Partition partition =  new Partition();
        LinkedList.Node newHead = partition.partition_unstable(ll.head, 3);

        LinkedList.Node cur = newHead;
        while (cur != null) {
            System.out.println(cur.data);
            cur = cur.next;
        }

    }

}
