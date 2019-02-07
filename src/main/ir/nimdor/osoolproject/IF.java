package ir.nimdor.osoolproject;

// DORSA
import java.nio.file.Files;
import java.nio.file.Paths;

public class IF extends Component {
    PipeReg prev , next  ;
    int pc  ;
    String instruction_string ;
    Instruction instruction;
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

        instruction_string =  get_instruction(pc);

        instruction = get_binary_insturction(instruction_string);
        pc += 1 ;
        next.getNonControlVariables().setPc(pc);
        next.setInstruction(instruction);
    }

    @Override
    public void printInfo(){

        System.out.println("IF information : ");

        System.out.println("Clock : " + ( pc * 4 )  ) ;    // starting pc or ending pc ?
        System.out.println("Instruction : "  );
        instruction.print();



    }

    // decide the value of pc --> MUX    ----> from prev
    public int get_value(){
        if ( prev.isPc_control() )
            pc = prev.getNonControlVariables().getPc() ;

        return pc  ;
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
    private Instruction get_binary_insturction(String instruct ) { // ( done )
        Instruction instruction = new Instruction(instruct);
        return instruction ;
    }
}
