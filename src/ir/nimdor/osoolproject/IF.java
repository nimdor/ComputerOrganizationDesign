package ir.nimdor.osoolproject;
// DORSA
import java.nio.file.Files;
import java.nio.file.Paths;

public class IF extends Component {
    PipeReg prev , next  ;
    int pc ;
    @Override
    public void run(PipeReg prev , PipeReg next) {
        this.prev =  prev ;
        this.next = next ;
        pc = get_value ();
        String instruction_string =  get_instruction(pc);
        Instruction instruction = get_binary_instruction(instruction_string);

    }

    // decide the value of pc --> MUX
    public int get_value(){

        return pc + 1 ;
    }

    // read instruction of the indexed line from the file  --> input line
    private String get_instruction(int pcc) {
        String line ="null";
    try {
         line = Files.readAllLines(Paths.get("file.txt")).get(pcc);

    }catch (Exception e) {

        e.printStackTrace();
    }
        return line ;


    }

    // translate string instruction to binary instruction  --> instruction memory
    private Instruction get_binary_instruction ( String instuct ) {

        return null ;
    }
}
