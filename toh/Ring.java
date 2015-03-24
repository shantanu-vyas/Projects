public class Ring
{
	private String ringString = "";
	private int length;
	public Ring(int _length)
	{
		length = _length;
	}
	public void init()
	{
		for (int i = 1; i <=length; i++)
		{
			ringString = ringString + "_";
		}
	}
	public int getLength()
	{
		return length;
	}
	public String getRing()
	{
		return ringString;
	}
	public String toString()
	{
		String temp = "";
		for (int i = 0; i < length; i++)
		{
			temp = temp + "_";
		}
		return temp;
	}
}
