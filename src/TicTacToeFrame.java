import java.awt.*;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import java.awt.event.ActionEvent;

public class TicTacToeFrame extends JFrame
{
    Border empty;
    JPanel mainPnl;
    JPanel titlePnl;
    JLabel titleLbl;
    ImageIcon img;
    JPanel msgPnl;
    JLabel msg;
    JPanel gamePnl;
    JPanel countPnl;
    JLabel oWins;
    JLabel xWins;
    JLabel tieCountLbl;
    JTextField xWinCountField;
    JTextField oWinCountField;
    JTextField tieCountField;
    int xWinCount = 0;
    int oWinCount = 0;
    int tieCount = 0;
    int numberOfTurns = 0;
    JPanel controlPnl;
    JButton quitBtn;
    JButton clearBtn;
    private static final int ROW = 3;
    private static final int COL = 3;
    public static String[][] board = new String[ROW][COL];
    private static final JButton[][] GUIboard = new JButton[ROW][COL];
    private String currentPlayer = "X";
    boolean again = false;
    public TicTacToeFrame()
    {
        mainPnl = new JPanel();
        mainPnl.setLayout(new GridLayout(5,1));
        createTitlePnl();
        mainPnl.add(titlePnl);
        createMsgPnl();
        mainPnl.add(msg);
        createDisplay();
        mainPnl.add(gamePnl);
        createCountPnl();
        mainPnl.add(countPnl);
        createControlPnl();
        mainPnl.add(controlPnl);

        add(mainPnl);
        mainPnl.setBackground(Color.WHITE);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Toolkit kit = Toolkit.getDefaultToolkit();
        Dimension screenSize = kit.getScreenSize();
        int screenHeight = screenSize.height;
        int screenWidth = screenSize.width;
        setSize(3*(screenWidth / 4), 3*(screenHeight / 4));
        setLocationRelativeTo(null);
    }
    private void createTitlePnl()
    {
        titlePnl = new JPanel();
        titlePnl.setBackground(Color.WHITE);
        titleLbl = new JLabel("TicTacToe Game!!");
        titleLbl.setFont(new Font("Times New Roman", Font.BOLD, 40));
        titleLbl.setVerticalTextPosition(JLabel.BOTTOM);
        titleLbl.setHorizontalTextPosition(JLabel.CENTER);

        titlePnl.add(titleLbl);
    }

    private void createDisplay()
    {
        gamePnl = new JPanel();
        gamePnl.setLayout(new GridLayout(3,3));
        gamePnl.setBackground(Color.WHITE);
        CreateGame();
    }
    private void createMsgPnl()
    {
        msgPnl = new JPanel();
        msgPnl.setBackground(Color.WHITE);
        msg = new JLabel("Player " + currentPlayer + "make your move. ", null, JLabel.CENTER);
        msg.setFont(new Font("Times New Roman", Font.PLAIN, 30));
        msg.setHorizontalTextPosition(JLabel.CENTER);
        msg.setVerticalTextPosition(JLabel.BOTTOM);
        msgPnl.add(msg);
    }
    private void createCountPnl()
    {
        countPnl = new JPanel();
        countPnl.setBackground(Color.WHITE);
        countPnl.setLayout(new GridLayout(0,1));

        xWins = new JLabel("Player X Win Count");
        xWins.setVerticalTextPosition(JLabel.BOTTOM);
        xWins.setHorizontalTextPosition(JLabel.CENTER);

        oWins = new JLabel("Player O Win Count");
        oWins.setVerticalTextPosition(JLabel.BOTTOM);
        oWins.setHorizontalTextPosition(JLabel.CENTER);

        tieCountLbl = new JLabel("Tie Count");
        tieCountLbl.setVerticalTextPosition(JLabel.BOTTOM);
        tieCountLbl.setHorizontalTextPosition(JLabel.CENTER);

        xWinCountField = new JTextField();
        xWinCountField.setText("0");
        xWinCountField.setHorizontalAlignment(JTextField.CENTER);
        empty = BorderFactory.createEmptyBorder();
        xWinCountField.setBorder(empty);
        xWinCountField.setEditable(false);

        oWinCountField = new JTextField();
        oWinCountField.setText("0");
        oWinCountField.setHorizontalAlignment(JTextField.CENTER);
        empty = BorderFactory.createEmptyBorder();
        oWinCountField.setBorder(empty);
        oWinCountField.setEditable(false);

        tieCountField = new JTextField();
        tieCountField.setText("0");
        tieCountField.setHorizontalAlignment(JTextField.CENTER);
        empty = BorderFactory.createEmptyBorder();
        tieCountField.setBorder(empty);
        tieCountField.setEditable(false);

        countPnl.add(xWins);
        countPnl.add(xWinCountField);
        countPnl.add(oWins);
        countPnl.add(oWinCountField);
        countPnl.add(tieCountLbl);
        countPnl.add(tieCountField);

        xWinCountField.setText("0");
        oWinCountField.setText("0");
        tieCountField.setText("0");

    }
    private void createControlPnl() {
        {
            controlPnl = new JPanel();
            controlPnl.setLayout(new GridLayout(1, 2));
            controlPnl.setBackground(Color.WHITE);
            clearBtn = new JButton("Reset");
            clearBtn.setFont(new Font("Times New Roman", Font.PLAIN, 25));
            clearBtn.addActionListener((ActionEvent ae) ->
            {
                int res = JOptionPane.showOptionDialog(null, "Do you want to reset the gameboard and score?",
                        "Message", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE,
                        img, new Object[]{"Yes", "No"}, JOptionPane.YES_OPTION);
                if (res == JOptionPane.YES_OPTION) {
                    clearBoard();
                    for (int row1 = 0; row1 <= 2; row1++) {
                        for (int col1 = 0; col1 <= 2; col1++) {
                            GUIboard[row1][col1].setText(" ");
                        }
                    }
                    oWinCountField.setText("0");
                    xWinCountField.setText("0");
                    tieCountField.setText("0");
                    xWinCount = 0;
                    oWinCount = 0;
                    tieCount = 0;
                    JOptionPane.showMessageDialog(null, "Board and scores reset",
                            "Message", JOptionPane.INFORMATION_MESSAGE, img);
                } else if (res == JOptionPane.NO_OPTION) {
                    JOptionPane.showMessageDialog(null, "Canceled reset request", "Message"
                            , JOptionPane.INFORMATION_MESSAGE, img);
                } else if (res == JOptionPane.CLOSED_OPTION) {
                    JOptionPane.showMessageDialog(null, "Canceled reset request", "Message"
                            , JOptionPane.INFORMATION_MESSAGE, img);
                }
            });
            quitBtn = new JButton("Quit");
            quitBtn.setFont(new Font("Times New Roman", Font.PLAIN, 25));
            quitBtn.addActionListener((ActionEvent ae) ->
            {
                int res = JOptionPane.showOptionDialog(null, "Do you want to quit?",
                        "Message", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE,
                        img, new Object[]{"Yes", "No"}, JOptionPane.YES_OPTION);
                if (res == JOptionPane.YES_OPTION) {
                    System.exit(0);
                } else if (res == JOptionPane.NO_OPTION) {
                    JOptionPane.showMessageDialog(null, "Canceled quit request", "Message"
                            , JOptionPane.INFORMATION_MESSAGE, img);
                } else if (res == JOptionPane.CLOSED_OPTION) {
                    JOptionPane.showMessageDialog(null, "Canceled quit request", "Message"
                            , JOptionPane.INFORMATION_MESSAGE, img);
                }
            });
            controlPnl.add(quitBtn);
            controlPnl.add(clearBtn);
        }
    }
    private void clearBoard()
    {
        currentPlayer = "X";
        for(int row=0; row < ROW; row++)
        {
            for(int col=0; col < COL; col++)
            {
                board[row][col] = " ";
            }
        }
    }
    public static void isValidMove(String currentPlayer, int row, int col)
    {
        board[row][col] = currentPlayer;
    }
    private boolean isWin(String player)
    {
        if(isColWin(player) || isRowWin(player) || isDiagnalWin(player))
        {
            return true;
        }

        return false;
    }
    private boolean isColWin(String player)
    {
        for(int col=0; col < COL; col++)
        {
            if(board[0][col].equals(player) &&
                    board[1][col].equals(player) &&
                    board[2][col].equals(player))
            {
                return true;
            }
        }
        return false; // no col win
    }
    private boolean isRowWin(String player)
    {
        for(int row=0; row < ROW; row++)
        {
            if(board[row][0].equals(player) &&
                    board[row][1].equals(player) &&
                    board[row][2].equals(player))
            {
                return true;
            }
        }
        return false; // no row win
    }
    private boolean isDiagnalWin(String player)
    {
        if(board[0][0].equals(player) &&
                board[1][1].equals(player) &&
                board[2][2].equals(player) )
        {
            return true;
        }
        if(board[0][2].equals(player) &&
                board[1][1].equals(player) &&
                board[2][0].equals(player) )
        {
            return true;
        }
        return false;
    }
    private boolean isTie()
    {
        boolean xFlag = false;
        boolean oFlag = false;
        // Check all 8 win vectors for an X and O so
        // no win is possible
        // Check for row ties
        for(int row=0; row < ROW; row++)
        {
            if(board[row][0].equals("X") ||
                    board[row][1].equals("X") ||
                    board[row][2].equals("X"))
            {
                xFlag = true; // there is an X in this row
            }
            if(board[row][0].equals("O") ||
                    board[row][1].equals("O") ||
                    board[row][2].equals("O"))
            {
                oFlag = true; // there is an O in this row
            }

            if(! (xFlag && oFlag) )
            {
                return false; // No tie can still have a row win
            }

            xFlag = oFlag = false;

        }
        // Now scan the columns
        for(int col=0; col < COL; col++)
        {
            if(board[0][col].equals("X") ||
                    board[1][col].equals("X") ||
                    board[2][col].equals("X"))
            {
                xFlag = true; // there is an X in this col
            }
            if(board[0][col].equals("O") ||
                    board[1][col].equals("O") ||
                    board[2][col].equals("O"))
            {
                oFlag = true; // there is an O in this col
            }

            if(! (xFlag && oFlag) )
            {
                return false; // No tie can still have a col win
            }
        }
        // Now check for the diagonals
        xFlag = oFlag = false;

        if(board[0][0].equals("X") ||
                board[1][1].equals("X") ||
                board[2][2].equals("X") )
        {
            xFlag = true;
        }
        if(board[0][0].equals("O") ||
                board[1][1].equals("O") ||
                board[2][2].equals("O") )
        {
            oFlag = true;
        }
        if(! (xFlag && oFlag) )
        {
            return false; // No tie can still have a diag win
        }
        xFlag = oFlag = false;

        if(board[0][2].equals("X") ||
                board[1][1].equals("X") ||
                board[2][0].equals("X") )
        {
            xFlag =  true;
        }
        if(board[0][2].equals("O") ||
                board[1][1].equals("O") ||
                board[2][0].equals("O") )
        {
            oFlag =  true;
        }
        if(! (xFlag && oFlag) )
        {
            return false; // No tie can still have a diag win
        }

        // Checked every vector so I know I have a tie
        return true;
    }
    private void CreateGame()
    {
        do
        {
            clearBoard();
            numberOfTurns = 0;
            for (int row = 0; row <=2; row++)
            {
                for (int col = 0; col <= 2; col++)
                {
                    GUIboard[row][col] = new TicTacToeTile(row, col);
                    GUIboard[row][col].setBorder(new LineBorder(Color.RED));
                    GUIboard[row][col].setForeground(Color.BLACK);
                    GUIboard[row][col].setFont(new Font("Times New Roman", Font.ITALIC, 25));
                    gamePnl.add(GUIboard[row][col]);
                    GUIboard[row][col].setText(" ");
                    GUIboard[row][col].addActionListener(e ->
                    {
                       TicTacToeTile clicked = (TicTacToeTile) e.getSource();
                       JFrame frame = new JFrame("JOptionPane");
                       numberOfTurns++;
                       if (!clicked.getText().isBlank())
                       {
                           JOptionPane.showMessageDialog(frame, "Already taken, Enter a valid move.", "[Error] Invalid Input",
                                   JOptionPane.ERROR_MESSAGE);
                           return;
                       }
                       clicked.setText(String.valueOf(currentPlayer));
                       isValidMove(currentPlayer, clicked.getRow(), clicked.getCol());

                       System.out.println(numberOfTurns);
                       if(numberOfTurns >= 5)
                       {
                       if (isWin(currentPlayer)) {
                           int res = JOptionPane.showOptionDialog(frame, "Player " + currentPlayer + " Wins!\nWant to play again?", "Results", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, img, new Object[]{"Yes", "No"}, JOptionPane.YES_OPTION);
                           if (res == JOptionPane.YES_OPTION) {
                               again = true;
                           } else if (res == JOptionPane.NO_OPTION) {
                               clearBoard();
                               numberOfTurns = 0;
                               for (int row1 = 0; row1 <= 2; row1++) {
                                   for (int col1 = 0; col1 <= 2; col1++) {
                                       GUIboard[row1][col1].setText(" ");
                                   }
                               }
                               System.exit(0);
                           } else if (res == JOptionPane.CLOSED_OPTION) {
                               clearBoard();
                               numberOfTurns = 0;
                               for (int row1 = 0; row1 <= 2; row1++) {
                                   for (int col1 = 0; col1 <= 2; col1++) {
                                       GUIboard[row1][col1].setText(" ");
                                   }
                               }
                               System.exit(0);
                           }
                           if (currentPlayer.equals("X")) {
                               xWinCount = xWinCount + 1;
                               xWinCountField.setText(String.valueOf(xWinCount));
                           }
                           if (currentPlayer.equals("O")) {
                               oWinCount = oWinCount + 1;
                               oWinCountField.setText(String.valueOf(oWinCount));
                           }
                           clearBoard();
                           numberOfTurns = 0;
                           for (int row1 = 0; row1 <= 2; row1++) {
                               for (int col1 = 0; col1 <= 2; col1++) {
                                   GUIboard[row1][col1].setText(" ");
                               }
                           }
                           currentPlayer = "O";
                           msg.setText("Player " + currentPlayer + ", make your move");
                       }
                       }
                       if (numberOfTurns > 7)
                       {
                           if (isWin(currentPlayer)) {
                               int res = JOptionPane.showOptionDialog(frame, "Player " + currentPlayer + " Wins!\nWant to play again?", "Results", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, img, new Object[]{"Yes", "No"}, JOptionPane.YES_OPTION);
                               if (res == JOptionPane.YES_OPTION) {
                                   again = true;
                               } else if (res == JOptionPane.NO_OPTION) {
                                   clearBoard();
                                   numberOfTurns = 0;
                                   for (int row1 = 0; row1 <= 2; row1++) {
                                       for (int col1 = 0; col1 <= 2; col1++) {
                                           GUIboard[row1][col1].setText(" ");
                                       }
                                   }
                                   System.exit(0);
                               } else if (res == JOptionPane.CLOSED_OPTION) {
                                   clearBoard();
                                   numberOfTurns = 0;
                                   for (int row1 = 0; row1 <= 2; row1++) {
                                       for (int col1 = 0; col1 <= 2; col1++) {
                                           GUIboard[row1][col1].setText(" ");
                                       }
                                   }
                                   System.exit(0);
                               }
                               if (currentPlayer.equals("X")) {
                                   xWinCount = xWinCount + 1;
                                   xWinCountField.setText(String.valueOf(xWinCount));
                               }
                               if (currentPlayer.equals("O")) {
                                   oWinCount = oWinCount + 1;
                                   oWinCountField.setText(String.valueOf(oWinCount));
                               }
                               clearBoard();
                               numberOfTurns = 0;
                               for (int row1 = 0; row1 <= 2; row1++) {
                                   for (int col1 = 0; col1 <= 2; col1++) {
                                       GUIboard[row1][col1].setText(" ");
                                   }
                               }
                               currentPlayer = "O";
                               msg.setText("Player " + currentPlayer + ", make your move");
                           }
                       }
                        if (isTie()) {
                            int res = JOptionPane.showOptionDialog(frame, "Tie Game!\nDo you want to play again?", "Results", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, img, new Object[]{"Yes", "No"}, JOptionPane.YES_OPTION);
                            if (res == JOptionPane.YES_OPTION) {
                                again = true;
                            }
                            else if(res == JOptionPane.NO_OPTION){
                                clearBoard();
                                numberOfTurns = 0;
                                for (int row1 = 0; row1 <= 2; row1++) {
                                    for (int col1 = 0; col1 <= 2; col1++) {
                                        GUIboard[row1][col1].setText(" ");
                                    }
                                }
                                System.exit(0);
                            }
                            else if(res == JOptionPane.CLOSED_OPTION){
                                clearBoard();
                                numberOfTurns = 0;
                                for (int row1 = 0; row1 <= 2; row1++) {
                                    for (int col1 = 0; col1 <= 2; col1++) {
                                        GUIboard[row1][col1].setText(" ");
                                    }
                                }
                                System.exit(0);
                            }
                            tieCount = tieCount + 1;
                            tieCountField.setText(String.valueOf(tieCount));
                            clearBoard();
                            numberOfTurns = 0;
                            for (int row1 = 0; row1 <= 2; row1++) {
                                for (int col1 = 0; col1 <= 2; col1++) {
                                    GUIboard[row1][col1].setText(" ");
                                }
                            }
                            currentPlayer = "O";
                            msg.setText("Player " + currentPlayer + ", make your move.");
                        }
                        if (currentPlayer.equals("X")) {
                            currentPlayer = "O";
                        } else {
                            currentPlayer = "X";
                        }
                        msg.setText("Player " + currentPlayer + ", make your move.");
                    });
                }
            }
        }while(again);
    }
}