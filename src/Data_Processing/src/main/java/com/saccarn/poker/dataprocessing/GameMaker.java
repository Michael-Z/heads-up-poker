package com.saccarn.poker.dataprocessing;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Used to read a game and player actions from files.
 * Created by Neil on 15/12/2016.
 */
public class GameMaker {

    private File pdb;
    private File hdb = new File("C:\\Data\\hdb");
    private File hroster;

    private Scanner inHroster;
    private List<GamePlayerRecord> gprList = new ArrayList<>();

    public GameMaker() throws FileNotFoundException {
        hroster = new File("C:\\Data\\hroster");
        inHroster = new Scanner(hroster);
        pdb = new File(hroster.getParentFile(), "pdb");
    }

    public GameMaker(File hroster0) throws FileNotFoundException {
        hroster = hroster0;
        inHroster = new Scanner(hroster);
        pdb = new File(hroster.getParentFile(), "pdb");
    }

    public List<GamePlayerRecord> readAllGames() throws FileNotFoundException {
        while(inHroster.hasNextLine()) {
            String gameId = inHroster.next();
            int numPlayers = Integer.parseInt(inHroster.next());
            if (numPlayers == 2) {
                GamePlayerRecord gpr = readGame(gameId, numPlayers);
                gpr.doPreComputations();
                gprList.add(gpr);
            }
            inHroster.nextLine();
        }
        return gprList;
    }

    public GamePlayerRecord readGame(String gameId, int numPlayers) throws FileNotFoundException {
        String [] players = new String[numPlayers];
        for(int i = 0; i < numPlayers; i++) {
            players[i] = inHroster.next();
        }
        return getPlayerActions(players[0], players[1], gameId);
    }

    public GamePlayerRecord getPlayerActions(String name1, String name2, String gameId) throws FileNotFoundException {
        PlayerActions pa = new PlayerActions(pdb);
        return pa.getAction(name1, name2, gameId);
    }


    /**
     * Main method Used to test and verify manually the output is correct.
     * @param args
     * @throws FileNotFoundException
     */
    public  static void main(String [] args) throws FileNotFoundException {
        GameMaker gm = new GameMaker(new File("C:\\Data\\test\\nolimit\\199701\\hroster"));
        gm.readAllGames();
    }
}
