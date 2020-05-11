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

package com.albedo.java.modules.sys.service;

import com.albedo.java.common.persistence.service.DataService;
import com.albedo.java.modules.sys.domain.Role;
import com.albedo.java.modules.sys.domain.dto.RoleDto;
import com.albedo.java.modules.sys.repository.RoleRepository;

import java.util.List;
import java.util.Set;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author somewhere
 * @since 2019/2/1
 */
public interface RoleService extends DataService<RoleRepository, Role, RoleDto, String> {

	/**
	 * 通过用户ID，查询角色信息
	 *
	 * @param userId
	 * @return
	 */
	List<Role> findRolesByUserIdList(String userId);

	List<String> findRoleDeptIdList(String id);

	/**
	 * 通过角色ID，删除角色
	 *
	 * @param ids
	 * @return
	 */
	Boolean removeRoleByIds(Set<String> ids);

	void lockOrUnLock(Set<String> idList);
}
