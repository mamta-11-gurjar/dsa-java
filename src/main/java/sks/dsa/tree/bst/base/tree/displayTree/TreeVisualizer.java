package sks.dsa.tree.bst.base.tree.displayTree;

/**
 * Used to display or visualize a tree.
 *
 * @param <T> the type of tree to be displayed
 */

public interface TreeVisualizer<T> {

    /**
     * Displays the given tree.
     *
     * @param tree the tree that needs to be displayed
     */

    void visualizeTree(T tree);
}
