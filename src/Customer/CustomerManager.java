package Customer;

import java.util.ArrayList;

public class CustomerManager {
    private ArrayList<Customer> customers = new ArrayList<>();

    public void registerCustomer(Customer customer) {
        customers.add(customer);
    }

    public Customer getCustomer(String customerId) {
        for (Customer c : customers) {
            if (c.getCustomerId().equals(customerId)) {
                return c;
            }
        }
        return null;
    }

    public void listCustomers() {
        for (Customer c : customers) {
            System.out.println(c);
        }
    }
}
