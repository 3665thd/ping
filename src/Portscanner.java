import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

public class Portscanner {

	public static void main(String[] args) 
	throws InterruptedException, ExecutionException {
		final ExecutorService es =Executors.newFixedThreadPool(20);
		final String ip="127.0.0.1";
		final int timeout=200;
		final List<Future<ScanResult>> futures=new ArrayList<>();
		for(int port=1;port<1024;port++) {
			futures.add(portIsOpen(es, ip, port, timeout));
		}
		es.awaitTermination(20L, TimeUnit.MICROSECONDS);
		int openPorts=0;
		String openPortNumber = null;
		for(final Future<ScanResult> f:futures) {
			if(f.get().isOpen()) {
				openPorts++;
				if(openPortNumber==null)
					openPortNumber=f.get().getPort()+"";
				else
					openPortNumber +=","+ f.get().getPort();
				
			}
		}
		System.out.println(openPortNumber);
		System.out.println( "There are " + openPorts + "open ports on host" + ip +
				"(probed with a timeout of" + timeout + "ms)");
	}

	
public static Future<ScanResult> portIsOpen(final ExecutorService es,
		final String ip,final int port,final int timeout){
	return es.submit(new Callable<ScanResult>() {

		@Override
		public ScanResult call() throws Exception {
			try {
				Socket socket = new Socket();
				socket.connect(new InetSocketAddress(ip, port), timeout);
				socket.close();
				return new ScanResult(port, true);
			} catch (Exception e) {
				return new ScanResult(port,false);
			}
		}
		
	});
}
}
