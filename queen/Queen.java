public class Queen
{
  static int counter = 0;
  static Window window = new Window(8);
  static int boardSize;
  public static void main(String[] args)
  {

	if (args.length <= 0 || args.length > 64)
	{
	  System.out.println("Size below 0 or greater than 64 please try again");
	  System.exit(0);
	}
	else
	{
	  boardSize = Integer.parseInt(args[0]);
	  window = new Window(boardSize);
	}
    //a.pack();
    //window.setVisible(true);
    foo();
	window.clearAllMarkers();
	System.out.println(window.printBoard());
    System.out.println(counter);
    //System.out.println(window.printBoard());
  }


  // do something for if queens are less than boardSize after its done then move the

  public static void testShit() { window.setPosition(0,0);
    //window.setPosition(1,0); //window.setPosition(0,1);
    window.setPosition(1,1);

    //System.out.println("All Test: " + window.isInvalidPosition(1,0));
    System.out.println("Vert: " + window.checkVertical(0,0));
    System.out.println("Horiz: " + window.checkHorizontal(0,0));
    System.out.println("Diag: " + window.checkDiag(0,0));
    System.out.println(window.board[3][4]);
  }

  /* HOW THIS WORKS This method works by backtracking combinations until the
   * desired combination is acheieved.
   * It first finds the next available spot then will place a queen for now we
   * will call this placement a "master queen"
   * After this master queen is placed it will place another queen
   * If the desired amount of queens is not met and there are no more spaces
   * it will pop that queen off and put a temporary marker where that queen
   * was and search for the next spot
   * It will keep doing this popping and adding queens to backtrack the
   * combinations until all queens are placed

   */
  public static boolean foo()
  {
    counter++;
	System.out.println(counter);
    //make hsh
    int currentx = 0,currenty = 0;
    if (window.getQueenCounter() < boardSize)
    {
      currentx = window.findSpot().getx();
      currenty = window.findSpot().gety();
      if (currentx != -5)
      {
        window.setPosition(currentx,currenty);
        System.out.println("hash is: " + window.hash() + " number of queens: " + window.getQueenCounter());
        //if (window.hash() == nul)
        System.out.println(window.printBoard());
        window.removeMarker(Integer.toString(window.getQueenCounter()+1));
        /*
          purposes If you place the i'th queen and the amount of queens
          places is not satisfactory and there are no more positions
          available it should remove all temporary markers for the
          positions it tried
		  If this is not done it will place n queens then loop between 3 states 
        */
        if (foo() == false)
        {
          window.markTemp(currentx,currenty,Integer.toString(window.getQueenCounter()));
          //System.out.println(window.printBoard());
          if (foo() == false)
          {
            return false;
          }
        }
        else //future queen can be placed
        {
          window.removeMarker(Integer.toString(8-window.getQueenCounter()));
          return true;
        }
      }
      else //gets called when no other spots are avail
      {
        return false;
      }
    }
    window.removeMarker(Integer.toString(window.getQueenCounter()));
    return true;
  }
}

//note if the space between queen palced and next queen is more than 1 return false and pop
//this means its skipping a column and will neer come back meaning it wont place n queens in nxn
//this will severley improve efficiency
// this may also fix the efficiecny of the getNextValidSpot method.. or w/e its called
