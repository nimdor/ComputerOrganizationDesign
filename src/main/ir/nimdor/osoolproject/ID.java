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
        // set aluop
        if (instruction.getOp() == Commands.Rtype.getValue()) {
            controlVariables.setAluOP(2);

        }
        else if (instruction.getOp()== Commands.lw.getValue() || instruction.getOp() == Commands.sw.getValue()) {
            controlVariables.setAluOP(0);
        }
        else if (instruction.getOp()== Commands.beq.getValue()){
            controlVariables.setAluOP(1);
        }



    }


}
