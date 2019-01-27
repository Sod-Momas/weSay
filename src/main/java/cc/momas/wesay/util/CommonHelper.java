package cc.momas.wesay.util;

public abstract class CommonHelper {
	/**
	 * 用于统计在线人数
	 */
	private volatile static int onlineCount = 0;

	public static boolean isNullOrEmpty(String... strings) {
		for (String string : strings) {
			if (string == null || string.length() < 1) {
				return true;
			}
		}
		return false;
	}

	public static int getOnlineCount(){
		return CommonHelper.onlineCount;
	}
	/**
	 * 用户上线,用于统计在线人数
	 */
	public synchronized static void userLogin() {
		CommonHelper.onlineCount++;
	}

	/**
	 * 用户下线,用于统计在线人数
	 */
	public synchronized static void userLogout() {
		CommonHelper.onlineCount--;
	}
}
