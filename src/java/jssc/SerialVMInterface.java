package jssc;

/**
 * Mock implementation of the SerialInterface. This aims to provide a fake version of the interface to allow the
 * SerialPort class to test its behavior without relying on a real connection. This provides us with a way to implement
 * the mock version of the camel-serialport component.
 * 
 * @author juan.vega
 *
 */
@SuppressWarnings("unused")
public class SerialVMInterface implements SerialInterface {

	private static final String END_MESSAGE = System.getProperty("line.separator");

	private String portName;
	private boolean isConfigurationEvent = true;
	private StringBuilder vmMessage = new StringBuilder();

	// Values not used at the moment because of requirements and lack of understanding
	private boolean portOpen = false;
	private int RTS = 0;
	private int DTR = 0;
	private long portHandle = 0L;
	private int baudRate = SerialPort.BAUDRATE_9600;
	private int dataBits = SerialPort.DATABITS_8;
	private int stopBits = SerialPort.STOPBITS_1;
	private int parity = SerialPort.PARITY_EVEN;
	private int flags = 0;
	private int CTS = 0;
	private int DSR = 0;
	private int RING = 0;
	private int RLSD = 0;
	private int flowControlMode = 0;
	private int eventMask = 0;

	/**
	 * Returns 1 as this is the value returned when the real port is open for communication
	 */
	@Override
	public long openPort(String portName, boolean useTIOCEXCL) {
		this.portName = portName;
		this.portOpen = true;
		return 1;
	}

	@Override
	public boolean purgePort(long handle, int flags) {
		vmMessage = new StringBuilder();
		return true;
	}

	@Override
	public boolean closePort(long handle) {
		this.portOpen = false;
		return true;
	}

	@Override
	public int[][] waitEvents(long handle) {
		while (!isConfigurationEvent && vmMessage.length() == 0) {
			try {
				Thread.sleep(500);
			} catch (InterruptedException ie) {
				ie.printStackTrace();
			}

		}
		int[][] mockEvents = new int[1][2];
		mockEvents[0][0] = 1;
		mockEvents[0][1] = 1;
		isConfigurationEvent = false;
		return mockEvents;
	}

	@Override
	public boolean setRTS(long handle, boolean value) {
		this.RTS = (value) ? 1 : 0;
		return true;
	}

	@Override
	public boolean setDTR(long handle, boolean value) {
		this.DTR = (value) ? 1 : 0;
		return true;
	}

	@Override
	public byte[] readBytes(long handle, int byteCount) {
		if (vmMessage.length() < byteCount) {
			byteCount = vmMessage.length();
		}
		String bytesToRead = vmMessage.substring(0, byteCount);
		vmMessage = vmMessage.delete(0, byteCount);
		return bytesToRead.getBytes();
	}

	@Override
	public boolean writeBytes(long handle, byte[] buffer) {
		vmMessage = vmMessage.append(new String(buffer)).append(END_MESSAGE);
		return true;
	}

	@Override
	public int[] getBuffersBytesCount(long handle) {
		byte[] bytes = vmMessage.toString().getBytes();
		int[] intArray = new int[bytes.length];
		for (int i = 0; i < bytes.length; i++) {
			intArray[i] = bytes[i];
		}
		return intArray;
	}

	@Override
	public boolean setFlowControlMode(long handle, int mask) {
		this.flowControlMode = mask;
		return true;
	}

	@Override
	public int getFlowControlMode(long handle) {
		return flowControlMode;
	}

	@Override
	public String[] getSerialPortNames() {
		return new String[] { portName };
	}

	@Override
	public int[] getLinesStatus(long handle) {
		return new int[] { CTS, DSR, RING, RLSD };
	}

	@Override
	public boolean sendBreak(long handle, int duration) {
		return true;
	}

	@Override
	public boolean setParams(long portHandle, int baudRate, int dataBits, int stopBits, int parity, boolean setRTS,
			boolean setDTR, int flags) {
		this.portHandle = portHandle;
		this.baudRate = baudRate;
		this.dataBits = dataBits;
		this.stopBits = stopBits;
		this.parity = parity;
		this.RTS = (setRTS) ? 1 : 0;;
		this.DTR = (setDTR) ? 1 : 0;;
		this.flags = flags;
		return true;
	}

	@Override
	public boolean setEventsMask(long portHandle, int mask) {
		eventMask = mask;
		return true;
	}

	@Override
	public int getEventsMask(long portHandle) {
		return eventMask;
	}

}
