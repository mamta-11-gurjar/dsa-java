package sks.dsa.tree.bst.base.iterator.binaryTree.ordered;

import org.junit.jupiter.api.Test;
import sks.dsa.tree.bst.base.node.binaryTree.searchTree.BinarySearchNode;
import sks.dsa.tree.bst.base.node.binaryTree.searchTree.DefaultBinarySearchNodeImpl;
import sks.dsa.tree.bst.base.tree.binaryTree.searchTree.BinarySearchTree;
import sks.dsa.tree.bst.base.tree.binaryTree.searchTree.DefaultBinarySearchTreeImpl;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MultipleIteratorTest {

    private BinarySearchTree<Integer> createTree() {

        BinarySearchNode<Integer> root =
                new DefaultBinarySearchNodeImpl<>(4);

        BinarySearchNode<Integer> node2 =
                new DefaultBinarySearchNodeImpl<>(2);

        BinarySearchNode<Integer> node1 =
                new DefaultBinarySearchNodeImpl<>(1);

        BinarySearchNode<Integer> node3 =
                new DefaultBinarySearchNodeImpl<>(3);

        BinarySearchNode<Integer> node6 =
                new DefaultBinarySearchNodeImpl<>(6);

        BinarySearchNode<Integer> node5 =
                new DefaultBinarySearchNodeImpl<>(5);

        BinarySearchNode<Integer> node7 =
                new DefaultBinarySearchNodeImpl<>(7);

        root.setLeftChild(node2);
        root.setRightChild(node6);

        node2.setLeftChild(node1);
        node2.setRightChild(node3);

        node6.setLeftChild(node5);
        node6.setRightChild(node7);

        return new DefaultBinarySearchTreeImpl<>(root);
    }


    @Test
    void testOneIteratorDoesNotAffectAnother() {

        BinarySearchTree<Integer> tree = createTree();

        PreOrderBinaryTreeIterator<Integer> preOrder =
                new PreOrderBinaryTreeIterator<>(tree);

        PostOrderBinaryTreeIterator<Integer> postOrder =
                new PostOrderBinaryTreeIterator<>(tree);

        assertEquals(4, preOrder.next().getValue());
        assertEquals(2, preOrder.next().getValue());

        assertEquals(1, postOrder.next().getValue());
        assertEquals(3, postOrder.next().getValue());

        assertEquals(1, preOrder.next().getValue());

        assertEquals(2, postOrder.next().getValue());
    }

    @Test
    void testIteratorsCanBePausedAndResumed() {

        BinarySearchTree<Integer> tree = createTree();

        PreOrderBinaryTreeIterator<Integer> preOrder =
                new PreOrderBinaryTreeIterator<>(tree);

        PostOrderBinaryTreeIterator<Integer> postOrder =
                new PostOrderBinaryTreeIterator<>(tree);

        InOrderBinaryTreeIterator<Integer> InOrder =
                new InOrderBinaryTreeIterator<>(tree);


        assertEquals(4, preOrder.next().getValue());
        assertEquals(2, preOrder.next().getValue());

        assertEquals(1, postOrder.next().getValue());
        assertEquals(3, postOrder.next().getValue());
        
        assertEquals(1, preOrder.next().getValue());
        assertEquals(3, preOrder.next().getValue());

        assertEquals(2, postOrder.next().getValue());
        assertEquals(5, postOrder.next().getValue());
    }
}