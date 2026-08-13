package sks.dsa.tree.bst.base.iterator.binaryTree.ordered;

import org.junit.jupiter.api.Test;
import sks.dsa.tree.bst.base.node.binaryTree.searchTree.BinarySearchNode;
import sks.dsa.tree.bst.base.node.binaryTree.searchTree.DefaultBinarySearchNodeImpl;
import sks.dsa.tree.bst.base.tree.binaryTree.searchTree.BinarySearchTree;
import sks.dsa.tree.bst.base.tree.binaryTree.searchTree.DefaultBinarySearchTreeImpl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PreOrderBinaryTreeIteratorTest {

    @Test
    void testPreOrderIterator() {

        BinarySearchNode<Integer> rootNode =
                new DefaultBinarySearchNodeImpl<>(4);

        BinarySearchNode<Integer> leftNode =
                new DefaultBinarySearchNodeImpl<>(2);

        BinarySearchNode<Integer> leftLeftNode =
                new DefaultBinarySearchNodeImpl<>(1);

        BinarySearchNode<Integer> leftRightNode =
                new DefaultBinarySearchNodeImpl<>(3);

        rootNode.setLeftChild(leftNode);

        leftNode.setLeftChild(leftLeftNode);
        leftNode.setRightChild(leftRightNode);

        BinarySearchNode<Integer> rightNode =
                new DefaultBinarySearchNodeImpl<>(6);

        BinarySearchNode<Integer> rightLeftNode =
                new DefaultBinarySearchNodeImpl<>(5);

        BinarySearchNode<Integer> rightRightNode =
                new DefaultBinarySearchNodeImpl<>(7);

        rootNode.setRightChild(rightNode);

        rightNode.setLeftChild(rightLeftNode);
        rightNode.setRightChild(rightRightNode);

        BinarySearchTree<Integer> tree =
                new DefaultBinarySearchTreeImpl<>(rootNode);

        PreOrderBinaryTreeIterator<Integer> iterator =
                new PreOrderBinaryTreeIterator<>(tree);

        List<Integer> actual = new ArrayList<>();

        while (iterator.hasNext()) {
            actual.add(iterator.next().getValue());
        }

        assertEquals(
                Arrays.asList(4, 2, 1, 3, 6, 5, 7),
                actual
        );
    }
}