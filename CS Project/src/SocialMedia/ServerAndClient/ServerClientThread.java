package SocialMedia.ServerAndClient;

import SocialMedia.Profile;

import java.io.*;
import java.net.Socket;

/**
 * Represents a client connected to the server
 *
 * @author Jaden Baker
 * @version 12/7/20
 */

public class ServerClientThread extends Thread {
    public String line = null;
    public Socket socket;
    public ObjectOutputStream objectOutputStream = null;
    public ObjectInputStream objectInputStream = null;
    public ServerObjectStorage serverObjectStorage;
    public Profile profile;

    public ServerClientThread(Socket socket, ServerObjectStorage serverObjectStorage) {
        this.socket = socket;
        this.serverObjectStorage = serverObjectStorage;
    }

    private void stopThread() throws IOException {
        socket.close();
        System.out.println("Socket Closed");
        CentralServer.numberOfConnections--;
        serverObjectStorage.users.remove(profile);
        CentralServer.serverClientThreads.remove(this);
        System.out.println("----------------------------------------");
    }

    public void run() {
        try {
            InputStream inputStream = socket.getInputStream();
            OutputStream outputStream = socket.getOutputStream();

            // create a ObjectOutputStream so we can write data from it
            objectOutputStream = new ObjectOutputStream(outputStream);
            // create a ObjectInputStream so we can read data from it
            objectInputStream = new ObjectInputStream(inputStream);

        } catch (IOException e) {
            System.out.println("IO error in server thread");
        }

        try {
            System.out.println("Thread started");
            line = objectInputStream.readUTF();
            if (!line.equals("see users"))
                System.out.println(line);
            while (line.compareToIgnoreCase("quit") != 0) {
                switch (line) {
                    case "see users":
                        objectOutputStream.writeObject(serverObjectStorage.users);
                        objectOutputStream.flush();
                        objectOutputStream.reset();
                        break;
                    case "create profile":
                        Profile temp1 = (Profile) objectInputStream.readObject();
                        boolean foundDuplicate = false;
                        for (Profile p : serverObjectStorage.users) {
                            if (p.getUsername().equals(temp1.getUsername())) {
                                System.out.println("User has failed to add a user");
                                foundDuplicate = true;
                            }
                        }
                        if(!foundDuplicate) {
                            serverObjectStorage.users.add(temp1);
                            serverObjectStorage.saveUsersToDatabase();
                            System.out.println("User has tried to add a user");
                        }
                        break;
                    case "delete profile":
                        System.out.println("User has deleted their account");
                        try {
                            Profile temp2 = (Profile) objectInputStream.readObject();
                            System.out.println("remove " + temp2.getName());
                            for (Profile p : serverObjectStorage.users) {
                                if (p.getUsername().equals(temp2.getUsername())) {
                                    temp2 = p;
                                }
                            }
                            for (Profile i : temp2.getFriendsList().friends) {
                                i.getFriendsList().removeFriend(temp2);
                            }
                            serverObjectStorage.users.remove(temp2);
                            serverObjectStorage.saveUsersToDatabase();
                        } catch (ClassNotFoundException e) {
                            e.printStackTrace();
                        }
                        break;
                    case "update profile":
                        System.out.println("User has tried to update account");
                        try {
                            Profile temp3 = (Profile) objectInputStream.readObject();
                            Profile toCopyTo = null;
                            for (Profile p : serverObjectStorage.users) {
                                if (temp3.getUsername().equals(p.getUsername())) {
                                    toCopyTo = p;
                                }
                            }
                            System.out.println("Updated " + temp3.getName());
                            if (!(toCopyTo == null)) {
                                serverObjectStorage.users.remove(toCopyTo);
                                serverObjectStorage.users.add(temp3);
                                serverObjectStorage.saveUsersToDatabase();
                            } else {
                                System.out.println("Cant update user, name is null");
                            }
                        } catch (ClassNotFoundException e) {
                            e.printStackTrace();
                        }
                        break;
                    case "add friend":
                        System.out.println("User has tried to added a friend");
                        try {
                            Profile profile1 = (Profile) objectInputStream.readObject(); //Getting
                            Profile profile2 = (Profile) objectInputStream.readObject(); //Sending
                            for (Profile p : serverObjectStorage.users) {
                                if (profile1.getUsername().equals(p.getUsername())) {
                                    profile1 = p;
                                } else if (profile2.getUsername().equals(p.getUsername())) {
                                    profile2 = p;
                                }
                            }
                            if (profile1.friendsList.incomingFriendRequests.contains(profile2) && profile2.friendsList.outgoingFriendRequests.contains(profile1)
                                    || profile2.friendsList.incomingFriendRequests.contains(profile1) && profile1.friendsList.outgoingFriendRequests.contains(profile2)) {
                                profile1.friendsList.addFriend(profile2);
                                profile2.friendsList.addFriend(profile1);
                                profile1.friendsList.incomingFriendRequests.remove(profile2);
                                profile2.friendsList.outgoingFriendRequests.remove(profile1);
                                System.out.println(profile1.getUsername() + " has added " + profile2.getUsername() + " as a friend");
                            }
                            profile1.friendsList.incomingFriendRequests.add(profile2);
                            profile2.friendsList.outgoingFriendRequests.add(profile1);
                            System.out.println(profile1.getUsername() + " has received a friend request from " + profile2.getUsername());
                            serverObjectStorage.saveUsersToDatabase();
                        } catch (ClassNotFoundException e) {
                            e.printStackTrace();
                        }
                        break;
                    case "cancel friend":
                        System.out.println("User has tried to cancel a friend request");
                        try {
                            Profile profile1 = (Profile) objectInputStream.readObject(); //Getting
                            Profile profile2 = (Profile) objectInputStream.readObject(); //Sending
                            for (Profile p : serverObjectStorage.users) {
                                if (profile1.getUsername().equals(p.getUsername())) {
                                    profile1 = p;
                                } else if (profile2.getUsername().equals(p.getUsername())) {
                                    profile2 = p;
                                }
                            }
                            if (profile1.friendsList.incomingFriendRequests.contains(profile2) && profile2.friendsList.outgoingFriendRequests.contains(profile1)) {
                                profile1.friendsList.incomingFriendRequests.remove(profile2);
                                profile2.friendsList.outgoingFriendRequests.remove(profile1);
                            } else if (profile2.friendsList.incomingFriendRequests.contains(profile1) && profile1.friendsList.outgoingFriendRequests.contains(profile2)) {
                                profile2.friendsList.incomingFriendRequests.remove(profile1);
                                profile1.friendsList.outgoingFriendRequests.remove(profile2);
                            }
                            System.out.println(profile1.getUsername() + " has cancelled a friend request to " + profile2.getUsername());
                            serverObjectStorage.saveUsersToDatabase();
                        } catch (ClassNotFoundException e) {
                            e.printStackTrace();
                        }
                        break;
                    case "accept friend":
                        System.out.println("User has tried to accept a friend");
                        try {
                            Profile profile1 = (Profile) objectInputStream.readObject(); //Getting
                            Profile profile2 = (Profile) objectInputStream.readObject(); //Sending
                            for (Profile p : serverObjectStorage.users) {
                                if (profile1.getUsername().equals(p.getUsername())) {
                                    profile1 = p;
                                } else if (profile2.getUsername().equals(p.getUsername())) {
                                    profile2 = p;
                                }
                            }
                            System.out.println(profile1.friendsList.incomingFriendRequests.size() + " | " + profile2.friendsList.outgoingFriendRequests.size());
                            if (profile1.friendsList.incomingFriendRequests.contains(profile2) && profile2.friendsList.outgoingFriendRequests.contains(profile1)
                                    || profile2.friendsList.incomingFriendRequests.contains(profile1) && profile1.friendsList.outgoingFriendRequests.contains(profile2)) {
                                profile1.friendsList.addFriend(profile2);
                                profile2.friendsList.addFriend(profile1);
                                profile1.friendsList.incomingFriendRequests.remove(profile2);
                                profile2.friendsList.outgoingFriendRequests.remove(profile1);
                                System.out.println(profile1.getUsername() + " has added " + profile2.getUsername() + " as a friend");
                            }
                            serverObjectStorage.saveUsersToDatabase();
                        } catch (ClassNotFoundException e) {
                            e.printStackTrace();
                        }
                        break;
                }
                line = objectInputStream.readUTF();
            }
        } catch (IOException | ClassNotFoundException e) {
            line = this.getName();
            System.out.println("----------------------------------------");
            try {
                stopThread();
            } catch (IOException ioException) {
                System.out.println("Error with doing the stop thread stuff");
            }
            System.out.println("IO Error: " + line + " terminated abruptly");
        } catch (NullPointerException e) {
            line = this.getName();
            System.out.println("----------------------------------------");
            System.out.println("Client " + line + " Closed");
            try {
                stopThread();
            } catch (IOException ioException) {
                System.out.println("Error with doing the stop thread stuff");
            }
        } finally {
            try {
                System.out.println("Connection Closing...");
                if (objectOutputStream != null) {
                    objectOutputStream.close();
                    System.out.println("Socket Input Stream Closed");
                }

                if (objectInputStream != null) {
                    objectInputStream.close();
                    System.out.println("Socket Output Closed");
                }

                if (socket != null) {
                    stopThread();
                }
            } catch (IOException ie) {
                System.out.println("Socket Close Error");
            }
        }
    }
}