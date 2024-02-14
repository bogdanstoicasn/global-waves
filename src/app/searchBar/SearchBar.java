package app.searchBar;


import app.Admin;
import app.audio.LibraryEntry;

import java.util.ArrayList;
import java.util.List;

import static app.searchBar.FilterUtils.*;
import static app.searchBar.FilterUtils.filterByFollowers;

public class SearchBar {
    private List<LibraryEntry> results;
    private final String user;
    private static final Integer MAX_RESULTS = 5;
    private String lastSearchType;

    private LibraryEntry lastSelected;

    /**
     * Constructor
     * @param user the user that is using the search bar
     */
    public SearchBar(final String user) {
        this.results = new ArrayList<>();
        this.user = user;
    }

    /**
     * Clears the last selected item and the last search type
     */
    public void clearSelection() {
        lastSelected = null;
        lastSearchType = null;
    }

    /**
     * Searches for the given filters and type
     * @param filters the filters to be applied
     * @param type the type of the search
     * @return the list of results
     */
    public List<LibraryEntry> search(final Filters filters, final String type) {
        List<LibraryEntry> entries;

        switch (type) {
            case "song":
                entries = new ArrayList<>(Admin.getSongs());

                if (filters.getName() != null) {
                    entries = filterByName(entries, filters.getName());
                }

                if (filters.getAlbum() != null) {
                    entries = filterByAlbum(entries, filters.getAlbum());
                }

                if (filters.getTags() != null) {
                    entries = filterByTags(entries, filters.getTags());
                }

                if (filters.getLyrics() != null) {
                    entries = filterByLyrics(entries, filters.getLyrics());
                }

                if (filters.getGenre() != null) {
                    entries = filterByGenre(entries, filters.getGenre());
                }

                if (filters.getReleaseYear() != null) {
                    entries = filterByReleaseYear(entries, filters.getReleaseYear());
                }

                if (filters.getArtist() != null) {
                    entries = filterByArtist(entries, filters.getArtist());
                }

                break;
            case "playlist":
                entries = new ArrayList<>(Admin.getPlaylists());

                entries = filterByPlaylistVisibility(entries, user);

                if (filters.getName() != null) {
                    entries = filterByName(entries, filters.getName());
                }

                if (filters.getOwner() != null) {
                    entries = filterByOwner(entries, filters.getOwner());
                }

                if (filters.getFollowers() != null) {
                    entries = filterByFollowers(entries, filters.getFollowers());
                }

                break;
            case "podcast":
                entries = new ArrayList<>(Admin.getPodcasts());

                if (filters.getName() != null) {
                    entries = filterByName(entries, filters.getName());
                }

                if (filters.getOwner() != null) {
                    entries = filterByOwner(entries, filters.getOwner());
                }

                break;

            case "artist":
                entries = new ArrayList<>(Admin.getArtists());

                if (filters.getName() != null) {
                    entries = filterByName(entries, filters.getName());
                }

                break;

            case "album":
                entries = new ArrayList<>(Admin.getAlbums());

                if (filters.getOwner() != null) {
                    entries = filterByOwner(entries, filters.getOwner());
                }
                if (filters.getName() != null) {
                    entries = filterByName(entries, filters.getName());
                }
                if (filters.getDescription() != null) {
                    entries = filterByDescription(entries, filters.getDescription());
                }
                break;
            case "host":
                entries = new ArrayList<>(Admin.getHosts());

                if (filters.getName() != null) {
                    entries = filterByName(entries, filters.getName());
                }
                break;
            default:
                entries = new ArrayList<>();
        }

        while (entries.size() > MAX_RESULTS) {
            entries.remove(entries.size() - 1);
        }

        this.results = entries;
        this.lastSearchType = type;
        return this.results;
    }

    /**
     * Selects the item with the given number
     * @param itemNumber the number of the item to be selected
     * @return the selected item
     */
    public LibraryEntry select(final Integer itemNumber) {
        if (this.results.size() < itemNumber) {
            results.clear();

            return null;
        } else {
            lastSelected =  this.results.get(itemNumber - 1);
            results.clear();

            return lastSelected;
        }
    }

    /**
     * Gets the results
     * @return the results
     */
    public List<LibraryEntry> getResults() {
        return results;
    }

    /**
     * Sets the results
     * @param results the results to be set
     */
    public void setResults(final List<LibraryEntry> results) {
        this.results = results;
    }

    /**
     * Gets the user
     * @return the user
     */
    public String getUser() {
        return user;
    }

    /**
     * Gets the max number of results
     * @return the max number of results
     */
    public String getLastSearchType() {
        return lastSearchType;
    }

    /**
     * Sets the last search type
     * @param lastSearchType the last search type to be set
     */
    public void setLastSearchType(final String lastSearchType) {
        this.lastSearchType = lastSearchType;
    }

    /**
     * Gets the last selected item
     * @return the last selected item
     */
    public LibraryEntry getLastSelected() {
        return lastSelected;
    }

    /**
     * Sets the last selected item
     * @param lastSelected the last selected item to be set
     */
    public void setLastSelected(final LibraryEntry lastSelected) {
        this.lastSelected = lastSelected;
    }
}
