package project;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.eq;

@WebMvcTest(ItemController.class)
public class ItemControllerTest {

	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private ItemRepository itemRepo;
	
	@MockBean
	private PageHitsCounter pageHits;
	
	@Test
	public void createItemGetTest() throws Exception {
		mockMvc.perform(get("/createitem"))
		.andExpect(status().isOk())
		.andExpect(view().name("itemEntryForm"))
		.andExpect(model().attribute("item", new Item()));
	}
	
	
	@Test
	public void createItemPostTest_ValidItem() throws Exception {
		Item expectedCreatedItem = new Item(null, "Snow Fast", "Snow Brushes", "Automobile Accessories", 19.54, "Red");
		mockMvc.perform(post("/createitem")
				.param("name", "Snow Fast")
				.param("category", "Snow Brushes")
				.param("department", "Automobile Accessories")
				.param("price", "19.54")
				.param("color", "Red"))
		.andExpect(status().isOk())
		.andExpect(view().name("itemSaved"))
		.andExpect(model().attribute("item", expectedCreatedItem));
		
		Mockito.verify(itemRepo).save(eq(expectedCreatedItem));
	}
	
	
	@Test
	public void createItemPostTest_InvalidItem() throws Exception {
		// Price less than $1
		Item invalidTestItem = new Item(null, "Frontier BladeMax Blenders", "Blenders", "Kitchen Appliances", -9.89, "White");
		
		mockMvc.perform(post("/createitem")
				.param("name", "Frontier BladeMax Blenders")
				.param("category", "Blenders")
				.param("department", "Kitchen Appliances")
				.param("price", "-9.89")
				.param("color", "White"))
		.andExpect(status().isOk())
		.andExpect(view().name("itemEntryForm"))
		.andExpect(model().attribute("item", invalidTestItem));
		
		Mockito.verifyNoInteractions(itemRepo);
	}
	
	
	@Test
	public void listAllItemsTest() throws Exception {
		List<Item> expectedItemsList = new ArrayList<>();
		expectedItemsList.add(new Item(null, "Snow Fast", "Snow Brushes", "Automobile Accessories", 29.54, "Red"));
		expectedItemsList.add(new Item(null, "Swordy Blade", "Knives", "Kitchen Cutlery", 15.99, "Silver"));
		Mockito.when(itemRepo.findAll()).thenReturn(expectedItemsList);
		
		mockMvc.perform(get("/listitems"))
		.andExpect(status().isOk())
		.andExpect(view().name("itemList"))
		.andExpect(model().attribute("allItems", expectedItemsList));
	}
	
	
	@Test
	public void listAllItemsByCategoryTest() throws Exception {
		List<Item> expectedItemsList = new ArrayList<>();
		expectedItemsList.add(new Item(null, "Snow Fast", "Snow Brushes", "Automobile Accessories", 59.99, "Red"));
		expectedItemsList.add(new Item(null, "Remove Fast", "Snow Brushes", "Automobile Accessories", 31.99, "Blue"));
		
		Mockito.when(itemRepo.findByCategory("Snow Brushes")).thenReturn(expectedItemsList);
		
		mockMvc.perform(get("/listitems?category=Snow Brushes"))
		.andExpect(status().isOk())
		.andExpect(view().name("filteredItemsList"))
		.andExpect(model().attribute("filteredItems", expectedItemsList))
		.andExpect(model().attribute("givenCategory", "Snow Brushes"));
	}
}






