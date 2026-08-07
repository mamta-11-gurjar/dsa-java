package sks.dsa.tree.bst.base.iterator.binaryTree.ordered;

import sks.dsa.tree.bst.base.iterator.binaryTree.AbstractBinaryTreeIterator;
import sks.dsa.tree.bst.base.iterator.binaryTree.BinaryTreeIterator;
import sks.dsa.tree.bst.base.node.binaryTree.BinaryNode;
import sks.dsa.tree.bst.base.tree.binaryTree.BinaryTree;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Queue;

public class LevelOrderBinaryTreeIterator<ValueType>
        extends AbstractBinaryTreeIterator<ValueType>
        implements BinaryTreeIterator<ValueType>
{
    private final Queue<BinaryNode<ValueType>> traversalQueue = new LinkedList<>();
    private int position = 0;

    public LevelOrderBinaryTreeIterator(BinaryTree<ValueType> tree) {
        super(tree);
        if (getRoot() != null) {
            traversalQueue.add(getRoot());
        }
    }

    @Override
    public boolean hasNext() {
        return !traversalQueue.isEmpty();
    }

    @Override
    public BinaryNode<ValueType> next() {
        if (!hasNext()) {
            throw new NoSuchElementException("No more elements in the tree traversal");
        }

        BinaryNode<ValueType> currentNode = traversalQueue.poll();

        if (currentNode.getLeftChild() != null) {
            traversalQueue.add(currentNode.getLeftChild());
        }
        if (currentNode.getRightChild() != null) {
            traversalQueue.add(currentNode.getRightChild());
        }

        position++;
        return currentNode;
    }

    @Override
    public void buildTreeFromSequence(final ValueType[] values) {
    }

    @Override
    public void buildTreeFromSequence(BinaryNode<ValueType>[] nodes) {
    }

    @Override
    public void buildTreeFromSequence(final Collection<ValueType> values) {
    }

    @Override
    public void buildTreeFromSequence(List<BinaryNode<ValueType>> nodes) {
    }
}
