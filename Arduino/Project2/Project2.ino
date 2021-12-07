#include <USB-MIDI.h>
#include <Adafruit_CircuitPlayground.h>
// Simple tutorial on how to receive and send MIDI messages.
// Here, when receiving any message on channel 4, the Arduino
// will blink a led and play back a note for 1 second.

USBMIDI_CREATE_DEFAULT_INSTANCE();
bool is_pressed [100 * false]; // initialize all as false
void setup()
{
  CircuitPlayground.begin();
  MIDI.begin(4); // Launch MIDI and listen to channel 4
}

void updateTouches()
{
  for (int i = 0; i < 7; i++)
  {
    int kick_midi_value = 60;
    int snare_midi_value = 62;
    int hat_midi_value = 64;
    int tom_midi_value = 65;
    int kickcp = CircuitPlayground.readCap(1, 10);
    int snarecp = CircuitPlayground.readCap(2, 10);
    int hatcp = CircuitPlayground.readCap(3, 10);
    int tomcp = CircuitPlayground.readCap(4, 10);
    //Serial.println(CircuitPlayground.readCap(1, 10));

    // KICK
    if ( kickcp < 50 || CircuitPlayground.leftButton())
    {
      if (is_pressed[kick_midi_value] == false)
      {
        Serial.println("Kick");
        MIDI.sendNoteOn(kick_midi_value, 127, 1);    // Send a Note (pitch 42, velo 127 on channel 1)
        Serial.println(is_pressed[kick_midi_value]);
      }
      is_pressed[kick_midi_value] = true;

    } else if (kickcp > 200)
    { // if releasing touch
      if (is_pressed[kick_midi_value] == true)
      { // if the corresponding note to that touch pad was previously on....
        MIDI.sendNoteOff(kick_midi_value, 0, 1);     // Stop the note
        Serial.println(is_pressed[kick_midi_value]);
      }
      is_pressed[kick_midi_value] = false; // force the value to be false regardless of previous state
    }

    // SNARE
    if ( snarecp < 50 || CircuitPlayground.rightButton() )
    {

      if (is_pressed[snare_midi_value] == false)
      {
        Serial.println("Snare");
        MIDI.sendNoteOn(snare_midi_value, 127, 1);    // Send a Note (pitch 42, velo 127 on channel 1)
        Serial.println(is_pressed[snare_midi_value]);
      }
      is_pressed[snare_midi_value] = true;

    } else if (snarecp > 200)
    { // if releasing touch
      if (is_pressed[snare_midi_value] == true)
      { // if the corresponding note to that touch pad was previously on....
        MIDI.sendNoteOff(snare_midi_value, 0, 1);     // Stop the note
        Serial.println(is_pressed[snare_midi_value]);
      }
      is_pressed[snare_midi_value] = false; // force the value to be false regardless of previous state
    }

        // HAT
    if ( hatcp < 50 )
    {
      if (is_pressed[hat_midi_value] == false)
      {
        Serial.println("Hat");
        MIDI.sendNoteOn(hat_midi_value, 127, 1);    // Send a Note (pitch 42, velo 127 on channel 1)
        Serial.println(is_pressed[hat_midi_value]);
      }
      is_pressed[hat_midi_value] = true;

    } else if (hatcp > 200)
    { // if releasing touch
      if (is_pressed[hat_midi_value] == true)
      { // if the corresponding note to that touch pad was previously on....
        MIDI.sendNoteOff(hat_midi_value, 0, 1);     // Stop the note
        Serial.println(is_pressed[hat_midi_value]);
      }
      is_pressed[hat_midi_value] = false; // force the value to be false regardless of previous state
    }

            // TOM
    if ( tomcp < 50 )
    {
      if (is_pressed[tom_midi_value] == false)
      {
        Serial.println("Tom");
        MIDI.sendNoteOn(tom_midi_value, 127, 1);    // Send a Note (pitch 42, velo 127 on channel 1)
        Serial.println(is_pressed[tom_midi_value]);
      }
      is_pressed[tom_midi_value] = true;

    } else if (tomcp > 200)
    { // if releasing touch
      if (is_pressed[tom_midi_value] == true)
      { // if the corresponding note to that touch pad was previously on....
        MIDI.sendNoteOff(tom_midi_value, 0, 1);     // Stop the note
        Serial.println(is_pressed[tom_midi_value]);
      }
      is_pressed[tom_midi_value] = false; // force the value to be false regardless of previous state
    }
  }
}

void loop()
{
  digitalWrite(LED_BUILTIN, HIGH);
  updateTouches();
  digitalWrite(LED_BUILTIN, LOW);
}
