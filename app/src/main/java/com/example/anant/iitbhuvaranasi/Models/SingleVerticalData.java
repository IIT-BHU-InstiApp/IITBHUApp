package com.example.anant.iitbhuvaranasi.Models;

import java.util.List;

public class SingleVerticalData {

    private String club_name,club_image,council_name,council_image;
    private String title_event,map_location;
    private String description_event;
    private String image_event,date_event;
    private String location,viewcount;
    private int interestedcount,notifid;
    private Boolean interested;
    private List<String> interestdNames;


    public SingleVerticalData(){

    }


    public SingleVerticalData(String club_name, String club_image, String council_name, String council_image, String title_event, String description_event, String image_event, String date_event, String location, String viewcount, int interestedcount, Boolean interested, List<String> interestedNames, int notifid, String map_location) {
        this.club_name = club_name;
        this.club_image = club_image;
        this.council_name = council_name;
        this.council_image = council_image;
        this.title_event = title_event;
        this.description_event = description_event;
        this.image_event = image_event;
        this.date_event = date_event;
        this.location = location;
        this.viewcount = viewcount;
        this.interestedcount = interestedcount;
        this.interested = interested;
        this.notifid = notifid;
        this.map_location = map_location;
        this.interestdNames = interestedNames;
    }


/* public SingleVerticalData(String club, String title, String date, String image_club, String image, String viewcount, String interested, String notifid) {
        this.club = club;
        this.title = title;
        this.date = date;
        this.image_club = image_club;
        this.image = image;
        this.viewcount = viewcount;
        this.interested = interested;
        this.notifid=notifid;
    }*/

    public String getMap_location() {
        return map_location;
    }

    public void setMap_location(String map_location) {
        this.map_location = map_location;
    }

    public String getClub_name() {
        return club_name;
    }

    public void setClub_name(String club_name) {
        this.club_name = club_name;
    }

    public String getClub_image() {
        return club_image;
    }

    public void setClub_image(String club_image) {
        this.club_image = club_image;
    }

    public String getCouncil_name() {
        return council_name;
    }

    public void setCouncil_name(String council_name) {
        this.council_name = council_name;
    }

    public String getCouncil_image() {
        return council_image;
    }

    public void setCouncil_image(String council_image) {
        this.council_image = council_image;
    }

    public String getTitle_event() {
        return title_event;
    }

    public void setTitle_event(String title_event) {
        this.title_event = title_event;
    }

    public String getDescription_event() {
        return description_event;
    }

    public void setDescription_event(String description_event) {
        this.description_event = description_event;
    }

    public String getImage_event() {
        return image_event;
    }

    public void setImage_event(String image_event) {
        this.image_event = image_event;
    }

    public String getDate_event() {
        return date_event;
    }

    public void setDate_event(String date_event) {
        this.date_event = date_event;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getViewcount() {
        return viewcount;
    }

    public void setViewcount(String viewcount) {
        this.viewcount = viewcount;
    }

    public int getInterestedcount() {
        return interestedcount;
    }

    public void setInterestedcount(int interestedcount) {
        this.interestedcount = interestedcount;
    }

    public Boolean isInterested() {
        return interested;
    }

    public void setInterested(Boolean interested) {
        this.interested = interested;
    }

    public int getNotifid() {
        return notifid;
    }

    public void setNotifid(int notifid) {
        this.notifid = notifid;
    }

    public List<String> getInterestdNames(){
        return interestdNames;
    }
}
