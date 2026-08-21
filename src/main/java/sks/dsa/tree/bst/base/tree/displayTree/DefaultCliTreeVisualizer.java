package sks.dsa.tree.bst.base.tree.displayTree;

import sks.dsa.tree.bst.base.tree.binaryTree.searchTree.BinarySearchTree;

/**
 * Default CLI implementation for displaying a binary search tree.
 *
 * @param <T> type of value stored in the tree
 */
public class DefaultCliTreeVisualizer<T extends Comparable<T>>
        implements CliTreeVisualizer<BinarySearchTree<T>> {

    private final TreeStringer<BinarySearchTree<T>> treeStringer;

    public DefaultCliTreeVisualizer(
            TreeStringer<BinarySearchTree<T>> treeStringer) {

        this.treeStringer = treeStringer;
    }

    @Override
    public void visualizeTree(BinarySearchTree<T> tree) {

        System.out.println(
                treeStringer.stringify(tree)
        );
    }
}
