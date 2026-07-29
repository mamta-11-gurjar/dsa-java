package sks.dsa.tree.bst.base.iterator;

import sks.dsa.tree.bst.base.node.SelfReferentialNode;
import sks.dsa.tree.bst.base.tree.Tree;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;

/**
 * The TreeIterator interface is an iterator which is to be implemented by all the iterators associated with n-ary-trees iterators
 * @param <ValueType> The type of values this trees' nodes can store
 * @param <NodeType> The Node Type of this tree
 * @param <TreeType> Type of Tree
 */
public interface TreeIterator<ValueType,
        NodeType extends SelfReferentialNode<ValueType>,
        TreeType extends Tree<ValueType, NodeType>>
    extends Iterator<NodeType>
{
    /**
     * @return the underlying trees' root node
     */
    NodeType getRoot();

    /**
     * @return the underlying trees' size
     */
    int size();

    /**
     * @return underlying tree
     */
    TreeType getTree();

    /**
     * Every iterator is tasked to build the internal tree from a sequence of values in the iterators' traversal order
     * @param values a sequence of values that will be arranged in the traversal order of this iterator
     */
    void buildTreeFromSequence(final ValueType[] values);

    /**
     * Every iterator is tasked to build the internal tree from a sequence of values in the iterators' traversal order
     * @param nodes a sequence of nodes that will be arranged in the traversal order of this iterator
     */
    void buildTreeFromSequence(final NodeType [] nodes);

    /**
     * Every iterator is tasked to build the internal tree from a sequence of values in the iterators' traversal order
     * @param values a collection of values that will be arranged in the traversal order of this iterator
     */
    void buildTreeFromSequence(final Collection<ValueType> values);

    /**
     * Every iterator is tasked to build the internal tree from a sequence of values in the iterators' traversal order
     * @param nodes a list of nodes that will be arranged in the traversal order of this iterator
     */
    void buildTreeFromSequence(final List<NodeType> nodes);
}
