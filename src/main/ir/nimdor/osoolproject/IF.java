package ir.nimdor.osoolproject;

// DORSA
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

public class IF extends Component {
    PipeReg prev , next  ;
    int pc  ;
    String instruction_string ;
    Instruction instruction, pending_instruction  ;
    ArrayList<Tag> tags  ;
    int stall = 0 ;
    boolean stall_condition  ;

    public IF  ()  {
        tags = new ArrayList<>() ;
        pc = 0  ;
        prev = null ;
        next = null ;
        pending_instruction = null ;
        stall_condition=false
        create_list ();
    }


    @Override
    public void run(PipeReg prev , PipeReg next) {
        this.prev =  prev ;
        this.next = next ;

        if ( stall_condition ){
            handle() ;
            return ;
        }

        pc = get_value ();
        System.out.println("#pc value : " + pc );
        instruction_string =  get_instruction(pc);
        instruction = get_binary_insturction(instruction_string);
        instruction.print();

        if (instruction.getOp() == 4 ) {
            set_stall_condition();
            run (prev ,next) ;
            return ;

        }
        next.setInstruction(instruction);
        next.getNonControlVariables().setPc(pc);

    }

    void set_stall_condition (){
        stall_condition = true;
        stall = 3  ;
    }
    void handle(){
        if ( stall == 0 ){
            stall_condition = false ;
            next.setInstruction(pending_instruction);
            pc += 1 ;
            next.getNonControlVariables().setPc( pc );
            return ;

        }
        stall -- ;
        next.controlVariables.setStall(True);
        return ;

    }

    @Override
    public void printInfo(){

        System.out.println("IF information : ");
        System.out.println("Clock : " + ( pc * 4 )  ) ;    // starting pc or ending pc ?
        System.out.println("Instruction : "  );
        instruction.print();



    }
    private void create_list ( ){




        // The name of the file to open.
        String fileName = "file.txt";

        // This will reference one line at a time
        String line = null;
        try {
            // FileReader reads text files in the default encoding.
            FileReader fileReader =
                    new FileReader(fileName);

            // Always wrap FileReader in BufferedReader.
            BufferedReader bufferedReader =
                    new BufferedReader(fileReader);
            int index = 0;
            while((line = bufferedReader.readLine()) != null) {

                if (line.contains(":")){
                    tags.add(new Tag(line,index));
                }

            index ++  ;
            }

            // Always close files.
            bufferedReader.close();
        }
        catch(FileNotFoundException ex) {
            System.out.println(
                    "Unable to open file '" +
                            fileName + "'");
        }
        catch(IOException ex) {
            System.out.println(
                    "Error reading file '"
                            + fileName + "'");
            // Or we could just do this:
            // ex.printStackTrace();
        }

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
        Instruction instruction = new Instruction(instruct , tags);
        return instruction ;
    }



}
