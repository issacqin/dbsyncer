package org.dbsyncer.web.controller.system;

import org.dbsyncer.biz.ConfigService;
import org.dbsyncer.biz.vo.RestResult;
import org.dbsyncer.web.controller.BaseController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Controller
@RequestMapping(value = "/system")
public class SystemController extends BaseController {

	private final Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private ConfigService configService;

	@RequestMapping("")
	public String index(ModelMap model) {
		model.put("config", configService.getConfig());
		return "system/system";
	}

	@PostMapping("/edit")
	@ResponseBody
	public RestResult edit(HttpServletRequest request) {
		try {
			Map<String, String> params = getParams(request);
			return RestResult.restSuccess(configService.edit(params));
		} catch (Exception e) {
			logger.error(e.getLocalizedMessage(), e.getClass());
			return RestResult.restFail(e.getMessage());
		}
	}

	@GetMapping("/queryConfig")
	@ResponseBody
	public RestResult queryConfig(HttpServletRequest request) {
		try {
			return RestResult.restSuccess(configService.getConfig());
		} catch (Exception e) {
			logger.error(e.getLocalizedMessage(), e.getClass());
			return RestResult.restFail(e.getMessage());
		}
	}

}
