package sks.dsa.tree.bst.base.iterator.strategy;

import sks.dsa.tree.bst.base.node.SelfReferentialNode;

/**
 * Strategy interface for defining how to traverse a tree.
 * This allows different traversal algorithms to be plugged into a generic iterator
 * without coupling the iterator to the specific tree structure.
 *
 * @param <ValueType> The type of values stored in tree nodes
 * @param <NodeType> The type of nodes in the tree
 */
public interface TraversalStrategy<ValueType, NodeType extends SelfReferentialNode<ValueType>>
{
    /**
     * Initialize the strategy for traversal starting from the given root node.
     * This is called once before iteration begins.
     *
     * @param root the root node to start traversal from
     */
    void initialize(NodeType root);

    /**
     * Checks if there are more nodes to traverse.
     *
     * @return true if there are more nodes, false otherwise
     */
    boolean hasNext();

    /**
     * Returns the next node according to the traversal strategy.
     *
     * @return the next node to visit
     */
    NodeType next();

    /**
     * Resets the traversal strategy to start over from the root.
     */
    void reset();
}
