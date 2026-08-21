package sks.dsa.tree.bst.base.tree.displayTree;

/**
 * Converts a tree into its textual representation.
 *
 * @param <T> the type of tree
 */
public interface TreeStringer<T> {

    /**
     * Converts the given tree into a string representation.
     *
     * @param tree the tree to convert
     * @return string representation of the tree
     */
    String stringify(T tree);
}