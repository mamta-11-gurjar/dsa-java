 package sks.dsa.tree.bst.base.iterator.strategy.binaryTree;

import sks.dsa.tree.bst.base.iterator.strategy.TraversalStrategy;
import sks.dsa.tree.bst.base.node.binaryTree.BinaryNode;

import java.util.Stack;
import java.util.NoSuchElementException;

/**
 * Pre-order traversal strategy for binary trees.
 * Traversal order: Root -> Left -> Right
 *
 * Uses a stack-based iterative approach for memory efficiency.
 * Space complexity: O(log h) where h is the height of the tree
 *
 * @param <ValueType> The type of values stored in the binary tree nodes
 */
public class PreOrderStrategy<ValueType> implements TraversalStrategy<ValueType, BinaryNode<ValueType>>
{
    private final Stack<BinaryNode<ValueType>> traversalStack = new Stack<>();
    private BinaryNode<ValueType> root;

    /**
     * Initializes the pre-order traversal strategy with the given root node.
     *
     * @param root the root node to start traversal from
     */
    @Override
    public void initialize(BinaryNode<ValueType> root) {
        this.root = root;
        this.traversalStack.clear();
        if (root != null) {
            traversalStack.push(root);
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
     * Returns the next node in pre-order traversal (Root -> Left -> Right).
     *
     * Algorithm:
     * 1. Pop a node from the stack
     * 2. Push its right child (if exists) - pushed first because stack is LIFO
     * 3. Push its left child (if exists)
     * 4. Return the popped node
     *
     * @return the next node in pre-order traversal
     * @throws java.util.NoSuchElementException if there are no more nodes
     */
    @Override
    public BinaryNode<ValueType> next() {
        if (!hasNext()) {
            throw new NoSuchElementException("No more elements in pre-order traversal");
        }
        BinaryNode<ValueType> current = traversalStack.pop();

        // Push children in reverse order (right first, then left)
        // This ensures left subtree is processed before right subtree
        if (current.getRightChild() != null) {
            traversalStack.push(current.getRightChild());
        }
        if (current.getLeftChild() != null) {
            traversalStack.push(current.getLeftChild());
        }

        return current;
    }

    /**
     * Resets the traversal strategy to start over from the root.
     */
    @Override
    public void reset() {
        initialize(root);
    }
}
