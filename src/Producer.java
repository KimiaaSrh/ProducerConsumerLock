import java.util.Stack;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.locks.ReentrantLock;

public class Producer extends Thread{
	Stack<Integer> stack1; 
	ReentrantLock lck;
	MySemaphore semCon;
	MySemaphore semProd;
	private int id;
    Producer(Stack inputstack,ReentrantLock lckInput,int idInput,MySemaphore semConInput,MySemaphore semProdInput) { 
        this.stack1 = inputstack; 
        this.lck=lckInput;
        this.id=idInput;
        this.semCon=semConInput;
        this.semProd=semProdInput;
    } 
   
    @Override
    public void run() { 
    	for (int i = 0; i < 10; i++) {
//    		System.out.println("hello from producer "+this.id);
//    		try {
				semProd.aquire();
//			}/* catch (InterruptedException e1) {
				// TODO Auto-generated catch block
//				e1.printStackTrace();
//			}*/
//    		if(stack1.size()<5) { 
	           	 int pr=(int)(ThreadLocalRandom.current().nextInt());
	           	 lck.lock();
	           	 stack1.push(pr);
	           	 System.out.println(this.id+" produced "+pr);
	           	 lck.unlock(); 
	     		 semCon.release();
	     		 try {
					this.sleep((long)200);
				 } catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				 }
//            }
          	 
		}
         
    } 
}
