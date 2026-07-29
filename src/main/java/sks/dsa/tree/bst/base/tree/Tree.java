package sks.dsa.tree.bst.base.tree;

import sks.dsa.tree.bst.base.node.SelfReferentialNode;

/**
 * Interface for n-ary-tree with {@link SelfReferentialNode SelfReferentialNode} as internal node implementation
 * @param <ValueType> the value type this tree supports
 * @param <NodeType> a kind of {@link SelfReferentialNode SelfReferentialNode}
 */
public interface Tree<ValueType,
        NodeType extends SelfReferentialNode<ValueType>>
        extends Iterable<NodeType>
{
    /**
     * @return this tree's root
     */
    NodeType getRoot();

    /**
     * @return current tree's max depth
     */
    int getMaxTreeDepth();

    /**
     * @return current tree's max height
     */
    int getMaxTreeHeight();

    /**
     * @return current tree's number of children
     */
    int getNumberOfChildren();

    /**
     * @return current tree's size, number of nodes in current tree
     */
    int size();
}
