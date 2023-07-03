# NoMoreSnore
[CS330 2023 Spring Project] NoMoreSnore Application

## Motivation
- Snoring is a bothersome problem that affects both the snorer and the people sleeping around the snorer.
It often disrupts sleep and can lead to daytime drowsiness, reduced quality of life, and awkward 
relationships with sleeping partners. Importantly, it is hard for the snorer to be aware of when he/she 
snores, making snoring a tricky problem to fix.
- Let’s consider an example problem situation. User A started living in a dormitory upon entering college. 
User A was unaware of the loudness of his snoring, so he was surprised when his angry roommate 
expressed difficulty in sleeping due to his snoring. User A is looking for a way to wake up whenever he 
snores too loudly, so that he won’t bother his roommate too badly. He is determined to correct his snoring 
issues, so that snoring will not harm his relationships in the future.
- In order to help numerous people suffering from snoring, we propose a monitoring application called 
‘NoMoreSnore’. This application monitors the sleeping of users, and wakes them up when their snoring 
becomes too loud.

## Goal of the Application
- On detection of snoring, the application rings an alarm to wake up the snorer. The alarm can ring through 
earphones, in case there are other sleeping people around the snorer. The alarm does not stop ringing 
until the user wakes up, and a face is detected.

## Usage Patterns and Environment
- The application will be installed on a smartphone, and the user will turn on the application before going 
to sleep. Of course, the smartphone must be at a place where the alarm can be heard, if the alarm rings. 
Alternatively, the user can connect a wireless earphone and plug them on before going to sleep, in which 
case the alarm will ring through the earphone to wake up only the user.

## Models Used
- Audio event classification model to detect the sound of snoring
- Face detection model to detect the face of the (woken up) snorer

## Operation Example (Usage Scenario)
- The user turns on the application before going to sleep. The application monitors microphone and 
camera input while the user is sleeping. When the user starts snoring, the application detects it on the 
microphone and starts ringing an alarm. The user habitually moves only his hand and presses the alarm 
ending button, but the alarm does not turn off. The alarm is turned off only after the application detects 
that the user has woken up with camera input. After the alarm turns off, the application goes back to the 
monitoring state, and repeats the process of ringing alarms whenever snoring is detected.
