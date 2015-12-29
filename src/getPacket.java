import java.util.ArrayList;
import jpcap.JpcapCaptor;
import jpcap.PacketReceiver;
import jpcap.packet.ARPPacket;
import jpcap.packet.ICMPPacket;
import jpcap.packet.IPPacket;
import jpcap.packet.Packet;
import jpcap.packet.TCPPacket;
import jpcap.packet.UDPPacket;

public class getPacket implements PacketReceiver {
	String ipAddr;// ����ip��ַ����

	private int upLoadPacketSize = 0;// �ϴ����ݰ����ֽ���
	private int downLoadPacketSize = 0;// �������ݰ����ֽ���

	private int upLoadIPPacketSize = 0;// �ϴ�IP���ݰ����ֽ���
	private int downLoadIPPacketSize = 0;// ����IP���ݰ����ֽ���

	private int upLoadTCPPacketSize = 0; // �ϴ�TCP���ݰ����ֽ���
	private int downLoadTCPPacketSize = 0; // ����TCP���ݰ����ֽ���

	private int upLoadUDPPacketSize = 0; // �ϴ�UDP���ݰ����ֽ���
	private int downLoadUDPPacketSize = 0; // ����UDP���ݰ����ֽ���

	private static Packet currentPacket = new Packet();// ��ʾ����ץȡ�İ�

	public int getupLoadPacketSize() {
		return upLoadPacketSize;
	}

	public int getdownLoadPacketSize() {
		return downLoadPacketSize;
	}

	public int getupLoadIPPacketSize() {
		return upLoadIPPacketSize;
	}

	public int getdownLoadIPPacketSize() {
		return downLoadIPPacketSize;
	}

	public int getupLoadTCPPacketSize() {
		return upLoadTCPPacketSize;
	}

	public int getdownLoadTCPPacketSize() {
		return downLoadTCPPacketSize;
	}

	public int getupLoadUDPPacketSize() {
		return upLoadUDPPacketSize;
	}

	public int getdownLoadUDPPacketSize() {
		return downLoadUDPPacketSize;
	}

	public getPacket() {
		// ��ʵ��
	}

//	public getPacket(String[] ipAddr, int ipcount) {
//		this.ipAddr = ipAddr;
//		this.ipcount = ipcount;
//	}
	
	public getPacket(String ipAddr) {
		this.ipAddr = ipAddr;
	}

	// �жϵ�ǰץ�İ��Ƿ�Ϊ�գ���Ϊ�����ʾ����������û����
	public static boolean isPacketEmpty() {
		if (currentPacket == null) {
			return true;
		} else {
			return false;
		}
	}

	// ��ý�ȡ�����ݰ�
	public void receivePacket(Packet p) {
		// currentPacket=null;
		if (p instanceof TCPPacket) {// �����ȡ����TCP��
			TCPPacket tcp = (TCPPacket) p;
			if (tcp.src_ip.getHostAddress().equals(ipAddr.substring(1))) {// ���TCP����ԴIP��ַ���ڱ���ip��˰�Ϊ�ϴ���
				upLoadTCPPacketSize += tcp.len;
				upLoadPacketSize += tcp.len;
			} else if (tcp.dst_ip.getHostAddress().equals(
					ipAddr.substring(1))) {// ���TCP����Ŀ��IP��ַ���ڱ���ip��˰�Ϊ���ذ�
				downLoadTCPPacketSize += tcp.len;
				downLoadPacketSize += tcp.len;
			}
		}
		if (p instanceof UDPPacket) {// �����ȡ����UDP��
			UDPPacket udp = (UDPPacket) p;
			if (udp.src_ip.getHostAddress().equals(ipAddr.substring(1))) {// ���UDP����ԴIP��ַ���ڱ���ip��˰�Ϊ�ϴ���
				upLoadUDPPacketSize += udp.len;
				upLoadPacketSize += udp.len;
			} else if (udp.dst_ip.getHostAddress().equals(
					ipAddr.substring(1))) {// ���UDP����Ŀ��IP��ַ���ڱ���ip��˰�Ϊ���ذ�
				downLoadUDPPacketSize += udp.len;
				downLoadPacketSize += udp.len;
			}
		}
		if (p instanceof IPPacket) {// �����ȡ����IP��
			IPPacket ippacket = (IPPacket) p;
			if (ippacket.src_ip.getHostAddress().equals(ipAddr.substring(1))) {// ���IP����ԴIP��ַ���ڱ���ip��˰�Ϊ�ϴ���
				upLoadIPPacketSize += ippacket.len;
				upLoadPacketSize += ippacket.len;
			} else if (ippacket.dst_ip.getHostAddress().equals(
					ipAddr.substring(1))) {// ���IP����Ŀ��IP��ַ���ڱ���ip��˰�Ϊ���ذ�
				downLoadIPPacketSize += ippacket.len;
				downLoadPacketSize += ippacket.len;
				currentPacket = p;
			}
		}

//		trafficData.setupLoadTCPPacketSize(upLoadTCPPacketSize);
//		trafficData.setdownLoadTCPPacketSize(downLoadTCPPacketSize);
//		trafficData.setupLoadUDPPacketSize(upLoadUDPPacketSize);
//		trafficData.setdownLoadUDPPacketSize(downLoadUDPPacketSize);
//		trafficData.setupLoadIPPacketSize(upLoadIPPacketSize);
//		trafficData.setdownLoadIPPacketSize(downLoadIPPacketSize);
//		trafficData.setUpLoadPacketSize(upLoadPacketSize);
//		trafficData.setdownLoadPacketSize(downLoadPacketSize);

		System.out.println("�ϴ����ݰ���" + upLoadPacketSize + "�ֽ�");
		System.out.println("�������ݰ���" + downLoadPacketSize + "�ֽ�");
		System.out.println("IP�ϴ����ݰ���" + upLoadIPPacketSize + "�ֽ�");
		System.out.println("IP���ش����ݰ���" + downLoadIPPacketSize + "�ֽ�");
		System.out.println("TCP�ϴ����ݰ���" + upLoadTCPPacketSize + "�ֽ�");
		System.out.println("TCP���ش����ݰ���" + downLoadTCPPacketSize + "�ֽ�");
		System.out.println("UDP�ϴ����ݰ���" + upLoadUDPPacketSize + "�ֽ�");
		System.out.println("UDP���ش����ݰ���" + downLoadUDPPacketSize + "�ֽ�");
//		System.out.println(System.currentTimeMillis());
	}
}