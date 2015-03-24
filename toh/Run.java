import java.lang.Thread;
public class Run
{
    static Pole[] poles = new Pole[3];
    static int size;
    public static void main(String[] args)
    {
        if (args.length != 1)
            {
                System.out.println("invalid params please try again");
                System.exit(0);
            }
        else
            {
                size = Integer.parseInt(args[0]);
            }
        initBoard();
		foo(0,1,2,size);
        System.out.println("\n\n");

    }

    /*
      This method is designed to encapuslate some of the code and make
      stuff easier to code/read.
      It first checks to see the placement is valid (small ring on big ring)
      if it is it will place it otherwise it will put the ring back,
      this uses the pushRing method in the pole class
    */
    public static boolean tryToPlace(int startPole, int endPole)
    {
        //System.out.println("Attempting to place" + " " + startPole + " " + endPole);
        Ring tempRing=null;
        tempRing = poles[startPole].popRing();
        if (poles[endPole].pushRing(tempRing)==false)
            {
                poles[startPole].pushRing(tempRing);
                return false;
            }

        return true;
    }
    /* init board with rings */
    public static void initBoard()
    {
        poles[0] = new Pole(size);
        poles[1] = new Pole(size);
        poles[2] = new Pole(size);

        for (int i = size; i > 0; i--)
            {
                poles[0].pushRing(new Ring(i));
            }
    }

    /* just print the board */
    public static void printBoard()
    {
        System.out.println("");
        String line[] = new String[3];
        Ring[][] arrays = new Ring[][]{poles[0].toArray(),poles[1].toArray(),poles[2].toArray()};
        for (int i = 0; i < size; i++)
            {
                for (int p = 0; p < 3 ;p++)
                    {
                        if (arrays[p][i] == null)
                            {
                                System.out.print("|");
                            }
                        else
                            {
                                System.out.print("\t" + arrays[p][i]+"\t");
                            }
                    }
                System.out.println("");
            }
    }
	/* really bad for printing stuff
	   I was having alot of trouble doing this so to move on and get to the recursion
	   I did it by putting everything from a Ring to a String then use a formatted print
	   So everything prints all nicely =D
	 */
    public static void printBoard1()
    {
        System.out.println("");
        String line[][] = new String[3][size];
        Ring[][] arrays = new Ring[][]{poles[0].toArray(),poles[1].toArray(),poles[2].toArray()};
        for (int i = 0; i < size; i++) //element in array
            {
                for (int p = 0; p < 3 ;p++) //which pole
                    {
                        if (arrays[p][i] == null)
                            {
                                line[p][i] = "|";
                            }
                        else
                            {
                                line[p][i] = arrays[p][i].toString();
                            }
                    }
            }
        for (int s = 0; s < size; s++)
            {
                System.out.printf("%-10s %-10s %-10s\n", line[0][s], line[1][s],line[2][s]);
            }
    }


    /* solver*/
    public static void foo (int startPole, int tempPole, int endPole, int numOfRings)
    {

        System.out.println("");
        if (numOfRings == 1)
            {
                //System.out.println("Only 1 ring moving to end pole");
                tryToPlace(startPole, endPole);
                printBoard1();
            }
        else
            {
                //            System.out.println("moving rings to temp pole");
                foo(startPole,endPole,tempPole,numOfRings-1);
                //            System.out.println("moving last ring to end pole");
                tryToPlace(startPole,endPole);
                printBoard1();
                //            System.out.println("moving temp rings to end pole");
                foo(tempPole,startPole,endPole,numOfRings-1);
            }
    }
}
