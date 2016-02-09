package piano;

import java.util.ArrayList;
import java.util.List;
import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.sound.midi.MidiUnavailableException;

import midi.Midi;
import music.NoteEvent;
import music.Pitch;
import music.PitchLevel;

public class PianoMachine {
	
	private Midi midi;
	private midi.Instrument instrument = Midi.DEFAULT_INSTRUMENT;
	
	// level of pitch (in octaves) compared to the starting note pitch
	private PitchLevel pitchLevel = PitchLevel.DEFAULT_LEVEL;
	public boolean isRecording = false;
	public List<NoteEvent> recordedEvents = new ArrayList<NoteEvent>();
	
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
    
    /**
     * Turn an note event associated with the specified pitch.
     * @param rawPitch: the frequency of a musical note, required to
     * 					be among the set {0, 1, 2, ..., 10, 11}
     */
    public void beginNote(Pitch rawPitch) {
    	long time = getCurrentTime();
    	NoteEvent event;
    	event = new NoteEvent(rawPitch, time,
    						  instrument, NoteEvent.Kind.start);
    	if (isRecording) {
    		recordedEvents.add(event);
    	}
    	Pitch modulatedPitch = modulatePitch(rawPitch);
    	midi.beginNote(modulatedPitch.toMidiFrequency(), instrument);
    }
    
    /**
     * Turn off an note event associated with the specified pitch.
     * @param rawPitch: the frequency of a musical note, required to
     * 					be among the set {0, 1, 2, ..., 10, 11}
     */
    public void endNote(Pitch rawPitch) {
    	long time = getCurrentTime();
    	NoteEvent event;
    	event = new NoteEvent(rawPitch, time,
    						  instrument, NoteEvent.Kind.stop);
    	if (isRecording) {
    		recordedEvents.add(event);
    	}
    	Pitch modulatedPitch = modulatePitch(rawPitch);
    	midi.endNote(modulatedPitch.toMidiFrequency(), instrument);
    }
    
    /**
     * Switch the instrument mode to the next instrument among list
     * of musical instruments. If the current instrument is the last
     * one in the musical instrument list, switch back to the first
     * instrument.
     */
    public void changeInstrument() {
    	instrument = instrument.next();
    }
    
    /**
     * Shift the notes that the keys play up, respectively, by one
     * octave (12 semitones).
     * Any note can be shifted up at most by two octaves, from its
     * starting pitch.
     */
    public void shiftUp() {
    	switch (pitchLevel) {
    	case TWO_OCTAVES_BELOW:
    		pitchLevel = PitchLevel.ONE_OCTAVE_BELOW;
    		return;
    	case ONE_OCTAVE_BELOW:
    		pitchLevel = PitchLevel.DEFAULT_LEVEL;
    		return;
    	case DEFAULT_LEVEL:
    		pitchLevel = PitchLevel.ONE_OCTAVE_ABOVE;
    		return;
    	case ONE_OCTAVE_ABOVE:
    		pitchLevel = PitchLevel.TWO_OCTAVES_ABOVE;
    		return;
    	case TWO_OCTAVES_ABOVE:
    		return;
    	}
    }
    
    /**
     * Shift the notes that the keys play down, respectively, by one
     * octave (12 semitones).
     * Any note can be shifted down at most by two octaves, from its
     * starting pitch.
     */
    public void shiftDown() {
    	switch (pitchLevel) {
    	case TWO_OCTAVES_BELOW:
    		return;
    	case ONE_OCTAVE_BELOW:
    		pitchLevel = PitchLevel.TWO_OCTAVES_BELOW;
    		return;
    	case DEFAULT_LEVEL:
    		pitchLevel = PitchLevel.ONE_OCTAVE_BELOW;
    		return;
    	case ONE_OCTAVE_ABOVE:
    		pitchLevel = PitchLevel.DEFAULT_LEVEL;
    		return;
    	case TWO_OCTAVES_ABOVE:
    		pitchLevel = PitchLevel.ONE_OCTAVE_ABOVE;
    		return;
    	}
    }
    
    /**
     * Toggle between recording on and off. The previous
     * recording will be overridden, if a new recording is
     * made.
     * @return true if the piano machine begins recording, or
     * 		   false if the piano machine ends recording.
     */
    public boolean toggleRecording() {
    	if (isRecording) {	// the piano machine is recording before, terminate recording
    		isRecording = false;
    	} else {	// the piano machine is not recording before, begin recording
    		isRecording = true;
    		recordedEvents = new ArrayList<NoteEvent>();
    	}
    	return isRecording;
    }
    
    /**
     * Playback sequences of recorded notes.
     */
    protected void playback() {
    	if (recordedEvents.isEmpty()) {
    		return;
    	} else {
    		int eventAmount = recordedEvents.size();
    		if (eventAmount == 1) {
    			NoteEvent event = recordedEvents.get(0);
    			executeNoteEvent(event);
    		} else {
    			long[] delayTimeArray = new long[eventAmount-1];
    			delayTimeArray = getDelayTimeArray(recordedEvents);
    			for (int i = 0; i < eventAmount-1; i = i + 1) {
    				// play the all but the last note event
    				NoteEvent event = recordedEvents.get(i);
    				executeNoteEvent(event);
    				Midi.wait((int)delayTimeArray[i]);
    			}
    			// Don't forget the last note event
    			NoteEvent lastEvent = recordedEvents.get(eventAmount-1);
    			executeNoteEvent(lastEvent);
    		}
    	}
    }
    
    /**
     * Modulate the rawPitch to an appropriate modulatedPitch according to the
     * piano machine's pitch level (in octaves).
     * @param rawPitch standard pitch of a musical note
     * @return modulatedPitch the resulting pitch after modulation
     */
    private Pitch modulatePitch(Pitch rawPitch) {
    	Pitch modulatedPitch = rawPitch;
    	switch (pitchLevel) {
    	case DEFAULT_LEVEL:
    		modulatedPitch = rawPitch;
    		break;
    	case ONE_OCTAVE_BELOW:
    		modulatedPitch = rawPitch.transpose((Pitch.OCTAVE) * (-1));
    		break;
    	case TWO_OCTAVES_BELOW:
    		modulatedPitch =
    			rawPitch.transpose((Pitch.OCTAVE) * (-1)).transpose((Pitch.OCTAVE) * (-1));
    		break;
    	case ONE_OCTAVE_ABOVE:
    		modulatedPitch = rawPitch.transpose(Pitch.OCTAVE);
    		break;
    	case TWO_OCTAVES_ABOVE:
    		modulatedPitch =
    			rawPitch.transpose(Pitch.OCTAVE).transpose(Pitch.OCTAVE);
    		break;
    	}
    	return modulatedPitch;
    }
    
    /**
     * Return the system's current time in millisecond.
     */
    private long getCurrentTime() {
    	Calendar cal = new GregorianCalendar();
    	return cal.getTimeInMillis();
    }
    
    /**
     * Play a note, invoked by playback
     */
    protected void executeNoteEvent(NoteEvent event) {
    	if (event.getKind() == NoteEvent.Kind.start) {
			beginNote(event.getPitch());
		} else {
			endNote(event.getPitch());
		}
    }
    
    /**
     * Returns an array of delay time between note events.
     */
    protected long[] getDelayTimeArray(List<NoteEvent> eventList) {
    	int eventAmount = eventList.size();
    	long[] delayTimeArray = new long[eventAmount-1];
    	for (int i = 0; i < eventAmount-1; i = i + 1) {
    		long elapsedTime =
    			(long) Math.floor((double)(eventList.get(i+1).getTime() - eventList.get(i).getTime()) / 10.0);
    		delayTimeArray[i] = elapsedTime;
    	}
    	return delayTimeArray;
    }
}
