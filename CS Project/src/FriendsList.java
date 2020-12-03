import java.util.ArrayList;

/**
 * Represents a friends list for a profile
 *
 * @author Cole Busa
 * @version 12/3/20
 */
public class FriendsList {
    public ArrayList<Profile> friends;

    /**
     * An empty constructor for a friends list.
     */
    public FriendsList() {
        friends = new ArrayList<Profile>();
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
     * Adds the specified friend to the user's friendsList.
     * @param friend The friend to add to the friendsList.
     */
    public void addFriend(Profile friend) {
        friends.add(friend);
    }

    /**
     * Removes the specified friend from the user's friendsList.
     * @param friend The friend to remove from the friendsList.
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
        String formattedList = friends.get(0).getName();
        for (int i = 1; i < friends.size(); i++) {
            formattedList = formattedList + ", " + friends.get(i).getUsername();
        }
        return formattedList;
    }
}
