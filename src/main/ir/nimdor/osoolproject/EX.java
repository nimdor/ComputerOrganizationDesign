package ir.nimdor.osoolproject;

public class EX extends Component {

    public EX() {
    }

    @Override
    public void run(PipeReg prev, PipeReg next) {
        int ALUinp1 = prev.getNonControlVariables().getRs(), ALUinp2;
        if(prev.getControlVariables().isAluSrc()){
            ALUinp2 = prev.getNonControlVariables().getRd();
        }else{
            ALUinp2 = prev.getNonControlVariables().getRt();
        }
        if(prev.controlVariables.getAluOP() == 0){
            if(prev.getInstruction().funct == Commands.add.getValue()){
                next.nonControlVariables.setEXLogicalResult(ALUinp1 + ALUinp2);
            }else if(prev.getInstruction().funct == Commands.sub.getValue()){
                next.nonControlVariables.setEXLogicalResult(ALUinp1 - ALUinp2);
            }else if(prev.getInstruction().funct == Commands.and.getValue()){
                next.nonControlVariables.setEXLogicalResult(ALUinp1 & ALUinp2);
            }else if(prev.getInstruction().funct == Commands.or.getValue()){
                next.nonControlVariables.setEXLogicalResult(ALUinp1 | ALUinp2);
            }else if(prev.getInstruction().funct == Commands.nor.getValue()){
                next.nonControlVariables.setEXLogicalResult(~(ALUinp1 | ALUinp2));
            }
        }else if(prev.controlVariables.getAluOP() == 1){
            next.nonControlVariables.setEXLogicalResult(ALUinp1 + ALUinp2);
        }else if(prev.controlVariables.getAluOP() == 2){
            if(prev.getInstruction().funct == Commands.slt.getValue()){
                next.nonControlVariables.setEXZeroResult(ALUinp1 - ALUinp2 < 0);
            }else if(prev.getInstruction().funct == Commands.beq.getValue()){
                next.nonControlVariables.setEXZeroResult(ALUinp1 - ALUinp2 == 0);
            }
        }
    }
}
