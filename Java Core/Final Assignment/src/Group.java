import java.util.ArrayList;
import java.util.List;

class Group {
    private final String name;
    private final List<Team> teams;

    public Group(String name) {
        this.name = name;
        this.teams = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public List<Team> getTeams() {
        return teams;
    }

    public void addTeam(Team team) {
        teams.add(team);
    }
}