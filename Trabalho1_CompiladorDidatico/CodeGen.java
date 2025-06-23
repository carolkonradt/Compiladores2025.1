import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

class CodeGen{

	void geraCodigo (ArvoreSintatica arv, String nomeArquivo) throws IOException
	{
		String codigo = geraCodigo2(arv) + "PRINT";
		escreverArquivo(nomeArquivo, codigo);
	}

	private void escreverArquivo(String nomeArquivo, String codigo) throws IOException{
		try(BufferedWriter writer = new BufferedWriter(new FileWriter(nomeArquivo))) {
			writer.write(codigo);
		}
	}
	String geraCodigo2 (ArvoreSintatica arv)
	{

	if (arv instanceof Mult)
		return (geraCodigo2(((Mult) arv).arg1) + 
			geraCodigo2(((Mult) arv).arg2) +
			"MULT\n");

	if (arv instanceof Soma)
		return (geraCodigo2(((Soma) arv).arg1) + 
			geraCodigo2(((Soma) arv).arg2) +
			"SUM\n");

	if (arv instanceof Sub)
		return (geraCodigo2(((Sub) arv).arg1) + 
			geraCodigo2(((Sub) arv).arg2) +
			"SUB\n");
	
	if (arv instanceof Div)
		return (geraCodigo2(((Div) arv).arg1) + 
			geraCodigo2(((Div) arv).arg2) +
			"DIV\n");

	if (arv instanceof Num)
		return ("PUSH "  + ((Num) arv).num + "\n");

	return "";
	}
}
