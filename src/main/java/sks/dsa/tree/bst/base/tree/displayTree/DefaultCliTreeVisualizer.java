package sks.dsa.tree.bst.base.tree.displayTree;

import sks.dsa.tree.bst.base.node.binaryTree.BinaryNode;
import sks.dsa.tree.bst.base.tree.binaryTree.BinaryTree;

public class DefaultCliTreeVisualizer<T>
        implements CliTreeVisualizer<T> {

    @Override
    public void visualizeTree(BinaryTree<T> tree) {
        if (tree == null || tree.getRoot() == null) {
            System.out.println("Tree is empty.");
            return;
        }

        printTree(tree.getRoot(), "", true);
    }

    private void printTree(
            BinaryNode<T> node,
            String prefix,
            boolean isLast) {

        if (node == null) {
            return;
        }

        System.out.println(
                prefix +
                        (isLast ? "└── " : "├── ") +
                        node.getValue()
        );

        BinaryNode<T> left = node.getLeftChild();
        BinaryNode<T> right = node.getRightChild();

        if (left != null || right != null) {

            if (left != null) {
                printTree(
                        left,
                        prefix + (isLast ? "    " : "│   "),
                        right == null
                );
            }

            if (right != null) {
                printTree(
                        right,
                        prefix + (isLast ? "    " : "│   "),
                        true
                );
            }
        }
    }
}
