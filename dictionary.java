package DS;

public class dictionary {
private String word;
private String context;
public dictionary(String word, String context )
{
	this.word=word;
	this.context=context;
}
public static String data (dictionary word )
{
	return (word.getword()+"|"+word.getcontext());
}
public String getword()
{
	return word;
}
public String getcontext()
{
	return context;
}
public void setword(String word)
{
	this.word=word;
}
public void setcontext(String context)
{
	this.context=context;
}
}
