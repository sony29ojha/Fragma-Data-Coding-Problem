package fragmadata.soluction;

/**
 *
 * @author Sony
 */
public class FragmaDataSolution {
    public static void main(String[] args) {
       MatchCsvFile match = new MatchCsvFile();
       match.topTeamFieldFirst();
        
        DeliveriesCsvFile delivery = new DeliveriesCsvFile();
        delivery.init();
        delivery.printFourSixTotalCountByTeamAndYear();
        delivery.printEconomyRateOfBoller();
        delivery.printRunRateOfTeam();
    }
}
