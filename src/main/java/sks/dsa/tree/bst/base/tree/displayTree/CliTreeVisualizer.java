package sks.dsa.tree.bst.base.tree.displayTree;

/**
 * Displays a tree on the command line.
 *
 * @param <T> type of tree
 */
public interface CliTreeVisualizer<T> {

    /**
     * Displays the given tree.
     *
     * @param tree tree to display
     */
    void visualizeTree(T tree);
}