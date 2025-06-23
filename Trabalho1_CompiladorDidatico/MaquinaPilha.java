import java.io.BufferedReader;
import java.io.FileReader;
import java.util.EmptyStackException;
import java.util.Stack;

public class MaquinaPilha {
    private Stack<Integer> pilha;
    
    public MaquinaPilha() {
        pilha = new Stack<>();
    }
    
    public int executar(String arquivoEntrada) throws Exception {
        try (BufferedReader br = new BufferedReader(new FileReader(arquivoEntrada))) {
            String linha;
            
            while ((linha = br.readLine()) != null) {
                linha = linha.trim();
                if (linha.isEmpty()) continue;
                
                if (linha.startsWith("PUSH")) {
                    String[] partes = linha.split(" ");
                    if (partes.length != 2) {
                        throw new Exception("Instrução PUSH mal formada: " + linha);
                    }
                    try {
                        int valor = Integer.parseInt(partes[1]);
                        pilha.push(valor);
                    } catch (NumberFormatException e) {
                        throw new Exception("Valor numérico inválido em PUSH: " + partes[1]);
                    }
                } 
                else if (linha.equals("SUM")) {
                    executarOperacaoBinaria((a, b) -> a + b);
                } 
                else if (linha.equals("SUB")) {
                    executarOperacaoBinaria((a, b) -> a - b);
                } 
                else if (linha.equals("MULT")) {
                    executarOperacaoBinaria((a, b) -> a * b);
                } 
                else if (linha.equals("DIV")) {
                    executarOperacaoBinaria((a, b) -> {
                        if (b == 0) throw new ArithmeticException("Divisão por zero");
                        return a / b;
                    });
                } 
                else if (linha.equals("PRINT")) {
                    // PRINT é tratado no final, após todas as instruções
                    continue;
                } 
                else {
                    throw new Exception("Instrução desconhecida: " + linha);
                }
            }
            
            if (pilha.isEmpty()) {
                throw new Exception("Nenhum resultado na pilha");
            }
            
            return pilha.pop();
        } catch (EmptyStackException e) {
            throw new Exception("Pilha vazia durante execução de operação");
        }
    }
    
    private void executarOperacaoBinaria(OperacaoBinaria operacao) throws Exception {
        if (pilha.size() < 2) {
            throw new Exception("Pilha com menos de 2 elementos para operação binária");
        }
        int b = pilha.pop();
        int a = pilha.pop();
        pilha.push(operacao.aplicar(a, b));
    }
    
    @FunctionalInterface
    private interface OperacaoBinaria {
        int aplicar(int a, int b) throws Exception;
    }
    
    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.println("Uso: java MaquinaPilha <arquivoDeEntrada>");
            System.exit(1);
        }
        
        try {
            MaquinaPilha maquina = new MaquinaPilha();
            int resultado = maquina.executar(args[0]);
            System.out.println("Resultado: " + resultado);
        } catch (Exception e) {
            System.out.println("Erro na execução: " + e.getMessage());
            System.exit(1);
        }
    }
}