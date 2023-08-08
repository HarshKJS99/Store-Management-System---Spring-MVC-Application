package project;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface ItemRepository extends CrudRepository<Item, Integer> {


	public Item findByName(String name);
	public List<Item> findByCategory(String category);
}

