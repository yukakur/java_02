public class DoubleLinkedList implements GeekBrainsList{
    private Node head;



    @Override
    public void add(String o) {
        if(head == null) {
            head = new Node(o);
            return;
        }

        add(o, head);
    }
    private void add(String o, Node current) {
        if(current.getNext() == null) {
            current.setNext(new Node(current, null, o));
            return;
        }
        add(o, current.getNext());
    }

    @Override
    public void remove(String o) {
        if(head == null) {
            return;
        } else {
            if(head.getInfo().equals(o)) {
                head = head.getNext();
                head.prev = null;
                return;
            }
        }
        Node current = head;
        Node previous = head;
        while((current = current.getNext()) != null) {
            if(current.getInfo().equals(o)) {
                previous = current;
                current = current.getNext();
                return;
            }
            previous = current;
            current = current.getNext();
        }

    }

    private static class Node {

        Node prev;
        Node next;
        String info;

        public Node(String info) {
            this.info = info;
            this.prev = null;
            this.next = null;
        }

        public Node(Node prev, Node next, String info) {
            this.prev = prev;
            this.next = next;
            this.info = info;
        }

        public Node getPrev() {
            return prev;
        }

        public void setPrev(Node prev) {
            this.prev = prev;
        }

        public Node getNext() {
            return next;
        }

        public void setNext(Node next) {
            this.next = next;
        }

        public String getInfo() {
            return info;
        }

        public void setInfo(String info) {
            this.info = info;
        }

        @Override
        public String toString() {
            if(prev.getPrev() != null) {
                return "Node{" +
                        "info=" + info +
                        ", next=" + next +
                        ", prev='" + toString(getPrev().prev) + '\'' +
                        '}';
            }
            return "Node{" +
                    "info=" + info +
                    ", next=" + next +
                    ", prev='" + "null" + '\'' +
                    '}';
        }
        private String toString(Node prev) {

            if(prev.getPrev() != null) {
                return "Node{" +
                        "info=" + info +
                        ", next=" + next +
                        ", prev='" + toString(getPrev().next) + '\'' +
                        '}';
            }
            return null;
        }
    }

    @Override
    public String toString() {
        return "{" +
                "head=" + head +
                '}';
    }
}
