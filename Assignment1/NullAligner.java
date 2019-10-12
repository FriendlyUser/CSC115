/**
 * NullAligner.java 
 * Created for CSC115 Assignment One.
 *
 * This aligner does nothing, really, but it is really meant to
 * act as code to permit SeqAligner.java to compile.
 */

public class NullAligner implements Aligner {
 
    public NullAligner(String sequence[]) { }

	public static void main(String args[]) {
	}
    public void performAlignment() { }


    public int getOffset(int sequenceNumber) {
        return -1;
    }


    public String getSequence(int sequenceNumber) {
        return "";
    }


    public int getNumErrors() {
        return 0;
    }
}
