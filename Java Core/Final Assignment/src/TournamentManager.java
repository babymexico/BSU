import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

class TournamentManager {
    private static TournamentManager instance;
    private final List<Group> groups;
    private final List<Match> matches;
    private MatchResultStrategy matchResultStrategy;

    private TournamentManager() {
        this.groups = new ArrayList<>();
        this.matches = new ArrayList<>();
        this.matchResultStrategy = new DefaultMatchResultStrategy();
    }

    public static TournamentManager getInstance() {
        if (instance == null) {
            instance = new TournamentManager();
        }
        return instance;
    }

    public void addGroup(Group group) {
        groups.add(group);
    }

    public void addMatch(Match match) {
        matches.add(match);
    }

    public void setMatchResultStrategy(MatchResultStrategy strategy) {
        this.matchResultStrategy = strategy;
    }

    private Team findTeamByName(String teamName) {
        for (Group group : groups) {
            for (Team team : group.getTeams()) {
                if (team.getName().equals(teamName)) {
                    return team;
                }
            }
        }
        return null;
    }

    public void readMatchesFromFile(String fileName) {
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(" ");

                String team1Name = parts[0];
                String team2Name = parts[2];
                String goalsTeam1 = parts[1].split(":")[0];
                String goalsTeam2 = parts[1].split(":")[1];

                int goals1 = Integer.parseInt(goalsTeam1);
                int goals2 = Integer.parseInt(goalsTeam2);

                Team team1 = findTeamByName(team1Name);
                Team team2 = findTeamByName(team2Name);

                if (team1 != null && team2 != null) {
                    Match match = new Match(team1, team2, goals1, goals2);
                    matches.add(match);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void readGroupsFromFile(String fileName) {
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(" ");

                String groupName = parts[0];
                Group group = new Group(groupName);
                for (int i = 1; i < parts.length; i++) {
                    String teamName = parts[i];
                    Team team = new Team(teamName);
                    group.addTeam(team);
                }

                addGroup(group);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void calculateGroupResults() {
        for (Group group : groups) {
            for (Match match : matches) {
                if (group.getTeams().contains(match.getTeam1()) && group.getTeams().contains(match.getTeam2())) {
                    matchResultStrategy.updateMatchResult(
                            match.getTeam1(), match.getTeam2(),
                            match.getGoalsTeam1(), match.getGoalsTeam2()
                    );
                }
            }
        }
    }
    public void printGroupTablesToFile(String fileName) {
        try (PrintWriter writer = new PrintWriter(fileName)) {
            for (Group group : groups) {
                writer.println("Группа " + group.getName() + ":");
                for (Team team : group.getTeams()) {
                    writer.println(team.getName() + " " + team.getPoints() + " " + team.getWins() + " " + team.getDraws() +
                            " " + team.getLosses() + " " + team.getGoalsScored() + " " + team.getGoalsConceded());
                }
                writer.println();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void printResultsToFile(String fileName) {
        try (PrintWriter writer = new PrintWriter(fileName)) {
            List<Team> sortedTeams = new ArrayList<>();

            for (Group group : groups) {
                sortedTeams.addAll(group.getTeams());
            }

            sortedTeams.sort(Comparator.comparing(Team::getPoints).reversed().thenComparing(Team::getMatchesPlayed));

            for (Team team : sortedTeams) {
                writer.println(team.getName() + " | " + team.getPoints() + " | " + team.getMatchesPlayed());
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}