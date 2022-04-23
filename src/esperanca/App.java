package esperanca;

import java.util.Scanner;

public class App {
    private static final Scanner scanner = new Scanner(System.in);
    private static final int BOX_SIZE = 3;
    private static final int ROUND_LIMIT = 8;
    private static Integer row;
    private static Integer column;

    public static void main(String[] args) {
        char[][] board = new char[][]{
                {' ', ' ', ' '},
                {' ', ' ', ' '},
                {' ', ' ', ' '}
        };
        int currentRound = 0;
        boolean winner = false;

        while (!winner) {
            System.out.println("Turno [" + currentRound + "] do jogador " + playerXorO(currentRound));
            printBoard(board);
            inputPlayer();

            while (invalidMove(row, column)) {
                System.out.println("Digite um valor maior que -1 e menor que 3");
                inputPlayer();
            }
            playerMove(board, playerXorO(currentRound), row, column);
            printBoard(board);

            if (checkWinner(board, playerXorO(currentRound)) || endGame(currentRound)) {
                winner = true;
                String message = checkWinner(board, playerXorO(currentRound)) ?
                        "Jogador " + playerXorO(currentRound) + " Venceu!!!" :
                        "DEU VELHA!!!";

                System.out.println(message);
            }
            currentRound++;
        }
    }

    private static boolean endGame(int currentRound) {
        return currentRound == ROUND_LIMIT;
    }

    private static void inputPlayer() {
        System.out.print("Linha: ");
        row = scanner.nextInt();

        System.out.print("Coluna: ");
        column = scanner.nextInt();
    }
    private static Character playerXorO(int currentRound) {
        return (currentRound % 2 == 0) ? 'X' : 'O';
    }

    private static void printBoard(char[][] board) {
        for (int row = 0; row < BOX_SIZE; row++) {
            if (row > 0) {
                System.out.println("-+-+-");
            }
            for (int column = 0; column < BOX_SIZE; column++) {
                if (column > 0) {
                    System.out.print("|");
                }
                System.out.print(board[row][column]);
            }
            System.out.println();
        }
    }

    private static boolean isSpaceAvailable(char[][] board, Integer row, Integer column) {
        return board[row][column] == ' ';
    }

    private static boolean invalidMove(Integer row, Integer column) {
        return ((row < 0 || row > BOX_SIZE) && (column < 0 && column > BOX_SIZE));
    }

    private static void playerMove(char[][] board, char value, Integer row, Integer column) {
        board[row][column] = isSpaceAvailable(board, row, column) ? value : board[row][column];
    }

    private static boolean checkByRow(char[][] board, char value) {
        for (int row = 0; row < BOX_SIZE; row++) {
            return ((board[row][0] == value) && (board[row][1] == value) && (board[row][2] == value));
        }
        return false;
    }

    private static boolean checkByColumn(char[][] board, char value) {
        for (int column = 0; column < BOX_SIZE; column++) {
            return ((board[0][column] == value) && (board[1][column] == value) && (board[2][column] == value));
        }
        return false;
    }

    private static boolean checkByMainDiagonal(char[][] board, char value) {
        return ((board[0][0] == value) && (board[1][1] == value) && (board[2][2] == value));
    }

    private static boolean checkBySecondaryDiagonal(char[][] board, char value) {
        return ((board[0][2] == value) && (board[1][1] == value) && (board[2][0] == value));
    }

    private static boolean checkWinner(char[][] board, char value) {
        return checkByMainDiagonal(board, value) ||
                checkBySecondaryDiagonal(board, value) ||
                checkByColumn(board, value) ||
                checkByRow(board, value);
    }
}
