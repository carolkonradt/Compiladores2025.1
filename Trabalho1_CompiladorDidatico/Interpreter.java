class Interpreter {
    
    public int interpreta(ArvoreSintatica arv) {
        return interpretaExp(arv);
    }
    
    private int interpretaExp(ArvoreSintatica arv) {
        if (arv instanceof Num) {
            return ((Num) arv).num;
        }
        else if (arv instanceof Soma) {
            return interpretaExp(((Soma) arv).arg1) + interpretaExp(((Soma) arv).arg2);
        }
        else if (arv instanceof Mult) {
            return interpretaExp(((Mult) arv).arg1) * interpretaExp(((Mult) arv).arg2);
        }
        else if (arv instanceof Sub) {
            return interpretaExp(((Sub) arv).arg1) - interpretaExp(((Sub) arv).arg2);
        }
        else if (arv instanceof Div) {
            int divisor = interpretaExp(((Div) arv).arg2);
            if (divisor == 0) {
                throw new ArithmeticException("Divisão por zero");
            }
            return interpretaExp(((Div) arv).arg1) / divisor;
        }
        else {
            throw new IllegalArgumentException("Tipo de nó desconhecido na árvore sintática");
        }
    }
}