package com.meimengmeng.one.bean;

import java.util.List;

/**
 * Create by  leijiaxq
 * Date       2017/3/8 18:39
 * Describe
 */

public class UABean {

    /**
     * result : ok
     * ualist : [{"pk":"com.android.browser0","ua":"Mozilla/5.0 (iPhone; CPU iPhone OS 8_4 like Mac OS X) AppleWebKit/600.1.4 (KHTML, like Gecko) Mobile/12H143 MicroMessenger/6.2.3 NetType/WIFI Language/zh_CN"},{"pk":"com.android.browser1","ua":"Mozilla/5.0 (Mobile; Windows Phone 8.1; Android 4.0; ARM; Trident/7.0; Touch; rv:11.0; IEMobile/11.0; NOKIA; Lumia 635) like iPhone OS 7_0_3 Mac OS X AppleWebKit/537 (KHTML, like Gecko) Mobile Safari/537 MicroMessenger/6.0"},{"pk":"com.android.browser2","ua":"Mozilla/5.0 (Linux; Android 4.4.2; SM-T330NU Build/KOT49H) AppleWebKit/537.36 (KHTML, like Gecko) Version/4.0 Chrome/30.0.0.0 Safari/537.36 MicroMessenger/6.2.0.54_r1169949.561 NetType/WIFI Language/zh_HK"},{"pk":"com.android.browser3","ua":"Mozilla/5.0 (iPhone; CPU iPhone OS 7_0_4 like Mac OS X) AppleWebKit/537.51.1 (KHTML, like Gecko) Mobile/11B554a MicroMessenger/6.1.4 NetType/WIFI"},{"pk":"com.android.browser4","ua":"Dalvik/1.6.0 (Linux; U; Android 4.4.2; SGH-I257M Build/KOT49H) MicroMessenger/6.1.0.66_r1062275.542 NetType/WIFI"},{"pk":"com.android.browser5","ua":"Mozilla/5.0 (Linux; U; Android 4.4.2; zh-cn; SGH-I257M Build/KOT49H) AppleWebKit/533.1 (KHTML, like Gecko)Version/4.0 MQQBrowser/5.5 Mobile Safari/533.1 MicroMessenger/6.0.2.40_r954892.520 NetType/WIFI"},{"pk":"com.android.browser6","ua":"Mozilla/5.0 (Macintosh; Intel Mac OS X 10_7_2) AppleWebKit/534.51.22 (KHTML, like Gecko) Version/5.1.1 Safari/534.51.22"},{"pk":"com.android.browser7","ua":"Mozilla/5.0 (Linux; U; Android 4.4.2; zh-cn; Lenovo S850t Build/KOT49H) AppleWebKit/537.36 (KHTML, like Gecko)Version/4.0 MQQBrowser/6.0 Mobile Safari/537.36"}]
     */

    public String                       result;
    public List<UABean.UalistEntity> ualist;

    public static class UalistEntity {
        /**
         * pk : com.android.browser0
         * ua : Mozilla/5.0 (iPhone; CPU iPhone OS 8_4 like Mac OS X) AppleWebKit/600.1.4 (KHTML, like Gecko) Mobile/12H143 MicroMessenger/6.2.3 NetType/WIFI Language/zh_CN
         */

        public String pk;
        public String ua;
    }
}
