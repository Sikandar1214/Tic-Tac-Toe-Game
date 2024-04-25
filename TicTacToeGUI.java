import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class TicTacToeGUI {
    private char[][] board;
    private char currentPlayer;
    private JFrame frame;
    private JButton[][] buttons;
    private JButton replayButton;

    public TicTacToeGUI() {
        board = new char[3][3];
        currentPlayer = 'X';

        // Create the main frame
        frame = new JFrame("Tic Tac Toe");
        frame.setSize(300, 350);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());
        frame.getContentPane().setBackground(new Color(0xE6E6E6)); // Set background color of the frame

        // Initialize the game board
        initializeBoard();

        // Create buttons for the game board
        JPanel gamePanel = new JPanel();
        gamePanel.setLayout(new GridLayout(3, 3));
        gamePanel.setBackground(new Color(0xE6E6E6)); // Set background color of the game panel
        buttons = new JButton[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                final int row = i;
                final int col = j;
                buttons[i][j] = new JButton();
                buttons[i][j].setFont(new Font(Font.SANS_SERIF, Font.BOLD, 50));
                buttons[i][j].setBackground(new Color(0xFFFFFF)); // Set background color of the buttons
                buttons[i][j].addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        // Check if the button is empty and the game is not over
                        if (board[row][col] == '-' && !checkWinner() && !isBoardFull()) {
                            // Set the button text to the current player's symbol
                            buttons[row][col].setText(String.valueOf(currentPlayer));
                            // Update the game board
                            board[row][col] = currentPlayer;
                            // Check for a winner
                            if (checkWinner()) {
                                JOptionPane.showMessageDialog(frame, "Player " + currentPlayer + " wins!");
                                replayButton.setEnabled(true);
                            } else if (isBoardFull()) {
                                JOptionPane.showMessageDialog(frame, "It's a draw!");
                                replayButton.setEnabled(true);
                            } else {
                                // Switch player
                                currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';
                            }
                        }
                    }
                });
                gamePanel.add(buttons[i][j]);
            }
        }

        // Create replay button
        replayButton = new JButton("Replay");
        replayButton.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 20));
        replayButton.setBackground(new Color(0x4CAF50)); // Set background color of the replay button
        replayButton.setForeground(Color.WHITE); // Set text color of the replay button
        replayButton.setEnabled(false);
        replayButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Reset the game
                initializeBoard();
                currentPlayer = 'X';
                // Clear button texts
                for (int i = 0; i < 3; i++) {
                    for (int j = 0; j < 3; j++) {
                        buttons[i][j].setText("");
                    }
                }
                // Disable replay button
                replayButton.setEnabled(false);
            }
        });

        // Add game panel and replay button to the frame
        frame.add(gamePanel, BorderLayout.CENTER);
        frame.add(replayButton, BorderLayout.SOUTH);

        // Add label indicating game developer
        JLabel developerLabel = new JLabel("Developed by Sikandar Khan");
        developerLabel.setFont(new Font(Font.SANS_SERIF, Font.ITALIC, 29));
        developerLabel.setHorizontalAlignment(SwingConstants.CENTER);
        frame.add(developerLabel, BorderLayout.NORTH);

        // Display the frame
        frame.setVisible(true);
    }

    // Initialize the game board with empty cells
    private void initializeBoard() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                board[i][j] = '-';
            }
        }
    }

    // Check if there is a winner
    private boolean checkWinner() {
        // Check rows, columns, and diagonals
        for (int i = 0; i < 3; i++) {
            if (board[i][0] != '-' && board[i][0] == board[i][1] && board[i][1] == board[i][2]) {
                return true;
            }
            if (board[0][i] != '-' && board[0][i] == board[1][i] && board[1][i] == board[2][i]) {
                return true;
            }
        }
        if (board[0][0] != '-' && board[0][0] == board[1][1] && board[1][1] == board[2][2]) {
            return true;
        }
        if (board[0][2] != '-' && board[0][2] == board[1][1] && board[1][1] == board[2][0]) {
            return true;
        }
        return false;
    }

    // Check if the board is full (draw)
    private boolean isBoardFull() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == '-') {
                    return false;
                }
            }
        }
        return true;
    }

    public static void main(String[] args) {
        // Create and start the game
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new TicTacToeGUI();
            }
        });
    }
}
