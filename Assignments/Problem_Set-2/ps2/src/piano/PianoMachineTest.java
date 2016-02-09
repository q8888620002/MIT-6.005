package piano;

import static org.junit.Assert.*;

import java.util.ArrayList;

import javax.sound.midi.MidiUnavailableException;

import midi.Instrument;
import midi.Midi;
import music.NoteEvent;
import music.Pitch;

import org.junit.Test;

public class PianoMachineTest {
	
    @Test
    public void singleNoteTest() throws MidiUnavailableException {
        String expected = "on(61,PIANO) wait(100) off(61,PIANO)";
        
        PianoMachine pm = new PianoMachine();
        
    	Midi midi = Midi.getInstance();
    	midi.clearHistory();
    	
        pm.beginNote(new Pitch(1));
		Midi.wait(100);
		pm.endNote(new Pitch(1));
		
        assertEquals(expected, midi.history());
    }
    
    @Test
    public void singleRepeatedNoteTest() throws MidiUnavailableException {
        String expected = "on(61,PIANO) wait(0) on(61,PIANO) wait(100) off(61,PIANO)";
        
        PianoMachine pm = new PianoMachine();
        
    	Midi midi = Midi.getInstance();
    	midi.clearHistory();
    	
        pm.beginNote(new Pitch(1));
        Midi.wait(0);
        pm.beginNote(new Pitch(1));
		Midi.wait(100);
		pm.endNote(new Pitch(1));
		
        assertEquals(expected, midi.history());
    }
    
    @Test
    public void twoNotesTest() throws MidiUnavailableException {
    	String expected =
    		"on(71,PIANO) wait(0) on(65,PIANO) wait(100) off(65,PIANO) wait(0) off(71,PIANO)";
    	
    	PianoMachine pm = new PianoMachine();
    	
    	Midi midi = Midi.getInstance();
    	midi.clearHistory();
    	
    	pm.beginNote(new Pitch(11));
    	Midi.wait(0);
    	pm.beginNote(new Pitch(5));
    	Midi.wait(100);
    	pm.endNote(new Pitch(5));
    	Midi.wait(0);
    	pm.endNote(new Pitch(11));
    	
    	assertEquals(expected, midi.history());
    }
    
    @Test
    public void twoRepeatedNotesTest() throws MidiUnavailableException {
    	String expected1 = "on(71,PIANO) wait(0) on(65,PIANO) wait(0) on(71,PIANO) "+
    			"wait(0) on(65,PIANO) wait(100) off(65,PIANO) wait(0) off(71,PIANO)";
    	
    	PianoMachine pm = new PianoMachine();
    	
    	Midi midi = Midi.getInstance();
    	midi.clearHistory();
    	
    	pm.beginNote(new Pitch(11));
    	Midi.wait(0);
    	pm.beginNote(new Pitch(5));
    	Midi.wait(0);
    	pm.beginNote(new Pitch(11));
    	Midi.wait(0);
    	pm.beginNote(new Pitch(5));
    	Midi.wait(100);
    	pm.endNote(new Pitch(5));
    	Midi.wait(0);
    	pm.endNote(new Pitch(11));
    	
    	assertEquals(expected1, midi.history());
    }
    
    @Test
    public void switchToBrightPianoTest() throws MidiUnavailableException {
    	String expected2 = "on(61,BRIGHT_PIANO) wait(100) off(61,BRIGHT_PIANO)";
    	
    	PianoMachine pm = new PianoMachine();
    	
    	Midi midi = Midi.getInstance();
    	midi.clearHistory();
    	
        pm.changeInstrument();
        
        pm.beginNote(new Pitch(1));
		Midi.wait(100);
		pm.endNote(new Pitch(1));
		
        assertEquals(expected2, midi.history());
    }
    
    @Test
    public void switchToElectricGrandTest() throws MidiUnavailableException {
    	String expected = "on(61,ELECTRIC_GRAND) wait(100) off(61,ELECTRIC_GRAND)";
    	
    	PianoMachine pm = new PianoMachine();
    	
    	Midi midi = Midi.getInstance();
    	
        pm.changeInstrument();
        pm.changeInstrument();
        midi.clearHistory();
        
        pm.beginNote(new Pitch(1));
		Midi.wait(100);
		pm.endNote(new Pitch(1));
		
        assertEquals(expected, midi.history());
    }
    
    @Test
    public void switchToMusicBoxTest() throws MidiUnavailableException {
    	String expected = "on(61,MUSIC_BOX) wait(100) off(61,MUSIC_BOX)";
    	
    	PianoMachine pm = new PianoMachine();
    	
    	Midi midi = Midi.getInstance();
    	
    	// switch continuously for 10 times, until the instrument
    	// change to MUSICAL_BOX
    	
        pm.changeInstrument();
        pm.changeInstrument();
        pm.changeInstrument();
        pm.changeInstrument();
        pm.changeInstrument();
        pm.changeInstrument();
        pm.changeInstrument();
        pm.changeInstrument();
        pm.changeInstrument();
        pm.changeInstrument();
        
        midi.clearHistory();
        
        pm.beginNote(new Pitch(1));
		Midi.wait(100);
		pm.endNote(new Pitch(1));
		
        assertEquals(expected, midi.history());
    }
    
    @Test
    public void shiftUpByOneOctaveTest() throws MidiUnavailableException {
    	String expected0 = "on(72,PIANO) wait(100) off(72,PIANO)";
    	String expected1 = "on(83,PIANO) wait(100) off(83,PIANO)";
    	
    	PianoMachine pm = new PianoMachine();
    	
    	Midi midi = Midi.getInstance();
    	
    	pm.shiftUp();
    	midi.clearHistory();
    	
        pm.beginNote(new Pitch(0));
		Midi.wait(100);
		pm.endNote(new Pitch(0));
    	
        assertEquals(expected0, midi.history());
        
        midi.clearHistory();
        
        pm.beginNote(new Pitch(11));
		Midi.wait(100);
		pm.endNote(new Pitch(11));
		
        assertEquals(expected1, midi.history());
    }
    
    @Test
    public void shiftUpByTwoOctavesTest() throws MidiUnavailableException {
    	String expected0 = "on(84,PIANO) wait(100) off(84,PIANO)";
    	String expected1 = "on(95,PIANO) wait(100) off(95,PIANO)";
    	
    	PianoMachine pm = new PianoMachine();
    	
    	Midi midi = Midi.getInstance();
    	
    	pm.shiftUp();
    	pm.shiftUp();
    	midi.clearHistory();
    	
        pm.beginNote(new Pitch(0));
		Midi.wait(100);
		pm.endNote(new Pitch(0));
    	
        assertEquals(expected0, midi.history());
        
        midi.clearHistory();
        
        pm.beginNote(new Pitch(11));
		Midi.wait(100);
		pm.endNote(new Pitch(11));
		
        assertEquals(expected1, midi.history());;
    }
    
    @Test
    public void shiftUpBoundaryTest() throws MidiUnavailableException {
    	String expected0 = "on(84,PIANO) wait(100) off(84,PIANO)";
    	String expected1 = "on(95,PIANO) wait(100) off(95,PIANO)";
    	
    	PianoMachine pm = new PianoMachine();
    	
    	Midi midi = Midi.getInstance();
    	
    	pm.shiftUp();
    	pm.shiftUp();
    	pm.shiftUp();
    	midi.clearHistory();
    	
        pm.beginNote(new Pitch(0));
		Midi.wait(100);
		pm.endNote(new Pitch(0));
    	
        assertEquals(expected0, midi.history());
        
        midi.clearHistory();
        
        pm.beginNote(new Pitch(11));
		Midi.wait(100);
		pm.endNote(new Pitch(11));
		
        assertEquals(expected1, midi.history());;
    }
    
    @Test
    public void shiftDownByOneOctaveTest() throws MidiUnavailableException {
    	String expected0 = "on(48,PIANO) wait(100) off(48,PIANO)";
    	String expected1 = "on(59,PIANO) wait(100) off(59,PIANO)";
    	
    	PianoMachine pm = new PianoMachine();
    	
    	Midi midi = Midi.getInstance();
    	
    	pm.shiftDown();
    	midi.clearHistory();
    	
        pm.beginNote(new Pitch(0));
		Midi.wait(100);
		pm.endNote(new Pitch(0));
    	
        assertEquals(expected0, midi.history());
        
        midi.clearHistory();
        
        pm.beginNote(new Pitch(11));
		Midi.wait(100);
		pm.endNote(new Pitch(11));
		
        assertEquals(expected1, midi.history());
    }
    
    @Test
    public void shiftDownByTwoOctavesTest() throws MidiUnavailableException {
    	String expected0 = "on(36,PIANO) wait(100) off(36,PIANO)";
    	String expected1 = "on(47,PIANO) wait(100) off(47,PIANO)";
    	
    	PianoMachine pm = new PianoMachine();
    	
    	Midi midi = Midi.getInstance();
    	
    	pm.shiftDown();
    	pm.shiftDown();
    	midi.clearHistory();
    	
        pm.beginNote(new Pitch(0));
		Midi.wait(100);
		pm.endNote(new Pitch(0));
    	
        assertEquals(expected0, midi.history());
        
        midi.clearHistory();
        
        pm.beginNote(new Pitch(11));
		Midi.wait(100);
		pm.endNote(new Pitch(11));
		
        assertEquals(expected1, midi.history());;
    }
    
    @Test
    public void shiftDownBoundaryTest() throws MidiUnavailableException {
    	String expected0 = "on(36,PIANO) wait(100) off(36,PIANO)";
    	String expected1 = "on(47,PIANO) wait(100) off(47,PIANO)";
    	
    	PianoMachine pm = new PianoMachine();
    	
    	Midi midi = Midi.getInstance();
    	
    	pm.shiftDown();
    	pm.shiftDown();
    	pm.shiftDown();
    	midi.clearHistory();
    	
        pm.beginNote(new Pitch(0));
		Midi.wait(100);
		pm.endNote(new Pitch(0));
    	
        assertEquals(expected0, midi.history());
        
        midi.clearHistory();
        
        pm.beginNote(new Pitch(11));
		Midi.wait(100);
		pm.endNote(new Pitch(11));
		
        assertEquals(expected1, midi.history());;
    }
    
    @Test
    public void recordNoneTest() throws MidiUnavailableException {
    	
    	PianoMachine pm = new PianoMachine();
    	
    	Midi midi = Midi.getInstance();
    	midi.clearHistory();
    	
    	assertFalse(pm.isRecording);
    	
    	pm.toggleRecording();
    	assertTrue(pm.isRecording);
    	pm.toggleRecording();
    	assertFalse(pm.isRecording);
    	
    	pm.beginNote(new Pitch(0));
		Midi.wait(100);
		pm.endNote(new Pitch(0));
		
		assertEquals(pm.recordedEvents, new ArrayList<NoteEvent>());
    }
    
    @Test
    public void recordOneNoteEventTest() throws MidiUnavailableException {
    	
    	PianoMachine pm = new PianoMachine();
    	
    	Pitch expectedPitch = new Pitch(0);
    	Instrument expectedInstrument = Instrument.PIANO;
    	NoteEvent.Kind expectedBeginNote = NoteEvent.Kind.start;
    	NoteEvent.Kind expectedEndNote = NoteEvent.Kind.stop;
    	
    	Midi midi = Midi.getInstance();
    	midi.clearHistory();
    	
    	assertFalse(pm.isRecording);
    	
    	pm.toggleRecording();
    	assertTrue(pm.isRecording);
    	
    	pm.beginNote(new Pitch(0));
		Midi.wait(100);
		pm.endNote(new Pitch(0));
		
		pm.toggleRecording();
    	assertFalse(pm.isRecording);
		
    	assertEquals(pm.recordedEvents.get(0).getPitch(), expectedPitch);
		assertEquals(pm.recordedEvents.get(0).getInstr(), expectedInstrument);
		assertEquals(pm.recordedEvents.get(0).getKind(), expectedBeginNote);
		
		assertEquals(pm.recordedEvents.get(1).getPitch(), expectedPitch);
		assertEquals(pm.recordedEvents.get(1).getInstr(), expectedInstrument);
		assertEquals(pm.recordedEvents.get(1).getKind(), expectedEndNote);
    }
    
    @Test
    public void recordThreeNoteEventsTest() throws MidiUnavailableException {
    	
    	PianoMachine pm = new PianoMachine();
    	
    	Pitch expectedPitch0 = new Pitch(0);
    	Pitch expectedPitch5 = new Pitch(5);
    	Pitch expectedPitch8 = new Pitch(8);
    	Instrument expectedInstrument = Instrument.PIANO;
    	NoteEvent.Kind expectedBeginNote = NoteEvent.Kind.start;
    	NoteEvent.Kind expectedEndNote = NoteEvent.Kind.stop;
    	
    	Midi midi = Midi.getInstance();
    	midi.clearHistory();
    	
    	assertFalse(pm.isRecording);
    	
    	pm.toggleRecording();
    	assertTrue(pm.isRecording);
    	
    	// the first note event
    	pm.beginNote(new Pitch(0));
		Midi.wait(100);
		pm.endNote(new Pitch(0));
		
		// the first note event
		Midi.wait(10);
		pm.beginNote(new Pitch(5));
		Midi.wait(100);
		pm.endNote(new Pitch(5));
		
		// the third note event
		Midi.wait(10);
		pm.beginNote(new Pitch(8));
		Midi.wait(100);
		pm.endNote(new Pitch(8));
		
		pm.toggleRecording();
    	assertFalse(pm.isRecording);
		
    	assertEquals(pm.recordedEvents.get(0).getPitch(), expectedPitch0);
		assertEquals(pm.recordedEvents.get(0).getInstr(), expectedInstrument);
		assertEquals(pm.recordedEvents.get(0).getKind(), expectedBeginNote);
		
		assertEquals(pm.recordedEvents.get(1).getPitch(), expectedPitch0);
		assertEquals(pm.recordedEvents.get(1).getInstr(), expectedInstrument);
		assertEquals(pm.recordedEvents.get(1).getKind(), expectedEndNote);
		
		assertEquals(pm.recordedEvents.get(2).getPitch(), expectedPitch5);
		assertEquals(pm.recordedEvents.get(2).getInstr(), expectedInstrument);
		assertEquals(pm.recordedEvents.get(2).getKind(), expectedBeginNote);
		
		assertEquals(pm.recordedEvents.get(3).getPitch(), expectedPitch5);
		assertEquals(pm.recordedEvents.get(3).getInstr(), expectedInstrument);
		assertEquals(pm.recordedEvents.get(3).getKind(), expectedEndNote);
		
		assertEquals(pm.recordedEvents.get(4).getPitch(), expectedPitch8);
		assertEquals(pm.recordedEvents.get(4).getInstr(), expectedInstrument);
		assertEquals(pm.recordedEvents.get(4).getKind(), expectedBeginNote);
		
		assertEquals(pm.recordedEvents.get(5).getPitch(), expectedPitch8);
		assertEquals(pm.recordedEvents.get(5).getInstr(), expectedInstrument);
		assertEquals(pm.recordedEvents.get(5).getKind(), expectedEndNote);
    }
    
    @Test
    public void overrideRecordTest() throws MidiUnavailableException {
    	PianoMachine pm = new PianoMachine();
    	
    	Pitch expectedPitch0 = new Pitch(0);
    	Pitch expectedPitch2 = new Pitch(2);
    	Pitch expectedPitch5 = new Pitch(5);
    	Pitch expectedPitch7 = new Pitch(7);
    	Pitch expectedPitch10 = new Pitch(10);
    	Instrument expectedInstrument = Instrument.PIANO;
    	NoteEvent.Kind expectedBeginNote = NoteEvent.Kind.start;
    	NoteEvent.Kind expectedEndNote = NoteEvent.Kind.stop;
    	
    	Midi midi = Midi.getInstance();
    	midi.clearHistory();
    	
    	assertFalse(pm.isRecording);
    	
    	// the initial recording
    	pm.toggleRecording();
    	assertTrue(pm.isRecording);
    	
    	// the first note event
    	pm.beginNote(new Pitch(0));
		Midi.wait(100);
		pm.endNote(new Pitch(0));
		
		// the second note event
		Midi.wait(10);
		pm.beginNote(new Pitch(5));
		Midi.wait(100);
		pm.endNote(new Pitch(5));
		
		pm.toggleRecording();
    	assertFalse(pm.isRecording);
		
    	assertEquals(pm.recordedEvents.get(0).getPitch(), expectedPitch0);
		assertEquals(pm.recordedEvents.get(0).getInstr(), expectedInstrument);
		assertEquals(pm.recordedEvents.get(0).getKind(), expectedBeginNote);
		
		assertEquals(pm.recordedEvents.get(1).getPitch(), expectedPitch0);
		assertEquals(pm.recordedEvents.get(1).getInstr(), expectedInstrument);
		assertEquals(pm.recordedEvents.get(1).getKind(), expectedEndNote);
		
		assertEquals(pm.recordedEvents.get(2).getPitch(), expectedPitch5);
		assertEquals(pm.recordedEvents.get(2).getInstr(), expectedInstrument);
		assertEquals(pm.recordedEvents.get(2).getKind(), expectedBeginNote);
		
		assertEquals(pm.recordedEvents.get(3).getPitch(), expectedPitch5);
		assertEquals(pm.recordedEvents.get(3).getInstr(), expectedInstrument);
		assertEquals(pm.recordedEvents.get(3).getKind(), expectedEndNote);
		
		// the new recording, expected to override the previous one
    	pm.toggleRecording();
    	assertTrue(pm.isRecording);
    	
    	// the first note event
    	pm.beginNote(new Pitch(2));
		Midi.wait(100);
		pm.endNote(new Pitch(2));
		
		// the second note event
		Midi.wait(10);
		pm.beginNote(new Pitch(7));
		Midi.wait(100);
		pm.endNote(new Pitch(7));
		
		// the third note event
		Midi.wait(10);
		pm.beginNote(new Pitch(10));
		Midi.wait(100);
		pm.endNote(new Pitch(10));
		
		pm.toggleRecording();
    	assertFalse(pm.isRecording);
		
    	assertEquals(pm.recordedEvents.get(0).getPitch(), expectedPitch2);
		assertEquals(pm.recordedEvents.get(0).getInstr(), expectedInstrument);
		assertEquals(pm.recordedEvents.get(0).getKind(), expectedBeginNote);
		
		assertEquals(pm.recordedEvents.get(1).getPitch(), expectedPitch2);
		assertEquals(pm.recordedEvents.get(1).getInstr(), expectedInstrument);
		assertEquals(pm.recordedEvents.get(1).getKind(), expectedEndNote);
		
		assertEquals(pm.recordedEvents.get(2).getPitch(), expectedPitch7);
		assertEquals(pm.recordedEvents.get(2).getInstr(), expectedInstrument);
		assertEquals(pm.recordedEvents.get(2).getKind(), expectedBeginNote);
		
		assertEquals(pm.recordedEvents.get(3).getPitch(), expectedPitch7);
		assertEquals(pm.recordedEvents.get(3).getInstr(), expectedInstrument);
		assertEquals(pm.recordedEvents.get(3).getKind(), expectedEndNote);
		
		assertEquals(pm.recordedEvents.get(4).getPitch(), expectedPitch10);
		assertEquals(pm.recordedEvents.get(4).getInstr(), expectedInstrument);
		assertEquals(pm.recordedEvents.get(4).getKind(), expectedBeginNote);
		
		assertEquals(pm.recordedEvents.get(5).getPitch(), expectedPitch10);
		assertEquals(pm.recordedEvents.get(5).getInstr(), expectedInstrument);
		assertEquals(pm.recordedEvents.get(5).getKind(), expectedEndNote);
    }
    
    @Test
    public void playbackNoRecordingTest () throws MidiUnavailableException {
    	
    	String expectedBehavior = "";
    	
    	PianoMachine pm = new PianoMachine();
    	
    	Midi midi = Midi.getInstance();
    	midi.clearHistory();
    	
    	assertFalse(pm.isRecording);
    	
    	pm.toggleRecording();
    	assertTrue(pm.isRecording);
    	pm.toggleRecording();
    	assertFalse(pm.isRecording);
    	
    	pm.beginNote(new Pitch(0));
		Midi.wait(100);
		pm.endNote(new Pitch(0));
		
		midi.clearHistory();
		pm.playback();
		assertEquals(expectedBehavior, midi.history());
    }
    
    @Test
    public void playbackOneNoteEventTest() throws MidiUnavailableException {
    	
    	String expectedBehavior = "on(60,PIANO) wait(100) off(60,PIANO)";
    	
    	PianoMachine pm = new PianoMachine();
    	
    	Midi midi = Midi.getInstance();
    	midi.clearHistory();
    	
    	assertFalse(pm.isRecording);
    	
    	pm.toggleRecording();
    	assertTrue(pm.isRecording);
    	
    	pm.beginNote(new Pitch(0));
		Midi.wait(100);
		pm.endNote(new Pitch(0));
		
		pm.toggleRecording();
    	assertFalse(pm.isRecording);
		
		midi.clearHistory();
		pm.playback();
		assertEquals(expectedBehavior, midi.history());
    }
    
    @Test
    public void playbackThreeNoteEventsTest() throws MidiUnavailableException {
    	
    	PianoMachine pm = new PianoMachine();
    	
    	String expectedBehavior = "on(60,PIANO) wait(100) off(60,PIANO) wait(20) " +
    			"on(64,PIANO) wait(100) off(64,PIANO) wait(20) " +
    			"on(69,PIANO) wait(100) off(69,PIANO)";
    	
    	Midi midi = Midi.getInstance();
    	midi.clearHistory();
    	
    	assertFalse(pm.isRecording);
    	pm.toggleRecording();
    	assertTrue(pm.isRecording);

        pm.beginNote(new Pitch(0));
		Midi.wait(100);
		pm.endNote(new Pitch(0));
		Midi.wait(20);
		pm.beginNote(new Pitch(4));
		Midi.wait(100);
		pm.endNote(new Pitch(4));
		Midi.wait(20);
		pm.beginNote(new Pitch(9));
		Midi.wait(100);
		pm.endNote(new Pitch(9));
		
		pm.toggleRecording();
		assertFalse(pm.isRecording);
		
		midi.clearHistory();
		pm.playback();
		assertEquals(expectedBehavior, midi.history());
    }
}
