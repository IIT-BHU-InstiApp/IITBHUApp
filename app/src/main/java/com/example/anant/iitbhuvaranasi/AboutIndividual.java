package com.example.anant.iitbhuvaranasi;

public class AboutIndividual {
    private String id;
    private String name;
    private String imageName;
    private String branch;

    public static final int TYPE_HUMAN = 0;
    public static final int TYPE_LINK = 1;

    public AboutIndividual(String id, String name, String imageName,String branch) {
        this.id = id;
        this.name = name;
        this.imageName = imageName;
        this.branch = branch;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImageName() {
        return imageName;
    }

//    public void setImageName(String imageName) {
//        this.imageName = imageName;
//    }

    public String getBranch() {
        return branch;
    }

//    public void setBranch(String branch) {
//        this.branch = branch;
//    }
}
