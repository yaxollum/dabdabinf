package dabdabinf.application;

import dabdabinf.application.ExpectedNumberException;
import dabdabinf.application.NotEnoughArgumentsException;

public class Command
{
    private String[] splitCommand;

    public Command(String rawCommand)
    {
        splitCommand=rawCommand.trim().split("\\s+");
    }

    public String getName()
    {
        return splitCommand[0];
    }
    public String getArgument(int i) throws NotEnoughArgumentsException
    {
        if(i<splitCommand.length)
        {
            return splitCommand[i];
        }
        else
        {
            throw new NotEnoughArgumentsException();
        }
    }
    public int getIntArgument(int i) throws ExpectedNumberException, NotEnoughArgumentsException
    {
        try
        {
            return Integer.parseInt(getArgument(i));
        }
        catch(NumberFormatException e)
        {
            throw new ExpectedNumberException();
        }
    }
}
