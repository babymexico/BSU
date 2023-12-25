class Match {
    private final Team team1;
    private final Team team2;
    private final int goalsTeam1;
    private final int goalsTeam2;

    public Match(Team team1, Team team2, int goalsTeam1, int goalsTeam2) {
        this.team1 = team1;
        this.team2 = team2;
        this.goalsTeam1 = goalsTeam1;
        this.goalsTeam2 = goalsTeam2;
    }

    public Team getTeam1() {
        return team1;
    }

    public Team getTeam2() {
        return team2;
    }

    public int getGoalsTeam1() {
        return goalsTeam1;
    }

    public int getGoalsTeam2() {
        return goalsTeam2;
    }
}