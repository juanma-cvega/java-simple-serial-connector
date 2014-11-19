package jssc;

public interface SerialInterface {

	/**
	 * Open port
	 *
	 * @param portName
	 *            name of port for opening
	 * @param useTIOCEXCL
	 *            enable/disable using of <b>TIOCEXCL</b>. Take effect only on *nix based systems
	 * 
	 * @return handle of opened port or -1 if opening of the port was unsuccessful
	 */
	long openPort(String portName, boolean useTIOCEXCL);

	/**
	 * Purge of input and output buffer
	 * 
	 * @param handle
	 *            handle of opened port
	 * @param flags
	 *            flags specifying required actions for purgePort method
	 *
	 * @return If the operation is successfully completed, the method returns true, otherwise false
	 */
	boolean purgePort(long handle, int flags);

	/**
	 * Close port
	 * 
	 * @param handle
	 *            handle of opened port
	 * 
	 * @return If the operation is successfully completed, the method returns true, otherwise false
	 */
	boolean closePort(long handle);

	/**
	 * Wait events
	 *
	 * @param handle
	 *            handle of opened port
	 *
	 * @return Method returns two-dimensional array containing event types and their values (<b>events[i][0] - event
	 *         type</b>, <b>events[i][1] - event value</b>).
	 */
	int[][] waitEvents(long handle);

	/**
	 * Change RTS line state
	 * 
	 * @param handle
	 *            handle of opened port
	 * @param value
	 *            <b>true - ON</b>, <b>false - OFF</b>
	 *
	 * @return If the operation is successfully completed, the method returns true, otherwise false
	 */
	boolean setRTS(long handle, boolean value);

	/**
	 * Change DTR line state
	 *
	 * @param handle
	 *            handle of opened port
	 * @param value
	 *            <b>true - ON</b>, <b>false - OFF</b>
	 *
	 * @return If the operation is successfully completed, the method returns true, otherwise false
	 */
	boolean setDTR(long handle, boolean value);

	/**
	 * Read data from port
	 * 
	 * @param handle
	 *            handle of opened port
	 * @param byteCount
	 *            count of bytes required to read
	 * 
	 * @return Method returns the array of read bytes
	 */
	byte[] readBytes(long handle, int byteCount);

	/**
	 * Write data to port
	 * 
	 * @param handle
	 *            handle of opened port
	 * @param buffer
	 *            array of bytes to write
	 * 
	 * @return If the operation is successfully completed, the method returns true, otherwise false
	 */
	boolean writeBytes(long handle, byte[] buffer);

	/**
	 * Get bytes count in buffers of port
	 *
	 * @param handle
	 *            handle of opened port
	 *
	 * @return Method returns the array that contains info about bytes count in buffers: <br>
	 *         <b>element 0</b> - input buffer</br> <br>
	 *         <b>element 1</b> - output buffer</br>
	 *
	 * @since 0.8
	 */
	int[] getBuffersBytesCount(long handle);

	/**
	 * Set flow control mode
	 *
	 * @param handle
	 *            handle of opened port
	 * @param mask
	 *            mask of flow control mode
	 *
	 * @return If the operation is successfully completed, the method returns true, otherwise false
	 *
	 * @since 0.8
	 */
	boolean setFlowControlMode(long handle, int mask);

	/**
	 * Get flow control mode
	 *
	 * @param handle
	 *            handle of opened port
	 *
	 * @return Mask of setted flow control mode
	 *
	 * @since 0.8
	 */
	int getFlowControlMode(long handle);

	/**
	 * Get serial port names like an array of String
	 *
	 * @return unsorted array of String with port names
	 */
	String[] getSerialPortNames();

	/**
	 * Getting lines states
	 * 
	 * @param handle
	 *            handle of opened port
	 *
	 * @return Method returns the array containing information about lines in following order: <br>
	 *         <b>element 0</b> - <b>CTS</b> line state</br> <br>
	 *         <b>element 1</b> - <b>DSR</b> line state</br> <br>
	 *         <b>element 2</b> - <b>RING</b> line state</br> <br>
	 *         <b>element 3</b> - <b>RLSD</b> line state</br>
	 */
	int[] getLinesStatus(long handle);

	/**
	 * Send Break singnal for setted duration
	 * 
	 * @param handle
	 *            handle of opened port
	 * @param duration
	 *            duration of Break signal
	 * @return If the operation is successfully completed, the method returns true, otherwise false
	 *
	 * @since 0.8
	 */
	boolean sendBreak(long handle, int duration);

	/**
	 * Setting the parameters of opened port
	 *
	 * @param handle
	 *            handle of opened port
	 * @param baudRate
	 *            data transfer rate
	 * @param dataBits
	 *            number of data bits
	 * @param stopBits
	 *            number of stop bits
	 * @param parity
	 *            parity
	 * @param setRTS
	 *            initial state of RTS line (ON/OFF)
	 * @param setDTR
	 *            initial state of DTR line (ON/OFF)
	 * @param flags
	 *            additional Native settings. Take effect only on *nix based systems
	 * 
	 * @return If the operation is successfully completed, the method returns true, otherwise false
	 */
	boolean setParams(long portHandle, int baudRate, int dataBits, int stopBits, int parity, boolean setRTS,
			boolean setDTR, int flags);

	/**
	 * Set events mask
	 *
	 * @param handle
	 *            handle of opened port
	 * @param mask
	 *            events mask
	 * 
	 * @return If the operation is successfully completed, the method returns true, otherwise false
	 */
	boolean setEventsMask(long portHandle, int mask);

	/**
	 * Get events mask
	 * 
	 * @param handle
	 *            handle of opened port
	 * 
	 * @return Method returns event mask as a variable of <b>int</b> type
	 */
	int getEventsMask(long portHandle);

}
