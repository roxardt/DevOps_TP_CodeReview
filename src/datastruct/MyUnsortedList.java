package datastruct;

import java.util.Arrays;

public class MyUnsortedList<E> implements UnsortedList<E> {

    private static class Link<E> {
        final E element;
        Link<E> next;
        Link<E> previous;

        private Link(E element) {
            this.element = element;
        }
    }

    private Link<E> head;
    private Link<E> tail;
    private int size;

    private MyUnsortedList() {
        this.head = null;
        this.tail = null;
        this.size = 0;
    }

    @SafeVarargs
    public static <E> MyUnsortedList<E> of(E... elements) {
        return of(Arrays.asList(elements));
    }

    public static <E> MyUnsortedList<E> of(Iterable<E> elements) {
        MyUnsortedList<E> list = new MyUnsortedList<>();
        for (E element : elements) {
            list.append(element);
        }
        return list;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void prepend(E element) {
        size++;
        Link<E> newHead = new Link<>(element);
        newHead.next = head;
        if(head != null) head.previous = newHead;
        head = newHead;
        if(tail == null) tail = head;
    }

    @Override
    public void append(E element) {
        size++;
        Link<E> newTail = new Link<>(element);
        newTail.previous = tail;
        if(tail == null) {
        	tail = newTail;
        	head = newTail;
        } else {
            tail.next = newTail;
            tail = newTail;
        }
    }

    @Override
    public void insert(E elem, int pos) throws IndexOutOfBoundsException {
        if (pos < 0 || pos > size) {
            throw new IndexOutOfBoundsException();
        }
        if (pos == 0) {
            prepend(elem);
            return;
        }
        if (pos == size) {
            append(elem);
            return;
        }
        Link<E> prevLink = head;
        while (pos-- > 1) {
            prevLink = prevLink.next;
        }

        size++;
        Link<E> inserted = new Link<>(elem);
        Link<E> nextLink = prevLink.next;
        
        prevLink.next = inserted;
        inserted.previous = prevLink;

        inserted.next = nextLink;
        nextLink.previous = inserted;
    }

    @Override
    public E pop() {
        if (isEmpty()) {
            throw new EmptyListException();
        }

        size--;
        Link<E> oldHead = head;
        head = oldHead.next;
        if(head == null) tail = null;
        else head.previous = null;

        return oldHead.element;
    }

    @Override
    public E popLast() {
        if (isEmpty()) {
            throw new EmptyListException();
        }

        size--;
        Link<E> oldTail = tail;
        tail = oldTail.previous;
        if(tail == null) head = null;
        else tail.next = null;

        return oldTail.element;
    }

    @Override
    public E remove(int pos) throws IndexOutOfBoundsException {
        if (pos < 0 || pos >= size) {
            throw new IndexOutOfBoundsException();
        }
        if (pos == 0) {
            return pop();
        }
        if (pos == size-1) {
            return popLast();
        }

        Link<E> prevLink = head;
        while (--pos > 0) {
            prevLink = prevLink.next;
        }
        
        size--;
        
        Link<E> removed = prevLink.next;
        prevLink.next = removed.next;
        removed.next.previous = prevLink;
        return removed.element;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof MyUnsortedList)) {
            return false;
        }

        @SuppressWarnings("unchecked")
        MyUnsortedList<E> that = (MyUnsortedList<E>) obj;
        if (this.size != that.size) {
            return false;
        }

        Link<E> thisLink = this.head;
        Link<E> thatLink = that.head;
        while (thisLink != null) {
            if (thatLink == null || !thisLink.element.equals(thatLink.element)) {
                return false;
            }
            thisLink = thisLink.next;
            thatLink = thatLink.next;
        }

        return thatLink == null;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder("MyUnsortedList { size = ");
        builder.append(size);
        builder.append(", [");

        MyUnsortedList.Link<E> link = head;
        while (link != null) {
            builder.append(link.element);
            link = link.next;
            if (link != null) {
                builder.append(", ");
            }
        }

        return builder.append("] }").toString();
    }
    
    // Pour le test d'integrité
    // -1 si la liste contient un link tq link.next.previous != link
    // -2 si la tete à un précedent
    // -3 si la queue à un suivant
    // -4 si la tete ou la queue est vide
    // la taille réelle de la liste (de head à tail) sinon
    protected int isIntegrityRespected() {
    	if(head == null || tail == null) {
    		if(head == null && tail == null) return 0;
    		return -4;
    	}
    	if(head.previous != null) return -2;
    	if(tail.next != null) return -3;
    	Link<E> l = head;
    	int size = 1;
    	while(l != tail) {
    		size ++;
    		if(l.next.previous != l) return -1;
    		l = l.next;
    	}
    	return size;
    }
    
}
