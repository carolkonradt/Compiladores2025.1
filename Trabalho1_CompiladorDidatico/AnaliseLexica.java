import java.io.*;

enum TokenType{ NUM,SOMA,SUB,MULT,DIV,APar,FPar, EOF}

class Token{
  String lexema;
  TokenType token;

 Token (String l, TokenType t)
 	{ 	lexema=l;
		token = t;}	


 Token (char l, TokenType t) // Construtor adicional para conveniência com caracteres únicos (operadores, parênteses)
    { 	lexema=String.valueOf(l); 
		token = t;
	}
}
class AnaliseLexica {

	BufferedReader arquivo;

	AnaliseLexica(String a) throws Exception
	{
		
	 	this.arquivo = new BufferedReader(new FileReader(a));
		
	}
Token getNextToken() throws Exception
	{	
		// Token token; // Instanciação direta no return
		int eof = -1;
		char currchar;
		int currchar1;

			do{
				arquivo.mark(1); // Marca a posição para poder voltar se necessário ao ler o próximo token
				currchar1 =  arquivo.read();
				currchar = (char) currchar1;
			} while (currchar == '\n' || currchar == ' ' || currchar =='\t' || currchar == '\r');
			
			if(currchar1 == eof) { // Tratamento explícito de EOF primeiro
                arquivo.close();
                // Usa o construtor que aceita char para conveniência (será convertido para String)
                // ou pode-se usar new Token("EOF", TokenType.EOF) diretamente.
				return (new Token('\0', TokenType.EOF)); 
            }
            // O teste currchar1 != 10 (LF) já é coberto pelo while anterior
			
			if (currchar >= '0' && currchar <= '9') { // Se for um dígito, inicia a leitura do número
				StringBuilder numStr = new StringBuilder();
				do {
					numStr.append(currchar);
					arquivo.mark(1); // Marca antes de ler o próximo char
					currchar1 = arquivo.read();
					currchar = (char) currchar1;
				} while (currchar1 != eof && currchar >= '0' && currchar <= '9'); // Continua enquanto for dígito
				
                if (currchar1 != eof) { // Se não for EOF, o último char lido não era dígito
				    arquivo.reset(); // Devolve o último caractere lido (que não é dígito) para o buffer
                }
                // Cria o token NUM com a string de dígitos acumulada
				return (new Token (numStr.toString(), TokenType.NUM));
			}
			else { // Se não for número, trata como outros tokens (operadores, parênteses)
				switch (currchar){
					case '(':
                        // Usa o construtor Token(char, TokenType) que converte char para String
						return (new Token (currchar,TokenType.APar));
					case ')':
						return (new Token (currchar,TokenType.FPar));
					case '+':
						return (new Token (currchar,TokenType.SOMA));
					case '*':
						return (new Token (currchar,TokenType.MULT));
					 case '-':
						return (new Token (currchar,TokenType.SUB));
                    case '/':
						return (new Token (currchar,TokenType.DIV));
					
					default: 
                        // Garante que mesmo que o caractere seja um line feed (10) ou outro não esperado aqui,
                        // lance uma exceção. O `do-while` acima deveria pular LF, CR etc.
                        if (currchar1 == 10) { // Se por algum motivo um LF (10) passar, pode ser um EOF "disfarçado" ou erro
                             arquivo.close();
                             return (new Token('\0',TokenType.EOF)); 
                        }
                        throw (new Exception("Caractere inválido: '" + currchar + "' (código: " + ((int) currchar) + ")"));
				}
			}
		// Este ponto não deveria ser alcançado se a lógica acima estiver correta
		// e o EOF for tratado no início do if(currchar1 != eof ...)
		// Mas para garantir, se o loop inicial for quebrado por EOF:
        // arquivo.close(); // Já tratado acima
		// return (new Token('\0', TokenType.EOF));
	}
		
}

