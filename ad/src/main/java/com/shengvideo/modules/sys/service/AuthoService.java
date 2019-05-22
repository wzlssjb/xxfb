package com.shengvideo.modules.sys.service;

import com.google.gson.Gson;
import com.shengvideo.common.exception.RRException;
import me.chanjar.weixin.common.util.http.HttpType;
import me.chanjar.weixin.common.util.http.RequestExecutor;
import me.chanjar.weixin.common.util.http.RequestHttp;
import me.chanjar.weixin.common.util.http.okhttp.OkHttpSimpleGetRequestExecutor;
import okhttp3.OkHttpClient;
import org.springframework.stereotype.Service;


import java.util.Map;


@Service
public class AuthoService {
    public boolean autho(){
//        try {
//            RequestExecutor<String,String> executor = OkHttpSimpleGetRequestExecutor.create(new AuthoHttp());
//            String res = executor.execute("http://autho.shengvideo.com/user/autho",null);
////            String res = executor.execute("http://182.61.37.88:8081/user/autho",null);
//            Gson gson = new Gson();
//            Map r = gson.fromJson(res,Map.class);
//            double code = (double) r.get("code");
//            if (code != 0.0){
//                throw new RRException((String) r.get("msg"));
//            }
//        }catch (RRException e){
//            throw e;
//        }catch (Exception e){
//            throw new RRException("授权验证失败,请联系相关人员");
//        }
        return true;
    }

}

class AuthoHttp<H,P> implements RequestHttp {

    public AuthoHttp() {
    }

    @Override
    public Object getRequestHttpClient() {
        return new OkHttpClient();
    }

    @Override
    public Object getRequestHttpProxy() {
        return null;
    }

    @Override
    public HttpType getRequestType() {
        return HttpType.OK_HTTP;
    }
}
