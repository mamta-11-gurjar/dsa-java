package sks.dsa.tree.bst.base.node.binaryTree;

import sks.dsa.tree.bst.base.node.SelfReferentialNode;

/**
 * A self-referential node which can contain max two children
 * @param <T> Type of value this tree stores
 */
public interface BinaryNode <T> extends SelfReferentialNode<T>
{
    /**
     * Get the right chile of this binary node
     * @return right child
     */
    BinaryNode<T> getRightChild();

    /**
     * Get the left child of this binary node
     * @return left child
     */
    BinaryNode<T> getLeftChild();

    /**
     * Set the right chile of this binary node
     * @param nextElement  right child
     */
     void setRightChild(final BinaryNode<T> nextElement);

    /**
     * Set the left child of this binary node
     * @param nextElement left child
     */
     void setLeftChild(final BinaryNode<T> nextElement);

    /**
     * Get the total number of children this tree can store
     * @return total number of children
     */
    int numberOfChildren();

    /**
     * Total number of non-null immediate children
     * @return all present children
     */
    int numberOfNonNullImmediateChildren();

    /**
     * Get height of this particular node in the tree
     * @return height of htis node in the tree
     */
    int getHeight();
}
