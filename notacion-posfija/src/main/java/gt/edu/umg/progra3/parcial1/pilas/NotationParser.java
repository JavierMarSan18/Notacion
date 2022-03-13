package gt.edu.umg.progra3.parcial1.pilas;


public class NotationParser {

    private Pila pila;

    public NotationParser(Pila pila) {
        this.pila = pila;
    }

    public String posFixToInfix(String expression){
        PilaImpl ent = stringAPila(delSpacing(expression));// + 3 * 2 1
        PilaImpl ope = new PilaImpl(); //Pila para guardar los operadores
        PilaImpl sal = new PilaImpl(); //Pila para guardar la salida

        String strPostToIn;

        // 1 2 * 3 + ---> 1 * 2 + 3
        while (!ent.isEmpty()){ // + 3 * 2 1 --> 3 + 2 * 1
            switch (prefer(ent.peek())){
                case 1:
                    ope.push(ent.pop());
                    break;
                default:
                    sal.push(ent.pop());
                    if(!ope.isEmpty()) sal.push(ope.pop());
                   break;
            }
        }

        strPostToIn = addSpacing(pilaAString(sal));
        return strPostToIn;
    }

    private String delSpacing(String s){
        return s.replaceAll("\\s","");
    }

    private String addSpacing(String str){
        StringBuilder strSpacing = new StringBuilder();
        String symbols = "*/+-";

        for (int i=0;i<str.length();i++){
            if(symbols.contains(""+str.charAt(i))){
                strSpacing.append(" ").append(str.charAt(i)).append(" ");
            }else {
                strSpacing.append(str.charAt(i));
            }
        }
        return strSpacing.toString();
    }

    private int prefer(String str){
        int n = 10;
        if(str.equals("+") || str.equals("+") || str.equals("*") || str.equals("/")) n = 1;
        return n;
    }

    private PilaImpl stringAPila(String string){
        PilaImpl pila = new PilaImpl();
        for (int i=0;i<string.length();i++){
            pila.push(String.valueOf(string.charAt(i)));
        }
        return pila;
    }

    private String pilaAString(PilaImpl pila){

        StringBuilder strb = new StringBuilder();
        while (!pila.isEmpty()){
            strb.append(pila.pop());
        }
        return strb.toString();
    }

}
