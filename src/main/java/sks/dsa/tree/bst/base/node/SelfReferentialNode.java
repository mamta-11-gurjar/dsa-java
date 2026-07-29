package sks.dsa.tree.bst.base.node;

/**
 * An interface which specifies having a/many next reference(s) that could point to another self-type node
 * @param <T> Type of value this node can hold
 */
public interface SelfReferentialNode<T> extends Node<T, SelfReferentialNode<T>>
{}
