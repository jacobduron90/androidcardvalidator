To run this project using the source code:

In eclipse go to File > Import > Existing Code Into Workspace

Choose Browse to find the project directory and choose the project called CardForm

Click Finish and allow eclipse to import it into the work environment

Go to Project > Clean and choose CardFrom

Start a virtual emulator or plug in an android phone

Run the project as an Android Application. 

////////////////////////////////////////////////////////////////////////////

To run the app using the apk:

In the terminal go to your path for Android SDK

CD into platform-tools

Get a list of running emulators using: ./adb devices

Install the apk into an emulator using: ./adb -e install -r cardform.apk

Run the app from the terminal using: ./adb shell am start -n com.example.cardform/.Card
 

