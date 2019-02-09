package ir.nimdor.osoolproject;

public class Commons {

    public static boolean writeToReg (int[] registers, int index , int data , boolean regdst)  {
        if ( regdst == false ){
            return false;
        }
        registers[index] = data;
        return true;
    }
    public static void forwardPipeReg(PipeReg prev, PipeReg next) {
        next.setControlVariables(prev.getControlVariables());
        next.setNonControlVariables(prev.getNonControlVariables());
        next.setInstruction(prev.getInstruction());
        next.setPc_control(prev.isPc_control());
    }
}
