package SocialMedia.ServerAndClient;

import SocialMedia.Profile;

import java.io.Serializable;
import java.util.ArrayList;

public class ServerObjectStorage {
    public volatile ArrayList<Profile> users = new ArrayList<>();
}
