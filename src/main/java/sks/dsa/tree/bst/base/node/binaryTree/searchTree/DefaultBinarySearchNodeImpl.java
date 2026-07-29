package sks.dsa.tree.bst.base.node.binaryTree.searchTree;

import sks.dsa.tree.bst.base.node.SelfReferentialNode;
import sks.dsa.tree.bst.base.node.binaryTree.BinaryNode;

import java.util.List;
import java.util.NoSuchElementException;

public class DefaultBinarySearchNodeImpl<ValueType extends Comparable<ValueType>> implements BinarySearchNode<ValueType> {
    private final ValueType innerValue;
    private BinarySearchNode<ValueType> left, right;
    private int height;
    private int numberOfChildren;

    public DefaultBinarySearchNodeImpl(final ValueType innerValue) {
        this(innerValue, null, null);
    }

    public DefaultBinarySearchNodeImpl(final ValueType innerValue, final BinarySearchNode<ValueType> left, final BinarySearchNode<ValueType> right) {
        this.innerValue = innerValue;
        this.left = left;
        this.right = right;
        refreshHeight();
        refreshNumberOfChildren();
    }

    private void refreshNumberOfChildren() {
        int leftChildren = this.left!=null?this.left.numberOfChildren()+1:0;
        int rightChildren = this.right!=null?this.right.numberOfChildren()+1:0;
        this.numberOfChildren = leftChildren + rightChildren;
    }

    private void refreshHeight() {
        int leftHeight = this.left!=null?this.left.getHeight():0;
        int rightHeight = this.right!=null?this.right.getHeight():0;
        this.height = Math.max(leftHeight, rightHeight) + 1;
    }

    @Override
    public ValueType getValue() {
        return innerValue;
    }

    @Override
    public List<BinarySearchNode<ValueType>> getChildren() {
        assert left != null;
        assert right != null;
        return List.of(left, right);
    }

    @Override
    public BinarySearchNode<ValueType> getChild(int childNumber) {
        return switch (childNumber)
                {
                    case 0 -> this.left;
                    case 1 -> this.right;
                    default -> throw new NoSuchElementException("A binary search node doesn't contain more than two children");
                };
    }

    @Override
    public BinarySearchNode<ValueType> getNext() {
        throw new UnsupportedOperationException("Many next pointers hence next() isn't supported on a binary-search-tree");
    }

    @Override
    public void setChild(int childNumber, final SelfReferentialNode<ValueType> nextElement) {
        if(childNumber==0) {
            left = (BinarySearchNode<ValueType>) nextElement;
            refreshHeight();
            refreshNumberOfChildren();
        }
        else if(childNumber==1) {
            right = (BinarySearchNode<ValueType>) nextElement;
            refreshHeight();
            refreshNumberOfChildren();
        }
        else
            throw new NoSuchElementException("A binary search node doesn't contain more than two children");
    }

    @Override
    public void setNext(SelfReferentialNode<ValueType> nextElement) {
        throw new UnsupportedOperationException("Many next pointers hence next() isn't supported on a binary-search-tree");
    }

    @Override
    public boolean hasNext() {
        return left!=null || right!=null;
    }

    @Override
    public int maxNumberOfNextPointers() {
        return 2;
    }

    @Override
    public BinarySearchNode<ValueType> getRightChild() {
        return this.right;
    }

    @Override
    public BinarySearchNode<ValueType> getLeftChild() {
        return this.left;
    }

    @Override
    public void setRightChild(final BinaryNode<ValueType> nextElement) {
        right = (BinarySearchNode<ValueType>) nextElement;
        refreshHeight();
        refreshNumberOfChildren();
    }

    @Override
    public void setLeftChild(BinaryNode<ValueType> nextElement) {
        left = (BinarySearchNode<ValueType>) nextElement;
        refreshHeight();
        refreshNumberOfChildren();
    }

    @Override
    public int numberOfChildren() {
        refreshNumberOfChildren();
        return this.numberOfChildren;
    }

    @Override
    public int numberOfNonNullImmediateChildren() {
        int nonNullImmediateChildren = 0;
        if(left!=null)
            nonNullImmediateChildren++;
        if(right!=null)
            nonNullImmediateChildren++;
        return nonNullImmediateChildren;
    }

    @Override
    public int getHeight() {
        refreshHeight();
        return height;
    }
}
