public class Main {
    public static void main(String[] args) {
        TournamentManager tournamentManager = TournamentManager.getInstance();

        tournamentManager.readGroupsFromFile("GroupsEN.txt");
        tournamentManager.readMatchesFromFile("GameEN.txt");

        tournamentManager.calculateGroupResults();
        tournamentManager.printGroupTablesToFile("GroupsOut.txt");
        tournamentManager.printResultsToFile("Results.txt");
    }
}