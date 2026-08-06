package sks.dsa.tree.bst.base.iterator.strategy.binaryTree;

import sks.dsa.tree.bst.base.iterator.strategy.TraversalStrategy;
import sks.dsa.tree.bst.base.node.binaryTree.BinaryNode;

import java.util.Stack;
import java.util.NoSuchElementException;

/**
 * In-order traversal strategy for binary trees.
 * Traversal order: Left -> Root -> Right
 *
 * Uses a stack-based iterative approach for memory efficiency.
 * Space complexity: O(log h) where h is the height of the tree
 *
 * @param <ValueType> The type of values stored in the binary tree nodes
 */
public class InOrderStrategy<ValueType> implements TraversalStrategy<ValueType, BinaryNode<ValueType>>
{
    private final Stack<BinaryNode<ValueType>> traversalStack = new Stack<>();
    private BinaryNode<ValueType> root;
    private boolean firstIteration = true;

    /**
     * Initializes the in-order traversal strategy with the given root node.
     *
     * @param root the root node to start traversal from
     */
    @Override
    public void initialize(BinaryNode<ValueType> root) {
        this.root = root;
        this.traversalStack.clear();
        this.firstIteration = true;
        if (root != null) {
            pushLeftChildren(root);
        }
    }

    /**
     * Checks if there are more nodes to traverse.
     *
     * @return true if the traversal stack is not empty, false otherwise
     */
    @Override
    public boolean hasNext() {
        return !traversalStack.isEmpty();
    }

    /**
     * Returns the next node in in-order traversal (Left -> Root -> Right).
     *
     * Algorithm:
     * 1. Pop a node from the stack
     * 2. Push all left children of its right child (if exists)
     * 3. Return the popped node
     *
     * @return the next node in in-order traversal
     * @throws NoSuchElementException if there are no more nodes
     */
    @Override
    public BinaryNode<ValueType> next() {
        if (!hasNext()) {
            throw new NoSuchElementException("No more elements in in-order traversal");
        }
        BinaryNode<ValueType> current = traversalStack.pop();
        pushLeftChildren(current.getRightChild());
        return current;
    }

    /**
     * Resets the traversal strategy to start over from the root.
     */
    @Override
    public void reset() {
        initialize(root);
    }

    /**
     * Helper method to push all left children of a node onto the stack.
     *
     * @param node the node to start from
     */
    private void pushLeftChildren(BinaryNode<ValueType> node) {
        if (node == null) {
            return;
        }
        traversalStack.push(node);
        pushLeftChildren(node.getLeftChild());
    }
}
