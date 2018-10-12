package fragmadata.soluction;

/**
 *
 * @author Sony
 * its a pojo class use to represent data of deliveries.csv file
 */
public class Deliveries {
    //intex for column name
    public static final int MATCH_ID = 0;
    public static final int INNING = 1;
    public static final int BATTING_TEAM = 2;
    public static final int BOWLING_TEAM = 3;
    public static final int OVER = 4;
    public static final int BALL = 5;
    public static final int BATSMAN = 6;
    public static final int BOWLER = 7;
    public static final int WIDE_RUNS = 8;
    public static final int BYE_RUNS = 9;
    public static final int LEGBYE_RUNS = 10;
    public static final int NOBALL_RUNS = 11;
    public static final int PENALTY_RUNS = 12;
    public static final int BATSMAN_RUNS = 13;
    public static final int EXTRA_RUNS = 14;
    public static final int TOTAL_RUNS = 15;
    
    //data members of the class which represent one record of the matches.csv file
    private int matchId;
    private int inning;
    private String battingTeam;
    private String bowlingTeam;
    private int over;
    private int ball;
    private String bastman;
    private String bowler;
    private int wideRuns;
    private int byeRuns;
    private int legBuyRuns;
    private int noBallRuns;
    private int penaltyruns;
    private int bastmanRuns;
    private int extraRuns;
    private int totalRuns;
    
    //geter and setters

    public int getBall() {
        return ball;
    }

    public void setBall(int ball) {
        this.ball = ball;
    }

    public String getBastman() {
        return bastman;
    }

    public void setBastman(String bastman) {
        this.bastman = bastman;
    }

    public int getBastmanRuns() {
        return bastmanRuns;
    }

    public void setBastmanRuns(int bastmanRuns) {
        this.bastmanRuns = bastmanRuns;
    }

    public String getBattingTeam() {
        return battingTeam;
    }

    public void setBattingTeam(String battingTeam) {
        this.battingTeam = battingTeam;
    }

    public String getBowler() {
        return bowler;
    }

    public void setBowler(String bowler) {
        this.bowler = bowler;
    }

    public String getBowlingTeam() {
        return bowlingTeam;
    }

    public void setBowlingTeam(String bowlingTeam) {
        this.bowlingTeam = bowlingTeam;
    }

    public int getByeRuns() {
        return byeRuns;
    }

    public void setByeRuns(int byeRuns) {
        this.byeRuns = byeRuns;
    }

    public int getExtraRuns() {
        return extraRuns;
    }

    public void setExtraRuns(int extraRuns) {
        this.extraRuns = extraRuns;
    }

    public int getInning() {
        return inning;
    }

    public void setInning(int inning) {
        this.inning = inning;
    }

    public int getLegBuyRuns() {
        return legBuyRuns;
    }

    public void setLegBuyRuns(int legBuyRuns) {
        this.legBuyRuns = legBuyRuns;
    }

    public int getMatchId() {
        return matchId;
    }

    public void setMatchId(int matchId) {
        this.matchId = matchId;
    }

    public int getNoBallRuns() {
        return noBallRuns;
    }

    public void setNoBallRuns(int noBallRuns) {
        this.noBallRuns = noBallRuns;
    }

    public int getOver() {
        return over;
    }

    public void setOver(int over) {
        this.over = over;
    }

    public int getPenaltyruns() {
        return penaltyruns;
    }

    public void setPenaltyruns(int penaltyruns) {
        this.penaltyruns = penaltyruns;
    }

    public int getTotalRuns() {
        return totalRuns;
    }

    public void setTotalRuns(int totalRuns) {
        this.totalRuns = totalRuns;
    }

    public int getWideRuns() {
        return wideRuns;
    }

    public void setWideRuns(int wideRuns) {
        this.wideRuns = wideRuns;
    }
    
}
