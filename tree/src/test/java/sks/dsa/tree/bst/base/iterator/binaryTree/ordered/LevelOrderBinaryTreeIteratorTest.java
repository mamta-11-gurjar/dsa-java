package sks.dsa.tree.bst.base.iterator.binaryTree.ordered;

import org.junit.jupiter.api.Test;
import sks.dsa.tree.bst.base.node.binaryTree.BinaryNode;
import sks.dsa.tree.bst.base.node.binaryTree.searchTree.BinarySearchNode;
import sks.dsa.tree.bst.base.node.binaryTree.searchTree.DefaultBinarySearchNodeImpl;
import sks.dsa.tree.bst.base.tree.binaryTree.searchTree.DefaultBinarySearchTreeImpl;
import sks.dsa.tree.bst.base.tree.binaryTree.BinaryTree;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class LevelOrderBinaryTreeIteratorTest {

    private <T> List<T> collectValues(LevelOrderBinaryTreeIterator<T> it) {
        List<T> out = new ArrayList<>();
        while (it.hasNext()) {
            BinaryNode<T> n = it.next();
            out.add(n.getValue());
        }
        return out;
    }

    @Test
    void testEmptyTree() {
        BinaryTree<Integer> emptyTree = new BinaryTree<>() {
            @Override public BinaryNode<Integer> getRoot(){ return null; }
            @Override public int getMaxTreeDepth(){ return 0; }
            @Override public int getMaxTreeHeight(){ return 0; }
            @Override public int getNumberOfChildren(){ return 0; }
            @Override public int size(){ return 0; }
            @Override public java.util.Iterator<BinaryNode<Integer>> iterator(){ return new java.util.ArrayList<BinaryNode<Integer>>().iterator(); }
            @Override public boolean isTreeBalanced(){ return false; }
            @Override public boolean isTreeComplete(){ return false; }
            @Override public BinaryTree<Integer> getLeftSubTree(){ return null; }
            @Override public BinaryTree<Integer> getRightSubTree(){ return null; }
        };
        LevelOrderBinaryTreeIterator<Integer> it = new LevelOrderBinaryTreeIterator<>(emptyTree);
        assertTrue(collectValues(it).isEmpty());
    }

    @Test
    void testSingleNode() {
        BinarySearchNode<Integer> root = new DefaultBinarySearchNodeImpl<>(1);
        DefaultBinarySearchTreeImpl<Integer> tree = new DefaultBinarySearchTreeImpl<>(root);
        LevelOrderBinaryTreeIterator<Integer> it = new LevelOrderBinaryTreeIterator<>(tree);
        assertEquals(List.of(1), collectValues(it));
    }

    @Test
    void testRootWithTwoChildren() {
        BinarySearchNode<Integer> root = new DefaultBinarySearchNodeImpl<>(1);
        BinarySearchNode<Integer> left = new DefaultBinarySearchNodeImpl<>(2);
        BinarySearchNode<Integer> right = new DefaultBinarySearchNodeImpl<>(3);
        root.setLeftChild(left);
        root.setRightChild(right);
        DefaultBinarySearchTreeImpl<Integer> tree = new DefaultBinarySearchTreeImpl<>(root);
        assertEquals(List.of(1,2,3), collectValues(new LevelOrderBinaryTreeIterator<>(tree)));
    }

    @Test
    void testCompleteThreeLevel() {
        BinarySearchNode<Integer> root = new DefaultBinarySearchNodeImpl<>(4);
        BinarySearchNode<Integer> l = new DefaultBinarySearchNodeImpl<>(2);
        BinarySearchNode<Integer> r = new DefaultBinarySearchNodeImpl<>(6);
        BinarySearchNode<Integer> ll = new DefaultBinarySearchNodeImpl<>(1);
        BinarySearchNode<Integer> lr = new DefaultBinarySearchNodeImpl<>(3);
        BinarySearchNode<Integer> rl = new DefaultBinarySearchNodeImpl<>(5);
        BinarySearchNode<Integer> rr = new DefaultBinarySearchNodeImpl<>(7);
        root.setLeftChild(l); l.setLeftChild(ll); l.setRightChild(lr);
        root.setRightChild(r); r.setLeftChild(rl); r.setRightChild(rr);
        DefaultBinarySearchTreeImpl<Integer> tree = new DefaultBinarySearchTreeImpl<>(root);
        assertEquals(List.of(4,2,6,1,3,5,7), collectValues(new LevelOrderBinaryTreeIterator<>(tree)));
    }

    @Test
    void testLeftSkewed() {
        BinarySearchNode<Integer> root = new DefaultBinarySearchNodeImpl<>(1);
        BinarySearchNode<Integer> n2 = new DefaultBinarySearchNodeImpl<>(2);
        BinarySearchNode<Integer> n3 = new DefaultBinarySearchNodeImpl<>(3);
        root.setLeftChild(n2); n2.setLeftChild(n3);
        DefaultBinarySearchTreeImpl<Integer> tree = new DefaultBinarySearchTreeImpl<>(root);
        assertEquals(List.of(1,2,3), collectValues(new LevelOrderBinaryTreeIterator<>(tree)));
    }

    @Test
    void testRightSkewed() {
        BinarySearchNode<Integer> root = new DefaultBinarySearchNodeImpl<>(1);
        BinarySearchNode<Integer> n2 = new DefaultBinarySearchNodeImpl<>(2);
        BinarySearchNode<Integer> n3 = new DefaultBinarySearchNodeImpl<>(3);
        root.setRightChild(n2); n2.setRightChild(n3);
        DefaultBinarySearchTreeImpl<Integer> tree = new DefaultBinarySearchTreeImpl<>(root);
        assertEquals(List.of(1,2,3), collectValues(new LevelOrderBinaryTreeIterator<>(tree)));
    }

    @Test
    void testUnbalanced() {
        BinarySearchNode<Integer> root = new DefaultBinarySearchNodeImpl<>(1);
        BinarySearchNode<Integer> b = new DefaultBinarySearchNodeImpl<>(2);
        BinarySearchNode<Integer> c = new DefaultBinarySearchNodeImpl<>(3);
        BinarySearchNode<Integer> d = new DefaultBinarySearchNodeImpl<>(4);
        root.setLeftChild(b); root.setRightChild(d);
        b.setRightChild(c);
        DefaultBinarySearchTreeImpl<Integer> tree = new DefaultBinarySearchTreeImpl<>(root);
        assertEquals(List.of(1,2,4,3), collectValues(new LevelOrderBinaryTreeIterator<>(tree)));
    }

    @Test
    void testDuplicates() {
        BinarySearchNode<Integer> root = new DefaultBinarySearchNodeImpl<>(1);
        BinarySearchNode<Integer> a = new DefaultBinarySearchNodeImpl<>(2);
        BinarySearchNode<Integer> b = new DefaultBinarySearchNodeImpl<>(2);
        BinarySearchNode<Integer> c = new DefaultBinarySearchNodeImpl<>(3);
        BinarySearchNode<Integer> d = new DefaultBinarySearchNodeImpl<>(3);
        root.setLeftChild(a); root.setRightChild(b);
        a.setLeftChild(c); a.setRightChild(d);
        DefaultBinarySearchTreeImpl<Integer> tree = new DefaultBinarySearchTreeImpl<>(root);
        assertEquals(List.of(1,2,2,3,3), collectValues(new LevelOrderBinaryTreeIterator<>(tree)));
    }

    @Test
    @SuppressWarnings("unchecked")
    void testLargeBreadth() {
        int n = 15;
        DefaultBinarySearchNodeImpl<Integer>[] nodes = new DefaultBinarySearchNodeImpl[n];
        for (int i=0;i<n;i++) nodes[i] = new DefaultBinarySearchNodeImpl<>(i+1);
        for (int i=0;i<n;i++) {
            int leftIdx = 2*i+1;
            int rightIdx = 2*i+2;
            if (leftIdx < n) nodes[i].setLeftChild(nodes[leftIdx]);
            if (rightIdx < n) nodes[i].setRightChild(nodes[rightIdx]);
        }
        DefaultBinarySearchTreeImpl<Integer> tree = new DefaultBinarySearchTreeImpl<>(nodes[0]);
        List<Integer> expected = new ArrayList<>();
        for (int i=1;i<=n;i++) expected.add(i);
        assertEquals(expected, collectValues(new LevelOrderBinaryTreeIterator<>(tree)));
    }
}
