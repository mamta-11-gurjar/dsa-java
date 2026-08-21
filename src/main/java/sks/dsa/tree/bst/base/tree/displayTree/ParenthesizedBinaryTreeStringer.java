package sks.dsa.tree.bst.base.tree.displayTree;

import sks.dsa.tree.bst.base.node.binaryTree.BinaryNode;
import sks.dsa.tree.bst.base.tree.binaryTree.searchTree.BinarySearchTree;

/**
 * Converts a binary search tree into a compact
 * parenthesized String representation.
 *
 * Example:
 *
 *        4
 *       / \
 *      2   6
 *     / \ / \
 *    1  3 5  7
 *
 * Result:
 *
 * 4(2(1,3),6(5,7))
 *
 * @param <T> type of value stored in the tree
 */
public class ParenthesizedBinaryTreeStringer<T extends Comparable<T>>
        implements TreeStringer<BinarySearchTree<T>> {

    @Override
    public String stringify(BinarySearchTree<T> tree) {

        if (tree == null || tree.getRoot() == null) {
            return "Tree is empty.";
        }

        return buildTreeString(tree.getRoot());
    }

    private String buildTreeString(BinaryNode<T> node) {

        if (node == null) {
            return "";
        }

        String value = String.valueOf(node.getValue());

        BinaryNode<T> left = node.getLeftChild();
        BinaryNode<T> right = node.getRightChild();

        if (left == null && right == null) {
            return value;
        }

        return value
                + "("
                + buildTreeString(left)
                + ","
                + buildTreeString(right)
                + ")";
    }
}
