import java.util.ArrayList;
import java.util.List;

public class Tree<T> { 
    public static class Node<E>  {
        private E value;
        private Node<E> parent;
        private List<Node<E>> children;

        public Node(E value, Node<E> parent) {
            this.value = value;
            this.parent = parent;
            this.children = new ArrayList<Node<E>>();
        }
    }
}