package sks.dsa.tree.bst.base.iterator.binaryTree.ordered;

import sks.dsa.tree.bst.base.iterator.binaryTree.AbstractBinaryTreeIterator;
import sks.dsa.tree.bst.base.iterator.binaryTree.BinaryTreeIterator;
import sks.dsa.tree.bst.base.node.binaryTree.BinaryNode;
import sks.dsa.tree.bst.base.tree.binaryTree.BinaryTree;

import java.util.Collection;
import java.util.List;
import java.util.Stack;

public class InOrderBinaryTreeIterator <ValueType>
        extends AbstractBinaryTreeIterator<ValueType>
        implements BinaryTreeIterator<ValueType>
{
    private BinaryNode<ValueType> currentNode = getRoot();
    private int position = 0;
    private boolean thisIsTheFirstIteration = true;

    private final Stack<BinaryNode<ValueType>> callUnwindStack = new Stack<>();    // takes O(lg(h)) space

    public InOrderBinaryTreeIterator(BinaryTree<ValueType> tree) {
        super(tree);
    }

    @Override
    public boolean hasNext() {
        return position<size();
    }

    @Override
    public BinaryNode<ValueType> next() {
        if(thisIsTheFirstIteration) {
            insertLeftChildrenInStack(currentNode, callUnwindStack);
            thisIsTheFirstIteration = false;
        }
        currentNode = callUnwindStack.pop();
        insertLeftChildrenInStack(currentNode.getRightChild(), callUnwindStack);
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

    protected void insertLeftChildrenInStack(BinaryNode<ValueType> node,
                                             Stack<BinaryNode<ValueType>> unwindStack) {
        if(node == null)
            return;
        unwindStack.push(node);
        insertLeftChildrenInStack(node.getLeftChild(), unwindStack);
    }
}
