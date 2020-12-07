# The Wall
A social media application for CS 18000 <br />
**Note for developers: SRC file is located inside the CS Project file**

# How to setup
If you are testing the server, first go to the SettingsAndConstants.java file and change LOCALHOST to true. This is because you are testing it on a probably not portforwarded IP so you won't be communicating with people outside the network. Probably doesn't affect anything, but it is a fail safe. After this, run the CentralServer.java to boot up the server. After that, just run UserInput to boot up the client.

# Class-by-Class documentation:

**EditProfileGUI:**

Description:

Testing:

We used JUnit testing to ensure this class, its fields, and its methods exist. We also verify the inheritance, the modifiers and type of each field, as well as the modifiers and return type of the methods. On top of this, every constructor is verified to work properly.

**FriendsGUI:**

Description:

Testing:

We used JUnit testing to ensure this class, its fields, and its methods exist. We also verify the inheritance, the modifiers and type of each field, as well as the modifiers and return type of the methods. On top of this, every constructor is verified to work properly.

**SocialProfileGUI:**

Description:

Testing:

We used JUnit testing to ensure this class, its fields, and its methods exist. We also verify the inheritance, the modifiers and type of each field, as well as the modifiers and return type of the methods. On top of this, every constructor is verified to work properly.

**UsersListGUI:**

Description:

Testing:

We used JUnit testing to ensure this class, its fields, and its methods exist. We also verify the inheritance, the modifiers and type of each field, as well as the modifiers and return type of the methods. On top of this, every constructor is verified to work properly.

**CentralServer:**

Description:

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

Description:

Testing:

We used JUnit testing to ensure this class, its fields, and its methods exist. We also verify the inheritance, the modifiers and type of each field, as well as the modifiers and return type of the methods. On top of this, every constructor is verified to work properly.

**FriendsList:**

Description: A class to handle the list of friends for a given user. This class also stores incoming and outgoing friend requests in their own ArrayLists.

Testing:

We used JUnit testing to ensure this class, its fields, and its methods exist. We also verify the inheritance, the modifiers and type of each field, as well as the modifiers and return type of the methods. On top of this, every constructor is verified to work properly.

We also used JUnit testing for each method in this class, assuming proper input, for correct output. We assume proper input because all formatting is verified in the GUI itself.

**Profile:**

Description:

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

Description:

Testing:

We used JUnit testing to ensure this class, its fields, and its methods exist. We also verify the inheritance, the modifiers and type of each field, as well as the modifiers and return type of the methods. On top of this, every constructor is verified to work properly.
