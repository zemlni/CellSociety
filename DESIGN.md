Cell Society Design
==================

## Introduction

The main difficulty that we have identified in the Cell Society project is designing a program that will be flexible enough to support many different Cell Automata.

We want to be able to add new types of simulations in the future, while having to change as little of our existing code as possible. We came to the conclusion that a game is primarily defined by its rules; as a consequence, we chose to encapsulate a game's rules in one coherent structure. 

Each generic cell will have a specific state, which will update on each time tick. The cell's state will have different information, depending on which simulation is being run: different colors and different rules, for instance.

The User Interface will consist of a screen on which the user sees the current state of a grid. This grid will change on each time tick. 

Through the interface, the user will be able to adjust important values for different simulations. The user will also be able to pause and play the simulation, or pick a new simulation. 

In planning CellSociety, we sought to design an organized project that promotes pain-free testing, debugging and refactoring. 

## Overview

We chose to divide our program into three layers of abstraction:

![](overview.jpg)

1. **The UI.**

	The User Interface will have an *IO* class that handles the user's input. This class will communicate the modification of important simulation parameters by the user to the *Simulation* layer of abstraction. 
	
	The *Grid* class, described in the next section, will also play a part in the UI. It will let the user visualize the current state of the simulation.

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

![](ui.jpg)

Our project's UI will use a menu similar to the one provided in the simulation attached to the Schelling’s Model of Segregation project. The user would be able to modify the grid dimensions and the delay (how quickly the simulation runs) before the game is actually launched.

When the user first opens the project, he or she would select the type of game from a scrolling menu. Before the game begins, the user will also have the ability to shuffle and randomize the cells on the board.

The descriptions of the game will be displayed under the game's name.

We will build popup screens into the interface so the user cannot begin the simulation until acceptable data has been entered. 

Pressing the “Start" button will begin the simulation. The simulation will continue until “Stop" is pressed. Pressing the “Step" button will go through one iteration of the simulation.


## Design Details 


### Details
- The *Game* superclass will handle the reading in of the XML file with the initial configurations and parameter values. These will be different for each game, so we will make an inheritance hierarchy and subclass for each new type of simulation that we are required to implement. The *Game* subclasses will also dictate which type of *State* gets which rules. This is important because it ensures that our code is flexible and requires small amounts of modifications when being extended.

- The *Simulation* class will own the game loop. The class will set the simulation's clock and time step mechanism.

- The *Grid* class will have a grid object that contains a Collection of *Cells*. The *Grid* class will be part of the User Interface as well. Since each *Cell* has a *State* instance variable, it also tells the UI what color to display the *Cell* in. Thus, *Grid* is a bridge between the backend and the frontend. 

- The *Cell* class will represent one graphical unit and will  own a *State* object. The *adjacentCells()* method will return the cells adjacent to the current Cell. The Cell will also have an *update()* method, which will be invoked on each time tick. This method will take in the adjacent cells' states and the use the cell's own state and act on this information according to the cell's state's rules. 

- The *Rules* superclass will have subclasses for each of the games we need to implement as well. In order to avoid subclassing each game's numerous *States*, we subclass *Rules* and assign an instance of these *Rules* to a *State*. This is done, once again, for extensibility purposes. The rules are determined by each type of *Game*, and consist in statements that conditionally operate on the *State* of adjacent cells.

- On the UI side, the *Grid* will handle the updating of the simulation's colors as the simulation runs. We will also have a class to handle the inputs to the User Interface and update the interface's indicators based on the state of the simulation. Input is collected through the menus and fields. We consider it important to separate the User Interface from the backend. This is done to make code more readable, organized, and easier to debug and test. 
- There will be a *Main* class in the root of the project. It will be responsible for launching the program.

### Use Cases
#### Apply the rules to a middle cell: set the next state of a cell to dead by counting its number of neighbors using the Game of Life rules for a cell in the middle (i.e., with all its neighbors)
1. The cell will retrieve a list of its neighbors through its *getAdjacentCells()* method. This method uses simple math to access the *Grid* and get the cells adjacent to the cell in question. 
2. Then, taking into account the *States* of its neighbors, the *Cell* will change (or not) according to the rules defined in its *State* instance variable. In this case, the *Rules* class will have a method that counts the Ccell's number of neighbors.
3. If the *Cell* changes *State*, this will be reflected in the visualization. If not, then the *Cell* will remain the same as it was on the previous time tick.

#### Apply the rules to an edge cell: set the next state of a cell to live by counting its number of neighbors using the Game of Life rules for a cell on the edge (i.e., with some of its neighbors missing)
1. The cell will retrieve a list of its neighbors through its *getAdjacentCells()* method. This method uses simple math to access the *Grid* and get the cells adjacent to the cell in question. 
2. Then, taking into account the *States* of its neighbors, the *Cell* will change (or not) according to the rules defined in its *State* instance variable. In this case, the *Rules* class will have a method that counts the Ccell's number of neighbors.
3. If the *Cell* changes *State*, this will be reflected in the visualization. If not, then the *Cell* will remain the same as it was on the previous time tick.

#### Move to the next generation: update all cells in a simulation from their current state to their next state and display the result graphically

1. The *Simulation* class will maintain the game loop. On each time tick of the simulation, all Cells in the grid will be told to update. 
2. Each *Cell* will analyze the states of its surrounding cells and chose to take an action (different actions depending on which simulation is being run). 
3. Once every cell has chosen which action to take, the program will iterate through the planned changes and resolve all conflicts (e.g. two fish trying to move to the same *Cell*).
4. The states will be updated and as a result the visualization will be updated as well.

#### Set a simulation parameter: set the value of a parameter, probCatch, for a simulation, Fire, based on the value given in an XML file
1. The *Game* class will be responsible for reading in initial parameters and configurations of the *Grid*. This will happen each time the simulation is spawned, or each time a new simulation is chosen. 
2. Next, a *Rules* subclass will be created with this parameter during the initialization, and the appropriate *State* objects will be assigned this *Rules* subclass.
3. The simulation will begin when the user presses the start button.

#### Switch simulations: use the GUI to change the current simulation from Game of Life to Wator

1. The user can pause or play the simulation.
2. The *Simulation* class will pause the simulation if and when the *IO* class tells it to. 
3. Then, the user must click on the drop-down menu on the side of the current simulation to select Wator.
4. The *IO* class will tell the *Game* class which new game to launch.
5. The *Game* class will load the new game and initialize the simulation from Wator's XML file. 
6. The *Simulation* class will start the simulation as soon as the user clicks start on the UI. 
7. The *IO* class will tell the *Simulation* class to begin.

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
- It lets us clearly identify which rules belong to which game.
	
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
