class Parser{

	AnaliseLexica scanner;
	

	Parser(AnaliseLexica s)
	{
		scanner = s;
	}

ArvoreSintatica parseProg() throws Exception
	{
		ArvoreSintatica resultado = Exp();
		Token tokenCorrente = scanner.getNextToken(); // Verifica se há algo após a expressão principal
		if(tokenCorrente.token != TokenType.EOF) {
            // Se o token "EOF" tiver um lexema como "\0" ou similar, pode causar confusão na mensagem de erro.
            // Melhorar a mensagem se o token não for EOF.
            String lexemaEsperado = tokenCorrente.lexema;
            if (tokenCorrente.token == TokenType.NUM) lexemaEsperado = "<NUM>"; // Para não imprimir o número todo
            else if (tokenCorrente.lexema.trim().isEmpty() || !Character.isLetterOrDigit(tokenCorrente.lexema.charAt(0))) {
                 lexemaEsperado = "'" + tokenCorrente.lexema + "'";
            }
			throw (new Exception("Esperava: EOF mas encontrou: " + tokenCorrente.token + " (lexema: " + lexemaEsperado +")"));
        }
		return resultado;
	}

	Exp Exp() throws Exception
	{       Exp exp1, exp2;
		Token tokenCorrente =  scanner.getNextToken();

		if(tokenCorrente.token == TokenType.NUM) {
            // ALTERADO: tokenCorrente.lexema já é uma String
			return new Num(Integer.parseInt(tokenCorrente.lexema)); 
        }
		
		if(tokenCorrente.token == TokenType.APar)
			{
				exp1 = Exp();
				if(exp1 == null)
					throw (new Exception("Não encontrei expressão após '('!"));
				
				Operador op = Op ();

				if (op == null) // Op() agora deve lançar exceção se não encontrar operador
					throw (new Exception("Não encontrei operador válido após a primeira expressão!"));

				exp2 = Exp();
				if(exp2 == null)
					throw (new Exception("Não encontrei expressão após o operador!"));	
				
				op.arg1 = exp1;
				op.arg2 = exp2;
				tokenCorrente =  scanner.getNextToken();
				if(tokenCorrente.token != TokenType.FPar)
					throw (new Exception("Estava esperando: ')' mas encontrei: " + tokenCorrente.token + " (lexema: " + tokenCorrente.lexema +")"));
				return op;
								
			} else {
                // Mensagem de erro mais clara
                String lexemaEncontrado = tokenCorrente.lexema;
                if (tokenCorrente.token == TokenType.NUM) lexemaEncontrado = "<NUM>";
                else if (tokenCorrente.lexema.trim().isEmpty() || !Character.isLetterOrDigit(tokenCorrente.lexema.charAt(0))) {
                    lexemaEncontrado = "'" + tokenCorrente.lexema + "'";
                }
                throw (new Exception ("Esperava: '(' ou <NUM> mas encontrei: " + tokenCorrente.token + " (lexema: " + lexemaEncontrado +")"));
            }
	}

	Operador Op () throws Exception
		{
		
		Token tokenCorrente = scanner.getNextToken();
		switch(tokenCorrente.token){
			case SOMA:
				return new Soma(null,null);
			case MULT:
				return new Mult(null,null);
			case SUB:
				return new Sub(null,null);
			case DIV:
				return new Div(null,null);
			default: 
		}
		return null;
			

		}

}
