Cell Society Design
==================

## Introduction

This section describes the problem your team is trying to solve by writing this program, the primary design goals of the project (i.e., where is it most flexible), and the primary architecture of the design (i.e., what is closed and what is open). This section should discuss the program at a high-level (i.e., without referencing specific classes, data structures, or code).

## Overview

PICTURE

We chose to divide our program into three portions:

1. **The UI.**
2. **The Simulation.** 

	We will have a *Simulation* class that will serve as a clock to our program. On each time step, the class will tell the *Grid* class to update its cells according to the rules assigned to their current states. Naturally, this class will have a *step()* method. Ideally, we'd like to keep this method minimal: we want to dissociate the actual game from our project's simulation capability. 
	
	The *Simulation* class, in other words, will simply be a chronometer for our project. The *Grid* class, because it controls a Collection of *Cell* objects, will serve as the interface between the UI and the *Game* itself.

3. **The Game**.

	To enhance flexibility and modularity, we have divided our *Game* section into a number of parts. 
	
	- *Cell*: midway between the UI and the Game, the *Cell* is the basic graphic unit for our project. Each cell will have a color setter and a *getAdjacentCells()* method. This method will be crucial, because many rules depend on a cell's adjacent cells. Finally, each *Cell* will have a State instance variable. This variable will dictate the cell's current state and appearance.  
	- *State*: a description of the state of a cell. Will have a color field, which will in turn act upon the cell's color field. Will also have a Rules instance variable, which will let the state compute its next state. Will have a *setNextState(State currentState, ArrayList<Cell> adjacentCells)* function, which will determine the state of cells for the following time step.
	- *Rules*: a superclass that will be subclassed for each game. Will have a number of methods (methods that depend on the game), which will be called with a State, an ArrayList of Cell's, and which will act upon the states of the cells it was passed as a parameter.
	- *Game*: a superclass that will be subclassed for each game. Each subclass will create the types of *State* it requires, and assign the proper colors and *Rules* to each of those *State* objects. In addition, the *Game* superclass will be responsible for parsing XML configuration files for each game.

We felt that these three distinct layers of abstraction gave us the best chance of retaining flexibility and modularity as the project's complexity increased. Specifically, we appreciated the *Simulation*'s role as an interface between the UI and the Game.

## User Interface

PICTURE

Our project's UI will use a menu similar to the one provided in the simulation attached to the Schelling’s Model of Segregation project. The user would be able to modify the grid dimensions and the delay (how quickly the simulation runs) before the game is actually launched.

When the user first opens the project, he or she would select the type of game from a scrolling menu. Before the game begins, the user will also have the ability to shuffle and randomize the cells on the board.

The descriptions of the game will be displayed under the game's name.

We will build popup screens into the interface so the user cannot begin the simulation until acceptable data has been entered. 

Pressing the “Start" button will begin the simulation. The simulation will continue until “Stop" is pressed. Pressing the “Step" button will go through one iteration of the simulation.

## Design Details 

This section describes each component introduced in the Overview in detail (as well as any other sub-components that may be needed but are not significant to include in a high-level description of the program). It should describe how each component handles specific features given in the assignment specification, what resources it might use, how it collaborates with other components, and how each could be extended to include additional requirements (from the assignment specification or discussed by your team). Include the steps needed to complete the Use Cases below to help make your descriptions more concrete. Finally, justify the decision to create each component with respect to the design's key goals, principles, and abstractions. This section should go into as much detail as necessary to cover all your team wants to say.

## Design Considerations 

While planning *CellSociety*, our group discussed three main design decisions extensively: the Actor model as compared to the State model; the decision to subclass States; and our way of applying the rules.

Here were our pros and cons for each point of discussion: 

1. **The Actor model as compared to the State model.**

	In the Actor model, Actors move across a grid of cells, whereas in the State model, the state of a cell is regularly set. We debated between these two approaches for quite a long time.
	
	The Actor model is preferable for these reasons: 
	
	- In many of these games, characters or actors are moving. Conceptually then, it might be reasonable to model these games as Actors moving over grids.
	- It seems normal to ask an Actor to move, to call a method on an actor rather to set it to a new location.
	
	The State model is preferable for these reasons:
	
	- In these games, there are often empty cells, cells that don't move. The Actor model does not take this type of cell into account, while the State model does.
	- The State abstraction is simpler, and allows us to do away with movement conceptually: we no longer need to worry about movement, and instead we just need to set the state of cells. 
	
	While both abstractions had their conceptual advantages, we eventually settled on the State model. We noticed that when we were discussing the matter, our language tended to favor the "set this cell to" construction. This in itself felt like a valid reason to choose the State model.

2. **The decision to create subclasses of State**.
	
	Originally, we determined that for each game, we should create subclasses of State and store them in a package related to the game. In a wildfire simulation, for instance, we would create a Fire subclass, an Empty subclass, and a Tree subclass. The alternative was to **not** manually create a number of subclasses, but instead create our States from a Game subclass (wildfire, in this situation).
	
	The manual subclass model is preferable for these reasons:
	
	- It is very clear what States belong to what Game. They are in the same package, as separate files.
	- The implementation, while perhaps not flexible, is the simplest and most intuitive.

	The alternative, to create our States from a Game subclass, is preferable for these reasons:
	
	- It is far more flexible. For each game, we need to create one subclass (a subclass of Game), instead of many subclasses of State.
	- Conceptually, our Game subclass controls and determines the behavior of the game – which is exactly what one would expect.

	In the end, we voted for flexibility and the principle of least astonishment, and chose to create our States from within a Game subclass instead of manually subclassing each State for each game.
	

3. **Our way of accessing the rules.**

	From the start, we felt we should have a Rules class. We debated between two different implementations, however: having every rule, for every game, in a static Rules class; or subclassing the Rules class for each Game, and storing all of one game's rules in one subclass.
	
	Having every rule for every game in one Rules class is advantageous because:
	
	- Similar rules can be reused between games (no code duplication in that regard).
	- Each State can conveniently be initialized with an ArrayList of Rules (these rules could be called using either Reflection or Lambda Expressions).

	Subclassing the Rules class for each game is better because:
	
	- It prevents the creation of a massive, confusing Rules class.
	- It circumvents the need for mistake-prone tools like Java's Reflection.
	-  It lets us clearly identify which rules belong to which game.

	In the end, this choice was easy: the latter option prevailed. Subclassing the Rules class feels more flexible and robust from a coding standpoint.
	
We designed our current plan under that assumption that, to create a new game, one needs to: create a subclass of Game, and create a subclass of Rules (beyond adding an XML file with game characteristics). At this point, we are aware that combining the Game and Rules class is possible (and would make for a simpler addition of games), but we feel that would reduce our program's flexibility. We'd rather preserve flexibility at this early stage in our project!

## Team Responsibilities

This section describes the program components each team member plans to take primary and secondary responsibility for and a high-level plan of how the team will complete the program.