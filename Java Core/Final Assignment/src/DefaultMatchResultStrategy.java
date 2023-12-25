class DefaultMatchResultStrategy implements MatchResultStrategy {
    @Override
    public void updateMatchResult(Team team1, Team team2, int goalsTeam1, int goalsTeam2) {
        if (goalsTeam1 > goalsTeam2) {
            team1.addWin();
            team2.addLoss();
        } else if (goalsTeam1 < goalsTeam2) {
            team1.addLoss();
            team2.addWin();
        } else {
            team1.addDraw();
            team2.addDraw();
        }
        team1.addGoals(goalsTeam1, goalsTeam2);
        team2.addGoals(goalsTeam2, goalsTeam1);
    }
}