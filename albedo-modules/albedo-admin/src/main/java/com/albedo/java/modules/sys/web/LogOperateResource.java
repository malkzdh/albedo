/*
 *  Copyright (c) 2019-2020, somewhere (somewhere0813@gmail.com).
 *  <p>
 *  Licensed under the GNU Lesser General Public License 3.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *  <p>
 * https://www.gnu.org/licenses/lgpl.html
 *  <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.albedo.java.modules.sys.web;

import com.albedo.java.common.core.util.R;
import com.albedo.java.common.core.vo.PageModel;
import com.albedo.java.common.log.annotation.Log;
import com.albedo.java.common.log.enums.BusinessType;
import com.albedo.java.common.util.ExcelUtil;
import com.albedo.java.modules.sys.domain.LogOperate;
import com.albedo.java.modules.sys.service.LogOperateService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.Set;

/**
 * <p>
 * 操作日志表 前端控制器
 * </p>
 *
 * @author somewhere
 * @since 2019/2/1
 */
@RestController
@AllArgsConstructor
@RequestMapping("${application.admin-path}/sys/log-operate")
public class LogOperateResource {
	private final LogOperateService logOperateService;

	/**
	 * 简单分页查询
	 *
	 * @param pm 分页对象
	 * @return
	 */
	@GetMapping
	@PreAuthorize("@pms.hasPermission('sys_logOperate_view')")
	public R<IPage> getPage(PageModel pm) {
		return R.buildOkData(logOperateService.page(pm));
	}

	/**
	 * 删除操作日志
	 *
	 * @param ids ID
	 * @return success/false
	 */
	@DeleteMapping
	@PreAuthorize("@pms.hasPermission('sys_logOperate_del')")
	@Log(value = "操作日志", businessType = BusinessType.DELETE)
	public R removeById(@RequestBody Set<String> ids) {
		return R.buildOkData(logOperateService.removeByIds(ids));
	}


	@Log(value = "操作日志", businessType = BusinessType.EXPORT)
	@GetMapping(value = "/export")
	@PreAuthorize("@pms.hasPermission('sys_logOperate_export')")
	public void export(PageModel pm, HttpServletResponse response) {
		ExcelUtil<LogOperate> util = new ExcelUtil(LogOperate.class);
		util.exportExcel(logOperateService.list(Wrappers.emptyWrapper()), "操作日志", response);
	}

}
