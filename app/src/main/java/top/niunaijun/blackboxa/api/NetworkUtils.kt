package top.niunaijun.blackboxa.api

import android.util.Log
import java.net.Inet4Address
import java.net.NetworkInterface

/** Resolves the first site-local ipv4 address, used for the api hints. */
object NetworkUtils {

    private const val TAG = "NetworkUtils"

    fun localAddress(): String {
        try {
            for (network in NetworkInterface.getNetworkInterfaces()) {
                if (!network.isUp || network.isLoopback) continue
                for (address in network.inetAddresses) {
                    if (address is Inet4Address && !address.isLoopbackAddress &&
                        address.isSiteLocalAddress
                    ) {
                        return address.hostAddress ?: continue
                    }
                }
            }
        } catch (e: Exception) {
            Log.w(TAG, "resolve local address failed: ${e.message}")
        }
        return "0.0.0.0"
    }
}
