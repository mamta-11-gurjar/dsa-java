package sks.dsa.tree.bst.base.node.binaryTree.searchTree;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DefaultBinarySearchNodeImplTest
{
    @Test
    public void testForBaseCorrectness()
    {
        BinarySearchNode<Integer> rootNode = new DefaultBinarySearchNodeImpl<>(4);
        assertEquals(4, rootNode.getValue());
        assertEquals(1, rootNode.getHeight());
        assertEquals(0, rootNode.numberOfNonNullImmediateChildren());
        assertEquals(0, rootNode.numberOfChildren());
        assertNull(rootNode.getLeftChild());
        assertNull(rootNode.getRightChild());
        assertFalse(rootNode.hasNext());
        assertEquals(2, rootNode.maxNumberOfNextPointers());
    }

    @Test
    public void testForNextChildrenProperties()
    {
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

        assertEquals(3, rootNode.getHeight());
        assertEquals(2, rootNode.getRightChild().getHeight());

        assertEquals(0, leftLeftNode.numberOfChildren());
        assertEquals(0, leftRightNode.numberOfChildren());
        assertEquals(2, leftNode.numberOfChildren());
        assertEquals(2, rightNode.numberOfChildren());
        assertEquals(6, rootNode.numberOfChildren());
    }

    @Test
    public void testForNextChildrenPropertiesAndHeights()
    {
        BinarySearchNode<Integer> rootNode = new DefaultBinarySearchNodeImpl<>(4);
        BinarySearchNode<Integer> leftNode = new DefaultBinarySearchNodeImpl<>(2);
        BinarySearchNode<Integer> leftLeftNode = new DefaultBinarySearchNodeImpl<>(1);
        BinarySearchNode<Integer> leftRightNode = new DefaultBinarySearchNodeImpl<>(3);

        rootNode.setLeftChild(leftNode);
        leftNode.setLeftChild(leftLeftNode);
        leftNode.setRightChild(leftRightNode);

        BinarySearchNode<Integer> rightNode = new DefaultBinarySearchNodeImpl<>(6);
        BinarySearchNode<Integer> rightLeftNode = new DefaultBinarySearchNodeImpl<>(5);

        rootNode.setRightChild(rightNode);
        rightNode.setLeftChild(rightLeftNode);

        assertEquals(3, rootNode.getHeight());
        assertEquals(2, rootNode.getRightChild().getHeight());

        assertEquals(0, leftLeftNode.numberOfChildren());
        assertEquals(0, leftRightNode.numberOfChildren());
        assertEquals(2, leftNode.numberOfChildren());
        assertEquals(1, rightNode.numberOfChildren());
        assertEquals(5, rootNode.numberOfChildren());
    }
}