package ast;

import java.util.ArrayList;

public class Fun{
	public String nome;
	public ArrayList<ParamFormalFun> params;
	public String retorno;
	public ArrayList<VarDecl> vars;
	public ArrayList<Comando> body;
	
	public Fun(String nome,ArrayList<ParamFormalFun> params, String retorno,ArrayList<VarDecl> vars,ArrayList<Comando> body)
	{
		this.nome = nome;
		this.params = params;
		this.retorno = retorno;
		this.vars = vars;
		this.body = body;
	}

	@Override
	public String toString(){
		String varDeclarations = "";
		String parameters = "";
		String commands = "";

		for (int i = 0; i < params.size(); i++)
			if (i == params.size() - 1) {
				parameters += params.get(i).toString();
			} else {
				parameters += params.get(i).toString() + ", ";
			}
		
		for (VarDecl v : vars) {
            varDeclarations +="\t" + v.toString() + "\n";
        }

		for (Comando c : body) {
            commands += "\t" + c.toString() + "\n";
        }
		return "public " + retorno + " " + nome + "(" + parameters + "){\n" + varDeclarations + commands + "}\n";
	}
}
