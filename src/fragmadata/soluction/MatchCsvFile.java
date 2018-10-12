package fragmadata.soluction;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Sony
 * this class operation related to match.csv file
 */
public class MatchCsvFile {
    //read data from matche.csv file w.r.t field and values
    // similer to sql where class (where year = 2016 and year = 2017)
    public LinkedList<Matches> getMatchCsvFileData(String fileName, int field, String values[]){
        BufferedReader reader = FileUtil.getBufferReader(fileName);
        String line = "";
        LinkedList<Matches> matches = new LinkedList<Matches>();
        if(reader == null){
            System.out.println("Unable to read data from "+fileName);
            System.exit(0);
        }
        try {
            //remove header
            reader.readLine();
            while((line = reader.readLine()) != null) {
                //Get all data available in line
                String[] tokens = line.split(FileUtil.COMMA_DELIMITER);
                if(tokens.length > 0){
                    Matches matche = new Matches();
                    try {
                        matche.setMatchId(Integer.parseInt(tokens[Matches.MATCH_ID]));
                        matche.setSeason(tokens[Matches.SEASON]);
                        matche.setCity(tokens[Matches.CITY]);
                        matche.setDate(tokens[Matches.DATE]);
                        matche.setTeam1(tokens[Matches.TEAM1]);
                        matche.setTeam2(tokens[Matches.TEAM2]);
                        matche.setTossWinner(tokens[Matches.TOSS_WINNER]);
                        matche.setTossDecision(tokens[Matches.TOSS_DECISION]);
                        matche.setResult(tokens[Matches.RESULT]);
                        matche.setWinner(tokens[Matches.WINNER]);
                        if(matche.checkIndexAndValues(field, values))
                            matches.add(matche);
                    } catch (Exception e){
                        //System.out.println("data missing at "+matche.getMatchId()+" at index "+e.getMessage());
                    }
                }
            }
        } catch (IOException ex) {
            System.out.println("Error while parsing data as "+ex.getMessage());
        }
        return matches;
    }
    
    //sort map by value and returm top n
    private List<Entry<String, Integer>> getTopBySeastion(Map<Map<String,String>,Integer> map,String season, int top){
        Map<String,Integer> filterMap = new HashMap<String,Integer>();
        for(Map.Entry<Map<String,String>,Integer> entry : map.entrySet()){
            Map<String, String> key = entry.getKey();
            for(Map.Entry<String,String> secondEntry : key.entrySet()){
                String key1 = secondEntry.getKey();
                //check for year
                if(key1.equals(season)){   
                    filterMap.put(secondEntry.getValue(), entry.getValue());
                }
            }
        }
        Set<Entry<String, Integer>> set = filterMap.entrySet();
        List<Entry<String, Integer>> list = new ArrayList<Entry<String, Integer>>(set);
        Collections.sort( list, new Comparator<Entry<String, Integer>>()
        {
            @Override
            public int compare(Entry<String, Integer> o1,Entry<String, Integer> o2 )
            {
                return (o2.getValue()).compareTo( o1.getValue() );
            }
        } );
        return list.subList(0, top);
    }
    
    //get teams which elected to field first after winning toss in the year 2016 and 2017.
    public void topTeamFieldFirst(){
        MatchCsvFile reader = new MatchCsvFile();
        String file = "";
        try {
            file = new java.io.File( "." ).getCanonicalPath()+"\\Resources\\matches.csv";
        } catch (IOException ex) {
            System.out.println(file+" File not found ");
        }
        //read data of year 2016 and 2017 only
        LinkedList<Matches> matchCsvFileData = reader.getMatchCsvFileData(file, Matches.SEASON, new String[]{"2016","2017"});
        //map to store year team and count
        Map<Map<String,String>,Integer> countByField = new HashMap<Map<String, String>,Integer>();
        for (int i = 0; i < matchCsvFileData.size(); i++) {
            Matches match = matchCsvFileData.get(i);
            //team who chose field first
            if("field".equals(match.getTossDecision())){
                String year = match.getSeason();
                String team = match.getTossWinner();
                Map<String,String> yearAndTeam = new HashMap<String, String>();
                yearAndTeam.put(year, team);
                //if already present in map thrn increase count else add with ount = 1
                if(countByField.containsKey(yearAndTeam)){
                    int count = countByField.get(yearAndTeam);
                    countByField.put(yearAndTeam, count+1);
                } else {
                    countByField.put(yearAndTeam, 1);
                }
            }
        }
        List<Entry<String, Integer>> topBySeastion = getTopBySeastion(countByField, "2017", 4);
        System.out.println("---------------------------solution 1-------------------------------------------------\n");
        System.out.println("year\tTeam\tCount");
        print("2017",topBySeastion);
        topBySeastion = getTopBySeastion(countByField, "2016", 4);
        print("2016",topBySeastion);
    }
  
    //print in formate
    private void print(String season,List<Entry<String, Integer>> printdata){
        for(Map.Entry<String,Integer> entry : printdata){
            System.out.println(season+"\t"+entry.getKey()+"\t"+entry.getValue());
        }
    }
    
    //return mapping between matchId nad year
    public Map<Integer,String> getMappingOfYearAndMatchId(){
        BufferedReader reader = null;
        
        try {
            String file = new java.io.File( "." ).getCanonicalPath()+"\\Resources\\matches.csv";
            reader = FileUtil.getBufferReader(file);
        } catch (IOException ex) {
            System.out.println("File not found as "+ex.getMessage());
        }
        String line = "";
        Map<Integer,String> mapping = new HashMap<Integer,String>();
        if(reader == null){
            System.out.println("Unable to read data from file");
            System.exit(0);
        }
        try {
            //remove header
            reader.readLine();
            while((line = reader.readLine()) != null) {
                //Get all data available in line
                String[] tokens = line.split(FileUtil.COMMA_DELIMITER);
                if(tokens.length > 0){
                    mapping.put(Integer.parseInt(tokens[Matches.MATCH_ID]),tokens[Matches.SEASON]);
                }
            }
        } catch (IOException ex) {
            System.out.println("Error while parsing data as "+ex.getMessage());
        }
        return mapping;
    }
}
