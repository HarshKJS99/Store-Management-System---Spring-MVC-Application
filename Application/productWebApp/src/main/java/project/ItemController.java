package project;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class ItemController {

	@Autowired
	private ItemRepository itemRepo;
	
	@Autowired
	private PageHitsCounter pageHits;
	
	// Get Mapping for Welcome (Index) page of the website
	// Page Hits is incremented every time this is called
	@GetMapping("/")
	public String indexPage() {
		pageHits.incrementPageHitsCount();
		return "index";
	}
	
	
	// Get Mapping - renders a page with a form which allows a user to create an entry in the database
	// Page Hits is incremented every time this is called
	@GetMapping("/createitem")
	public String itemEntryForm(Model model) {
		model.addAttribute("item", new Item());
		pageHits.incrementPageHitsCount();
		return "itemEntryForm";
	}
	
	
	// Post Mapping - saves the item entry into the database if the data fields are valid
	// If its not valid, returns the same form
	@PostMapping("/createitem")
	public String createUser(@Valid Item itemToSave, BindingResult bindingResult) {
		if(bindingResult.hasErrors()) {
			return "itemEntryForm";
		} else {
			this.itemRepo.save(itemToSave);
			return "itemSaved";
		}
	}
	
	
	// Renders a page which displays all the items with their details
	// Accepts an optional parameter to filter the list by a category
	// Page Hits is incremented every time this is called
	@GetMapping("/listitems")
	public String listAllUsers(@RequestParam(required = false) String category, Model model) {
		
		pageHits.incrementPageHitsCount();
		
		// If there are items of the given category, renders another page 
		// which displays all the items of the given category
		if (!this.itemRepo.findByCategory(category).isEmpty()) {
			model.addAttribute("filteredItems", this.itemRepo.findByCategory(category));
			model.addAttribute("givenCategory", category);
			
			return "filteredItemsList";
		}
		
		// If there are no items of the given category, render the page with all the items
		model.addAttribute("allItems", this.itemRepo.findAll());
		
		return "itemList";
	}
	
}






