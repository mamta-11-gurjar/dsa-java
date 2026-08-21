package sks.dsa.tree.bst.base.tree.displayTree;

import org.junit.jupiter.api.Test;
import sks.dsa.tree.bst.base.node.binaryTree.searchTree.BinarySearchNode;
import sks.dsa.tree.bst.base.node.binaryTree.searchTree.DefaultBinarySearchNodeImpl;
import sks.dsa.tree.bst.base.tree.binaryTree.searchTree.BinarySearchTree;
import sks.dsa.tree.bst.base.tree.binaryTree.searchTree.DefaultBinarySearchTreeImpl;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BinaryTreeStringerTest {

    private BinarySearchTree<Integer> createTree(
            BinarySearchNode<Integer> root) {

        return new DefaultBinarySearchTreeImpl<>(root);
    }

    private BinarySearchNode<Integer> node(int value) {
        return new DefaultBinarySearchNodeImpl<>(value);
    }

    @Test
    void shouldHandleEmptyTree() {

        TreeStringer<BinarySearchTree<Integer>> stringer =
                new BinaryTreeStrategy<>();

        String actual = stringer.stringify(null);

        assertEquals("Tree is empty.", actual);
    }

    @Test
    void shouldHandleSingleRootTree() {

        BinarySearchNode<Integer> root = node(10);

        BinarySearchTree<Integer> tree = createTree(root);

        TreeStringer<BinarySearchTree<Integer>> stringer =
                new BinaryTreeStrategy<>();

        String actual = stringer.stringify(tree);

        String expected =
                "└── 10" + System.lineSeparator();

        assertEquals(expected, actual);
    }

    @Test
    void shouldHandleLeftSequenceTree() {

        /*
                 4
                /
               3
              /
             2
            /
           1
        */

        BinarySearchNode<Integer> root = node(4);
        BinarySearchNode<Integer> node3 = node(3);
        BinarySearchNode<Integer> node2 = node(2);
        BinarySearchNode<Integer> node1 = node(1);

        root.setLeftChild(node3);
        node3.setLeftChild(node2);
        node2.setLeftChild(node1);

        BinarySearchTree<Integer> tree = createTree(root);

        TreeStringer<BinarySearchTree<Integer>> stringer =
                new BinaryTreeStrategy<>();

        String actual = stringer.stringify(tree);

        String expected =
                "└── 4" + System.lineSeparator() +
                        "    └── 3" + System.lineSeparator() +
                        "        └── 2" + System.lineSeparator() +
                        "            └── 1" + System.lineSeparator();

        assertEquals(expected, actual);
    }

    @Test
    void shouldHandleRightSequenceTree() {

        /*
             1
              \
               2
                \
                 3
                  \
                   4
        */

        BinarySearchNode<Integer> root = node(1);
        BinarySearchNode<Integer> node2 = node(2);
        BinarySearchNode<Integer> node3 = node(3);
        BinarySearchNode<Integer> node4 = node(4);

        root.setRightChild(node2);
        node2.setRightChild(node3);
        node3.setRightChild(node4);

        BinarySearchTree<Integer> tree = createTree(root);

        TreeStringer<BinarySearchTree<Integer>> stringer =
                new BinaryTreeStrategy<>();

        String actual = stringer.stringify(tree);

        String expected =
                "└── 1" + System.lineSeparator() +
                        "    └── 2" + System.lineSeparator() +
                        "        └── 3" + System.lineSeparator() +
                        "            └── 4" + System.lineSeparator();

        assertEquals(expected, actual);
    }

    @Test
    void shouldHandleBalancedTree() {

        /*
                 4
                / \
               2   6
              / \ / \
             1  3 5  7
        */

        BinarySearchNode<Integer> root = node(4);
        BinarySearchNode<Integer> node2 = node(2);
        BinarySearchNode<Integer> node1 = node(1);
        BinarySearchNode<Integer> node3 = node(3);
        BinarySearchNode<Integer> node6 = node(6);
        BinarySearchNode<Integer> node5 = node(5);
        BinarySearchNode<Integer> node7 = node(7);

        root.setLeftChild(node2);
        root.setRightChild(node6);

        node2.setLeftChild(node1);
        node2.setRightChild(node3);

        node6.setLeftChild(node5);
        node6.setRightChild(node7);

        BinarySearchTree<Integer> tree = createTree(root);

        TreeStringer<BinarySearchTree<Integer>> stringer =
                new BinaryTreeStrategy<>();

        String actual = stringer.stringify(tree);

        String expected =
                "└── 4" + System.lineSeparator() +
                        "    ├── 2" + System.lineSeparator() +
                        "    │   ├── 1" + System.lineSeparator() +
                        "    │   └── 3" + System.lineSeparator() +
                        "    └── 6" + System.lineSeparator() +
                        "        ├── 5" + System.lineSeparator() +
                        "        └── 7" + System.lineSeparator();

        assertEquals(expected, actual);
    }

    @Test
    void shouldHandleOnlyLeftSubtree() {

        /*
             4
            /
           2
          /
         1
        */

        BinarySearchNode<Integer> root = node(4);
        BinarySearchNode<Integer> node2 = node(2);
        BinarySearchNode<Integer> node1 = node(1);

        root.setLeftChild(node2);
        node2.setLeftChild(node1);

        BinarySearchTree<Integer> tree = createTree(root);

        TreeStringer<BinarySearchTree<Integer>> stringer =
                new BinaryTreeStrategy<>();

        String actual = stringer.stringify(tree);

        String expected =
                "└── 4" + System.lineSeparator() +
                        "    └── 2" + System.lineSeparator() +
                        "        └── 1" + System.lineSeparator();

        assertEquals(expected, actual);
    }

    @Test
    void shouldHandleOnlyRightSubtree() {

        /*
         1
          \
           3
            \
             5
        */

        BinarySearchNode<Integer> root = node(1);
        BinarySearchNode<Integer> node3 = node(3);
        BinarySearchNode<Integer> node5 = node(5);

        root.setRightChild(node3);
        node3.setRightChild(node5);

        BinarySearchTree<Integer> tree = createTree(root);

        TreeStringer<BinarySearchTree<Integer>> stringer =
                new BinaryTreeStrategy<>();

        String actual = stringer.stringify(tree);

        String expected =
                "└── 1" + System.lineSeparator() +
                        "    └── 3" + System.lineSeparator() +
                        "        └── 5" + System.lineSeparator();

        assertEquals(expected, actual);
    }

    @Test
    void shouldHandleSparseTree() {

        /*
                 8
                / \
               4   12
                \
                 6
                  \
                   7
        */

        BinarySearchNode<Integer> root = node(8);
        BinarySearchNode<Integer> node4 = node(4);
        BinarySearchNode<Integer> node12 = node(12);
        BinarySearchNode<Integer> node6 = node(6);
        BinarySearchNode<Integer> node7 = node(7);

        root.setLeftChild(node4);
        root.setRightChild(node12);

        node4.setRightChild(node6);
        node6.setRightChild(node7);

        BinarySearchTree<Integer> tree = createTree(root);

        TreeStringer<BinarySearchTree<Integer>> stringer =
                new BinaryTreeStrategy<>();

        String actual = stringer.stringify(tree);

        String expected =
                "└── 8" + System.lineSeparator() +
                        "    ├── 4" + System.lineSeparator() +
                        "    │   └── 6" + System.lineSeparator() +
                        "    │       └── 7" + System.lineSeparator() +
                        "    └── 12" + System.lineSeparator();

        assertEquals(expected, actual);
    }

    @Test
    void shouldHandleSplayLikeTreeShape() {

        /*
         After a splay operation the tree can become heavily
         unbalanced. Here we test the resulting structure.

                 7
                /
               5
              /
             3
              \
               4
        */

        BinarySearchNode<Integer> root = node(7);
        BinarySearchNode<Integer> node5 = node(5);
        BinarySearchNode<Integer> node3 = node(3);
        BinarySearchNode<Integer> node4 = node(4);

        root.setLeftChild(node5);
        node5.setLeftChild(node3);
        node3.setRightChild(node4);

        BinarySearchTree<Integer> tree = createTree(root);

        TreeStringer<BinarySearchTree<Integer>> stringer =
                new BinaryTreeStrategy<>();

        String actual = stringer.stringify(tree);

        String expected =
                "└── 7" + System.lineSeparator() +
                        "    └── 5" + System.lineSeparator() +
                        "        └── 3" + System.lineSeparator() +
                        "            └── 4" + System.lineSeparator();

        assertEquals(expected, actual);
    }
}