package project;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PageHitsController {
	
	@Autowired
	private PageHitsCounter pageHits;
	
	// API which returns current page hits
	@GetMapping("/current-page-hits")
	public Integer getCurrentPageHits() {
		return pageHits.getPageHitsCount();
	}
}