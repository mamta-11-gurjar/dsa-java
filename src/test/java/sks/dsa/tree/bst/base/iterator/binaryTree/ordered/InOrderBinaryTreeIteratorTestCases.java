package sks.dsa.tree.bst.base.iterator.binaryTree.ordered;

import org.junit.jupiter.api.Test;
import sks.dsa.tree.bst.base.node.binaryTree.BinaryNode;
import sks.dsa.tree.bst.base.node.binaryTree.searchTree.BinarySearchNode;
import sks.dsa.tree.bst.base.node.binaryTree.searchTree.DefaultBinarySearchNodeImpl;
import sks.dsa.tree.bst.base.tree.binaryTree.searchTree.BinarySearchTree;
import sks.dsa.tree.bst.base.tree.binaryTree.searchTree.DefaultBinarySearchTreeImpl;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class InOrderBinaryTreeIteratorTest {

    @Test
    void testIterationOrder() {

        BinarySearchNode<Integer> rootNode = new DefaultBinarySearchNodeImpl<>(4);
        BinarySearchNode<Integer> leftNode = new DefaultBinarySearchNodeImpl<>(2);
        BinarySearchNode<Integer> leftLeftNode = new DefaultBinarySearchNodeImpl<>(1);
        BinarySearchNode<Integer> leftRightNode = new DefaultBinarySearchNodeImpl<>(3);

        rootNode.setLeftChild(leftNode);
        leftNode.setLeftChild(leftLeftNode);
        leftNode.setRightChild(leftRightNode);

        BinarySearchNode<Integer> rightNode = new DefaultBinarySearchNodeImpl<>(6);
        BinarySearchNode<Integer> rightLeftNode = new DefaultBinarySearchNodeImpl<>(5);
        BinarySearchNode<Integer> rightRightNode = new DefaultBinarySearchNodeImpl<>(7);

        rootNode.setRightChild(rightNode);
        rightNode.setLeftChild(rightLeftNode);
        rightNode.setRightChild(rightRightNode);

        BinarySearchTree<Integer> tree =
                new DefaultBinarySearchTreeImpl<>(rootNode);

        List<Integer> result = new ArrayList<>();

        for (BinaryNode<Integer> node : tree) {
            result.add(node.getValue());
        }

        assertEquals(List.of(1, 2, 3, 4, 5, 6, 7), result);
    }

    @Test
    void testSingleNodeTree() {

        BinarySearchNode<Integer> rootNode =
                new DefaultBinarySearchNodeImpl<>(10);

        BinarySearchTree<Integer> tree =
                new DefaultBinarySearchTreeImpl<>(rootNode);

        List<Integer> result = new ArrayList<>();

        for (BinaryNode<Integer> node : tree) {
            result.add(node.getValue());
        }

        assertEquals(List.of(10), result);
    }

    @Test
    void testLeftSkewedTree() {

        BinarySearchNode<Integer> root = new DefaultBinarySearchNodeImpl<>(4);
        BinarySearchNode<Integer> node3 = new DefaultBinarySearchNodeImpl<>(3);
        BinarySearchNode<Integer> node2 = new DefaultBinarySearchNodeImpl<>(2);
        BinarySearchNode<Integer> node1 = new DefaultBinarySearchNodeImpl<>(1);

        root.setLeftChild(node3);
        node3.setLeftChild(node2);
        node2.setLeftChild(node1);

        BinarySearchTree<Integer> tree =
                new DefaultBinarySearchTreeImpl<>(root);

        List<Integer> result = new ArrayList<>();

        for (BinaryNode<Integer> node : tree) {
            result.add(node.getValue());
        }

        assertEquals(List.of(1, 2, 3, 4), result);
    }

    @Test
    void testRightSkewedTree() {

        BinarySearchNode<Integer> root = new DefaultBinarySearchNodeImpl<>(1);
        BinarySearchNode<Integer> node2 = new DefaultBinarySearchNodeImpl<>(2);
        BinarySearchNode<Integer> node3 = new DefaultBinarySearchNodeImpl<>(3);
        BinarySearchNode<Integer> node4 = new DefaultBinarySearchNodeImpl<>(4);

        root.setRightChild(node2);
        node2.setRightChild(node3);
        node3.setRightChild(node4);

        BinarySearchTree<Integer> tree =
                new DefaultBinarySearchTreeImpl<>(root);

        List<Integer> result = new ArrayList<>();

        for (BinaryNode<Integer> node : tree) {
            result.add(node.getValue());
        }

        assertEquals(List.of(1, 2, 3, 4), result);
    }
}
