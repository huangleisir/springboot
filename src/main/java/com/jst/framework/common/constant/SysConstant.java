package com.jst.framework.common.constant;

public class SysConstant {
	
	
	/** dubbo服务组*/
	public static final String DUBBO_GROUP = "PARK";
	/*01-已下单；02-已取消；03-停车中；04-代扣失败；05-已完成
	支持多个，多个用逗号分开如01,02 */
	public final static String ORDER_STATUS_ONE = "01";
	public final static String ORDER_STATUS_TWO = "02";
	public final static String ORDER_STATUS_THREE = "03";
	public final static String ORDER_STATUS_FOUR = "04";
	public final static String ORDER_STATUS_FIVE = "05";

	 /** 提取出字符串中的中文字符正则 */
    public static final String EXTRACT_CHINESE = "[\\u4e00-\\u9fa5]";
    /** 提取出字符串中的字母和数字正则 */
    public static final String EXTRACT_SUBTITLES_NUMBERS = "[a-z||A-Z||0-9]";
	
    public static final String INCREASE_NUM_MSG = "增加月卡";
    public static final String REDUCE_NUM_MSG = "减少月卡";
    public static final String UNIT_MSG = "张";
    
}
