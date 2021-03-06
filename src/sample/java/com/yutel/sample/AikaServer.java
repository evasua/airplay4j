package com.yutel.sample;

import java.net.InetAddress;

import com.yutel.silver.Aika;
import com.yutel.silver.Aika.AikaConnectListener;
import com.yutel.silver.exception.AirplayException;
import com.yutel.silver.vo.Device;

public class AikaServer implements AikaConnectListener {
	private static Device mDevice;
	private static AikaServer singleServer;

	private Aika mAika;
	private InetAddress mIP;
	private int mPort;

	private AikaServer(Device device, InetAddress ip) {
		mDevice = device;
		mIP = ip;
		mPort = 8888;
	}

	public void start() {
		mAika = Aika.create(mIP, mPort, null);
		// connect listener is must fist
		mAika.setConnectListener(this);
		// device info
		mAika.config(mDevice);
		mAika.start();
	}

	public Aika getAika() {
		return mAika;
	}

	public void stop() {
		mAika.stop();
	}

	public static AikaServer getInstance() {
		if (singleServer == null) {
			System.out.println("AikaServer is init or started,please wait!");
		}
		return singleServer;
	}

	public static AikaServer getInstance(Device device, InetAddress ip) {
		if (singleServer == null) {
			singleServer = new AikaServer(device, ip);
		}
		return singleServer;
	}

	@Override
	public void video(String url, String rate, String pos)
			throws AirplayException {
		VideoPlayer vp = new VideoPlayer();
		vp.init();
		System.out.println("video=" + url + ",pos=" + pos);
	}

	@Override
	public void photo() throws AirplayException {
		System.out.println("photo()");
	}
}
