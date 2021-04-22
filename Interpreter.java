package interpreter;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
import java.util.Stack;

public class Interpreter {

    public static void main(String[] args) throws FileNotFoundException, IOException {
        switch (args.length){
            case 0:
                System.out.println("write something, for fuck's sake.");
            break;
            case 1:
                if(args[0].equals("run") || args[0].equals("file"))
                    System.out.println("error: not enough arguments.");
                else
                    System.out.println("error: command not recognised.");
            break;
            case 2:
                if(!args[0].equals("run") && !args[0].equals("file"))
                    System.out.println("error: command not recognised.");
                else{
                    if(args[0].equals("run"))
                        interpretString(args[1]);
                    else
                        InterpretFile(args[1]);
                }
            break;
            default:
                System.out.println("error: too many arguments.");
        }
        
    }

    private static String interpretString(String arg) throws IOException {
        String intstr,ascii;
        intstr=reduce(arg);
        int i,app;
        Scanner input = new Scanner(System.in);
        //checks if it is a correct program
        for(i=0;i<intstr.length();i++){
            if (intstr.charAt(i) < 'a' || intstr.charAt(i) > 'z'){
                System.out.println("syntax error, symbol(s) not recognised");
                System.exit(1);
            }
        }
        //executes it
        Stack <Integer> memcell = new Stack<>();
        int lastIndex = -1;
        char c;
        for (i=0;i<intstr.length();i++){
            switch(intstr.charAt(i)){
                case 'a':
                    memcell.push(0);
                    lastIndex++;
                break;
                case 'b':
                    if (memcell.empty()){
                        System.out.println("error: trying to access an empty memory cell.");
                        System.exit(0);
                    }else{
                        memcell.pop();
                        lastIndex--;
                    }                
                break;
                case 'c':
                    if(lastIndex < 1){
                         System.out.println("error: stack has less than two elements.");
                         System.exit(0);
                    }
                    else{
                        memcell.push(memcell.elementAt(lastIndex)-memcell.elementAt(lastIndex -1));
                        lastIndex++;
                    }
                break;
                case 'd':
                    if (memcell.empty() || memcell.peek() == 0){
                        System.out.println("error: trying to access an empty or zero memory cell.");
                        System.exit(0);
                    }else 
                        memcell.set(lastIndex, memcell.lastElement()-1);
                break;
                case 'e':
                    if(lastIndex < 1){
                         System.out.println("error: stack has less than two elements.");
                         System.exit(0);
                    }
                    else{
                        memcell.push(memcell.elementAt(lastIndex)%memcell.elementAt(lastIndex -1));
                        lastIndex++;
                    }
                break;
                case 'f':
                    if (lastIndex == -1){
                        System.out.println("error: stack is empty.");
                    }
                    else{
                        c=(char) memcell.lastElement().intValue();
                        System.out.print(c);
                    }
                break;
                case 'g':
                    if(lastIndex < 1){
                         System.out.println("error: stack has less than two elements.");
                         System.exit(0);
                    }
                    else{
                        memcell.push(memcell.elementAt(lastIndex)+memcell.elementAt(lastIndex -1));
                        lastIndex++;
                    }
                break;
                case 'h':
                    System.out.println("insert a number:\n");
                    memcell.push(input.nextInt());
                    lastIndex++;        
                break;
                case 'i':
                    if (memcell.empty()){
                        System.out.println("error: trying to access an empty memory cell.");
                        System.exit(0);
                    }else
                        memcell.set(lastIndex, memcell.lastElement()+1);
                break;
                case 'j':
                    System.out.println("\ninsert a character:");
                    ascii=input.nextLine();

                    if  (ascii.length()>1){
                        System.out.println("error: only one character at a time.");
                        System.exit(0);
                    } else{
                    memcell.push((int)ascii.charAt(0));
                    lastIndex++;}     
                break;
                case 'k':
                break;
                case 'l':
                    if(lastIndex < 1){
                         System.out.println("error: stack has less than two elements.");
                         System.exit(0);
                    }
                    else{
                        app=memcell.lastElement();
                        memcell.set(lastIndex, memcell.elementAt(lastIndex -1));
                        memcell.set(lastIndex-1, app);
                    }
                break;
                case 'm':
                    if(lastIndex < 1){
                         System.out.println("error: stack has less than two elements.");
                         System.exit(0);
                    }
                    else{
                        memcell.push(memcell.elementAt(lastIndex)*memcell.elementAt(lastIndex -1));
                        lastIndex++;
                    }
                break;
                case 'n':
                break;
                case 'o':
                break;
                case 'p':
                    if(lastIndex < 1){
                         System.out.println("error: stack has less than two elements.");
                         System.exit(0);
                    }
                    if (memcell.elementAt(lastIndex -1) == 0)
                        System.out.println("ERROR: YOU'RE TRYING TO DIVIDE BY 0, YOU FOOL, YOU'VE DOOMED US ALL");
                    else{
                        memcell.push(memcell.elementAt(lastIndex)/memcell.elementAt(lastIndex -1));
                        lastIndex++;
                    }
                break;
                case 'q':
                    if (memcell.empty()){
                        System.out.println("error: trying to access an empty memory cell.");
                        System.exit(0);
                    } else{
                        memcell.push(memcell.lastElement());
                        lastIndex++;
                    }
                break;
                case 'r':
                    if (memcell.empty()){
                        System.out.println("error: trying to access an empty memory cell.");
                        System.exit(0);
                    }else
                        memcell.push(memcell.size());
                break;
                case 's':
                break;
                case 't':
                break;
                case 'u':
                break;
                case 'v':
                    if (memcell.empty()){
                        System.out.println("error: trying to access an empty memory cell.");
                        System.exit(0);
                    }else
                        memcell.set(lastIndex, memcell.lastElement()+5);
                break;
                case 'w':
                    if (memcell.empty() || memcell.peek() < 5){
                        System.out.println("error: trying to access an empty cell or the element is lower than 5.");
                        System.exit(0);
                    }else 
                        memcell.set(lastIndex, memcell.lastElement()-5);
                break;
                case 'x':
                    if (memcell.empty()){
                        System.out.println("error: trying to access an empty memory cell.");
                        System.exit(0);
                    }else
                        System.out.print(memcell.peek());                    
                break;
                case 'y':
                    if (memcell.empty()){
                        System.out.println("error: trying to access an empty memory cell.");
                        System.exit(0);
                    }else{
                        memcell.clear();
                        lastIndex = -1;
                    }
                break;
                case 'z':
                    System.exit(0);
                break;
                default:
            }
        }
        return intstr;
    }

    private static void InterpretFile(String filename) throws FileNotFoundException, IOException {
        File f=new File(filename);
        Scanner in;
        String s = "";
        
        if (!f.exists())
            System.out.println("error: file not found");
        else{
            in=new Scanner(f);
            while(in.hasNextLine()){
                s=s.concat(reduce(in.nextLine()));
            }
        }
        interpretString(s);
    }
    
private static String reduce(String arg) {
    int strcon = 0,i;//string counter,to delete blank spaces
    String intstr="";
    //scans the inputted string and deletes blank spaces and comments
    for (i=0;i<arg.length();i++){
        if (arg.charAt(i) != ' '){
            if (i<=arg.length()-3){
                if (arg.substring(i, i+2).equals("//"))
                    break;
                else{
                    intstr+=arg.charAt(i);
                    strcon++;
                }
            }else{
                intstr+=arg.charAt(i);
                strcon++;
                }
        }
    }
    return intstr;
}
}