Cell Society Design
==================

## Introduction

One of the problems that have been identified in the Cell Society project is how to organize a program that will be flexible enough to support many different Cell Automata. We want to be able to add a new type of simulation in the future and have to change as little of our existing code as possible. Thus, as the factors that determine the behavior of a simulations are the rules by which it lives. Thus, our team decided to encapsulate all of the rules per game, so that the program is extensible. Each cell will have a state, which will update on the coming of the next time tick. The cells are generic and and contain a state. This state will have different information, depending on which simulation is being run. This state object will have rules that are pertinent to it. The UI will consist of a screen in which the user sees the current state of the grid. This will change on each time tick. The user will be able to adjust key values for different simulations. The user will also be able to pause and unpause the simulation, and pick a new simulation. We look to have an organized design that facilitates easy testing, debugging and refactoring. 

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


### Details
The Game class will handle reading in of the xml file with the initial configurations and parameter values. These will be different for each game, so we will make an inheritance hierarchy and subclass for each new type of simulation that we are required to implement. The Game subclasses will also dictate which type of state gets which rules. This is important as an extensibility feature so that we have to change existing code as little as possible. The simulation class will be holding the game loop. This is what will have the time ticks of the simulation. The Grid class will have a grid object that contains a cells in a structure. The grid class will be part of UI as well. Since each cell has a state instance variable, this also tells the UI what color to display the cell in. Thus, Grid is a bridge between the backend and the frontend. The cell class will handle the operations of the game related to its neighboring cells. The cell will have a set of rules. There will be a method to get the adjacent cells of the cell. The cell will also have a update() method, which will be invoked on each time tick. This method will take in the adjacent cells' states and the use the cell's own state and act on this information according to the cell's rules. The rules class will have subclasses for each of the games we need to implement as well. Since a cell's state can defines rules within a game, we don't need to subclass for all the possible states. Instead, we subclass for the separate simulations and the state will determine the rules that are applied to that cell on that time tick. The cell will have an instance of a rules object and will use that in its update method. This setup is again done for extensibility purposes. The rules are determined by each type of simulation. These are if (condition) then (outcome) statements that operate on states of adjacent cells. On the UI side, the grid will handle the status of the grid and updating the colors as the simulation runs. We will also have a class to handle the inputs and outputs to the UI. These include the sliders, the menus and updating the indicators based on what simulation is running. It is important to separate UI from backend. This is done to make code more readable, organized, and easier to debug and test. There will be a Main class in the root of the project which will just be responsible for loading up the window and launching the program from the beginning. 
### Use Cases
#### Apply the rules to a middle cell: set the next state of a cell to dead by counting its number of neighbors using the Game of Life rules for a cell in the middle (i.e., with all its neighbors)
Similarly, the cell will retrieve a list of its neighbors through its getNeighbors() method. This method uses simple math and accesses the grid to get the cells adjacent to the cell in question. Then, taking into account its state and the states of all of its neighbors, the cell will change (or not) according to the rule that it is acting by. If the cell changes state, this will be reflected in the visualization. If not, then the cell will remain the same as it was on the previous time tick. On a change of the state, the rules of the cell also changes.
#### Apply the rules to an edge cell: set the next state of a cell to live by counting its number of neighbors using the Game of Life rules for a cell on the edge (i.e., with some of its neighbors missing)
Similarly, the cell will retrieve a list of its neighbors through its getNeighbors() method. This method uses simple math and accesses the grid to get the cells adjacent to the cell in question. Next, using its state and the state of all of its neighbors, the cell will act according to the rules that has been assigned to it. If the state of the cell changes, the rules assigned to it will change accordingly. 
#### Move to the next generation: update all cells in a simulation from their current state to their next state and display the result graphically
The Simulation class will maintain the game loop. On each time tick of the simulation, all cells in the grid will be told to update. This means to analyze the states of all the surrounding cells and chose to take an action (different actions depending on which simulation is being run). Once every cell has chosen which action to take, the program will run through and resolve all conflicts (eg two fish try to move to the same cell). The states will be updated and as a result the visualization will be updated as well.
#### Set a simulation parameter: set the value of a parameter, probCatch, for a simulation, Fire, based on the value given in an XML fire
The Game class will be responsible to read in initial parameters and configurations of the grid, if given. This will happen each time the simulation is spawned, or each time a new simulation is chosen. Next, this parameter will be applied to the corresponding rule in the rules class as part of the initialization, and the simulation will begin when the user presses the start button.
#### Switch simulations: use the GUI to change the current simulation from Game of Life to Wator
The user can pause the simulation (or not pause). The Simulation class will pause the simulation when (if) the UI io class tells it to. Then the user must click on the drop down menu on the side of what simulation to run and click on Wator. The io class will tell the game class which new game to load up. The Game class will load the new game and initialize simulation from the xml file over again with Wator. The Simulation class will start the simulation as soon as the user clicks start on the UI. The UI io class will tell the Simulation class to begin.

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
The code will be split up as follows:

- Sam Schwaller will handle the User Interface code. 
- Nikita Zemlevskiy and Elliott Bolzan will split up the backend as follows:
	- Nikita will handle creating the Grid, Game and Simulation classes (and their subclasses where appropriate).
	- Elliott will handle the State, Rules, and Cell classes (and their subclasses where appropriate). 

Each of is responsible for debugging and testing our own code. These are our **primary responsibilities**. 

In addition, we will all be involved in refactoring our work together. Nikita will be responsible for merging the code when it is ready. Those are our **secondary responsibilities**.
