package ast;

import java.util.ArrayList;

public class Main{

	public ArrayList<VarDecl> vars;
	public ArrayList<Comando> coms;
	
	public Main(ArrayList<VarDecl> vars,ArrayList<Comando> coms)
	{
		this.vars = vars;
		this.coms = coms;
	}

	@Override
	public String toString(){
		String varDeclarations = "";
		String commands = "";

		for (VarDecl var : vars) 
			varDeclarations += "\t" + var.toString();
		
		for (Comando command : coms) 
			commands += "\t" + command.toString();

		return varDeclarations + "\n" + commands;
	}


}
