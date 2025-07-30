package ast;

import java.util.ArrayList;

public class CIf extends Comando{
	public int linha;
	public Exp exp;
	public ArrayList<Comando> bloco;
	
	public CIf(int linha,Exp exp, ArrayList<Comando> bloco)
	{
	  this.linha = linha;
	  this.exp = exp;
	  this.bloco = bloco;
	} 

	@Override
   	public String toString(){
		String commands = "";
		for (Comando com : bloco) 
			commands += "\t" + com.toString();

      	return "if(" + exp.toString() + "){\n\t" + commands + "\t}\n"; 
   }

}
