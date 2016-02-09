package piano;

import java.util.ArrayList;
import java.util.Date;

import javax.sound.midi.MidiUnavailableException;

import midi.Instrument;
import midi.Midi;
import music.NoteEvent;
import music.Pitch;

public class PianoMachine {
	
	
	private Midi midi;
	private midi.Instrument instr = Midi.DEFAULT_INSTRUMENT;
	private int numOfOctave = 0;
	private int ocatve = Pitch.OCTAVE;
	public boolean recordingMode = false;
	public  ArrayList<NoteEvent> noteEvents = new ArrayList<NoteEvent>();
	public static boolean playBackOn = false;
	
	/**
	 * constructor for PianoMachine.
	 * 
	 * initialize midi device and any other state that we're storing.
	 */
    public PianoMachine() {
    	try {
            midi = Midi.getInstance();
        } catch (MidiUnavailableException e1) {
            System.err.println("Could not initialize midi device");
            e1.printStackTrace();
            return;
        }
    }
    
    //TODO write method spec
    /**
     * Turn on an note event associated with specific pitch
     * @param rawPitch: the frequency of a music note,
     *        an integer ranging from 1,2,3.....,10,11
     */
    
    
    
    public void beginNote(Pitch rawPitch) {
    
    	int pitchValue = rawPitch.hashCode() + (numOfOctave*ocatve);
    	midi.beginNote(new Pitch(pitchValue).toMidiFrequency(), instr);
    	
    	if(recordingMode){
    		long time = (new Date()).getTime();
    		Pitch newPitch = new Pitch(pitchValue);
    		noteEvents.add(new NoteEvent(newPitch,time,instr,NoteEvent.Kind.start));
    	}
    	//TODO implement for question 1

    }
    /**
     * Turn off an note event associated with specific event
     * @param rawPitch: the frequency of a music note,
     *        an integer ranging from 1,2,3.....,10,11
     */
    
    //TODO write method spec
    public void endNote(Pitch rawPitch) {
    
    	int pitchValue = rawPitch.hashCode() + (numOfOctave*ocatve);
    	midi.endNote(new Pitch(pitchValue).toMidiFrequency(), instr);
    	Pitch newPitch = new Pitch(pitchValue);
    	
    	//TODO implement for question 1
    	
    	if(recordingMode){
    		long time = (new Date()).getTime();
    		noteEvents.add(new NoteEvent(newPitch,time,instr, NoteEvent.Kind.stop));
    	}
    }
    
    //TODO write method spec
    /**
     * when "I" key was pressed, switch our instrument mode to a next instrument 
     *  or back to the start among the instrument lists if we're at the end 
     * 
     */
    public void changeInstrument() {
    	instr = instr.next();
       	//TODO: implement for question 2
    }
    
    /**
     * Shifting the keys play up by an octave (12 semitones), 2 octaves maximum
     * 
     */
    public void shiftUp() {
    	numOfOctave++;
    	if(numOfOctave >= 2){
    		 numOfOctave = 2;
    	}
    	//TODO: implement for question 3
    }
    /*
     * Shifting the keys play down by an octave, 2 octaves maximum
     * 
     */
    public void shiftDown() {
    	numOfOctave--;
    	if(numOfOctave <= -2){
   		 numOfOctave = -2;
   	}
    	//TODO: implement for question 3
    }
    
    //TODO write method spec
    /**
     * Turn on or off recording mode.If it is on, it will record the sequences of note events
     *  and preserving the rhythm they were played with
     * @return true if the recording mode is off initially/false if the recording mode is on
     */
    public boolean toggleRecording() {
    	if(!recordingMode){
    		recordingMode = true;
    		noteEvents = new ArrayList<NoteEvent>();
    		return recordingMode;
    	}else{
    		recordingMode = false;
        	return recordingMode;
    	}
    	
    }
    
    
    /**
     * Turn on or off the playback mode. If the mode is on, 
     * it plays the notes according to sequences and rhythm of the recording. 
     * 
     */
    
  //TODO: implement for question 4
    protected void playback() {    	
    	 playBackOn = true;
    	long prevEventTime = -1;
    	
    	for (NoteEvent n: noteEvents){
    	    music.NoteEvent.Kind noteType = n.getKind();
    		Pitch pitchValue = n.getPitch();
    		Instrument instrument = n.getInstr();
    		
    		if(prevEventTime > 0 ){
    			int eventInterval = (int) ((n.getTime() - prevEventTime)/10);
    			Midi.wait(eventInterval);
    		}
    		
    		prevEventTime = n.getTime();
    		
    		switch(noteType){
    		case start:
    			midi.beginNote(pitchValue.toMidiFrequency(), instrument);
    			break;
    		case stop:
    			midi.endNote(pitchValue.toMidiFrequency(), instrument);
    			break;
    		}
    	}
    	
    	playBackOn = false;
    }
}
