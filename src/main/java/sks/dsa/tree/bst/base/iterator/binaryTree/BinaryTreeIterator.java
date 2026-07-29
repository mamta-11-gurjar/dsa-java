package sks.dsa.tree.bst.base.iterator.binaryTree;

import sks.dsa.tree.bst.base.iterator.TreeIterator;
import sks.dsa.tree.bst.base.node.binaryTree.BinaryNode;
import sks.dsa.tree.bst.base.tree.binaryTree.BinaryTree;

import java.util.Iterator;

public interface BinaryTreeIterator<ValueType> extends
        TreeIterator<ValueType,
                        BinaryNode<ValueType>,
                        BinaryTree<ValueType>>,
        Iterator<BinaryNode<ValueType>>
{

}
