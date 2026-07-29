package sks.dsa.tree.bst.base.node.binaryTree.searchTree;

import sks.dsa.tree.bst.base.node.binaryTree.BinaryNode;

public interface BinarySearchNode<ValueType extends Comparable<ValueType>>
        extends BinaryNode<ValueType>, Comparable<BinarySearchNode<ValueType>>
{
    default int compareTo(final BinarySearchNode<ValueType> o) {
        return getValue().compareTo(o.getValue());
    }
}
