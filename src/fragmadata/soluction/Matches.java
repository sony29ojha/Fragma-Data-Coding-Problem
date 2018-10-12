package fragmadata.soluction;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author Sony
 * its a pojo class use to represent data of matches.csv file
 */
public class Matches {
    //intex for column name
    public static final int MATCH_ID = 0;
    public static final int SEASON = 1;
    public static final int CITY = 2;
    public static final int DATE = 3;
    public static final int TEAM1 = 4;
    public static final int TEAM2 = 5;
    public static final int TOSS_WINNER = 6;
    public static final int TOSS_DECISION = 7;
    public static final int RESULT = 8;
    public static final int WINNER = 9;
    
    //data members of the class which represent one record of the matches.csv file
    private int matchId;
    private String season;
    private String city;
    private String date;
    private String team1;
    private String team2;
    private String tossWinner;
    private String tossDecision;
    private String result;
    private String winner;
    
    //getter and setter

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getMatchId() {
        return matchId;
    }

    public void setMatchId(int matchId) {
        this.matchId = matchId;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getSeason() {
        return season;
    }

    public void setSeason(String season) {
        this.season = season;
    }

    public String getTeam1() {
        return team1;
    }

    public void setTeam1(String team1) {
        this.team1 = team1;
    }

    public String getTeam2() {
        return team2;
    }

    public void setTeam2(String team2) {
        this.team2 = team2;
    }

    public String getTossWinner() {
        return tossWinner;
    }

    public void setTossWinner(String tossWinner) {
        this.tossWinner = tossWinner;
    }

    public String getTossDecision() {
        return tossDecision;
    }

    public void setTossDecision(String tossDecision) {
        this.tossDecision = tossDecision;
    }

    public String getWinner() {
        return winner;
    }

    public void setWinner(String winner) {
        this.winner = winner;
    }
    
    /* check filed and values are equle or not
     * retun ture if matches else return false
     */
    public boolean checkIndexAndValues(int index, String values[]) {
        List<String> value = new ArrayList<String>(Arrays.asList(values));
        switch(index){
            case MATCH_ID:
                if(value.contains(getMatchId())) {
                    return true;
                } else {
                    return false;
                }
            case SEASON:
                if(value.contains(getSeason())) {
                    return true;
                } else {
                    return false;
                }
            case CITY:
                if(value.contains(getCity())) {
                    return true;
                } else {
                    return false;
                }
            case DATE:
                if(value.contains(getDate())) {
                    return true;
                } else {
                    return false;
                }
            case TEAM1:
                if(value.contains(getTeam1())) {
                    return true;
                } else {
                    return false;
                }
            case TEAM2:
                 if(value.contains(getTeam2())) {
                    return true;
                } else {
                    return false;
                }
            case TOSS_WINNER:
                 if(value.contains(getTossWinner())) {
                    return true;
                } else {
                    return false;
                }
            case TOSS_DECISION:
                 if(value.contains(getTossDecision())) {
                    return true;
                } else {
                    return false;
                }
            case RESULT:
                 if(value.contains(getResult())) {
                    return true;
                } else {
                    return false;
                }
            case WINNER:
                 if(value.contains(getWinner())) {
                    return true;
                } else {
                    return false;
                }
            default:
                return false;
        }
    }
}
