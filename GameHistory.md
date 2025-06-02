# A MongoDB-based Roulette Game History


## A Domain class for RouletteGameHistory
- The class shall be annotated with @Document(collection = "rouletteGameHistory") to specify the MongoDB collection.
- The id field shall be annotated with @Id to map it to the MongoDB _id field.
- Other fields store relevant information about each roulette game played, such as the player ID, game date, result, bet amount, and bet type.
- This class can be used with a Spring Data MongoDB repository interface to perform CRUD operations on roulette game history documents.
- Convert it to a Spring Domain class annotated with @Document
- Bet
- Win
- Winner
- Stat
- Group
- 

## A Repository for CRUD and Query methods

## Custom queries - Mongo Templates based Method interfaces in Repository interface

Tiers:
  put(209, Arrays.asList(27, 30));//Tiers
  put(211, Arrays.asList(33, 36));//Tiers
  put(219, Arrays.asList(23, 24));//Tiers
  put(226, Arrays.asList(5, 8));//Tires
  put(239, Arrays.asList(10, 11));//Tiers
  put(253, Arrays.asList(13, 16));//Tiers

Orpheline:
  put(101, Arrays.asList(1));//orpheline
  put(202, Arrays.asList(6, 9));//orpheline
  put(229, Arrays.asList(14, 17));//orpheline
  put(230, Arrays.asList(17, 20));//orpheline  
  put(259, Arrays.asList(31, 34));//orpheline

Voisins:  
  put(300, Arrays.asList(0, 2, 3)); // Voisins
  put(250, Arrays.asList(4, 7));//Voisins
  put(204, Arrays.asList(12, 15));//Voisins
  put(206, Arrays.asList(18, 21));//Voisins
  put(255, Arrays.asList(19, 22));//Voisins
  put(235, Arrays.asList(32, 35));//Voisins
  put(419, Arrays.asList(25, 26, 28, 29));//Voisins
  
Zero:
  put(126, Arrays.asList(26));//Zero
  put(200, Arrays.asList(0, 3));//Zero
  put(204, Arrays.asList(12, 15));//Voisins/Zero
  put(235, Arrays.asList(32, 35));//Voisins/Zero
  

  
  
  