package com.deeppomal.superherob;

public class FightModel {

    private String war1,war2;
    private String pub1,pub2;
    private String warPic1,warPic2;
    private String result;

    public FightModel()
    {}
    public FightModel(String war1,String war2,String pub1,String pub2,String warPic1,String warPic2,String result) {

        this.war1=war1;
        this.war2=war2;
        this.pub1=pub1;
        this.pub2=pub2;
        this.warPic1=warPic1;
        this.warPic2=warPic2;
        this.result=result;
    }

    public String getPub1() {
        return pub1;
    }

    public String getPub2() {
        return pub2;
    }

    public String getWar1() {
        return war1;
    }

    public String getResult() {
        return result;
    }

    public String getWar2() {
        return war2;
    }

    public String getWarPic1() {
        return warPic1;
    }

    public String getWarPic2() {
        return warPic2;
    }
}
