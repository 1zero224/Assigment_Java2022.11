package abdn.scnu.cs;

import java.util.Arrays;

public class RunGame {
    public static void main(String[] args){
        if(args.length==3){
            Game round=new Game(Integer.parseInt(args[0]),Integer.parseInt(args[0]),Integer.parseInt(args[0]));
        }

    }
}
