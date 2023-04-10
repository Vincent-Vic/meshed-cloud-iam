package cn.meshed.cloud.iam.account.web;

import cn.dev33.satoken.oauth2.config.SaOAuth2Config;
import cn.dev33.satoken.oauth2.logic.SaOAuth2Handle;
import cn.dev33.satoken.stp.StpUtil;
import cn.meshed.cloud.iam.account.OAuth2ServerAdapter;
import cn.meshed.cloud.iam.account.data.LoginSuccessDTO;
import cn.meshed.cloud.iam.domain.account.System;
import cn.meshed.cloud.iam.domain.account.ability.DoLoginHandle;
import cn.meshed.cloud.iam.domain.account.ability.SystemAbility;
import com.alibaba.cola.dto.SingleResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
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
	private final SystemAbility systemAbility;


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
						setNotLoginView(() -> new ModelAndView("login.html")).
				// 登录处理函数
						setDoLoginHandle(getDoLoginHandle()).
				// 授权确认视图
						setConfirmView((clientId, scope) -> {
					SingleResponse<System> response = systemAbility.getSystemByClientId(clientId);
					if (response.isSuccess()) {
						Map<String, Object> map = new HashMap<>(2);
						map.put("clientId", response.getData().getName());
						map.put("scope", "用户信息");
						return new ModelAndView("confirm.html", map);
					}
					return new ModelAndView("error.html");
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

	
}
