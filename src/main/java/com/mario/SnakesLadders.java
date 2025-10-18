package com.mario;

import java.util.Map;

import static java.util.Map.entry;

// Kara URL: https://www.codewars.com/kata/587136ba2eefcb92a9000027/java
// When I was a child, I used to play this game with my two brothers, so I added support for multiple players (:
public class SnakesLadders {

    public SnakesLadders() {}

    int noPlayers = 2;
    private final int [] playersPosition = new int[noPlayers];
    private int currentPlayer = 0;
    private boolean gameOver = false;

    private final Map<Integer, Integer> snakes = Map.ofEntries(
        entry(16, 6),
        entry(49, 11),
        entry(46, 25),
        entry(62, 19),
        entry(64, 60),
        entry(74, 53),
        entry(89, 68),
        entry(92, 88),
        entry(95, 75),
        entry(99, 80)
    );

    private final Map<Integer, Integer> ladders = Map.ofEntries(
        entry(2, 38),
        entry(7, 14),
        entry(8, 31),
        entry(15, 26),
        entry(21, 42),
        entry(28, 84),
        entry(36, 44),
        entry(51, 67),
        entry(71, 91),
        entry(78, 98),
        entry(87, 94)
    );


    public String play(int die1, int die2) {
        if (this.gameOver) {
            return "Game over!";
        }

        this.playersPosition[currentPlayer] += die1 + die2;

        if (this.playersPosition[currentPlayer] == 100) {
            this.gameOver = true;
            return "Player " + (currentPlayer + 1) + " Wins!";
        } else if(this.playersPosition[currentPlayer] > 100) {
            this.playersPosition[currentPlayer] = 100 - this.playersPosition[currentPlayer] % 100;
        }

        if(this.snakes.containsKey(this.playersPosition[currentPlayer])) {
            this.playersPosition[currentPlayer] = this.snakes.get(this.playersPosition[currentPlayer]);
        } else if(this.ladders.containsKey(this.playersPosition[currentPlayer])) {
            this.playersPosition[currentPlayer] = this.ladders.get(this.playersPosition[currentPlayer]);
        }

        String label =  "Player " + (currentPlayer + 1) + " is on square " + this.playersPosition[currentPlayer];
        this.currentPlayer = die1 != die2 ? (this.currentPlayer + 1) % noPlayers : this.currentPlayer;
        return label;
    }

}
