package sks.dsa.tree.bst.base.tree.displayTree;

import sks.dsa.tree.bst.base.node.binaryTree.BinaryNode;
import sks.dsa.tree.bst.base.tree.binaryTree.searchTree.BinarySearchTree;

/**
 * Strategy for converting a binary search tree into a String representation.
 *
 * @param <T> type of value stored in the tree
 */
public class BinaryTreeStrategy<T extends Comparable<T>>
        implements TreeStrategy<BinarySearchTree<T>> {

    @Override
    public String stringify(BinarySearchTree<T> tree) {

        if (tree == null || tree.getRoot() == null) {
            return "Tree is empty.";
        }

        StringBuilder result = new StringBuilder();

        buildTreeString(
                tree.getRoot(),
                "",
                true,
                result
        );

        return result.toString();
    }

    private void buildTreeString(
            BinaryNode<T> node,
            String prefix,
            boolean isLast,
            StringBuilder result) {

        if (node == null) {
            return;
        }

        result.append(prefix)
                .append(isLast ? "└── " : "├── ")
                .append(node.getValue())
                .append(System.lineSeparator());

        BinaryNode<T> left = node.getLeftChild();
        BinaryNode<T> right = node.getRightChild();

        if (left != null) {
            buildTreeString(
                    left,
                    prefix + (isLast ? "    " : "│   "),
                    right == null,
                    result
            );
        }

        if (right != null) {
            buildTreeString(
                    right,
                    prefix + (isLast ? "    " : "│   "),
                    true,
                    result
            );
        }
    }
}