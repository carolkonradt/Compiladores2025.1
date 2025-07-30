package ast;

import java.util.ArrayList;

public class Prog{
    public Main main;
    public ArrayList<Fun> fun;
    public Prog(Main main, ArrayList<Fun> fun)
    {
        this.main = main;
        this.fun = fun;
    }

    @Override
    public String toString(){
        String body = "public static void main(String args[]){\n" + main.toString() + "}\n";
        String funcs = "";
        for (Fun f : fun) {
            funcs += f.toString() + "\n";
        }
        return body + "\n" + funcs; 
    }
}

