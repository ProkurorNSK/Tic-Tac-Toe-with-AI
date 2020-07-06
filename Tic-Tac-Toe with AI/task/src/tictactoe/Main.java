package tictactoe;

import java.util.Random;
import java.util.Scanner;

public class Main {
    private static final int SIZE = 3;
    private static final char X = 'X';
    private static final char O = 'O';
    private static final char[][] field = new char[SIZE][SIZE];
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        Player playerX;
        Player playerO;

        while (true) {
            System.out.print("Input command: ");
            String[] commands = scanner.nextLine().split(" ");
            int countX = 0;
            int countO = 0;


            if (commands.length == 0) {
                System.out.println("Bad parameters!");
                continue;
            }
            switch (commands[0]) {
                case "exit":
                    return;
                case "start":
                    if (commands.length != 3) {
                        System.out.println("Bad parameters!");
                        continue;
                    }
                    switch (commands[1]) {
                        case "user":
                            playerX = new HumanPlayer(X);
                            break;
                        case "easy":
                            playerX = new EasyBot(X);
                            break;
                        case "medium":
                            playerX = new MediumBot(X);
                            break;
                        case "hard":
                            playerX = new HardBot(X);
                            break;
                        default:
                            System.out.println("Bad parameters!");
                            continue;
                    }
                    switch (commands[2]) {
                        case "user":
                            playerO = new HumanPlayer(O);
                            break;
                        case "easy":
                            playerO = new EasyBot(O);
                            break;
                        case "medium":
                            playerO = new MediumBot(O);
                            break;
                        case "hard":
                            playerO = new HardBot(O);
                            break;
                        default:
                            System.out.println("Bad parameters!");
                            continue;
                    }
                    break;
                default:
                    System.out.println("Bad parameters!");
                    continue;
            }

            Player currentPlayer = playerX;
            for (int i = 0; i < SIZE; i++) {
                for (int j = 0; j < SIZE; j++) {
                    field[i][j] = ' ';
                }
            }
            print();

            while (true) {

                currentPlayer.move();

                if (currentPlayer == playerX) {
                    countX++;
                } else {
                    countO++;
                }

                print();

                if (checkWin(currentPlayer.getMark())) {
                    System.out.println(currentPlayer.getMark() + " wins");
                    break;
                } else if (countX + countO == SIZE * SIZE) {
                    System.out.println("Draw");
                    break;
                }

                currentPlayer = (currentPlayer == playerX) ? playerO : playerX;
            }
        }
    }

    private static void print() {
        System.out.println("---------");
        for (int i = 0; i < SIZE; i++) {
            System.out.print("| ");
            for (int j = 0; j < SIZE; j++) {
                System.out.print(Main.field[i][j] + " ");
            }
            System.out.print("|\n");
        }
        System.out.println("---------");
    }

    private static boolean checkWin(char mark) {

        boolean equals;
        for (int i = 0; i < SIZE; i++) {
            equals = true;
            for (int j = 0; j < SIZE; j++) {
                if (Main.field[i][j] != mark) {
                    equals = false;
                    break;
                }
            }
            if (equals) {
                return true;
            }

            equals = true;
            for (int j = 0; j < SIZE; j++) {
                if (Main.field[j][i] != mark) {
                    equals = false;
                    break;
                }
            }
            if (equals) {
                return true;
            }
        }

        equals = true;
        for (int i = 0; i < SIZE; i++) {
            if (Main.field[i][i] != mark) {
                equals = false;
                break;
            }
        }
        if (equals) {
            return true;
        }

        equals = true;
        for (int i = 0; i < SIZE; i++) {
            if (Main.field[i][SIZE - 1 - i] != mark) {
                equals = false;
                break;
            }
        }
        return equals;
    }

    abstract static class Player {
        private char mark;

        protected char getMark() {
            return mark;
        }

        abstract void move();
    }

    static class EasyBot extends Player {

        private final Random random = new Random();

        public EasyBot(char mark) {
            super.mark = mark;
        }

        @Override
        public void move() {
            System.out.println("Making move level \"easy\"");
            while (true) {
                int x = random.nextInt(3) + 1;
                int y = random.nextInt(3) + 1;
                char value = field[SIZE - y][x - 1];
                if (value != X && value != O) {
                    field[SIZE - y][x - 1] = getMark();
                    break;
                }
            }
        }
    }

    static class MediumBot extends Player {

        private final Random random = new Random();

        public MediumBot(char mark) {
            super.mark = mark;
        }

        @Override
        public void move() {
            System.out.println("Making move level \"medium\"");
            while (true) {
                int x = random.nextInt(3) + 1;
                int y = random.nextInt(3) + 1;
                char value = field[SIZE - y][x - 1];
                if (value != X && value != O) {
                    field[SIZE - y][x - 1] = getMark();
                    break;
                }
            }
        }
    }

    static class HardBot extends Player {

        private final Random random = new Random();

        public HardBot(char mark) {
            super.mark = mark;
        }

        @Override
        public void move() {
            System.out.println("Making move level \"hard\"");
            while (true) {
                int x = random.nextInt(3) + 1;
                int y = random.nextInt(3) + 1;
                char value = field[SIZE - y][x - 1];
                if (value != X && value != O) {
                    field[SIZE - y][x - 1] = getMark();
                    break;
                }
            }
        }
    }

    static class HumanPlayer extends Player {

        public HumanPlayer(char mark) {
            super.mark = mark;
        }

        @Override
        public void move() {
            while (true) {
                System.out.print("Enter the coordinates: ");
                try {
                    String[] gameMove = scanner.nextLine().split(" ");
                    String xs = gameMove[0];
                    String ys = gameMove[1];
                    int x = Integer.parseInt(xs);
                    int y = Integer.parseInt(ys);

                    if (x < 1 || x > SIZE || y < 1 || y > SIZE) {
                        System.out.println("Coordinates should be from 1 to 3!");
                        continue;
                    }

                    char value = field[SIZE - y][x - 1];
                    if (value == X || value == O) {
                        System.out.println("This cell is occupied! Choose another one!");
                        continue;
                    }

                    field[SIZE - y][x - 1] = getMark();
                    break;
                } catch (Exception e) {
                    System.out.println("You should enter numbers!");
                }
            }
        }
    }
}

