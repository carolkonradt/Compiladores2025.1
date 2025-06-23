class Compilador{

	public static void main(String[]args)
	{	
		ArvoreSintatica arv=null;
	
		try{

			AnaliseLexica al = new AnaliseLexica(args[0]);
			Parser as = new Parser(al);
		
			arv = as.parseProg();
		
			
			CodeGen backend = new CodeGen();
			backend.geraCodigo(arv, "output.txt");
			System.out.println("Arquivo gerado com sucesso!");
			//Interpreter interpretador = new Interpreter();
			//int resultado = interpretador.interpreta(arv);
			//System.out.println("Resultado da expressão: " + resultado);

		}catch(Exception e)
		{			
			System.out.println("Erro de compilação:\n" + e);
		}



	}
}
