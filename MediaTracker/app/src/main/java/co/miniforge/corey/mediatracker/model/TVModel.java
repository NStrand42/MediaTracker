package co.miniforge.corey.mediatracker.model;

import org.json.JSONObject;

/**
 * Created by corey on 10/20/17.
 */

public class TVModel extends MediaItem {
    //Fields
    public int currentEpisodesWatched;
    public int totalEpisodes;

    public TVModel(JSONObject jsonObject) {
        super(jsonObject);

    }
}
