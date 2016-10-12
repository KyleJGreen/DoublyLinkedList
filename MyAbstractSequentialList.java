public abstract class MyAbstractSequentialList<E> extends MyAbstractList<E> {
  /** Create a default list */
  protected MyAbstractSequentialList() {
  }

  /** Create a list from an array of objects */
  protected MyAbstractSequentialList(E[] objects) {
    super(objects);
  }

  public java.util.Iterator<E> iterator() {
  	return listIterator();
  }
  
  public java.util.ListIterator<E> listIterator() {
  	return listIterator(0);
  }
  
  abstract public E getFirst();
  abstract public E getLast();
  abstract public void addFirst(E e);
  abstract public void addLast(E e);
  abstract public E removeFirst();
  abstract public E removeLast();
  abstract public java.util.ListIterator<E> listIterator(int index); 
}