class Team {
    private final String name;
    private int points;
    private int wins;
    private int draws;
    private int losses;
    private int goalsScored;
    private int goalsConceded;

    public Team(String name) {
        this.name = name;
        this.points = 0;
        this.wins = 0;
        this.draws = 0;
        this.losses = 0;
        this.goalsScored = 0;
        this.goalsConceded = 0;
    }

    public int getMatchesPlayed(){
        return wins + draws + losses;
    }
    public String getName() {
        return name;
    }

    public int getPoints() {
        return points;
    }

    public int getWins() {
        return wins;
    }

    public int getDraws() {
        return draws;
    }

    public int getLosses() {
        return losses;
    }

    public int getGoalsScored() {
        return goalsScored;
    }

    public int getGoalsConceded() {
        return goalsConceded;
    }

    public void addWin() {
        points += 3;
        wins++;
    }

    public void addDraw() {
        points += 2;
        draws++;
    }

    public void addLoss() {
        points += 1;
        losses++;
    }

    public void addGoals(int scored, int conceded) {
        goalsScored += scored;
        goalsConceded += conceded;
    }
}