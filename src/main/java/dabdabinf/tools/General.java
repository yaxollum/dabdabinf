package dabdabinf.tools;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class General
{
    static MessageDigest md;
    
    static
    {
        try
		{
			md=MessageDigest.getInstance("SHA-256");
		}
		catch (NoSuchAlgorithmException e)
		{
			System.out.println("ERROR: "+e);
		}
    }
    
	public static String readEntireFile(File ff)
	{
		try
		{
			Scanner fileScanner=new Scanner(ff);
			fileScanner.useDelimiter("\\Z");
			return fileScanner.next();
		}
		catch (FileNotFoundException e)
		{
			System.out.println("ERROR: FILE \""+ff.getName()+"\" NOT FOUND!");
			System.exit(1);
		}
		
		return null;
	}

    public static String readEntireResource(String path)
    {
        Scanner resourceScanner=new Scanner(General.class.getResourceAsStream(path));
        resourceScanner.useDelimiter("\\Z");
        return resourceScanner.next();
    }
	
	public static void writeToFile(File ff,String s)
	{
	    PrintWriter printWriter=null;
	    try
	    {
	        printWriter=new PrintWriter(ff);
	    }
	    catch(FileNotFoundException e)
	    {
	        System.out.println(e);
	        System.exit(1);
	    }
	    printWriter.println(s);
	    printWriter.close();
	}
	
	public static byte[] sha256(byte[] data)
	{
	    return md.digest(data);
	}

    public static String randomDigitString(int len)
    {
        char[] str=new char[len];
        for(int i=0;i<len;++i)
        {
            str[i]=rng.nextInt(10)+'0';
        }
        return new String(str);
    }

    public static boolean isValidBase64(String str)
    {
        for(int i=0;i<str.length();++i)
        {
            char c=str.charAt(i);
            if(c=='='||
                c=='+'||
                c=='/'||
                (c>='A'&&c<='Z')||
                (c>='a'&&c<='z'))
            {
                continue;
            }
            else
            {
                return false;
            }
        }
        return true;
    }

    public static boolean isValidMagicWord(String str)
    {
        if(str.length()!=10) return false;
        for(int i=0;i<10;++i)
        {
            char c=str.charAt(i);
            if(!(c>='a'&&c<='z'))
            {
                return false;
            }
        }
        return true;
    }
}
