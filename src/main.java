import java.io.IOException;

import jpcap.JpcapCaptor;
import jpcap.NetworkInterface;

public class main {
	public static void main(String[] args) {
		// ��������豸�б�
		NetworkInterface[] devices = JpcapCaptor.getDeviceList();
		NetworkInterface ni = null;
		String str = "";
		JpcapCaptor jpcap;
		for (int i = 0; i < devices.length; i++) {
			ni = devices[i];
			for (int j = 0; j < ni.addresses.length; j++) {
				// һ�����������ж����ַ,��ñ���IP��ַ
				str = ni.addresses[j].address.toString();
			}
			// ���������ϵ�ץȡ����
			try {
				jpcap = JpcapCaptor.openDevice(ni, 65535, true, 20);
				// ������Ӧ��ץȡ�̲߳�����
//				jpcap.loopPacket(-1, new getPacket(str));
				jpcap.loopPacket(-1, new getPacket("/10.211.55.4"));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		System.out.print(str);
	}
}
