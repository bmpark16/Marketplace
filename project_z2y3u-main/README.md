# Bryan's Personal Project : FashionShop 

## A Fashion Store 

This application will be similar to apps such as StockX, Goat, Ebay and Craigslist where users can view and buy
fashion items such as shoes and other clothing items. Everybody can use use this application to purchase clothing, but
mostly people that are interested in fashion or shoes will be using this app. This project interests me because I 
personally like shoes and clothing, especially browsing different catalogues and looking at things that I can do not
have the financial powers to own. 

## User Stories

- As a user, I wish to be able to look at different fashion items. 
- As a user, I wish to be able to add shoes/clothes to a wishlist 
- As a user, I wish to be able to remove shoes/clothes from a wishlist. 
- As a user, I wish to be able to save my wishlists to view all the clothes I was interested in. 
- As a user, I wish to be able to buy clothes/shoes. 
- As a user, I wish to be able to view the list of items that I have bought.
- As a user, I wish to be able to list my clothes/shoes. 
- As a user, when I wish to quit the application from the menu, I want to be given the option to save my wishlist,
purchased list, listings list and bid list. 
- As a user, when I first start the app, I want to be given the option to load my saved lists. 

## Instruction for Grader 

- The first action related to the related user story "adding multiple Xs to a Y" in multiple ways.
  1) One way is to start the App, click on "View Listings" button. This will take you to the page where you can view 
  the apparels that have been listed for sale. Click on an item you wish to view, and click "Add to Wishlist". This 
  will add the selected item to your wishlist, which you can view it at the "My Wishlist" page that is available when 
  click "My Account" on the main menu. 
  2) Another is to select an item from the "View Listings" page, and then place a bid by editing the textbox that is 
  below. If you bid over the listed bid price but under the buy now price, this will add the item to the "my biddings"
  list, which can be viewed through the "My Bids" tab on my Account. If your bid matches or exceeds the buy 
  now price, it will add to the "Purchased" list, which can be viewed through the "Purchased History" tab on My Account. 
  3) On the main menu, if you select "List Items", you can list an item you wish to view. If you fill out the text boxes
  and click "List Item", a pop up message will appear, signaling a successful listing. This adds the item to the forSale
  list and the "My Listings" list, which can be viewed in the "My Listings" tab on My Account. 

- The second action related to the user story removing selected items off a list. If you go to any viewing page from My
Account, there is a "Remove" button on the bottom. If you select an item and press the button, this will remove the 
item off the list. 

- My visual component can be viewed on the main menu when you first start up the app, which is the picture of the
clothes. 

- You can save the state of the application by pressing the "Save" button on the main menu
- You can load the saved state of the application by pressing the "Load" button on the main menu. 

## Phase 4 : Task 2 

Tue Apr 02 21:41:48 PDT 2024

Windrunner Jacket has been added to Market

Tue Apr 02 21:41:48 PDT 2024

Windrunner Jacket has been added to My Listings

Tue Apr 02 21:41:56 PDT 2024

Logo Hoodie has been added to wishlist

Tue Apr 02 21:42:01 PDT 2024

YEEZY 350 SESAME has been added to bidding history

Tue Apr 02 21:42:05 PDT 2024

AJ1 RED TOES has been added to purchase history

Tue Apr 02 21:42:19 PDT 2024

AJ1 RED TOES has been removed from purchased history

## Phase 4 : Task 3 
From my UML Diagram, I feel like that I have way too many associations between class. Especially, the individual windows
that display the different lists in my account all have association to the list of Apparels. If I had more time on the 
project, I would have tried to have this list of Apparel on the abstract class "Individual Accounts Window". This way, 
since all of my individual windows extend the abstract class, I wouldn't need to have the lists as fields in these 
classes, but rather, have a method in my abstract class that allows me to get the required list depending on which 
window is made. 

