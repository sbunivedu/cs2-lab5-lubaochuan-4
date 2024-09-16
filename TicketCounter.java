import jsjf.QueueADT;
import jsjf.LinkedQueue;

public class TicketCounter{
  private final static int PROCESS = 120;
  private final static int MAX_CASHIERS = 10;
  private final static int NUM_CUSTOMERS = 100;
  
  public static void main(String[] args){
    Customer customer;
    QueueADT<Customer> customerQueue = new LinkedQueue<Customer>();
    int[] cashierTime = new int[MAX_CASHIERS];
    int totalTime, averageTime, departs, start;

    // run the simulation for various number of cashiers 
    for (int cashiers = 0; cashiers < MAX_CASHIERS; cashiers++){
      // set each cashiers time to zero initially
      for (int count = 0; count < cashiers; count++){
        cashierTime[count] = 0;
      }
      
      // load customer queue
      for (int count = 1; count <= NUM_CUSTOMERS; count++){
        customerQueue.enqueue(new Customer(count * 15));
      }

      totalTime = 0;
      System.out.println("customers:"+customerQueue.size());

      // process all customers in the queue
      while (!customerQueue.isEmpty()){
        for (int count = 0; count <= cashiers; count++){
          if (!customerQueue.isEmpty()){
            customer = customerQueue.dequeue();
            if (customer.getArrivalTime() > cashierTime[count]){
              start = customer.getArrivalTime();
            }else{
              start = cashierTime[count];
            }
            departs = start + PROCESS;
            customer.setDepartureTime(departs);
            cashierTime[count] = departs;
            totalTime += customer.totalTime();
          }
        }
      }

      // output results for this simulation
      averageTime = totalTime / NUM_CUSTOMERS;
      System.out.println("Number of cashiers: " + (cashiers + 1));
      System.out.println("Average time: " + averageTime + "\n");
    }
  }
}