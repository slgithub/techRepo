/**
 * 
 */
package com.mediator.example.itemcategory;

/**
 * @author su828791
 *
 */
public interface MediatorFace {
	
	void registerItem(Item item);
	void registerCategory(Category cat);
	
	
    void add(Item item,Category cat);
    void delete(Item item,Category cat);
}
