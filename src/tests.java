
// import Customer.Customer;
// import Customer.CustomerManager;
// import Customer.DeliveryRequest;
// import Common.Location;
// import Delivery.Parcel;
// import Dispatch.ChargingStation;
// import Dispatch.Dispatcher;
// import Dispatch.WeatherSystem;
// import Drone.Drone;
// import Drone.GPSmodule;
// import Drone.HeavyDrone;
// import Drone.LightDrone;

// import javax.swing.*;
// import java.awt.*;
// import java.util.ArrayList;

// public class RunSystem extends JFrame {

// //    private Dispatcher dispatcher;
// //    private JTextArea displayArea;
// //    public RunSystem() {
// //        super("Drone Delivery Simulation Dashboard");
// //        // Initialize system components
// //        WeatherSystem weather = new WeatherSystem();
// //        ChargingStation station = new ChargingStation(20);
// //        dispatcher = new Dispatcher(weather, station, new Location("Kandy"));
// //    }
//     private static final int MAX_DRONES = 20;
//     private static final int HEAVY_DRONE_WEIGHT = 10000;
//     private static final int LIGHT_DRONE_WEIGHT = 1000;
//     private static final int HEAVY_DRONE_SPEED = 3000;
//     private static final int LIGHT_DRONE_SPEED = 3500;

//     private JButton[] droneButtons = new JButton[MAX_DRONES];

//     private DeliveryRequest[] DreqList = new DeliveryRequest[MAX_DRONES];

//     JButton[] requestButtons = new JButton[MAX_DRONES];

//     private JPanel rightPanel;
//     private JTextArea terminal;

//     WeatherSystem weather = new WeatherSystem();
//     ChargingStation station = new ChargingStation(20);
//     Dispatcher dispatcher = new Dispatcher(weather, station, new Location("Kandy"), MAX_DRONES);
//     CustomerManager customerManager = new CustomerManager();

//     public RunSystem() {

//         setTitle("Drone Dispatcher");
//         setSize(1200, 700);
//         setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//         setLayout(new BorderLayout());

//         // ---------------- LEFT PANEL ----------------
//         JPanel leftPanel = new JPanel();
//         leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));
//         leftPanel.setPreferredSize(new Dimension(350, 0));

//         JButton addDroneBtn = new JButton("Add Drone");
//         JButton deliveryBtn = new JButton("Delivery Request");
//         JButton updateWeatberBtn = new JButton("Update Weather");

//         terminal = new JTextArea(10, 20);
//         terminal.setEditable(false);
//         JScrollPane terminalScroll = new JScrollPane(terminal);

//         addDroneBtn.addActionListener(e -> openAddDroneWindow());
//         deliveryBtn.addActionListener(e -> openDeliveryRequestWindow());
//         updateWeatberBtn.addActionListener(e -> {
//             weather.updateWeather();
//             terminal.append("Weather updated: " + (weather.isSafeToFly() ? "Safe to fly\n" : "Unsafe to fly\n") + "(Current: " + weather.getCurrentWeather() + ")\n");
//         });

//         leftPanel.add(addDroneBtn);
//         leftPanel.add(Box.createVerticalStrut(10));
//         leftPanel.add(deliveryBtn);
//         leftPanel.add(Box.createVerticalStrut(10));
//         leftPanel.add(updateWeatberBtn);
//         leftPanel.add(Box.createVerticalStrut(20));
//         leftPanel.add(new JLabel("Terminal Output:"));
//         leftPanel.add(terminalScroll);

//         // ---------------- CENTER GRID PANEL ----------------
//         JPanel gridPanel = new JPanel(new GridLayout(5, 4, 8, 8));
//         gridPanel.setBorder(BorderFactory.createTitledBorder("Drone Fleet"));

//         for (int i = 0; i < MAX_DRONES; i++) {

//             JButton b = new JButton("Empty");
//             b.setPreferredSize(new Dimension(80, 40));  // smaller slot size
//             b.setFont(new Font("Arial", Font.PLAIN, 11)); // optional smaller text
//             int index = i;  // for use in lambda
//             b.addActionListener(e -> showDroneInfo(index));
//             droneButtons[i] = b;
//             gridPanel.add(b);
//         }

//         // DELIVERY REQUEST LIST GRID (5x5)
//         JPanel requestGrid = new JPanel(new GridLayout(5, 4, 5, 5));
//         requestGrid.setBorder(BorderFactory.createTitledBorder("Delivery Requests"));

//         for (int i = 0; i < MAX_DRONES; i++) {
//             JButton r = new JButton("Empty");
//             r.setPreferredSize(new Dimension(80, 40));     // small slots
//             r.setFont(new Font("Arial", Font.PLAIN, 10));  // small text
//             int index = i;  // for use in lambda
//             r.addActionListener(e -> showReqInfo(index));
//             requestButtons[i] = r;
//             requestGrid.add(r);
//         }

//         // ---------------- RIGHT PANEL ----------------
//         rightPanel = new JPanel();
//         rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.Y_AXIS));
//         rightPanel.setPreferredSize(new Dimension(300, 0));
//         rightPanel.add(new JLabel("Drone Info Display"));
//         rightPanel.add(Box.createVerticalStrut(20));

//         // ---------------- ADD TO FRAME ----------------
//         add(leftPanel, BorderLayout.WEST);

//         JPanel centerWrapper = new JPanel();
//         centerWrapper.setLayout(new BoxLayout(centerWrapper, BoxLayout.Y_AXIS));
//         centerWrapper.add(gridPanel);
//         centerWrapper.add(Box.createVerticalStrut(15));  // space
//         centerWrapper.add(requestGrid);

//         add(centerWrapper, BorderLayout.CENTER);

//         add(rightPanel, BorderLayout.EAST);

//         setVisible(true);
//     }

//     // ===========================================================
//     //                      ADD DRONE WINDOW
//     // ===========================================================
//     private void openAddDroneWindow() {

//         //dialog box size and layout desing
//         JDialog dialog = new JDialog(this, "Add Drone", true);
//         dialog.setSize(300, 250);
//         dialog.setLayout(new GridLayout(5, 3, 30, 10));

//         JTextField D_idField = new JTextField();

//         //drop box for drone types
//         String[] types = {"Light Drone", "Heavy Drone"};
//         JComboBox<String> typeBox = new JComboBox<>(types);

//         //add button to add drone
//         JButton addBtn = new JButton("Add");

//         //action listener for add button
//         addBtn.addActionListener(e -> {
//             addDrone(D_idField.getText(), (String) typeBox.getSelectedItem());
//             dialog.dispose(); //close dialog after adding drone
//         });

//         //adding components to dialog box
//         dialog.add(new JLabel("Drone Slot (number 0-25):"));
//         dialog.add(D_idField);
//         dialog.add(new JLabel("Select Type:"));
//         dialog.add(typeBox);
//         dialog.add(addBtn);

//         //set dialog location and make it visible
//         dialog.setLocationRelativeTo(this);
//         dialog.setVisible(true);
//     }
//     int addedDrones = 0;

//     private void addDrone(String id, String type) {
//         addedDrones++;
//         if (addedDrones >= MAX_DRONES) {
//             terminal.append("ERROR: No free slots!\n");
//             return;
//         }

//         Drone drone = null;

//         int index = 0;
//         try {
//             index = Integer.parseInt(id);
//             System.out.println(index);
//         } catch (NumberFormatException e) {
//             System.err.println("Invalid number format: " + e.getMessage());
//         }

//         switch (type) {
//             case "Light Drone" ->
//                 drone = new LightDrone(index, LIGHT_DRONE_WEIGHT, LIGHT_DRONE_SPEED, dispatcher.getLocation());
//             case "Heavy Drone" ->
//                 drone = new HeavyDrone(index, HEAVY_DRONE_WEIGHT, HEAVY_DRONE_SPEED, dispatcher.getLocation());

//         }

//         dispatcher.registerDrone(drone, index);

//         droneButtons[index].setText(dispatcher.droneFleet[index].getDroneID());
//         droneButtons[index].setBackground(Color.GREEN);

//         terminal.append("Drone added: " + dispatcher.droneFleet[index].getDroneID() + "\n");
//     }

//     // ===========================================================
//     //                     DISPLAY DRONE INFORMATION
//     // ===========================================================
//     private void showDroneInfo(int index) {
//         rightPanel.removeAll();

//         if (index >= MAX_DRONES) {
//             rightPanel.add(new JLabel("Empty Slot"));
//         } else {
//             Drone d = dispatcher.droneFleet[index];

//             rightPanel.add(new JLabel("Drone ID: " + d.getDroneID()));
//             rightPanel.add(new JLabel("Type: " + d.getClass().getSimpleName()));
//             rightPanel.add(new JLabel("Max Weight: " + d.getMaxWeight()));
//             rightPanel.add(new JLabel(" "));
//             rightPanel.add(new JLabel("Battery: " + d.getBatteryLevel() + "%"));
//             rightPanel.add(new JLabel("Battery Cell type: " + d.getBattery().getCellType()));
//             rightPanel.add(new JLabel(" "));
//             rightPanel.add(new JLabel("Speed: " + d.getSpeed() + " km/h"));
//             rightPanel.add(new JLabel("Status: " + d.getStatus()));

//             if (d.getLocation() != null) {
//                 rightPanel.add(new JLabel("Location: " + d.getLocation().toString()));
//             }

//             //button for remove drone
//             JButton removeBtn = new JButton("Remove Drone");
//             removeBtn.addActionListener(e -> {
//                 dispatcher.removeDrone(index);
//                 droneButtons[index].setText("Empty");
//                 droneButtons[index].setBackground(null);
//                 rightPanel.removeAll();
//                 rightPanel.add(new JLabel("Drone Removed"));
//                 rightPanel.revalidate();
//                 rightPanel.repaint();
//                 terminal.append("Drone removed from slot " + index + "\n");
//             });

//             rightPanel.add(Box.createVerticalStrut(10));
//             rightPanel.add(removeBtn);

//         }

//         rightPanel.revalidate();
//         rightPanel.repaint();
//     }

//     // ===========================================================
//     //             DELIVERY REQUEST WINDOW (STUB FOR NOW)
//     // ===========================================================
//     private void openDeliveryRequestWindow() {

//         //dialog box size and layout desing
//         JDialog dialog = new JDialog(this, "Add Delivery Request", true);
//         dialog.setSize(300, 600);
//         dialog.setLayout(new GridLayout(11, 1, 5, 10));

//         JTextField C_NameField = new JTextField();
//         JTextField C_AddressField = new JTextField();
//         JTextField C_PhnoField = new JTextField();

//         String[] locStrings = {"Colombo", "Kandy", "Galle", "Jaffna", "Negombo", "Anuradhapura", "Kurunegala", "Matara"};
//         JComboBox<String> locationBox = new JComboBox<>(locStrings);

//         JTextField P_WeightField = new JTextField();

//         //add button to add drone
//         JButton addBtn = new JButton("Add");

//         //action listener for add button
//         addBtn.addActionListener(e -> {
//             addDeliveryRequest(C_NameField.getText(), C_AddressField.getText(),
//                     C_PhnoField.getText(),
//                     Double.parseDouble(P_WeightField.getText()),
//                     (String) locationBox.getSelectedItem());
//             dialog.dispose(); //close dialog after adding drone
//         });

//         //adding components to dialog box
//         dialog.add(new JLabel("Enter Customer Name:"));
//         dialog.add(C_NameField);
//         dialog.add(new JLabel("Enter Customer Address:"));
//         dialog.add(C_AddressField);
//         dialog.add(new JLabel("Enter Customer Phone Number:"));
//         dialog.add(C_PhnoField);
//         dialog.add(new JLabel("Enter Parcel Weight (g):"));
//         dialog.add(P_WeightField);
//         dialog.add(new JLabel("Select Delivery Location:"));

//         dialog.add(locationBox);
//         dialog.add(addBtn);

//         //set dialog location and make it visible
//         dialog.setLocationRelativeTo(this);
//         dialog.setVisible(true);
//     }

//     static int requestCount = 0;
//     static int emtySlot;

//     private void addDeliveryRequest(String customerName, String customerAddress, String customerPhone, double parcelWeight, String deliveryCity) {

//         // Create Customer
//         Customer customer = new Customer(customerName, customerAddress, customerPhone, new Location(deliveryCity));
//         customerManager.registerCustomer(customer);

//         // Create Parcel
//         Parcel parcel = new Parcel(parcelWeight, deliveryCity);

//         // Create Delivery Request
//         DeliveryRequest request = customer.requestDelivery(parcel, new Location(deliveryCity));

//         // Update Request Buttons
//         for (int i = 0; i < DreqList.length; i++) {
//             if (DreqList[i] == null) {
//                 emtySlot = i;
//                 break;
//             }
//             System.out.println(DreqList[i]);
//             System.out.println(emtySlot);
//         }

//         DreqList[emtySlot] = request;

//         requestButtons[emtySlot].setText("Req: " + customer.getCustomerId());
//         requestButtons[emtySlot].setBackground(Color.CYAN);

//         if (requestCount < DreqList.length - 1) {
//             requestCount++;
//         } else {
//             terminal.append("ERROR: No free request slots!\n");
//             return;
//         }
//         terminal.append("Delivery Request added for Customer: " + customer.getCustomerId() + "\n");
//     }

//     private void showReqInfo(int index) {

//         rightPanel.removeAll();

//         if (index >= DreqList.length || DreqList[index] == null) {
//             rightPanel.add(new JLabel("Empty Slot"));
//         } else {
//             DeliveryRequest dreq = DreqList[index];

//             rightPanel.add(new JLabel("Request ID: " + dreq.getRequestId()));
//             rightPanel.add(new JLabel("Status: " + dreq.getStatus()));

//             rightPanel.add(new JLabel(" "));
//             rightPanel.add(new JLabel("Customer Info:"));
//             rightPanel.add(new JLabel("Customer ID: " + dreq.getCustomer().getCustomerId()));
//             rightPanel.add(new JLabel("Name: " + dreq.getCustomer().getName()));
//             rightPanel.add(new JLabel("Address: " + dreq.getCustomer().getAddress()));
//             rightPanel.add(new JLabel("Contact: " + dreq.getCustomer().getContactNumber()));
//             rightPanel.add(new JLabel("Location: " + dreq.getCustomer().getLocation().toString()));

//             rightPanel.add(new JLabel(" "));
//             rightPanel.add(new JLabel("Parcel Info:"));
//             rightPanel.add(new JLabel("Parcel ID: " + dreq.parcel.getParcelId()));
//             rightPanel.add(new JLabel("Weight: " + dreq.parcel.getWeight() + " g"));

//             rightPanel.add(new JLabel(" "));

//             // Button to assign drone
//             JButton assignBtn = new JButton("Assign Drone and Start Delivery");
//             assignBtn.addActionListener(e -> {

//                 Drone assigned = dispatcher.assignDrone(dreq, dreq.parcel.getWeight());

//                 if (assigned != null) {
//                     terminal.append("Assigned Drone: " + assigned.getDroneID() + " to Request: " + dreq.getRequestId() + "\n");
//                     dreq.updateStatus("Drone Assigned: " + assigned.getDroneID() + " | In delivery");

//                     dispatcher.droneFleet[assigned.getIdNumber()].setStatus("Delivering Parcel: " + dreq.parcel.getParcelId());

//                     droneButtons[assigned.getIdNumber()].setBackground(Color.ORANGE);

//                     showReqInfo(index); // Refresh display
//                     requestButtons[index].setBackground(Color.PINK);

//                     // Simulate delivery completion
//                     dispatcher.droneFleet[assigned.getIdNumber()].setDestination(dreq.getCustomer().getLocation());

//                     SwingWorker<Void, Void> deliveryWorker = new SwingWorker<Void, Void>() {
//                         @Override
//                         protected Void doInBackground() throws Exception {

//                             while (!dispatcher.droneFleet[assigned.getIdNumber()].gps.simulateTravel()) {
//                                 Thread.sleep(5000);

//                                 dispatcher.droneFleet[assigned.getIdNumber()].updateDrone();
//                                 terminal.append("Drone " + assigned.getDroneID() + " traveling...\n");

//                             }
//                             return null;

//                         }

//                         @Override
//                         protected void done(){
//                             dreq.updateStatus("Delivered");

//                             showReqInfo(index); // Refresh display

//                             dispatcher.droneFleet[assigned.getIdNumber()].setStatus("Idle");

//                             DreqList[index] = null;
//                             requestButtons[index].setText("Empty");
//                             requestButtons[index].setBackground(null);
//                             rightPanel.removeAll();
//                             rightPanel.add(new JLabel("Request Deleted"));
//                             rightPanel.revalidate();
//                             rightPanel.repaint();
//                             terminal.append("Delivery Completed....\n");
//                             terminal.append("Delivery Request " + dreq.getRequestId() + " deleted.\n");

//                             Thread.sleep(1000);
                            
                            
//                             dispatcher.droneFleet[assigned.getIdNumber()].setStatus("Returning to Base");
//                             dispatcher.droneFleet[assigned.getIdNumber()].setDestination(dispatcher.getLocation());
//                             while (!dispatcher.droneFleet[assigned.getIdNumber()].gps.simulateTravel()) {

//                                 Thread.sleep(5000);
//                                 dispatcher.droneFleet[assigned.getIdNumber()].updateDrone();
//                                 terminal.append("Drone " + assigned.getDroneID() + " returning to base...\n");

//                             }

//                             dispatcher.droneFleet[assigned.getIdNumber()].setStatus("Idle");
//                             droneButtons[assigned.getIdNumber()].setBackground(Color.GREEN);
//                             terminal.append("Drone " + assigned.getDroneID() + " returned to base and is now idle.\n");
//                             dispatcher.droneFleet[assigned.getIdNumber()].rechargeBattery();
//                             terminal.append("Drone " + assigned.getDroneID() + " battery recharged to 100%.\n");
//                         }
//                     };
//                     deliveryWorker.execute();

//                     terminal.append(dispatcher.droneFleet[assigned.getIdNumber()].gps.getGPSInfo());
//                     System.out.println(dispatcher.droneFleet[assigned.getIdNumber()].gps.getGPSInfo());

//                     // deleteing the request after delivery
//                     // Simulate time passing
//                     //----------------
//                 } else {
//                     terminal.append("No available drone for Request: " + dreq.getRequestId() + "\n");
//                 }
//             });

//             //delete request button
//             JButton deleteBtn = new JButton("Delete Request");
//             deleteBtn.addActionListener(e -> {
//                 DreqList[index] = null;
//                 requestButtons[index].setText("Empty");
//                 requestButtons[index].setBackground(null);
//                 rightPanel.removeAll();
//                 rightPanel.add(new JLabel("Request Deleted"));
//                 rightPanel.revalidate();
//                 rightPanel.repaint();
//                 terminal.append("Delivery Request " + dreq.getRequestId() + " deleted.\n");
//             });

//             rightPanel.add(Box.createVerticalStrut(10));
//             rightPanel.add(deleteBtn);

//             rightPanel.add(assignBtn);

//         }

//         rightPanel.revalidate();
//         rightPanel.repaint();
//     }

//     // ------------- MAIN FUNCTION -------------
//     public static void main(String[] args) {
//         new RunSystem();
//     }
// }
