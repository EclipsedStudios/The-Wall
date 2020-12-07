# The Wall
A social media application for CS 18000 <br />
**Note for developers: SRC file is located inside the CS Project file**

# How to setup
If you are testing the server, first go to the SettingsAndConstants.java file and change LOCALHOST to true. This is because you are testing it on a probably not portforwarded IP so you won't be communicating with people outside the network. Probably doesn't affect anything, but it is a fail safe. After this, run the CentralServer.java to boot up the server. After that, just run UserInput to boot up the client.

# Class-by-Class documentation:

**EditProfileGUI:**

Description: Displays the Edit GUI menu that allows users to change any field about their profile they want besides their username.

Testing:

We used JUnit testing to ensure this class, its fields, and its methods exist. We also verify the inheritance, the modifiers and type of each field, as well as the modifiers and return type of the methods. On top of this, every constructor is verified to work properly.

**FriendsGUI:**

Description: FriendsGUI contains the method for displaying the friends menu for a certain user, displaying 3 lists in tandem. The first, showing the user’s current friends, the second showing the user’s incoming friend requests and the last on the far right showing the user’s outgoing friend requests. At the bottom of the menu are 4 buttons, two to accept and deny incoming friend requests and one to cancel an outgoing friend request. Along with a back button for the main page.

Testing:

We used JUnit testing to ensure this class, its fields, and its methods exist. We also verify the inheritance, the modifiers and type of each field, as well as the modifiers and return type of the methods. On top of this, every constructor is verified to work properly.

**SocialProfileGUI:**

Description: Contains the methods for displaying a users main profile window to themselves. It can also display a user’s profile to another different user which is used in conjunction with the UsersListGUI for displaying other users. 

Testing:

We used JUnit testing to ensure this class, its fields, and its methods exist. We also verify the inheritance, the modifiers and type of each field, as well as the modifiers and return type of the methods. On top of this, every constructor is verified to work properly.

**UsersListGUI:**

Description: UsersListGUI is a container for a static method that will display the latest version of the Users List GUI, which is a GUI that contains a list of every user on the right and allows for selection and a button to view the currently selected user, along with a back button.

Testing:

We used JUnit testing to ensure this class, its fields, and its methods exist. We also verify the inheritance, the modifiers and type of each field, as well as the modifiers and return type of the methods. On top of this, every constructor is verified to work properly.

**CentralServer:**

Description: Represents the main server used for all processes and communicates with the ServerClientThread for retrieving data.

Testing:

We used JUnit testing to ensure this class, its fields, and its methods exist. We also verify the inheritance, the modifiers and type of each field, as well as the modifiers and return type of the methods. On top of this, every constructor is verified to work properly.

**ServerClientThread:**

Description: ServerClientThread handles the interactions from the client and server, acting as a middle man to allow many clients to connect to one server.

Testing:

We used JUnit testing to ensure this class, its fields, and its methods exist. We also verify the inheritance, the modifiers and type of each field, as well as the modifiers and return type of the methods. On top of this, every constructor is verified to work properly.

**ServerObjectStorage:**

Description: ServerObjectStorage stores the users on the server and saves the users to file when requested by user or called by server. Also saves on server shutdown

Testing:

We used JUnit testing to ensure this class, its fields, and its methods exist. We also verify the inheritance, the modifiers and type of each field, as well as the modifiers and return type of the methods. On top of this, every constructor is verified to work properly.

**SettingsAndConstants:**

Description: Stores IP, Port, and a welcome message that the CentralServer will use.

Testing:

We used JUnit testing to ensure this class, its fields, and its methods exist. We also verify the inheritance, the modifiers and type of each field, as well as the modifiers and return type of the methods. On top of this, every constructor is verified to work properly.

**UserClient:**

Description: Represents the User connected through the GUIs that communicates with the ServerClientThread and in turn talks to the CentralServer.

Testing:

We used JUnit testing to ensure this class, its fields, and its methods exist. We also verify the inheritance, the modifiers and type of each field, as well as the modifiers and return type of the methods. On top of this, every constructor is verified to work properly.

**FriendsList:**

Description: A class to handle the list of friends for a given user. This class also stores incoming and outgoing friend requests in their own ArrayLists.

Testing:

We used JUnit testing to ensure this class, its fields, and its methods exist. We also verify the inheritance, the modifiers and type of each field, as well as the modifiers and return type of the methods. On top of this, every constructor is verified to work properly.

We also used JUnit testing for each method in this class, assuming proper input, for correct output. We assume proper input because all formatting is verified in the GUI itself.

**Profile:**

Description: Represents a Profile in memory, consisting of a username, password, age, website, likes/interests, email, friends list and an about me.

Testing:

We used JUnit testing to ensure this class, its fields, and its methods exist. We also verify the inheritance, the modifiers and type of each field, as well as the modifiers and return type of the methods. On top of this, every constructor is verified to work properly.

We also used JUnit testing for each method in this class, assuming proper input, for correct output. We assume proper input because all formatting is verified in the GUI itself.

**Tester:**

Description: This class contains all the JUnit tests for the project. Every test is split up by method into categories. For every class, there are tester methods for:

1. Its existence

2. Correct Inheritance

3. Correctly formatted fields 

4. Correctly formatted methods

**TestRunner:**

Description: This class is used to run the Tester class. If every test passes, this class prints out “Working Correctly!”

**UserInput:**

Description: Main starting point of the application and contains the main method to run the program.. Creates the first welcome screen and logins users or collects user input to create a new account for them.

Testing:

We used JUnit testing to ensure this class, its fields, and its methods exist. We also verify the inheritance, the modifiers and type of each field, as well as the modifiers and return type of the methods. On top of this, every constructor is verified to work properly.
