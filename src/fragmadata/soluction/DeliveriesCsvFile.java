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
import java.util.Map.Entry;
import java.util.Set;

/**
 *
 * @author Sony
 * this class operation related to deliveries.csv file
 */
public class DeliveriesCsvFile {
    //maping of matchId and year
    Map<Integer,String> mappingOfMatchIdWithYear = new HashMap<Integer,String>();
    //store foursixtotalruncount
    Map<String,String> map = new HashMap<String,String>();
    //store totalruns legby by w.r.t year and player
    //key=year,player value=total,leyby,by,bollcount
    Map<String,String> economy = new HashMap<String,String>();
    //store net run rate data
    //key=year,team value=runScred,bollRaced,runConCede,bollBowled
    Map<String,String> runrate = new HashMap<String, String>();
    //constructor
    public DeliveriesCsvFile() {
        //loging data..
        MatchCsvFile object = new MatchCsvFile();
        mappingOfMatchIdWithYear = object.getMappingOfYearAndMatchId();
    }
    //read data from deliveries.csv file
    private LinkedList<Deliveries> getDeliveriesCsvFileData(String fileName){
        BufferedReader reader = FileUtil.getBufferReader(fileName);
        String line = "";
        LinkedList<Deliveries> deliveries = new LinkedList<Deliveries>();
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
                    Deliveries deliverie = new Deliveries();
                    try {
                        deliverie.setMatchId(Integer.parseInt(tokens[Deliveries.MATCH_ID]));
                        deliverie.setInning(Integer.parseInt(tokens[Deliveries.INNING]));
                        deliverie.setBattingTeam(tokens[Deliveries.BATTING_TEAM]);
                        deliverie.setBowlingTeam(tokens[Deliveries.BOWLING_TEAM]);
                        deliverie.setOver(Integer.parseInt(tokens[Deliveries.OVER]));
                        deliverie.setBall(Integer.parseInt(tokens[Deliveries.BALL]));
                        deliverie.setBastman(tokens[Deliveries.BATSMAN]);
                        deliverie.setBowler(tokens[Deliveries.BOWLER]);
                        deliverie.setWideRuns(Integer.parseInt(tokens[Deliveries.WIDE_RUNS]));
                        deliverie.setByeRuns(Integer.parseInt(tokens[Deliveries.BYE_RUNS]));
                        deliverie.setLegBuyRuns(Integer.parseInt(tokens[Deliveries.LEGBYE_RUNS]));
                        deliverie.setNoBallRuns(Integer.parseInt(tokens[Deliveries.NOBALL_RUNS]));
                        deliverie.setPenaltyruns(Integer.parseInt(tokens[Deliveries.PENALTY_RUNS]));
                        deliverie.setBastmanRuns(Integer.parseInt(tokens[Deliveries.BATSMAN_RUNS]));
                        deliverie.setExtraRuns(Integer.parseInt(tokens[Deliveries.EXTRA_RUNS]));
                        deliverie.setTotalRuns(Integer.parseInt(tokens[Deliveries.TOTAL_RUNS]));
                        deliveries.add(deliverie);
                    } catch (Exception e){
                        //System.out.println("data missing at "+matche.getMatchId()+" at index "+e.getMessage());
                    }
                }
            }
        } catch (IOException ex) {
            System.out.println("Error while parsing data as "+ex.getMessage());
        }
        return deliveries;
    }
    
    //iterating all deliveries and storing number of fours, six and total runs
    //key = MATCH_ID,BATTING_TEAM values=Four,six,total runes

    private void getFourSixByTeamAndYear(LinkedList<Deliveries> deliveries){
        for(int i=0;i<deliveries.size();i++){
            Deliveries delivery = deliveries.get(i);
            int matchId = delivery.getMatchId();
            String year = getYear(matchId);
            String team = delivery.getBattingTeam();
            int four_count=0,six_count=0;
            if(delivery.getBastmanRuns() == 4){
                four_count++;
            } else if(delivery.getBastmanRuns() == 6){
                six_count++;
            }
            int totalRuns = delivery.getTotalRuns();
            String key = year+FileUtil.COMMA_DELIMITER+team;
            String value = four_count+FileUtil.COMMA_DELIMITER+six_count+FileUtil.COMMA_DELIMITER+totalRuns;
            //store to map teram un
            if(map.containsKey(key)){
                String get = map.get(key);
                String[] split = get.split(FileUtil.COMMA_DELIMITER);
                four_count += Integer.parseInt(split[0]);
                six_count += Integer.parseInt(split[1]);
                totalRuns += Integer.parseInt(split[2]);
                value = four_count+FileUtil.COMMA_DELIMITER+six_count+FileUtil.COMMA_DELIMITER+totalRuns;
                map.put(key,value);
            } else {
                map.put(key,value);
            }  
            
            //store to economy
            key=year+FileUtil.COMMA_DELIMITER+delivery.getBowler();
            int boll = 1;
            if(delivery.getWideRuns()!=0){
                boll = 0;
            } 
            if(delivery.getNoBallRuns() != 0){
                boll = 0;
            }
            value=delivery.getTotalRuns()+FileUtil.COMMA_DELIMITER+delivery.getLegBuyRuns()+FileUtil.COMMA_DELIMITER
                    +delivery.getByeRuns()+FileUtil.COMMA_DELIMITER+boll;
            if(economy.containsKey(key)){
                String get = economy.get(key);
                String[] split = get.split(FileUtil.COMMA_DELIMITER);
                int totalRunes = delivery.getTotalRuns()+Integer.parseInt(split[0]);
                int legby = delivery.getLegBuyRuns()+Integer.parseInt(split[1]);
                int by = delivery.getByeRuns()+Integer.parseInt(split[2]);
                boll = Integer.parseInt(split[3])+boll;
                value = totalRunes+FileUtil.COMMA_DELIMITER+legby+FileUtil.COMMA_DELIMITER+by+FileUtil.COMMA_DELIMITER+
                        boll;
                economy.put(key, value);
            } else {
                economy.put(key, value);
            }
            
            //storing to runrate map data as betting team
            boll = 1;
            if(delivery.getWideRuns()!=0){
                boll = 0;
            } 
            if(delivery.getNoBallRuns() != 0){
                boll = 0;
            }
            key=year+FileUtil.COMMA_DELIMITER+delivery.getBattingTeam();
            value=delivery.getTotalRuns()+FileUtil.COMMA_DELIMITER+boll+FileUtil.COMMA_DELIMITER+"0"+FileUtil.COMMA_DELIMITER+"0";
            if(runrate.containsKey(key)){
                String get = runrate.get(key);
                String[] split = get.split(FileUtil.COMMA_DELIMITER);
                int run = Integer.parseInt(split[0])+delivery.getTotalRuns();
                boll = Integer.parseInt(split[1])+boll;
                value = run+FileUtil.COMMA_DELIMITER+boll+FileUtil.COMMA_DELIMITER+split[2]+FileUtil.COMMA_DELIMITER+split[3];
                runrate.put(key, value);
            } else {
                runrate.put(key, value);
            }
            
            
            //storing to runrate map data as bowling team
            boll = 1;
            if(delivery.getWideRuns()!=0){
                boll = 0;
            } 
            if(delivery.getNoBallRuns() != 0){
                boll = 0;
            }
            key=year+FileUtil.COMMA_DELIMITER+delivery.getBowlingTeam();
            value="0"+FileUtil.COMMA_DELIMITER+"0"+FileUtil.COMMA_DELIMITER+delivery.getTotalRuns()+FileUtil.COMMA_DELIMITER+boll;
            if(runrate.containsKey(key)){
                String get = runrate.get(key);
                String[] split = get.split(FileUtil.COMMA_DELIMITER);
                int run = Integer.parseInt(split[2])+delivery.getTotalRuns();
                boll = Integer.parseInt(split[3])+boll;
                value = split[0]+FileUtil.COMMA_DELIMITER+split[1]+FileUtil.COMMA_DELIMITER+run+FileUtil.COMMA_DELIMITER+boll;
                runrate.put(key, value);
            } else {
                runrate.put(key, value);
            }
        }
    }
    
    public void init(){
        String file = "";
        try {
            file = new java.io.File( "." ).getCanonicalPath()+"\\Resources\\deliveries.csv";
        } catch (IOException ex) {
            System.out.println(file+" File not found ");
        }
        getFourSixByTeamAndYear(getDeliveriesCsvFileData(file));
    }
    
    public void printFourSixTotalCountByTeamAndYear(){
        System.out.println("---------------------------solution 2-------------------------------------------------\n");
        System.out.println("YEAR\tTEAM_NAME\tFOURS_COUNT\tSIXES_COUNT\tTOTAL_SCORE");
        for(Map.Entry<String,String> entry : map.entrySet()){
            String key = entry.getKey();
            String value = entry.getValue();
            String[] split = key.split(FileUtil.COMMA_DELIMITER);
            String[] split1 = value.split(FileUtil.COMMA_DELIMITER);
            System.out.println(split[0]+"\t"+split[1]+"\t"+split1[0]+"\t"+split1[1]+"\t"+split1[2]);
        }
    }
    
    public void printEconomyRateOfBoller(){
        System.out.println("---------------------------solution 3-------------------------------------------------\n");
        System.out.println("YEAR\tPLAYER\tECONOMY");
        Map<String,Map<String,Double>> byYear = getEconomyByYear(economy);
        for(Map.Entry<String,Map<String,Double>> entry : byYear.entrySet()){
            Map<String, Double> value = entry.getValue();
             List<Entry<String, Double>> topTen = getTopTen(value);
            for(int i=0;i<topTen.size();i++){
                Entry<String, Double> get = topTen.get(i);
                 System.out.println(entry.getKey()+"\t"+get.getKey()+"\t"+get.getValue());
            }
        }
    }
    
    public void printRunRateOfTeam(){
        System.out.println("---------------------------solution 4-------------------------------------------------\n");
        System.out.println("YEAR\tTEAM\tNET_RUN_RATE");
        Map<String, String> maxRunRateByYear = getMaxRunRateByYear(runrate);
        for(Map.Entry<String,String> entry : maxRunRateByYear.entrySet()){
            String key = entry.getKey();
            String value = entry.getValue();
            String[] split = value.split(FileUtil.COMMA_DELIMITER);
            System.out.println(key+"\t"+split[0]+"\t"+split[1]);
        }
    }

    private String getYear(int matchId) {
        return mappingOfMatchIdWithYear.get(matchId);
    }
    
    //calculate runrate
    private Double getRunRate(int totalScored,int bollBat, int totalConcended, int bollBow){
        Double overBat = bollBat/6.0;
        Double overBow = bollBow/6.0;
        Double runRate = (totalScored/overBat)-(totalConcended/bollBow);
        return runRate;
    }

    private  Map<String,String> getMaxRunRateByYear(Map<String, String> runrate) {
        Map<String,String> runrateByYear = new HashMap<String, String>();
        for(Map.Entry<String,String> entry : runrate.entrySet()){
            String key = entry.getKey();
            String[] split = key.split(FileUtil.COMMA_DELIMITER);
            String year = split[0];
            String team = split[1];
            String value = entry.getValue();
            String[] split1 = value.split(FileUtil.COMMA_DELIMITER);
            Double calRunRate = getRunRate(Integer.parseInt(split1[0]), Integer.parseInt(split1[1]), Integer.parseInt(split1[2]), 
                    Integer.parseInt(split1[3]));
            String putValue = team+FileUtil.COMMA_DELIMITER+calRunRate;
            if(runrateByYear.containsKey(year)){
                String get = runrateByYear.get(year);
                String[] split2 = get.split(FileUtil.COMMA_DELIMITER);
                String getTeam = split2[0];
                Double getCalRunRate = Double.parseDouble(split2[1]);
                if(getCalRunRate<calRunRate){
                     runrateByYear.put(year, putValue);
                }
            } else {
                runrateByYear.put(year, putValue);
            }
        }
        return runrateByYear;
    }
    
    //calculate economy
    private Double getEconomy(int totalRun, int legBy, int by, int boll){
        Double over = boll/6.0;
        Double economy = (totalRun - legBy - by) / over;
        return  economy;
    }

    private Map<String, Map<String, Double>> getEconomyByYear(Map<String, String> economy) {
        Map<String, Map<String, Double>> byYear = new HashMap<String, Map<String, Double>>();
        for(Map.Entry<String,String> entry : economy.entrySet()){
            String key = entry.getKey();
            String[] split = key.split(FileUtil.COMMA_DELIMITER);
            String year = split[0];
            String player = split[1];
            String value = entry.getValue();
            String[] split1 = value.split(FileUtil.COMMA_DELIMITER);
            //if over is grater then 10
            if(Integer.parseInt(split1[3]) >= 60){
                Double calEconomy = getEconomy(Integer.parseInt(split1[0]), Integer.parseInt(split1[1]), Integer.parseInt(split1[2]),
                    Integer.parseInt(split1[3]));
                if(byYear.containsKey(year)){
                    Map<String, Double> get = byYear.get(year);
                    get.put(player, calEconomy);
                    byYear.put(year, get);
                } else {
                    HashMap<String, Double> hashMap = new HashMap<String, Double>();
                    hashMap.put(player, calEconomy);
                    byYear.put(year, hashMap);
                } 
            }        
        }
        return byYear;
    }

    private  List<Entry<String, Double>> getTopTen(Map<String, Double> value) {
        Set<Entry<String, Double>> set = value.entrySet();
        List<Entry<String, Double>> list = new ArrayList<Entry<String, Double>>(set);
        Collections.sort( list, new Comparator<Entry<String, Double>>()
        {
            @Override
            public int compare(Entry<String, Double> o1,Entry<String, Double> o2 )
            {
                return (o2.getValue()).compareTo( o1.getValue() );
            }
        } );
        return  list.subList(list.size()-10, list.size());
    }
}
