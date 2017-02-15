import java.io.File;
import java.io.FileNotFoundException;

/**
 * Created by Neil on 06/02/2017.
 */
public class GameAnalyser {

    private File dir;

    public GameAnalyser(File dir0) {
        dir = dir0;
    }

    public GameAnalyser() {
        dir = new File("C:\\Data\\test\\nolimit\\");
    }

    public void analyse() throws FileNotFoundException {
        File [] fs = dir.listFiles();
        for(int i = 0; i < fs.length; i++) {
            GameMaker gm = new GameMaker(new File(fs[i], "hroster"));
            gm.readAllGames();
        }

    }

    public static void main(String [] args) throws FileNotFoundException {
        GameAnalyser ga = new GameAnalyser();
        ga.analyse();
    }
}
