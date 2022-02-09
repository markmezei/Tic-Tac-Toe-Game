/**
 * @author markmezei
 * @GitHub: https://github.com/markmezei
 * @created: 2022.01.22.
 **/

package Game;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.concurrent.TimeUnit;

public class TicTacToe{
    private static final String[] WELCOME_MESSAGE = {"W","E","L","C","O","M", "E ","T", "O ","T","H","E ","G","A","M","E"};
    private static final String EXIT_MESSAGE = "BYE! I HOPE YOU ENJOYED THE GAME";
    static ArrayList<Integer> PlayerPositions = new ArrayList<>();
    static ArrayList<Integer> AIPositions = new ArrayList<>();
    static ArrayList<Integer> Player1Positions = new ArrayList<>();
    static ArrayList<Integer> Player2Positions = new ArrayList<>();
    static String Player1Name;
    static String Player2Name;
    static int PlayerPosition;
    static int AIPosition;
    static int Player1Position;
    static int Player2Position;
    static int start;
    static String result;
    static Scanner scan = new Scanner(System.in);
    static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
    static String line = "--------------------";
    static String[][] gameboard = {{"    ", "|","    ","|","    "},
            {"----", "+","----","+","----"},
            {"    ", "|","    ","|","    "},
            {"----", "+","----","+","----"},
            {"    ", "|","    ","|","    "}};

    public static void Gameplay() throws IOException, InterruptedException {
        System.out.print("\nCHOOSE A GAME MODE\n");
        TimeUnit.MICROSECONDS.sleep(30000);
        System.out.print("\n1. PLAYER VS. AI");
        TimeUnit.MICROSECONDS.sleep(30000);
        System.out.print("\n2. PLAYER VS. PLAYER");
        System.out.println();
        int gamemode = Integer.parseInt(input.readLine());
        if(gamemode == 1) {
            System.out.print("\nStarting PLAYER VS. AI...\n\n");
            TimeUnit.SECONDS.sleep(2);
        }
        else if(gamemode == 2) {
            System.out.print("\nStarting PLAYER VS. PLAYER...\n\n");
            TimeUnit.SECONDS.sleep(2);
        }
        else {
            System.exit(-1);
        }

        switch(gamemode)
        {
            case 1:
                System.out.println(line);
                PlayerVsAI();
                System.out.print(line);
                System.out.print("\n\n1.PLAYER");
                System.out.print("\n2.AI");
                System.out.print("\n\nWho will start the game? You or the AI?: ");
                start = Integer.parseInt(input.readLine());
                System.out.println();
                printBoard(gameboard);
                while(true)
                {
                    if(start == 1){
                        user();
                        result = WinningSituations();
                        if(result.length() > 0) {
                            System.out.println();
                            printBoard(gameboard);
                            System.out.println(result);
                            emptyboard(gameboard);
                            break;
                        }
                        AI();
                        result = WinningSituations();
                        if(result.length() > 0) {
                            System.out.println();
                            printBoard(gameboard);
                            System.out.println(result);
                            emptyboard(gameboard);
                            break;
                        }

                    }
                    else if(start == 2){
                        AI();
                        result = WinningSituations();
                        if(result.length() > 0) {
                            System.out.println();
                            printBoard(gameboard);
                            System.out.println(result);
                            emptyboard(gameboard);
                            break;
                        }
                        user();
                        result = WinningSituations();
                        if(result.length() > 0) {
                            System.out.println();
                            printBoard(gameboard);
                            System.out.println(result);
                            emptyboard(gameboard);
                            break;
                        }
                    }
                }
                break;
            case 2:
                System.out.println(line);
                PlayerVsPlayer();
                System.out.print(line);
                System.out.print("\n\n1st player's name: ");
                Player1Name = input.readLine();
                System.out.print("\n2nd player's name: ");
                Player2Name = input.readLine();
                System.out.print("\n1." + Player1Name);
                System.out.print("\n2." + Player2Name);
                System.out.print("\n\nWho will start the game? " + Player1Name + " or " + Player2Name + "?: ");
                start = Integer.parseInt(input.readLine());
                System.out.println();
                printBoard(gameboard);
                while(true)
                {
                   if(start == 1){
                       player1();
                       result = WinningSituations();
                       if(result.length() > 0) {
                           System.out.println();
                           printBoard(gameboard);
                           System.out.println(result);
                           emptyboard(gameboard);
                           break;
                       }
                       player2();
                       result = WinningSituations();
                       if(result.length() > 0) {
                           System.out.println();
                           printBoard(gameboard);
                           System.out.println(result);
                           emptyboard(gameboard);
                           break;
                       }
                   }
                   else if(start == 2){
                       player2();
                       result = WinningSituations();
                       if(result.length() > 0) {
                           System.out.println();
                           printBoard(gameboard);
                           System.out.println(result);
                           emptyboard(gameboard);
                           break;
                       }
                       player1();
                       result = WinningSituations();
                       if(result.length() > 0) {
                           System.out.println();
                           printBoard(gameboard);
                           System.out.println(result);
                           emptyboard(gameboard);
                           break;
                       }
                   }
                }
                break;
        }
    }

    public static void player1() throws IOException {
        System.out.print("\n" + Player1Name + "'s turn...: ");
        Player1Position = Integer.parseInt(input.readLine());
        System.out.println();

        while(Player1Position > 9 || Player1Position < 0) {
            System.out.print("\nWRONG NUMBER!\n");
            System.out.print("\n" + Player1Name + "'s turn...: ");
            Player1Position = Integer.parseInt(input.readLine());
            System.out.println();
        }
        while(Player1Positions.contains(Player1Position) || Player2Positions.contains(Player1Position)) {
            System.out.print("\nPosition is taken, enter a correct position!: ");
            Player1Position = Integer.parseInt(input.readLine());
            while(Player1Position > 9 || Player1Position < 0) {
                System.out.print("\nWRONG NUMBER!\n");
                System.out.print("\n" + Player1Name + "'s turn...: ");
                Player1Position = Integer.parseInt(input.readLine());
                System.out.println();
            }
        }
        Positions(gameboard, Player1Position, "PLAYER 1");
        System.out.println();
        printBoard(gameboard);
    }

    public static void player2() throws IOException {
        System.out.print("\n" + Player2Name + "'s turn...: ");
        Player2Position = Integer.parseInt(input.readLine());
        System.out.println();

        while(Player2Position > 9 || Player2Position < 0) {
            System.out.print("\nWRONG NUMBER!\n");
            System.out.print("\n" + Player2Name + "'s turn...: ");
            Player2Position = Integer.parseInt(input.readLine());
            System.out.println();
        }
        while(Player1Positions.contains(Player2Position) || Player2Positions.contains(Player2Position)) {
            System.out.print("\nPosition is taken, enter a correct position!: ");
            Player2Position = Integer.parseInt(input.readLine());
            while(Player2Position > 9 || Player2Position < 0) {
                System.out.print("\nWRONG NUMBER!\n");
                System.out.print("\n" + Player2Name + "'s turn...: ");
                Player2Position = Integer.parseInt(input.readLine());
                System.out.println();
            }
        }
        Positions(gameboard, Player2Position, "PLAYER 2");
        System.out.println();
        printBoard(gameboard);
    }

    public static void user() throws IOException {
        System.out.print("\nYour turn...: ");
        PlayerPosition = Integer.parseInt(input.readLine());
        System.out.println();

        while(PlayerPosition > 9 || PlayerPosition < 0) {
            System.out.print("\nWRONG NUMBER!\n");
            System.out.print("\nYour turn...: ");
            PlayerPosition = Integer.parseInt(input.readLine());
            System.out.println();
        }
        while(PlayerPositions.contains(PlayerPosition) || AIPositions.contains(PlayerPosition)) {
            System.out.print("\nPosition is taken, enter a correct position!: ");
            PlayerPosition = Integer.parseInt(input.readLine());
            while(PlayerPosition > 9 || PlayerPosition < 0) {
                System.out.print("\nWRONG NUMBER!\n");
                System.out.print("\nYour turn...: ");
                PlayerPosition = Integer.parseInt(input.readLine());
                System.out.println();
            }
        }
        Positions(gameboard, PlayerPosition, "PLAYER");
        System.out.println();
        printBoard(gameboard);
    }



    public static void AI() throws InterruptedException {
        Random random = new Random();
        AIPosition = random.nextInt(9) + 1;
        while(PlayerPositions.contains(AIPosition) || AIPositions.contains(AIPosition)) {
            AIPosition = random.nextInt(9) + 1;
        }
        System.out.print("\nAI's turn...\n");
        TimeUnit.MILLISECONDS.sleep(2000);
        Positions(gameboard, AIPosition, "AI");
        System.out.println();
        printBoard(gameboard);
    }

    public static void Title() throws InterruptedException {
        System.out.println();
        for(int k = 0; k< 60;k++){
            TimeUnit.MICROSECONDS.sleep(15000);
            System.out.print("-");
        }
        System.out.println();

        System.out.print("\t\t\t\t");
        for(String print : WELCOME_MESSAGE)
        {
            TimeUnit.MICROSECONDS.sleep(30000);
            System.out.print("" + print);
        }

        System.out.println();
        for(int i = 0; i< 60;i++){
            TimeUnit.MICROSECONDS.sleep(15000);
            System.out.print("-");
        }
        System.out.println("\n");

    }


    public static void printBoard(String[][] gameboard) {
        for(String[] row : gameboard){
            for (String board : row){
                System.out.print(board);
            }
            System.out.println();
        }
    }


    public static void Positions(String[][] gameboard, int position, String user) {
        String XO = " ";
        if(user.equals("PLAYER")) {
            XO=" X  ";
            PlayerPositions.add(position);
        }
        else if(user.equals("AI")) {
            XO=" O  ";
            AIPositions.add(position);
        }
        else if(user.equals("PLAYER 1")) {
            XO=" X  ";
            Player1Positions.add(position);
        }
        else if(user.equals("PLAYER 2")) {
            XO=" O  ";
            Player2Positions.add(position);
        }

        switch (position) {
            case 1 -> gameboard[0][0] = XO;
            case 2 -> gameboard[0][2] = XO;
            case 3 -> gameboard[0][4] = XO;
            case 4 -> gameboard[2][0] = XO;
            case 5 -> gameboard[2][2] = XO;
            case 6 -> gameboard[2][4] = XO;
            case 7 -> gameboard[4][0] = XO;
            case 8 -> gameboard[4][2] = XO;
            case 9 -> gameboard[4][4] = XO;
        }
    }


    public static void PlayerVsAI() throws InterruptedException {
        String[] alphabets = {"P","L","A","Y","E","R ", "V","S ", "A","I"};
        for(String i:alphabets)
        {
            TimeUnit.MICROSECONDS.sleep(50000);
            System.out.print("" + i);
        }
        System.out.print("\n");
    }

    public static void PlayerVsPlayer() throws InterruptedException {
        String[] alphabets = {"P","L","A","Y","E","R ", "V","S ", "P","L","A","Y","E","R"};
        for(String i:alphabets)
        {
            TimeUnit.MICROSECONDS.sleep(50000);
            System.out.print("" + i);
        }
        System.out.print("\n");
    }


    public static String WinningSituations(){

        List<Integer> row1 = Arrays.asList(1,2,3);
        List<Integer> row2 = Arrays.asList(4,5,6);
        List<Integer> row3 = Arrays.asList(7,8,9);
        List<Integer> column1 = Arrays.asList(1,4,7);
        List<Integer> column2 = Arrays.asList(2,5,8);
        List<Integer> column3 = Arrays.asList(3,6,9);
        List<Integer> cross1 = Arrays.asList(1,5,9);
        List<Integer> cross2 = Arrays.asList(3,5,7);

        List<List> win = new ArrayList<>();
        win.add(row1);
        win.add(row2);
        win.add(row3);
        win.add(column1);
        win.add(column2);
        win.add(column3);
        win.add(cross1);
        win.add(cross2);

        String end = "";
        for(List winner : win)
        {
            if(PlayerPositions.containsAll(winner)) {
                end = "\nCONGRATULATIONS! YOU WON!\n";
            }
            else if(AIPositions.containsAll(winner)) {
                end = "\nAI WON! :(\n";
            }
            else if((PlayerPositions.size() + AIPositions.size() == 9) && !PlayerPositions.containsAll(winner) && !AIPositions.containsAll(winner)){
                end = "\nDRAW\n";
            }
            else if((PlayerPositions.size() + AIPositions.size() == 9) && PlayerPositions.containsAll(winner)){
                end = "\nCONGRATULATIONS! YOU WON!\n";
            }
            else if((PlayerPositions.size() + AIPositions.size() == 9) && AIPositions.containsAll(winner)){
                end = "\nAI WON! :(\n";
            }
            else if(Player1Positions.containsAll(winner)) {
                end = "\nCONGRATULATIONS! " + Player1Name + " WON!\n";
            }
            else if(Player2Positions.containsAll(winner)) {
                end = "\nCONGRATULATIONS! " + Player2Name + " WON!\n";
            }
            else if((Player1Positions.size() + Player2Positions.size() == 9) && !Player1Positions.containsAll(winner) && !Player2Positions.containsAll(winner)){
                end = "\nDRAW\n";
            }
            else if((Player1Positions.size() + Player2Positions.size() == 9) && Player1Positions.containsAll(winner)){
                end = "\nCONGRATULATIONS! " + Player1Name + " WON!\n";
            }
            else if((Player1Positions.size() + Player2Positions.size() == 9) && Player2Positions.containsAll(winner)){
                end = "\nCONGRATULATIONS! " + Player2Name + " WON!\n";
            }

        }
        return end;
    }


    public static void exit() throws IOException, InterruptedException {
        System.out.print("\nDo you want to play again?(y/n): ");
        char again = scan.next().charAt(0);
        while(again=='y') {
            Gameplay();
            System.out.print("\nDo you want to play again?(y/n): ");
            again = scan.next().charAt(0);
        }
        if(again=='n') {
            System.out.println();
            System.out.println(EXIT_MESSAGE);
        }
    }

    public static void emptyboard(String[][] gameboard){
        PlayerPositions.clear();
        AIPositions.clear();
        Player1Positions.clear();
        Player2Positions.clear();
        gameboard[0][0] = "    ";
        gameboard[0][2] = "    ";
        gameboard[0][4] = "    ";
        gameboard[2][0] = "    ";
        gameboard[2][2] = "    ";
        gameboard[2][4] = "    ";
        gameboard[4][0] = "    ";
        gameboard[4][2] = "    ";
        gameboard[4][4] = "    ";
    }
}

