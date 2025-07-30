package ast;

import java.util.ArrayList;

public class CChamadaFun extends Comando{
	public int linha;
	public String fun;
	public ArrayList<Exp> args;
	
	public CChamadaFun(int linha,String fun, ArrayList<Exp> args)
	{
	  this.linha = linha;
	  this.fun = fun;
	  this.args = args;
	}
	
	@Override
  	public String toString(){
		String params = "";
		for (int i = 0; i < args.size(); i++)
			if (i == args.size() - 1) {
				params += args.get(i).toString();
			} else {
				params += args.get(i).toString() + ", ";
			}
    	return fun + "(" + params + ");\n"; 
   }

}
