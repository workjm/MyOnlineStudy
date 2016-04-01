package com.workjm.myonlinestudy.utils;

import android.content.Context;

public class ServerHelper {
	private static final String TAG = "ServerHelper";


	public static final String ThemeUrl = "http://fans.jstinno.com/uploadapk/";
	public static final String THEME_ATION = "getJsonTheme.action";












	public static final String DATA_URL = "/web/ThemeAction!get.action?action=11&phoneTypeId=1";
	public static final String TYPE_ACTION="getJsonThemeType.action";
	public static final String RELATION_ACTION="getJsonThemeRelation";
    public static final String DELETED_ACTION="getDelIds.action";
	public static final String THEME_PARAM = "themeLastSyncDate";
	public static final String TYPE_PARAM = "typeLastSyncDate";
	public static final String RELATION_PARAM = "relationLastSyncDate";
	public static final String IMEI_PARAM = "imei";
	public static final String APK_VERSION_CODE_PARAM = "apkVersionCode";
	public static final String PRODUCTMODEL_PARAM = "productModel";
	public static final String PRODUCTBRAND_PARAM = "productBrand";
	public static final String PRODUCTNAME_PARAM = "productName";
	public static final String PRODUCTMANUFACTURER_PARAM = "productManufacturer";
	public static final String CUSTOMBUILDVERSION_PARAM = "customBuildVersion";
	public static final String INTERNALBUILDVERSION_PARAM = "internalBuildVersion";



	///////////server order param
	public static final String ORDERBY_NEWEST = "1";
	public static final String ORDERBY_TOP = "2";
	public static final String ORDERBY_HOT = "3";

	public static final String PARAM_mediatype = "mediatype";
	public static final String PARAM_dpi = "dpi";
	public static final String PARAM_resolution = "resolution";
	public static final String PARAM_model = "model";
	public static final String PARAM_hwmainkeys = "hwmainkeys";
	public static final String PARAM_apkversion = "apkVersionCode";
	public static final String PARAM_templateversion = "templateversion";
	public static final String PARAM_vendor = "vendor";
	
	public static final String PARAM_order = "order";
	public static final String PARAM_language = "language";
	public static final String PARAM_region = "region";
	
	private String mResolution = "";
	private static ServerHelper mHelper;

	private Context mContext;


	public static ServerHelper getInstance(Context context) {
		if (mHelper == null) {
			mHelper = new ServerHelper(context);
		}
		return mHelper;
	}

	private ServerHelper(Context context) {
		this.mContext = context;
	}


}
