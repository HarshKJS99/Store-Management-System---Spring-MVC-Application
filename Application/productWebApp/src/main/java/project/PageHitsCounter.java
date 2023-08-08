package project;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PageHitsCounter {
	
	@NotNull
	@Min(0)
	private Integer pageHitsCount;
	
	// Method to increment the Page Hits
	public void incrementPageHitsCount() {
		this.pageHitsCount += 1;
	}
}
	