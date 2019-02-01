package ir.nimdor.osoolproject;

// the instruction in binary format (?!) , saved separately
public class Instruction {
    int type
            , op
            , rs
            , rt
            , rd
            , shamt
            , funct
            ,address_constant
            , address_field ;

    public Instruction ( String instruct ) {
        if (iscommand("add" , instruct)){

            op = 0;
            funct = 32 ;

            return;
        }
        if (iscommand("sub", instruct)){
            op = 0 ;
            funct =34 ;


            return;
        }
        if (iscommand("and" , instruct)){
            op = 0 ;
            funct = 36 ;
            return ;
        }
        if (iscommand("or", instruct)){
            op = 0 ;
            funct = 37 ;
            return ;
        }
        if (iscommand("nor", instruct)){
            op = 0 ;
            funct = 39;
            return ;
        }
        if(iscommand("slt", instruct)){
            op = 0 ;
            funct = 42 ;
            return;
        }
        if (iscommand("beq" , instruct)){
            op = 4 ;


            return ;
        }
        if (iscommand("lw" , instruct)){
            op = 35 ;
            return ;
        }
        if (iscommand("sw", instruct)){
            op = 43 ;

            return;

        }

    }

    private boolean iscommand(String command , String inst){
        if ( command.length() > inst.length() ) return false ;
       for ( int i = 0 ;i < (int) command.length() ; i ++ ) {
           if (inst.charAt(i) != command.charAt(i) ) return false ;
       }
       return true ;

    }

}
