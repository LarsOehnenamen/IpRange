package ee.larsoe.utils.iprange;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class IpRange {
    /**
     * Returns whether the caller's IP address is located outside the internal IP range.
     * <p>
     * Internal IP ranges
     * <p>
     * ::1          -   IPv6  loopback
     * 10.0.0.0     -   10.255.255.255  (10/8 prefix)
     * 127.0.0.0    -   127.255.255.255 (127/8 prefix)
     * 172.16.0.0   -   172.31.255.255  (172.16/12 prefix)
     * 192.168.0.0  -   192.168.255.255 (192.168/16 prefix)
     *
     * @param callerIp human-readable address label
     * @return true if caller IP is located within the internal IP ranges
     * @throws UnknownHostException in case the caller IP is malformed/invalid
     */
    public boolean isPrivateIpRange(String callerIp) throws UnknownHostException {
        byte[] ipBytes;
        ipBytes = InetAddress.getByName(callerIp).getAddress();
        int[] ip = new int[ipBytes.length];
        int i = 0;
        for (byte b : ipBytes) {
            ip[i++] = (b & 0xFF);
        }
        switch (ip[0]) {
            case 10:
            case 127:
                return true;
            case 172:
                return ip[1] >= 16 && ip[1] < 32;
            case 192:
                return ip[1] == 168;
            default:
                return false;
        }
    }
}
