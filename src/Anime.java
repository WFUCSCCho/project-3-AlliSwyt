/*∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗*
@file: Anime.java
@description: This class is for Anime objects. It is made for the anime inputs to either be hard coded or read from a string
corresponding to one line in the file MAL-anime.csv (downloaded from Kaggle). It has information of the title, rank, type,
number of episodes, time aired, members, pageUrl, image Url, and score of the anime. It has toString and equals methods and
also implements comparable and overrides a compareTo method.
@author: Alli Swyt
@date: September 25, 2025
∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗*/

public class Anime implements Comparable<Anime> {
    private String title;
    private Integer rank;
    private String type;
    private int episodes;
    private String aired;
    private int members;
    private String pageUrl;
    private String imageUrl;
    private double score;
    private String unknownEpisodes;

    //Parameterized constructor for Anime object
    public Anime(String title, int rank, String type, int episodes, String aired, int members, String pageUrl, String imageUrl, double score) {
        this.title = title;
        this.rank = rank;
        this.type = type;
        this.episodes = episodes;
        this.unknownEpisodes = "";
        this.aired = aired;
        this.members = members;
        this.pageUrl = pageUrl;
        this.imageUrl = imageUrl;
        this.score = score;
    }

    //constructor for when there is an unknown number of episodes - so the episodes are a string rather than an int
    public Anime(String title, int rank, String type, String episodes, String aired, int members, String pageUrl, String imageUrl, double score) {
        this.title = title;
        this.rank = rank;
        this.type = type;
        this.episodes = 0;
        unknownEpisodes = episodes;
        this.aired = aired;
        this.members = members;
        this.pageUrl = pageUrl;
        this.imageUrl = imageUrl;
        this.score = score;
    }

    //constructor taking only one input string with commas and everything --must be DIRECTLY copied from MAL-anime.csv
    public Anime(String input) {
        String[] data = new String[10];
        //takes the title of the anime but if there are commas or double quotes in the title it deals with that with if statements
        if (input.indexOf('\"') != -1) {
            int j;
            int i;
            for (i = 0; i < input.length(); i++) {
                if (input.charAt(i) == ',') {
                    break;
                } else
                    data[0] += input.charAt(i);
            }
            data[1] = String.valueOf(input.charAt(i + 2));
            for (j = i + 3; j < input.length(); j++) {
                if (input.charAt(j) == '\"' && (input.charAt(j + 1) != '\"' && input.charAt(j - 1) != '\"' || (input.substring(j, j + 3).equals("\"\"\"")))) { //should leave the for loop if it reads a " that doesn't have another " after it, or if it does, then it will break if it has "" after it, so """ all together.
                    if (input.substring(j, j + 3).equals("\"\"\"")) { // if there are three quotes together like """, then it will update the j variable;
                        j += 2;
                        data[1] += "\"\"";
                    }
                    break;
                } else
                    data[1] += input.charAt(j); //adds characters to the movie title until it is completed
            }
            String[] splitData = input.substring(j + 2).split(","); //splits the rest of the string based on commas and inputs these into the array of strings
            for (int k = 2; k < 10; k++) {
                data[k] = splitData[k - 2]; //transfers the data from the split substring into the data array of strings
            }
        } else
            data = input.split(",");

        this.title = data[1];
        this.rank = Integer.parseInt(data[2]);
        this.type = data[3];

        try {
            this.episodes = Integer.parseInt(data[4]);
            this.unknownEpisodes = "";
        }
        catch (NumberFormatException e) {
            this.episodes = 0;
            this.unknownEpisodes = data[4];
        }

        this.aired = data[5];
        this.members = Integer.parseInt(data[6]);
        this.pageUrl = data[7];
        this.imageUrl = data[8];
        this.score = Double.parseDouble(data[9]);
    }

    //default constructor
    public Anime() {
        title = "?";
        rank = 0;
        type = "?";
        episodes = 0;
        unknownEpisodes = "?";
        aired = "?";
        members = 0;
        pageUrl = "?";
        imageUrl = "?";
        score = 0;
    }

    //Copy Constructor
    public Anime(Anime anime) {
        title = anime.getTitle();
        rank = anime.getRank();
        type = anime.getType();
        episodes = anime.getEpisodes();
        unknownEpisodes = anime.getUnknownEpisodes();
        aired = anime.getAired();
        members = anime.getMembers();
        pageUrl = anime.getPageUrl();
        imageUrl = anime.getImageUrl();
        score = anime.getScore();
    }

    //getter methods
    public String getTitle() { return title; }
    public Integer getRank() { return rank; }
    public String getType() { return type; }
    public int getEpisodes() { return episodes; }
    public String getAired() { return aired; }
    public int getMembers() { return members; }
    public String getPageUrl() { return pageUrl; }
    public String getImageUrl() { return imageUrl; }
    public double getScore() { return score; }
    public String getUnknownEpisodes() { return unknownEpisodes; }

    @Override
    public String toString() {
        return "rank: " + rank + ", Title: " + title + ", type: " + type + ", Number of episodes: " + episodes + ", Date Aired: " + aired + ", members: " + members + ", page Url: " + pageUrl + ", image Url: " + imageUrl + ", score: " + score;
    }


    //This method returns true if every part of the anime object is exactly the same.
    //For this data set, technically no two objects should be the same.
    public boolean equals(Anime obj) {
        boolean isEqual = false;
        if (obj.getTitle().equals(title) && obj.getRank().equals(rank) && obj.getType().equals(type) && obj.getAired().equals(aired) && obj.getMembers() == members && obj.getPageUrl().equals(pageUrl) && obj.getImageUrl().equals(imageUrl) && (Math.abs(obj.getScore() - score) < 0.00001)) {
            //some anime objects have unkonwn episodes of string value with a ? so this check compares between the two data types: int and String
            if (episodes == 0 && obj.getEpisodes() == 0) {
                isEqual = unknownEpisodes.equals(obj.getUnknownEpisodes());
            }
            else if (episodes != 0 && obj.getEpisodes() != 0) {
                isEqual = episodes == obj.getEpisodes();
            }
        }
        return isEqual;
    }

    @Override
    public int compareTo(Anime anime2) {
        return this.rank.compareTo(anime2.getRank());
    }

}
