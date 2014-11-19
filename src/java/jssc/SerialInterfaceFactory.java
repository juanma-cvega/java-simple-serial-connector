package jssc;

import static jssc.SerialInterfaceType.VM;

public abstract class SerialInterfaceFactory {

	private SerialInterfaceFactory() {
	}

	public static SerialInterface getInterface(SerialInterfaceType type) {
		if (VM.equals(type)) {
			return new SerialVMInterface();
		} else {
			return new SerialNativeInterface();
		}
	}
}
