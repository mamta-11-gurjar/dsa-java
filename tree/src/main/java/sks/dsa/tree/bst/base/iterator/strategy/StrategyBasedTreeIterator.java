package sks.dsa.tree.bst.base.iterator.strategy;

import sks.dsa.tree.bst.base.node.SelfReferentialNode;
import sks.dsa.tree.bst.base.tree.Tree;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * A generic, strategy-based iterator that decouples tree traversal from tree structure.
 * This iterator can be used with any tree type and any traversal strategy.
 *
 * This design follows the Strategy pattern, allowing different traversal algorithms
 * to be plugged in without modifying the iterator itself.
 *
 * @param <ValueType> The type of values stored in tree nodes
 * @param <NodeType> The type of nodes in the tree
 * @param <TreeType> The type of the tree being traversed
 */
public class StrategyBasedTreeIterator<
        ValueType,
        NodeType extends SelfReferentialNode<ValueType>,
        TreeType extends Tree<ValueType, NodeType>>
    implements Iterator<NodeType>
{
    private final TreeType tree;
    private final TraversalStrategy<ValueType, NodeType> strategy;
    private int position = 0;

    /**
     * Constructs a strategy-based iterator for the given tree and traversal strategy.
     *
     * @param tree the tree to iterate over
     * @param strategy the traversal strategy to use
     */
    public StrategyBasedTreeIterator(TreeType tree, TraversalStrategy<ValueType, NodeType> strategy) {
        this.tree = tree;
        this.strategy = strategy;
        this.position = 0;
        this.strategy.initialize(tree.getRoot());
    }

    /**
     * Checks if there are more nodes to traverse.
     *
     * @return true if there are more nodes, false otherwise
     */
    @Override
    public boolean hasNext() {
        return strategy.hasNext();
    }

    /**
     * Returns the next node according to the traversal strategy.
     *
     * @return the next node to visit
     * @throws NoSuchElementException if there are no more nodes to traverse
     */
    @Override
    public NodeType next() {
        if (!hasNext()) {
            throw new NoSuchElementException("No more elements in the tree traversal");
        }
        position++;
        return strategy.next();
    }

    /**
     * Gets the underlying tree being traversed.
     *
     * @return the tree
     */
    public TreeType getTree() {
        return tree;
    }

    /**
     * Gets the root node of the tree.
     *
     * @return the root node
     */
    public NodeType getRoot() {
        return tree.getRoot();
    }

    /**
     * Gets the size of the tree.
     *
     * @return the number of nodes in the tree
     */
    public int size() {
        return tree.size();
    }

    /**
     * Gets the current position in the traversal.
     *
     * @return the number of nodes visited so far
     */
    public int getPosition() {
        return position;
    }

    /**
     * Resets the iterator to the beginning of the traversal.
     */
    public void reset() {
        position = 0;
        strategy.reset();
    }
}
