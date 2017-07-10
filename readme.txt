/* WARNING: if folder "savedgames" is not in src/main/resources then create it by your own */

Hello, we are Alessandro, Alex and Santa and this is our Software Engineering project!
Here it's how we worked and how it works:

We implemented the MVC pattern through these packages:
MODEL: BOARD, GAMECOMPONENTS, EFFECT, PLAYER;
CONTROLLER: CONTROLLER, GAMEMANAGEMENT, ACTION;
VIEW: VIEW, CLIENT, fx;

TO RUN:
1. Launch "Main" from PACKAGE: VIEW, to run the server;
2. If you want to play:
	by CLI: run "MainClient" from PACKAGE: CLIENT;
	by GUI: run "MainGUI" from PACKAGE: fx;
3. Sign up with your credentials or use ours:
 	username: ale, password: prova;
	username: alex, password: prova;
	username: santa, password: prova;
4. Once you logged, choose your color and create the game.
5. Launch another Main(CLI or GUI), and sign in (or sign up). Now you can see all the on-going games, you can:
	- join one of them, by inserting the sequential number of the games that appear.
		(Ex. if it appears: game: 1 players: 2 host: ale
				game: 2 players: 2 host: ale
				game: 5 players: 2 host: ale
		Type 1 for game 1, 2 for game 2, 3 for game 5)
	- create another game;
6. When the second user joins the game, a timer of 90 seconds will start.
7. The game starts if:
	- the timer expires;
	- the host clicks READY (he can only do it if the minimum number of player is reached);
	- the maximum number of player is reached;

TO CONFIG:
CONNECTIONS:
	PORT & IP:
	Connection.json in main directory.
	CLIENT: 
	(CLI) "MainCLI" from PACKAGE: CLIENT --> methods: factorySocket(), factoryRMI();
	(GUI) "MetaController" from PACKAGE: GUI -->  methods: factorySocket(), factoryRMI();
	SERVER:
	"Server" from PACKAGE: GAMEMANAGEMENT --> methods: Server(), startServer();
CARD: 
	PACKAGE: UTILITIES --> DevCards.json, ExCards.json
PERSONAL BOARD:
	PACKAGE: PLAYER --> personalBoardBonus.json
BOARD:
	PACKAGE: BOARD --> spaceEffects.json
	PACKAGE: ACTION --> faithPointsTracking.json
TIMER:
	PACKAGE: CLIENT --> timeout.json
		

TO PLAY:
We implemented all the advanced rules (except for some cards with unique effect), so you can do everything said in the GameRules document.
If you choose to play by CLI:
Follow the instructions given by the server (better if it's your turn, or the server will read your input on the next turn).
If you choose to play by GUI (better choice ;) ):
	to make a simple placement (you can always click on RESET to refresh your action):
	- select your family member;
	- select the action space, by clicking on the button on the board;
	- choose how many servants you want to convert;
	- click on CONFIRM;
	- if your action is invalid or you get an extra action, you will be notificated;
Every turn you can choose to play a leader card (to turn one of them or to play one you already turned) or to discard one of them (if you didn't 
play it before), but only if your resources permits it.
Obviously, if you play one of your leader card, then you can play your turn.
You can pass your turn (by clicking on PASS), zoom on every card (by clicking on it), 
see the personal board of every player in game with the menu on top-right.
You have 60 seconds to play, then you will automatically pass.

FIFTH PLAYER:
We tried to introduce a fifth player, called The Conspirator, with 0 resources. That's how it works:
1. At the beginning of every round he can make 4 turns on a row.
2. His goal is to guess where the other players are going to place their family Member, so he just have to place his 4 black family members 
on the board. (THEY WILL NOT BE VISIBLE).
He doesn't have to satisfy any requirement, except for placing twice on the same action space, and won't get any card or bonus. 
Every action space has a boolean attribute called "black" that is true if the black player is placed there. 
3. After his turns, the other players starts playing following the original structure of the game.
4. Everytime a coloured player places a family member on a "black" action space, his blackPoint counter is incremented.
5. If the blackPoint counter of a player is 2, then the black player and the colored one switches their personal Board and color.
6. Now, the NEW black player has a new role in the game and the OLD one should continues his match like he is a normal player, using
the statistics of the player switched.
7. Everytime a switch happens, the "black" attribute of action spaces is setted to "false".

This implementation means that the final black player cannot win, since he cannot get any resource from his placements.

CONNECTIONS:
To build the communication between Client and Server, we used two main classes:
-MessageToClient;
-InputForm;
MessageToClient: these (and their subclasses) are sent from the server to the client.
After they are received, they are executed (executeCLI() or executeGUI()) and let the player do his action.
By CLI, in the execution of Messages, the player is expected to fill a InputForm.
By GUI, it calls some methods on the scene controller.
InputForm: these (and their subclasses) are filled in the execution of Messages or through the interaction with the GUI, and then the are sent 
to the server from the client, the server reads it and passes their content to the Controller (classes ControllerForm), who edits properly the model, 
following what is written in the InputForm.

Everytime a player joins or disconnects from the game, the other players are notificated.
If a player is disconnected, he will automatically pass its turn.

TO SAVE THE GAME:
In our GUI you can find a "SAVE" button on the top-right (by CLI you have to write the command "save").
Every player can save in any moment of the game, going on with the match.
That's how it works:
It saves a file .ser that contains the instance of the game in that moment, with all its stats.
If the server goes down or the players leaves, the file stays on the disk.
Once a player who is already in a saved game logs in again, he's asked if he wants to load the saved gamed, but
only if all the other players left that or it ended.
If he answer YES, then he has to wait for the other players of that game to log in.
(After the loading of the game, the file .ser is deleted, so you cannot reload it again unless you don't save it another time)
Once every players of that game is in, the game goes on.
If he answer NO, then he goes in the lobby.

