/**
 * 
 */
package com.mediator.example.itemcategory;

import java.util.HashMap;
import java.util.Map;

/**
 * @author su828791
 *
 */
public class AssociationMediator implements MediatorFace{
	
	private Category category;
	private Item item;
	Map<Item,Category> itemCategoryMap = null;

	/**
	 * 
	 */
	public AssociationMediator() {
		// TODO Auto-generated constructor stub
		itemCategoryMap = new HashMap<Item,Category>();
	}

	@Override
	public void registerItem(Item item) {
		// TODO Auto-generated method stub
		this.item=item;
		
	}

	@Override
	public void registerCategory(Category cat) {
		// TODO Auto-generated method stub
		this.category=cat;
	}

	@Override
	public void add(Item item, Category cat) {
		// TODO Auto-generated method stub
		itemCategoryMap.put(item, cat);
	}

	@Override
	public void delete(Item item, Category cat) {
		// TODO Auto-generated method stub
		itemCategoryMap.remove(item);
		
	}

}
