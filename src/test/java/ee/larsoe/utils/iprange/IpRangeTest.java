package ee.larsoe.utils.iprange;

import org.junit.Test;

import java.net.UnknownHostException;

import static ee.larsoe.utils.iprange.IpRange.isPrivateIpRange;
import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertFalse;

public class IpRangeTest {
    @Test
    public void shouldConfirmInternalIpRanges() throws UnknownHostException {
        assertTrue(isPrivateIpRange("10.0.0.0"));
        assertTrue(isPrivateIpRange("10.0.0.1"));
        assertTrue(isPrivateIpRange("10.255.255.255"));
        assertTrue(isPrivateIpRange("127.0.0.0"));
        assertTrue(isPrivateIpRange("127.0.0.1"));
        assertTrue(isPrivateIpRange("127.255.255.255"));
        assertTrue(isPrivateIpRange("172.16.0.0"));
        assertTrue(isPrivateIpRange("172.16.0.1"));
        assertTrue(isPrivateIpRange("172.31.255.255"));
        assertTrue(isPrivateIpRange("192.168.0.0"));
        assertTrue(isPrivateIpRange("192.168.0.1"));
        assertTrue(isPrivateIpRange("192.168.255.255"));
    }

    @Test
    public void shouldConfirmExternalIpRanges() throws UnknownHostException {
        assertFalse(isPrivateIpRange("9.0.0.0"));
        assertFalse(isPrivateIpRange("11.255.255.255"));
        assertFalse(isPrivateIpRange("126.0.0.0"));
        assertFalse(isPrivateIpRange("128.255.255.255"));
        assertFalse(isPrivateIpRange("171.16.0.0"));
        assertFalse(isPrivateIpRange("173.31.255.255"));
        assertFalse(isPrivateIpRange("191.168.0.0"));
        assertFalse(isPrivateIpRange("193.168.255.255"));
    }

    @Test(expected = UnknownHostException.class)
    public void shouldThrowUnknownHostException() throws UnknownHostException {
        isPrivateIpRange("malformed address");
    }
}
