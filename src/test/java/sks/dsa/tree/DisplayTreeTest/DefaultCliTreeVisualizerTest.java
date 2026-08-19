package sks.dsa.tree.bst.base.tree.displayTree;

import org.junit.jupiter.api.Test;
import sks.dsa.tree.bst.base.node.binaryTree.searchTree.BinarySearchNode;
import sks.dsa.tree.bst.base.node.binaryTree.searchTree.DefaultBinarySearchNodeImpl;
import sks.dsa.tree.bst.base.tree.binaryTree.searchTree.BinarySearchTree;
import sks.dsa.tree.bst.base.tree.binaryTree.searchTree.DefaultBinarySearchTreeImpl;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DefaultCliTreeVisualizerTest {

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
    void shouldDisplayTreeOnCli() {

        BinarySearchTree<Integer> tree = createTree();

        DefaultCliTreeVisualizer<Integer> visualizer =
                new DefaultCliTreeVisualizer<>();

        visualizer.visualizeTree(tree);
    }
}
