package sks.dsa.tree.bst.base.tree.binaryTree;

import sks.dsa.tree.bst.base.node.binaryTree.BinaryNode;
import sks.dsa.tree.bst.base.tree.Tree;

/**
 * A tree with max two children per node.
 * @param <T> Type of data this tree nodes can store
 */
public interface BinaryTree<T> extends Tree<T, BinaryNode<T>>
{
    /**
     * @return is this a balanced binary tree
     */
    boolean isTreeBalanced();

    /**
     * @return is this a complete binary tree
     */
    boolean isTreeComplete();

    /**
     *
     * @return right subtree of a binary tree
     */
    BinaryTree<T> getRightSubTree();

    /**
     *
     * @return left subtree of a binary tree
     */
    BinaryTree<T> getLeftSubTree();
}
