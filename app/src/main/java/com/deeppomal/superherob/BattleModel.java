package com.deeppomal.superherob;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class BattleModel {

    @SerializedName("response")
    private String response;

    @SerializedName("id")
    private String id;

    @SerializedName("name")
    private String name;

    @SerializedName("powerstats")
    private powerstats powerstats;

    @SerializedName("biography")
    private biography biography;

    @SerializedName("appearance")
    private appearance appearance;

    @SerializedName("work")
    private work work;

    @SerializedName("connections")
    private connections connections;

    @SerializedName("image")
    private image image;


    public BattleModel(String response, String id, String name,powerstats powerstats, biography biography, appearance appearance,
                       work work,connections connections,image image) {
        this.response=response;
        this.id = id;
        this.name = name;
        this.powerstats = powerstats;
        this.biography = biography;
        this.appearance = appearance;
        this.work = work;
        this.connections = connections;
        this.image = image;


    }

    public String getResponse() {
        return response;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public BattleModel.work getWork() {
        return work;
    }

    public BattleModel.image getImage() {
        return image;
    }

    public BattleModel.powerstats getPowerstats() {
        return powerstats;
    }

    public BattleModel.biography getBiography() {
        return biography;
    }


    public static class powerstats  {

        @SerializedName("intelligence")
        private String intelligence;

        @SerializedName("strength")
        private String strength;

        @SerializedName("speed")
        private String speed;

        @SerializedName("durability")
        private String durability;

        @SerializedName("power")
        private String power;

        @SerializedName("combat")
        private String combat;

        public powerstats(String intelligence,String strength,String speed,String durability,String power,String combat)
        {
                this.intelligence=intelligence;
                this.strength=strength;
                this.speed=speed;
                this.durability=durability;
                this.power=power;
                this.combat=combat;
        }

        public String getSpeed() {
            return speed;
        }

        public String getDurability() {
            return durability;
        }

        public String getPower() {
            return power;
        }

        public String getStrength() {
            return strength;
        }

        public String getCombat() {
            return combat;
        }

        public String getIntelligence() {
            return intelligence;
        }

    }
    public static class biography {
        @SerializedName("full-name")
        private String full_name;

        @SerializedName("alter-egos")
        private String alter_egos;

        @SerializedName("aliases")
        private ArrayList<String> aliases;

        @SerializedName("place-of-birth")
        private String place_of_birth;

        @SerializedName("first-appearance")
        private String first_appearance;

        @SerializedName("publisher")
        private String publisher;

        @SerializedName("alignment")
        private String alignment;

        public biography(String full_name,String alter_egos,ArrayList<String> aliases,String place_of_birth,
                         String first_appearance,String publisher,String alignment)
        {
            this.full_name=full_name;
            this.alter_egos=alter_egos;
            this.aliases=aliases;
            this.place_of_birth=place_of_birth;
            this.first_appearance=first_appearance;
            this.publisher=publisher;
            this.alignment=alignment;
        }

        public ArrayList<String> getAliases() {
            return aliases;
        }

        public String getAlter_egos() {
            return alter_egos;
        }

        public String getFirst_appearance() {
            return first_appearance;
        }

        public String getFull_name() {
            return full_name;
        }

        public String getPlace_of_birth() {
            return place_of_birth;
        }

        public String getAlignment() {
            return alignment;
        }

        public String getPublisher() {
            return publisher;
        }

    }

    public static class appearance {

        @SerializedName("gender")
        private String gender;

        @SerializedName("race")
        private String race;

        @SerializedName("height")
        private ArrayList<String> height;

        @SerializedName("weight")
        private ArrayList<String> weight;

        @SerializedName("eye-color")
        private String eye_color;

        @SerializedName("hair-color")
        private String hair_color;

        public appearance(String gender,String race,ArrayList<String> height,ArrayList<String> weight,
                         String eye_color,String hair_color)
        {
            this.gender=gender;
            this.race=race;
            this.height=height;
            this.weight=weight;
            this.eye_color=eye_color;
            this.hair_color=hair_color;
        }

        public ArrayList<String> getHeight() {
            return height;
        }

        public ArrayList<String> getWeight() {
            return weight;
        }

        public String getEye_color() {
            return eye_color;
        }

        public String getGender() {
            return gender;
        }

        public String getHair_color() {
            return hair_color;
        }

        public String getRace() {
            return race;
        }
    }

    public static class work{

        @SerializedName("occupation")
        private String occupation;

        @SerializedName("base")
        private String base;

        public work(String occupation,String base)
        {
            this.occupation=occupation;
            this.base=base;
        }

        public String getBase() {
            return base;
        }

        public String getOccupation() {
            return occupation;
        }
    }

    public static class connections {
        @SerializedName("group-affiliation")
        private String group_affiliation;

        @SerializedName("relatives")
        private String relatives;

        public connections(String group_affiliation,String relatives)
        {
            this.group_affiliation=group_affiliation;
            this.relatives=relatives;
        }

        public String getGroup_affiliation() {
            return group_affiliation;
        }

        public String getRelatives() {
            return relatives;
        }
    }

    public static class image {

        @SerializedName("url")
        private String url;

        public image (String url)
        {
            this.url=url;
        }

        public String getUrl() {
            return url;
        }
    }
}
