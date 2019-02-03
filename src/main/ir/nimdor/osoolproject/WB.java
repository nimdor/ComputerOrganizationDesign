package ir.nimdor.osoolproject;

public class WB extends Component {

    int[] memory;

    public WB(int[] memory) {
        this.memory = memory;
    }

    @Override
    public void run(PipeReg prev, PipeReg next) {
        if (prev.controlVariables.isMemToReg()){
            Commons.writeToReg(memory, prev.nonControlVariables.getRd(), prev.nonControlVariables.getMEMResult(),
                    prev.controlVariables.isRegWrite());
        }else{
            Commons.writeToReg(memory, prev.nonControlVariables.getRd(), prev.nonControlVariables.getEXLogicalResult(),
                    prev.controlVariables.isRegWrite());
        }
    }

    public boolean write ( int index , int data , boolean regdst )  {
        if ( regdst == false ) return false ;
        memory[index] = data ;
        return true ;
    }
}
