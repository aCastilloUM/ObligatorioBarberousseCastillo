import java.time.LocalDate;

public class  Song {
    String spotify_id;
    String name;
    String artists;
    int daily_rank;
    String country;
    String snapshot_date;
    double tempo;

    public Song(String spotify_id, String name, String artists, int daily_rank, String country, String snapshot_date, double tempo) {
        this.spotify_id = spotify_id;
        this.name = name;
        this.artists = artists;
        this.daily_rank = daily_rank;
        this.country = country;
        this.snapshot_date = snapshot_date;
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

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getSnapshot_date() {
        return snapshot_date;
    }

    public void setSnapshot_date(String snapshot_date) {
        this.snapshot_date = snapshot_date;
    }

    public double getTempo() {
        return tempo;
    }

    public void setTempo(double tempo) {
        this.tempo = tempo;
    }
}
