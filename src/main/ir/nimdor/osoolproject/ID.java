package main.ir.nimdor.osoolproject;

import main.ir.nimdor.osoolproject.Commands;

//Dorsa
public class ID extends Component {
    PipeReg prev , next;
    Instruction instruction ;
    int[] memory  ;

    public ID (){
        memory = new int[100];
    }

    @Override
    public void run(PipeReg prev , PipeReg next ) {
        this.prev = prev ;
        this.next = next ;
        instruction = prev.getInstruction() ;
        next.setInstruction(instruction);
        next.getNonControlVariables().setPc(prev.getNonControlVariables().getPc());

        updatememory () ;
        updatecontrolvalues();
    }

    private void updatememory(){

        NonControlVariables nonvar = next.getNonControlVariables() ;
        nonvar.setRd(memory[instruction.getRd()]);
        nonvar.setRs(memory[instruction.getRs()]);
        nonvar.setRt(memory[instruction.getRt()]);

    }
    private void updatecontrolvalues(){

        ControlVariables controlVariables = new ControlVariables();
        // set controls based on opcode
        if (instruction.getOp() == Commands.Rtype.getValue()) {
            controlVariables.setAluOP(2);
            controlVariables.setRegDest(true);
            controlVariables.setAluSrc(false);
            controlVariables.setMemToReg(false);
            controlVariables.setRegWrite(true);
            controlVariables.setMemRead(false);
            controlVariables.setMemWrite(false);
            controlVariables.setBranch(false);
        }
        else if (instruction.getOp()== Commands.lw.getValue() || instruction.getOp() == Commands.sw.getValue()) {
            controlVariables.setAluOP(0);
            if (instruction.getOp() == Commands.lw.getValue()) {
                controlVariables.setRegDest(false);
                controlVariables.setAluSrc(true);
                controlVariables.setMemToReg(true);
                controlVariables.setRegWrite(true);
                controlVariables.setMemRead(true);
                controlVariables.setMemWrite(false);
                controlVariables.setBranch(false);
            }
            else {
                controlVariables.setRegDest(false);// not impo
                controlVariables.setAluSrc(true);
                controlVariables.setMemToReg(false); // not impo
                controlVariables.setRegWrite(false) ;
                controlVariables.setMemRead(false);
                controlVariables.setMemWrite(true);
                controlVariables.setBranch(false);

            }
        }
        else if (instruction.getOp()== Commands.beq.getValue()){
            controlVariables.setAluOP(1);
            controlVariables.setRegDest(false); // not impo
            controlVariables.setAluSrc(false);
            controlVariables.setMemToReg(false); // not impo
            controlVariables.setRegWrite(false);
            controlVariables.setMemRead(false);
            controlVariables.setMemWrite(false);
            controlVariables.setBranch(true);
        }

        next.setControlVariables(controlVariables);


    }

    public boolean write ( int index , int data , boolean regdst )  {
        if ( regdst == false ) return false ;
        memory[index] = data ;
        return true ;
    }


}
