package sks.dsa.tree.bst.base.iterator.binaryTree.ordered;

import org.junit.jupiter.api.Test;
import sks.dsa.tree.bst.base.node.binaryTree.BinaryNode;
import sks.dsa.tree.bst.base.node.binaryTree.searchTree.BinarySearchNode;
import sks.dsa.tree.bst.base.node.binaryTree.searchTree.DefaultBinarySearchNodeImpl;
import sks.dsa.tree.bst.base.tree.binaryTree.searchTree.BinarySearchTree;
import sks.dsa.tree.bst.base.tree.binaryTree.searchTree.DefaultBinarySearchTreeImpl;

import java.util.List;
import java.util.Spliterator;
import java.util.Spliterators;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MultipleIteratorsUsingStreamsTest {

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
    void testMultipleIteratorsUsingStreams() {

        BinarySearchTree<Integer> tree = createTree();

        PreOrderBinaryTreeIterator<Integer> preOrder =
                new PreOrderBinaryTreeIterator<>(tree);

        InOrderBinaryTreeIterator<Integer> inOrder =
                new InOrderBinaryTreeIterator<>(tree);

        PostOrderBinaryTreeIterator<Integer> postOrder =
                new PostOrderBinaryTreeIterator<>(tree);

        List<Integer> preOrderResult = StreamSupport.stream(
                        Spliterators.spliteratorUnknownSize(
                                preOrder,
                                Spliterator.ORDERED
                        ),
                        true
                )
                .map(BinaryNode::getValue)
                .limit(2)
                .collect(Collectors.toList());

        List<Integer> postOrderResult = StreamSupport.stream(
                        Spliterators.spliteratorUnknownSize(
                                postOrder,
                                Spliterator.ORDERED
                        ),
                        true
                )
                .map(BinaryNode::getValue)
                .limit(2)
                .collect(Collectors.toList());

        List<Integer> inOrderResult = StreamSupport.stream(
                        Spliterators.spliteratorUnknownSize(
                                inOrder,
                                Spliterator.ORDERED
                        ),
                        true
                )
                .map(BinaryNode::getValue)
                .limit(2)
                .collect(Collectors.toList());

        assertEquals(List.of(4, 2), preOrderResult);
        assertEquals(List.of(1, 3), postOrderResult);
        assertEquals(List.of(1, 2), inOrderResult);
    }
}