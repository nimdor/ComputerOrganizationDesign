package ir.nimdor.osoolproject;


import org.junit.*;

public class IFTest {

    PipeLine pipeLine;

    @Before
    public void setUp() throws Exception {
        pipeLine = Main.setupPipeLine();
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void run() {
        pipeLine.getIFcomponent().run(pipeLine.getIFReg(), pipeLine.getIDReg());
        System.err.println(pipeLine.getIFcomponent().pc);

    }

    @Test
    public void get_value() {
    }
}