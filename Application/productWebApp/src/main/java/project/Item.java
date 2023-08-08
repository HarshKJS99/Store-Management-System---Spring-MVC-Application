package project;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Item {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;
	
	@NotBlank(message = "The Name cannot be Blank!")
	@Size(min = 2, max = 100, message = "Number of characters must be between 2 and 100!")
	private String name;
	
	@NotBlank(message = "The Category field cannot be Blank!")
	@Size(min = 2, max = 50, message = "Number of characters must be between 2 and 50!")
	private String category;
	
	private String department;
	
	@NotNull
	@Min(value = 1, message = "The Price must be Greater than $1.00!")
	@Max(value = 100000, message = "The Price must be less than or equal to $100,000.00!")
	private Double price;
	
	@NotBlank(message = "The Color field cannot be Blank!")
	@Size(min = 2, max = 50, message = "Number of characters must be between 2 and 50!")
	private String color;
}






