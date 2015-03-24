import java.util.Stack;

public class Pole
{
    int size;
    //    Stack<String> rings = new Stack<String>();
    //String[] rings;
    Ring[] rings;

    public Pole(int _size)
    {
        size = _size;
        rings = new Ring[size];
    }

    /*
      this first checks to see if there is anything on the pole, if
      not places at bottom, but in the case that there are rings it
      has to validate the ring placement (small on big)
      if its invalid it will return false (this method is not
      completley done here it is finished in trytoplace method in the
      run class
    */
    public boolean pushRing(Ring newRing)
    {
        if (rings[size-1] == null) //place if there is nothing else on pole
            {
                rings[size-1] = newRing;
                return true;
            }
        else
            {
                for (int i = size -1; i >= 0; i--) //find next open spot
                    {
                        if (rings[i] == null) //make sure its a open space ^^
                            {
                                //checks to see if the space is valid
                                try
                                    {
                                        if (rings[i+1] != null && rings[i+1].getLength() > newRing.getLength())
                                            {

                                                rings[i] = newRing;
                                                return true;
                                            }
                                    }
                                catch(Exception e)
                                    {
                                        return false;
                                    }
                            }
                    }
                return false;
            }
    }

    //pop and returns ring
    public Ring popRing()
    {
        for (int i = 0; i < size; i++)
            {
                if (rings[i] != null)
                    {
                        Ring temp = rings[i];
                        rings[i] = null;
                        return temp;
                    }
            }
        return null;
    }
    //hardest part of the entire project
    public Ring[] toArray()
    {
        return rings;
    }
}
