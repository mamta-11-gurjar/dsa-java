package sks.dsa.tree.bst.base.iterator.strategy.example;

import sks.dsa.tree.bst.base.iterator.strategy.StrategyBasedTreeIterator;
import sks.dsa.tree.bst.base.iterator.strategy.binaryTree.PreOrderStrategy;
import sks.dsa.tree.bst.base.iterator.strategy.binaryTree.InOrderStrategy;
import sks.dsa.tree.bst.base.node.binaryTree.BinaryNode;
import sks.dsa.tree.bst.base.node.binaryTree.searchTree.BinarySearchNode;
import sks.dsa.tree.bst.base.node.binaryTree.searchTree.DefaultBinarySearchNodeImpl;
import sks.dsa.tree.bst.base.tree.binaryTree.searchTree.BinarySearchTree;
import sks.dsa.tree.bst.base.tree.binaryTree.searchTree.DefaultBinarySearchTreeImpl;

import java.util.ArrayList;
import java.util.List;

/**
 * Example demonstrating the decoupled iterator design.
 * Shows how to use different traversal strategies with the same tree.
 */
public class DecoupledIteratorExample {

    /**
     * Creates a sample binary tree:
     *        4
     *       / \
     *      2   6
     *     / \ / \
     *    1  3 5  7
     */
    private static BinarySearchTree<Integer> createSampleTree() {
        BinarySearchNode<Integer> root = new DefaultBinarySearchNodeImpl<>(4);
        BinarySearchNode<Integer> left = new DefaultBinarySearchNodeImpl<>(2);
        BinarySearchNode<Integer> leftLeft = new DefaultBinarySearchNodeImpl<>(1);
        BinarySearchNode<Integer> leftRight = new DefaultBinarySearchNodeImpl<>(3);

        root.setLeftChild(left);
        left.setLeftChild(leftLeft);
        left.setRightChild(leftRight);

        BinarySearchNode<Integer> right = new DefaultBinarySearchNodeImpl<>(6);
        BinarySearchNode<Integer> rightLeft = new DefaultBinarySearchNodeImpl<>(5);
        BinarySearchNode<Integer> rightRight = new DefaultBinarySearchNodeImpl<>(7);

        root.setRightChild(right);
        right.setLeftChild(rightLeft);
        right.setRightChild(rightRight);

        return new DefaultBinarySearchTreeImpl<>(root);
    }

    /**
     * Demonstrates pre-order traversal.
     * Pre-order: Root -> Left -> Right
     */
    public static void demonstratePreOrderTraversal() {
        System.out.println("\n=== PRE-ORDER TRAVERSAL ===");
        System.out.println("Expected: 4, 2, 1, 3, 6, 5, 7");
        System.out.println("Actual:   ");

        BinarySearchTree<Integer> tree = createSampleTree();
        StrategyBasedTreeIterator<Integer, BinaryNode<Integer>, BinarySearchTree<Integer>> 
            iterator = new StrategyBasedTreeIterator<>(tree, new PreOrderStrategy<>());

        List<Integer> result = new ArrayList<>();
        while (iterator.hasNext()) {
            BinaryNode<Integer> node = iterator.next();
            result.add(node.getValue());
            System.out.print(node.getValue() + " ");
        }
        System.out.println();
        System.out.println("Total nodes traversed: " + result.size());
    }

    /**
     * Demonstrates in-order traversal.
     * In-order: Left -> Root -> Right
     */
    public static void demonstrateInOrderTraversal() {
        System.out.println("\n=== IN-ORDER TRAVERSAL ===");
        System.out.println("Expected: 1, 2, 3, 4, 5, 6, 7");
        System.out.println("Actual:   ");

        BinarySearchTree<Integer> tree = createSampleTree();
        StrategyBasedTreeIterator<Integer, BinaryNode<Integer>, BinarySearchTree<Integer>> 
            iterator = new StrategyBasedTreeIterator<>(tree, new InOrderStrategy<>());

        List<Integer> result = new ArrayList<>();
        while (iterator.hasNext()) {
            BinaryNode<Integer> node = iterator.next();
            result.add(node.getValue());
            System.out.print(node.getValue() + " ");
        }
        System.out.println();
        System.out.println("Total nodes traversed: " + result.size());
    }

    /**
     * Demonstrates switching strategies on the same tree.
     */
    public static void demonstrateSwitchingStrategies() {
        System.out.println("\n=== SWITCHING STRATEGIES ON THE SAME TREE ===");

        BinarySearchTree<Integer> tree = createSampleTree();

        // Pre-order traversal
        System.out.println("\nPre-order: ");
        StrategyBasedTreeIterator<Integer, BinaryNode<Integer>, BinarySearchTree<Integer>> 
            preOrderIterator = new StrategyBasedTreeIterator<>(tree, new PreOrderStrategy<>());
        while (preOrderIterator.hasNext()) {
            System.out.print(preOrderIterator.next().getValue() + " ");
        }

        // In-order traversal
        System.out.println("\nIn-order:  ");
        StrategyBasedTreeIterator<Integer, BinaryNode<Integer>, BinarySearchTree<Integer>> 
            inOrderIterator = new StrategyBasedTreeIterator<>(tree, new InOrderStrategy<>());
        while (inOrderIterator.hasNext()) {
            System.out.print(inOrderIterator.next().getValue() + " ");
        }
        System.out.println();
    }

    /**
     * Demonstrates iterator reset functionality.
     */
    public static void demonstrateReset() {
        System.out.println("\n=== ITERATOR RESET ===");

        BinarySearchTree<Integer> tree = createSampleTree();
        StrategyBasedTreeIterator<Integer, BinaryNode<Integer>, BinarySearchTree<Integer>> 
            iterator = new StrategyBasedTreeIterator<>(tree, new PreOrderStrategy<>());

        // First traversal
        System.out.println("First traversal:  ");
        while (iterator.hasNext()) {
            System.out.print(iterator.next().getValue() + " ");
        }

        // Reset
        iterator.reset();
        System.out.println("\nAfter reset:");

        // Second traversal
        System.out.println("Second traversal: ");
        while (iterator.hasNext()) {
            System.out.print(iterator.next().getValue() + " ");
        }
        System.out.println();
    }

    /**
     * Demonstrates accessing tree information through the iterator.
     */
    public static void demonstrateTreeInformation() {
        System.out.println("\n=== TREE INFORMATION ===");

        BinarySearchTree<Integer> tree = createSampleTree();
        StrategyBasedTreeIterator<Integer, BinaryNode<Integer>, BinarySearchTree<Integer>> 
            iterator = new StrategyBasedTreeIterator<>(tree, new PreOrderStrategy<>());

        System.out.println("Tree size: " + iterator.size());
        System.out.println("Root value: " + iterator.getRoot().getValue());
        System.out.println("Initial position: " + iterator.getPosition());

        // Traverse a few nodes
        iterator.next();
        System.out.println("After 1 next(): position = " + iterator.getPosition());
        iterator.next();
        System.out.println("After 2 next(): position = " + iterator.getPosition());
    }

    /**
     * Main method to run all demonstrations.
     */
    public static void main(String[] args) {
        System.out.println("╔════════════════════════════════════════════════════════╗");
        System.out.println("║    DECOUPLED TREE ITERATOR - DEMONSTRATION             ║");
        System.out.println("╚════════════════════════════════════════════════════════╝");

        demonstratePreOrderTraversal();
        demonstrateInOrderTraversal();
        demonstrateSwitchingStrategies();
        demonstrateReset();
        demonstrateTreeInformation();

        System.out.println("\n╔════════════════════════════════════════════════════════╗");
        System.out.println("║    KEY BENEFITS OF THIS DESIGN                         ║");
        System.out.println("╠════════════════════════════════════════════════════════╣");
        System.out.println("║ ✓ Decoupled: Tree and traversal are independent        ║");
        System.out.println("║ ✓ Reusable: Any strategy works with any tree          ║");
        System.out.println("║ ✓ Extensible: Add new strategies without changes      ║");
        System.out.println("║ ✓ Flexible: Switch strategies at runtime              ║");
        System.out.println("║ ✓ Efficient: Stack-based, O(log h) space complexity   ║");
        System.out.println("║ ✓ Type-safe: Full generic support                     ║");
        System.out.println("╚════════════════════════════════════════════════════════╝");
    }
}
