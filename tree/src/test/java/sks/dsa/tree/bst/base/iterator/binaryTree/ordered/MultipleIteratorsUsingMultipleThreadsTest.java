package sks.dsa.tree.bst.base.iterator.binaryTree.ordered;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import org.junit.jupiter.api.Test;
import sks.dsa.tree.bst.base.node.binaryTree.searchTree.BinarySearchNode;
import sks.dsa.tree.bst.base.node.binaryTree.searchTree.DefaultBinarySearchNodeImpl;
import sks.dsa.tree.bst.base.tree.binaryTree.searchTree.BinarySearchTree;
import sks.dsa.tree.bst.base.tree.binaryTree.searchTree.DefaultBinarySearchTreeImpl;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MultipleIteratorsUsingMultipleThreadsTest {

    private BinarySearchTree<Integer> createTree() {

        BinarySearchNode<Integer> root =
                new DefaultBinarySearchNodeImpl<>(4);

        BinarySearchNode<Integer> node2 =
                new DefaultBinarySearchNodeImpl<>(2);

        BinarySearchNode<Integer> node1 =
                new DefaultBinarySearchNodeImpl<>(1);

        BinarySearchNode<Integer> node3 =
                new DefaultBinarySearchNodeImpl<>(3);

        BinarySearchNode<Integer> node6 =
                new DefaultBinarySearchNodeImpl<>(6);

        BinarySearchNode<Integer> node5 =
                new DefaultBinarySearchNodeImpl<>(5);

        BinarySearchNode<Integer> node7 =
                new DefaultBinarySearchNodeImpl<>(7);

        root.setLeftChild(node2);
        root.setRightChild(node6);

        node2.setLeftChild(node1);
        node2.setRightChild(node3);

        node6.setLeftChild(node5);
        node6.setRightChild(node7);

        return new DefaultBinarySearchTreeImpl<>(root);
    }

    @Test
    void MultipleIteratorsUsingMultipleThreads()
            throws InterruptedException, ExecutionException {

        BinarySearchTree<Integer> tree = createTree();

        PreOrderBinaryTreeIterator<Integer> preOrder =
                new PreOrderBinaryTreeIterator<>(tree);

        InOrderBinaryTreeIterator<Integer> inOrder =
                new InOrderBinaryTreeIterator<>(tree);

        PostOrderBinaryTreeIterator<Integer> postOrder =
                new PostOrderBinaryTreeIterator<>(tree);

        ExecutorService executor =
                Executors.newFixedThreadPool(3);

        Future<List<Integer>> preOrderFuture =
                executor.submit(() -> {

                    List<Integer> result = new ArrayList<>();

                    while (preOrder.hasNext()) {
                        result.add(preOrder.next().getValue());
                    }

                    return result;
                });

        Future<List<Integer>> inOrderFuture =
                executor.submit(() -> {

                    List<Integer> result = new ArrayList<>();

                    while (inOrder.hasNext()) {
                        result.add(inOrder.next().getValue());
                    }

                    return result;
                });

        Future<List<Integer>> postOrderFuture =
                executor.submit(() -> {

                    List<Integer> result = new ArrayList<>();

                    while (postOrder.hasNext()) {
                        result.add(postOrder.next().getValue());
                    }

                    return result;
                });

        List<Integer> preOrderResult =
                preOrderFuture.get();

        List<Integer> inOrderResult =
                inOrderFuture.get();

        List<Integer> postOrderResult =
                postOrderFuture.get();

        assertEquals(
                Arrays.asList(4, 2, 1, 3, 6, 5, 7),
                preOrderResult
        );

        assertEquals(
                Arrays.asList(1, 2, 3, 4, 5, 6, 7),
                inOrderResult
        );

        assertEquals(
                Arrays.asList(1, 3, 2, 5, 7, 6, 4),
                postOrderResult
        );


        executor.shutdown();
        executor.awaitTermination(5, TimeUnit.SECONDS);
    }
}