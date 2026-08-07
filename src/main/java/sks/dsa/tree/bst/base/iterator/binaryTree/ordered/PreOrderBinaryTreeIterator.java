
package sks.dsa.tree.bst.base.iterator.binaryTree.ordered;

import sks.dsa.tree.bst.base.iterator.binaryTree.AbstractBinaryTreeIterator;
import sks.dsa.tree.bst.base.iterator.binaryTree.BinaryTreeIterator;
import sks.dsa.tree.bst.base.node.binaryTree.BinaryNode;
import sks.dsa.tree.bst.base.tree.binaryTree.BinaryTree;

import java.util.Collection;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Stack;

public class PreOrderBinaryTreeIterator<ValueType>
        extends AbstractBinaryTreeIterator<ValueType>
        implements BinaryTreeIterator<ValueType>
{
    private final Stack<BinaryNode<ValueType>> traversalStack = new Stack<>();
    private int position = 0;


    public PreOrderBinaryTreeIterator(BinaryTree<ValueType> tree) {
        super(tree);
        if (getRoot() != null) {
            traversalStack.push(getRoot());
        }
    }

    @Override
    public boolean hasNext() {
        return !traversalStack.isEmpty();
    }


    @Override
    public BinaryNode<ValueType> next() {
        if (!hasNext()) {
            throw new NoSuchElementException("No more elements in the tree traversal");
        }

        BinaryNode<ValueType> currentNode = traversalStack.pop();

        if (currentNode.getRightChild() != null) {
            traversalStack.push(currentNode.getRightChild());
        }
        if (currentNode.getLeftChild() != null) {
            traversalStack.push(currentNode.getLeftChild());
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
