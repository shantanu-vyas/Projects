import java.awt.*;
import java.awt.event.*;
import java.io.*;
import javax.swing.*;
import java.util.*;

public class Window extends JFrame
{
    JTextArea text; 
    int size;
    String[][] board;
    int numberOfQueens = 0;
    private Container c = getContentPane();
    public Window(int _size)
    {
        super("N Queens");
        //init vars
        size = _size;
        board = new String[size][size];

        setSize(200,200);
        setDefaultCloseOperation(EXIT_ON_CLOSE);


        c.setLayout(new FlowLayout());
        text = new JTextArea("");
        c.add(text);
        resetBoard();
        //    setPosition(1,0);
        //randomboard();
        text.setText(printBoard());
        //checkDiag(0,0);
        //    System.out.println(isInvalidPosition(3,3));

    }
    public void resetBoard()
    {
        // board = new String[][]{
        //     {"_","_","_","_","_","_","_","_"},
        //     {"_","_","_","_","_","_","_","_"},
        //     {"_","_","_","_","_","_","_","_"},
        //     {"_","_","_","_","_","_","_","_"},
        //     {"_","_","_","_","_","_","_","_"},
        //     {"_","_","_","_","_","_","_","_"},
        //     {"_","_","_","_","_","_","_","_"},
        //     {"_","_","_","_","_","_","_","_"}};

        // c.add(text)
        //;
        //System.out.println(printBoard());
        for (int i = 0; i < size; i++)
        {
            for (int p = 0; p < size; p++)
            {
                board[i][p] = "_";
            }
        }
    }
    public String printBoard()
    {
        String temp = "";
        for (int i = 0; i<size; i++)
        {
            for (int p = 0; p<size; p++)
            {
                temp = (temp + board[i][p] + " ");
            }
            temp = temp + "\n";
        }

        return temp;
    }
    public void setPosition(int x, int y) //makes place a queen, adds 1 to counter
    {
        numberOfQueens++;
        board[y][x] = "Q";
    }
    public void remove(int x, int y) //removes queen, -1 to counter
    {
        numberOfQueens--;
        board[y][x] = "_";
    }
    public String[][] getBoard() //return the stringboard
    {
        return board;
    }
    public boolean isInvalidPosition(int x, int y) //checks to see if its in a conflicting posit
    {
        if (checkVertical(x,y) || checkHorizontal(x,y) || checkDiag(x,y))
        {
            return true;
        }
        return false;
    }
    //to *fix* get rid of isTemp...
    public boolean checkVertical(int x, int y)
    {
        for (int i = 0; i < size; i++)
        {
            if (board[i][x].equals("Q"))
            {
                if(i != y)
                {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean checkHorizontal(int x, int y)
    {
        for (int i = 0; i < size; i++)
        {
            if (board[y][i].equals("Q"))
            {
                if (i != x)
                {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean checkDiag(int x, int y)
    {
        int tempx = x;
        int tempy = y;
        boolean invalidSpot;
        while(true)
        {
            tempx = tempx+1;
            tempy = tempy-1;
            if(tempx > size-1 || tempy < 0)
            {
                break;
            }
            else if (board[tempy][tempx].equals("Q"))
            {
                return true;
            }
        }
        tempx = x;
        tempy = y;
        while(true)
        {
            tempx = tempx-1;
            tempy = tempy-1;
            if (tempx < 0 || tempy < 0)
            {
                break;
            }
            else if (board[tempy][tempx].equals("Q"))
            {
                return true;
            }
        }
        tempx = x;
        tempy = y;
        while(true)
        {
            tempx = tempx+1;
            tempy = tempy+1;
            if (tempx > size-1 || tempy > size-1)
            {
                break;
            }
            else if (board[tempy][tempx].equals("Q"))
            {
                return true;
            }
        }
        tempx = x;
        tempy = y;
        while(true)
        {
            tempx = tempx-1;
            tempy = tempy+1;
            if( tempx < 0 || tempy > size-1)
            {
                break;
            }
            else if (board[tempy][tempx].equals("Q"))
            {
                return true;
            }
        }
        return false;
    }

    public void randomboard() //...
    {
        board = new String[][]{
            {"0a", "1a","2a","3a","4a","5a","6a","7c"},
            {"0b", "1b","2b","3b","4b","5b","6b","7b"},
            {"0c", "1c","2c","3c","4c","5c","6c","7c"},
            {"0d", "1d","2d","3d","4d","5d","6d","7d"},
            {"0e", "1e","2e","3e","4e","5e","6e","7e"},
            {"0f", "1f","2f","3f","4f","5f","6f","7f"},
            {"0g", "1g","2g","3g","4g","5g","6g","7g"},
            {"0h", "1h","2h","3h","4h","5h","6h","7h"}};

        c.add(text);
    }
    public void addToQueen() //add 1 to queens
    {
        numberOfQueens++;
    }
    public void deleteQueenCounter() //subtract 1 
    {
        numberOfQueens--;
    }
    public int getQueenCounter() //return queen
    {
        return numberOfQueens;
    }
    //params are not including
    public Position findSpot() //find next avail spot, return as Position type
    {
        for (int i = 0; i < size; i++)
        {
            for (int p = 0; p < size; p++)
            {
                if (isInvalidPosition(i,p))
                {
                    //System.out.print("X");
                }
                //if (!isInvalidPosition(i,p) && !board[p][i].equals("Q") && !board[p][i].equals("T"))
                if (!isInvalidPosition(i,p) && !board[p][i].equals("Q") && !isTemp(i,p))
                {
                    return new Position(i,p);
                }
            }
        }

        return new Position(-5,-5);
    }
    public boolean isTemp(int x, int y) //checks to see if its temp vari
    {
        if ((board[y][x]).equals("_") || (board[y][x].equals("Q")))
        {
            return false;
        }
        try
        {
            Integer.parseInt(board[y][x]);
        }
        catch(Exception e)
        {
            return false;
        }
        return true;
    }
    public void markTemp(int x, int y, String marker) //marks position with specified temp marker
    {
        numberOfQueens--;
        board[y][x] = marker;
    }
    public void removeMarker(String marker)
    {
        for (int i = 0; i < size; i++)
        {
            for (int p = 0; p < size; p++)
            {
                if (board[i][p].equals(marker))
                {
                    board[i][p] = "_";
                }
            }
        }
    }

    public void removeAllTemps() //removes all temps (obselete)
    {
        for (int i = 0; i < size; i++)
        {
            for (int p = 0; p < size; p++)
            {
                if (board[i][p].equals("T"))
                {
                    board[i][p] = "_";
                }
            }
        }
    }
    public void printConflicts()
    {
        for (int i = 0; i < size; i++)
        {
            for (int p = 0; p < size; p++)
            {
                if (board[p][i].equals("T"))
                {
                    System.out.print("|T");
                }

                else if (isInvalidPosition(i,p))
                {
                    System.out.print("|X");
                }
                else if (!isInvalidPosition(i,p) && !board[p][i].equals("Q") && !board[p][i].equals("K"))// && !board[p][i].equals("T"))
                {
                    System.out.print("|_");
                }
                else if (board[p][i].equals("Q"))
                {
                    System.out.print("|Q");
                }
                else if (board[p][i].equals("T"))
                {
                    System.out.print("|T");
                }
            }
            System.out.println("|");
        }
    }
    //used for seeing if board state changed when my eyes start hurting
    public int hash()
    {
        int temp = 7;
        for (int i = 0; i < size; i++)
        {
            for (int p = 0; p < size; p++)
            {
                temp = temp*31+board[p][i].charAt(0);
            }
        }
        return temp;
    }

    //for some reason not all temp markers are being deleted? figure this out
    //this is temp fix
	//shiet still cant find fix submiting anyways
    public void clearAllMarkers()
    {
        for (int i = 0; i < size; i++)
        {
            for (int p = 0; p < size; p++)
            {
                if (isTemp(p,i))
                    //if(board[i][p].equals("2") || board[i][p].equals("3") || board[i][p].equals("4"))
                {
                    board[i][p] = "_";
                }
            }
        }
    }
}
