public class GameBoard
{
    private int numOfRings;
    private String[][] board;
    public GameBoard(int _numOfRings)
    {
        numOfRings = _numOfRings;
        board = new String[numOfRings][3];
		initPoles();
    }
    public void initPoles()
    {
        for (int i = 0; i < 3; i++)
        {
            for (int p = 0; p < numOfRings; p++)
            {
				board[p][i] = "|";
            }
        }
    }
    public void placeInitialRings()
    {
        //for (int i = numOfRings; i > 0; i--)
        for (int i = 0; i < numOfRings; i++)
        {
            String ring = "";
            for (int p = 0; p < i; p++)
            {
                ring = ring + "_";
            }
            board[i][1] = ring;
        }
    }
    public int getNumOfRings()
    {
        return numOfRings;
    }
    public void printBoard()
    {
        String temp = "";
        for (int i = 0; i < 3; i++)
        {
            for (int p = 0; p < numOfRings; p++)
            {
				System.out.print(board[p][i]);
            }
            System.out.println();
        }

    }
}
//fuck this make pole class
