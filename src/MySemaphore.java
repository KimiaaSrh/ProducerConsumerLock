import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class MySemaphore {


//	Lock xx= new ReentrantLock();;
	LockProxy<Lock> lck;
	ReentrantLock blocklck=new ReentrantLock();
	ReentrantLock conslck=new ReentrantLock();
	int lckCounter;

	public MySemaphore(int inputlckCounter) {
		conslck.lock();
		this.lckCounter = inputlckCounter;
		lck= new LockProxy(new Lock() {
            @Override
            public void lock() {

            }

            @Override
            public void lockInterruptibly() throws InterruptedException {

            }

            @Override
            public boolean tryLock() {
                return false;
            }

            @Override
            public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
                return false;
            }

            @Override
            public void unlock() {

            }

            @Override
            public Condition newCondition() {
                return null;
            }
        });
		conslck.unlock();
//		this.lck=lckInput;
	}
	
	public void aquire() {
		this.blocklck.lock();
//		if (this.lckCounter>0) {
		this.lckCounter--;
//		}
		if (this.lckCounter==0)  
			this.lck.lock();
		this.blocklck.unlock();
	}
	
	public void release() {
//		if (this.lckCounter!=this.lckCounter) {
		this.blocklck.lock();
		this.lckCounter++;
//		}
//		else
		if(this.lckCounter>0)
			this.lck.unlock();
		this.blocklck.unlock();
	}
	
}
