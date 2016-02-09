package piano;

import static org.junit.Assert.*;

import javax.sound.midi.MidiUnavailableException;

import midi.Midi;
import music.Pitch;

import org.junit.Test;

public class PianoMachineTest {
	
	

    @Test
    public void singleNoteTest() throws MidiUnavailableException {
    	
    	PianoMachine pm = new PianoMachine();
        String expected0 = "on(61,PIANO) wait(100) off(61,PIANO)";
        
    	Midi midi = Midi.getInstance();

    	midi.clearHistory();
    	
        pm.beginNote(new Pitch(1));
		Midi.wait(100);
		pm.endNote(new Pitch(1));

        System.out.println(midi.history());
        assertEquals(expected0,midi.history());
    }
    // wait for 100 millisecond then end a note event
    @Test 
    public void singleNoteTest01() throws MidiUnavailableException{
    	PianoMachine pm = new PianoMachine();
    	
    	String expectedOutput = "on(71,PIANO) wait(100) off(71,PIANO)";
    	
    	Midi midi = Midi.getInstance();
    	
    	midi.clearHistory();
    	
    	pm.beginNote(new Pitch('B'));
    	Midi.wait(100);
    	pm.endNote(new Pitch('B'));
    	
    	System.out.println(midi.history());
    	assertEquals(expectedOutput, midi.history());
    }
    // end note event immediately after a note event begining
    
    @Test 
    public void singleNoteTest02() throws MidiUnavailableException{
    	PianoMachine pm = new PianoMachine();
    	
    	String expectedOutput = "on(71,PIANO) wait(0) off(71,PIANO)";
    	
    	Midi midi = Midi.getInstance();
    	
    	midi.clearHistory();
    	
    	pm.beginNote(new Pitch('B'));
    	Midi.wait(0);
    	pm.endNote(new Pitch('B'));
    	
    	System.out.println(midi.history());
    	assertEquals(expectedOutput, midi.history());
    }
    // 
    @Test 
    public void MultipleNoteTest01() throws MidiUnavailableException{
    	PianoMachine pMachine = new PianoMachine();
    	String expected = "on(71,PIANO) wait(0) on(69,PIANO) wait(100) off(71,PIANO)";
    	
    	Midi midi = Midi.getInstance();
    	midi.clearHistory();
    	
    	pMachine.beginNote(new Pitch('B'));
    	Midi.wait(0);
    	pMachine.beginNote(new Pitch('A'));
    	Midi.wait(100);
    	pMachine.endNote(new Pitch('B'));
    	System.out.print(midi.history());
    	assertEquals(expected, midi.history());
    }
    
    @Test
    public void  multipleNoteTest() throws MidiUnavailableException{
    	
    	PianoMachine pm = new PianoMachine();
    	String expectedOutput = "on(62,PIANO) wait(0) on(64,PIANO) "
    			+ "wait(200) on(67,PIANO) wait(100) off(62,PIANO)";
    	
    	Midi midi = Midi.getInstance();
    	
    	midi.clearHistory();
    	
    	pm.beginNote(new Pitch('D'));
    	Midi.wait(0);
    	pm.beginNote(new Pitch('E'));
    	Midi.wait(200);
    	pm.beginNote(new Pitch('G'));
    	Midi.wait(100);
    	pm.endNote(new Pitch('D'));
    	
    	System.out.println(midi.history());
    	assertEquals(expectedOutput,midi.history());
    }
    
    @Test 
    // change instrument from PIANO to BRIGHT_PIANO
    public void changeInstrumentTets1() throws MidiUnavailableException{
    	PianoMachine pm = new PianoMachine();
    	String output = "on(60,PIANO) wait(100) off(60,BRIGHT_PIANO)";
    	
    	Midi midi = Midi.getInstance();
    	
    	midi.clearHistory();
    	pm.beginNote(new Pitch(0));
    	Midi.wait(100);
    	pm.changeInstrument();
    	pm.endNote(new Pitch(0));
    	
    	System.out.println(midi.history());
    	assertEquals(output, midi.history());
    }
    
    @Test 
    // change instrument from PIANO to BRIGHT_PIANO
    public void changeInstrumentTets2() throws MidiUnavailableException{
    	PianoMachine pm = new PianoMachine();
    	String output = "on(60,BRIGHT_PIANO) wait(100) off(60,BRIGHT_PIANO)";
    	
    	Midi midi = Midi.getInstance();
    	
    	midi.clearHistory();
    	
    	pm.changeInstrument();
    	pm.beginNote(new Pitch(0));
    	Midi.wait(100);
    	pm.endNote(new Pitch(0));
    	
    	System.out.println(midi.history());
    	assertEquals(output, midi.history());
    }
    
    
    @Test 
    // change instrument 2 times from PIANO to ELECTRIC_PIANO
    public void changeInstrumentTets3() throws MidiUnavailableException{
    	PianoMachine pm = new PianoMachine();
    	String output = "on(60,ELECTRIC_GRAND) wait(100) off(60,ELECTRIC_GRAND)";
    	
    	Midi midi = Midi.getInstance();
    	
    	midi.clearHistory();
    	
    	pm.changeInstrument();
    	pm.changeInstrument();
    	pm.beginNote(new Pitch(0));
    	Midi.wait(100);
    	pm.endNote(new Pitch(0));
    	
    	System.out.println(midi.history());
    	assertEquals(output, midi.history());
    }
    
    
    @Test 
    // change instrument 7 times from PIANO to CLAVINET
    public void changeInstrumentTets4() throws MidiUnavailableException{
    	PianoMachine pm = new PianoMachine();
    	String output = "on(60,PIANO) wait(100) off(60,CLAVINET)";
    	
    	Midi midi = Midi.getInstance();
    	
    	midi.clearHistory();
    	
    	pm.beginNote(new Pitch(0));
    	
    	pm.changeInstrument();
    	pm.changeInstrument();
    	pm.changeInstrument();
    	pm.changeInstrument();
    	pm.changeInstrument();
    	pm.changeInstrument();
    	pm.changeInstrument();
    	
    	Midi.wait(100);
    	pm.endNote(new Pitch(0));
    	
    	System.out.println(midi.history());
    	assertEquals(output, midi.history());
    }
    
    
    @Test 
    
    public void changeInstrumentTets5() throws MidiUnavailableException{
    	PianoMachine pm = new PianoMachine();
    	String output = "on(60,BRIGHT_PIANO) wait(100) on(61,HONKY_TONK_PIANO) wait(0) off(60,HONKY_TONK_PIANO)";
    	
    	Midi midi = Midi.getInstance();
    	
    	midi.clearHistory();
    	
    	pm.changeInstrument();
    	pm.beginNote(new Pitch(0));
    	pm.changeInstrument();
    	pm.changeInstrument();
    	Midi.wait(100);
    	pm.beginNote(new Pitch(1));
    	Midi.wait(0);
    	pm.endNote(new Pitch(0));
    	
    	System.out.println(midi.history());
    	assertEquals(output, midi.history());
    }
    
    @Test 
    // shift key up at begin
    public void shiftKeyUpByOneOctave1() throws MidiUnavailableException{
    	PianoMachine pm = new PianoMachine();
    	String output = "on(72,PIANO) wait(100) off(72,PIANO)";
    	
    	Midi midi = Midi.getInstance();
    	
    	midi.clearHistory();
    	
    	
    	pm.shiftUp();
    	pm.beginNote(new Pitch(0));
    	Midi.wait(100);
    	pm.endNote(new Pitch(0));
    	
    	System.out.println(midi.history());
    	assertEquals(output, midi.history());
    }
    
    @Test 
    // shift key up after a note event began 
    public void shiftKeyUpByOneOctave2() throws MidiUnavailableException{
    	PianoMachine pm = new PianoMachine();
    	String output = "on(60,PIANO) wait(100) off(72,PIANO)";
    	
    	Midi midi = Midi.getInstance();
    	
    	midi.clearHistory();
    	
    	pm.beginNote(new Pitch(0));
    	pm.shiftUp();
    	Midi.wait(100);
    	pm.endNote(new Pitch(0));
    	
    	System.out.println(midi.history());
    	assertEquals(output, midi.history());
    }
    @Test 
    // shift key,an octave, up after changing instrument 
    public void shiftKeyUpByOneOctave3() throws MidiUnavailableException{
    	PianoMachine pm = new PianoMachine();
    	String output = "on(60,BRIGHT_PIANO) wait(100) on(72,BRIGHT_PIANO) wait(0) off(72,BRIGHT_PIANO)";
    	
    	Midi midi = Midi.getInstance();
    	
    	midi.clearHistory();
    	
    	pm.changeInstrument();
    	pm.beginNote(new Pitch(0));
    	Midi.wait(100);
    	pm.shiftUp();
    	pm.beginNote(new Pitch(0));
    	pm.endNote(new Pitch(0));
    	
    	System.out.println(midi.history());
    	assertEquals(output, midi.history());
    }
    @Test 
    // shift key,an octave, up after changing instrument 
    public void shiftKeyUpByOneOctave4() throws MidiUnavailableException{
    	PianoMachine pm = new PianoMachine();
    	String output = "on(72,BRIGHT_PIANO) wait(100) off(72,BRIGHT_PIANO)";
    	
    	Midi midi = Midi.getInstance();
    	
    	midi.clearHistory();
    	
    	pm.changeInstrument();
    	pm.shiftUp();
    	pm.beginNote(new Pitch(0));
    	Midi.wait(100);
    	pm.endNote(new Pitch(0));
    	
    	System.out.println(midi.history());
    	assertEquals(output, midi.history());
    }
    
    
    @Test 
    // shift key up to maximum after a note event began 
    public void shiftKeyByTwoOctave() throws MidiUnavailableException{
    	PianoMachine pm = new PianoMachine();
    	String output = "on(84,PIANO) wait(100) off(84,PIANO)";
    	
    	Midi midi = Midi.getInstance();
    	
    	midi.clearHistory();

    	pm.shiftUp();
    	pm.shiftUp();
    	
    	pm.beginNote(new Pitch(0));
    	Midi.wait(100);
    	pm.endNote(new Pitch(0));
    	
    	System.out.println(midi.history());
    	assertEquals(output, midi.history());
    }
    
    @Test 
    // shift key,3 octaves, to maximum 
    public void shiftKeyUpOutOfBoundary() throws MidiUnavailableException{
    	PianoMachine pm = new PianoMachine();
    	String output = "on(84,PIANO) wait(100) off(84,PIANO)";
    	
    	Midi midi = Midi.getInstance();
    	
    	midi.clearHistory();
    	pm.shiftUp();
    	pm.shiftUp();
    	pm.shiftUp();
    	pm.beginNote(new Pitch(0));
    	Midi.wait(100);
    	pm.endNote(new Pitch(0));
    	
    	System.out.println(midi.history());
    	assertEquals(output, midi.history());
    }
    
    @Test 
    // shift key down by an octave 
    public void shiftKeyDownByOneOctave() throws MidiUnavailableException{
    	PianoMachine pm = new PianoMachine();
    	String output = "on(48,PIANO) wait(100) off(48,PIANO)";
    	
    	Midi midi = Midi.getInstance();
    	
    	midi.clearHistory();
    	
    	pm.shiftDown();
    	pm.beginNote(new Pitch(0));
    	Midi.wait(100);
    	pm.endNote(new Pitch(0));
    	
    	System.out.println(midi.history());
    	assertEquals(output, midi.history());
    }
    
    @Test 
    // shift key down to minimum by two octaves
    public void shiftKeyDownByTwoOctave1() throws MidiUnavailableException{
    	PianoMachine pm = new PianoMachine();
    	String output = "on(60,PIANO) wait(100) off(36,PIANO)";
    	
    	Midi midi = Midi.getInstance();
    	
    	midi.clearHistory();
    	
    	pm.beginNote(new Pitch(0));
    	pm.shiftDown();
    	pm.shiftDown();
    	Midi.wait(100);
    	pm.endNote(new Pitch(0));
    	
    	System.out.println(midi.history());
    	assertEquals(output, midi.history());
    }
    
    
    @Test 
    // shift key down to minimum (36) by two octaves
    public void shiftKeyDownByTwoOctave2() throws MidiUnavailableException{
    	PianoMachine pm = new PianoMachine();
    	String output = "on(36,BRIGHT_PIANO) wait(100) off(36,BRIGHT_PIANO)";
    	
    	Midi midi = Midi.getInstance();
    	
    	midi.clearHistory();
    	
    	pm.changeInstrument();
    	pm.shiftDown();
    	pm.shiftDown();
    	pm.beginNote(new Pitch(0));
    	Midi.wait(100);
    	pm.endNote(new Pitch(0));
    	
    	System.out.println(midi.history());
    	assertEquals(output, midi.history());
    }
    
    @Test 
    // shift key down to minimum( 36 ) by 3 octaves 
    public void shiftKeyDownOutOfBoundary() throws MidiUnavailableException{
    	PianoMachine pm = new PianoMachine();
    	String output = "on(36,BRIGHT_PIANO) wait(100) off(36,BRIGHT_PIANO)";
    	
    	Midi midi = Midi.getInstance();
    	
    	midi.clearHistory();
    	
    	pm.changeInstrument();
    	pm.shiftDown();
    	pm.shiftDown();
    	pm.shiftDown();
    	pm.beginNote(new Pitch(0));
    	Midi.wait(100);
    	pm.endNote(new Pitch(0));
    	
    	System.out.println(midi.history());
    	assertEquals(output, midi.history());
    }
    
    @Test 
    // Turn on recording mode. 
    public void turnOnRecordingMode() throws MidiUnavailableException{
    	PianoMachine pMachine = new PianoMachine();
    	String expected = "on(61,PIANO) wait(100) off(61,PIANO)";
    	
    	Midi midi = Midi.getInstance();
    	midi.clearHistory();
    	
    	pMachine.toggleRecording();
    	assertTrue(pMachine.recordingMode);
    	pMachine.beginNote(new Pitch(1));
    	Midi.wait(100);
    	pMachine.endNote(new Pitch(1));
    	pMachine.toggleRecording();
    	
    	System.out.println(midi.history());
    	assertEquals(expected, midi.history());
    	
    }
    
    @Test 
    // Turn off recording mode. 
    public void turnOffRecordingMode() throws MidiUnavailableException{
    	PianoMachine pMachine = new PianoMachine();
    	String expected = "on(61,PIANO) wait(100) off(61,PIANO)";
    	
    	Midi midi = Midi.getInstance();
    	midi.clearHistory();
    	
    	assertFalse(pMachine.recordingMode);
    	
    	pMachine.toggleRecording();
    	assertTrue(pMachine.recordingMode);
    	pMachine.toggleRecording();
    	
    	assertFalse(pMachine.recordingMode);
    	
    	pMachine.beginNote(new Pitch(1));
    	Midi.wait(100);
    	pMachine.endNote(new Pitch(1));
    	
    	System.out.println(midi.history());
    	assertEquals(expected, midi.history());
    	
    }
   
	
	
    @Test
    // play the playback after recording
    public void playbackTest() throws MidiUnavailableException{
    	String expecteds1 = "on(61,PIANO) wait(100) off(61,PIANO)";
    	String expecteds2 = "on(60,PIANO) wait(100) off(60,PIANO)";
    	
    	PianoMachine pMachine = new PianoMachine();
    	Midi midi = Midi.getInstance();
    	
    	
    	pMachine.toggleRecording();
    	pMachine.beginNote(new Pitch(1));
    	Midi.wait(100);
    	pMachine.endNote(new Pitch(1));
    	pMachine.toggleRecording();
    	midi.clearHistory();
    	
    	pMachine.playback();
    	
    	System.out.println(midi.history());
    	assertEquals(expecteds1, midi.history());
    
    	midi.clearHistory();
    	pMachine.beginNote(new Pitch(1));
    	Midi.wait(100);
    	
    	assertFalse(pMachine.recordingMode);
    	
    	pMachine.toggleRecording();
    	midi.clearHistory();
    	pMachine.beginNote(new Pitch(0));
    	Midi.wait(100);
    	pMachine.endNote(new Pitch(0));
    	pMachine.toggleRecording();
    	midi.clearHistory();
    	
    	pMachine.playback();
    	
    	System.out.println(midi.history());
    	assertEquals(expecteds2, midi.history());
    	
    }
    
    @Test 
    // playback test for changing instruments
    public void playbackChangingInstrTest() throws MidiUnavailableException{
    	PianoMachine pMachine = new PianoMachine();
    	Midi midi = Midi.getInstance();
    	
    	String output = "on(60,BRIGHT_PIANO) wait(100) off(60,BRIGHT_PIANO)";
    	
    	
    	midi.clearHistory();
    	pMachine.toggleRecording();
    	pMachine.changeInstrument();
    	pMachine.beginNote(new Pitch(0));
    	Midi.wait(100);
    	pMachine.endNote(new Pitch(0));
    	pMachine.toggleRecording();
    	assertFalse(pMachine.recordingMode);
    	midi.clearHistory();
    	
    	pMachine.playback();
    	
    	System.out.println(midi.history());
    	assertEquals(output, midi.history());
    }
    
    
    @Test 
    // playback test for shifting key
    public void playbackShiftingKeyTest1() throws MidiUnavailableException{
    	PianoMachine pMachine = new PianoMachine();
    	Midi midi = Midi.getInstance();
    	
    	String string1 = "on(72,PIANO) wait(100) off(72,PIANO)";
    	String string2 = "on(84,PIANO) wait(100) off(84,PIANO)";
    	
    	midi.clearHistory();
    	pMachine.toggleRecording();
    	pMachine.shiftUp();
    	pMachine.beginNote(new Pitch(0));
    	Midi.wait(100);
    	pMachine.endNote(new Pitch(0));
    	pMachine.toggleRecording();
    	
    	midi.clearHistory();
    	pMachine.playback();
    	
    	assertEquals(string1, midi.history());
    	
    	
    	midi.clearHistory();
    	pMachine.toggleRecording();
    	pMachine.shiftUp();
    	pMachine.shiftUp();
    	pMachine.beginNote(new Pitch(0));
    	Midi.wait(100);
    	pMachine.endNote(new Pitch(0));
    	pMachine.toggleRecording();
    	
    	midi.clearHistory();
    	pMachine.playback();
    	
    	assertEquals(string2, midi.history());
    	
    }
    
    @Test 
    // playback test for shifting key
    public void playbackShiftingKeyTest2() throws MidiUnavailableException{
    	PianoMachine pMachine = new PianoMachine();
    	Midi midi = Midi.getInstance();
    	
    	String string1 = "on(48,PIANO) wait(100) off(48,PIANO)";
    	String string2 = "on(36,PIANO) wait(100) off(36,PIANO)";
       	
    	midi.clearHistory();
    	pMachine.toggleRecording();
    	pMachine.shiftDown();
    	pMachine.beginNote(new Pitch(0));
    	Midi.wait(100);
    	pMachine.endNote(new Pitch(0));
    	pMachine.toggleRecording();
    	
    	midi.clearHistory();
    	pMachine.playback();
    	
    	assertEquals(string1, midi.history());
    	
    	midi.clearHistory();
    	pMachine.toggleRecording();
    	pMachine.shiftDown();
    	pMachine.shiftDown();
    	pMachine.beginNote(new Pitch(0));
    	Midi.wait(100);
    	pMachine.endNote(new Pitch(0));
    	pMachine.toggleRecording();
    	
    	midi.clearHistory();
    	
    	pMachine.playback();
    	
    	assertEquals(string2, midi.history());
    	
    }
    @Test
    public void playbackThreeNoteEventsTest() throws MidiUnavailableException {
    	
    	PianoMachine pm = new PianoMachine();
    	
    	String expectedBehavior = "on(60,PIANO) wait(100) off(60,PIANO) wait(20) " +
    			"on(64,PIANO) wait(100) off(64,PIANO) wait(20) " +
    			"on(69,PIANO) wait(100) off(69,PIANO)";
    	
    	Midi midi = Midi.getInstance();
    	midi.clearHistory();
    	
    	assertFalse(pm.recordingMode);
    	pm.toggleRecording();
    	assertTrue(pm.recordingMode);

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
		assertFalse(pm.recordingMode);
		
		midi.clearHistory();
		pm.playback();
		assertEquals(expectedBehavior, midi.history());
    }

}
