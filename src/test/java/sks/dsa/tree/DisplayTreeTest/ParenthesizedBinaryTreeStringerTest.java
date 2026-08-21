package sks.dsa.tree.DisplayTreeTest;

import org.junit.jupiter.api.Test;
import sks.dsa.tree.bst.base.node.binaryTree.searchTree.BinarySearchNode;
import sks.dsa.tree.bst.base.node.binaryTree.searchTree.DefaultBinarySearchNodeImpl;
import sks.dsa.tree.bst.base.tree.binaryTree.searchTree.BinarySearchTree;
import sks.dsa.tree.bst.base.tree.binaryTree.searchTree.DefaultBinarySearchTreeImpl;
import sks.dsa.tree.bst.base.tree.displayTree.ParenthesizedBinaryTreeStringer;
import sks.dsa.tree.bst.base.tree.displayTree.TreeStringer;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ParenthesizedBinaryTreeStringerTest {

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
    void shouldConvertTreeToParenthesizedString() {

        BinarySearchTree<Integer> tree = createTree();

        TreeStringer<BinarySearchTree<Integer>> stringer =
                new ParenthesizedBinaryTreeStringer<>();

        String actual = stringer.stringify(tree);

        String expected =
                "4(2(1,3),6(5,7))";

        assertEquals(expected, actual);
    }

    @Test
    void shouldHandleNullTree() {

        TreeStringer<BinarySearchTree<Integer>> stringer =
                new ParenthesizedBinaryTreeStringer<>();

        String actual = stringer.stringify(null);

        assertEquals("Tree is empty.", actual);
    }

    @Test
    void shouldHandleSingleNodeTree() {

        BinarySearchNode<Integer> root =
                new DefaultBinarySearchNodeImpl<>(10);

        BinarySearchTree<Integer> tree =
                new DefaultBinarySearchTreeImpl<>(root);

        TreeStringer<BinarySearchTree<Integer>> stringer =
                new ParenthesizedBinaryTreeStringer<>();

        String actual = stringer.stringify(tree);

        assertEquals("10", actual);
    }
}