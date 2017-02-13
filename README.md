Cell Society Team 18 README
==========

# Names of all people who worked on the project.
Elliott Bolzan (eab91), Sam Schwaller (scs51), Nikita Zemlevskiy (naz7).
# Date you started, date you finished, and an estimate of the number of hours worked on the project.
Started 1/28, completed 2/12. Hours worked: Elliott and Nikita: 50 each.
# Each person's role in developing the project.
Elliott Bolzan: backend/frontend/testing.
Nikita Zemlevskiy: backend/frontend/testing.
Sam Schwaller: frontend/testing.
# Any books, papers, online, or human resources that you used in developing the project.
Stack Overflow for XML parsing and general questions. Code borrowed from Robert Duvall for pieces of XML parsing.
Code also borrowed from Robert Duvall's labs for the user interface elements.
# Files used to start the project (the class(es) containing main)
src/cellsociety_team18/Main.java.
# Files used to test the project.
XML files may be modified to change parameters of each simulation. Parameters of each simulation may also be set from the app while it is running. These files are located in the `data` subfolder. Make sure the `data` and `resources` subfolders are added to your Java project's build path.
# Any data or resource files required by the project (including format of non-standard files)
All files under `data`, which are XML files specifying parameters for each simulation.
All files under `resources`, which specify user facing strings.
# Any information about using the program (i.e., command-line/applet arguments, key inputs, interesting example data files, or easter eggs)
Once app is launched from Main, the UI is self explanatory. 

Click on the `Pick a Game` combo box to select a simulation, and the respective grid will pop up in place of the text.

Click on the `Pick a Configuration` combo box to select a configuration for a game. 

Under the Parameter/Value table you will be able to click on `Value` of different parameters pertinent to each simulation. Click on those to change them and press enter once changed. Entering a positive number into `Grid Width` changes the amount of cells in the grid. Entering a positive number into `Cell Size` changes the size of each cell. Selecting `Outline Grid` toggles borders between cells on the grid. On the `Cell Type` combo box you will be able to select between Square, Hexagonal, and Triangular cells. On the `Edge Type` combo box you will be able to select between Toroidal and Bounded edges. Under `Cell Distribution` you will be able to select distribution of cells according to given parameters or completely randomly. Under `Number of Neighbors` you will select how many max. neighbors a cell can see around it.

Clicking `Load` will load the simulation from the current configuration. To load multiple simulations at once simply specify the new simulations and load them. To quit out of a simulation just close the window. 

Click `Start` to begin the simulation. If you wish to speed up the simulation, enter a smaller delay time in ms. If you wish to stop the simulation, click `Stop`. 

Clicking `Start` will start the simulation back up again. If you wish to step through one iteration of the simulation, stop it, then click `Step`. 

If you wish to restart the simulation with a new random distribution of cells according to the parameters specified in the XML file, click `Reload`. 

# Any known bugs, crashes, or problems with the project's functionality
Project is fully functional as described above. 
# Any extra features included in the project.
Some extra features are described above under information. 
# Your impressions of the assignment to help improve it in the future.
This was a fun assignment and was a good team work experience. Note - the last commit on Monday morning was just adding .classpath and .project files for the convenience of the graders so they wouldn't have to set up the project source folders manually.
