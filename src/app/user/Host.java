package app.user;

import app.Admin;
import app.audio.Collections.Podcast;
import app.audio.Files.Episode;
import app.player.PlayerStats;
import fileio.input.CommandInput;

import java.util.ArrayList;
import java.util.Iterator;

public class Host extends User {

    private final ArrayList<Podcast> podcasts;
    private final ArrayList<Announcement> announcements;

    /**
     * @param username
     * @param age
     * @param city
     */
    public Host(final String username, final int age, final String city) {
        super(username, age, city);
        podcasts = new ArrayList<>();
        announcements = new ArrayList<>();
    }

    /**
     *
     * @return name
     */
    public String getName() {
        return super.getUsername();
    }

    /**
     *
     * @return podcasts
     */
    public ArrayList<Podcast> getPodcasts() {
        return podcasts;
    }

    /**
     *
     * @return announcements
     */
    public ArrayList<Announcement> getAnnouncements() {
        return announcements;
    }

    /**
     * adds a podcast to the list
     * @param podcast
     */
    public void addPodcast(final Podcast podcast) {
        podcasts.add(podcast);
    }

    /**
     * adds an announcement to the list
     * @param announcement
     */
    public void addAnnouncement(final Announcement announcement) {
        announcements.add(announcement);
    }

    /**
     * @param commandInput
     */
    public String addAnnouncement(final CommandInput commandInput) {
        for (Announcement announcement : announcements) {
            if (announcement.getName().equals(commandInput.getName())) {
                return getUsername() + " has already added an announcement with this name.";
            }
        }
        Announcement announcement = new Announcement(commandInput.getName(),
                commandInput.getDescription());
        announcements.add(announcement);
        return getUsername() + " has successfully added new announcement.";
    }

    /**
     * @param name, removes an announcement with the given name
     */
    public String removeAnnouncement(final String name) {
        Iterator<Announcement> iterator = announcements.iterator();

        while (iterator.hasNext()) {
            Announcement announcement = iterator.next();

            if (announcement.getName().equals(name)) {
                iterator.remove();
                return getUsername() + " has successfully deleted the announcement.";
            }
        }

        return getUsername() + " has no announcement with the given name.";
    }

    /**
     * @param podcast, check remove a podcast
     */
    private static boolean checkPodcastDelete(final Podcast podcast) {
        // we delete the podcast if it's not loaded
        for (User normalUser: Admin.getUsers()) {
            if (!(Host.class.isAssignableFrom(normalUser.getClass()))) {
                PlayerStats stats = normalUser.getPlayerStats();
                String type = normalUser.getPlayer().getType();
                if (type != null && (type.equals("podcast") || type.equals("episode"))) {
                    String podcastName = stats.getName();
                    for (Podcast podcast1 : Admin.getPodcasts()) {
                        if (podcast1.getName().equals(podcastName)) {
                            return false;
                        }
                        for (Episode episode : podcast1.getEpisodes()) {
                            if (episode.getName().equals(podcastName)) {
                                return false;
                            }
                        }
                    }

                }
            }
        }
        return true;
    }

    /**
     * @param toBeRemoved, removes a podcast
     */
    public String removePodcast(final Podcast toBeRemoved) {
        if (checkPodcastDelete(toBeRemoved)) {
            Admin.deletePodcast(toBeRemoved);
            podcasts.remove(toBeRemoved);
            return  getUsername() + " deleted the podcast successfully.";
        }
        return getUsername() + " can't delete this podcast.";
    }
}
