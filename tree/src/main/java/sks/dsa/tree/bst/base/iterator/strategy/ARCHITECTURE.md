/**
 * DECOUPLED TREE ITERATOR ARCHITECTURE
 * 
 * This document explains the decoupled iterator design that allows any tree type 
 * to be traversed using any traversal strategy without coupling the tree structure 
 * to the traversal logic.
 * 
 * ============================================================================
 * DESIGN PATTERN: STRATEGY PATTERN
 * ============================================================================
 * 
 * The design uses the Strategy pattern to decouple tree traversal from tree structure:
 * 
 *    1. TraversalStrategy<ValueType, NodeType>
 *       - Interface defining how to traverse any tree node type
 *       - Methods: initialize(), hasNext(), next(), reset()
 *    
 *    2. PreOrderStrategy<ValueType> & InOrderStrategy<ValueType>
 *       - Concrete implementations of specific traversal algorithms
 *       - Stack-based iterative approach (memory efficient)
 *    
 *    3. StrategyBasedTreeIterator<ValueType, NodeType, TreeType>
 *       - Generic iterator that uses any TraversalStrategy
 *       - Works with any tree type and node type
 *       - Fully decoupled from tree structure
 * 
 * ============================================================================
 * PACKAGE STRUCTURE
 * ============================================================================
 * 
 * sks.dsa.tree.bst.base.iterator.strategy/
 *   ├── TraversalStrategy.java
 *   ├── StrategyBasedTreeIterator.java
 *   └── binaryTree/
 *       ├── PreOrderStrategy.java
 *       ├── InOrderStrategy.java
 *       └── (PostOrderStrategy.java - can be added)
 * 
 * ============================================================================
 * USAGE EXAMPLES
 * ============================================================================
 * 
 * 1. BASIC PRE-ORDER TRAVERSAL
 *    ========================
 * 
 *    BinarySearchTree<Integer> tree = createYourTree();
 *    StrategyBasedTreeIterator<Integer, BinaryNode<Integer>, BinarySearchTree<Integer>> 
 *        iterator = new StrategyBasedTreeIterator<>(tree, new PreOrderStrategy<>());
 * 
 *    while (iterator.hasNext()) {
 *        BinaryNode<Integer> node = iterator.next();
 *        System.out.println(node.getValue());
 *    }
 * 
 * 
 * 2. BASIC IN-ORDER TRAVERSAL
 *    ========================
 * 
 *    BinarySearchTree<Integer> tree = createYourTree();
 *    StrategyBasedTreeIterator<Integer, BinaryNode<Integer>, BinarySearchTree<Integer>> 
 *        iterator = new StrategyBasedTreeIterator<>(tree, new InOrderStrategy<>());
 * 
 *    while (iterator.hasNext()) {
 *        BinaryNode<Integer> node = iterator.next();
 *        System.out.println(node.getValue());
 *    }
 * 
 * 
 * 3. SWITCH STRATEGIES AT RUNTIME
 *    =============================
 * 
 *    BinarySearchTree<Integer> tree = createYourTree();
 *    
 *    // Pre-order traversal
 *    StrategyBasedTreeIterator<Integer, BinaryNode<Integer>, BinarySearchTree<Integer>> 
 *        preOrderIterator = new StrategyBasedTreeIterator<>(tree, new PreOrderStrategy<>());
 *    collectResults(preOrderIterator);  // [1, 2, 3, 4, 5]
 *    
 *    // In-order traversal on the same tree
 *    StrategyBasedTreeIterator<Integer, BinaryNode<Integer>, BinarySearchTree<Integer>> 
 *        inOrderIterator = new StrategyBasedTreeIterator<>(tree, new InOrderStrategy<>());
 *    collectResults(inOrderIterator);   // [1, 2, 3, 4, 5]
 * 
 * 
 * 4. USING WITH FOR-EACH LOOPS
 *    ==========================
 * 
 *    BinarySearchTree<Integer> tree = createYourTree();
 *    StrategyBasedTreeIterator<Integer, BinaryNode<Integer>, BinarySearchTree<Integer>> 
 *        iterator = new StrategyBasedTreeIterator<>(tree, new PreOrderStrategy<>());
 *    
 *    // Manual for-loop using iterator methods
 *    List<Integer> results = new ArrayList<>();
 *    while (iterator.hasNext()) {
 *        results.add(iterator.next().getValue());
 *    }
 * 
 * 
 * 5. RESETTING THE ITERATOR
 *    =======================
 * 
 *    StrategyBasedTreeIterator<Integer, BinaryNode<Integer>, BinarySearchTree<Integer>> 
 *        iterator = new StrategyBasedTreeIterator<>(tree, new PreOrderStrategy<>());
 *    
 *    // First traversal
 *    while (iterator.hasNext()) {
 *        process(iterator.next());
 *    }
 *    
 *    // Reset to start over
 *    iterator.reset();
 *    
 *    // Second traversal
 *    while (iterator.hasNext()) {
 *        process(iterator.next());
 *    }
 * 
 * 
 * 6. GETTING TREE INFORMATION
 *    ==========================
 * 
 *    StrategyBasedTreeIterator<Integer, BinaryNode<Integer>, BinarySearchTree<Integer>> 
 *        iterator = new StrategyBasedTreeIterator<>(tree, new PreOrderStrategy<>());
 *    
 *    int treeSize = iterator.size();              // Total nodes in tree
 *    BinaryNode<Integer> root = iterator.getRoot(); // Root node
 *    BinarySearchTree<Integer> t = iterator.getTree(); // Original tree
 *    int position = iterator.getPosition();       // Current position (0-based)
 * 
 * ============================================================================
 * HOW TO ADD A NEW TRAVERSAL STRATEGY
 * ============================================================================
 * 
 * To add a new traversal strategy (e.g., PostOrderStrategy, BFS, LevelOrder):
 * 
 * 1. Create a new class implementing TraversalStrategy:
 * 
 *    public class PostOrderStrategy<ValueType> 
 *        implements TraversalStrategy<ValueType, BinaryNode<ValueType>>
 *    {
 *        private Stack<BinaryNode<ValueType>> traversalStack = new Stack<>();
 *        private BinaryNode<ValueType> root;
 *        private BinaryNode<ValueType> lastVisited = null;
 *        
 *        @Override
 *        public void initialize(BinaryNode<ValueType> root) {
 *            this.root = root;
 *            traversalStack.clear();
 *            if (root != null) {
 *                pushLeftChildren(root);
 *            }
 *        }
 *        
 *        @Override
 *        public boolean hasNext() {
 *            return !traversalStack.isEmpty();
 *        }
 *        
 *        @Override
 *        public BinaryNode<ValueType> next() {
 *            BinaryNode<ValueType> current = traversalStack.peek();
 *            // Post-order logic: Left -> Right -> Root
 *            if (current.getRightChild() != null && 
 *                lastVisited != current.getRightChild() && 
 *                current.getLeftChild() != lastVisited) {
 *                pushLeftChildren(current.getRightChild());
 *            } else {
 *                current = traversalStack.pop();
 *                lastVisited = current;
 *            }
 *            return current;
 *        }
 *        
 *        @Override
 *        public void reset() {
 *            initialize(root);
 *            lastVisited = null;
 *        }
 *        
 *        private void pushLeftChildren(BinaryNode<ValueType> node) {
 *            // Implementation...
 *        }
 *    }
 * 
 * 2. Use it immediately with StrategyBasedTreeIterator:
 * 
 *    StrategyBasedTreeIterator<Integer, BinaryNode<Integer>, BinarySearchTree<Integer>> 
 *        iterator = new StrategyBasedTreeIterator<>(tree, new PostOrderStrategy<>());
 * 
 * ============================================================================
 * ADVANTAGES OF THIS DESIGN
 * ============================================================================
 * 
 * ✓ DECOUPLING: Tree structure is completely independent of traversal strategy
 * ✓ REUSABILITY: Any traversal strategy can work with any tree type
 * ✓ EXTENSIBILITY: Add new strategies without modifying existing code
 * ✓ FLEXIBILITY: Switch strategies at runtime without reconstruction
 * ✓ MEMORY EFFICIENCY: Stack-based iterative approach (O(log h) space)
 * ✓ TYPE SAFETY: Generic types ensure compile-time type checking
 * ✓ TESTABILITY: Each strategy can be tested independently
 * 
 * ============================================================================
 * PERFORMANCE CHARACTERISTICS
 * ============================================================================
 * 
 * All strategies use stack-based iterative approaches (no recursion):
 * 
 * Time Complexity:  O(n) - visit each node exactly once
 * Space Complexity: O(log h) - where h is tree height (stack size)
 * 
 * This is much better than recursive approaches which use O(h) call stack space.
 * 
 * ============================================================================
 * TEST COVERAGE
 * ============================================================================
 * 
 * PreOrderStrategyTest (8 tests):
 *   ✓ Balanced tree traversal
 *   ✓ Single node tree
 *   ✓ Left-skewed tree
 *   ✓ Right-skewed tree
 *   ✓ Complex tree structure
 *   ✓ Null root handling
 *   ✓ Reset functionality
 *   ✓ Exception handling
 * 
 * StrategyBasedTreeIteratorTest (9 tests):
 *   ✓ Pre-order traversal with strategy
 *   ✓ In-order traversal with strategy
 *   ✓ Iterator position tracking
 *   ✓ Iterator reset functionality
 *   ✓ Tree and root getters
 *   ✓ Strategy switching at runtime
 *   ✓ Exception handling
 *   ✓ Single node tree
 *   ✓ Tree size retrieval
 * 
 * ============================================================================
 * CLASS HIERARCHY
 * ============================================================================
 * 
 * SelfReferentialNode<ValueType>  (Interface)
 *     ↑
 *     └── BinaryNode<ValueType>  (Interface)
 *             ↑
 *             └── BinarySearchNode<ValueType>  (Interface)
 *                     ↑
 *                     └── DefaultBinarySearchNodeImpl<ValueType>  (Concrete)
 * 
 * 
 * Tree<ValueType, NodeType>  (Interface)
 *     ↑
 *     └── BinaryTree<ValueType>  (Interface)
 *             ↑
 *             └── BinarySearchTree<ValueType>  (Interface)
 *                     ↑
 *                     └── DefaultBinarySearchTreeImpl<ValueType>  (Concrete)
 * 
 * 
 * TraversalStrategy<ValueType, NodeType>  (Interface)
 *     ↑
 *     ├── PreOrderStrategy<ValueType>  (Concrete)
 *     └── InOrderStrategy<ValueType>  (Concrete)
 * 
 * 
 * Iterator<NodeType>  (Java Interface)
 *     ↑
 *     └── StrategyBasedTreeIterator<ValueType, NodeType, TreeType>  (Concrete)
 * 
 * ============================================================================
 * 
 * Created: 2026-08-01
 * Version: 1.0
 * Author: Copilot
 * 
 * This architecture enables true separation of concerns:
 * - Trees don't need to know about traversal strategies
 * - Traversal strategies don't need to know about tree structure
 * - Iterators can work with any combination of tree and strategy
 */
