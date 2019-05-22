package com.shengvideo.modules.sys.controller;

import com.shengvideo.common.annotation.SysLog;
import com.shengvideo.common.exception.RRException;
import com.shengvideo.common.utils.Constant;
import com.shengvideo.common.utils.PageUtils;
import com.shengvideo.common.utils.R;
import com.shengvideo.common.validator.ValidatorUtils;
import com.shengvideo.modules.sys.entity.SysRoleEntity;
import com.shengvideo.modules.sys.service.SysRoleMenuService;
import com.shengvideo.modules.sys.service.SysRoleService;
import com.shengvideo.modules.sys.service.SysUserRoleService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 角色管理
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2016年11月8日 下午2:18:33
 */
@RestController
@RequestMapping("/sys/role")
public class SysRoleController extends AbstractController {
	@Autowired
	private SysRoleService sysRoleService;
	@Autowired
	private SysRoleMenuService sysRoleMenuService;
	@Autowired
	private SysUserRoleService sysUserRoleService;

	/**
	 * 角色列表
	 */
	@GetMapping("/list")
	@RequiresPermissions("sys:role:list")
	public R list(@RequestParam Map<String, Object> params){
		//如果不是超级管理员，则只查询自己创建的角色列表
		if(getUserId() != Constant.SUPER_ADMIN){
			params.put("createUserId", getUserId());
		}
		params.put("type",1);
		PageUtils page = sysRoleService.queryPage(params);

		return R.ok().put("page", page);
	}
	
	/**
	 * 角色列表
	 */
	@GetMapping("/select")
	@RequiresPermissions("sys:role:select")
	public R select(){
		Map<String, Object> map = new HashMap<>();
		map.put("type",1);
		//如果不是超级管理员，则只查询自己所拥有的角色列表
		if(getUserId() != Constant.SUPER_ADMIN){
			map.put("createUserId", getUserId());
		}
		List<SysRoleEntity> list = sysRoleService.selectByMap(map);
		
		return R.ok().put("list", list);
	}
	
	/**
	 * 角色信息
	 */
	@GetMapping("/info/{roleId}")
	@RequiresPermissions("sys:role:info")
	public R info(@PathVariable("roleId") Long roleId){
		SysRoleEntity role = sysRoleService.selectById(roleId);
		
		//查询角色对应的菜单
		List<Long> menuIdList = sysRoleMenuService.queryMenuIdList(roleId);
		role.setMenuIdList(menuIdList);
		
		return R.ok().put("role", role);
	}
	
	/**
	 * 保存角色
	 */
	@SysLog("保存角色")
	@PostMapping("/save")
	@RequiresPermissions("sys:role:save")
	public R save(@RequestBody SysRoleEntity role){
		ValidatorUtils.validateEntity(role);
		role.setCreateUserId(getUserId());
		if (role.getType() != null && role.getType() == 2) {
            SysRoleEntity old = sysRoleService.getByUser(role.getUid());
		    if (old == null) {
                sysRoleService.save(role);
            }else {
		        old.setCreateUserId(getUserId());
		        old.setMenuIdList(role.getMenuIdList());
                sysRoleService.update(old);
            }
        }else {
            sysRoleService.save(role);
        }
		return R.ok().put("id",role.getRoleId());
	}
	/**
	 * 修改角色
	 */
	@SysLog("修改角色")
	@PostMapping("/update")
	@RequiresPermissions("sys:role:update")
	public R update(@RequestBody SysRoleEntity role){
		ValidatorUtils.validateEntity(role);
		
		role.setCreateUserId(getUserId());
		sysRoleService.update(role);
		
		return R.ok();
	}

    @SysLog("修改角色")
    @PostMapping("/updateinfo")
    @RequiresPermissions("sys:role:update")
    public R updateInfo(@RequestBody SysRoleEntity role){
	    sysRoleService.updateInfo(role);
        return R.ok();
    }
	/**
	 * 删除角色
	 */
	@SysLog("删除角色")
	@PostMapping("/delete")
	@Transactional
	@RequiresPermissions("sys:role:delete")
	public R delete(@RequestBody Long[] roleIds){
		for (Long id: roleIds) {
//			sysUserRoleService
			if (sysUserRoleService.findByRole(id).size() > 0) {
				throw new RRException("该角色已被使用");
			}
		}
//		sysRoleService.deleteBatch(roleIds);
		return R.ok();
	}
}
