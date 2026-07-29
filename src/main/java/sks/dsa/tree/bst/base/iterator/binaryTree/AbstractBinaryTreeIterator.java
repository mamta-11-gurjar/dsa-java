package sks.dsa.tree.bst.base.iterator.binaryTree;

import sks.dsa.tree.bst.base.iterator.AbstractTreeIterator;
import sks.dsa.tree.bst.base.node.binaryTree.BinaryNode;
import sks.dsa.tree.bst.base.tree.binaryTree.BinaryTree;

public abstract class AbstractBinaryTreeIterator<ValueType> extends AbstractTreeIterator<ValueType, BinaryNode<ValueType>, BinaryTree<ValueType>> implements BinaryTreeIterator<ValueType>
{
    public AbstractBinaryTreeIterator(final BinaryTree<ValueType> tree) {
        super(tree);
    }
}
