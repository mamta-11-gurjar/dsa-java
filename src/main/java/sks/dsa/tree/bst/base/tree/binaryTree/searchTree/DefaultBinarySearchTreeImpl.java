package sks.dsa.tree.bst.base.tree.binaryTree.searchTree;

import sks.dsa.tree.bst.base.iterator.binaryTree.ordered.InOrderBinaryTreeIterator;
import sks.dsa.tree.bst.base.node.binaryTree.BinaryNode;
import sks.dsa.tree.bst.base.node.binaryTree.searchTree.BinarySearchNode;

import java.util.Iterator;

public class DefaultBinarySearchTreeImpl<ValueType extends Comparable<ValueType>> implements BinarySearchTree<ValueType>
{
    private final BinarySearchNode<ValueType> rootNode;

    public DefaultBinarySearchTreeImpl(final BinarySearchNode<ValueType> rootNode) {
        this.rootNode = rootNode;
    }

    public DefaultBinarySearchTreeImpl(final BinarySearchTree<ValueType> otherTree) {
        this.rootNode = (BinarySearchNode<ValueType>) otherTree.getRoot();
    }

    @Override
    public BinarySearchNode<ValueType> getRoot() {
        return rootNode;
    }

    @Override
    public int getMaxTreeDepth() {
        return 0;
    }

    @Override
    public int getMaxTreeHeight() {
        return getRoot().getHeight();
    }

    @Override
    public int getNumberOfChildren() {
        return getRoot().numberOfChildren();
    }

    @Override
    public int size() {
        return getRoot().numberOfChildren()+1;
    }

    @Override
    public boolean isTreeBalanced() {
        return false;
    }

    @Override
    public boolean isTreeComplete() {
        return false;
    }

    @Override
    public BinarySearchTree<ValueType> getRightSubTree() {
        return new DefaultBinarySearchTreeImpl<>((BinarySearchNode<ValueType>) getRoot().getRightChild());
    }

    @Override
    public BinarySearchTree<ValueType> getLeftSubTree() {
        return new DefaultBinarySearchTreeImpl<>((BinarySearchNode<ValueType>) getRoot().getLeftChild());
    }

    @Override
    public Iterator<BinaryNode<ValueType>> iterator() {
        return new InOrderBinaryTreeIterator<>(this);
    }
}
