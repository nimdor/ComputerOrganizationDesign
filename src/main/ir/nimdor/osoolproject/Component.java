package ir.nimdor.osoolproject;

public abstract class Component {
    abstract public void run(PipeReg prev, PipeReg next);

    abstract public void printInfo();
}
