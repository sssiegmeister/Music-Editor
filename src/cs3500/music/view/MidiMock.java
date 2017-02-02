package cs3500.music.view;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.sound.midi.ControllerEventListener;
import javax.sound.midi.InvalidMidiDataException;
import javax.sound.midi.MetaEventListener;
import javax.sound.midi.MidiUnavailableException;
import javax.sound.midi.Receiver;
import javax.sound.midi.Sequence;
import javax.sound.midi.Sequencer;
import javax.sound.midi.Track;
import javax.sound.midi.Transmitter;


/**
 * A class that mocks a synthesizer: The only functionality of this class is that it can
 * return a mock receiver.
 */
public class MidiMock implements Sequencer {

  MockReceiver receiver;

  /**
   * Constructor for a MidiMock object
   *
   * @param r the MidiMock's MockReciever
   */
  public MidiMock(MockReceiver r) {
    receiver = r;
  }

  //only method that must be overwritten for the mock.
  @Override
  public Receiver getReceiver() throws MidiUnavailableException {
    return receiver;
  }


  @Override
  public List<Receiver> getReceivers() {
    throw new UnsupportedOperationException();
  }


  @Override
  public Transmitter getTransmitter() throws MidiUnavailableException {
    throw new UnsupportedOperationException();
  }


  @Override
  public List<Transmitter> getTransmitters() {
    throw new UnsupportedOperationException();
  }

  @Override
  public void setSequence(Sequence sequence) throws InvalidMidiDataException {
    return;
  }

  @Override
  public void setSequence(InputStream stream) throws IOException, InvalidMidiDataException {
    throw new UnsupportedOperationException();
  }

  @Override
  public Sequence getSequence() {
    throw new UnsupportedOperationException();
  }

  @Override
  public void start() {
    return;
  }

  @Override
  public void stop() {
    throw new UnsupportedOperationException();
  }

  @Override
  public boolean isRunning() {
    throw new UnsupportedOperationException();
  }

  @Override
  public void startRecording() {
    throw new UnsupportedOperationException();
  }

  @Override
  public void stopRecording() {
    throw new UnsupportedOperationException();
  }

  @Override
  public boolean isRecording() {
    throw new UnsupportedOperationException();
  }

  @Override
  public void recordEnable(Track track, int channel) {
    throw new UnsupportedOperationException();
  }

  @Override
  public void recordDisable(Track track) {
    throw new UnsupportedOperationException();
  }

  @Override
  public float getTempoInBPM() {
    throw new UnsupportedOperationException();
  }

  @Override
  public void setTempoInBPM(float bpm) {
    throw new UnsupportedOperationException();
  }

  @Override
  public float getTempoInMPQ() {
    throw new UnsupportedOperationException();
  }

  @Override
  public void setTempoInMPQ(float mpq) {
    throw new UnsupportedOperationException();
  }

  @Override
  public void setTempoFactor(float factor) {
    throw new UnsupportedOperationException();
  }

  @Override
  public float getTempoFactor() {
    throw new UnsupportedOperationException();
  }

  @Override
  public long getTickLength() {
    throw new UnsupportedOperationException();
  }

  @Override
  public long getTickPosition() {
    throw new UnsupportedOperationException();
  }

  @Override
  public void setTickPosition(long tick) {
    throw new UnsupportedOperationException();
  }

  @Override
  public long getMicrosecondLength() {
    throw new UnsupportedOperationException();
  }

  @Override
  public Info getDeviceInfo() {
    throw new UnsupportedOperationException();
  }

  @Override
  public void open() throws MidiUnavailableException {
    throw new UnsupportedOperationException();
  }

  @Override
  public void close() {
    throw new UnsupportedOperationException();
  }

  @Override
  public boolean isOpen() {
    throw new UnsupportedOperationException();
  }

  @Override
  public long getMicrosecondPosition() {
    throw new UnsupportedOperationException();
  }

  @Override
  public int getMaxReceivers() {
    throw new UnsupportedOperationException();
  }

  @Override
  public int getMaxTransmitters() {
    throw new UnsupportedOperationException();
  }

  @Override
  public void setMicrosecondPosition(long microseconds) {
    throw new UnsupportedOperationException();
  }

  @Override
  public void setMasterSyncMode(SyncMode sync) {
    throw new UnsupportedOperationException();
  }

  @Override
  public SyncMode getMasterSyncMode() {
    throw new UnsupportedOperationException();
  }

  @Override
  public SyncMode[] getMasterSyncModes() {
    throw new UnsupportedOperationException();
  }

  @Override
  public void setSlaveSyncMode(SyncMode sync) {
    throw new UnsupportedOperationException();
  }

  @Override
  public SyncMode getSlaveSyncMode() {
    throw new UnsupportedOperationException();
  }

  @Override
  public SyncMode[] getSlaveSyncModes() {
    throw new UnsupportedOperationException();
  }

  @Override
  public void setTrackMute(int track, boolean mute) {
    throw new UnsupportedOperationException();
  }

  @Override
  public boolean getTrackMute(int track) {
    throw new UnsupportedOperationException();
  }

  @Override
  public void setTrackSolo(int track, boolean solo) {
    throw new UnsupportedOperationException();
  }

  @Override
  public boolean getTrackSolo(int track) {
    throw new UnsupportedOperationException();
  }

  @Override
  public boolean addMetaEventListener(MetaEventListener listener) {
    throw new UnsupportedOperationException();
  }

  @Override
  public void removeMetaEventListener(MetaEventListener listener) {
    throw new UnsupportedOperationException();
  }

  @Override
  public int[] addControllerEventListener(ControllerEventListener listener, int[] controllers) {
    throw new UnsupportedOperationException();
  }

  @Override
  public int[] removeControllerEventListener(ControllerEventListener listener, int[] controllers) {
    throw new UnsupportedOperationException();
  }

  @Override
  public void setLoopStartPoint(long tick) {
    throw new UnsupportedOperationException();
  }

  @Override
  public long getLoopStartPoint() {
    throw new UnsupportedOperationException();
  }

  @Override
  public void setLoopEndPoint(long tick) {
    throw new UnsupportedOperationException();
  }

  @Override
  public long getLoopEndPoint() {
    throw new UnsupportedOperationException();
  }

  @Override
  public void setLoopCount(int count) {
    throw new UnsupportedOperationException();
  }

  @Override
  public int getLoopCount() {
    throw new UnsupportedOperationException();
  }
}
