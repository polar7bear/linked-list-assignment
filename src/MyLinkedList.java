import java.util.Iterator;

//리스트의 각 Node를 나타내는 클래스
class Node<T> {
    T data;
    Node<T> next;

    public Node(T data) {
        this.data = data;
        this.next = null;
    }
}

//LinkedList 클래스
public class MyLinkedList<T> implements Iterable<T> {
    private Node<T> head;

    //add
    public void add(T data) {
        Node<T> newNode = new Node<>(data);

        if (head == null) {
            head = newNode; //첫번째 요소에 값이 없다면 newNode 추가
        } else {
            Node<T> current = head;

            while (current.next != null) { //빈 요소를 찾을때까지 순회
                current = current.next;
            }
            current.next = newNode;
        }
    }

    //get
    public T get(int index) {
        if (index < 0) {
            throw new IndexOutOfBoundsException();
        }
        Node<T> current = head;
        int currentIndex = 0;

        while (current != null) {
            if (index == currentIndex) {
                return current.data;
            }

            current = current.next;
            currentIndex++;
        }

        throw new IndexOutOfBoundsException();
    }

    //delete
    public void delete(int index) {
        if (index < 0) {
            throw new IndexOutOfBoundsException();
        }
        if (head == null) {
            throw new IndexOutOfBoundsException();
        }

        if (index == 0) {
            head = head.next;
            return;
        }
        Node<T> current = head;
        int currentIndex = 0;

        while (current != null && currentIndex < index - 1) {
            current = current.next;
            currentIndex++;
        }

        //인덱스가 리스트의 크기를 초과하는 경우
        if (current == null || current.next == null) {
            throw new IndexOutOfBoundsException();
        }

        current.next = current.next.next;
    }

    private class MyLinkedListIterator implements Iterator<T> {
        private Node<T> current = head;

        @Override
        public boolean hasNext() {
            return current != null; //null이 아니라면 리턴
        }

        @Override
        public T next() {
            T data = current.data;
            current = current.next; //data를 현재의 data로 대입하고, next를 한번 더 사용했을 때 그다음 값이 나와야 하기 때문에 현재요소를 next값으로 저장
            return data;
        }
    }

    @Override
    public Iterator<T> iterator() {
        return new MyLinkedListIterator();
    }

    public static void main(String[] args) {
        MyLinkedList<Integer> list = new MyLinkedList<>();

        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);
        list.add(6);
        int get3 = list.get(3);
        list.delete(1);
        int afterDeleteGet3 = list.get(3);

        for (int add : list) {
            System.out.print("add = " + add + " ");
        }
        System.out.println();
        System.out.println("get3 = " + get3);
        System.out.println("afterDeleteGet3 = " + afterDeleteGet3);
    }
}

