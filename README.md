# Store-Management-System---Spring-MVC-Application
A Spring MVC project to manage products database of the store.


This projects is a Spring MVC project.
	                  

Description about the Project: This Project is for a Database website for items in a store. 
                               The Items have attributes: Name, Category, Department, Price, Color.
                               Items can be added into the database using this website.
                               Items can be viewed and filtered in this website.


Instructions to run the application:
    1. Run the java application class to run the project
    2. Navigate to the running port of localhost server. (http://localhost:8080)
    3. You land on the Welcome/index page of the application.  
    4. To create a new entry in the database, click the appropriate navigation link.
       This renders a page with a form to enter the data about the item.
       The item is added into the database only if it passes the validation.
    5. To view the items in the database, click the appropriate navigation link.
    6. To filter the list by category on listitems page, search the category in the search tab and hit search button
       This passes an optional request parameter in the url and renders a template which returns list of items of the
       given category.
    
    
                  
Classes: (package - project)
	
	1. Item.java 
	   -- Data Class - Class used to define the structure of an Item (product)
	   -- Fields: Integer id, String name, String category, String department, Double price, String color.
	   -- Validations: For each fields include, fields not being blank and limitations on the size of field
	                   entered by user. Price should be more than $1.00 and less than $100,000.00
	   -- Uses Lombok annotations for Getters, setters, constructors etc.
	   
	   -- REQUIREMENTS MET: - must have atleast 3 fields for the user to fill out
	                        - form must be validated on the server side (for every field)
	                        - use of lombok in data classes	                             
	                        
	                                                
	2. PageHitsCounter.java
	   -- A Class to represent the Page Hits Counter. 
	   -- Fields: Integer pageHitsCount - Represents the counter which keeps record of number of Page Hits.
	   -- Methods: Method to increment the Page Hits Counter.
	   -- Uses Lombok annotations for Getters, setters, constructors etc.
	   
	   -- REQUIREMENTS MET: - use of lombok in data classes
	                        - There must be an API that returns the number of page hits since the server was online	                  
	                       
	                        
	3. PageHitsConfig.java
	   -- A Spring Configuration Bean Class to configure the PageHitsCounter.
	   -- Configure PageHitsCounter and helps to inject it into controllers.
	   
	
	4. ItemRepository.java
	   -- A CRUD Repository for storing Data --> Items.
	   -- Implements methods like findByName, findByCategory.
	   
	           
	5. ItemController.java
	   -- A Spring MVC Controller.
	   -- Injects 2 dependencies - PageHitsCounter, ItemRepository
	   -- Get and Post Mapping for /createitem
	      Get request renders a webpage - a form that allows a user to create an entry for the item
	      Once the entries are validated on the server side with the given validations, the item is saved
	      into the AWS RDS MySQL Database. Once that is done, a new page with confirmation is rendered.
	      
	   -- Also, PageHitsCounter is incremented each time Get Request is sent for /createitem
	      this keeps track of number of page hits since the server was online.
	      
	   -- Get Mapping for /listitems -- the page to list all the items in the database
	      Also, it accepts an optional parameter for category
	      this filters the list of all items by the category provided
	      if there are any items by given category, a template filteredItemsList is rendered
	      with the items filtered by given category, else all the items are displayed and 
	      template itemList is rendered.
	      
	   -- REQUIREMENTS MET: - A page with a form where users have to input information
                            - must have atleast 3 fields for the user to fill out
                            - form must be validated on the server side (for every field)
                            - contents from the form should be persisted if it passes validation (saved into a database)
                            - A page that users can go to that lists the items created from the form 
                              in requirement 1 using Templates/Thymeleaf
                            - must take an optional get param to filter the list by an attribute (Category)
                            - There must be at-least 1 dependency injected into two different locations in the project (PageHitsCounter)
                            
                            
    6. PageHitsController.java
       -- A Rest Controller.
       -- Injects 1 dependency - PageHitsCounter.
       -- Get mapping for an API /current-page-hits
          just returns the current number of page hits
       
       -- REQUIREMENTS MET: - There must be an API that returns the number of page hits since the server was online  
                            - There must be at-least 1 dependency injected into two different locations in the project (PageHitsCounter)
                            
                            
                            
 
Templates: (Styled using Bootstrap)
           -- REQUIREMENTS MET: - Aesthetically pleasing website (e.x using css or frameworks)
    
    1. index.html
       -- A Welcome page, has navigation links to the create an item entry and view items in the database
    
    
    2. itemEntryForm.html
       -- A template which renders a form for user to create an new item entry and submit and save it to database.
       -- Also displays the number of page hits.  
       
       -- REQUIREMENTS MET: - A page with a form where users have to input information
                            - form must be validated on the server side (for every field)
                            - contents from the form should be persisted if it passes validation (saved into a database)
                            - This api (which returns page hits) should be called asynchronously every 3 seconds and 
                              the results displayed on every page
                              
                              
    3. itemSaved.html
       -- A page which gives confirmation that the item is saved
       -- Also displays the number of page hits.
      
       -- REQUIREMENTS MET: - This api (which returns page hits) should be called asynchronously every 3 seconds and 
                              the results displayed on every page
                              
                              
    4. itemList.html
       -- A page which lists all the items in the database.
       -- Has a search textbox for user to enter the category, which would then filter the items by category entered.
       -- Also displays the number of page hits.
      
       -- REQUIREMENTS MET: - A page that users can go to that lists the items created from the 
                              form in requirement 1 using Templates/Thymeleaf
                            - This api (which returns page hits) should be called asynchronously every 3 seconds and 
                              the results displayed on every page
                              
                              
    5. filteredItemsList.html
       -- A page which is only rendered if there are any items by the given category.
          This lists all the items filtered by the category (all items will have the same given category).
       -- Also displays the number of page hits.
       
       -- REQUIREMENTS MET: - A page that users can go to that lists the items created from the 
                              form in requirement 1 using Templates/Thymeleaf
                            - must take an optional get param to filter the list by an attribute
                            - This api (which returns page hits) should be called asynchronously every 3 seconds and 
                              the results displayed on every page




Test Class: (package - project)
 
    1. ItemControllerTest.java
       -- A test class to test all the functionalities of the ItemController.java Controller.
       -- REQUIREMENTS MET: - Classes should have unit tests                            
                              
                          
                          
                          
                          
                          
                          
                          
          
  
