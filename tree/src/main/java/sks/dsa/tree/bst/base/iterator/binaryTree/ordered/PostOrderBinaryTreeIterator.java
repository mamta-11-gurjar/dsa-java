package sks.dsa.tree.bst.base.iterator.binaryTree.ordered;

import sks.dsa.tree.bst.base.iterator.binaryTree.AbstractBinaryTreeIterator;
import sks.dsa.tree.bst.base.iterator.binaryTree.BinaryTreeIterator;
import sks.dsa.tree.bst.base.node.binaryTree.BinaryNode;
import sks.dsa.tree.bst.base.tree.binaryTree.BinaryTree;

import java.util.Collection;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Stack;

public class PostOrderBinaryTreeIterator<ValueType>
        extends AbstractBinaryTreeIterator<ValueType>
        implements BinaryTreeIterator<ValueType>
{
    private final Stack<BinaryNode<ValueType>> traversalStack = new Stack<>();
    private final Stack<BinaryNode<ValueType>> outputStack = new Stack<>();
    private int position = 0;
    private boolean initialized = false;

    public PostOrderBinaryTreeIterator(BinaryTree<ValueType> tree) {
        super(tree);
    }

    private void initialize() {
        if (getRoot() != null) {
            traversalStack.push(getRoot());

            while (!traversalStack.isEmpty()) {
                BinaryNode<ValueType> currentNode = traversalStack.pop();
                outputStack.push(currentNode);

                if (currentNode.getLeftChild() != null) {
                    traversalStack.push(currentNode.getLeftChild());
                }
                if (currentNode.getRightChild() != null) {
                    traversalStack.push(currentNode.getRightChild());
                }
            }
        }
        initialized = true;
    }

    @Override
    public boolean hasNext() {
        if (!initialized) {
            initialize();
        }
        return !outputStack.isEmpty();
    }

    @Override
    public BinaryNode<ValueType> next() {
        if (!hasNext()) {
            throw new NoSuchElementException("No more elements in the tree traversal");
        }

        BinaryNode<ValueType> currentNode = outputStack.pop();
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
