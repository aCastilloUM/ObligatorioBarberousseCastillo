import java.time.LocalDate;

public class  Song {
    String hashKeySong;
    String spotify_id;
    String name;
    String artists;
    int daily_rank;
    int daily_movement;
    int weekly_movement;
    String country;
    LocalDate snapshot_date;
    int duration_ms;
    String album_name;
    LocalDate album_release;
    double tempo;

    public Song(String hashKeySong, String spotify_id, String name, String artists, int daily_rank, int daily_movement, int weekly_movement, String country, LocalDate snapshot_date, int duration_ms, String album_name, LocalDate album_release, double tempo) {
        this.hashKeySong = hashKeySong;
        this.spotify_id = spotify_id;
        this.name = name;
        this.artists = artists;
        this.daily_rank = daily_rank;
        this.daily_movement = daily_movement;
        this.weekly_movement = weekly_movement;
        this.country = country;
        this.snapshot_date = snapshot_date;
        this.duration_ms = duration_ms;
        this.album_name = album_name;
        this.album_release = album_release;
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

    public int getDaily_movement() {
        return daily_movement;
    }

    public void setDaily_movement(int daily_movement) {
        this.daily_movement = daily_movement;
    }

    public int getWeekly_movement() {
        return weekly_movement;
    }

    public void setWeekly_movement(int weekly_movement) {
        this.weekly_movement = weekly_movement;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public LocalDate getSnapshot_date() {
        return snapshot_date;
    }

    public void setSnapshot_date(LocalDate snapshot_date) {
        this.snapshot_date = snapshot_date;
    }

    public int getDuration_ms() {
        return duration_ms;
    }

    public void setDuration_ms(int duration_ms) {
        this.duration_ms = duration_ms;
    }

    public String getAlbum_name() {
        return album_name;
    }

    public void setAlbum_name(String album_name) {
        this.album_name = album_name;
    }

    public LocalDate getAlbum_release() {
        return album_release;
    }

    public void setAlbum_release(LocalDate album_release) {
        this.album_release = album_release;
    }

    public double getTempo() {
        return tempo;
    }

    public void setTempo(double tempo) {
        this.tempo = tempo;
    }
}
