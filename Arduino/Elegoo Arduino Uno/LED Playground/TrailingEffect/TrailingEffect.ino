#include <FastLED.h>

#define NUM_LEDS 50
#define LED_PIN 9           // Choose any of the PWM pins: 3, 5, 6, 9, 10, 11
#define BRIGHTNESS 200
#define TRAIL_LENGTH 10     // Length of the fading trail

// Define the primary color (leading LED) and secondary color (trail fades into this color)
CRGB primaryColor = CRGB::Blue;
CRGB secondaryColor = CRGB::Red;

CRGB leds[NUM_LEDS];

void setup() {
  // Use WS2812 chipset with GRB color order
  FastLED.addLeds<WS2812, LED_PIN, RGB>(leds, NUM_LEDS);
  FastLED.setBrightness(BRIGHTNESS);
}

void loop() {
  for (int i = 0; i < NUM_LEDS + TRAIL_LENGTH; i++) {
    // Clear all LEDs before drawing the next frame
    fadeToBlackBy(leds, NUM_LEDS, 30);

    // Set the leading LED to the primary color
    if (i < NUM_LEDS) {
      leds[i] = primaryColor;
    }

    // Create the fading trail with LERP into the secondary color
    for (int j = 1; j <= TRAIL_LENGTH; j++) {
      int trailIndex = i - j;
      if (trailIndex >= 0 && trailIndex < NUM_LEDS) {
        // Calculate fade factor based on the trail position
        float fadeFactor = 1.0 - (float)j / TRAIL_LENGTH;
        leds[trailIndex] = blend(primaryColor, secondaryColor, 255 * (1 - fadeFactor));
      }
    }

    FastLED.show();
    delay(50); // Delay to control the speed of the animation
  }
}
