package SocialMedia;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Represents a friends list for a profile
 *
 * @author Cole Busa, Max Fuligni
 * @version 12/3/20
 */
public class FriendsList implements Serializable {
    public ArrayList<Profile> friends;
    public ArrayList<Profile> incomingFriendRequests;
    public ArrayList<Profile> outgoingFriendRequests;

    /**
     * An empty constructor for a friends list.
     */
    public FriendsList() {
        friends = new ArrayList<Profile>();
        incomingFriendRequests = new ArrayList<>();
        outgoingFriendRequests = new ArrayList<>();
    }

    /**
     * A constructor for a friends list with a given list of friends.
     * @param friends The desired friends to create the friend list with.
     */
    public FriendsList(ArrayList<Profile> friends) {
        this.friends = friends;
    }

    /**
     * Returns the friends list.
     * @return the friends list
     */
    public ArrayList<Profile> getFriends() {
        return friends;
    }


    /**
     *
     * @param profile to check if friends with
     * @return true if friends, false if not
     */

    public boolean isFriendsWith(Profile profile) {
        boolean friends = false;
        for (Profile p :  getFriends()) {
            if (p.getUsername().equalsIgnoreCase(profile.getUsername())) {
                friends = true;
            }
        }

        return friends;
    }

    /**
     *
     * @param profile to check if user has outgoing friend request to
     * @return true if yes, false if no
     */
    public boolean hasOutgoingFriendRequest(Profile profile) {
        boolean friends = false;
        for (Profile p :  getOutgoingFriendRequests()) {
            if (p.getUsername().equalsIgnoreCase(profile.getUsername())) {
                friends = true;
            }
        }

        return friends;
    }

    /**
     *
     * @param profile to check if user has incoming friend request to
     * @return true if yes, false if no
     */
    public boolean hasIncomingFriendRequest(Profile profile) {
        boolean friends = false;
        for (Profile p :  getIncomingFriendRequests()) {
            if (p.getUsername().equalsIgnoreCase(profile.getUsername())) {
                friends = true;
            }
        }

        return friends;
    }

    public ArrayList<Profile> getIncomingFriendRequests() { return incomingFriendRequests; }

    public ArrayList<Profile> getOutgoingFriendRequests() { return outgoingFriendRequests; }

    /**
     * Adds the specified friend to the user's friendsList.
     * M
     * @param friend The friend to add to the friendsList.
     */
    public void addFriend(Profile friend) {
        if (hasOutgoingFriendRequest(friend)) {
            outgoingFriendRequests.remove(friend);
        }
        if (hasIncomingFriendRequest(friend)) {
            incomingFriendRequests.remove(friend);
        }
        if (!friends.contains(friend)) {
            friends.add(friend);
        }
    }

    /**
     * Removes the specified friend from the user's friendsList.
     * @param friend The friend to remove from the friendsList.
     * @return 1 if the friend is successfully removed, 0 otherwise.
     */
    public int removeFriend(Profile friend) {
        for (int i = 0; i < friends.size(); i++) {
            if (friends.get(i).getUsername().equals(friend.getUsername())) {
                friends.remove(i);
                return 1;
            }
        }
        return 0;
    }

    /**
     * A method to get the mutual friends of two users.
     * @param secondUser The second user to check mutual friends with.
     * @return An arraylist of strings containing the mutual friends of the two users.
     */
    public ArrayList<Profile> mutualFriends(FriendsList secondUser) {
        ArrayList<Profile> mutualFriends = new ArrayList<Profile>();
        for (int i = 0; i < friends.size(); i++) {
            for (int j = 0; j < secondUser.getFriends().size(); j++) {
                if (friends.get(i).getUsername().equals(secondUser.getFriends().get(j).getUsername())) {
                    mutualFriends.add(secondUser.getFriends().get(j));
                    break;
                }
            }
        }
        return mutualFriends;
    }

    /**
     * Returns a string format of the friends list (only includes usernames).
     * @return A string format of the friends list.
     */
    public String toString() {
        String formattedList = friends.get(0).getUsername();
        for (int i = 1; i < friends.size(); i++) {
            formattedList = formattedList + ", " + friends.get(i).getUsername();
        }
        return formattedList;
    }
}
