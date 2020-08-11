package fscms.mods.bbs.util;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import fscms.mods.bbs.service.BbsRoleService;
import fscms.mods.bbs.vo.BbsRoleVO;
import fscms.mods.user.vo.UserVO;

@Component
public class BbsPowHelper {
	
	@Resource(name = "bbsRoleService")
	private BbsRoleService bbsRoleService;
	
	/**
	 * @Method Name	: getPow
	 * @작성일		: 2019. 12. 2.
	 * @작성자		: edmund85@naver.com
	 * @변경이력		:
	 * @Method 설명	: 게시판 권한 체크
	 */
	public boolean getPow(
			HttpServletRequest request,
			String powType,
			int bbsEstbsSn,
			UserVO userSession,
			String type) throws Exception {
		
		boolean pow = false;
		
		if(StringUtils.equals(powType, "N")) {
			// 모든 사용자
			pow = true;
		} else if(StringUtils.equals(powType, "U")) {
			// 회원 이상
			if(userSession != null && userSession.getUserSn() != null) {
				pow = true;
			}
		} else if(StringUtils.equals(powType, "A") && userSession != null && userSession.getUserSn() != null) {
			// 지정 사용자
			if(userSession.getRoleSn() == null) {
				// 사이트 지정 권한 설정
				// 여기 체크
			}
			
			BbsRoleVO bbsRoleVO = new BbsRoleVO();
			bbsRoleVO.setBbsEstbsSn(bbsEstbsSn);
			bbsRoleVO.setRoleSn(userSession.getRoleSn());
			bbsRoleVO.setRoleTy(type);
			BbsRoleVO bbsRole = bbsRoleService.selectObj(bbsRoleVO);
			
			if(bbsRole != null && bbsRole.getBbsRoleSn() != null) {
				pow = true;
			}
		}
		
		return pow;
	}

}
