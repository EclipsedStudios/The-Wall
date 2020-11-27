import java.util.ArrayList;

/**
 * Represents a friends list for a profile
 *
 * @author Cole Busa
 * @version 11/27/20
 */
public class FriendsList {
    public ArrayList<String> friends;

    /**
     * An empty constructor for a friends list.
     */
    public FriendsList() {
        friends = new ArrayList<String>();
    }

    /**
     * A constructor for a friends list with a given list of friends.
     * @param friends The desired friends to create the friend list with.
     *                (Each string in the arraylist should be in the format
     *                Name:Username)
     */
    public FriendsList(ArrayList<String> friends) {
        this.friends = friends;
    }

    /**
     * Returns the friends list.
     * @return the friends list
     */
    public ArrayList<String> getFriends() {
        return friends;
    }

    /**
     * Adds the specified friend to the user's friendsList.
     * @param friend Should be in format Name:Username
     */
    public void addFriend(String friend) {
        friends.add(friend);
    }

    /**
     * Removes the specified friend from the user's friendsList.
     * @param friend Should be in the format Name:Username
     * @return 1 if the friend is successfully removed, 0 otherwise.
     */
    public int removeFriend(String friend) {
        for (int i = 0; i < friends.size(); i++) {
            if (friends.get(i).equals(friend)) {
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
    public ArrayList<String> mutualFriends(FriendsList secondUser) {
        ArrayList<String> mutualFriends = new ArrayList<String>();
        for (int i = 0; i < friends.size(); i++) {
            for (int j = 0; j < secondUser.getFriends().size(); j++) {
                if (friends.get(i).equals(secondUser.getFriends().get(j))) {
                    mutualFriends.add(secondUser.getFriends().get(j));
                    break;
                }
            }
        }
        return mutualFriends;
    }

    /**
     * Returns a string format of the friends list.
     * @return A string format of the friends list.
     */
    public String toString() {
        String formattedList = friends.get(0);
        for (int i = 1; i < friends.size(); i++) {
            formattedList = formattedList + ", " + friends.get(i);
        }
        return formattedList;
    }
}
