package sks.dsa.tree.bst.base.node;

import java.util.List;
import java.util.Optional;

/**
 * A node is a data structure which can store a value and a/many next pointer(s)
 * @param <ValueType> Type of the value this node can store
 * @param <NextPointerDataType> Data-Type of the next-pointer(s)
 */
public interface Node<ValueType, NextPointerDataType>
{
    /**
     * Get the value stored within this node
     * @return stored value
     */
    ValueType getValue();

    /**
     * All next references -> n=this node's children
     * @return a list of alll the next references
     */
    List<? extends NextPointerDataType> getChildren();

    /**
     * Get the nth child of this self-referential data-structure
     * @param childNumber the child to be returned
     * @return self-referential child node
     */
    NextPointerDataType getChild(int childNumber);

    /**
     * As a self-referential node can have multiple children hence returning zeroth index child looks good for now
     * @return the zeroth index child
     */
    NextPointerDataType getNext();

    /**
     * Set the nth child of this self-referential data-structure
     * @param childNumber the child to be returned
     * @param nextElement self-referential child node
     */
     void setChild(int childNumber, final NextPointerDataType nextElement);

    /**
     * As a self-referential node can have multiple children hence providing setter for zeroth index child looks good for now
     * @param nextElement the zeroth index child
     */
     void setNext(final NextPointerDataType nextElement);

    /**
     * Whether next-pointers have any values
     * @return if next pointers have non-null values
     */
    boolean hasNext();

    /**
     * Provide optional results for next operation
     * @return Optional Next-Reference
     */
    default Optional<? extends NextPointerDataType> getOptionalNext() {
        return Optional.ofNullable(getNext());
    }

    /**
     * As node can store many next pointers hence this is a queryable method for the maximum number of next pointers
     * @return maximum number of next pointers
     */
    int maxNumberOfNextPointers();
}
