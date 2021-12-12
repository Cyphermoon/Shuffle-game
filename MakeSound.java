import javax.sound.midi.*;

public class MakeSound {
    public void probable() throws CypherMoonException{
        throw new CypherMoonException("You Just made a cypher moon exception error");
    }

    public void play(int instrument, int note){
        try {
            Sequencer sequencer = MidiSystem.getSequencer();
            sequencer.open();

            Sequence sequence = new Sequence(Sequence.PPQ, 4);
            Track track = sequence.createTrack();

            // ShortMessage first = new ShortMessage();
            // first.setMessage(192, 1, instrument, 0);
            // MidiEvent changeInstrument = new MidiEvent(first, 1);
            // track.add(changeInstrument);

            ShortMessage first = new ShortMessage();
            first.setMessage(192, 1, instrument, 100);
            MidiEvent changeInstrument = new MidiEvent(first, 1);
            track.add(changeInstrument);

            ShortMessage a = new ShortMessage();
            a.setMessage(144, 1, note, 100);
            MidiEvent noteOn = new MidiEvent(a, 1);
            track.add(noteOn);

            ShortMessage b = new ShortMessage();
            b.setMessage(128, 1, note, 100);
            MidiEvent noteOf = new MidiEvent(b, 3);
            track.add(noteOf);

            sequencer.setSequence(sequence);
            sequencer.start();
            
        } catch (MidiUnavailableException ex) {
            System.out.println(ex.getMessage());
        } 
        catch(InvalidMidiDataException ex){
            ex.printStackTrace();
        }
    }

    // public static void main(String[] args){
    //     System.out.println("We are about to get today started the right way");
    //     ExceptionHandling eHandling = new ExceptionHandling();
    //     if(args.length < 2){
    //         System.out.println("Do not forget about the instrument or note");
    //     }else{
    //         int instrument = Integer.parseInt(args[0]);
    //         int note = Integer.parseInt(args[1]);
    //         eHandling.play(instrument, note);
    //     }
    //}
}

class CypherMoonException extends Exception{

    public CypherMoonException(){
        this("This is a cypher moon exception error");
    }

    public CypherMoonException(String message){
        super(message);
    }
}
