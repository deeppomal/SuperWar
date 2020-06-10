package com.deeppomal.superherob;

public class BattleSiteDB {

    private String[] battleSiteName = new String[]{"Stark Tower", "Baxter Building","Helicarrier","Klyntar","Nine Realms"};
    private String[] battleSiteInfo = new String[]{ "The tower formerly known as Stark Tower and Avengers Tower is a high-rise building " +
            "located in NYC, constructed by Tony Stark." +
            "\n\nA mixture of Intelligence and Speed would increase chances of winning a battle at this site",
    "The Baxter Building is a 35-story building located at 42nd Street and Madison Avenue, Manhattan, New York City" +
            " It has been home to the Fantastic Four."+
    "\n\nTo win here a fighter must have good combination of Strength , Power and Combat.",
    "The Helicarrier is an advanced aerial vehicle designed to be capable of sustained, independently-powered flight."+
    "\n\nDurability and Strength is a must to win here.",
            "Klyntar is an artificial planet, located in a remote sector of the Andromeda Galaxy."+
                    "\n\nIntelligence and Combat would be favoured at this battle site.",
            "The Nine Realms are a group of distant planets that are interconnected by the cosmic nexus " +
                    "'Yggdrasil' and are home to various different races and cultures."+
                    " \n\nTo win here a fighter must have a good combination of Power and Durability."
    };

    private int [] knownHeroes = new int[]{6,14,17,30,31,35,36,46,60,63,65,67,68,69,71,89,97,104,106,107,112,145,149,156,160,161,165,
    167,194,196,201,213,218,226,232,234,235,236,242,247,251,263,275,280,285,287,289,299,303,307,309,313,321,332,339,
    345,346,357,370,374,405,414,423,430,476,479,483,485,487,489,498,502,508,522,530,536,537,547,550,558,566,572,589,620,623,630,644,655,
    659,660,669,680,687,690,697,703,708,714,717,720,724,730};
    private Double [][] battleScore = new Double[][]{
            {1.2,1.0,1.4,1.0,1.0,1.0},
            {1.0,1.2,1.0,1.0,1.4,1.6},
            {1.0,1.2,1.0,1.4,1.0,1.0},
            {1.2,1.0,1.0,1.0,1.0,1.4},
            {1.0,1.0,1.0,1.2,1.4,1.0}
    };

    public String getBattleSiteName(int index)
    {
        return battleSiteName[index];
    }
    public String getBattleSiteInfo(int index)
    {
        return battleSiteInfo[index];
    }
    public Integer getKnownHeroes(int index){return knownHeroes[index];}
    public Integer getknownHeroesSize(){return knownHeroes.length;}
    public Double getBattleScore(int index1,int index2) {
        return battleScore[index1][index2];
    }
}
