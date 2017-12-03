package com.ssh.nisus.utils;

public class Debug {

    public static void log(Object o)
    {
        StackTraceElement ste = new Throwable().getStackTrace()[1];
        String info = o +"------------"+ ste.getFileName() + ">> Line " + ste.getLineNumber();
        System.out.println(info);
    }
    
    public static void log()
    {
    	StackTraceElement ste = new Throwable().getStackTrace()[1];
    	String info = "------------"+ ste.getFileName() + ">> Line " + ste.getLineNumber();
    	System.out.println(info);
    }
	
}
