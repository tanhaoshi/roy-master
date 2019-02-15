package roy.application.master.utils.adb;

import android.util.Log;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

public class AdbShellUtils {

	public static void runShell(String command) {
		Process process = null;
		DataOutputStream dos = null;

		try {

			process = Runtime.getRuntime().exec("/system/xbin/su");

			dos = new DataOutputStream(process.getOutputStream());
			// dos.writeBytes("echo 1 > /sys/class/leds/white:pd27:led3/brightness");
			
			dos.writeBytes(command + "\n");
			dos.writeBytes("exit\n");
			dos.flush();

			try {
				process.waitFor();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			dos.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 获取屏幕密度
	 */
	public static String runModificationDensity() {
		StringBuffer mRespBuff = new StringBuffer();
		try {
			Process mProcess = Runtime.getRuntime().exec("wm density");
			BufferedReader mReader = new BufferedReader(new InputStreamReader(mProcess.getInputStream()));
			char[] buff = new char[1024];
			int ch = 0;
			while ((ch = mReader.read(buff)) != -1) {
				mRespBuff.append(buff, 0, ch);
			}
			mReader.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mRespBuff.toString();
	}

	// 判断是否具有ROOT权限
	public static boolean is_root(){
		boolean res = false;
		try{
			if ((!new File("/system/bin/su").exists()) &&
					(!new File("/system/xbin/su").exists())){
				res = false;
			}
			else {
				res = true;
			}
		}
		catch (Exception e) {

		}
		return res;
	}

	// 获取ROOT权限
	public static void get_root(){
		if (is_root()){
			Log.d("get_root","已经具有ROOT权限!");
		}
		else{
			try{
				Log.d("get_root","正在获取ROOT权限...");
				Runtime.getRuntime().exec("su");
			}
			catch (Exception e){
				Log.d("get_root","获取ROOT权限时出错!");
			}
		}
	}

	public static void moveFileToSystem(String filePath, String sysFilePath) {
		runShell("mount -o rw,remount /system");
		runShell("chmod 777 /system/media");
		runShell("cp  " + filePath + " " + sysFilePath);
	}

}
