import java.util.Iterator;

public class DoubleLinkedList implements GeekBrainsList{
    private Node head;


    @Override
    public void add(String o) {
        if (head == null) {
            head = new Node(o);
            return;
        }

        add(o, head);
    }

    private void add(String o, Node current) {
        if (current.getNext() == null) {
            current.setNext(new Node(o, current, null));
            return;
        }
        add(o, current.getNext());
    }

    @Override
    public void remove(String o) {
        if (head == null) {
            return;
        } else {
            if (head.getInfo().equals(o)) {
                head = head.getNext();
                head.prev = null;
                return;
            }
        }

        remove(head, head.getNext(), o);
    }


    private void remove(Node previous, Node current, String o) {
        if(current == null ) {
            return;
        }

        if(current.getInfo().equals(o)) {
            previous.setNext(current.getNext());
            current.getNext().setPrev(previous);
            return;
        }

        remove(current, current.getNext(), o);
    }
    public Iterator<Node> iterator () {
        Iterator<Node> iterator = new Iterator<>() {
            private Node currentHead = head;

            @Override
            public Node next() {
                if (this.hasNext()) {
                    currentHead = head.getNext();
                    return head.getNext();
                }
                else return null;
            }

            @Override
            public boolean hasNext() {
                if(head.getNext() != null)return true;
                else return false;
            }

            @Override
            public void remove() {
                DoubleLinkedList.this.remove(currentHead.getInfo());

            }
        };
        return iterator;
    }



    private static class Node {
        String info;
        Node prev;
        Node next;


        public Node(String info) {
            this.info = info;
            this.prev = null;
            this.next = null;
        }

        public Node(String info, Node prev, Node next) {
            this.info = info;
            this.prev = prev;
            this.next = next;

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
            if(prev != null && next != null) {
                return "Node{" +
                        "info='" + info + '\'' +
                        ", prev=" + prev.info +
                        ", next=" + next +
                        '}';
            } else if(prev != null) {
                return "Node{" +
                        "info='" + info + '\'' +
                        ", prev=" + prev.info +
                        ", next=" + null +
                        '}';
            } else  {
                return "Node{" +
                        "info='" + info + '\'' +
                        ", prev=" + null +
                        ", next=" + next +
                        '}';
            }
        }
    }

    @Override
    public String toString() {
        return "{" +
                "head=" + head +
                '}';
    }
}
