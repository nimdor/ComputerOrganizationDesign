package main.ir.nimdor.osoolproject;

public class MEM extends Component {
    int[] memory = new int[Configs.MEMORY_SIZE];

    @Override
    public void run(PipeReg prev, PipeReg next) {
        if (prev.controlVariables.isMemWrite()){
            memory[prev.nonControlVariables.getRd() + prev.getNonControlVariables().getRt()] =
                    prev.getNonControlVariables().getRs();
        }else if(prev.controlVariables.isMemRead()){
            next.nonControlVariables
                    .setMEMResult(memory[prev.nonControlVariables.getRd() + prev.getNonControlVariables().getRt()]);
        }
    }
}
