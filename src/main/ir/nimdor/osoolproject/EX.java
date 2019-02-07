package ir.nimdor.osoolproject;

import java.util.ArrayList;

public class EX extends Component {
    private int ALUinp1, ALUinp2, ALUout;
    private boolean ALUzero;

    public EX() {
    }

    @Override
    public void run(PipeReg prev, PipeReg next) {
        ALUinp1 = ALUinp2 = ALUout = 0;
        ALUzero = false;
        ALUinp1 = prev.getNonControlVariables().getRs();
        if(prev.getControlVariables().isAluSrc()){
            ALUinp2 = prev.getNonControlVariables().getRd();
        }else{
            ALUinp2 = prev.getNonControlVariables().getRt();
        }
        if(prev.controlVariables.getAluOP() == 2){
            if(prev.getInstruction().funct == Commands.add.getValue()){
                ALUout = ALUinp1 + ALUinp2;
            }else if(prev.getInstruction().funct == Commands.sub.getValue()){
                ALUout = ALUinp1 - ALUinp2;
            }else if(prev.getInstruction().funct == Commands.and.getValue()){
                ALUout = ALUinp1 & ALUinp2;
            }else if(prev.getInstruction().funct == Commands.or.getValue()){
                ALUout = ALUinp1 | ALUinp2;
            }else if(prev.getInstruction().funct == Commands.nor.getValue()){
                ALUout = ~(ALUinp1 | ALUinp2);
            }else if(prev.getInstruction().funct == Commands.slt.getValue()) {
                ALUzero = (ALUinp1 - ALUinp2 < 0);
            }
        }else if(prev.controlVariables.getAluOP() == 0){
            ALUout = ALUinp1 + ALUinp2;
        }else if(prev.controlVariables.getAluOP() == 1){
            if(prev.getInstruction().funct == Commands.beq.getValue()){
                ALUzero = (ALUinp1 - ALUinp2 == 0);
            }
        }
        next.nonControlVariables.setEXLogicalResult(ALUout);
        next.nonControlVariables.setEXZeroResult(ALUzero);
    }

    @Override
    public void printInfo() {
        System.out.println("Main ALU input: " + ALUinp1 + " " + ALUinp2);
        System.out.println("Main ALU output: " + ALUout + " zero: " + ALUzero);

    }
}
