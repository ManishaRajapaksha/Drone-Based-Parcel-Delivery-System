Drone Based Parcel Delivery System
===============================================

-----------------------------------------------

System Features
---------------
- Drone Fleet Management (Light Drone / Heavy Drone)
- Dispatcher with fixed drone slots
- Delivery request handling
- Parcel weight vs drone capacity checking
- Battery and GPS simulation
- Simple weather update simulation
- Simple beginner-level GUI using Java Swing

-----------------------------------------------

User Interface Overview
-----------------------
The application interface consists of three main sections:

LEFT PANEL
- Add Drone button
- Delivery Request button
- Update Weather button
- Terminal Output area (logs and system messages)

CENTER PANEL
- Drone Fleet Grid (4 x 5 = 20 slots)
- Delivery Request List Grid (4 x 5 = 25 slots)

RIGHT PANEL
- Shows details of the selected drone and the deliveryRequest

-----------------------------------------------

How to Run the Project
---------------------
1. Open the project in an IDE such as:
   - IntelliJ IDEA
   - VS Code
   - Eclipse

2. Ensure you have Java JDK installed (Java 17 or above recommended).

3. Navigate to the following file:
   src/Drone/RunSystem.java

4. Run the `main()` method inside `RunSystem.java`.

5. The Drone Dispatcher GUI window will open.

-----------------------------------------------

How to Use the System
---------------------
1. Click "Add Drone"
   - Adds a new drone (Light or Heavy) to the required slot
   - The drone appears in the Drone Fleet grid

2. Click a Drone Slot
   - Displays detailed drone information on the right panel

3. Click "Delivery Request"
   - Opens a dialog box to enter:
     - Customer details
     - Parcel details
     - Location
   - Assigns a suitable drone based on weight capacity

4. Click "Update Weather"
   - Simulates weather changes affecting delivery conditions

5. Terminal Output
   - Displays system messages and simulation logs

-----------------------------------------------



End of README
===============================================
