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
            String subs = instruct.substring(3 + 1 );
            String[] parts = subs.split(",");
            rd = getindex(parts[0]);
            rs = getindex(parts[1]);
            rt = getindex(parts[2]);
            return;
        }
        if (iscommand("sub", instruct)){
            op = 0 ;
            funct =34 ;

            String subs = instruct.substring(3 + 1 );
            String[] parts = subs.split(",");
            rd = getindex(parts[0]);
            rs = getindex(parts[1]);
            rt = getindex(parts[2]);

            return;
        }
        if (iscommand("and" , instruct)){
            op = 0 ;
            funct = 36 ;

            String subs = instruct.substring(3 + 1 );
            String[] parts = subs.split(",");

            rd = getindex(parts[0]);
            rs = getindex(parts[1]);
            rt = getindex(parts[2]);
            return ;
        }
        if (iscommand("or", instruct)){
            op = 0 ;
            funct = 37 ;
            String subs = instruct.substring(2 + 1 );
            String[] parts = subs.split(",");
            rd = getindex(parts[0]);
            rs = getindex(parts[1]);
            rt = getindex(parts[2]);

            return ;
        }
        if (iscommand("nor", instruct)){
            op = 0 ;
            funct = 39;
            String subs = instruct.substring(3 + 1 );
            String[] parts = subs.split(",");

            rd = getindex(parts[0]);
            rs = getindex(parts[1]);
            rt = getindex(parts[2]);
            return ;
        }
        if(iscommand("slt", instruct)){
            op = 0 ;
            funct = 42 ;
            String subs = instruct.substring(3 + 1 );
            String[] parts = subs.split(",");
            rd = getindex(parts[0]);
            rs = getindex(parts[1]);
            rt = getindex(parts[2]);

            return;
        }
        if (iscommand("beq" , instruct)){
            op = 4 ;

            String subs = instruct.substring(3 + 1 );
            String[] parts = subs.split(",");

            rs = getindex(parts[0]);
            rt = getindex(parts[1]);
            // ----------------------------------------------------- need to fill address feild ! ( how ? ! ) ---------------------------------------------
            return ;
        }


        //  ------------------------------------------------------- these two depend on indexing of words in memory ! ----------------------------------------
        if (iscommand("lw" , instruct)){
            op = 35 ;
            String subs = instruct.substring(2 + 1 );
            String[] parts = subs.split(",") ;
            return ;
        }
        if (iscommand("sw", instruct)){
            op = 43 ;

            String subs = instruct.substring(2 + 1 );
            String[] parts = subs.split(",");


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

    private int getindex ( String s ){ // (done)
        String str = s.substring(1); // remove $ sign
        switch (str) {

            case "zero":
                return 0;

            case "v0":
                return 2;

            case "v1":
                return 3;
            case "a0":
                return 4;
            case "a1":
                return 5;
            case "a2":
                return 6;
            case "a3":
                return 7;
            case "t0":
                return 8;
            case "t1":
                return 9;
            case "t2":
                return 10;
            case "t3":
                return 11;
            case "t4":
                return 12;
            case "t5":
                return 13;
            case "t6":
                return 14;
            case "t7":
                return 15;
            case "s0":
                return 16;
            case "s1":
                return 17;
            case "s2":
                return 18;
            case "s3":
                return 19;
            case "s4":
                return 20;
            case "s5":
                return 21;
            case "s6":
                return 22;
            case "s7":
                return 23;
            case "t8":
                return 24;
            case "t9":
                return 25;
            case "gp":
                return 28;
            case "sp":
                return 29;
            case "fp":
                return 30;
            case "ra":
                return 31;

        }




        return 0 ;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getOp() {
        return op;
    }

    public void setOp(int op) {
        this.op = op;
    }

    public int getRs() {
        return rs;
    }

    public void setRs(int rs) {
        this.rs = rs;
    }

    public int getRt() {
        return rt;
    }

    public void setRt(int rt) {
        this.rt = rt;
    }

    public int getRd() {
        return rd;
    }

    public void setRd(int rd) {
        this.rd = rd;
    }

    public int getShamt() {
        return shamt;
    }

    public void setShamt(int shamt) {
        this.shamt = shamt;
    }

    public int getFunct() {
        return funct;
    }

    public void setFunct(int funct) {
        this.funct = funct;
    }

    public int getAddress_constant() {
        return address_constant;
    }

    public void setAddress_constant(int address_constant) {
        this.address_constant = address_constant;
    }

    public int getAddress_field() {
        return address_field;
    }

    public void setAddress_field(int address_field) {
        this.address_field = address_field;
    }
}
