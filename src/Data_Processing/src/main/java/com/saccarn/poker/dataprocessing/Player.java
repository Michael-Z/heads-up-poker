package com.saccarn.poker.dataprocessing;

/**
 * Created by Neil on 16/02/2017.
 */
public class Player {
    private String name = "";

    private int numBetRaisesTurn = 0;
    private int numBetRaisesRiver = 0;
    private int numBetRaisesFlop = 0;
    private int numBetRaisesPreFlop = 0;
    private int totalActions = 0;
    private int totalNumActionsFlop = 0;
    private int totalNumActionsPreFlop = 0;
    private int totalNumActionsTurn = 0;
    private int totalNumActionsRiver = 0;
    private boolean winner = false;

    public int getTotalNumActionsFlop() {
        return totalNumActionsFlop;
    }

    public int getTotalNumActionsPreFlop() {
        return totalNumActionsPreFlop;
    }

    public int getTotalNumActionsTurn() {
        return totalNumActionsTurn;
    }

    public int getTotalNumActionsRiver() {
        return totalNumActionsRiver;
    }


    public int getNumBetRaisesTurn() {
        return numBetRaisesTurn;
    }

    public int getNumBetRaisesRiver() {
        return numBetRaisesRiver;
    }

    public int getNumBetRaisesFlop() {
        return numBetRaisesFlop;
    }

    public int getNumBetRaisesPreFlop() {
        return numBetRaisesPreFlop;
    }

    public void setNumBetRaisesTurn(int numBetRaisesTurn) {
        this.numBetRaisesTurn = numBetRaisesTurn;
    }

    public void setNumBetRaisesRiver(int numBetRaisesRiver) {
        this.numBetRaisesRiver = numBetRaisesRiver;
    }

    public void setNumBetRaisesFlop(int numBetRaisesFlop) {
        this.numBetRaisesFlop = numBetRaisesFlop;
    }

    public void setNumBetRaisesPreFlop(int preFlopNumBetRaises) {
        this.numBetRaisesPreFlop = preFlopNumBetRaises;
    }

    public int getTotalNumBetRaises() {
        return numBetRaisesTurn + numBetRaisesFlop +
                numBetRaisesRiver + numBetRaisesPreFlop;
    }

    public double getNumBetRaiseToTotalActionRatio() {
        return (double)getTotalNumBetRaises() / (double)getTotalActions();
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {return name;}

    public void setWinner() {
        winner = true;
    }
    public void setLoser() {
        winner = false;
    }

    public boolean isWinner() {
        return winner;
    }

    public void setTotalActions(int totalActions) {
        this.totalActions = totalActions;
    }

    public int getTotalActions() {
        return totalActions;
    }

    public void setTotalNumActionsFlop(int totalNumActionsFlop) {
        this.totalNumActionsFlop = totalNumActionsFlop;
    }

    public void setTotalNumActionsPreFlop(int totalNumActionsPreFlop) {
        this.totalNumActionsPreFlop = totalNumActionsPreFlop;
    }

    public void setTotalNumActionsTurn(int totalNumActionsTurn) {
        this.totalNumActionsTurn = totalNumActionsTurn;
    }

    public void setTotalNumActionsRiver(int totalNumActionsRiver) {
        this.totalNumActionsRiver = totalNumActionsRiver;
    }
}
