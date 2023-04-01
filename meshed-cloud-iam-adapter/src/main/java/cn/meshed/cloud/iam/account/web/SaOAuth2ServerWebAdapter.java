package cn.meshed.cloud.iam.account.web;

import cn.dev33.satoken.context.SaHolder;
import cn.dev33.satoken.oauth2.config.SaOAuth2Config;
import cn.dev33.satoken.oauth2.logic.SaOAuth2Handle;
import cn.dev33.satoken.oauth2.logic.SaOAuth2Util;
import cn.dev33.satoken.stp.SaTokenInfo;
import cn.dev33.satoken.stp.StpUtil;
import cn.dev33.satoken.util.SaResult;
import cn.meshed.cloud.iam.account.OAuth2ServerAdapter;
import cn.meshed.cloud.iam.account.data.LoginSuccessDTO;
import cn.meshed.cloud.iam.domain.account.ability.DoLoginHandle;
import com.alibaba.cola.dto.Response;
import com.alibaba.cola.dto.SingleResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.BiFunction;

/**
 * Sa-OAuth2 Server端 控制器
 *
 * @author kong
 */
@RequiredArgsConstructor
@RestController
public class SaOAuth2ServerWebAdapter implements OAuth2ServerAdapter {

	private final DoLoginHandle doLoginHandle;


	/**
	 * 处理所有OAuth相关请求
	 *
	 * @return
	 */
	public Object request() {
		return SaOAuth2Handle.serverRequest();
	}

	/**
	 * Sa-OAuth2 定制化配置
	 *
	 * @param cfg
	 */
	@Autowired
	public void setSaOAuth2Config(SaOAuth2Config cfg) {
		cfg.
				// 未登录的视图
			setNotLoginView(()-> new ModelAndView("login.html")).
			// 登录处理函数
			setDoLoginHandle(getDoLoginHandle()).
			// 授权确认视图 
			setConfirmView((clientId, scope)->{
				Map<String, Object> map = new HashMap<>(2);
				map.put("clientId", clientId);
				map.put("scope", scope);
				return new ModelAndView("confirm.html", map);
			})
			;
	}

	private BiFunction<String, String, Object> getDoLoginHandle() {
		return (name, pwd) -> {
			SingleResponse<LoginSuccessDTO> response = doLoginHandle.loginHandle(name, pwd);
			if(response.isSuccess()) {
				LoginSuccessDTO loginSuccess = response.getData();
				StpUtil.login(loginSuccess.getId());
				return response;
			}
			return response;
		};
	}

	/**
	 * 全局异常拦截
 	 * @param e
	 * @return
	 */
	@ExceptionHandler
	public Response handlerException(Exception e) {
		e.printStackTrace(); 
		return Response.buildFailure("400",e.getMessage());
	}



	// ---------- 开放相关资源接口： Client端根据 Access-Token ，置换相关资源 ------------ 

	/**
	 * 获取Userinfo信息：昵称、头像、性别等等
	 * @return
	 */
	@RequestMapping("/oauth2/userinfo")
	public SaResult userinfo() {
		SaTokenInfo tokenInfo = StpUtil.getTokenInfo();
		System.out.println(tokenInfo);
		// 获取 Access-Token 对应的账号id 
		String accessToken = SaHolder.getRequest().getParamNotNull("access_token");
		Object loginId = SaOAuth2Util.getLoginIdByAccessToken(accessToken);
		System.out.println("-------- 此Access-Token对应的账号id: " + loginId);
		
		// 校验 Access-Token 是否具有权限: userinfo
		SaOAuth2Util.checkScope(accessToken, "userinfo");
		
		// 模拟账号信息 （真实环境需要查询数据库获取信息）
		Map<String, Object> map = new LinkedHashMap<String, Object>();
		map.put("nickname", "shengzhang_");
		map.put("avatar", "http://xxx.com/1.jpg");
		map.put("age", "18");
		map.put("sex", "男");
		map.put("address", "山东省 青岛市 城阳区");
		return SaResult.data(map);
	}
	
}
