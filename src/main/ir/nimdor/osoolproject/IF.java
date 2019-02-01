package ir.nimdor.osoolproject;
// DORSA
import java.nio.file.Files;
import java.nio.file.Paths;

public class IF extends Component {
    PipeReg prev , next  ;
    int pc  ;
    public IF  ()  {
        pc = 0  ;
        prev = null ;
        next = null ;
    }
    @Override
    public void run(PipeReg prev , PipeReg next) {
        this.prev =  prev ;
        this.next = next ;
        pc = get_value ();
        String instruction_string =  get_instruction(pc);
        Instruction instruction = get_binary_instruction(instruction_string);
        next.setInstruction(instruction);
    }

    // decide the value of pc --> MUX
    public int get_value(){
        // add a multiplexor here
        return pc + 1 ;
    }

    // read instruction of the indexed line from the file  --> input line
    private String get_instruction(int pcc) {   // ( done )
        String line ="null";
        try {
            line = Files.readAllLines(Paths.get("file.txt")).get(pcc);
        } catch (Exception e) {

            e.printStackTrace();
        }
        return line;
    }

    // translate string instruction to binary instruction  --> instruction memory
    private Instruction get_binary_instruction ( String instruct ) { // ( done )
        Instruction instruction = new Instruction(instruct);
        return instruction ;
    }
}
