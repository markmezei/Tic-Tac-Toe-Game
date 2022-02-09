/**
 * @author markmezei
 * @GitHub: https://github.com/markmezei
 * @created: 2022.01.22.
 **/

package Game;
import java.io.IOException;
import static Game.TicTacToe.Gameplay;
import static Game.TicTacToe.Title;
import static Game.TicTacToe.exit;

public class Main {
    public static void main(String[] args) throws InterruptedException, IOException {
        Title();
        Gameplay();
        exit();
        System.exit(0);
    }
}
