/**
 * Aligner.java 
 * Created for CSC115 Assignment One.
 */

/**
 * An interface implemented by all Aligner classes.
 */
public interface Aligner {

    /**
     * Given the alignment performed, indicate the position at
     * which the first string character would appear in the
     * aligned result. -1 indicates no alignment possible.
     *
     * @param sequenceNumber index of the sequence string for
     *        which the offset is requested
     * @return number of characters from the start of the alignment
     *         at which the sequence string lines up
     *
     */
    public int getOffset(int sequenceNumber);


    public String getSequence(int sequenceNumber);


    /**
     * Reports the number of mismatches the present in the
     * best-possible alignment of the sequences.
     *
     * @return number of mismatches positions in the current
     *         alignment
     */
    public int getNumErrors();

    /**
     * Performs the actual sequence alignment, but according to
     * the algorithm as implemented.
     */
    public void performAlignment();
}
