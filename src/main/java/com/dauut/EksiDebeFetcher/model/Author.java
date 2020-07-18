package com.dauut.EksiDebeFetcher.model;

import java.util.List;
import java.util.Objects;

public class Author {
    private String nick;
    private String URL;
    private List<String> badges;
    private ActivityDetails activityDetails;

    public Author(String nick, String URL, List<String> badges, ActivityDetails activityDetails) {
        this.nick = nick;
        this.URL = URL;
        this.badges = badges;
        this.activityDetails = activityDetails;
    }

    public Author(String nick, String URL, ActivityDetails activityDetails) {
        this.nick = nick;
        this.URL = URL;
        this.activityDetails = activityDetails;
    }

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    public String getURL() {
        return URL;
    }

    public void setURL(String URL) {
        this.URL = URL;
    }

    public List<String> getBadges() {
        return badges;
    }

    public void setBadges(List<String> badges) {
        this.badges = badges;
    }

    public ActivityDetails getActivityDetails() {
        return activityDetails;
    }

    public void setActivityDetails(ActivityDetails activityDetails) {
        this.activityDetails = activityDetails;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Author author = (Author) o;
        return Objects.equals(nick, author.nick) &&
                Objects.equals(URL, author.URL) &&
                Objects.equals(badges, author.badges) &&
                Objects.equals(activityDetails, author.activityDetails);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nick, URL, badges, activityDetails);
    }
}
