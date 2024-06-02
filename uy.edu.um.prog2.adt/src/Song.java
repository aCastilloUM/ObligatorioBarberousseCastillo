import java.time.LocalDate;

public class  Song {
    String spotify_id;
    String name;
    String artists;
    int daily_rank;
    double tempo;

    public Song(String spotify_id, String name, String artists, int daily_rank, double tempo) {
        this.spotify_id = spotify_id;
        this.name = name;
        this.artists = artists;
        this.daily_rank = daily_rank;
        this.tempo = tempo;
    }

    public String getSpotify_id() {
        return spotify_id;
    }

    public void setSpotify_id(String spotify_id) {
        this.spotify_id = spotify_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getArtists() {
        return artists;
    }

    public void setArtists(String artists) {
        this.artists = artists;
    }

    public int getDaily_rank() {
        return daily_rank;
    }

    public void setDaily_rank(int daily_rank) {
        this.daily_rank = daily_rank;
    }

    public double getTempo() {
        return tempo;
    }

    public void setTempo(double tempo) {
        this.tempo = tempo;
    }
}
