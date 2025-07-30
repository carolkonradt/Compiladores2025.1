package ast;


public class CReadInput extends Comando{
	public int linha;
	public String var;
	
	
	public CReadInput(int linha,String var)
	{
	  this.linha = linha;
	  this.var = var;
	} 

	@Override
   	public String toString(){
      return var + " = new java.util.Scanner(System.in).nextLine(); \n"; 
   }

}
