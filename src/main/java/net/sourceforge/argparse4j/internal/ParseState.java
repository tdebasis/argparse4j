package net.sourceforge.argparse4j.internal;

import java.util.ArrayList;
import java.util.List;

import net.sourceforge.argparse4j.inf.ArgumentParserException;

public class ParseState {
    /**
     * Array of arguments.
     */
    public String[] args;
    /**
     * Index in args array, which points next argument to process.
     */
    public int index;
    /**
     * Index in {@link #args} array, which points to the last argument read from
     * file. -1 means that no argument is read from file. If arguments are read
     * from file recursively (e.g., argument file is found in argument file),
     * this value is properly extended to point to the actual last argument by
     * position.
     */
    public int lastFromFileArgIndex;
    /**
     * True if special argument "--" is found and consumed.
     */
    public boolean consumedSeparator;
    /**
     * True if negative number like flag is registered in the parser.
     */
    public boolean negNumFlag;

    /**
     * Deferred exception encountered while parsing. This will be thrown after
     * parsing completed and no other exception was thrown.
     */
    public ArgumentParserException deferredException;

    /**
     * Index of positional argument (Argument object) we are currently
     * processing.
     */
    public int posargIndex;

    /**
     * The number of arguments (well, parameters) consumed for the current
     * positional Argument object.
     */
    public int posargConsumed;

    /**
     * Accumulated positional arguments we have seen so far.
     */
    public List<String> posargArgs;

    public ParseState(String args[], int index, boolean negNumFlag) {
        this.args = args;
        this.index = index;
        this.lastFromFileArgIndex = -1;
        this.negNumFlag = negNumFlag;
        this.deferredException = null;
        this.posargIndex = 0;
        this.posargConsumed = 0;
        this.posargArgs = new ArrayList<String>();
    }

    void resetArgs(String args[]) {
        this.args = args;
        this.index = 0;
    }

    String getArg() {
        return args[index];
    }

    boolean isArgAvail() {
        return index < args.length;
    }

    void resetPosargs() {
        this.posargIndex = 0;
        this.posargConsumed = 0;
        // create new list, so that we can use sublist method while processing
        // arguments.
        this.posargArgs = new ArrayList<String>();
    }
}
